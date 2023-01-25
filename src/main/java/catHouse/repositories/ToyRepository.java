package catHouse.repositories;

import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;

public class ToyRepository implements Repository{

    private Collection<Toy> toys;

    public ToyRepository(Collection<Toy> toys) {
        this.toys = new ArrayList<>();
    }

    public ToyRepository() {
        this.toys = new ArrayList<>();
    }

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    @Override
    public boolean removeToy(Toy toy) {
        toys.remove(toy);
        return true;
    }

    @Override
    public Toy findFirst(String type) {
        Toy myToy = null;
        for (Toy toy : toys) {
            if (toy.getClass().getSimpleName().equals(type)){
                myToy = toys.stream().findFirst().orElse(null);
            }
        }
         return myToy;
        
    }
}
