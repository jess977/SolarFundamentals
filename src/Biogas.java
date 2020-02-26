import DTO.InputDTO;
import IO.IOInterface;
import IO.TerminalIO;

import java.io.*;
import java.lang.*;
import java.util.*;
/*
TO DO:
     - yet to finish
     - Confirm with supplier: gas holder & digester volume, retention time method,
     - Find Index method on Java?
*/

public class Biogas {
    final static IOInterface io = new TerminalIO();

    public static void main(String[] args) throws IOException {
        /* Import or call RES Suppliers code outputs*/
        InputDTO input = io.read();
        /*----------------------------------------Biogas MATLAB code-------------------------------------------------*/
        // User Inputs - add to TerminalIO of user
        input.Gas_consumed; //double
        input.livestock_manure_vol; //double
        input.Number; //double
        input.organic_waste; //double
        intput.buckets; //double
        // Supplier inputs or litterature from supplier
        livestock_volatile_content; //double // from litterature and value "associated" with the livestock name from dropdown
        input.organic_volatile_percentage; //double // from litterature and value "associated" with crop name from dropdown
        intput.Water_Vol_ratio; //double// depends on the biodigester? input from supplier ideally!
        input.Total_plant_vol; //double
        input.Type; // string
        input.Biodigester_cost;

        if (input.Type.equals("Fixed Dome")) {
            Biodigester_type = 1;
        } else if (input.Type.equals("Floating Drum")) {
            Biodigester_type = 2;
        } else if (input.Type.equals("Balloon")) {
            Biodigester_type = 3;
        }

        Biodigester_type;

        double Feedstock_manure_sum = 0;
        double total_livestock_volatile_content_sum = 0;
        double Feedstock_dry_sum = 0;
        double total_organic_volatile_content_sum = 0;
        for (int n = 0; n < Number.length; n++) {
            // Total Feedstock Volume in kg/day
            double Feedstock_manure = input.livestock_manure_vol[n] * input.Number[n]; // in kg/day or L/day, but assumed to be the same as the volumetric in Litres
            Feedstock_manure_sum += Feedstock_manure;
            // Volatile content in kg/m^3
            double total_livestock_volatile_content = livestock_volatile_content * input.Number[n]; // from litterature and value "associated" with the livestock name from dropdown
            total_livestock_volatile_content_sum += total_livestock_volatile_content; // in kg/day
        }
        for (int n = 0; n < buckets.length; n++) {
            // Feedsock from crops a day
            double Feedstock_dry = input.organic_waste[n] * input.buckets[n]; // volume in kg/day
            Feedstock_dry_sum += Feedstock_dry;
            double total_organic_volatile_content = input.organic_waste[n] * input.buckets[n] * organic_volatile_percentage; // from litterature and value "associated" with crop name from dropdown
            total_organic_volatile_content_sum += total_organic_volatile_content; // volatile content in kg/day
        }
        // Total Volume of animal and crop feedstock
        double Waste_Vol = Feedstock_manure_sum + Feedstock_dry_sum; // in kg/day
        double Total_Feedstock_Vol = (Waste_Vol * Water_Vol_ratio) / 1000; // in m^3/day, hence divided by 1000 - litterature from supplier
        double Total_volatile_content = (total_livestock_volatile_content_sum + total_organic_volatile_content_sum) / Total_Feedstock_Vol; // in kg/m^3

        /*Importing Air Temperature from Renewables.ninja*/
        double[] T_air = req.getT_air();
        // Some element are equal to -999, equal them to zero
        // T_air(T_air(:,1)==-999)=0;
        // T_air(T_air<16) = 16;
        // T_air(T_air>33) = 33;

        // Yield factors for biogas production based on temperature and retention time
        // import excel spreasheet or build database

        //Initialising values
        double[] Digester_vol = new double[Total_plant_vol.length];
        double[] Gas_storage_vol = new double[Total_plant_vol.length];
        double[] Retention_time = new double[Total_plant_vol.length];
        double[] Annual_production = new double[Total_plant_vol.length];
        double[] Average_daily = new double[Total_plant_vol.length];

        for (k = 0; k <= input.Total_plant_vol.length; k++) { // for each biodigester
            // Finding the digester volume and the gas holder volume - ask supplier
            if (Biodigester_type[k] = 1) {
                Digester_vol[k] = 0.8 * input.Total_plant_vol[k]; // in m^3 account for feedstock and water volumes
                Gas_storage_vol[k] = 0.2 * input.Total_plant_vol[k]; // in m^3
            } else if (Biodigester_type[k] = 2) {
                Digester_vol[k] = 0.7 * input.Total_plant_vol[k]; // in m^3 account for feedstock and water volumes
                Gas_storage_vol[k] = 0.3 * input.Total_plant_vol[k]; // in m^3
            } else if (Biodigester_type[k] = 3) {
                Digester_vol[k] = 0.75 * input.Total_plant_vol[k]; // in m^3 account for feedstock and water volumes
                Gas_storage_vol[k] = 0.25 * input.Total_plant_vol[k]; // in m^3
            }
            // Retention time in days - confirm method with supplier
            Retention_time[k] = Digester_vol[k] / Total_Feedstock_Vol; // in days
            if (Retention_time[k] < 6) {
                Retention_time[k] = 6;
            } else if (Retention_time[k] > 100) {
                Retention_time[k] = 100;
            }

            // Yield factors for biogas production based on temperature and retention time - confirm with supplier
            // look for Find function in Java
            Y_row_index = find(Yield_factors(:,2) >=Retention_time(k), 1);
            for n = 1; n <= T_air.length; n++){
                // look for Find function in Java
                Y_column_index = find(Yield_factors(1,:) =>T_air(n), 1);
                Y_factor_daily = Yield_factors(Y_row_index, Y_column_index); // changes with daily temperature
                // Calculating the daily gas production
                double Daily_gas_production = Y_factor_daily * Digester_vol[k] * Total_volatile_content / 1000; // in m^3 for each day withing a year
                Annual_production[k] += Daily_gas_production; // in m^3 in one year
            }

            Average_daily[k] = Annual_production / 365; // in m^3 daily average

            // Comparing biogas produced with gas storage
            Stored_gas[k] = Average_daily[k] - Gas_consumed;
            if (Gas_storage_vol[k] < Stored_gas[k] ||(Gas_consumed > Average_daily[k]) {
                Biodigester_cost[k] = 0;
                Digester_vol[k] = 0;
                Gas_storage_vol[k] = 0;
                Retention_time[k] = 0;
                Annual_production[k] = 0;
                Average_daily[k] = 0;
            }
        }

        // Sorting array - add average_daily, annual_production, stored_gas
        for (int r = 0; r < Biodigester_cost.length; r++) {
            if (Biodigester_cost[r] > Biodigester_cost[r + 1]) {
                // Sorting cost array from low to high
                double Temporary_Biodigester_cost = Biodigester_cost[r]; // temporary cost variable
                Biodigester_cost[r] = Biodigester_cost[r + 1]; // swap costs
                Biodigester_cost[r + 1] = Temporary_Biodigester_cost;
                // Swap other arrays based on cost array
                double Temporary_Biodigester_type = Biodigester_type[r];
                Biodigester_type[r] = Biodigester_type[r + 1]; // swap costs
                Biodigester_type[r + 1] = Temporary_Biodigester_type;
                double Temporary_Digester_vol = Digester_vol[r];
                Digester_vol[r] = Digester_vol[r + 1]; // swap costs
                Digester_vol[r + 1] = Temporary_Digester_vol;
                double Temporary_Gas_storage_vol = Gas_storage_vol[r];
                Gas_storage_vol[r] = Gas_storage_vol[r + 1]; // swap costs
                Gas_storage_vol[r + 1] = Temporary_Gas_storage_vol;
                double Temporary_Annual_production = Annual_production[r];
                Annual_production[r] = Annual_production[r + 1]; // swap costs
                Annual_production[r + 1] = Temporary_Annual_production;
                double Temporary_Average_daily = Average_daily[r];
                Average_daily[r] = Average_daily[r + 1]; // swap costs
                Average_daily[r + 1] = Temporary_Average_daily;
            }
        }

    }
}

