import API.APIRequestInterface;
import API.RenewablesNinja;
import DTO.InputDTO;
import IO.IOInterface;
import IO.TerminalIO;

import java.io.*;
import java.lang.*;
import java.util.*;

/*
TODO:
    Last calculated variable are unused? Shouldn't it be output?;
    Remove throws;
    Remove constant MAGIC_NUMBER.
Extras:
    Make request interface and class.
Updates:
        Suppliers' GUI explanation in InputDTO
        Output results display suggestion at the end
*/
public class SolarPVModule { //Changed name, it was "SolarPV"
    final static IOInterface io = new TerminalIO();
    final static APIRequestInterface req = new RenewablesNinja();

    final static double MAGIC_NUMBER = 0;

    public static void main(String[] args) throws IOException {
        /* Import or call RES Suppliers code outputs*/
        InputDTO input = io.read();

        /*Importing Air Temperature from Renewables.ninja*/
        double[] T_air = req.getT_air();

        /* Typical STC conditions*/
        double T_NOCT = 20;
        double G_NOCT = 800;
        double T_cell_STC = 25;

        double[] PeakSunHours_vector = new double[SolarFundamentals.GlobalTiltedTrradiationIArray.size()];
        double[] New_Pmpp_vector = new double[input.Pmpp.length];
        double[] Annual_energy_output_vector = new double[input.Pmpp.length];
        double[] NumberOFModules_vector = new double[input.Pmpp.length];
        double[] Array_Cost_vector = new double[input.Pmpp.length];

        /*Peak Sun Hours in one year*/
        final int G = 1000; // STC of G = 1000 W/m^2 under which the performance of PV is measured
        int idx = 0;
        for (double GTI_element : SolarFundamentals.GlobalTiltedTrradiationIArray) { // for each element for GTI
            double PeakSunHours = GTI_element / G; // Peak Sun Hours for each hour in a year, array
            PeakSunHours_vector[idx++] = (PeakSunHours);
        }

        for (int k = 0; k < input.Pmpp.length; k++) { // for k module

            /*Temperature Influence on module Pmpp*/
            for (int n = 0; n < T_air.length; n++) { // update to length function later
                double Tcell = T_air[n] + SolarFundamentals.GlobalTiltedTrradiationIArray.get(n) * (input.NOCT[k] - T_NOCT) / G_NOCT;
                New_Pmpp_vector[n] = input.Pmpp[k] * (1 + input.PmppVariation[k] * (Tcell - T_cell_STC));
            }

            /*Annual energy output from module*/
            double Annual_energy_output = 0;
            double[] Hourly_Module_output_vector = new double[PeakSunHours_vector.length];
            for (int n = 0; n < PeakSunHours_vector.length; n++) { // update to length function later
                double Hourly_Module_output = PeakSunHours_vector[n] * New_Pmpp_vector[n]; // Energy output kWh array
                Hourly_Module_output_vector[n] = Hourly_Module_output; // creating hourly output vector
                Annual_energy_output += Hourly_Module_output; // adding hourly outputs in one year
            }
            Annual_energy_output_vector[k] = Annual_energy_output; // annual output of module k

            double NumberOfModules = EnergyConsumption.Annual_energy_demand / Annual_energy_output_vector[k];
            NumberOFModules_vector[k] = NumberOfModules; // for each module k

            /*Comparing hourly energy demand with hourly balance of system output to make sure demand is met at every hour - ask Supplier*/
            for (i = 0; i <= AdditionalComponents.Bank_Energy_Capacity_full. length; i++) {
                for (int n = 0; n < Hourly_Module_output_vector.length; n++) {
                    double Hourly_System_output = Hourly_Module_output_vector[n] * NumberOFModules_vector[k] + AdditionalComponents.Bank_Energy_Capacity_full[i]; // Energy output kWh system array
                    if (Hourly_System_output < EnergyConsumption.HourlyDemand[n]) { // Variable EnergyConsumption.HourlyDemand to be created
                        NumberOFModules_vector[k] = NumberOfModules + 1; }
                }
                break;
            }

            /*Array Cost*/
            double Array_Cost = NumberOFModules_vector[k] * input.Module_Cost[k];
            Array_Cost_vector[k] = Array_Cost;
        }

        // Sorting solar PV modules for one supplier only
        for (int l = 0; l < Array_Cost_vector.length-1; l++) {
            if (Array_Cost_vector[l] > Array_Cost_vector[l + 1]) {
                // Sorting cost array from low to high
                double Temporary_Cost = Array_Cost_vector[l]; // temporary cost variable
                Array_Cost_vector[l] = Array_Cost_vector[l + 1]; // swap costs
                Array_Cost_vector[l + 1] = Temporary_Cost;
                // Swap other arrays based on cost array
                double Temporary_No_Modules = NumberOFModules_vector[l];
                NumberOFModules_vector[l] = NumberOFModules_vector[l + 1]; // swap costs
                NumberOFModules_vector[l + 1] = Temporary_No_Modules;
                double Temporary_Pmpp = New_Pmpp_vector[l];
                New_Pmpp_vector[l] = New_Pmpp_vector[l + 1]; // swap costs
                New_Pmpp_vector[l + 1] = Temporary_No_Modules;
                double Temporary_annual_output = Annual_energy_output_vector[l];
                Annual_energy_output_vector[l] = Annual_energy_output_vector[l + 1]; // swap costs
                Annual_energy_output_vector[l + 1] = Temporary_No_Modules;
            }
        }

    }
}