package epd_evaluable_iii;

import java.io.*;


public class EPD_evaluable_III {

    public static void main(String[] args) {
        int camino[] = null;
        String fichero = "C:\\Users\\sergi\\Desktop\\tsp\\data\\a280.tsp";
        double distancias[][] = Ficheros(fichero);
        long  inicio,fin,sumatorioTiempos1,sumatorioTiempos2,sumatorioTiempos3;
        int realizado;
       
       
        camino = getCaminoAlgoritmoVoraz(distancias, 27);// calculamos el camino con el algoritmo voraz empezando por la ciudad numero 27
        System.out.println(ImprimirCamino(camino, distancias) + "\n" + "Distancia total=" + getDistanciaTotal(distancias, camino));
       
        camino=busquedaMejorCaminoAlItera(distancias, 27);//calculamos el camino con el algoritmo aleatorio con iteraciones empezando por la ciudad numero 27
        System.out.println(ImprimirCamino(camino, distancias) + "\n" + "Distancia total=" + getDistanciaTotal(distancias, camino));
        
       
        camino=busquedaMejorCaminoAlCon(distancias, 27);//calculamos el camino con el algoritmo aleatorio con contador empezando por la ciudad numero 27
        System.out.println(ImprimirCamino(camino, distancias) + "\n" + "Distancia total=" + getDistanciaTotal(distancias, camino));
        
        
        sumatorioTiempos1=0;
        sumatorioTiempos2=0;
        sumatorioTiempos3=0;
        realizado=100;
        
        for(int i = 0; i<realizado;i++){// repite el numero de veces que se realiza la accion 100 veces 
        inicio = System.nanoTime();
        camino = getCaminoAlgoritmoVoraz(distancias, 27);
         fin = System.nanoTime();
        sumatorioTiempos1+= fin-inicio;// calcula la media de los tiempos, del algoritmoVoraz 1.
        }
        
         for(int i = 0; i<realizado;i++){// repite el numero de veces que se realiza la accion 100 veces 
        inicio = System.nanoTime();
        camino = busquedaMejorCaminoAlItera(distancias, 27);
         fin = System.nanoTime();
        sumatorioTiempos2+= fin-inicio;// calcula la media de los tiempos, algoritmoAleatorio numero de iteraciones .
        }
        
          for(int i = 0; i<realizado;i++){// repite el numero de veces que se realiza la accion 100 veces 
        inicio = System.nanoTime();
        camino=busquedaMejorCaminoAlCon(distancias, 27);
        fin = System.nanoTime();  //captura el tiempo final del test
        sumatorioTiempos3+= fin-inicio;// calcula la media de los tiempos, algoritmoAleatorio numero con contador.
        }
        
        
        System.out.println("\n\n");
        System.out.println(String.format("Tiempo transcurrido en el algoritmo voraz= %d",sumatorioTiempos1/realizado));// muestra el tiempo transcurrido  en el algoritmoVoraz 1
        System.out.println(String.format("Tiempo transcurrido en el primeroaleatorio= %d",sumatorioTiempos2/realizado));// muestra el tiempo transcurrido  en el algoritmoAleatorio numero de iteraciones
        System.out.println(String.format("Tiempo transcurrido en el segundoaleatorio= %d",sumatorioTiempos3/realizado));// muestra el tiempo transcurrido  en el algoritmoAleatorio numero con contador
        
        
        System.out.println("\n\n");
        System.out.println("Fin problema\n");
        
        

    }
    public static double[][] Ficheros( String Cadena) {
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
    public static int[] getCaminoAlgoritmoVoraz(double distancias[][], int inicio) {
        int[] camino = new int[distancias.length + 1];// creamos un vector del tamaño de la cantidad de ciudades que hay mas una mas para volver a la posicion inicial
        camino[0] = inicio - 1;// introducimos el valor inicial en la matriz camino, le restamos uno porque el camino va a estar basado en lalocalizacion de la ciudad seleccionada en la matriz distancia.
        boolean caminoterminado = false;// creamos un booleano para saber cuando ya esta lleno el camino con las ciudades a las que tenemos que ir
        int i = 1;//ponemos la i a uno ya que la posicion inicial es introducida a mano
        while (caminoterminado == false) {// hasta que o haya acabado el camino no termina el bucle
            int anterior = camino[i - 1];// anterior es una variable que lo que hace es marcar la anterior ciudad a la nueva seleccionada para elegir la ciudad con menor distancia entre ellas.
            camino[i] = getSiguienteAlgoritmoVoraz(distancias, camino, anterior, i);//conseguimos la siguiente ciudad a la que ir obteniendo el mejor tiempo de ir entre la ciudad anterior y ella, sin repetir ciudad en el camino
            i++;// sumamos uno para ir guardando el camino completo
            if (i == distancias.length) {// cuando i llega a el fin del camino
                camino[i] = inicio - 1;// introducimos la ciudad inicial para terminar el camino
                caminoterminado = true;// ponemos el booleano a true para indicar que ya tenemos el camino completo
            }
        }

        return camino;// devuelve el camino completo
    }
    public static int getSiguienteAlgoritmoVoraz(double distancias[][], int[] camino, int inicio, int size) {
        int selec = 0;// ciudad ya seleccionada
        int siguiente = 0;//ciudad dos que comparar para seleccionar la mejor ciudad que elegir para el inicio introducido

        while (seleccionado(selec, camino, size)) {//si selec esta ya en camino sumo uno recorriendo por completo todas las ciudades hasta encontrar la primera no usada en el camino
            selec++;// suma uno si selec ya esta en camino
        }
        siguiente = selec + 1;//ponemos siguiente en la ciudad siguiente a la selecionada
        while (siguiente < camino.length - 1) {// mientras que siguiente no haya llegado a la ultima ciudad seguimos ejecutando el codigo
            while (seleccionado(siguiente, camino, size)) {// igual que con selec buscamos la siguiente ciudad que no este en el camino ya
                if (siguiente < camino.length - 1) {
                    siguiente++;
                }
            }
            if (!seleccionado(siguiente, camino, size) && siguiente < camino.length - 1) {// si no esta seleccionado  y siguiente sigue  sin superar el maximo de ciudades que comparar 
                if (getDistancia(distancias, inicio, selec) > getDistancia(distancias, inicio, siguiente)) {// si la distancia de siguiente es mejor que la seleccionada
                    selec = siguiente;// seleccionado pasa a ser la nueva ciudad
                    siguiente++;// y siguiente suma uno para seguir comparando con todas las ciudades del camino

                } else {
                    siguiente++;// si siguiente no esta en el camino pero es peor que la ciudad ya seleccionada 
                }
            }

        }
        return selec;//decuelve selec
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
        String s = "[";
        for (int i = 0; i < camino.length - 1; i++) {// recorre la matriz distancia con el camino creado imprimiendo la ciudad seleccionada en el camino de la matriz distancia
            s += (int)distancias[camino[i]][0] + ",";// convierte en int el numero de la ciudad para que no salga como double quitando el 1.00,2.00,etx
        }
        s +=(int) distancias[camino[camino.length - 1]][0] + "]";
        return s;
    }
    public static int[] busquedaMejorCaminoAlCon(double[][] distancia, int posicionInicial) {
        int[] camino = null;// crea el camino nuevo
        int contador = 0, valorMaximo = 100;// pone el contador a 0 e introducimos el valor maximo del contador

        while (contador < valorMaximo) {// mientras no llegue al maximo el contador se sigue ejecutando

            int[] caminoCandidato = getCaminoAleatorio(distancia, posicionInicial);// obtiene un nuevo camino aleatorio 
            if (camino == null// si estamos en el primer camino
                    || getDistanciaTotal(distancia, caminoCandidato) < getDistanciaTotal(distancia, camino)) {// o si la distancia del nuevo camino es menor que la del camino anterior
                camino = caminoCandidato;// el caminocandidato se convierte en el nuevo camino candidato
                contador = 0;// si el camino es mejor ponemos el contador a 0
            }else{
            contador++;// si el camino nuevo no es mejor sumamos uno al contador
            }
        }

        return camino;// Una vez que el contador llegue al valor maximo, devuelve el camino obtenido

    }
    public static int[] getCaminoAleatorio(double[][] distancia, int inicio) {
          int[] camino = new int[distancia.length + 1];// creamos un vector del tamaño de el camino a realizar

        camino[0] = inicio-1;//introducimos el valor inicial en el vector camino, le restamos uno porque el camino va a estar basado en la localizacion de la ciudad seleccionada en la matriz distancia.
        int siguienteCiudad; 
        for (int i = 1; i < distancia.length; i++) {// 
            siguienteCiudad = getValorAleatorioEnCamino(distancia.length,camino,i);
            camino[i] = siguienteCiudad;
        }
        camino[camino.length - 1] = inicio-1;// introducimos el valor inicial del camino en la ultima posición del vector csmino para indicar la vuelta a la ciudad de inicio.

        return camino;// devuelve el camino obtenido
    }
    public static int getValorAleatorioEnCamino(int valorMaximo, int[] camino,int size) {
        int valorenCamino = (int) Math.floor(Math.random()*valorMaximo); // escogemos un valor aleatorio entre 0 y la última ciudad del fichero que estemos calculando el camino
        while(seleccionado(valorenCamino,camino,size)==true) // llamamos al método "seleccionado", para comprobar si la ciudad que hemos obtenido a través del número aleatorio pertenece a nuestro camino
            valorenCamino = (int) Math.floor(Math.random()*valorMaximo);// en el caso de pertenecer al camino, se generará otro número aleatorio y se repetirá el procedimiento anterior
        return valorenCamino;// en el caso de no pertenecer, devolverá esa ciudad
        
    }
    public static int[] busquedaMejorCaminoAlItera(double[][] distancia, int posicionInicial) {
        int[] camino = null;
        int iterador = 0, valorMaximo = 100;

        while (iterador < valorMaximo) {// mientras que el número de iteraciones no sea el número maximo de iteraciones, buscará caminos alternativos.

            int[] caminoCandidato = getCaminoAleatorio(distancia, posicionInicial);// guardamos un camino nuevo que será un candidato a mejorar el camino que tenemos actualmente.
            if (camino == null
                    || getDistanciaTotal(distancia, caminoCandidato) < getDistanciaTotal(distancia, camino)) {// para comprobar si un camino es mejor que el camino candidato, se comprobará si el coste de cada uno
                camino = caminoCandidato;// si el coste del camino candidato es menor que nuestro actual camino, pondremos que nuestro actual camino será el camino candidato
               
            }
            iterador++; // en el caso de que el camino actual sea mejor, el iterador seguirá aumentando
            }
        

        return camino;// una vez acabado las iteraciones, se devolverá el mejor camino encontrado una vez realizadas todas las iteraciones

    }
    public static void vueltaAtras(int inicio){
        
        
    }
   
}
