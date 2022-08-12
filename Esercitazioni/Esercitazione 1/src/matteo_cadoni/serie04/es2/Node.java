package matteo_cadoni.serie04.es2;

public class Node<T> {
    private T value;
    private Node next;

    public Node(T value, Node next)
    {
        this.value=value;
        this.next=next;
    }
    public T getValue() {
        return value;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
