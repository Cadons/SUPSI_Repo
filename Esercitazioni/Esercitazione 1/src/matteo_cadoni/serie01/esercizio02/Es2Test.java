package matteo_cadoni.serie01.esercizio02;

public class Es2Test {
    public static void main(String[] args) {
        RentalService s1=new RentalService();
        s1.addVechicle(new CombustionVehicle("Fiat Topolino", 4, 4, 200, new Combustion(400,3.5,50)));
        s1.addVechicle(new ElettricVehicle("Tesla Model 3", 5, 4, 100, new Elettric(750,149,20)));
        s1.addVechicle(new CombustionVehicle("Yamaha MT9", 1, 2, 200, new Combustion(847,5.5,15)));

        s1.addVechicle(new HybridVehicle("Toyota Prius Hybrid", 4, 4, 100, new Elettric(200,3.4,5),new Combustion(1200,2.5,10)));
        System.out.println("Print garage data");
    s1.printVechiclesInfo();
        System.out.println("Print with seats number = 4");
        s1.getVehicleWithNSeats(4).forEach((e)->{
            System.out.println(e);
        });
        System.out.println("Print trave rage treshold 3");
        s1.getVehicleBeyondRageThreshold(3).forEach((e)->{
            System.out.println(e);
        });
        System.out.println("Print trave rage treshold 1");
        s1.getVehicleBeyondRageThreshold(1).forEach((e)->{
            System.out.println(e);
        });

    }
}
