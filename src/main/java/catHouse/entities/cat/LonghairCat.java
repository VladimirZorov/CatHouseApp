package catHouse.entities.cat;

public class LonghairCat extends BaseCat{

    private static final int INITIAL_KILOGRAMS = 9;
    private int kilograms;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
        this.kilograms = INITIAL_KILOGRAMS;
    }

    @Override
    public void eating() {
        this.kilograms += 3;
    }
}
