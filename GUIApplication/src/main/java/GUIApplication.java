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
 */

class EnergifyScrollTablePanel extends JPanel {
    private JTable table;

    public EnergifyScrollTablePanel(Object[][] rowData, Object[] rowHeader) {
        DefaultTableModel model = new DefaultTableModel(rowData, rowHeader);
        table = new JTable();
        table.setModel(model);//table.setFillsViewportHeight(true);

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        addButton.addActionListener(actionEvent -> model.addRow(rowData[0]));
        removeButton.addActionListener(actionEvent -> {
            int idx = table.getSelectedRow();
            while (idx >= 0) {
                model.removeRow(idx);
                idx = table.getSelectedRow();
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.add(addButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(5,0)));
        buttonsPanel.add(removeButton);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        tablePanel.add(table, BorderLayout.CENTER);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(tablePanel);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(scrollPane);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(buttonsPanel);
    }
    public JTable getTable() {return table;}
}

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
        /*Object[][] appliancesRowData = {{"Edit", "Edit", "Edit", "Edit", "Edit"}};
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

        JPanel appliancesButtonsPanel = new JPanel();
        appliancesButtonsPanel.setLayout(new BoxLayout(appliancesButtonsPanel, BoxLayout.LINE_AXIS));
        appliancesButtonsPanel.add(appliancesAddButton);
        appliancesButtonsPanel.add(Box.createRigidArea(new Dimension(5,0)));
        appliancesButtonsPanel.add(appliancesRemoveButton);

        JPanel appliancesTablePanel = new JPanel();
        appliancesTablePanel.setLayout(new BorderLayout());
        appliancesTablePanel.add(appliancesTable.getTableHeader(), BorderLayout.PAGE_START);
        appliancesTablePanel.add(appliancesTable, BorderLayout.CENTER);

        ScrollPane appliancesScrollPane = new ScrollPane();
        appliancesScrollPane.add(appliancesTablePanel);

        JPanel appliancesPanel = new JPanel();
        appliancesPanel.setLayout(new BoxLayout(appliancesPanel, BoxLayout.PAGE_AXIS));

        appliancesPanel.add(appliancesScrollPane);
        appliancesPanel.add(Box.createRigidArea(new Dimension(0,5)));
        appliancesPanel.add(appliancesButtonsPanel);*/

        EnergifyScrollTablePanel appliancesPanel = new EnergifyScrollTablePanel(
                new Object[][] {{"Edit", "Edit", "Edit", "Edit", "Edit"}},
                new String[] {"Type of Appliance", "Power Rating", "Number of Appliance", "Operational Hours/day", "ADD/RM"});

        appliancesPanel.getTable().getColumnModel().getColumn(0) //Type Of Appliances
                .setCellEditor(new DefaultCellEditor(
                        new JComboBox<>(new String[] {"Lightbulb", "Radio", "TV", "Refrigerator", "Fan", "Mobile", "Electric Stove", "Other"})));

        appliancesPanel.getTable().getColumnModel().getColumn(4) //Current Type
                .setCellEditor(new DefaultCellEditor(
                        new JComboBox<>(new String[] {"DC", "AC"})));

        //----------------- Water & Sanitation -----//
        JPanel waterAndSanitationPanel = new JPanel();

        //----------------- Agriculture ------------//
        JPanel agriculturePanel = new JPanel();

        //----------------- Other Uses -------------//
        final String OTHER_USES_EMPTY_PANEL = "No";
        final String OTHER_USES_TABLE_PANEL = "Yes";

        EnergifyScrollTablePanel otherUsesFirstTablePanel = new EnergifyScrollTablePanel(
                new Object[][] {{"Edit", "Edit", "Edit"}},
                new String[] {"Activities requiring electricity", "Electricity consumed", "Operational Hours/day"});

        EnergifyScrollTablePanel otherUsesSecondTablePanel = new EnergifyScrollTablePanel(
                new Object[][] {{"Edit", "Edit", "Edit"}},
                new String[] {"Activities requiring heat", "Heat consumed", "Operational Hours/day"});

        JPanel otherUsesTablesPanel = new JPanel();
        otherUsesTablesPanel.setLayout(new BoxLayout(otherUsesTablesPanel, BoxLayout.PAGE_AXIS));
        otherUsesTablesPanel.add(otherUsesFirstTablePanel);
        otherUsesTablesPanel.add(otherUsesSecondTablePanel);

        JPanel otherUsesCardPanel = new JPanel(new CardLayout());
        otherUsesCardPanel.add(new JPanel(), OTHER_USES_EMPTY_PANEL);
        otherUsesCardPanel.add(otherUsesTablesPanel, OTHER_USES_TABLE_PANEL);

        JComboBox<String> otherUsesComboBox = new JComboBox<>(new String[] {OTHER_USES_EMPTY_PANEL, OTHER_USES_TABLE_PANEL});
        otherUsesComboBox.addItemListener(evt -> {
            CardLayout cl = (CardLayout)(otherUsesCardPanel.getLayout());
            cl.show(otherUsesCardPanel, (String)evt.getItem());
        });

        JPanel otherUsesComboBoxPanel = new JPanel();
        otherUsesComboBoxPanel.add(otherUsesComboBox);

        JPanel otherUsesPanel = new JPanel();
        otherUsesPanel.setLayout(new BoxLayout(otherUsesPanel, BoxLayout.PAGE_AXIS));
        otherUsesPanel.add(otherUsesComboBoxPanel);
        otherUsesPanel.add(otherUsesCardPanel);

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