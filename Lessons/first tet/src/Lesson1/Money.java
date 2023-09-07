package Lesson1;

public class Money {
    private int amount;
    private String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
    public boolean equals(Object other) {
        return ((other instanceof Money) &&
                ((Money)other).getCurrency().equals(currency) &&
                ((Money)other).getAmount() == amount);
    }
}