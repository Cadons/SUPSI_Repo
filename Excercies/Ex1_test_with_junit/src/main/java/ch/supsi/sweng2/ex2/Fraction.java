package ch.supsi.sweng2.ex2;

import java.util.Objects;

public class Fraction {
    private long num;
    private long den;
    public Fraction(long num, long den) throws Exception {
        if(den!=0) {
        this.num=num;
        this.den=den;
        }else {
            throw new Exception();
        }
    }
    public Fraction sum(Fraction b) throws Exception {

            var den = b.getDen() * this.den;
            var out = new Fraction((long) ((den / (double) this.den) * this.num + (den / (double) b.getDen()) * b.getNum()), den);

            return semplify(out);

    }
    public Fraction subtract(Fraction b) throws Exception {
        var den=b.getDen()*this.den;
        var out= sum(new Fraction(-b.getNum(),b.getDen()));

        return semplify(out);
    }
    public Fraction multiply(Fraction b) throws Exception {
        var out= new Fraction(num*b.getNum(),this.den*b.getDen());
        return semplify(out);
    }
    public Fraction divide(Fraction b) throws Exception {
        var out= multiply(new Fraction(b.getDen(),b.getNum()));
        return semplify(out);
    }

public Fraction semplify(Fraction f){
    var gcd=gcd(f.getNum(),f.getDen());
    f.setNum(f.getNum()/gcd);
    f.setDen(f.getDen()/gcd);
    return f;
}


    private long gcd(long u, long v){
        if(v==0)
            return u;

        return gcd(v,u%v);
    }

    public long getDen() {
        return den;
    }

    public void setDen(long den) {
        this.den = den;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public long getNum() {
        return num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return num == fraction.num && den == fraction.den;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, den);
    }
}
