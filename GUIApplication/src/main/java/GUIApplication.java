import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

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
import java.beans.ConstructorProperties;

/* TO DO:
        - Finish water & sanitation
        - Fix resize
 */

class PreferredSizeComboBox<T> extends JComboBox<T> {
    public PreferredSizeComboBox(T[] items) {
        super(items);
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension max = super.getMaximumSize();
        max.width = getPreferredSize().width;
        return max;
    }
}

class EnergifyCardPanel extends JPanel {
    static final String EMPTY_PANEL = "No";
    static final String SHOW_PANEL = "Yes";

    public EnergifyCardPanel(JPanel show) {
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.add(new JPanel(), EMPTY_PANEL);
        cardPanel.add(show, SHOW_PANEL);

        PreferredSizeComboBox<String> comboBox = new PreferredSizeComboBox<>(new String[] {EMPTY_PANEL, SHOW_PANEL});
        comboBox.addItemListener(evt -> {
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            cl.show(cardPanel, (String)evt.getItem());
        });

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.add(comboBox);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(comboBoxPanel);
        add(cardPanel);
    }

    public EnergifyCardPanel(String label, JPanel show) {
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.add(new JPanel(), EMPTY_PANEL);
        cardPanel.add(show, SHOW_PANEL);

        PreferredSizeComboBox<String> comboBox = new PreferredSizeComboBox<>(new String[] {EMPTY_PANEL, SHOW_PANEL});
        comboBox.addItemListener(evt -> {
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            cl.show(cardPanel, (String)evt.getItem());
        });

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.PAGE_AXIS));
        comboBoxPanel.add(new Label(label));
        comboBoxPanel.add(comboBox);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(comboBoxPanel);
        add(cardPanel);
    }

    public EnergifyCardPanel(String label, JPanel show, String[] options) {
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.add(new JPanel(), options[0]);
        cardPanel.add(show, options[1]);

        PreferredSizeComboBox<String> comboBox = new PreferredSizeComboBox<>(options);
        comboBox.addItemListener(evt -> {
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            cl.show(cardPanel, (String)evt.getItem());
        });

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.PAGE_AXIS));
        comboBoxPanel.add(new Label(label));
        comboBoxPanel.add(comboBox);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(comboBoxPanel);
        add(cardPanel);
    }
}

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

        PreferredSizeComboBox<String> whatTypeOfEntityComboBox = new PreferredSizeComboBox<>(new String[]{"Household", "Community", "Facility"});
        PreferredSizeComboBox<String> howFarIsTheBlankFromTheGridComboBox = new PreferredSizeComboBox<>(new String[]{"Rural/Remote area","Part of urban area"});
        PreferredSizeComboBox<String> isTheUserCurrentlyLocatedInTheComboBox = new PreferredSizeComboBox<>(new String[]{"Yes","No"});

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
                        new PreferredSizeComboBox<>(new String[] {"Lightbulb", "Radio", "TV", "Refrigerator", "Fan", "Mobile", "Electric Stove", "Other"})));

        appliancesPanel.getTable().getColumnModel().getColumn(4) //Current Type
                .setCellEditor(new DefaultCellEditor(
                        new PreferredSizeComboBox<>(new String[] {"DC", "AC"})));

        //----------------- Water & Sanitation -----//
        JPanel waterAndSanitationPanel = new JPanel();

        //----------------- Agriculture ------------//
        JPanel agriculturePanel = new JPanel();
        agriculturePanel.setLayout(new BoxLayout(agriculturePanel, BoxLayout.LINE_AXIS));

        EnergifyScrollTablePanel agricultureCropTable = new EnergifyScrollTablePanel(new String[][] {{"Edit"}}, new String[] {"Type Of Crop"});
        agriculturePanel.add(new EnergifyCardPanel("How much crop land is irrigated if any?", agricultureCropTable));
        agricultureCropTable.getTable().getColumnModel().getColumn(0) //Type Of Crop
                .setCellEditor(new DefaultCellEditor(
                        new PreferredSizeComboBox<>(new String[] {"Maise", "Milet", "Wheat", "Tomatoes", "Onions", "Potatoes", "Cucumber"})));

        agriculturePanel.add(Box.createRigidArea(new Dimension(5,0)));

        agriculturePanel.add(new EnergifyCardPanel( //livestock
                "How much water is given to livestock if any?",
                new EnergifyScrollTablePanel(new String[][] {{"Area Covered"}}, new String[] {"Edit"})));

        agriculturePanel.add(Box.createRigidArea(new Dimension(5,0)));

        agriculturePanel.add(new EnergifyCardPanel( //practices
                "Are any of the following practices applied?",
                new JPanel(),
                new String[] {"drip-irrigation", "vertical cultivation"}));

        //----------------- Other Uses -------------//
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

        /*JPanel otherUsesCardPanel = new JPanel(new CardLayout());
        otherUsesCardPanel.add(new JPanel(), EMPTY_PANEL);
        otherUsesCardPanel.add(otherUsesTablesPanel, TABLE_PANEL);

        JComboBox<String> otherUsesComboBox = new JComboBox<>(new String[] {EMPTY_PANEL, TABLE_PANEL});
        otherUsesComboBox.addItemListener(evt -> {
            CardLayout cl = (CardLayout)(otherUsesCardPanel.getLayout());
            cl.show(otherUsesCardPanel, (String)evt.getItem());
        });

        JPanel otherUsesComboBoxPanel = new JPanel();
        otherUsesComboBoxPanel.add(otherUsesComboBox);

        JPanel otherUsesPanel = new JPanel();
        otherUsesPanel.setLayout(new BoxLayout(otherUsesPanel, BoxLayout.PAGE_AXIS));
        otherUsesPanel.add(otherUsesComboBoxPanel);
        otherUsesPanel.add(otherUsesCardPanel);*/
        EnergifyCardPanel otherUsesPanel = new EnergifyCardPanel(otherUsesTablesPanel);

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

        root.setLayout(new BoxLayout(rootPane, BoxLayout.PAGE_AXIS));

        root.add(Box.createRigidArea(new Dimension(0,20)));
        root.add(introPanel);
        root.add(Box.createRigidArea(new Dimension(0,20)));
        root.add(tabbedPane);

        root.pack();
        root.setMinimumSize(root.getSize());
        root.setVisible(true);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //------------------------------------------//
    }
}