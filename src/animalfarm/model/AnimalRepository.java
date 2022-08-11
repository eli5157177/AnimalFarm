package animalfarm.model;

import animalfarm.util.Utilities;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

public class AnimalRepository {
    /* Class Acts like a Back-End for the program. it contains all data and related data methods
       there is  full separation between the interface to the database.
       The class creates all of the graphic objects, and provides methods for Event handling
   */

    static int animal_id =0;                                                   // animals ID base
    // new animal repository structures------>
    ArrayList <Animal> animals = new ArrayList<>();                            // Array List of animals objects
    Object[][] data ;                                                           // Data object for the table
    Utilities ut ;                                                              // utilities for repository

public int getArraySize(){
    // Method returns animal Array size
    return animals.size();
}
    public Object[] getAnimalAt(int row) {


        data[row][0] = animals.get(row).getId();
        data[row][1] = animals.get(row).getType();
        data[row][2] = animals.get(row).getName();
        return data[row];
    }
    public Object[] getAnimalAt(int row,ArrayList<Animal> a) {


        data[row][0] = a.get(row).getId();
        data[row][1] = a.get(row).getType();
        data[row][2] = a.get(row).getName();

        return data[row];


    }

   public void addNewAnimal(String type,String name){
       animals.add(new Animal(type,name,animal_id++));
   }
   public void removeAnimalAt(int row){
       animals.remove(row);
   }

public void startData(){

    /* Method Starts The repository
      loading Animals Data priority:
        1. Try to find file - "animals.bin" in the active Directory
        2. if fail to find the data file, it will create some data,
            see ut.testCreateData()
     ---------------------------------------------------------*/
    ut = new Utilities();

    if ( !ut.fileFound() ){
        animal_id =  ut.testCreateData(animals,animal_id);
        /* If this line will remove, program start with empty table and will work properly.*/

    }else{
        try {
            animal_id = ut.loadFile(animals);
        }catch (Exception e) {e.printStackTrace();}
    }
}

    public Object[][]  getTableData(int numOfRows) {
        // loading Table attributes and data
        //names = new String[]{"Animal ID", "Animal Type", "animal name"};   // Table titles
        data = new Object[numOfRows][3];

        // Load Data to the table Data Model
        for(int i=0;i<animals.size();i++) {
            data[i][0] = animals.get(i).getId();
            data[i][1] = animals.get(i).getType();
            data[i][2] = animals.get(i).getName();
        }
         return data;
    }

    public void saveData2File() throws IOException {
        /* Method Outputs Array of Animals to Data file, Convert it in to stream of Animal Objects
        argument:
        ArrayList -  must implement Serializable java class, in order to
         be able to stream the array in to Binary file(\\active_directory\\Animals.bin).
     */
        ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("Animals.bin"));
        // outFile.writeObject((Object) animals);
        for (Animal a : animals) outFile.writeObject(a);
        outFile.close();

        // Show Save success msg:
        JOptionPane.showMessageDialog(null,"Animal List Saved Successfully to: Animals.bin " );
        //System.out.println(animals);

    }

//    public ArrayList<Animal> listAllAnimals(String sortKey){
//       returns sorted Array as a copy of Array, no changes to original repository
//       return - arraylist result =
//    ----------------------------------------------------------------------------------------------------------------->
public  ArrayList <Animal> sortAnimals(String key) {
        /* ----------------------------------------------------------------------
            Method  Sort  Animals (ArrayList)                                   *
            I Used Collections sort method                                      *
            the method receive a Comparator object and the arrayList            *
            The sort method works on a copy of data from repository, original
            data will not change.
        ------------------------------------------------------------------------*/
    ArrayList <Animal> sortedArrayList=new ArrayList<>(animals);  // copy of Array Animal

    switch (key) {
        case "Sort (by Animal Name)" ->
            sortedArrayList.sort(Comparator.comparing(Animal::getName));


        case "Sort (by Animal ID)" ->
            sortedArrayList.sort(Comparator.comparing(Animal::getId));


        case "Sort (by Animal Type)" ->
            sortedArrayList.sort(Comparator.comparing(Animal::getType));

        default ->  {}

    }
       return (sortedArrayList);         // return the sorted array

    } // method end

} // Class End
