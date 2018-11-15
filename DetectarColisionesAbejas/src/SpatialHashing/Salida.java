package SpatialHashing;

import java.io.IOException;
import java.io.PrintWriter;


/**Guarda en un archivo llamado "respuestaConjuntoDeDatosCon" {número de abejas}"abejas.txt"
 * las coordenadas (latitud,longitud y altura) de las abejas que están en riesgo de colisión con cualquier otra.
 * */
public class Salida {

    /**Arreglo de todas las abejas que procesa el programa*/
    Abeja [] TodasAbejas;
    /**Dato que epresenta el momento en el que se inició el programa (en ms)*/
    long startTime;

    /**Construye e inicializa una nueva Salida
     * @param todasAbejas arreglo de todas las abejas que procesa el programa
     * @param startTime Dato que epresenta el momento en el que se inició el programa (en ms)*/
    public Salida (Abeja[] todasAbejas,long startTime) {
        TodasAbejas = todasAbejas;
        this.startTime = startTime;
        GuardarInfo();
    }

    /*Recorre el arreglo de abejas e imprime las abejas cuyo booleano Colisiona sea verdadero */
    public void GuardarInfo() {
        final String file = "respuestaConjuntoDeDatosCon" +TodasAbejas.length+ "abejas.txt";
        try {
            int count = 0;
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            for (int i = 0; i < TodasAbejas.length; i++) {
                if (TodasAbejas[i].isColisiona()) {
                    writer.println(TodasAbejas[i].getLatitud() + "," + TodasAbejas[i].getLongitud() + "," + TodasAbejas[i].getZ());
                    count++;
                }
            }
            long estimatedTime = System.currentTimeMillis() - startTime;
            writer.println("El algoritmo procesando "+TodasAbejas.length+" abejas, se demoró "+ estimatedTime +" ms");
            System.out.println("El algoritmo procesando "+TodasAbejas.length+" abejas, se demoró "+ estimatedTime +" ms");
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Error al guardar el archivo");
        }
    }
}
