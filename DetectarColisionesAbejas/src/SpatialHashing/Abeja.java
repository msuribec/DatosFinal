package SpatialHashing;

/**La clase Abjea define un punto que representa una ubicación en el espacio de coordenadas
 * (latitud, longitud, altura) donde latitud y longitud se dan en grados  y altura en metros.
 *
 * Cada abeja tiene un estado (booleano) que representa si la abeja está a 100 metros o menos de otra abeja
 * y unas coordenadas que guardan su posición en una matriz tridimensional.
 *
 * .*/
public class Abeja {
    /**Factor de conversión de grados a metros.1 grado se toma como 111111 metros*/
    final static  int factorConversion =111111;
    /**coordenadas en metros (latitud y longitud convertidas de grados a metros)*/
    double x,y,z;
    /**coordenadas en grados de  latitud y longitud */
    double latitud,longitud;
    /**Representa si la abeja está a 100 metros o menos de otra abeja */
    boolean colisiona;

    /**coordenadas en metros de la ubicación de la abeja en una matriz tridimensional*/
    int xGrid,yGrid,zGrid;


    /**Construye e inicializa una abjea.
     * Asigna las coordenadas en grados  de la ubicación de la abeja.(latitud y longitud)
     * Asigna las coordenadas en metros  de la ubicación de la abeja.(x,y,z)Convirtiendo la latitud y longitud de grados a metros
     * @param coordX cadena que representa la latitud en grados de la abeja
     * @param coordY cadena que representa la longitud en grados de la abeja
     * @param coordZ cadena que representa la altura en metros de la abeja*/
    public Abeja(String coordX, String coordY, String coordZ){
        this.latitud=Double.valueOf(coordX);
        this.longitud=Double.valueOf(coordY);
        this.z=Double.valueOf(coordZ);

        this.x=factorConversion*Double.valueOf(coordX);
        this.y=factorConversion*Double.valueOf(coordY);

    }

    /**Asigna las coordenadas en metros de la ubicación de la abeja en una matriz tridimensional
     * @param xGrid coordenada x de la ubicación de la abeja en una matriz 3D (En metros)
     * @param yGrid coordenada y de la ubicación de la abeja en una matriz 3D (En metros)
     * @param zGrid coordenada z de la ubicación de la abeja en una matriz 3D (En metros)*/

    public void setGrid(int xGrid,int yGrid,int zGrid){
        this.xGrid=xGrid;
        this.yGrid=yGrid;
        this.zGrid=zGrid;
    }

    /**Devuelve la coordenada x de la ubicación de la abeja en una matriz 3D (En metros) de esta abeja
     * @return coordenada x de la ubicación de la abeja en una matriz 3D de esta abeja*/
    public int getxGrid() {
        return xGrid;
    }

    /**Devuelve la coordenada y de la ubicación de la abeja en una matriz 3D (En metros) de esta abeja
     * @return coordenada y de la ubicación de la abeja en una matriz 3D de esta abeja*/
    public int getyGrid() {
        return yGrid;
    }

    /**Devuelve la coordenada z de la ubicación de la abeja en una matriz 3D (En metros) de esta abeja
     * @return coordenada z de la ubicación de la abeja en una matriz 3D de esta abeja*/
    public int getzGrid() {
        return zGrid;
    }

    /**Devuelve la coordenada x de esta abeja en metros
     * @return coordenada x de esta abeja en metros*/
    public double getX() { return x; }
    /**Devuelve la coordenada y de esta abeja en metros
     * @return coordenada y de esta abeja en metros*/
    public double getY() { return y; }

    /**Devuelve la coordenada z (altura) de esta abeja en metros
     * @return coordenada z (altura)  de esta abeja en metros*/
    public double getZ() { return z; }

    /**Devuelve la ubicación  , latitud en grados , de esta abeja
     * @return la ubicación  , latitud en grados , de esta abeja*/
    public double getLatitud() { return latitud; }

    /**Devuelve la ubicación  , longitud en grados , de esta abeja
     * @return la ubicación  , longitud en grados , de esta abeja*/
    public double getLongitud() { return longitud; }

    /**Devuelve el booleano que representa si la abeja está a 100 metros o menos de otra abeja
     * @return el booleano que representa si la abeja está a 100 metros o menos de otra abeja */
    public boolean isColisiona() { return colisiona; }

    /**Asigna el booleano que representa si la abeja está a 100 metros o menos de otra abeja
     * @param colisiona  booleano que representa si la abeja está a 100 metros o menos de otra abeja */
    public void setColisiona(boolean colisiona) { this.colisiona = colisiona; }

    @Override
    public String toString() {
        return "Abeja{" +
                "latitud=" + latitud +
                ", longitud=" + longitud +
                ", altura=" + z +
                '}';
    }
}
