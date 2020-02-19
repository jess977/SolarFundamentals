import java.lang.*;
import java.util.Scanner;

/*
TODO:
        Simplify the IO interaction;
        Make a method to remove the two cycles that are identical; - think it's changed now!
        Change user input with if else to cycle (avoid breaking with small mistakes) * 2 occurrences.
        Load profile needs to be improved and need hourly demand - Jess
Updates:
        in red.
*/
public class EnergyConsumption {

    public static double Daily_energy_demand;
    public static double Peak_load;
    public static double Annual_energy_demand;
    public static double AC_Peak_load;

    static {

        Scanner MyAppliances = new Scanner(System.in); // Scanner for user input

        System.out.println("Enter sequence of other desired appliances' power rating [W], seperated by a comma: "); // this corresponds to 1st line column 4 and can be removed
        String Power_Rating_input = MyAppliances.nextLine();
        String[] string_Power_Rating = Power_Rating_input.split(",");
        double[] Power_Rating = new double[string_Power_Rating.length];

        System.out.println("Enter respective quantities of each appliance, seperated by a comma: "); // this corresponds to 1st line column 2 and can be removed
        String Quantity_input = MyAppliances.nextLine();
        String[] string_Quantity = Quantity_input.split(",");
        int[] Quantity = new int[string_Quantity.length];

        System.out.println("How many hours a day each appliance will be used ? (seperated by a comma) "); // this corresponds to 1st line column 3 and can be removed
        String Daily_Operation_input = MyAppliances.nextLine();
        String[] string_Daily_Operation = Daily_Operation_input.split(",");
        double[] Daily_Operation = new double[string_Daily_Operation.length];

        System.out.println("Specify for each appliance if it's AC or DC type, seperated by a comma: "); // this corresponds to 1st line column 5 and can be removed
        String CurrentType_input = MyAppliances.nextLine();
        String[] string_CurrentType = CurrentType_input.split(",");
        double[] CurrentType = new double[string_CurrentType.length];

        for (int i = 0; i < Power_Rating.length; ++i) {
            Power_Rating[i] = Double.parseDouble(string_Power_Rating[i]);
            Quantity[i] = Integer.parseInt(string_Quantity[i]);
            Daily_Operation[i] = Double.parseDouble(string_Daily_Operation[i]);
            if (string_CurrentType[i] = "AC") {
                CurrentType[i] = 0.85;
            } else {
                CurrentType[i] = 1;
            }
        }

        System.out.println("Specify the number of days of autonomy for the battery: ");
        String AutonomyDays_input = MyAppliances.nextLine();
        double AutonomyDays = Double.parseDouble[AutonomyDays_input];

        for (int n = 0; n < Power_Rating.length; n++) {
            /*Enery consumption*/
            double Energy = Power_Rating[n] * Quantity[n] * Daily_Operation[n] / CurrentType[n] * 10 * Math.exp(-3); // in kWh
            double Load = Power_Rating[n] * Quantity[n] / CurrentType[n] * 10 * Math.exp(-3); // in kW
            if (CurrentType[n] = 0.85) {
                double AC_load = Power_Rating[n] * Quantity[n] / CurrentType[n] * 10 * Math.exp(-3); // in kW
            } else {
                double AC_load = 0;
            }

            // Load profile needs to be improved and need hourly demand
            Daily_energy_demand += Energy; // assumes all appliances are used for the same amount of hours everyday
            Annual_energy_demand = Daily_energy_demand * 365;
            Peak_load += Load; // assumes peak system load is the sum of loads, i.e. all used at the same time
            AC_Peak_load += AC_load;
        }

            MyAppliances.close(); // to prevent resource leaks
            SolarPVModule.io.write(Daily_energy_demand); // used in Solar PV code
            SolarPVModule.io.write(Peak_load); // used in Solar PV code
            SolarPVModule.io.write(Annual_energy_demand); // used in Solar PV code
            AdditionalComponents.io.write(AC_Peak_load); // used in Additional Components code

    }
}