package animalfarm.model;

import animalfarm.model.Animal;

// Sub Class of Animal, Defines an Animal with specific type: cat
public class Cat extends Animal {
     // Could Define here specific Variables for a cat
    public Cat(String name,int id) {
        super("cat",name,id);       // when creating "cat" object, this type is  known
    }
    public  int getID(){
         return super.getId();
    }

}

