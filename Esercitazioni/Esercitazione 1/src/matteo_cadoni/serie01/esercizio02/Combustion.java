package matteo_cadoni.serie01.esercizio02;

public  class Combustion extends Engine implements CombustionEngine {

    private int tankSize;


    public Combustion(int cylinderCapacity, double consumption, int tankSize) {
        super(cylinderCapacity, consumption);

        this.tankSize = tankSize;
    }

    public int getCylinderCapacity() {
        return getCapacity();
    }

    public int getTankSize() {
        return tankSize;
    }

    @Override
    public double getAveragePetrolConsumption() {
        return getConsumption();
    }

    @Override
    public String toString() {
        return "Combustion{" +
                "cylinderCapacity=" + getCapacity() +
                ", tankSize=" + tankSize +
                '}';
    }
}
