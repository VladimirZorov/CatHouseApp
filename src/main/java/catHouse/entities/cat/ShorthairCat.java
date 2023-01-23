package catHouse.entities.cat;

public class ShorthairCat extends BaseCat {

    private static final int INITIAL_KILOGRAMS = 7;
    private int kilograms;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        this.kilograms = INITIAL_KILOGRAMS;
    }


    @Override
    public void eating() {
        this.kilograms += 1;
    }
}
