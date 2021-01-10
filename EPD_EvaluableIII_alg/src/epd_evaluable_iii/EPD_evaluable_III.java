package epd_evaluable_iii;

import java.io.*;
import poo.io.IO;

public class EPD_evaluable_III {

    static boolean acabado = false;
    static LinkedStack<Vertice> llamadas = new LinkedStack<>();

    public static void main(String[] args) {

        String fichero = "D:\\GIISI\\Segundo\\ALG\\EPD\\tsp\\tsp\\data\\prueba.tsp";
        double distancias[][] = Ficheros(fichero);//Creacion de la matriz de ciudades a partir del archivo tsp.
        Grafo graph = new Grafo(distancias.length);//Creacion del Grafo.
        for (int i = 0; i < graph.tablAdc.length; i++) {//Insertamos los vertices en el Grafo.
            graph.nuevoVertice(i);
        }

        for (int j = 0; j < graph.size(); j++) {//Insertamos las aristas de los vertices en el Grafo.
            for (int k = 0; k < graph.size(); k++) {
                graph.nuevaArista(j, k);
            }

        }
        int caminoOptimo[] = new int[graph.numVerts+1];
        System.out.println("¿En que ciudad comienza el camino?\n");
        int ciudad = (int) IO.readNumber();
        System.out.println("Estamos trabajando en ello\n");
        vueltaAtras(graph.getVertice(ciudad-1), graph, caminoOptimo, distancias);
        System.out.println("El camino es:\n");
        System.out.println(ImprimirCamino(caminoOptimo,distancias));

//        long  inicio,fin,sumatorioTiempos1,sumatorioTiempos2,sumatorioTiempos3;
//        int realizado;
//       
//        camino = getCaminoAlgoritmoVoraz(distancias, 27);// calculamos el camino con el algoritmo voraz empezando por la ciudad numero 27
//        System.out.println(ImprimirCamino(camino, distancias) + "\n" + "Distancia total=" + getDistanciaTotal(distancias, camino));
//       
//        camino=busquedaMejorCaminoAlItera(distancias, 27);//calculamos el camino con el algoritmo aleatorio con iteraciones empezando por la ciudad numero 27
//        System.out.println(ImprimirCamino(camino, distancias) + "\n" + "Distancia total=" + getDistanciaTotal(distancias, camino));
//        
//       
//        camino=busquedaMejorCaminoAlCon(distancias, 27);//calculamos el camino con el algoritmo aleatorio con contador empezando por la ciudad numero 27
//        System.out.println(ImprimirCamino(camino, distancias) + "\n" + "Distancia total=" + getDistanciaTotal(distancias, camino));
//        
//        
//        sumatorioTiempos1=0;
//        sumatorioTiempos2=0;
//        sumatorioTiempos3=0;
//        realizado=100;
//        
//        for(int i = 0; i<realizado;i++){// repite el numero de veces que se realiza la accion 100 veces 
//        inicio = System.nanoTime();
//        camino = getCaminoAlgoritmoVoraz(distancias, 27);
//         fin = System.nanoTime();
//        sumatorioTiempos1+= fin-inicio;// calcula la media de los tiempos, del algoritmoVoraz 1.
//        }
//        
//         for(int i = 0; i<realizado;i++){// repite el numero de veces que se realiza la accion 100 veces 
//        inicio = System.nanoTime();
//        camino = busquedaMejorCaminoAlItera(distancias, 27);
//         fin = System.nanoTime();
//        sumatorioTiempos2+= fin-inicio;// calcula la media de los tiempos, algoritmoAleatorio numero de iteraciones .
//        }
//        
//          for(int i = 0; i<realizado;i++){// repite el numero de veces que se realiza la accion 100 veces 
//        inicio = System.nanoTime();
//        camino=busquedaMejorCaminoAlCon(distancias, 27);
//        fin = System.nanoTime();  //captura el tiempo final del test
//        sumatorioTiempos3+= fin-inicio;// calcula la media de los tiempos, algoritmoAleatorio numero con contador.
//        }
//        
//        
//        System.out.println("\n\n");
//        System.out.println(String.format("Tiempo transcurrido en el algoritmo voraz= %d",sumatorioTiempos1/realizado));// muestra el tiempo transcurrido  en el algoritmoVoraz 1
//        System.out.println(String.format("Tiempo transcurrido en el primeroaleatorio= %d",sumatorioTiempos2/realizado));// muestra el tiempo transcurrido  en el algoritmoAleatorio numero de iteraciones
//        System.out.println(String.format("Tiempo transcurrido en el segundoaleatorio= %d",sumatorioTiempos3/realizado));// muestra el tiempo transcurrido  en el algoritmoAleatorio numero con contador
//        
//        
//        System.out.println("\n\n");
//        System.out.println("Fin problema\n");
//        
//        
    }

