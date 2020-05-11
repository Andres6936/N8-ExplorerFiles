package edu.explorer.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel con la informacián del directorio actual
 */
public class PanelDirectorioActual extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para subir el directorio
     */
    private static final String SUBIR = "SUBIR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Interfaz principal de la aplicacián
     */
    private final InterfazExploradorArchivos principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Texto del directorio actual
     */
    private final JTextField txtDirectorio;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pPrincipal es la ventana principal de la aplicacián
     */
    public PanelDirectorioActual( InterfazExploradorArchivos pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Directorio Actual:" ) );
        setLayout( new BorderLayout( ) );

        // Agrega los elementos
        txtDirectorio = new JTextField( );
        txtDirectorio.setColumns( 60 );
        txtDirectorio.setEditable( false );
        add( txtDirectorio, BorderLayout.CENTER );

        // Agrega el botán

        // Botán subir directorio
        JButton botonSubir = new JButton("Subir");
        botonSubir.setActionCommand( SUBIR );
        botonSubir.addActionListener( this );
        add(botonSubir, BorderLayout.EAST );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la informacián del directorio actual
     * @param rutaActual es la ruta actual del explorador
     */
    public void refrescar( String rutaActual )
    {
        txtDirectorio.setText( rutaActual );
    }

    // -----------------------------------------------------------------
    // Eventos
    // -----------------------------------------------------------------

    /**
     * Maneja los eventos de los botones
     * @param e es el evento de click
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( SUBIR.equals( comando ) )
            // Click en el botán subir
            principal.subir( );
    }
}
