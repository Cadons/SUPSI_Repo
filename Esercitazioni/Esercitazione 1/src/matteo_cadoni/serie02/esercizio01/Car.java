package matteo_cadoni.serie02.esercizio01;

public abstract class Car extends Vehicle {

    public Car(String brand) {
        super(brand, 4);
    }

    @Override
    public String toString() {
        return "Car{} " + super.toString();
    }
}
