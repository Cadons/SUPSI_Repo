package matteo_cadoni.serie01.esercizio02;

public class ElettricVehicle extends Vehicle implements ElettricEngine{
    private Elettric engine;
    public ElettricVehicle(String brand, int numberOfSeats, int numberoOfWheels, double distanceWithAFuel,Elettric engine) {
        super(brand, numberOfSeats, numberoOfWheels, distanceWithAFuel);
        this.engine=engine;
    }

    @Override
    public int getChargeCapacity() {
        return engine.getChargeCapacity();
    }

    @Override
    public double getAvaregeEnergyConsumpation() {
        return engine.getConsumption();
    }

    @Override
    public int getBatteryAge() {
        return engine.getBatteryAge();
    }

    @Override
    public double getTravelRage() {

        double ratio=getChargeCapacity()/getAvaregeEnergyConsumpation();
        if(getBatteryAge()>=48)
        {
            ratio-=(ratio*0.02)*(getBatteryAge()-48);
        }
        return ratio;

    }

    @Override
    public String toString() {
        return "ElettricVehicle{" +
                "engine=" + engine +
                super.toString()+
                '}';
    }
}
