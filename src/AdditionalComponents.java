import DTO.InputDTO;
import IO.IOInterface;
import IO.TerminalIO;

import java.io.*;
import java.lang.*;
import java.util.*;

/*
TO DO:
     - Calculations done with only 1st charge controller - change to allow all possible combinations
     - Meeting with suppliers and understand methods they use
     - Add C-rate method from RET course
     - Storing n battery parameters (columns) for inverter k (row) - Marcos to check sintax inside []
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
                input.Inverter_voltage = 0;
            }
        }

        // Sorting inverters for supplier output only - add "input." to variables - ask Marcos
        for (int r = 0; r < Inverter_cost.length; r++) {
            if (Inverter_cost[r] > Inverter_cost[r + 1]) {
                // Sorting cost array from low to high
                double Temporary_Inverter_cost = Inverter_cost[r]; // temporary cost variable
                Inverter_cost[r] = Inverter_cost[r + 1]; // swap costs
                Inverter_cost[r + 1] = Temporary_Inverter_cost;
                // Swap other arrays based on cost array
                double Temporary_Inverter_voltage = Inverter_voltage[r];
                Inverter_voltage[r] = Inverter_voltage[r + 1]; // swap voltages
                Inverter_voltage[r + 1] = Temporary_Inverter_voltage;
                double Temporary_Inverter_P_min = Inverter_P_min[r];
                Inverter_P_min[r] = Inverter_P_min[r + 1]; // swap min power
                Inverter_P_min[r + 1] = Temporary_Inverter_P_min;
                double Temporary_Inverter_P_max = Inverter_P_max[r];
                Inverter_P_max[r] = Inverter_P_max[r + 1]; // swap max power
                Inverter_P_max[r + 1] = Temporary_Inverter_P_max;
                double Temporary_Inverter_efficiency = Inverter_efficiency[r];
                Inverter_efficiency[r] = Inverter_efficiency[r + 1]; // swap efficiencies
                Inverter_efficiency[r + 1] = Temporary_Inverter_efficiency;
            }
        }

        /*---------------------------------------------Battery selection--------------------------------------------------*/
        // Default Values
        double DOD_max = 0.8;
        double Safe_Inverter_efficiency = 0.85;
        // Initialising variables
        double[] Required_no_of_batteries_array = new double[input.Battery_Voltage.length];
        double[] Bank_Energy_Capacity_array = new double[input.Battery_Voltage.length];
        double[] Inv_plus_bat_cost = new double[input.Battery_Voltage.length];
        // Initialising the addition of n batteries per inverter k - Marcos to check sintax inside []
        double[] Battery_cost_full = new double[Inverter_efficiency.length * input.Battery_Voltage.length]; // outside nested for loop
        double[] Required_no_of_batteries_full = new double[Inverter_efficiency.length * input.Battery_Voltage.length]; // outside nested for loop
        double[] Bank_cost_full = new double[Inverter_efficiency.length * input.Battery_Voltage.length]; // outside nested for loop
        double[] Bank_Energy_Capacity_full = new double[Inverter_efficiency.length * input.Battery_Voltage.length]; // outside nested for loop
        double[] Inv_plus_bat_cost_full = new double[Inverter_efficiency.length * input.Battery_Voltage.length]; // outside nested for loop
        // Battery calculations for inverter k
        for (int k = 0; k <= Inverter_efficiency.length; k++) {
            if (input.Inverter_cost != 0) { // must be different from zero
                for (int n = 0; n <= Battery_Voltage.length; n++) {
                    // Required amount of charge to be delivered by the battery
                    double Required_Capacity = EnergyConsumption.Daily_energy_demand * EnergyConsumption.AutonomyDays / (DOD_max * input.Inverter_voltage[k] * input.Inverter_efficiency[k] * input.Battery_efficiency[n]);
                    // Required number of batteries to form the bank
                    double Required_no_of_batteries = Required_Capacity / input.Battery_Capacity[n];
                    double Bank_cost[n] = Required_no_of_batteries * input.Battery_cost[n];
                    // Add C-rate method from RET course
                    double Battery_current = input.Battery_Capacity[n] / input.C_rate[n];
                    double Required_current = EnergyConsumption.Daily_energy_demand / (input.Battery_Voltage[n] * Required_no_of_batteries * EnergyConsumption.AutonomyDays * 24);
                    // code to zero rows from array
                    if (Required_current > Battery_current) {
                        Bank_Energy_Capacity_array[k] = 0;
                        Required_no_of_batteries_array[k] = 0;
                    } else {
                        Bank_Energy_Capacity_array[k] = input.Battery_Voltage[k] * Required_no_of_batteries * input.Battery_Capacity[k];
                        Required_no_of_batteries_array[k] = Required_no_of_batteries;
                    }
                    // Create array with added cost of inverter k and battery n
                    Inv_plus_bat_cost[n] = input.Inverter_cost[k] + Bank_cost[n];

                    // Storing n battery parameters (columns) for inverter k (row) - Marcos to check sintax inside []
                    Battery_cost_full[k * Inverter_efficiency.length + n] = input.Battery_cost[n];
                    Required_no_of_batteries_full[k * Inverter_efficiency.length + n] = Required_no_of_batteries_array[n];
                    Bank_cost_full[k * Inverter_efficiency.length + n] = Bank_cost[n];
                    Bank_Energy_Capacity_full[k * Inverter_efficiency.length + n] = Bank_Energy_Capacity_array[n];
                    Inv_plus_bat_cost_full[k * Inverter_efficiency.length + n] = Inv_plus_bat_cost[n];
                }
            }
        }
        // Sorting batteries for inverter for all inverters - add "input." to variables - ask Marcos
        for (int n = 0; n < Battery_cost_full.length; n++) { // I mean for length of the row
            if (Inv_plus_bat_cost_full[n] > Inv_plus_bat_cost_full[n + 1]) {
                // Sorting cost array from low to high based on battery + inverter cost
                double Temporary_Battery_cost = Battery_cost_full[n]; // temporary cost variable
                Battery_cost_full[n] = Battery_cost_full[n + 1]; // swap costs
                Battery_cost_full[n + 1] = Temporary_Battery_cost;
                double Temporary_no_of_batteries = Required_no_of_batteries_full[n];
                Required_no_of_batteries_full[n] = Required_no_of_batteries_full[n + 1]; // swap number of batteries
                Required_no_of_batteries_full[n + 1] = Temporary_no_of_batteries;
                double Temporary_Inv_plus_bat_cost = Inv_plus_bat_cost_full[n];
                Inv_plus_bat_cost_full[n] = Inv_plus_bat_cost_full[n + 1]; // swap total costs
                Inv_plus_bat_cost_full[n + 1] = Temporary_Inv_plus_bat_cost;
                double Temporary_Bank_cost = Bank_cost_full[n];
                Bank_cost_full[n] = Bank_cost_full[n + 1]; // swap total costs
                Required_Bank_cost_full[n + 1] = Temporary_Bank_cost;
                double Temporary_Bank_Energy_Capacity = Bank_Energy_Capacity_full[n];
                Bank_Energy_Capacity_full[n] = Bank_Energy_Capacity_full[n + 1]; // swap costs
                Bank_Energy_Capacity_full[n + 1] = Temporary_Bank_Energy_Capacity;
            }
        }
        // No need to create a matrix, keep seperate arrays for each parameter.
        /*---------------------------------------Charge Controller selection----------------------------------------------*/
        for (int k = 0; k <= Inverter_efficiency.length; k++) { // for only first battery/inverter combo or all of them? discuss supplier
            for (int n = 0; n = Controller_volt_in_lower.length; n++) {
                double Max_allowable_controller_current = EnergyConsumption.Peak_load * Safety_factor / Inverter_Voltage[n];
                // code to zero rows from array
                if (input.Inverter_Voltage[k] < input.Controller_min_output_voltage[n] || input.Inverter_Voltage[k] > input.Controller_max_output_voltage[n] || Max_allowable_controller_current => input.Controller_current[n]){
                    input.Controller_cost[n] = 0;
                    input.Controller_min_output_voltage[n] = 0;
                    input.Controller_max_output_voltage[n] = 0;
                    input.Controller_current[n] = 0;
                    input.Controller_min_input_voltage[n] = 0;
                }
            }
        }

        // Sorting array - - add "input." to variables - ask Marcos
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

    }
}

