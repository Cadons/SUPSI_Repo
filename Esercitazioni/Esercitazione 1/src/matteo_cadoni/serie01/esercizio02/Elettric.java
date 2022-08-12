package matteo_cadoni.serie01.esercizio02;

public  class  Elettric extends Engine implements ElettricEngine {
    private int batteryAge;


    public Elettric(int chargingCapacity, double consumption, int batteryAge) {
        super(chargingCapacity, consumption);
        this.batteryAge = batteryAge;

    }

    @Override
    public int getBatteryAge() {
        return batteryAge;
    }


    @Override
    public int getChargeCapacity() {
        return getCapacity();
    }

    @Override
    public double getAvaregeEnergyConsumpation() {
        return getConsumption();
    }


    @Override
    public String toString() {
        return "Elettric{" +
                "batteryAge=" + batteryAge +
                ", chargeCapcity=" + getCapacity() +
                '}';
    }
}
