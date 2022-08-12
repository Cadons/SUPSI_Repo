package matteo_cadoni.serie01.esercizio02;

import java.util.ArrayList;

public class RentalService {
    ArrayList<Vehicle> garage=new ArrayList<Vehicle>();
    public void addVechicle(Vehicle e)
    {
            if(e!=null)
                garage.add(e);
            else
                System.out.println("Insert vehicle!");
    }

    public  void printVechiclesInfo()
    {
        garage.forEach((e)-> System.out.println(e));
    }
    public ArrayList<Vehicle> getVehicleBeyondRageThreshold(double rage)
    {
        ArrayList<Vehicle> tmp =new ArrayList<Vehicle>();
        for (Vehicle e: garage) {
            if(e.getTravelRage()>rage)
            {

                tmp.add(e);
            }

        }
        return tmp;
    }
    public ArrayList<Vehicle> getVehicleWithNSeats(int seats)
    {
        ArrayList<Vehicle> tmp =new ArrayList<Vehicle>();
        for (Vehicle e: garage) {
            if(e.getNumberOfSeats()==seats)
            {
                tmp.add(e);
            }

        }
        return tmp;
    }

}
