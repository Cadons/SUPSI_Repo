package matteo_cadoni.serie01.esercizio02;

public class HybridVehicle extends Vehicle implements ElettricEngine,CombustionEngine{
    private Elettric elettricEngine;
    private Combustion combustionEngine;
    public HybridVehicle(String brand, int numberOfSeats, int numberoOfWheels, double distanceWithAFuel,Elettric e1,Combustion e2) {
        super(brand, numberOfSeats, numberoOfWheels, distanceWithAFuel);
        this.elettricEngine=e1;
        this.combustionEngine=e2;
    }

    @Override
    public int getCylinderCapacity() {
        return combustionEngine.getCylinderCapacity();
    }

    @Override
    public int getTankSize() {
        return combustionEngine.getTankSize();
    }

    @Override
    public double getAveragePetrolConsumption() {
        return combustionEngine.getConsumption();
    }



    @Override
    public int getChargeCapacity() {
        return elettricEngine.getChargeCapacity();
    }



    @Override
    public double getAvaregeEnergyConsumpation() {
        return elettricEngine.getConsumption();
    }

    @Override
    public int getBatteryAge() {
        return elettricEngine.getBatteryAge();
    }



    @Override
    public double getTravelRage() {
        double ratio=getChargeCapacity()/getAvaregeEnergyConsumpation();
        if(getBatteryAge()>=48)
        {
            ratio-=(ratio*0.02)*(getBatteryAge()-48);
        }
        return ratio+getTankSize()/getAveragePetrolConsumption();
    }

    @Override
    public String toString() {
        return "HybridVehicle{" +
                "elettricEngine=" + elettricEngine +
                ", combustionEngine=" + combustionEngine +
                super.toString()+
                '}';
    }
}
