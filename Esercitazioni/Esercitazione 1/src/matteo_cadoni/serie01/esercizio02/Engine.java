package matteo_cadoni.serie01.esercizio02;

public abstract class Engine {
    private int capacity;
    private  double consumption;
    public Engine(int capacity, double consumption) {
        this.capacity = capacity;
        this.consumption=consumption;
    }



    public int getCapacity() {
        return capacity;
    }

    public double getConsumption() {
        return consumption;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "capacity=" + capacity +
                '}';
    }
}
