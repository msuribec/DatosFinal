package SpatialHashing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**Lee el archivo con el número de abejas específico y crea un arreglo de objetos Abeja , dondeguarda las
 * coordenadas en latitud , longitud (en grados ambos)y altura (en metros) de cada línea del archivo*/
public class Entrada {

    /**Lee el archivo y devuelve el arreglo con las coordenadas de las abejas
     * @param numeroAbejas Número de abejas que se deberán procesar en el programa
     * @return arreglo con las coordenadas de las abejas*/
    public  Abeja[] ReadFile(int numeroAbejas){

        final String Filename = "ConjuntoDeDatosCon"+numeroAbejas+"abejas.txt";
        Abeja[] allBees = new Abeja[numeroAbejas];

        try {
            BufferedReader br = new BufferedReader(new FileReader(Filename));
            String thisLine = br.readLine();
            thisLine = br.readLine();
            int index = 0;
            while (thisLine != null){ // while !EOF
                String [] cadenaParticionada = thisLine.split(",");
                Abeja abeja = new Abeja(cadenaParticionada[0],cadenaParticionada[1],cadenaParticionada[2]);
                allBees[index++] = abeja;
                thisLine = br.readLine();
            }
        }
        catch(IOException ioe) { System.out.println("Error leyendo el archivo de entrada"); }
        return allBees;
    }
}