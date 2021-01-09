package epd_evaluable_iii;

public class Grafo implements IGrafo{

    int numVerts;
    static int maxVerts = 15;
    Vertice[] tablAdc;

    public Grafo(int max) {
        tablAdc = new Vertice[max];
        numVerts = 0;
        maxVerts = max;
    }

    public Grafo() {
        this(maxVerts);
    }

    @Override
    public Vertice[] vertice() {
        return tablAdc;
    }

    @Override
    public Vertice getVertice(int i) {
        return tablAdc[i];

    }

    //delvuelve la listaADY del numero de vertice correspondiente
    @Override
    public LinkedList<Object> listaAdy(int v) {

        return tablAdc[v].lad;
    }

    // encuentra un vertice
    @Override
    public boolean findVertice(int num) {
        boolean encontrado = false;
        int i = 0;
        while (i < numVerts && encontrado != true) {
            encontrado = tablAdc[i].equals(num);
            if (!encontrado) {
                i++;
            }
        }
        return encontrado;
    }

    //crea un nuevo vertice
    @Override
    public void nuevoVertice(int nuevo) {
        if (numVerts <= maxVerts) {
            boolean existe = findVertice(nuevo);
            if (!existe) {
                Vertice v = new Vertice(nuevo);
                tablAdc[nuevo++] = v;

            }
            numVerts++;
        }
    }

    //comprobar que dos vertices son adyacentes
    @Override
    public boolean adyacente(int a, int b) {
        if (tablAdc[a].lad.find(getVertice(b)) || a == b) {
            return true;
        } else {
            return false;
        }
    }

    //añade arista
    @Override
    public void nuevaArista(int a, int b) {
        if (!adyacente(a, b)) {
            Vertice d = getVertice(b);
            Arista ab = new Arista(d);
            tablAdc[a].lad.addFront(ab);
        }
    }

    //elimina arista
    @Override
    public void borrarArista(int a, int b)  {
        if (adyacente(a, b)) {
            Vertice d = getVertice(b);
            Arista ab = new Arista(d);
            tablAdc[a].lad.remove(ab);
        }
    }

    @Override
    public boolean isEmty() {
        return numVerts==0;
    }

    @Override
    public int size() {
        return numVerts;
    }

    @Override
    public void eliminarVertice(int num) {
        boolean encontrado = false;
        int i = 0;
        int vert = -1;
        while (i < numVerts && encontrado != true) {
            encontrado = tablAdc[i].equals(num);
            if (!encontrado) {
                i++;
            }else{
                vert = i;
            }      
    }
        if(encontrado== true){
           if(vert==numVerts-1){
              tablAdc[vert]=null;
              numVerts--;
           }else{
               tablAdc[vert]=null;
               for(int j=vert+1;j<numVerts;j++){
                   tablAdc[j-1]=tablAdc[j];
               }
               numVerts--;
           }     
            }
    

}
}