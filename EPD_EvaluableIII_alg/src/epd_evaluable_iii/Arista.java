package epd_evaluable_iii;

public class Arista implements IArista{

    Vertice destino;//destino del vertice 

    public Arista(Vertice d) {//inicial variable destino
        destino = d;
    }

    @Override
    public Vertice getDestino() {//obtener destino
        return destino;
    }

    @Override
    public boolean equals(Object obj) {//equals de arista
         Arista other = (Arista) obj;
        return (this.destino == other.destino);//si el mismo destino es la misma arista
    }
    
    
    

}
