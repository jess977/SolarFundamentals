package DTO;

public class InputDTO {
    public double[] Pmpp, NOCT, PmppVariation, Module_Cost, Inverter_P_min, Inverter_efficiency, Inverter_voltage,
    Inverter_cost, Controller_cost, Controller_min_output_voltage, Controller_max_output_voltage, Controller_current,
    Controller_min_input_voltage, Battery_Voltage, Battery_Capacity, Battery_efficiency, C_rate, Battery_cost;

    public InputDTO(double[] Pmpp, double[] NOCT, double[] PmppVariation, double[] Module_Cost, double[] Inverter_P_min, double[] Inverter_efficiency, double[] Inverter_voltage,
                    double[] Inverter_cost, double[] Controller_cost, double[] Controller_min_output_voltage, double[] Controller_max_output_voltage, double[] Controller_current,
                    double[] Controller_min_input_voltage, double[] Battery_Voltage, double[] Battery_Capacity, double[] Battery_efficiency, double[] C_rate, double[] Battery_cost) {
        this.Pmpp = Pmpp;
        this.NOCT = NOCT;
        this.PmppVariation = PmppVariation;
        this.Module_Cost = Module_Cost;
        this.Inverter_P_min;
        this.Inverter_efficiency;
        this.Inverter_voltage;
        this.Inverter_cost;
        this.Controller_cost;
        this.Controller_min_output_voltage;
        this.Controller_max_output_voltage;
        this.Controller_current;
        this.Controller_min_input_voltage;
        this.Battery_Voltage;
        this.Battery_Capacity;
        this.Battery_efficiency;
        this.C_rate;
        this.Battery_cost
    }
}

/* Ideally input should be in form of a table - see "GUI idea" in "Energify > Tools"
   In contrast with customer GUI, suppliers' input should be stored in a database

   (first column)       |       (second column)      |     (third column)        |    (fourth column)      |
    Product name        |        Supplier Name       |      Module cost          |    Solar panel Pmpp     | -->  this line could be an inprinted text box on the GUI ?
-----------------------------------------------------------------------------------------------------------|
   blank box that       |     same as column one     |   blank box that intakes  |  Same as column three   |
    intakes text        |                            | numbers? otherwise intakes|                         |
                        |                            |   text and converts it    |                         |
blank box not allowed!  |   blank box not allowed!   |  blank box not allowed!   |  blank box not allowed! |
-----------------------------------------------------------------------------------------------------------|
       (fifth column)        |     (sixth column)      |    (seventh column)     |     (eight column)      |
            NOCT             |     PmmpVariation       |         Contact         |        address          |
-------------------------------------------------------|-------------------------|-------------------------|
     same as column three    |  same as column three   |    same as column one   |    same as column one   |
  blank box not allowed!     |  blank box not allowed! |  blank box not allowed! |  blank box not allowed! |
--------------------------------------------------------
 ideally supplier can add as many lines as they wish ? each line corresponding to a different product, have like a + button to add lines or - to remove
-------------------------------------------------------------------------------------------------------------------------------------
*/