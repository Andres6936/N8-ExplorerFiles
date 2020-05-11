package edu.explorer.interfaz;

import edu.explorer.mundo.Archivo;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel para la básqueda de archivos
 */
public class PanelBusqueda extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para buscar
     */
    private static final String BUSCAR = "BUSCAR";

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
     * Lista de archivos
     */
    private final JList<Object> listaArchivos;

    /**
     * Texto de la básqueda
     */
    private final JTextField txtBusqueda;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pPrincipal es la ventana principal de la aplicacián
     */
    public PanelBusqueda( InterfazExploradorArchivos pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Básqueda" ) );
        setLayout( new BorderLayout( ) );

        // Agrega los elementos
        listaArchivos = new JList<>( );

        // Scroll para la lista
        JScrollPane scroll = new JScrollPane(listaArchivos);
        scroll.setPreferredSize( new Dimension( 250, 250 ) );
        add(scroll, BorderLayout.CENTER );

        // Panel de la básqueda
        JPanel panelBusqueda = new JPanel( );
        panelBusqueda.setBorder( new TitledBorder( "Palabra" ) );
        panelBusqueda.setLayout( new BorderLayout( ) );
        add( panelBusqueda, BorderLayout.SOUTH );

        // Botán Buscar
        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.setActionCommand( BUSCAR );
        botonBuscar.addActionListener( this );
        panelBusqueda.add(botonBuscar, BorderLayout.EAST );

        txtBusqueda = new JTextField( );
        txtBusqueda.setColumns( 25 );
        panelBusqueda.add( txtBusqueda, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la informacián de los archivos
     * @param archivos son los archivos presentes en el directorio actual
     */
    public void refrescar( ArrayList<Archivo> archivos )
    {
        if( archivos.size( ) > 0 )
        {
            listaArchivos.setListData( archivos.toArray( ) );
        }
        else
        {
            // Si no devolviá archivos, muestra un mensaje
            listaArchivos.setListData( new Object[]{ "0 archivos encontrados..." } );
        }
    }

    /**
     * Limpia el panel
     */
    public void limpiar( )
    {
        // Limpia la lista de resultados y el campo de texto
        listaArchivos.setListData( new Object[0] );
        txtBusqueda.setText( "" );
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
        // Click en el botán buscar
        String criterio = txtBusqueda.getText( );

        // Verifica que no tenga espacios
        if( criterio.indexOf( ' ' ) != -1 )
        {
            JOptionPane.showMessageDialog( this, "No debe incluir espacios", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            principal.buscar( criterio );
        }
    }
}
