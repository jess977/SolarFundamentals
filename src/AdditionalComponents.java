import DTO.InputDTO;
import IO.IOInterface;
import IO.TerminalIO;

import java.io.*;
import java.lang.*;
import java.util.*;
/*
TO DO:
     - Calculations done with only 1st inverter - change to allow all possible combinations
     - Storing arrays method to be finished
*/

public class AdditionalComponents {
    final static IOInterface io = new TerminalIO();

    public static void main(String[] args) throws IOException {
        /* Import or call RES Suppliers code outputs*/
        InputDTO input = io.read();
        /*----------------------------------------Inverter Selection code-------------------------------------------------*/
        // Default Values
        double Safety_factor = 1.25;

        for (int k = 1; k <= Inverter_P_min.length; k++) {
            double Actual_AC_Power = EnergyConsumption.AC_Peak_load * Safety_factor / input.Inverter_efficiency[k];
            if (input.Inverter_P_min[k] > Actual_AC_Power || (input.Inverter_P_max[k] < Actual_AC_Power) {
                input.Inverter_cost[k] = 0;
                input.Inverter_P_min[k] = 0;
                input.Inverter_P_max[k] = 0;
                input.Inverter_efficiency[k] = 0;
                // can inverter power increase if more than one in series or parallel?
            }
        }

        // Sorting arrays
        for (int r = 0; r < Inverter_cost.length; r++) {
            if (Inverter_cost[r] > Inverter_cost[r + 1]) {
                // Sorting cost array from low to high
                double Temporary_Inverter_cost = Inverter_cost[r]; // temporary cost variable
                Inverter_cost[r] = Inverter_cost[r + 1]; // swap costs
                Inverter_cost[r + 1] = Temporary_Inverter_cost;
                // Swap other arrays based on cost array
                double Temporary_Inverter_P_min = Inverter_P_min[r];
                Inverter_P_min[r] = Inverter_P_min[r + 1]; // swap costs
                Inverter_P_min[r + 1] = Temporary_Inverter_P_min;
                double Temporary_Inverter_P_max = Inverter_P_max[r];
                Inverter_P_max[r] = Inverter_P_max[r + 1]; // swap costs
                Inverter_P_max[r + 1] = Temporary_Inverter_P_max;
                double Temporary_Inverter_efficiency = Inverter_efficiency[r];
                Inverter_efficiency[r] = Inverter_efficiency[r + 1]; // swap costs
                Inverter_efficiency[r + 1] = Temporary_Inverter_efficiency;
            }
        }
        // Table displaying recommended inverters
        double[][] Inverter_grid = new double[Inverter_cost.length][4]; // array of rows = length and 4 columns
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r <= Inverter_cost.length; r++) {
                Inverter_grid[r][c] = Inverter_cost[r];
                Inverter_grid[r][c + 1] = Inverter_efficiency[r];
                Inverter_grid[r][c + 2] = Inverter_P_min[r];
                Inverter_grid[r][c + 3] = Inverter_P_max[r];
            }
        }
        /*---------------------------------------------Battery selection--------------------------------------------------*/
        // Default Values
        double DOD_max = 0.8;
        double Safe_Inverter_efficiency = 0.85;
        // Initialising variables
        double[] Required_no_of_batteries_array = new double[Battery_Voltage.length];
        double[] Bank_Energy_Capacity_array = new double[Battery_Voltage.length];
        for (int k = 0; k <= Inverter_efficiency.length; k++) {
            for (int n = 0; n <= Battery_Voltage.length; n++) {
                // Required amount of charge to be delivered by the battery
                double Required_Capacity = EnergyConsumption.Daily_energy_demand * EnergyConsumption.AutonomyDays / (DOD_max * input.Inverter_voltage[k] * input.Inverter_efficiency[k] * input.Battery_efficiency[n]);
                // Required number of batteries to form the bank
                double Required_no_of_batteries = Required_Capacity / input.Battery_Capacity[n];

                // Add C-rate method from RET course
                double Battery_current = Battery_Capacity[n] / C_rate[n];
                double Required_current = EnergyConsumption.Daily_energy_demand / (input.Battery_Voltage[n] * Required_no_of_batteries * EnergyConsumption.AutonomyDays * 24);

                // code to zero rows from array
                if (Required_current > Battery_current) {
                    Bank_Energy_Capacity_array[k] = 0;
                    Required_no_of_batteries_array[k] = 0;
                } else {
                    Bank_Energy_Capacity_array[k] = input.Battery_Voltage[k] * Required_no_of_batteries * input.Battery_Capacity[k];
                    Required_no_of_batteries_array[k] = Required_no_of_batteries;
                }
            }
            // Sorting arrays of n batteries for inverter k
            for (int n = 0; n < Battery_cost.length; n++) {
                if (Battery_cost[n] > Battery_cost[n + 1]) {
                // Sorting cost array from low to high
                double Temporary_Battery_cost = Battery_cost[n]; // temporary cost variable
                Battery_cost[n] = Battery_cost[n + 1]; // swap costs
                Battery_cost[n + 1] = Temporary_Battery_cost;
                // Swap other arrays based on cost array
                double Temporary_no_of_batteries = Required_no_of_batteries_array[n];
                Required_no_of_batteries_array[n] = Required_no_of_batteries_array[n + 1]; // swap costs
                Required_no_of_batteries_array[n + 1] = Temporary_no_of_batteries;
                double Temporary_Bank_Energy_Capacity = Bank_Energy_Capacity_array[n];
                Bank_Energy_Capacity_array[n] = Bank_Energy_Capacity_array[n + 1]; // swap costs
                Bank_Energy_Capacity_array[n + 1] = Temporary_Bank_Energy_Capacity;
                }
            }
            // Matrix of n batteries given inverter k
            double[][] Battery_grid = new double[Battery_cost.length][3]; // array of rows = length and 4 columns
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i <= Battery_cost.length; i++) {
                Battery_grid[i][j] = Battery_cost[j];
                Battery_grid[i][j + 1] = Required_no_of_batteries_array[i];
                Battery_grid[i][j + 2] = Bank_Energy_Capacity_array[i];
                }
            }
            // Storing battery grid for inverter k ?
            int[][] myArray = {{41, 52, 63}, {74, 85, 96}, {93, 82, 71} };
            double[][] copyArray = new double[Battery_grid.length][]; // array of rows = length and 0 columns
            for (int i = 0; i < copyArray.length; ++i) {
                copyArray[i] = new int[Battery_grid[i].length];
                for (int j = 0; j < copyArray[i].length; ++j) {
                    copyArray[i][j] = Battery_grid[i][j];
                }
            }
        }

        /*---------------------------------------Charge Controller selection----------------------------------------------*/
        for (int k = 0; k <= Controller_volt_in_lower.length; k++) {
            double Max_allowable_controller_current = EnergyConsumption.Peak_load * Safety_factor / Inverter_Voltage[n];

            // code to zero rows from array
            if (input.Inverter_Voltage[1] < input.Controller_min_output_voltage[k] || input.Inverter_Voltage[1] > input.Controller_max_output_voltage[k] || Max_allowable_controller_current =>
            input.Controller_current[k]){
                input.Controller_cost[k] = 0;
                input.Controller_min_output_voltage[k] = 0;
                input.Controller_max_output_voltage[k] = 0;
                input.Controller_current[k] = 0;
                input.Controller_min_input_voltage[k] = 0;

            }
        }

        // Sorting array
        for (int r = 0; r < Controller_cost.length; r++) {
            if (Controller_cost[r] > Controller_cost[r + 1]) {
                // Sorting cost array from low to high
                double Temporary_Controller_cost = Controller_cost[r]; // temporary cost variable
                Controller_cost[r] = Controller_cost[r + 1]; // swap costs
                Controller_cost[r + 1] = Temporary_Controller_cost;
                // Swap other arrays based on cost array
                double Temporary_Controller_current = Controller_current[r];
                Controller_current[r] = Controller_current[r + 1]; // swap costs
                Controller_current[r + 1] = Temporary_Controller_current;
            }
        }
        // Matrix displaying recommended charge regulators
        double[][] Controller_grid = new double[Controller_cost.length][3]; // array of rows = length and 4 columns
        for (int c= 0; c < 3; c++) {
            for (int r = 0; r <= Controller_cost.length; r++) {
                Controller_grid[r][c] = Controller_cost[r];
                Controller_grid[r][c + 1] = Controller_current[r];
            }
        }
    }
}

