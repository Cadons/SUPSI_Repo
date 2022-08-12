package matteo_cadoni.serie01.esercizio02;

public class CombustionVehicle extends Vehicle implements CombustionEngine{
    private Combustion engine;

    public CombustionVehicle(String brand, int numberOfSeats, int numberoOfWheels, double distanceWithAFuel, Combustion engine) {
        super(brand, numberOfSeats, numberoOfWheels, distanceWithAFuel);
        this.engine=engine;
    }



    @Override
    public int getCylinderCapacity() {
        return engine.getCylinderCapacity();
    }

    @Override
    public int getTankSize() {
        return engine.getTankSize();
    }

    @Override
    public double getAveragePetrolConsumption() {
        return engine.getConsumption();
    }

    @Override
    public double getTravelRage() {
          return getTankSize()/getAveragePetrolConsumption();
    }

    @Override
    public String toString() {
        return "CombustionVehicle{" +
                "engine=" + engine +
                super.toString()+
                '}';
    }
}
