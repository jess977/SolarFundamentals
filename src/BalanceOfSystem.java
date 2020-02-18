import DTO.InputDTO;
import IO.IOInterface;
import IO.TerminalIO;

import java.io.*;
import java.lang.*;
import java.util.*;

/*
TODO:
     Import SolarPVModule & BalanceOfSystem arrays here and combine for output results
*/
public class BalanceOfSystem {
    final static IOInterface io = new TerminalIO();

    public static void main(String[] args) throws IOException {
        /* Import or call RES Suppliers code outputs*/
        InputDTO input = io.read();

        // Add
        /*
          (first column)        |    (second column)         |      (third column)      |   (fourth column)    |    (fifth column)
           Product name         |    Supplier naame          |        Array_Cost        |  Number of modules   |       New_Pmpp             --> this line could be an inprinted text box on the GUI ?
        ------------------------------------------------------------------------------------------------------------------------------------
                                                              1st cost-effective option
                                                              2nd cost-effective option
                                                                         ...
        -------------------------------------------------------------------------------------------------------------------------------------
               (sixth column)        |    (seventh column)     |     (8th column)         |
       Annual_energy_output_array    |    supplier contact     |   supplier address       |
        -----------------------------|-------------------------|--------------------------|
                                                              1st cost-effective option
                                                              2nd cost-effective option
                                                                         ...
        -------------------------------------------------------------------------------------------------------------------------------------
        */
    }
}