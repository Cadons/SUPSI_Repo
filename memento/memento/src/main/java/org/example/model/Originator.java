package org.example.model;

public class Originator  {
    private String value;
    public void setValue(String value){
        this.value = value+this.value;
    }
    public String getValue(){
        return value;
    }
    public Originator(String value) {
        this.value = value;
    }

    public Memento save() {
        return new Memento(value);
    }


    public void restore(Memento memento) {
        value = memento.getValue();
    }
    public static class Memento{
        private String value;
        private Memento(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
}
