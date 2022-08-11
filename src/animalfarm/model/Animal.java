package animalfarm.model;

import java.io.Serializable;
/* (Super) Class Animal Define an Animal pattern,
   The class implement's java Serializable interface.
    In order to Stream Animal objects to file, Socket etc.
    Serializable convert object state to a byte stream.
    Then When reading a stream it reverted into a copy of the object
    See how it works: Class Utilities.loadFile(), Utilities.save2File()
     */
public class Animal implements Serializable {
   private final int id;            //unique number for animal identification
   private String name;
   private String type;

   // constructor for Animal
   public Animal(String type, String name, int id) {
      this.type = type;
      this.name = name;
      this.id = id;

   }

   public int getId() {
      return id;
   }

   public String getName() {
      return name;
   }


   public String getType() {
      return type;
   }
}

