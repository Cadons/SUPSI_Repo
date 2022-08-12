package matteo_cadoni.serie06.es2;

import java.util.Optional;
import java.util.Stack;

interface AdvancedStack {
	
	interface Evaluate {
		
		/**
		 * The condition to test {@code objectToTest} against
		 * 
		 * @param objectToTest
		 * @return
		 */
		boolean verify(Object objectToTest);
	}
	

    /**
     * Push given object in the stack
     */
    void push(Object o);

    /**
     * Removes and returns the top element from the stack
     * 
     * @return
     */
    Optional<Object> pop();

    /**
     * Returns the top element without removing it from the stack
     * 
     * @return
     */
    Optional<Object> peek();

    /**
     * Returns the size of the stack
     * 
     * @return
     */
    long size();

    /**
     * Returns true if the stack is empty
     * 
     * @return
     */
    boolean isEmpty();
    
    /**
     * Tests each element inside the stack with {@code testLogic} and returns the count {@code testLogic} has returned true
     * 
     * @param testLogic
     * @return
     */
    long count(Evaluate testLogic);
}

class CustomStack implements AdvancedStack
{


    private class StackElement
    {
        private Object value;
        private StackElement next;
        StackElement(Object value, StackElement next)
        {
            this.value=value;
            this.next=next;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }

        public void setNext(StackElement next) {
            this.next = next;
        }

        public StackElement getNext() {
            return next;
        }
    }
    private StackElement head=null, tail=null;
    private int cnt=0;

    @Override
    public void push(Object o) {

       StackElement data=new StackElement(o,null);
        if(head==null)
        {
            head=data;
            tail=head;
        }else
        {
            tail.setNext(data);
            tail=data;
        }
        cnt++;
    }

    @Override
    public Optional<Object> pop() {
        StackElement tmp=head;
        if(head==tail)
        {
            cnt--;

            head=null;
            tail=null;
            return Optional.of(tmp.value);
        }
        while (tmp.getNext()!=tail)
            tmp=tmp.getNext();
        var tmp2=tmp.getNext();
        tmp.setNext(null);
        tail=tmp;
        cnt--;
        return Optional.of(tmp2.value);
    }

    @Override
    public Optional<Object> peek() {
        return Optional.of(tail.getValue());
    }

    @Override
    public long size() {
        return cnt;
    }

    @Override
    public boolean isEmpty() {
        return head!=null?false:true;
    }

    @Override
    public long count(Evaluate testLogic) {
      long counter=0;
        StackElement tmp=head;
        while (tmp.getNext()!=null)
        {
            if(testLogic.verify(tmp.getValue()))
            {
                counter++;
            }
            tmp=tmp.getNext();
        }
        return counter;
    }
}

