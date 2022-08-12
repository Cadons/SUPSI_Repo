package matteo_cadoni.serie01.esercizio02;

public abstract class Vehicle {
    private String brand;
    private  int numberOfSeats;
    private int numberOfWheels;
    private double distanceWithAFuel;

    public Vehicle(String brand, int numberOfSeats, int numberoOfWheels, double distanceWithAFuel) {

        this.brand = brand;
        this.numberOfSeats = numberOfSeats;
        this.numberOfWheels = numberoOfWheels;
        this.distanceWithAFuel = distanceWithAFuel;

    }


    public abstract double getTravelRage();


    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public double getDistanceWithAFuel() {
        return distanceWithAFuel;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", numberOfWheels=" + numberOfWheels +
                ", distanceWithAFuel=" + distanceWithAFuel +
                ", getTravelRage=" + getTravelRage()+
                '}';
    }
}
