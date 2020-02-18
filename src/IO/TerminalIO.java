package IO;

import DTO.InputDTO;

import java.util.Scanner;

public class TerminalIO implements IOInterface {

    private Scanner Specifications = new Scanner(System.in); // Scanner for user input

    @Override
    public InputDTO read() {
        // Supplier Inputs for Solar PV Module
        System.out.println("Enter sequence of Rated Power Output modules [W], seperated by a comma: ");
        String Pmpp_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Pmpp = Pmpp_input.split(","); // storing splitted characters by commas into string array
        double[] Pmpp = new double[string_Pmpp.length];

        System.out.println("Enter sequence of Nominal Operating Cell Temperatures of modules [C], seperated by a comma: ");
        String NOCT_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_NOCT = NOCT_input.split(","); // storing splitted characters by commas into string array
        double[] NOCT = new double[string_NOCT.length];

        System.out.println("Enter sequence of temperature coefficients of power [%/C], seperated by a comma: ");
        String PmppVariation_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_PmppVariation = PmppVariation_input.split(","); // storing splitted characters by commas into string array
        double[] PmppVariation = new double[string_PmppVariation.length];

        System.out.println("Enter sequence of modules cost [$], seperated by a comma: ");
        String Module_Cost_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Module_Cost = Module_Cost_input.split(","); // storing splitted characters by commas into string array
        double[] Module_Cost = new double[string_Module_Cost.length];

        for (int i = 0; i < Pmpp.length; i++) {
            Pmpp[i] = Double.parseDouble(string_Pmpp[i]);
            NOCT[i] = Double.parseDouble(string_NOCT[i]);
            PmppVariation[i] = Double.parseDouble(string_PmppVariation[i]);
            Module_Cost[i] = Double.parseDouble(string_Module_Cost[i]);
        }

        // Supplier Inputs for Inverters
        System.out.println("Enter sequence of Minimum Inverter Power [W], seperated by a comma: ");
        String Inverter_P_min_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Inverter_P_min = Inverter_P_min_input.split(","); // storing splitted characters by commas into string array
        double[] Inverter_P_min = new double[string_Inverter_P_min.length];

        System.out.println("Enter sequence of Maximum Inverter Power [W], seperated by a comma: ");
        String Inverter_P_max_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Inverter_P_max = Inverter_P_max_input.split(","); // storing splitted characters by commas into string array
        double[] Inverter_P_max = new double[string_Inverter_P_max.length];

        System.out.println("Enter sequence of Inverter Efficiency [%], seperated by a comma: ");
        String Inverter_efficiency_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Inverter_efficiency = Inverter_efficiency_input.split(","); // storing splitted characters by commas into string array
        double[] Inverter_efficiency = new double[string_Inverter_efficiency.length];

        System.out.println("Enter sequence of Inverter Voltage [V], seperated by a comma: ");
        String Inverter_voltage_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Inverter_voltage = Inverter_voltage_input.split(","); // storing splitted characters by commas into string array
        double[] Inverter_voltage = new double[string_Inverter_voltage.length];

        System.out.println("Enter sequence of Inverter Cost [USD], seperated by a comma: ");
        String Inverter_cost_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Inverter_cost = Inverter_cost_input.split(","); // storing splitted characters by commas into string array
        double[] Inverter_cost = new double[string_Inverter_cost.length];

        for (int i = 0; i < string_Inverter_P_min.length; ++i) {
            Inverter_P_min[i] = Double.parseDouble(string_Inverter_P_min[i]);
            Inverter_P_max[i] = Double.parseDouble(string_Inverter_P_max[i]);
            Inverter_efficiency[i] = Double.parseDouble(string_Inverter_efficiency[i]);
            Inverter_voltage[i] = Double.parseDouble(string_Inverter_voltage[i]);
            Inverter_cost[i] = Double.parseDouble(string_Inverter_cost[i]);
        }

        // Supplier Inputs for Charge Controllers
        System.out.println("Enter sequence of Minimum Controller Voltage Input [V], seperated by a comma: ");
        String Controller_min_input_voltage_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] Controller_min_input_voltage = Controller_min_input_voltage_input.split(","); // storing splitted characters by commas into string array
        double[] Controller_min_input_voltage = new double[string_Controller_min_input_voltage.length];

