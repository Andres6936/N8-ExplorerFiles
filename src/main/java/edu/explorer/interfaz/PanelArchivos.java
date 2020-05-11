package edu.explorer.interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import edu.explorer.mundo.*;

/**
 * Panel con la informacián de los archivos
 */
public class PanelArchivos extends JPanel implements ListSelectionListener
{
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
    private final JList<Archivo> listaArchivos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pPrincipal es la ventana principal de la aplicacián
     */
    public PanelArchivos( InterfazExploradorArchivos pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Archivos" ) );
        setLayout( new BorderLayout( ) );

        // Agrega los elementos
        listaArchivos = new JList<>( );
        listaArchivos.addListSelectionListener( this );

        // Scroll para la lista
        JScrollPane scroll = new JScrollPane(listaArchivos);
        scroll.setPreferredSize( new Dimension( 250, 200 ) );
        add(scroll, BorderLayout.CENTER );
        setPreferredSize( new Dimension( 300, 250 ) );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la informacián de los archivos
     * @param archivos son los archivos nuevos
     */
    public void refrescar( Archivo[] archivos )
    {
        listaArchivos.setListData( archivos );
    }

    /**
     * Devuelve el archivo seleccionado. Devuelve null si ningán archivo esta seleccionado
     * @return Archivo seleccionado
     */
    public Archivo darArchivoSeleccionado( )
    {
        return ( Archivo )listaArchivos.getSelectedValue( );
    }

    // -----------------------------------------------------------------
    // Eventos
    // -----------------------------------------------------------------

    /**
     * Cambio en la seleccián de la lista
     * @param e es el evento de cambio
     */
    public void valueChanged( ListSelectionEvent e )
    {
        // Seleccián en la lista de archivos
        Archivo archivo = darArchivoSeleccionado( );
        // Muestra el diálogo del archivo
        principal.verInfoArchivo( archivo );
    }
}