    public static double[][] Ficheros(String Cadena) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        double[][] distancias = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(Cadena);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int contador = 0;
            boolean leerCordenadas = false;
            while ((linea = br.readLine()) != null) {// recorremos el fichero buscando solamente las ciudades
                if ("\n".equals(linea) || "EOF".equals(linea)) {
                    leerCordenadas = false;
                }

                if (leerCordenadas) {
                    contador++;// aumentamos 1 cada vez que encontramos una ciudad
                }
                if ("NODE_COORD_SECTION".equals(linea)) {
                    leerCordenadas = true;
                }
            }
            distancias = new double[contador][3];// creamos una matriz con todos las dimensiones de las ciudades encontrados en el fichero
            boolean fin = false;
            while (fin == false) {
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                int i = 0;
                while ((linea = br.readLine()) != null) {// recorremos el fichero rellenando la matriz con todos los datos de cada una de las ciudades
                    if ("\n".equals(linea) || "EOF".equals(linea)) {
                        leerCordenadas = false;
                        if ("EOF".equals(linea)) {
                            fin = true;
                        }
                    }

                    if (leerCordenadas && i < contador) {

                        String linea2[] = linea.replace("   ", " ").replace("  ", " ").trim().split(" ");// eliminamos todos los espacios que no necesitamos del fichero y dibimos los datos de la ciudades por espacios intermedios
                        distancias[i][0] = Double.parseDouble(linea2[0]);// introducimos cada uno de los valores de las ciudades en el lugar correspondiente en la matriz distancias
                        distancias[i][1] = Double.parseDouble(linea2[1]);
                        distancias[i][2] = Double.parseDouble(linea2[2]);
                        i++;
                    }
                    if ("NODE_COORD_SECTION".equals(linea)) {
                        leerCordenadas = true;
                    }
                }
            }

