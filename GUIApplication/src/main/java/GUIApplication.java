import javax.swing.*;
import java.awt.*;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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

        howFarIsTheBlankFromTheGridComboBox.addActionListener(actionEvent -> {
            howFarIsTheBlankFromTheGridLabel.setText(String.format("How far is the %s from the grid?", howFarIsTheBlankFromTheGridComboBox.getSelectedItem()));
        });

        isTheUserCurrentlyLocatedInTheComboBox.addActionListener(actionEvent -> {
            isTheUserCurrentlyLocatedInTheLabel.setText(String.format("Is the user currently located in the %s", isTheUserCurrentlyLocatedInTheComboBox.getSelectedItem()));
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
        appliancesPanel.setLayout(new GridLayout(1,4,5,5));

        appliancesPanel.add(new Label("Type of Appliance"));
        appliancesPanel.add(new Label("Power Rating"));
        appliancesPanel.add(new Label("Number of Appliance"));
        appliancesPanel.add(new Label("Operational Hours/day"));

        //----------------- Water & Sanitation -----//
        JPanel waterAndSanitationPanel = new JPanel();

        //----------------- Agriculture ------------//
        JPanel agriculturePanel = new JPanel();

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