package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
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
import static catHouse.common.ExceptionMessages.*;

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
        for (House house : houses) {
            if (house.getName().equals(houseName)) {
                Toy toy = toys.findFirst(toyType);
                if (toy.getClass().getSimpleName().equals(toyType)) {
                    house.buyToy(toy);
                    toys.removeToy(toy);
                    return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
                } else {
                    throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
                }
            }
        }
        return String.format(NO_TOY_FOUND, toyType);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        for (House house : houses) {
            if (house.getName().equals(houseName)) {
                Cat cat;
                if (catType.equals("ShorthairCat")) {
                    cat = new ShorthairCat(catName, catBreed, price);
                    if (house.getName().equals("HouseForPersian")) {
                        house.addCat(cat);
                    } else {
                        throw new IllegalArgumentException(UNSUITABLE_HOUSE);
                    }
                } else if (catType.equals("LonghairCat")) {
                    cat = new LonghairCat(catName, catBreed, price);
                    if (house.getName().equals("HouseForSphynx")) {
                        house.addCat(cat);
                    } else {
                        throw new IllegalArgumentException(UNSUITABLE_HOUSE);
                    }
                } else {
                    throw new IllegalArgumentException(INVALID_CAT_TYPE);
                }
            }
        }
        return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        int fedCount = 0;
        for (House house : houses) {
            if (house.getName().equals(houseName)) {
                house.feeding();
                fedCount = house.getCats().size();
            }
        }
        return String.format(FEEDING_CAT, fedCount);
    }

    @Override
    public String sumOfAll(String houseName) {
        double value = 0.00;
        for (House house : houses) {
            if (house.getName().equals(houseName)) {
                for (Cat cat : house.getCats()){
                    value += cat.getPrice();
                }
                for (Toy toy : house.getToys()) {
                    value += toy.getPrice();
                }
            }
        }
        return String.format(VALUE_HOUSE, houseName, value);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        for (House house : houses) {
           sb.append(house.getStatistics()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
