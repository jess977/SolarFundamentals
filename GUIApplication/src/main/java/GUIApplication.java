import javax.swing.*;
import java.awt.*;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/* TO DO:
       - Add grid layout rows and columns in Appliances
       - Agriculture not finished
 */

public class GUIApplication {
    public static void main(String[] args) {
        //----------------- Intro ------------------//
        JPanel introPanel = new JPanel();
        introPanel.setLayout(new GridLayout(1,3,0,0));

        JPanel whatTypeOfEntityPanel = new JPanel();
        JPanel howFarIsTheBlankFromTheGridPanel = new JPanel();
        JPanel isTheUserCurrentlyLocatedInThePanel = new JPanel();

        JLabel whatTypeOfEntityLabel = new JLabel("What type of entity?");
        JLabel howFarIsTheBlankFromTheGridLabel = new JLabel("How far is the ______ from the grid?");
        JLabel isTheUserCurrentlyLocatedInTheLabel = new JLabel("Is the user currently located in the ______");

        JComboBox<String> whatTypeOfEntityComboBox = new JComboBox<>(new String[]{"Household", "Community", "Facility"});
        JComboBox<String> howFarIsTheBlankFromTheGridComboBox = new JComboBox<>(new String[]{"Rural/Remote area","Part of urban area"});
        JComboBox<String> isTheUserCurrentlyLocatedInTheComboBox = new JComboBox<>(new String[]{"Yes","No"});

        whatTypeOfEntityPanel.setLayout(new GridLayout(2,1,0,0));
        howFarIsTheBlankFromTheGridPanel.setLayout(new GridLayout(2,1,0,0));
        isTheUserCurrentlyLocatedInThePanel.setLayout(new GridLayout(2,1,0,0));

        whatTypeOfEntityComboBox.addActionListener(actionEvent -> {
            howFarIsTheBlankFromTheGridLabel.setText(String.format("How far is the %s from the grid?", whatTypeOfEntityComboBox.getSelectedItem()));
            isTheUserCurrentlyLocatedInTheLabel.setText(String.format("Is the user currently located in the %s", whatTypeOfEntityComboBox.getSelectedItem()));
        });

        whatTypeOfEntityPanel.add(whatTypeOfEntityLabel);
        howFarIsTheBlankFromTheGridPanel.add(howFarIsTheBlankFromTheGridLabel);
        isTheUserCurrentlyLocatedInThePanel.add(isTheUserCurrentlyLocatedInTheLabel);

        whatTypeOfEntityPanel.add(whatTypeOfEntityComboBox);
        howFarIsTheBlankFromTheGridPanel.add(howFarIsTheBlankFromTheGridComboBox);
        isTheUserCurrentlyLocatedInThePanel.add(isTheUserCurrentlyLocatedInTheComboBox);

        introPanel.add(whatTypeOfEntityPanel);
        introPanel.add(howFarIsTheBlankFromTheGridPanel);
        introPanel.add(isTheUserCurrentlyLocatedInThePanel);

        //----------------- Appliances -------------//
        JPanel appliancesPanel = new JPanel();
        appliancesPanel.setLayout(new GridLayout(1,5,5,5));

        JPanel TypeOfAppliancePanel = new JPanel();
        JPanel PowerRatingPanel = new JPanel();
        JPanel NumberOfAppliancesPanel = new JPanel();
        JPanel OperationalHoursPanel = new JPanel();
        JPanel TypeOfCurrentPanel = new JPanel();

        TypeOfAppliancePanel.setLayout(new GridLayout(1,2,0,0));
        PowerRatingPanel.setLayout(new GridLayout(1,2,0,0));
        NumberOfAppliancesPanel.setLayout(new GridLayout(1,2,0,0));
        OperationalHoursPanel.setLayout(new GridLayout(1,2,0,0));
        TypeOfCurrentPanel.setLayout(new GridLayout(1,2,0,0));

        JComboBox<String> TypeOfApplianceComboBox = new JComboBox<>(new String[]{"Lightbulb","Radio","TV","Refrigerator",
                "Fan","Mobile","Electric Stove","Other"});
        TypeOfApplianceComboBox.setEditable(true);
        JComboBox<String> CurrentTypeComboBox = new JComboBox<>(new String[]{"AC","DC"});

        JTextField PowerRatingTextField = new JTextField(20);
        PowerRatingTextField.addActionListener(actionEvent -> {
            String PowerRatingInput = PowerRatingTextField.getText(); // later change so that each value to be stored as array element
        });
        JTextField NumberOfAppliancesTextField = new JTextField(20);
        NumberOfAppliancesTextField.addActionListener(actionEvent -> {
            String NumberOfAppliancesInput = NumberOfAppliancesTextField.getText(); // later change so that each value to be stored as array element
        });
        JTextField OperationalHoursTextField = new JTextField(20);
        OperationalHoursTextField.addActionListener(actionEvent -> {
            String OperationalHoursInput = OperationalHoursTextField.getText(); // later change so that each value to be stored as array element
        });

        TypeOfAppliancePanel.add(new Label("Type of Appliance"));
        PowerRatingPanel.add(new Label("Power Rating"));
        NumberOfAppliancesPanel.add(new Label("Number of Appliance"));
        OperationalHoursPanel.add(new Label("Operational Hours/day"));
        TypeOfCurrentPanel.add(new Label("Current Type"));

        TypeOfAppliancePanel.add(TypeOfApplianceComboBox); //check
        TypeOfCurrentPanel.add(CurrentTypeComboBox); //check

        PowerRatingPanel.add(PowerRatingTextField);
        NumberOfAppliancesPanel.add(NumberOfAppliancesTextField);
        OperationalHoursPanel.add(OperationalHoursTextField);

        appliancesPanel.add(TypeOfAppliancePanel);
        appliancesPanel.add(PowerRatingPanel);
        appliancesPanel.add(NumberOfAppliancesPanel);
        appliancesPanel.add(OperationalHoursPanel);
        appliancesPanel.add(TypeOfCurrentPanel);

        //---------------------- Water & Sanitation ---------------------//
        JPanel WaterAndSanitationPanel = new JPanel();

        //-------------------------- Agriculture ------------------------//
        JPanel AgriculturePanel = new JPanel();
        AgriculturePanel.setLayout(new GridLayout(3,3,0,0));

        JLabel IrrigatedCropLandLabel = new JLabel("Is there crop land to be irrigated?");
        JLabel LivestockWateringLabel = new JLabel("How much water is given to livestock if any?");
        JLabel AgriculturalPracticesLabel = new JLabel("Are any of the following agricultural practices applied?");

        JComboBox<String> IrrigatedCropLandComboBox = new JComboBox<>(new String[]{"Yes","No"});
        IrrigatedCropLandComboBox.setEditable(true);
        // create Case function such that Yes = TypeOfCropsPanel appears, No = no panel
        IrrigatedCropLandComboBox.addActionListener(actionEvent -> {

        });
        JComboBox<String> LivestockWateringComboBox = new JComboBox<>(new String[]{"No Livestock"});
        LivestockWateringComboBox.setEditable(true);
        // need to add option to make listener work
        LivestockWateringComboBox.addActionListener(actionEvent -> {
            String LivestockWatering = LivestockWateringComboBox.getSelectedItem();
        });

        JPanel TypeOfCropsPanel = new JPanel();
        JPanel AreaCoveredPanel = new JPanel();

        TypeOfCropsPanel.setLayout(new GridLayout(1,2,0,0));
        AreaCoveredPanel.setLayout(new GridLayout(1,2,0,0));

        JLabel IrrigatedCropLandLabel = new JLabel("Type of Crops");
        JLabel LivestockWateringLabel = new JLabel("Area Covered");

        JComboBox<String> IrrigatedCropLandComboBox = new JComboBox<>(new String[]{"Cucumber","Maise","Millet","Onions",
                "Potatoes","Tomatoes","Wheat"
        });
        // IrrigatedCropLandComboBox.addItemListener

        JTextField AreaCoveredTextField = new JTextField(20);
        AreaCoveredTextField.addActionListener(actionEvent -> {
            String AreaCoveredInput = AreaCoveredTextField.getText(); // later change so that each value to be stored as array element
        });

        //----------------- Other Uses -------------//
        JPanel OtherUsesPanel = new JPanel();

        //----------------- Tabs -------------------//
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Appliances", appliancesPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


        tabbedPane.addTab("Water & Sanitation", waterAndSanitationPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Agriculture", agriculturePanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.addTab("Other Uses", otherUsesPanel);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        //----------------- Root -------------------//
        JFrame root = new JFrame("Energify");
        root.setMinimumSize(new Dimension(1000,200));
        root.setLayout(new GridLayout(2,1,5,5));
        //root.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.BLACK));

        //Container c = root.getContentPane();
        //c.setLayout(new BorderLayout(8,6));
        //c.setBackground(Color.green);

        root.add(introPanel);
        root.add(tabbedPane);
        root.setVisible(true);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //------------------------------------------//
    }
}