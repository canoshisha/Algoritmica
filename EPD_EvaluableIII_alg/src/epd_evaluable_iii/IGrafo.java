/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epd_evaluable_iii;

public interface IGrafo {

    public Vertice[] vertice();

    public Vertice getVertice(int i);

    //delvuelve la listaADY del numero de vertice correspondiente
    public LinkedList<Object> listaAdy(int v);

    // encuentra un vertice
    public boolean findVertice(int num);

    //crea un nuevo vertice
    public void nuevoVertice(int nuevo);

    //comprobar que dos vertices son adyacentes
    boolean adyacente(int a, int b);

    //añade arista
    public void nuevaArista(int a, int b);

    //elimina arista
    public void borrarArista(int a, int b);

    public boolean isEmty();

    public int size();

    public void eliminarVertice(int num);
}
