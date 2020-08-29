package edu.explorer.interfaz;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import edu.explorer.mundo.*;

/**
 * Esta es la ventana principal de la aplicacián.
 */
public class InterfazExploradorArchivos extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private final Explorador explorador;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con los archivos
     */
    private final PanelArchivos panelArchivos;

    /**
     * Panel con los sub-directorios
     */
    private final PanelDirectorios panelDirectorios;

    /**
     * Panel con el directorio actual
     */
    private final PanelDirectorioActual panelDirectorioActual;

    /**
     * Panel para la básqueda de documentos
     */
    private final PanelBusqueda panelBusqueda;

    /**
     * Panel para la creacián de archivos
     */
    private final PanelNuevoArchivo panelNuevoArchivo;

    /**
     * Panel con las extensiones
     */
    private final PanelExtension panelExtension;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la interfaz
     */
    public InterfazExploradorArchivos( )
    {
        // Crea la clase principal
        explorador = new Explorador( );

        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 800, 650 );
        setTitle( "miniExplorer - Explorador de Archivos" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Panel Imagen

        // Panel con la imagen de tátulo

        PanelImagen panelImagen = new PanelImagen();
        add(panelImagen, BorderLayout.NORTH );

        // Panel Izquierdo
        JPanel panelIzquierdo = new JPanel( );
        panelIzquierdo.setLayout( new BorderLayout( ) );
        add( panelIzquierdo, BorderLayout.WEST );
        panelDirectorios = new PanelDirectorios( this );
        panelIzquierdo.add( panelDirectorios, BorderLayout.SOUTH );
        panelArchivos = new PanelArchivos( this );
        panelIzquierdo.add( panelArchivos, BorderLayout.CENTER );

        // Panel Central
        JPanel panelCentral = new JPanel( );
        panelCentral.setLayout( new BorderLayout( ) );
        add( panelCentral, BorderLayout.CENTER );
        panelDirectorioActual = new PanelDirectorioActual( this );
        panelCentral.add( panelDirectorioActual, BorderLayout.NORTH );
        panelBusqueda = new PanelBusqueda( this );
        panelCentral.add( panelBusqueda, BorderLayout.CENTER );
        panelNuevoArchivo = new PanelNuevoArchivo( this );
        panelCentral.add( panelNuevoArchivo, BorderLayout.EAST );

        // Panel Extensiones
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        setLocationRelativeTo( null );
        setResizable( false );

        // Actualiza la informacián
        refrescar( );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Navega a un subdirectorio especificado por su ándice
     * @param numDir es el ándice del subdirectorio destino
     */
    public void navegar( int numDir )
    {
        explorador.irDirectorio( numDir );
        // Actualiza la informacián
        refrescar( );
    }

    /**
     * Sube un nivel el directorio actual
     */
    public void subir( )
    {
        explorador.subirDirectorio( );
        // Actualiza la informacián
        refrescar( );
    }

    /**
     * Realiza una básqueda de archivos por el criterio especificado
     * @param prefijo es el prefijo para la básqueda
     */
    public void buscar( String prefijo )
    {
        ArrayList<Archivo> resultado = explorador.buscarPorPrefijo( prefijo );
        panelBusqueda.refrescar( resultado );
    }

    /**
     * Crea el archivo especificado con el texto especificado
     * @param nombre es el nombre del archivo
     * @param texto es el texto para el archivo
     */
    public void crearArchivo( String nombre, String texto )
    {
        try
        {
            Archivo archivo = explorador.crearArchivo( nombre );
            archivo.escribirArchivo( texto );
            panelNuevoArchivo.limpiar( );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        // Actualiza la informacián
        panelArchivos.refrescar( explorador.darArchivos( ) );
    }

    /**
     * Muestra la informacián del archivo especificado
     * @param archivo es el archivo a visualizar
     */
    public void verInfoArchivo( Archivo archivo )
    {
        //
        // Actualiza la informacián en el panel de botones
        panelExtension.actualizar( archivo );
        //
        // Muestra el dialogo si el archivo no es null
        if( archivo != null )
        {
            DialogoInfoArchivo dialogo = new DialogoInfoArchivo( this, archivo );
            dialogo.setVisible( true );
        }

    }

    /**
     * Actualiza la informacián en la interfaz
     */
    public void refrescar( )
    {
        panelDirectorioActual.refrescar(explorador.darRutaActual());

        panelDirectorios.updateActualPath(explorador.darRutaActual());
        panelDirectorios.refrescar(explorador.darSubDirectorios());

        panelArchivos.refrescar(explorador.darArchivos());
        panelBusqueda.limpiar();
    }

    // -----------------------------------------------------------------
    // Puntos de Extensián
    // -----------------------------------------------------------------

    /**
     * Mátodo para la extensián 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = explorador.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Mátodo para la extensián 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = explorador.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Mátodo para la extensián 3
     */
    public void reqFuncOpcion3( )
    {
        Archivo archivo = panelArchivos.darArchivoSeleccionado( );
        if( archivo == null )
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar un archivo!", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            String resultado = explorador.metodo3( archivo );
            JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
        }
    }

    /**
     * Mátodo para la extensián 4
     */
    public void reqFuncOpcion4( )
    {
        Archivo archivo = panelArchivos.darArchivoSeleccionado( );
        if( archivo == null )
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar un archivo!", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            String resultado = explorador.metodo4( archivo );
            JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
        }
    }

    // -----------------------------------------------------------------
    // Programa principal
    // -----------------------------------------------------------------

    /**
     * Este mátodo ejecuta la aplicacián, creando la ventana de la interfaz
     * @param args son los argumentos de la aplicacián. No se requiere ninguno.
     */
    public static void main( String[] args )
    {
        InterfazExploradorArchivos interfaz = new InterfazExploradorArchivos( );
        interfaz.setVisible( true );
    }
}