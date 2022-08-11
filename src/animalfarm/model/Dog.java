package animalfarm.model;

// Sub Class of Animal, Defines an Animal with specific type: Dog

import animalfarm.model.Animal;

public class Dog extends Animal {
    // Could Define here specific Variables for a dog
    public Dog(String name,int id) {
        super("dog",name,id);      // when creating cat, type is already known
    }
    public  int getID(){
        return super.getId();
    }
}