        System.out.println("Enter sequence of Minimum Controller Voltage Output [V], seperated by a comma: ");
        String Controller_min_output_voltage_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Controller_min_output_voltage = Controller_min_output_voltage_input.split(","); // storing splitted characters by commas into string array
        double[] Controller_min_output_voltage = new double[string_Controller_min_output_voltage.length];

        System.out.println("Enter sequence of Maximum Controller Voltage Output [V], seperated by a comma: ");
        String Controller_max_output_voltage_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Controller_max_output_voltage = Controller_max_output_voltage_input.split(","); // storing splitted characters by commas into string array
        double[] Controller_max_output_voltage = new double[string_Controller_max_output_voltage.length];

        System.out.println("Enter sequence of Controller Current [h], seperated by a comma: ");
        String Controller_current_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Controller_current = Controller_current_input.split(","); // storing splitted characters by commas into string array
        double[] Controller_current = new double[string_Controller_current.length];

        System.out.println("Enter sequence of Controller Cost [USD], seperated by a comma: ");
        String Controller_cost_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Controller_cost = Controller_cost_input.split(","); // storing splitted characters by commas into string array
        double[] Controller_cost = new double[string_Controller_cost.length];

        for (int i = 0; i < string_Controller_cost.length; ++i) {
            Controller_cost[i] = Double.parseDouble(string_Controller_cost[i]);
            Controller_min_output_voltage[i] = Double.parseDouble(string_Controller_min_output_voltage[i]);
            Controller_max_output_voltage[i] = Double.parseDouble(string_Controller_max_output_voltaget[i]);
            Controller_current[i] = Double.parseDouble(string_Controller_current[i]);
            Controller_min_input_voltage[i] = Double.parseDouble(string_Controller_min_input_voltage[i]);
        }

        // Supplier Inputs for Batteries
        System.out.println("Enter sequence of Battery Voltage [V], seperated by a comma: ");
        String Battery_Voltage_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Battery_Voltage = Battery_Voltage_input.split(","); // storing splitted characters by commas into string array
        double[] Battery_Voltage = new double[string_Battery_Voltage.length];

        System.out.println("Enter sequence of Battery Capacity [Ah], seperated by a comma: ");
        String Battery_Capacity_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Battery_Capacity = Battery_Capacity_input.split(","); // storing splitted characters by commas into string array
        double[] Battery_Capacity = new double[string_Battery_Capacity.length];

        System.out.println("Enter sequence of Battery Efficiency [%], seperated by a comma: ");
        String Battery_efficiency_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Battery_efficiency = Battery_efficiency_input.split(","); // storing splitted characters by commas into string array
        double[] Battery_efficiency = new double[string_Battery_efficiency.length];

        System.out.println("Enter sequence of Battery C-rate [h], seperated by a comma: ");
        String C_rate_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_C_rate = C_rate_input.split(","); // storing splitted characters by commas into string array
        double[] C_rate = new double[string_C_rate.length];

        System.out.println("Enter sequence of Battery Cost [USD], seperated by a comma: ");
        String Battery_cost_input = Specifications.nextLine(); // obtains line string from GIS/GUI Input Data
        String[] string_Battery_cost = Battery_cost_input.split(","); // storing splitted characters by commas into string array
        double[] Battery_cost = new double[string_Battery_cost.length];

        for (int i = 0; i < string_Controller_cost.length; ++i) {
            Battery_Voltage[i] = Double.parseDouble(string_Battery_Voltage[i]);
            Battery_Capacity[i] = Double.parseDouble(string_Battery_Capacity[i]);
            Battery_efficiency[i] = Double.parseDouble(string_Battery_efficiency[i]);
            C_rate[i] = Double.parseDouble(string_C_rate[i]);
            Battery_cost[i] = Double.parseDouble(string_Battery_cost[i]);
        }

        return new InputDTO(Pmpp, NOCT, PmppVariation, Module_Cost, Inverter_P_min, Inverter_efficiency, Inverter_voltage,
                Inverter_cost, Controller_cost, Controller_min_output_voltage, Controller_max_output_voltage, Controller_current,
                Controller_min_input_voltage, Battery_Voltage, Battery_Capacity, Battery_efficiency, C_rate, Battery_cost);
    }

    @Override
    public double read(String req) {
        System.out.println(req);
        return Specifications.nextInt();
    }

    @Override
    public void write(Object str) {
        System.out.println(str);
    }
}