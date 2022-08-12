package matteo_cadoni.serie05.es2;

// Food products

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

abstract class Food {
    public void eat() {
        System.out.println(String.format("Eating %s", toString()));
    }
}

class IceCream extends Food {
    public void freeze() {
        System.out.println("Freezing " + toString());
    }
    
    @Override
    public String toString() {
        return String.format("IceCream %d", hashCode());
    }
}

abstract class Bakery extends Food {
    public void bake() {
        System.out.println("Baking " + toString());
    }
}

class Cake extends Bakery {
    @Override
    public String toString() {
        return String.format("Cake %d", hashCode());
    }
}

class Bread extends Bakery {
    @Override
    public String toString() {
        return String.format("Bread %d", hashCode());
    }
}
abstract class FoodManufacturer<T extends Food>
{
     public abstract T produce();
     public abstract void addToStock(ArrayList<? super T> collection);
}
class IceCreamMaker extends  FoodManufacturer<IceCream>
{

    @Override
    public IceCream produce() {
        IceCream tmp=new IceCream();
        tmp.freeze();
        return  tmp;
    }

    @Override
    public void addToStock(ArrayList<? super IceCream> collection) {
        collection.add(produce());
    }


}
class Baker extends FoodManufacturer<Bread>
{


    @Override
    public Bread produce() {
        Bread tmp=new Bread();
        tmp.bake();
        return  tmp;
    }
    @Override
    public void addToStock(ArrayList<? super Bread> collection) {
        collection.add(produce());
    }
}
class PastryChef extends FoodManufacturer<Cake>
{


    @Override
    public Cake produce() {
        Cake tmp=new Cake();
        tmp.bake();
        return  tmp;
    }
    @Override
    public void addToStock(ArrayList<? super Cake> collection) {
        collection.add(produce());
    }
}
public class FoodMarket {


private static void putInTheCharity(FoodManufacturer<? extends Food> manufacturer ,ArrayList<Food> box)
{
    var t=manufacturer.produce();
    for (Food e: box) {
        if(e.getClass().isInstance(t))
        {
            return;
        }
    }

        manufacturer.addToStock(box);

}
    public static void main(String[] args) {
        ArrayList<Cake> pastryChef=new ArrayList<>();
        ArrayList<Bread> baker=new ArrayList<>();
        ArrayList<IceCream> iceCreamMaker=new ArrayList<>();
        ArrayList<Food> charityBox=new ArrayList<>();
        var baker_=new Baker();
        var iceCreamMaker_=new IceCreamMaker();
        var pastryChef_=new PastryChef();
        for (int i=0;i<5;i++)
        {
            baker_.addToStock(baker);
            iceCreamMaker_.addToStock(iceCreamMaker);
            pastryChef_.addToStock(pastryChef);
           // baker_.addToStock(iceCreamMaker);//not allowed
        }
        putInTheCharity(pastryChef_,charityBox);
        putInTheCharity(baker_,charityBox);
        putInTheCharity(iceCreamMaker_,charityBox);
        putInTheCharity(iceCreamMaker_,charityBox);

        System.out.println("---Charity Box----");
        charityBox.forEach((e)-> System.out.println(e));

    }
}
