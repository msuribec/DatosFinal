package Visualizar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.InputStream;

import SpatialHashing.*;

/**Representa un JFrame
 * <br>
 *     Una Instancia de Inicio muestra un título y un campo para introducir el número de abejas para correr el programa .
 *     Así como botones para iniciar y salir ..
 */
public class Inicio extends JFrame implements ActionListener {


    /**String con el número de abejas que se deberán procesar en el programa*/
    private String numAbejas="";
    /**String del botón para iniciar el programa*/
    private final static String COMENZAR = "Inicio";
    /**String del botón para salir del programa*/
    private final static String SALIR = "Salir";


    /**Construye e inicializa un nuevo Inicio*/
    private Inicio(){
        JPanel jPanel1;
        jPanel1 = new JPanel();
        jPanel1.setLayout( new GridLayout( 2, 2) );
        JPanel jpanel2 = new JPanel();
        jpanel2.setLayout(new BorderLayout());


        Color fondo = new Color(255, 155, 77);
        Color letra = new Color(255, 255, 255);

        Border b = new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true);



        JButton butInicio = new JButton();
        butInicio.setActionCommand( COMENZAR );
        butInicio.addActionListener( this );
        butInicio.setBackground(new java.awt.Color(255, 155, 77));
        butInicio.setBorder(b);

        butInicio.setForeground(letra);
        butInicio.setText("INICIO");
        butInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        JButton butSalir = new JButton();
        butSalir.setActionCommand(SALIR);
        butSalir.addActionListener( this );
        butSalir.setBackground(new java.awt.Color(255, 155, 77));
        butSalir.setForeground(letra);
        butSalir.setText("SALIR");
        butSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        butSalir.setBorder(b);

        JLabel labtitulo = new JLabel("COLISIONES ENTRE ABEJAS");
        labtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labtitulo.setForeground(letra);
        labtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labtitulo.setPreferredSize(new java.awt.Dimension(535, 50));


        JLabel labnombre = new JLabel();
        labnombre.setForeground(letra);
        labnombre.setBorder(b);

        InputStream fuenteMontserrat = getClass().getResourceAsStream("/Montserrat-Bold_1.otf");
        try{
            Font Fuente0 = Font.createFont(Font.TRUETYPE_FONT,fuenteMontserrat);
            labnombre.setFont(Fuente0.deriveFont(Font.BOLD, 12)); // NOI18N
            butInicio.setFont(Fuente0.deriveFont(Font.BOLD, 10));
            labtitulo.setFont(Fuente0.deriveFont(Font.BOLD, 20)); // NOI18N
            butSalir.setFont(Fuente0.deriveFont(Font.BOLD, 10));

        }catch (Exception e){

        }

        labnombre.setText("NÚMERO DE ABEJAS");
        labnombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JTextField labinput = new JTextField();
        labinput.setForeground(letra);
        labinput.setBackground(fondo);
        labinput.setBorder(b);
        labinput.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labinput.addActionListener(e -> numAbejas =((JTextField)e.getSource()).getText());
        labinput.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                numAbejas =((JTextField)e.getSource()).getText();
            }
            public void focusGained(FocusEvent e) { }
        });

        JLabel labimagen = new JLabel();
        ImageIcon iconLogo = new ImageIcon(getClass().getResource("/main.png"));
        labimagen.setIcon(iconLogo);

        jPanel1.add(labnombre);
        jPanel1.add(labinput);
        jPanel1.add(butInicio);
        jPanel1.add(butSalir);



        setLayout(new BorderLayout());
        setTitle("App");
        setSize(500,200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setBackground(fondo);
        add(labtitulo, BorderLayout.PAGE_START);

        add(jPanel1,BorderLayout.CENTER);
        add(labimagen, BorderLayout.LINE_END);

        getContentPane().setBackground(fondo);
    }


    /**Dado el número de abejas a procesar, lee el archivo correspondiente, detecta que abejas están en riesgo de colisión y
     * guarda lso resultados en un archivo; mediante la inicialización de nuevos objetos Entrada,DetectarColisiones y Salida, respectivamente*
     * @param numAbejas Número de abejas que se deberán procesar en el programa*/

    public void Ejecutar(int numAbejas){
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long startTime = System.currentTimeMillis();
        Entrada e = new Entrada();
        Abeja[] allBees = e.ReadFile(numAbejas);
        new DetectarColisiones(allBees,numAbejas);
        new Salida(allBees,startTime);
        long afterUsedMem =Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        System.out.println("Memoria utilizada con "+numAbejas+" abejas: "+ actualMemUsed);

    }

    public void actionPerformed( ActionEvent evento ) {
        String comando = evento.getActionCommand( );
        switch (comando){
            case (COMENZAR):
                Ejecutar(Integer.valueOf(numAbejas));
                break;
            case (SALIR):
                this.dispose();
                break;
        }

    }



    public static void main( String[] args ) {
        new Inicio( ).setVisible( true );
    }
}
