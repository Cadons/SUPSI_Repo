package matteo_cadoni.serie04.es2;

import java.util.Comparator;

public class MyQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    public void add(T v) {

        if (head == null) {
            head = new Node<T>(v, null);
            tail = head;

        } else {
            var tmp = new Node<T>(v, null);
            tail.setNext(tmp);
            tail = tmp;

        }
    }
    public void print()
    {
        if(head!=null)
        {
            var tmp=head;
            while (tmp!=null)
            {
                System.out.println(tmp);
                tmp=tmp.getNext();

            }
        }else
        {

                System.out.println("Queue void");

        }

    }
    public void sort(Comparator<T> comparator)
    {
        if(head!=null)
        {
            var start=head;

            while (start!=null)
            {
                Node<T> cur=start.getNext();
                while (cur!=null)
                {
                    if(comparator.compare(start.getValue(),cur.getValue())>=1)
                    {
                        var tmp=start.getValue();
                        start.setValue(cur.getValue());
                        cur.setValue(tmp);

                    }
                    cur=cur.getNext();
                }
                start=start.getNext();
            }
        }

    }
    public T remove()
    {
        if(head!=null)
        {
            var tmp=head;
            head=head.getNext();
            return tmp.getValue();
        }else
            return null;

    }
}
