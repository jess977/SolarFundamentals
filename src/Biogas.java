import DTO.InputDTO;
import IO.IOInterface;
import IO.TerminalIO;

import java.io.*;
import java.lang.*;
import java.util.*;
/*
TO DO:
     - yet to finish
*/

public class Biogas {
    final static IOInterface io = new TerminalIO();

    public static void main(String[] args) throws IOException {
        /* Import or call RES Suppliers code outputs*/
        InputDTO input = io.read();
        /*----------------------------------------Biogas MATLAB code-------------------------------------------------*/
        // User Inputs - add to TerminalIO of user
        input.Gas_consumed;
        input.livestock_manure_vol;
        input.Number;
        input.organic_waste;
        intput.buckets;
        // Supplier inputs or litterature from supplier
        livestock_volatile_content; // from litterature and value "associated" with the livestock name from dropdown
        input.organic_volatile_percentage; // from litterature and value "associated" with crop name from dropdown
        intput.Water_Vol_ratio; // depends on the biodigester? input from supplier ideally!
        input.Biodigesters_suppliers;
        input.Type;
        input.Biodigester_type;
        input.Total_plant_vol;
        input.Total_plant_vol;

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
        // Ave_daily_temperature(Ave_daily_temperature(:,1)==-999)=0;
        // Ave_daily_temperature(Ave_daily_temperature<16) = 16;
        // Ave_daily_temperature(Ave_daily_temperature>33) = 33;

        // Yield factors for biogas production based on temperature and retention time
        // import excel spreasheet or build database

        //Initialising values
        double[] Digester_vol = new double[Total_plant_vol.length];
        double[] Gas_storage_vol = new double[Total_plant_vol.length];
        double[] Retention_time = new double[Total_plant_vol.length];
        double[] Annual_production = new double[Total_plant_vol.length];
        double[] Average_daily = new double[Total_plant_vol.length];
        double[] Y_column_vector = new double[T_air.length];

        for (n = 0; n <= input.Total_plant_vol.length; n++) {
            // Finding the digester volume and the gas holder volume
            if (Biodigester_type[n] = 1) {
                Digester_vol[n] = 0.8 * input.Total_plant_vol[n]; // in m^3 account for feedstock and water volumes
                Gas_storage_vol[n] = 0.2 * input.Total_plant_vol[n]; // in m^3
            } else if (Biodigester_type[n] = 2) {
                Digester_vol[n] = 0.7 * input.Total_plant_vol[n]; // in m^3 account for feedstock and water volumes
                Gas_storage_vol[n] = 0.3 * input.Total_plant_vol[n]; // in m^3
            } else if (Biodigester_type[n] = 3) {
                Digester_vol[n] = 0.75 * input.Total_plant_vol[n]; // in m^3 account for feedstock and water volumes
                Gas_storage_vol[n] = 0.25 * input.Total_plant_vol[n]; // in m^3
            }
            // Retention time in days
            Retention_time(i) = Digester_vol(i) / Total_Feedstock_Vol; // in days
            if Retention_time(i) < 6
            Retention_time(i) = 6;
            elseif Retention_time (i) > 100
            Retention_time(i) = 100;
            end
            Biodigester_supplier(i, 6) = Retention_time(i);

            // Yield factors for biogas production based on temperature and retention time
            Y_row_index = find(Yield_factors(:,2) >=Retention_time(i), 1);
            for j = 1:length(Ave_daily_temperature)
            Y_column_index = find(Yield_factors(1,:) >=Ave_daily_temperature(j), 1);
            Y_column_vector(j) = Yield_factors(Y_row_index, Y_column_index); // changes with daily temperature
            end

            // Calculating the daily gas production
            Daily_gas_production = (Y_column_vector. * Digester_vol(i) * Total_volatile_content) / 1000; // in m^3 for each day withing a year
            Annual_production(i) = sum(Daily_gas_production); // in m^3 in one year
            Biodigester_supplier(i, 2) = Annual_production(i);
            Average_daily(i) = sum(Daily_gas_production) / 365; // in m^3 daily average
            Biodigester_supplier(i, 1) = Average_daily(i);

            // Comparing biogas produced with gas storage
            Stored_gas = Average_daily(i) - Gas_consumed;
            if (Gas_storage_vol(i) < Stored_gas) ||(Gas_consumed > Average_daily(i))
            Biodigester_supplier(i,:) =0;
            Type(i,:) =0;
            end
        }
        Biodigester_supplier(~any(Biodigester_supplier,2),:) = []; %Deletes all the zero rows
        Type(Type(:,1)=='0') = [];

        // Sorting array
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
                double Temporary_Biodigester_vol = Biodigester_vol[r];
                Biodigester_vol[r] = Biodigester_vol[r + 1]; // swap costs
                Biodigester_vol[r + 1] = Temporary_Biodigester_vol;
            }
        }

    }
}

