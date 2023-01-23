package catHouse.repositories;

import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;

public class ToyRepository implements Repository{

    private Collection<Toy> toys;

    public ToyRepository(Collection<Toy> toys) {
        this.toys = new ArrayList<>();
    }

    @Override
    public void buyToy(Toy toy) {

    }

    @Override
    public boolean removeToy(Toy toy) {
        return false;
    }

    @Override
    public Toy findFirst(String type) {
        return null;
    }
}
