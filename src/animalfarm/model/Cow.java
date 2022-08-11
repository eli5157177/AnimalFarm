package animalfarm.model;

// Sub Class of Animal, Defines an Animal with specific type: cow

import animalfarm.model.Animal;

public class Cow extends Animal {
    // Could Define here specific Variables for a cow
    public Cow(String name,int id) {
        super("cow",name,id);      // when creating "cat", type is already known
    }
    public  int getID(){
        return super.getId();
    }
}