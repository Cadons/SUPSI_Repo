package matteo_cadoni.serie02.esercizio01;

import matteo_cadoni.serie02.esercizio01.RacingCar;

public class RallyCar extends RacingCar {

    private String copilotName;

    public RallyCar(String brand, int carNumber, String pilotName, String copilotName) {
        super(brand, carNumber, pilotName);
        this.copilotName = copilotName;
    }

    @Override
    public String toString() {
        return "RallyCar{" +
                "copilotName='" + copilotName + '\'' +
                "} " + super.toString();
    }
}
