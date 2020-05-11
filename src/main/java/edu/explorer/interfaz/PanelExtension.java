package edu.explorer.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import edu.explorer.mundo.Archivo;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Opcián 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opcián 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando Opcián 3
     */
    private static final String OPCION_3 = "OPCION_3";

    /**
     * Comando Opcián 4
     */
    private static final String OPCION_4 = "OPCION_4";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicacián
     */
    private final InterfazExploradorArchivos principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botán Opcián 3
     */
    private final JButton btnOpcion3;

    /**
     * Botán Opcián 4
     */
    private final JButton btnOpcion4;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana es la ventana principal
     */
    public PanelExtension( InterfazExploradorArchivos ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 4 ) );

        // Botán opcián 1

        JButton btnOpcion1 = new JButton("Opcián 1");
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add(btnOpcion1);

        // Botán opcián 2
        JButton btnOpcion2 = new JButton("Opcián 2");
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add(btnOpcion2);

        // Botán opcián 3
        btnOpcion3 = new JButton( "Opcián 3 - N/A" );
        btnOpcion3.setActionCommand( OPCION_3 );
        btnOpcion3.addActionListener( this );
        add( btnOpcion3 );

        // Botán opcián 4
        btnOpcion4 = new JButton( "Opcián 4 - N/A" );
        btnOpcion4.setActionCommand( OPCION_4 );
        btnOpcion4.addActionListener( this );
        add( btnOpcion4 );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la informacián de los botones
     * @param archivoSeleccionado es el archivo seleccionado
     */
    public void actualizar( Archivo archivoSeleccionado )
    {
        if( archivoSeleccionado == null )
        {
            btnOpcion3.setText( "Opcián 3 - N/A" );
            btnOpcion4.setText( "Opcián 4 - N/A" );
        }
        else
        {
            btnOpcion3.setText( "Opcián 3 - " + archivoSeleccionado.darNombre( ) );
            btnOpcion4.setText( "Opcián 4 - " + archivoSeleccionado.darNombre( ) );
        }
    }

    /**
     * Manejo de los eventos de los botones
     * @param e es la accián que generá el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( OPCION_1.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( OPCION_3.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion3( );
        }
        else if( OPCION_4.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion4( );
        }
    }
}
