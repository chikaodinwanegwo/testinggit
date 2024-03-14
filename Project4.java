/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;
/**
 *
 * @author chikaodinwanegwo
 */
public class Project4 extends JFrame {
     /* Declare class consents */
    private static final String FRAME_TITLE = "Real Estate Database";
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;
    private static final Font TEXT_FONT = new Font("Times New Roman", Font.BOLD, 15);
    private static final int TEXT_WIDTH = 5;
    private static final String[ ] DATABASE_ACTION = {"Insert", "Delete", "Find"};
    private static final String[ ] STATUS = {"FOR_SALE", "UNDER_CONTRACT", "SOLD"};

    /* Declare Class Fields */
    protected String propertyAddress;
    protected Status statusOfProperty;
    protected int numberOfBedrooms;
    protected int squareFootage;
    protected int price;
    protected int transactionNumber;

    /* Create an object of the components */
    protected JLabel transactionNumberLabel;
    protected JLabel addressLabel;
    protected JLabel bedroomsLabel;
    protected JLabel squareFootageLabel;
    protected JLabel priceLabel;

    protected JTextField transactionNumberField;
    protected JTextField addressField;
    protected JTextField bedroomsField;
    protected JTextField squareFootageField;
    protected JTextField priceField;

    protected JButton processButton;
    protected JButton changeStatusButton;

    protected JComboBox<String> databaseActionCombo;
    protected JComboBox<String> changeStatusCombo;

    protected JPanel controlPanel;

    protected Property record;

    protected TreeMap<Integer, Property> databaseRecords;


