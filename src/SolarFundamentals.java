import java.io.IOException;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
TODO:
        Constants in cycle being created every time;
        DST is requested from the user why an if else;
        Remove uses of SolarPV.MAGIC_NUMBER;
        Remove empty catch blocks * 2 occurrences;
        Change user input with if else to cycle (avoid breaking with small mistakes) * 1 occurrence.
Updates:
        Made some changes. Can all imssing weather data be obtained from Renewables.ninja - discuss with Marcos
        There will be no user inputs for SolarFundamentals
*/
public class SolarFundamentals {

    static ArrayList<Double> BeamRadiationArray = new ArrayList<>();
    static ArrayList<Double> GlobalTiltedTrradiationIArray = new ArrayList<>();

    static {
        ArrayList<Double> SolarZenith = new ArrayList<>();
        ArrayList<Double> IncidentAngle = new ArrayList<>();
        for (int n = 1; n <= 365; n++) { // For each calendar day

            // Time Zone Meridian, Local Longitude, Daylight Saving Time and Equation of Time
            double A = 0.258;
            double B = -7.416;
            double C = -3.648;
            double D = -9.228;
            double Local_Time = SolarPV.MAGIC_NUMBER; // local time from renewables.ninja ?
            double CoordinatedUniversalTime = SolarPV.MAGIC_NUMBER; // Coordinated Universal Time (UTC) from renewables.ninja ?
            if (Local_Time - CoordinatedUniversalTime <= 12) {
                double UTC_offset = Local Time - CoordinatedUniversalTime;
            } else {
                double UTC_offset = -(24 - Local Time + CoordinatedUniversalTime);
            }
            double TimeZoneMeridian = -15 * UTC_offset; // Time Zone
            double LocalLongitudeInDegrees = SolarPV.MAGIC_NUMBER; // Longitude from Renewables.ninja ?

            double EquationOfTime = A * Math.cos(2 * Math.PI * (n - 1) / 365) + B * Math.sin(2 * Math.PI * (n - 1) / 365) + C * Math.cos(4 * Math.PI * (n - 1) / 365) + D * Math.sin(4 * Math.PI * (n - 1) / 365); // in min
            double LocalLatitudeInDegrees = SolarPV.MAGIC_NUMBER; // Latitude from Renewables.ninja ?
            double LocalLatitude = LocalLatitudeInDegrees * Math.PI / 180; // in radians

            // Daylight Saving Time
            // maybe we need a small database based on this list ? --> https://www.timeanddate.com/time/dst/2020.html
            double n_DST_summer = SolarPV.MAGIC_NUMBER; // day of the year for which the DST changes to summer time - depends on the location
            double n_DST_winter = SolarPV.MAGIC_NUMBER; // day of the year for which the DST changes to winter time - depends on the location
            String n_DST = "No DST in 2020"; // temporarily
            double DST;
            if (n > n_DST_summer && n < n_DST_winter) {
                DST = 1;
            } else if (n < n_DST_summer && n > n_DST_winter || n_DST.equals("No DST in 2020")) {
                DST = 0;
            }

            // The Declination Angle
            double DeclinationAngle = Math.asin(0.39795 * Math.cos(2 * Math.PI * (n - 173) / 365)); // in radians

            /* Solar Time */
            for (int ClockTime = 1; ClockTime <= 24; ClockTime = ClockTime + 1) { // for each hour of the Clock Time
                double SolarTime = ClockTime + (TimeZoneMeridian - LocalLongitudeInDegrees) / 15 + EquationOfTime / 60 + DST;

                /* Solar Position */
                // Hour Angle
                double HourAngle = Math.PI * (SolarTime - 12) / 12; // in radians

                // Solar Zenith Angle
                SolarZenith.add(Math.acos(Math.cos(LocalLatitude) * Math.cos(DeclinationAngle) * Math.cos(HourAngle) + Math.sin(LocalLatitude) * Math.sin(DeclinationAngle))); // in radians

                // Solar Azimuth Angle
                double SolarAzimuth = Math.signum(HourAngle) * Math.abs(Math.acos((Math.cos(SolarZenith.get(ClockTime-1)) * Math.sin(LocalLatitude) - Math.sin(DeclinationAngle)) / (Math.sin(SolarZenith.get(ClockTime-1)) * Math.cos(LocalLatitude)))); // in radians

                double SlopeAngle;
                double SurfaceAzimuth;

                // Ground fixed optimum - later add rooftop option
                string SurfacePosition  = "Ground Fixed Optimum";
                SlopeAngle = LocalLatitude; // Slope Angle in radians
                if (LocalLatitude >= 0) { // Northen Hemisphere
                    double SurfaceAzimuthInDegrees = 0; // Due South
                } else (LocalLatitude < 0) { // Southrn Hemisphere
                    double SurfaceAzimuthInDegrees = 180; // Due North
                }
                SurfaceAzimuth = SurfaceAzimuthInDegrees * Math.PI / 180; // in radians

                // Incidence Angle
                IncidentAngle.add(Math.acos(Math.cos(SlopeAngle) * Math.cos(SolarZenith.get(ClockTime-1)) + Math.sin(SlopeAngle) * Math.sin(SolarZenith.get(ClockTime-1)) * Math.cos(SolarAzimuth - SurfaceAzimuth))); // in radians
            }
        }

        /*DIF from Renewables.ninja*/
        double[] DIF = null;
        try {
            DIF = SolarPV.req.getDIF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*GHI from Renewables.ninja*/
        double[] GHI = null;
        try {
            GHI = SolarPV.req.getGHI();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int n = 0; n < GHI.length; n++) { // for each hour in a year
            // Beam Radiation
            double BeamRadiation = (GHI[n] - DIF[n]) / Math.cos(SolarZenith.get(n)); // needs to take into account every element of GHI and Diffuse Radiation
            BeamRadiationArray.add(BeamRadiation);

            // Global Tilted Irradiation - for Solar PV
            double GTI = BeamRadiation * Math.cos(IncidentAngle.get(n)) + DIF[n]; // W/m^2
            GlobalTiltedTrradiationIArray.add(GTI);
        }

        System.out.println(GlobalTiltedTrradiationIArray); // used in Solar PV code
    }
}