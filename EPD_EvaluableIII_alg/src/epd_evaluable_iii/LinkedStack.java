package epd_evaluable_iii;

public class LinkedStack<E> implements IStack<E> {

    private Node<E> firstNode;//primer nodo
    private Node<E> lastNode;//ultimo nodo
    private int size;//numero de nodos

    public LinkedStack() {
        firstNode = null;//inicializo el primer nodo
        lastNode = null;//inciializo el ultimo nodo
        size = 0;// cantidad de nodos en la  pila enlazada
    }

    @Override
    public int size() {
        return size;// devuelve el numero de nodos
    }

    @Override
    public boolean isEmpty() {
        return size==0;// si size es 0 esta vacio true
    }

    @Override
    public E top() throws IndexOutOfBoundsException {// solo muesta el ultimo que entro
        if (firstNode != null) {//si hay un primer elemento lo devuelve
            return firstNode.getElement();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void push(E element) throws IndexOutOfBoundsException {//introducir un elemento en la pila
        if (element != null) {// si el elemento que se desea insertar es distinto de null
            Node<E> newNode = new Node<E>();//se crea un nuevo nodo
            newNode.setElement(element);//se introduce el elemento nuevo en el nodo
            if (lastNode != null && firstNode != null) {//si no esta vacio
                newNode.setNext(firstNode);//el nuevo nodo pone como siguiente el que habia en primer lugar
                firstNode = newNode;// el nuevo nodo se coloca primero
            } else {// si es el primer elemento en entrar
                newNode.setNext(null);//el nuevo nodo no tiene siguiente elemento
                lastNode = newNode;// el ultimo y primer nodo son ahora el nodo nuevo
                firstNode = newNode;
            }
            size++;// se suma uno al numero de nodos de la pila
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E pop() throws IndexOutOfBoundsException {
        if (firstNode != null) {//si el primer nodo es distinto a null
            if (firstNode == lastNode) {//si solo hay un nodo en la pila
                Node<E> nodeDevolver = new Node<E>();//nodo a devolever
                nodeDevolver = firstNode;// el nodo a devolver sera el primer elemento de la pila
                firstNode = null;// primero elemento = null
                size--;// se resta un elemento
                return nodeDevolver.getElement();// se devuelve el elemento introducido en el nodo
            } else {
                Node<E> nodeDevolver = new Node<E>();// nodo a devolver
                Node<E> nodeNuevo = new Node<E>();// nodo que se pondrá en primer lugar 
                nodeDevolver = firstNode;//el primer nodo sera el que se debe devolver
                nodeNuevo = firstNode.getNext();// se le da valor al nodo nuevo
                firstNode.setNext(null);// // primer nodo pierde el elemento siguiente
                firstNode = nodeNuevo;// el primer nodo es el nuevo
                size--;// se resta un elemento 
                return nodeDevolver.getElement();// se devuelve el elemento que estaba en primer lugar

            }

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {// to string de los nodos
        Node<E> newNode = new Node<E>();// se crea un nodo
        newNode = firstNode;//nodo nuevo primer nodo
        String s = newNode.toString() + " ";//se añade al string el tostring del nodo
        while (newNode.getNext() != null) {// si tiene siguiente
            s += newNode.getNext().toString() + " ";//se añade tostring al string
            newNode = newNode.getNext();// el nodo ahora es el siguiente
        }
        return s;// devuelve el string
    }
    

}
