package edu.explorer.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel para la creacián de nuevos archivos
 */
public class PanelNuevoArchivo extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para crear el archivo
     */
    private static final String CREAR = "CREAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicacián
     */
    private final InterfazExploradorArchivos principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Texto para el archivo
     */
    private final JTextArea txtTexto;

    /**
     * Texto con el nombre del archivo
     */
    private final JTextField txtNombre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pPrincipal es la ventana principal de la aplicacián
     */
    public PanelNuevoArchivo( InterfazExploradorArchivos pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Crear Archivo" ) );
        setLayout( new BorderLayout( ) );

        // Agrega los elementos
        txtTexto = new JTextArea( );
        txtTexto.setLineWrap( true );
        txtTexto.setWrapStyleWord( true );

        // Scroll para el texto
        JScrollPane scroll = new JScrollPane(txtTexto);
        scroll.setPreferredSize( new Dimension( 220, 220 ) );
        add(scroll, BorderLayout.CENTER );

        // Panel de la básqueda
        JPanel panelBusqueda = new JPanel( );
        panelBusqueda.setBorder( new TitledBorder( "Nombre para el Archivo" ) );
        panelBusqueda.setLayout( new BorderLayout( ) );
        add( panelBusqueda, BorderLayout.SOUTH );

        // Botán para crear el archivo
        JButton botonCrear = new JButton("Crear");
        botonCrear.setActionCommand( CREAR );
        botonCrear.addActionListener( this );
        panelBusqueda.add(botonCrear, BorderLayout.EAST );

        txtNombre = new JTextField( );
        txtNombre.setColumns( 14 );
        panelBusqueda.add( txtNombre, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Limpia los texto de nombre y contenido del archivo
     */
    public void limpiar( )
    {
        txtNombre.setText( "" );
        txtTexto.setText( "" );
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

        if( CREAR.equals( comando ) )
            // Click en el botán buscar
            principal.crearArchivo( txtNombre.getText( ), txtTexto.getText( ) );
    }
}
