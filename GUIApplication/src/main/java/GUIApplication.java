import javax.swing.*;
import java.awt.*;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridLayout;
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
        introPanel.setMaximumSize(new Dimension(1000, 54));

        JPanel whatTypeOfEntityPanel = new JPanel();
        JPanel howFarIsTheBlankFromTheGridPanel = new JPanel();
        JPanel isTheUserCurrentlyLocatedInThePanel = new JPanel();

        JLabel whatTypeOfEntityLabel = new JLabel("\t\tWhat type of entity?");
        JLabel howFarIsTheBlankFromTheGridLabel = new JLabel();
        JLabel isTheUserCurrentlyLocatedInTheLabel = new JLabel();

        JComboBox<String> whatTypeOfEntityComboBox = new JComboBox<>(new String[]{"Household", "Community", "Facility"});
        JComboBox<String> howFarIsTheBlankFromTheGridComboBox = new JComboBox<>(new String[]{"Rural/Remote area","Part of urban area"});
        JComboBox<String> isTheUserCurrentlyLocatedInTheComboBox = new JComboBox<>(new String[]{"Yes","No"});

        whatTypeOfEntityPanel.setLayout(new GridLayout(2,1,0,0));
        howFarIsTheBlankFromTheGridPanel.setLayout(new GridLayout(2,1,0,0));
        isTheUserCurrentlyLocatedInThePanel.setLayout(new GridLayout(2,1,0,0));

        whatTypeOfEntityComboBox.addActionListener(actionEvent -> {
            howFarIsTheBlankFromTheGridLabel.setText(String.format("\t\tHow far is the %s from the grid?", whatTypeOfEntityComboBox.getSelectedItem().toString().toLowerCase()));
            isTheUserCurrentlyLocatedInTheLabel.setText(String.format("\t\tIs the user currently located in the %s?", whatTypeOfEntityComboBox.getSelectedItem().toString().toLowerCase()));
        });

        whatTypeOfEntityComboBox.setSelectedIndex(0);

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

        Object[][] appliancesRowData = {{"Edit", "Edit", "Edit", "Edit", "Edit"}};
        DefaultTableModel appliancesModel = new DefaultTableModel(appliancesRowData, new String[] {"Type of Appliance", "Power Rating", "Number of Appliance", "Operational Hours/day", "ADD/RM"});
        JTable appliancesTable = new JTable();
        appliancesTable.setModel(appliancesModel);
        //appliancesTable.setFillsViewportHeight(true);

        appliancesTable.getColumnModel().getColumn(0) //Type Of Appliances
                .setCellEditor(new DefaultCellEditor(
                        new JComboBox<>(new String[] {"Lightbulb", "Radio", "TV", "Refrigerator", "Fan", "Mobile", "Electric Stove", "Other"})));

        appliancesTable.getColumnModel().getColumn(4) //Current Type
                .setCellEditor(new DefaultCellEditor(
                        new JComboBox<>(new String[] {"DC", "AC"})));

        JButton appliancesAddButton = new JButton("Add");
        JButton appliancesRemoveButton = new JButton("Remove");
        appliancesAddButton.addActionListener(actionEvent -> appliancesModel.addRow(appliancesRowData[0]));
        appliancesRemoveButton.addActionListener(actionEvent -> {
            int idx = appliancesTable.getSelectedRow();
            while (idx >= 0) {
                appliancesModel.removeRow(idx);
                idx = appliancesTable.getSelectedRow();
            }
        });

        //---------------------- Water & Sanitation ---------------------//
        JPanel WaterAndSanitationPanel = new JPanel();

        //-------------------------- Agriculture ------------------------//
        JPanel AgriculturePanel = new JPanel();

        //----------------- Other Uses -------------//
        JPanel otherUsesPanel = new JPanel();

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
        Container rootPane = root.getContentPane();

        root.setMinimumSize(new Dimension(873,290));
        root.setLayout(new BoxLayout(rootPane, BoxLayout.PAGE_AXIS));
        //root.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.BLACK));

        //rootPane.setLayout(new BorderLayout(8,6));
        //rootPane.setBackground(Color.green);

        root.add(Box.createRigidArea(new Dimension(0,20)));
        root.add(introPanel);
        root.add(Box.createRigidArea(new Dimension(0,20)));
        root.add(tabbedPane);
        //Button b = new Button("Tmp");
        //b.addActionListener( a -> System.out.println(introPanel.getMinimumSize()));//java.awt.Dimension[width=873,height=54]
        //root.add(b);
        root.pack();
        root.setVisible(true);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //------------------------------------------//
    }
}