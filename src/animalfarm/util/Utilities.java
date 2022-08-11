package animalfarm.util;

import animalfarm.model.Animal;
import animalfarm.model.Cat;
import animalfarm.model.Cow;
import animalfarm.model.Dog;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/* Class Contains utility methods for Animal types managing , creation of test data etc.

 */

public class Utilities {

   static ArrayList<String> types = new ArrayList<>(Arrays.asList("cat", "dog", "cow"));


    public boolean typeExist(String s) {
        // Method returns true if the given Animal type exist
        // If the type not found it will add to the list of types
        for (String type : types)
            if (s.equals(type))
                return true;

        types.add(s);
        return false;
    }


    public String[] getTypes() {
        /*
        Method creates and returns list of Animal Types currently in the system
        */
        int len = types.size();
        String[] typesStr = new String[len];
        for (int i = 0; i < types.size(); i++) {
            typesStr[i] = types.get(i);
        }

        return typesStr;
    }

    public int testCreateData(ArrayList<Animal> animals, int animal_id) {
      /* Method creates a set of Animals objects, as a Default data for the program
         If the program not finding file: Animals.bin containing the "real" Data,
         Then it will use this set of animals Data to init.
         argument: ArrayList <Animal> is the destination Animal Data
       */

        animals.add(new Cat("kittyCaty", animal_id++));                // creating a cat --> name, id
        animals.add(new Dog("Bar-dogo", animal_id++));                  // creating a Dog --> name, id
        animals.add(new Cow("CowBoy", animal_id++));                   // creating a cowboy --> name, id
        animals.add(new Cat("helloKitty", animal_id++));               // creating a cat --> name, id
        animals.add(new Dog("Booboo", animal_id++));                   // creating a Dog --> name, id
        animals.add(new Cow("Sunshine", animal_id));                   // creating a cowboy --> name, id


        /* Update model manually if new types(other than cat,dog,cow) Entered only here:
           for example new snake checkType("snake");                                 */

        return ++animal_id;         // Number of object created == animals.size()
    }

    public void checkType(String s) {
        /// Method returns check if animal type is not a new type
        // if a Type is new type adds it

        for (String type : types)
            if (s.equals(type))
                return;
        types.add(s); // new Type
    }

    public boolean fileFound() {
        /* Method checks if file "Animals.bin" exists in the active directory
           returns true/false                                                 */
        Path activeDirectory = Paths.get("Animals.bin");
        // file not found:  Animals.BIN
        return Files.exists(activeDirectory);
    }
    public int loadFile(ArrayList<Animal> animals) throws IOException, ClassNotFoundException {

    /* Method Loads animal Data file, Convert it in to Array list of Animal Objects
        argument:
         ArrayList -  must implement Serializable java class, in order to
         be able to stream the array in/out with Binary file(\\active_directory\\Animals.bin).
         Return:
         Method return's (int) number of Animal transferred from the file to the ArrayList
     */
    FileInputStream inFile = new FileInputStream("Animals.bin");
    ObjectInputStream inObj = new ObjectInputStream(inFile);


    while (true) {
        try {
          Animal  a = (Animal) inObj.readObject();
            animals.add(a);
        }catch (EOFException eof){break;}     // EOF reached

    }
        int max=0;
        for (Animal a : animals) {
            checkType(a.getType());           // read Animal types created in previous program run
            max= Math.max(max,a.getId());     // get max ID in order to give a unique ID for the next animals
        }
        return max+1;
    }


}
