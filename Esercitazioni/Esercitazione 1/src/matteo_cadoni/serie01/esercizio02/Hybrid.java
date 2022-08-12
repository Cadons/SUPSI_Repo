package matteo_cadoni.serie01.esercizio02;

public  class Hybrid extends Engine implements ElettricEngine, CombustionEngine{
    private int cylinderCapacity;
    private int tankSize;


    private int batteryAge;

    private int chargeCapcity;



    public Hybrid(int capacity, double consumption) {
        super(capacity, consumption);
    }



    private double travelRangeElettricalUnit() {

        return getBatteryAge()<48?(getChargeCapacity()/getAvaregeEnergyConsumpation())-(getChargeCapacity()/getAvaregeEnergyConsumpation())*(0.2*getBatteryAge()): getChargeCapacity()/getAvaregeEnergyConsumpation();
    }

    private double travelRangeCombustionUnit() {
        return tankSize/getAveragePetrolConsumption();
    }


    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    public int getTankSize() {
        return tankSize;
    }

    @Override
    public double getAveragePetrolConsumption() {
        return getConsumption();
    }


    @Override
    public int getBatteryAge() {
        return batteryAge;
    }



    @Override
    public int getChargeCapacity() {
        return chargeCapcity;
    }

    @Override
    public double getAvaregeEnergyConsumpation() {
        return 0;
    }

    @Override
    public String toString() {
        return "Hybrid{" +
                "cylinderCapacity=" + cylinderCapacity +
                ", tankSize=" + tankSize +
                ", batteryAge=" + batteryAge +
                ", chargeCapcity=" + chargeCapcity +
                super.toString()+
                '}';
    }
}
