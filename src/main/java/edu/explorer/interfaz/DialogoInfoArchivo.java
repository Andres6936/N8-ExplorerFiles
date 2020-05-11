package edu.explorer.interfaz;

import java.awt.*;

import javax.swing.*;

import edu.explorer.mundo.*;

/**
 * Dialogo para la visualizacián de la informacián de un archivo
 */
public class DialogoInfoArchivo extends JDialog
{

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo de visualizacián
     * @param principal es la ventana principal de la aplicacián
     * @param archivo es el archivo que se va a visualizar
     */
    public DialogoInfoArchivo( JFrame principal, Archivo archivo )
    {
        super( principal, true );
        setTitle( "Archivo " + archivo.darNombre( ) );
        setLayout( new BorderLayout( ) );

        // Crea el panel con la informacián

        // Panel con la informacián
        PanelInfoArchivo panelInfoArchivo = new PanelInfoArchivo(archivo);
        add(panelInfoArchivo, BorderLayout.CENTER );

        // Pack de la interfaz y centra la caja de diálogo
        pack( );
        setLocationRelativeTo( principal );
    }
}
