package edu.explorer.interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel de la imagen del tátulo
 */
public class PanelImagen extends JPanel
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor sin parámetros
     */
    public PanelImagen( )
    {
        FlowLayout layout = new FlowLayout( );
        layout.setHgap( 0 );
        layout.setVgap( 0 );
        setLayout( layout );

        // Carga la imagen
        ImageIcon icono = new ImageIcon( "data/titulo.jpg" );

        // La agrega a la etiqueta

        // Imagen del tátulo
        JLabel imagen = new JLabel("");
        imagen.setIcon( icono );
        add(imagen);

        // Color de fondo blanco
        setBackground( Color.WHITE );
        setBorder( new LineBorder( Color.GRAY ) );
    }
}
