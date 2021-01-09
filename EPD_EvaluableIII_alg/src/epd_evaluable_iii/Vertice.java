package epd_evaluable_iii;



public class Vertice implements IVertice{

    private int numVertice;//numero del vertice
     LinkedList<Object> lad;//lista de aristas
    private Boolean visitado ;//bool de visitado

    public Vertice(int numVertice) {// se inicializa los atributos de vertice
        this.numVertice = numVertice;
        lad = new LinkedList<>();
        visitado =false;
    }

    @Override
    public LinkedList<Object> getLad() {//se devuelve la lista de aristas
        return lad;
    }

    @Override
    public void setLad(LinkedList<Object> lad) {//se introduce una lista de aristas 
        this.lad = lad;
    }

    @Override
    public boolean equals(Vertice d) {// compara un vertice con el
        return numVertice == d.getNumVertice();
    }

    @Override
    public int getNumVertice() {// captar el numero del vertice
        return numVertice;
    }

    @Override
    public String toString() {//imprime el numero del vertice
        return  numVertice +"";
    }


    @Override
    public Boolean getVisitado() {// se obtiene el booleano visitado
        return visitado;
    }

    @Override
    public void setVisitado(Boolean visitado) {//se modifica el booleano visitado
        this.visitado = visitado;
    }
    

}
