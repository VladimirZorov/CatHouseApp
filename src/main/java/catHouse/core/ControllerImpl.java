package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.INVALID_HOUSE_TYPE;
import static catHouse.common.ExceptionMessages.INVALID_TOY_TYPE;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        if (type.equals("ShortHouse")) {
            house = new ShortHouse(name);
        } else if (type.equals("LongHouse")) {
            house = new LongHouse(name);
        } else {
            throw new NullPointerException(INVALID_HOUSE_TYPE);
        }
        houses.add(house);
        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        if (type.equals("Ball")) {
            toy = new Ball();
        } else if (type.equals("Mouse")) {
            toy = new Mouse();
        } else {
            throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }
        toys.buyToy(toy);
        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {

        return null;
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        return null;
    }

    @Override
    public String feedingCat(String houseName) {
        int fedCount = 0;
        for (House house : houses) {
            if (house.getName().equals(houseName)) {
                house.feeding();
                fedCount++;
            }
        }
        return String.format(FEEDING_CAT, fedCount);
    }

    @Override
    public String sumOfAll(String houseName) {
        ToyRepository mytoys = new ToyRepository();
        double value = 0.00;
        for (House house : houses) {
            if (house.getName().equals(houseName)) {
                value += house.getCats().size();
                house.getToys().forEach(toy -> toy.getPrice());
            }
        }
        return String.format(VALUE_HOUSE, houseName, value);
    }

    @Override
    public String getStatistics() {
        return null;
    }
}