    public Project4( )
    {
        transactionNumberLabel = new JLabel("Transaction No: ", JLabel.CENTER);
        transactionNumberLabel.setFont(TEXT_FONT);

        addressLabel = new JLabel("Address: ", JLabel.CENTER);
        addressLabel.setFont(TEXT_FONT);

        bedroomsLabel = new JLabel("Bedrooms: ", JLabel.CENTER);
        bedroomsLabel.setFont(TEXT_FONT);

        squareFootageLabel = new JLabel("Square Footage: ", JLabel.CENTER);
        squareFootageLabel.setFont(TEXT_FONT);

        priceLabel = new JLabel("Price: ", JLabel.CENTER);
        priceLabel.setFont(TEXT_FONT);

        transactionNumberField = new JTextField(TEXT_WIDTH);
        transactionNumberField.setEnabled(true);
        transactionNumberField.setFont(TEXT_FONT);
        transactionNumberField.setText(" ");

        addressField = new JTextField(TEXT_WIDTH);
        addressField.setEnabled(true);
        addressField.setFont(TEXT_FONT);
        addressField.setText(" ");

        bedroomsField = new JTextField(TEXT_WIDTH);
        bedroomsField.setEnabled(true);
        bedroomsField.setFont(TEXT_FONT);
        bedroomsField.setText(" ");

        squareFootageField = new JTextField(TEXT_WIDTH);
        squareFootageField.setEnabled(true);
        squareFootageField.setFont(TEXT_FONT);
        squareFootageField.setText(" ");

        priceField = new JTextField(TEXT_WIDTH);
        priceField.setEnabled(true);
        priceField.setFont(TEXT_FONT);
        priceField.setText(" ");

        databaseActionCombo = new JComboBox< >(DATABASE_ACTION);
        databaseActionCombo.setEditable(false);
        databaseActionCombo.setFont(TEXT_FONT);

        changeStatusCombo = new JComboBox< >(STATUS);
        changeStatusCombo.setEditable(false);
        changeStatusCombo.setFont(TEXT_FONT);

        processButton = new JButton("Process");
        processButton.setFont(TEXT_FONT);
        processButton.setOpaque(true);

        changeStatusButton = new JButton("Change Status");
        changeStatusButton.setFont(TEXT_FONT);
        changeStatusButton.setOpaque(true);

        controlPanel = new JPanel(new GridLayout(7, 2));

        /* Add Swing components to JPanel */
        controlPanel.add(transactionNumberLabel);
        controlPanel.add(transactionNumberField);
        controlPanel.add(addressLabel);
        controlPanel.add(addressField);
        controlPanel.add(bedroomsLabel);
        controlPanel.add(bedroomsField);
        controlPanel.add(squareFootageLabel);
        controlPanel.add(squareFootageField);
        controlPanel.add(priceLabel);
        controlPanel.add(priceField);
        controlPanel.add(processButton);
        controlPanel.add(databaseActionCombo);
        controlPanel.add(changeStatusButton);
        controlPanel.add(changeStatusCombo);

        /* Add JPanel to JFrame */
        setContentPane(controlPanel);

        ActionListener buttonListener1 = new processButtonListener( );
        ActionListener buttonListener2 = new changeStatusButtonListener( );

        changeStatusButton.addActionListener(buttonListener2);
        processButton.addActionListener(buttonListener1);

        createMap( );
    }// end of Project4 constructor

    
    public void setPropertyRecords( )
    {
        propertyAddress = addressField.getText( );

        record = new Property(propertyAddress, numberOfBedrooms, squareFootage, price);

    }// end of setPropertyRecords method

    
    public void createMap( )
    {
        databaseRecords = new TreeMap< >( );

        JOptionPane.showMessageDialog(null, "Database was created successful!", "Info",
                JOptionPane.INFORMATION_MESSAGE);

    }// end of createMap method

   
    public void insertRecord( )
    {
        if(transactionNumber == 0)
        {
            JOptionPane.showMessageDialog(null, "Transaction Number not entered! Please enter a" +
                                           " transaction number", "Waring!", JOptionPane.WARNING_MESSAGE);
        }
        else if (verifyTreeMapKey(transactionNumber) || verifyTreeMapValue(record))
        {
            JOptionPane.showMessageDialog(null, "Transaction Number or Record already exist!" +
                                           " Please check data!", "Waring!", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            databaseRecords.put(transactionNumber, record);

            JOptionPane.showMessageDialog(null, "Record successfully inserted into database",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }// end of insertIntoMap method

    
    public void deleteRecord( )
    {
        if(verifyTreeMapKey(transactionNumber))
        {
            databaseRecords.remove(transactionNumber);

            JOptionPane.showMessageDialog(null, "Record successfully removed from database",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Record does not exist in database",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }// end of deleteTreeMapKey method

    
    public void findRecord( )
    {
        if(verifyTreeMapKey(transactionNumber) && verifyTreeMapValue(record))
        {
            databaseRecords.get(transactionNumber);

            JOptionPane.showMessageDialog(null, record.toString( ));
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Record does not exist in database",
                                          "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }// end of findRecord


    public boolean verifyTreeMapValue(Property record){
        return (databaseRecords.containsValue(record));

    }// end of verifyTreeMapKey method

    public boolean verifyTreeMapKey(int transactionNumber)
    {
        return databaseRecords.containsKey(transactionNumber);

    }// end of verifyTreeMapKey method

  
    public void verifyInput( )
    {
            try
            {
                transactionNumber = Integer.parseInt(transactionNumberField.getText( ));
                numberOfBedrooms = Integer.parseInt(bedroomsField.getText( ));
                squareFootage = Integer.parseInt(squareFootageField.getText( ));
                price = Integer.parseInt(priceField.getText( ));
            }
            catch(NumberFormatException error)
            {
                JOptionPane.showMessageDialog(null, error.getMessage( ));
            }
    }// end of verifyInput method

    
    public void clearTextFields( )
    {
        transactionNumberField.setText("");
        bedroomsField.setText(" ");
        squareFootageField.setText("");
        priceField.setText("");
        addressField.setText("");
    }//end of clearTextFields

    
    public static void createAndShowGUI()
    {
        JFrame window = new Project4( );

        // Set the title of the frame
        window.setTitle(FRAME_TITLE);

        // Set the frame's size
        window.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        // Display the JFrame to user
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }// end createAndShowGUI method

 
    public static void main(String[ ] args)
    {
        javax.swing.SwingUtilities.invokeLater(Project4::createAndShowGUI);

    }// end of main method


   
    class processButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int index = databaseActionCombo.getSelectedIndex();

           switch(index)
           {
               case 0:
                   verifyInput( );

                   setPropertyRecords( );

                   insertRecord( );

                   clearTextFields( );

               break;

               case 1:
                   deleteRecord( );
                   clearTextFields( );
               break;

               case 2:
                   findRecord( );
                   clearTextFields( );
               break;

               default:
                   throw new IllegalStateException("Unexpected value: " + index);
           }// end of switch
        }// end of actionPerformed method
    }// end of processButtonListener inner class

    
    class changeStatusButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(transactionNumber == 0)
            {
                JOptionPane.showMessageDialog(null, "Record does not exist in database",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                // Fetch the record using the transaction number
                databaseRecords.get(transactionNumber);
            }
            
            statusOfProperty = Status.valueOf((String) changeStatusCombo.getSelectedItem( ));

            record.changeState(statusOfProperty);

            findRecord( );
        }
    }
    
}//end of project4
