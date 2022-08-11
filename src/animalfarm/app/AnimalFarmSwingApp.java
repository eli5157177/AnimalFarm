package animalfarm.app;


import animalfarm.model.AnimalRepository;
import animalfarm.util.Utilities;
import animalfarm.model.Animal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

/* -------------------------------------------------------------------------------------------------------------------->
    Class contains the graphic user interface elements and Methods activated by these elements
    All the Interface element are Java(x) Swing objects, which is an Event oriented.
    Each element has graphic attributes and methods,
    also unique methods called Listeners "Listen" for its elements(Thread).
    When an event happens it fires the event Method.
    The interface class, completely separated from repository, so it is possible to change UI in the future
 -------------------------------------------------------------------------------------------------------------------->*/

public class AnimalFarmSwingApp {

    // ------------------------------------------------data structures and public: ----------------------------------->

    DefaultTableModel tblModel;                                             // Define table Model
    String[] tableNames ;

    // -------------------------------------Public Graphic elements variables:----------------------------------------->

    final int  TBL_ROWS_NO=100;                                              // Table (No. of rows to Draw)
    JFrame frm;
    JLabel lbl;
    JTable tbl;
    JScrollPane scrollPane;
    JPanel panelFunctions;
    JButton btnAdd;
    JTextField txt;
    JPanel panelSort;
    JButton btnSort1;
    JButton btnSort2;
    JButton btnSort3;
    //JLabel lblTbl;
    JPanel panelSave;
    JButton btnSave;
    JButton btnDel;
    JComboBox <String> comboTypes;
    JLabel lblMsg;
    JLabel lblAnimalName;
    JLabel lblAnimalType;
    Utilities ut=new Utilities() ;                                              // Utility methods functionality
    AnimalRepository rep;                                                       // Repository Object

    //Constructor: Creates Empty Repository, and initialize it
    public AnimalFarmSwingApp() {

        rep = new AnimalRepository();
        rep.startData();
    }

    public void createTableModel() {
        //Method  Load Data from repository to table
            tableNames = new String[]{"Animal ID", "Animal Type", "Animal Name"};   // Table titles
            tblModel = new DefaultTableModel( rep.getTableData(TBL_ROWS_NO), tableNames );
     }

     // The following are two methods with the same name, different arguments
     // if an array is sent as argument, the table will update with the sorted array data
    // if no argument update table with repository "real" data.
    public void updateTableModel() {
        /* Method updates the User interface Table
           It reads Animals data from the table data[][] and writes it to the user Interface table
         */
        tblModel.setNumRows(0);
        for(int i=0;i<rep.getArraySize();i++) tblModel.insertRow(i,rep.getAnimalAt(i));
        tblModel.setNumRows(TBL_ROWS_NO);

    }
    public void updateTableModel(ArrayList<Animal> sortedArray) {
        /* Method updates the User interface Table
           It reads Animals data from the table data[][] and writes it to the user Interface table
         */
        tblModel.setNumRows(0);
        for(int i=0;i<sortedArray.size();i++) tblModel.insertRow(i,rep.getAnimalAt(i,sortedArray));
        tblModel.setNumRows(TBL_ROWS_NO);

    }

