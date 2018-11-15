package SpatialHashing;

import java.util.LinkedList;
/**Algoritmo que detecta abejas en un arreglo que están a 100 metros  menos de otras abejas en el mismo arreglo.
 * */
public class DetectarColisiones {

    /**Mínimos y máximos (en cada dimensión) de las coordenadas de las abejas*/
    double XMin, YMin , ZMin ,XMax, YMax ,ZMax;
    /**Dimensiones de la matriz tridimensional*/
    int largo,alto,ancho;
    /** matriz tridimensional de listas de abejas.*/
    LinkedList<Abeja>[][][] AbejasQueColisionan;
    /** Dimensión (en metros) de la diagonal principal (pasa por la mitad de un cubo) de cada celda de la matriz*/
    final double DiagonalCubo = 100;
    /** Expresión para calcular el lado de cada celda de la matriz dada la diagonal*/
    final double lado = DiagonalCubo/(Math.sqrt(3));// Cáculo del lado de un cubo dada su diagonal principal


    /** Construye e inicializa un nuevo DetectarColisiones
     * @param TodasAbejas  arreglo de las abjeas a procesar
     * @param  numAbejas número de las abejas a procesar*/
    public DetectarColisiones(Abeja [] TodasAbejas,int numAbejas) {

        EncontrarMinMax(TodasAbejas);
        this.largo = (int)  Math.ceil((XMax- XMin) /lado);
        this.alto  = (int)  Math.ceil(((YMax- YMin)/lado));
        this.ancho = (int)  Math.ceil((ZMax- ZMin) /lado);

        if(largo ==0) largo =1;
        if(alto ==0) alto =1;
        if(ancho  ==0) ancho  =1;

        AbejasQueColisionan = new LinkedList [largo][alto][ancho];
        UbicarAbejas(TodasAbejas);
        DetectarAbejasColisionan(TodasAbejas);

    }


    /** Ubica cada abeja en la lista correspondiente a un espacio en la matriz, si la matriz ya tiene una abeja
     * el estado de colisión de ambas queda en verdadero
     * @param TodasAbejas  arreglo de las abjeas a procesar*/
    public void UbicarAbejas(Abeja [] TodasAbejas) {
        int x, y, z;
        for (int i = 0; i < TodasAbejas.length ; ++i) {
            Abeja helper =TodasAbejas[i];

            x = (int) Math.floor((helper.getX()-XMin)/lado);
            y = (int) Math.floor((helper.getY()-YMin)/lado);
            z = (int) Math.floor((helper.getZ()-ZMin)/lado);


            helper.setGrid(x,y,z);

            if (AbejasQueColisionan[x][y][z] == null) {
                AbejasQueColisionan[x][y][z] = new LinkedList<>();
                AbejasQueColisionan[x][y][z].add(helper);
            } else {//hay abejas en la lista
                AbejasQueColisionan[x][y][z].add(helper);
                helper.setColisiona(true);
                AbejasQueColisionan[x][y][z].getFirst().setColisiona(true);
            }
        }
    }

    /** Retorna si la distancia entre dos abejas es menor o igual a 100(metros)
     * @param ab1  un objeto abeja
     * @param ab2  un objeto abeja
     * @return true si la distancia entre las dos abejas es menor o igual a 100(metros) , false si es mayor
     * */

    public boolean EstanCerca (Abeja ab1, Abeja ab2){
        return ( Math.pow((ab2.getX()-ab1.getX()),2) +Math.pow((ab2.getY()-ab1.getY()),2)+ Math.pow((ab2.getZ()-ab1.getZ()),2)) <= 10000;
    }


    /**Helper function
     * <p>Encuentra los mínimos y máximos (en cada dimensión) de las coordenadas de las abejas en el arreglo dado
     * @param TodasAbejas arreglo de abejas a procesar
     * */
    public void EncontrarMinMax(Abeja[] TodasAbejas) {
        Abeja primera = TodasAbejas[0];
        XMin= primera.getX();XMax=XMin;
        YMin= primera.getY();YMax=YMin;
        ZMin= primera.getZ();ZMax=ZMin;
        for (int i=0; i< TodasAbejas.length; i++){
            if (TodasAbejas[i].getX()< this.XMin) this.XMin=TodasAbejas[i].getX();
            if (TodasAbejas[i].getX()> this.XMax) this.XMax=TodasAbejas[i].getX();
            if (TodasAbejas[i].getY()< this.YMin) this.YMin=TodasAbejas[i].getY();
            if (TodasAbejas[i].getY()> this.YMax) this.YMax=TodasAbejas[i].getY();
            if (TodasAbejas[i].getZ()< this.ZMin) this.ZMin=TodasAbejas[i].getZ();
            if (TodasAbejas[i].getZ()> this.ZMax) this.ZMax=TodasAbejas[i].getZ();

        }
    }

    /** Traversa toda la matriz tridimensional de abejas, si una abeja no está en colisión (está sola en un espacio de la matriz)
     * se revisan los espacios adyacentes y se buscan abejas que estén a 100 metros o menos de distancia
     * si se encuentra al menos una abeja con este parámetro , el estado de colisión de ambas es verdadero
     * @param TodasAbejas arreglo de abejas a procesar
     * */
    private void DetectarAbejasColisionan(Abeja [] TodasAbejas) {
        for (Abeja ab : TodasAbejas) {
            int x = ab.getxGrid();
            int y = ab.getyGrid();
            int z = ab.getzGrid();
            int indiceasup,indicebsup,indicecsup;
            if (x < AbejasQueColisionan.length-1) indiceasup = 1;
            else indiceasup =0;

            if (y < AbejasQueColisionan[x].length-1) indicebsup = 1;
            else indicebsup =0;

            if (z < AbejasQueColisionan[x][y].length-1) indicecsup = 1;
            else indicecsup =0;


            if (!ab.isColisiona() && AbejasQueColisionan[x][y][z] != null) {
                for (int a = indice(x); a <= (indiceasup); a++) {
                    for (int b = indice(y); b <= indicebsup; b++) {
                        for (int c = indice(z); c <= indicecsup ; c++) {
                            if ( (AbejasQueColisionan[x+a][y+b][z+c] != null) && (a != 0 || b != 0 || c != 0)) {
                                for (int k = 0; k < AbejasQueColisionan[x+a][y+b][z+c].size() ; k++) {
                                    Abeja ab2 = AbejasQueColisionan[x+a][y+b][z+c].get(k);
                                    if (EstanCerca(ab,ab2)) {
                                        AbejasQueColisionan[x][y][z].getFirst().setColisiona(true);
                                        ab.setColisiona(true);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /** Helper function
     * @param i índice a evaluar
     * @return  -1 si un índice es mayor a 0 ; 0 si el índice es menor o igual a 0
     * */
    public int indice (int i){
        if (i > 0) return -1;
        else return 0;
    }



}


