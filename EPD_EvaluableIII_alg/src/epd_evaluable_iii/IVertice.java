package epd_evaluable_iii;

public interface IVertice {

    public LinkedList<Object> getLad();

    public void setLad(LinkedList<Object> lad);

    public boolean equals(Vertice d);

    public int getNumVertice();

    @Override
    public String toString();

    public Boolean getVisitado();

    public void setVisitado(Boolean visitado);
}
