package edu.explorer.interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import edu.explorer.mundo.*;

/**
 * Panel con los subdirectorios actuales
 */
public class PanelDirectorios extends JPanel implements ListSelectionListener
{
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
     * Lista de directorios
     */
    private final JList<Object> listaDirectorios;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pPrincipal es la ventana principal de la aplicacián
     */
    public PanelDirectorios( InterfazExploradorArchivos pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Sub-Directorios" ) );
        setLayout( new BorderLayout( ) );

        // Agrega los elementos
        listaDirectorios = new JList<>( );
        listaDirectorios.addListSelectionListener( this );

        // Scroll para la lista
        JScrollPane scroll = new JScrollPane(listaDirectorios);
        scroll.setPreferredSize( new Dimension( 250, 180 ) );
        add(scroll, BorderLayout.CENTER );
        setPreferredSize( new Dimension( 300, 230 ) );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la informacián de los directorios
     * @param directories son los directorios nuevos
     */
    public void refrescar( Directory[] directories)
    {
        listaDirectorios.setListData(directories);
    }

    // -----------------------------------------------------------------
    // Eventos
    // -----------------------------------------------------------------

    /**
     * Accián ejecutada cuando la lista cambia de valor
     * @param e es el evento de cambio
     */
    public void valueChanged( ListSelectionEvent e )
    {
        // Seleccián de un elemento de la lista
        int indice = listaDirectorios.getSelectedIndex( );
        if( indice != -1 )
        {
            principal.navegar( indice );
        }
    }
}