    public void createUI(){

       /* Method Creates Swing Graphic Elements: windows,buttons,text elements,table etc.
           The elements are created and configured Then placed on a container (for example main window).
           The layout manager is Border- Layout which divide the screen to eras: North,South,East,West,East
           Finally elements are Draw to output according the lay-out manager configuration
         */

        // Main program window (Frame)
        frm = new JFrame("Animal Farm, Eli.b 8.2022, Ver. 1.0 ");
        frm.setSize(850, 400);// divides the screen area to: north,south,center,east,west
        frm.setDefaultCloseOperation(frm.EXIT_ON_CLOSE);
        frm.setLayout(new BorderLayout());

        //--------------------------------------------North area ------------------------------------------------------>

        lbl = new JLabel("Welcome to The Animal Farm");
        lbl.setFont(new Font("Arial", Font.BOLD, 25));
        lbl.setForeground(Color.BLUE);
        lbl.setBorder(new EmptyBorder(0, 250, 0, 0));

        //--------------------------------------------East area ------------------------------------------------------->

        //       Table:
        tbl = new JTable(tblModel);
        tbl.setPreferredScrollableViewportSize(new Dimension(300,300));
        scrollPane = new JScrollPane(tbl);
        tbl.setFillsViewportHeight(true);
        tbl.setFont(new Font("Arial",Font.PLAIN,15));
        tbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        tbl.setBorder(new LineBorder(Color.blue, 1));
        tbl.setDefaultEditor(Object.class, null);             // table will be focused but not editable




        //--------------------------------------------South ------------------------------------------------------------>
        //      Panel:
        panelFunctions = new JPanel(new FlowLayout());
        panelFunctions.setBorder(new LineBorder(Color.green, 2));

        //       button:
             btnAdd = new JButton("Add animal");    // button activate sort table by name
             btnAdd.setPreferredSize(new Dimension(100, 30));
             btnAdd.setEnabled(true);

        //       text area:
             txt = new JTextField("");
             txt.setPreferredSize(new Dimension(100, 30));
             txt.setEnabled(true);
             txt.setFont(new Font("Arial",Font.PLAIN,15));

             lblAnimalType = new JLabel("Animal Type:");
             lblAnimalName = new JLabel("Animal Name:");


             //    ComboBox :
             comboTypes= new JComboBox<>(ut.getTypes());
             comboTypes.setEditable(true);

             //    label :
            lblMsg = new JLabel("<html>Select known Animal Types From the Combo Box<br/>" +
                    "Or edit combo to add new Type</html>", SwingConstants.CENTER);
            lblMsg.setPreferredSize(new Dimension(300, 30));
            lblMsg.setForeground(Color.BLUE);
            lblMsg.setEnabled(true);


        panelFunctions.add(lblAnimalType);
        panelFunctions.add(comboTypes);
        panelFunctions.add(lblAnimalName);
        panelFunctions.add(txt);
        panelFunctions.add(btnAdd);
        panelFunctions.add(lblMsg);
//--------------------------------------------Center area -------------------------------------------------------->
        //    panel :
        panelSort = new JPanel();
        panelSort.setBorder(new LineBorder(Color.blue, 1));

                btnSort1 = new JButton("Sort (by Animal Name)");    // button activate sort table by name
                btnSort1.setPreferredSize(new Dimension(170, 30));
                btnSort1.setEnabled(true);
                btnSort1.setBorder(new LineBorder(Color.black,1));

                btnSort2 = new JButton("Sort (by Animal Type)");    // button activate sort table by name
                btnSort2.setPreferredSize(new Dimension(170, 30));
                btnSort2.setEnabled(true);
                btnSort2.setBorder(new LineBorder(Color.black,1));


                btnSort3 = new JButton("Sort (by Animal ID)");    // button activate sort table by name
                btnSort3.setPreferredSize(new Dimension(170, 30));
                btnSort3.setEnabled(true);
                btnSort3.setBorder(new LineBorder(Color.black,1));



        //       button:
                btnDel = new JButton("Remove animal");    // button activate sort table by name
                btnDel.setPreferredSize(new Dimension(170, 30));
                btnDel.setEnabled(true);
                btnDel.setBorder(new LineBorder(Color.black,1));


        panelSort.add(btnSort1);
        panelSort.add(btnSort2);
        panelSort.add(btnSort3);
        panelSort.add(btnDel, BorderLayout.CENTER);
        //panelSort.add(lblTbl, BorderLayout.CENTER);

//--------------------------------------------west area -------------------------------------------------------->
        panelSave = new JPanel();
        panelSave.setBorder(new LineBorder(Color.blue, 1));



                btnSave = new JButton("Save");    // button activate sort table by name
                btnSave.setPreferredSize(new Dimension(170, 30));
                btnSave.setEnabled(false);
                btnSave.setBorder(new LineBorder(Color.black,1));

        panelSave.add(btnSave);

        // ------------------------------------------------------------------------------------------------------------>
        //      Add Listeners - Listen:  [Event e -> {fire Method}]
        // ------------------------------------------------------------------------------------------------------------>

        btnSort1.addActionListener(e -> sortAnimals(btnSort1.getText()));
        btnSort2.addActionListener(e -> sortAnimals(btnSort2.getText()));
        btnSort3.addActionListener(e -> sortAnimals(btnSort3.getText()));

        // ----------------------------------------------------------------------------------------------------------->
        btnAdd.addActionListener(e -> {      // Listener for btnAdd Events

                    String name=txt.getText();
                    String type = String.valueOf(comboTypes.getSelectedItem());
                    //System.out.println(" --> "+ name+" "+type);

            // Check correct input from user:
            if ( (name.equals("")) || (type.equals("")))
                JOptionPane.showMessageDialog(frm, "Fill Animal Name and type");
            else {

                rep.addNewAnimal(type,name);               // add new animal to repository
                updateTableModel();
                btnSave.setEnabled(true);                  // changes has been made, save enabled
                btnSave.setForeground(Color.red);
                if (!ut.typeExist(type))                   // Check if animal type exist in Model (not in combo!)
                    comboTypes.addItem(type);

            } // else end

        });
        // ----------------------------------------------------------------------------------------------------------->
        btnDel.addActionListener(e -> {      // Listener for btnAdd Events

            int row = tbl.getSelectionModel().getLeadSelectionIndex();
            //int col = tbl.getColumnModel().getSelectionModel().getLeadSelectionIndex();
            if (row == -1){ // Table not selected
                JOptionPane.showMessageDialog(frm,"Select Row to Delete");
                return;
            }

            // Note:
            // table rows indexed: 0,1,2... While Array size() indexed: 1,2,3....
            //System.out.println("row:"+row + ",size:"+animals.size());
            if (row+1>rep.getArraySize()) return;  // user try to delete blank row: do nothing

               rep.removeAnimalAt(row);
               updateTableModel();
               btnSave.setEnabled(true);           // changes have been made, enable save to file
               btnSave.setForeground(Color.red);


        });

        // ------------------------------------------------------------------------------------------------------------>
        btnSave.addActionListener(e -> {

            try {
                rep.saveData2File();
                btnSave.setEnabled(false);
            }catch (Exception ex) {ex.printStackTrace();}

        }); // end of action listener

    } // End of CreateGUI Method


    public void show() {

         // set UI visualizations and visibility: use LayOut Manager
        //------------------------------------------------------------------------------------------------------------->
        // NOTE: adding twice to the region will display the last one
        frm.add(lbl, BorderLayout.NORTH);
        frm.add(scrollPane, BorderLayout.EAST);
        frm.add(panelFunctions,BorderLayout.SOUTH);
        frm.add(panelSort,BorderLayout.CENTER);
        frm.add(panelSave,BorderLayout.WEST);

        // -----------Center Window----->
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
    public  void sortAnimals(String key) {

        /* ----------------------------------------------------------------------
            Method  Sort  Animals (ArrayList)                                   *
            I Used Collections sort method                                      *
            the method receive a Comparator object and the arrayList            *
        ------------------------------------------------------------------------*/
        ArrayList<Animal> sortedArray ;
        sortedArray = rep.sortAnimals(key);     // key is the key sort
        updateTableModel(sortedArray);          // update Table view with the sorted array

    }// end of method

}// End of class GUI






