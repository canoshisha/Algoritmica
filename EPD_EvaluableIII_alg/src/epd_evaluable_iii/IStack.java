
package epd_evaluable_iii;


public interface IStack<E> {

    public int size();


    public boolean isEmpty();


    public E top() throws IndexOutOfBoundsException;
    


    public void push(E element) throws IndexOutOfBoundsException;

    
    public E pop() throws IndexOutOfBoundsException;
    

    }

