import DTO.InputDTO;
import IO.IOInterface;
import IO.TerminalIO;

import java.io.*;
import java.lang.*;
import java.util.*;
/*
TO DO:
     - everything.
*/

public class Biogas {
    final static IOInterface io = new TerminalIO();

    public static void main(String[] args) throws IOException {
        /* Import or call RES Suppliers code outputs*/
        InputDTO input = io.read();
        /*----------------------------------------Biogas MATLAB code-------------------------------------------------*/
        // User Inputs - add to TermialIO
        input.Gas_consumed;
        input.livestock_manure_vol;
        input.livestock_volatile_content; input.Number;
        input.crop_waste;
        intput.buckets;
        input.crop_volatile_percentage;
        intput.Water_Vol_ratio;
        input.Biodigesters_suppliers;

         // Total Feedstock Volume in kg/day
        double Feedstock_manure = sum(livestock_manure_vol.*Number); // in kg/day or L/day, but assumed to be the same as the volumetric in Litres
        // Volatile content in kg/m^3
        double total_livestock_volatile_content = sum(livestock_volatile_content.*Number); // in kg/day

        // Feedsock from crops a day
        double Feedstock_dry = sum(crop_waste.*buckets); // volume in kg/day
        double total_crop_volatile_content = sum(crop_waste.*buckets.*crop_volatile_percentage); // volatile content in kg/day

        //Total Volume of animal and crop feedstock
        double Waste_Vol = Feedstock_manure + Feedstock_dry; // in kg/day
        double Total_Feedstock_Vol = (Waste_Vol*Water_Vol_ratio)/1000; // in m^3/day, hence divided by 1000
        double Total_volatile_content = (total_livestock_volatile_content + total_crop_volatile_content)/Total_Feedstock_Vol; // in kg/m^3

        /*Importing Air Temperature from Renewables.ninja*/
        double[] T_air = req.getT_air();
        Ave_daily_temperature(Ave_daily_temperature(:,1)==-999)=0; % turn to zero
        Ave_daily_temperature(Ave_daily_temperature<16) = 16;
        Ave_daily_temperature(Ave_daily_temperature>33) = 33;

        // Yield factors for biogas production based on temperature and retention time
        A = readtable('YIELD_FACTORS.csv');
        Yield_factors = table2array(A);

        // Bio-digesters suppliers
        double Biodigesters_suppliers = table2array(Biodigesters);
        double Type = Biodigesters_suppliers (:,1);
        double Biodigester_type = Biodigesters_suppliers (:,2);
        double Total_plant_vol = Biodigesters_suppliers (:,3);

        // Biodigester supplier info
        Type = table2array(Biodigesters_suppliers (:,1));
        Type = string(Type);
        Total_plant_vol = table2array(Biodigesters_suppliers (:,2));

        //Initialising values
        double Digester_vol = zeros(length(Total_plant_vol),1);
        double Gas_storage_vol = zeros(length(Total_plant_vol),1);
        double Retention_time = zeros(length(Total_plant_vol),1);
        double Annual_production = zeros(length(Total_plant_vol),1);
        double Average_daily = zeros(length(Total_plant_vol),1);
        double Y_column_vector = zeros(length(Ave_daily_temperature),1);

        Biodigester_supplier = [Average_daily,Annual_production,Total_plant_vol,Digester_vol,Gas_storage_vol,Retention_time];

        for i = 1:length(Total_plant_vol)
                // Finding the digester volume and the gas holder volume
        if Biodigester_type(i) == 1
        Digester_vol(i) = 0.8*Total_plant_vol(i); % in m^3 account for feedstock and water volumes
        Biodigester_supplier(i,4) = Digester_vol(i);
        Gas_storage_vol(i) = 0.2*Total_plant_vol(i); % in m^3
        Biodigester_supplier(i,5) = Gas_storage_vol(i);
        elseif Biodigester_type(i) == 2
        Digester_vol(i) = 0.7*Total_plant_vol(i); % in m^3 account for feedstock and water volumes
        Biodigester_supplier(i,4) = Digester_vol(i);
        Gas_storage_vol(i) = 0.3*Total_plant_vol(i); % in m^3
        Biodigester_supplier(i,5) = Gas_storage_vol(i);
        elseif Biodigester_type(i) == 3
        Digester_vol(i) = 0.75*Total_plant_vol(i); % in m^3 account for feedstock and water volumes
        Biodigester_supplier(i,4) = Digester_vol(i);
        Gas_storage_vol(i) = 0.25*Total_plant_vol(i); % in m^3
        Biodigester_supplier(i,5) = Gas_storage_vol(i);
        end

        // Retention time in days
        Retention_time(i) = Digester_vol(i)/Total_Feedstock_Vol; // in days
        if Retention_time(i) < 6
        Retention_time(i)=6;
        elseif Retention_time(i) > 100
        Retention_time(i)=100;
        end
        Biodigester_supplier(i,6) = Retention_time(i);

        // Yield factors for biogas production based on temperature and retention time
        Y_row_index = find(Yield_factors(:,2) >= Retention_time(i),1);
        for j=1:length(Ave_daily_temperature)
        Y_column_index = find(Yield_factors(1,:) >= Ave_daily_temperature(j),1);
        Y_column_vector(j) = Yield_factors(Y_row_index,Y_column_index); // changes with daily temperature
        end

        // Calculating the daily gas production
        Daily_gas_production = (Y_column_vector.*Digester_vol(i)*Total_volatile_content)/1000; // in m^3 for each day withing a year
        Annual_production(i) = sum(Daily_gas_production); // in m^3 in one year
        Biodigester_supplier(i,2) = Annual_production(i);
        Average_daily(i) = sum(Daily_gas_production)/365; // in m^3 daily average
        Biodigester_supplier(i,1) = Average_daily(i);

        // Comparing biogas produced with gas storage
        Stored_gas = Average_daily(i)- Gas_consumed;
        if (Gas_storage_vol(i) < Stored_gas) || (Gas_consumed > Average_daily(i))
        Biodigester_supplier(i,:) = 0;
        Type(i,:) = 0;
        end
     end
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
        // Matrix displaying recommended charge regulators
        Group vector arrays into multidimensional array - add more arrays: supplier name, other costs, country location,
        double[][] Biodigester_grid = new double[Biodigester_cost.length][3]; // array of rows = length and 4 columns
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= Controller_cost.length; u++) {
                Biodigester_grid[j][i] = Biodigester_cost[j];
                Biodigester_grid[j][i + 1] = Biodigester_type[j];
                Biodigester_grid[j][i + 2] = Biodigester_vol[j];
            }
        }
    }
}

