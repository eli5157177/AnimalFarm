package animalfarm;
/* -----------------------------------------------<H1>Animal farm</H1> ---------------------------------------------->
 The program implement application tha manage Animal data in non-realistic Farm
 A user of this program can apply basic commands like: add new animal, sort the data and save data to disk.
<p>

@author  Eli Buhbut
@version 1.0
@since   11.8.2022
 --------------------------------------------------------------------------------------------------------------------*/

import animalfarm.app.AnimalFarmSwingApp;

public class Main extends AnimalFarmSwingApp {
    /*   Program entry point:

         Main concept: Logic Separation of Layers for different missions
         ---------------------------------------------------------------

         Interface layer  - Layer contains the main program object (UI)
         Data Layer - contains Method and utilities for create, init, manage the data
         User Interface Layer - contains a Java swing pure graphic interface elements
         Executable Layer  - an Event oriented Layer

                                                                                    */
    public static void main(String[] args) {

        AnimalFarmSwingApp userInterface = new AnimalFarmSwingApp();      //  Interface Layer
        userInterface.createTableModel();                                  //  Data Layer
        userInterface.createUI();                                         //  graphic Layer
        userInterface.show();                                             //  execute Layer

    }

}
/* -------------------------------------------Future optional expansion----------------------------------------------->
 1. Add a Database : like mySql,sqlServer etc. And communicate with JDBC/SQL
 2. Configure a web client interface
 3. Enhanced Read and write method with threads implementation. needed specially when having large data sets
 --------------------------------------------------------------------------------------------------------------------*/