            return distancias;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return distancias;
    }

    public static boolean seleccionado(int siguiente, int[] camino, int size) {// esta funcion comprueba si el elemento seleccionado en este momento se encuentra en el camino
        boolean ocupado = false;// booleano que muestra si ya esta en el camino
        for (int i = 0; i < size && ocupado == false; i++) {// recorremos las ciudades en el camino para ver si la ciudad introducida este en el camino
            if (camino[i] == siguiente) {
                ocupado = true;// si la ciudad esta ya en el camino ponemos el booleano a true
            }
        }
        return ocupado;//devuelve el booleano 
    }

    public static float getDistanciaTotal(double[][] distancia, int[] camino) {
        float distanciaTotal = 0;// inicializamos la distancia total
        for (int i = 0; i < camino.length - 1; i++) {// recorre el camino 
            distanciaTotal += getDistancia(distancia, camino[i], camino[i + 1]);//sumo la distancia de ciudad en ciudad obteniendo la distancia total del camino

        }
        return distanciaTotal;// devuelve la distancia total

    }

    public static double getDistancia(double[][] distancias, int inicio, int siguiente) {
        double primero = distancias[inicio][1] - distancias[siguiente][1];// obtiene el primer double de la formula
        double segundo = distancias[inicio][2] - distancias[siguiente][2];// obtiene el segundo double de la formula
        return Math.sqrt(Math.pow(primero, 2) + Math.pow(segundo, 2));// realiza la formula obteniendo la distancia entre las dos ciudad
    }

    public static String ImprimirCamino(int[] camino, double[][] distancias) {// imprime el camino 
        String s = "";
        for (int i = 0; i < camino.length - 1; i++) {// recorre la matriz distancia con el camino creado imprimiendo la ciudad seleccionada en el camino de la matriz distancia
            s += (int) distancias[camino[i]][0] + "-> ";// convierte en int el numero de la ciudad para que no salga como double quitando el 1.00,2.00,etx
        }
        s += (int) distancias[camino[camino.length - 1]][0] + "";
        return s;
    }

    public static void vueltaAtras(Vertice inicio, Grafo graph, int[] caminoOptimo, double[][] distancia) {
        dfs(graph, inicio, llamadas, caminoOptimo, distancia);
    }

    public static void dfs(Grafo graf, Vertice inicio, LinkedStack llamadas, int[] caminoOptimo, double[][] distancia) {
        
        if (acabado == false) {//si acabado == false
            llamadas.push(inicio);// la pila introduce el vertice inicio
            inicio.setVisitado(true);// inicio pone el booleano visitado a false
        }
        Node vertice = inicio.lad.getFirstNode();//se captura el primera arista de la lista 
        while (acabado == false && vertice != null) {// mientras acabado sea false y vertice distinto de null
            Arista AristaActual = (Arista) vertice.getElement();// cogemos la arista de la lista
            if (AristaActual.destino.getVisitado() != true) {// si no ha sido visitado el vertice adyacente 
                dfs(graf, AristaActual.destino, llamadas, caminoOptimo, distancia);// recursividad con el siguiente vertice adyacente
            }
            vertice = vertice.getNext();// si ha sido visitado miramos el siguiente vertice de la lista

        }
        if (vertice == null && acabado == false) {//si vertice es null y no se ha acabado
            if (llamadas.size()+1 == caminoOptimo.length) {//Si el tamaño de la pila es igual al tamaño del camino óptimo.
                int[] camino = pasoPilaArray(llamadas);//Obtenemos un camino candidato a ser el camino óptimo a partir de la pila.
                Vertice vert =(Vertice) llamadas.last();//Guardamos la ciudad inicial la cual empezamos el camino en una variable tipo vertice.
                camino[0] = vert.getNumVertice();//Guardamos la ciudad inicial en la ultima posicion del camino, indicando la vuelta a la ciudad inicial. 
                double dcamino = getDistanciaTotal(distancia, camino);//Obtenemos la distancia de el camino candidato.
                double dcaAc=getDistanciaTotal(distancia, caminoOptimo);//Obtenemos la distancia de el camino óptimo.
                if ( dcamino< dcaAc || getDistanciaTotal(distancia, caminoOptimo) == 0) {//Comparamos si la distancia del camino candidato es menor que la distancia del camino óptimo.
                    for(int i =0;i<camino.length;i++){//En el caso de que sea menor, se copiarán las ciudades del camino candidato en el camino óptimo.
                        caminoOptimo[i]=camino[i];
                    }
                }
            }
            if (llamadas.isEmpty()) {//compara el inicio vertice inicial con el fin que queremos encontrar
                acabado = true;// si es igual true
            }
            
            if(!acabado){
                llamadas.pop();// se saca el vertice de la pila
                inicio.setVisitado(false);// y se pone visitado a false
            }
            
        }

    }

    public static int[] pasoPilaArray(LinkedStack llamadas) {
        int[] camino = new int[llamadas.size()+1];// se crea un array de vertices del tamaño de la pila
        int i = 0;
        while (i<camino.length-1) {// se va sacando elementos de la pila e introduciendolos en el array
            Vertice vert = (Vertice) llamadas.topIndex(i);
            camino[i+1] = vert.getNumVertice();
            i++;
        }
        return camino;
    }

    

}
