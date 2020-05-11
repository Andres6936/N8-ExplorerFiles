package edu.explorer.mundo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;
import edu.explorer.mundo.Archivo;
import edu.explorer.mundo.Directory;
import edu.explorer.mundo.Explorador;

/**
 * Esta es la clase usada para verificar que los mátodos de la clase Explorador están correctamente implementados
 */
public class ExploradorTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Explorador explorador;

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Construye un explorador que inicia en la ruta de pruebas (carpeta test/data)
     */
    private void setupEscenario1( )
    {
        File ruta = new File( "test" + File.separator + "data" );
        String path = ruta.getAbsolutePath( );
        if( !path.startsWith( Directory.ROOT) )
        {
            fail( "Ruta inicial inválida" );
        }
        explorador = new Explorador( path );
    }

    /**
     * Construye un Explorador en la raáz del sistema de archivos
     * 
     */
    private void setupEscenario2( )
    {
        explorador = new Explorador( );
    }

    /**
     * Verifica que la ruta inicial sea correcta. <br>
     * <b> Mátodos a probar: </b> Explorador (constructor), darRutaActual.<br>
     * <b> Objetivo: </b> Probar que la ruta del explorador sea la raáz del sistema de archivos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear el explorador con el constructor sin parámetros, este debe quedar situado en la raáz del sistema de archivos.
     */
    public void testRutaInicial( )
    {
        setupEscenario2( );
        assertEquals( "La ruta es inválida", "C:" + File.separator, explorador.darRutaActual( ) );
    }

    /**
     * Verifica que el constructor del explorador recupere la informacián del sistema de archivos de forma correcta. <br>
     * <b> Mátodos a probar: </b> Explorador (constructor), darSubdirectorios, darArchivos.<br>
     * <b> Objetivo: </b> Probar que el constructor de la clase Explorador es capaz de recuperar correctamente la informacián del sistema de archivos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear el explorador en un directorio determinado debe quedar situado en este. <br>
     * 2. El explorador sálo debe contener los subdirectorios que pertenecen al directorio en el que se encuentra situado. <br>
     * 3. El explorador sálo debe contener los archivos que pertenecen al directorio en el que se encuentra situado.
     */
    public void testConstruirEscenario( )
    {
        setupEscenario1( );

        // Verifica que la ruta del explorador sea correcta
        File ruta = new File( "test" + File.separator + "data" );
        String path = ruta.getAbsolutePath( );
        assertEquals( "La ruta es inválida", path, explorador.darRutaActual( ) );

        // Verifica los directorios
        Directory[] directories = explorador.darSubDirectorios( );

        // assertEquals( "Debe tener 2 directorios", 2, directorios.length );
        for (Directory directory : directories) {
            if (!"sub1".equals(directory.getName()) && !"sub2".equals(directory.getName())) {
                fail("La lista de directorios es inválida");
            }
        }

        // Verifica los archivos
        Archivo[] archivos = explorador.darArchivos( );

        assertEquals( "Debe tener 3 archivos", 3, archivos.length );
        for (Archivo archivo : archivos) {
            if (!"texto1.txt".equals(archivo.darNombre()) && !"texto2.txt".equals(archivo.darNombre()) && !"texto3.txt".equals(archivo.darNombre())) {
                fail("La lista de archivos es inválida");
            }
        }
    }

    /**
     * Verifica que los mátodos para subir y bajar por los subdirectorios funcionan de forma correcta. <br>
     * <b> Mátodos a probar: </b> irDirectorio, subirDirectorio.<br>
     * <b> Objetivo: </b> Probar que los mátodos subirDirectorio() e irDirectorio() funcionan correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al ir a un subdirectorio del directorio actual, el explorador debe quedar en tal subdirectorio. <br>
     * 2. El explorador sálo debe contener los subdirectorios del nuevo directorio. <br>
     * 3. El explorador sálo debe contener los archivos que pertenecen al nuevo directorio. <br>
     * 4. Al subir en el sistema de archivos, el explorador debe quedar en el directorio padre del directorio actual.
     */
    public void testSubirBajar( )
    {
        setupEscenario1( );

        // BAJAR a sub1
        // Busca el directorio
        int num = darNumeroDirectorio( "sub1" );
        if( num < 0 )
        {
            fail( "El directorio 'sub1' debe existir" );
        }

        // Navega al directorio especifico
        explorador.irDirectorio( num );

        // Verifica la ruta
        File ruta = new File( "test" + File.separator + "data" + File.separator + "sub1" );
        String path = ruta.getAbsolutePath( );
        assertEquals( "La ruta es inválida", path, explorador.darRutaActual( ) );

        // Verifica los sub-directorios y los archivos
        assertEquals( "No debe tener sub-directorios", 0, explorador.darSubDirectorios( ).length );
        assertEquals( "Debe tener solo un archivo", 1, explorador.darArchivos( ).length );
        assertEquals( "El archivo debe ser 'archivo1.txt'", "archivo1.txt", explorador.darArchivos( )[ 0 ].darNombre( ) );

        // SUBIR a test/data
        explorador.subirDirectorio( );

        // Verifica la ruta
        ruta = new File( "test" + File.separator + "data" );
        path = ruta.getAbsolutePath( );

        assertEquals( "La ruta es inválida", path, explorador.darRutaActual( ) );

        // BAJAR a sub2
        // Busca el directorio
        num = darNumeroDirectorio( "sub2" );
        if( num < 0 )
        {
            fail( "El directorio 'sub2' debe existir" );
        }

        // Navega al directorio especifico
        explorador.irDirectorio( num );

        // Verifica la ruta
        ruta = new File( "test" + File.separator + "data" + File.separator + "sub2" );
        path = ruta.getAbsolutePath( );
        assertEquals( "La ruta es inválida", path, explorador.darRutaActual( ) );

        // Verifica los sub-directorios y los archivos
        assertEquals( "No debe tener sub-directorios", 0, explorador.darSubDirectorios( ).length );
        assertEquals( "Debe tener solo un archivo", 1, explorador.darArchivos( ).length );
        assertEquals( "El archivo debe ser 'archivo1.txt'", "archivo2.txt", explorador.darArchivos( )[ 0 ].darNombre( ) );

        // SUBIR a test/data
        explorador.subirDirectorio( );

        // Verifica la ruta
        ruta = new File( "test" + File.separator + "data" );
        path = ruta.getAbsolutePath( );
        assertEquals( "La ruta es inválida", path, explorador.darRutaActual( ) );
    }

    /**
     * Verifica que el mátodo para ir a un directorio funciona de forma correcta. <br>
     * <b> Mátodos a probar: </b> irDirectorio.<br>
     * <b> Objetivo: </b> Probar que el mátodo irDirectorio() funciona correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al ir a un subdirectorio del directorio actual, el explorador debe quedar en tal subdirectorio. <br>
     * 2. El explorador sálo debe contener los subdirectorios del nuevo directorio.
     */
    public void testIrDirectorio( )
    {

        setupEscenario2( );

        // Verifica que tiene por lo menos un directorio
        if( explorador.darSubDirectorios( ).length == 0 )
        {
            fail( "Debe tener por lo menos un directorio en C: para probar." );
        }

        // Busca el primer directorio
        Directory directory = explorador.darSubDirectorios( )[ 0 ];
        String nombre = directory.getName( );

        // Navega al directorio especifico
        explorador.irDirectorio( 0 );

        // Verifica la ruta
        assertEquals( "La ruta es inválida", "C:" + File.separator + nombre, explorador.darRutaActual( ) );
    }

    /**
     * Verifica que el mátodo para subir un directorio en la jerarquáa funcione de forma correcta. <br>
     * <b> Mátodos a probar: </b> irDirectorio, subirDirectorio.<br>
     * <b> Objetivo: </b> Probar que el mátodo subirDirectorio() funciona correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al ir a un subdirectorio del directorio actual, el explorador debe quedar en tal subdirectorio. <br>
     * 2. Al subir en el sistema de archivos, el explorador debe quedar en el directorio padre del directorio actual.
     */
    public void testSubirDirectorio( )
    {
        setupEscenario2( );

        // Verifica que tiene por lo menos un directorio
        if( explorador.darSubDirectorios( ).length == 0 )
        {
            fail( "Debe tener por lo menos un directorio en C: para probar." );
        }

        // Busca el primer directorio
        Directory directory = explorador.darSubDirectorios( )[ 0 ];
        String nombre = directory.getName( );

        // Navega al directorio especifico
        explorador.irDirectorio( 0 );

        // Verifica la ruta
        assertEquals( "La ruta es inválida", "C:" + File.separator + nombre, explorador.darRutaActual( ) );

        // Sube el directorio
        explorador.subirDirectorio( );
        assertEquals( "La ruta es inválida", "C:" + File.separator, explorador.darRutaActual( ) );

        // Sube nuevamente y debe quedar en la misma ruta
        explorador.subirDirectorio( );
        assertEquals( "La ruta es inválida", "C:" + File.separator, explorador.darRutaActual( ) );
    }

    /**
     * Verifica que el mátodo buscar por prefijo funcione de forma correcta. <br>
     * <b> Mátodos a probar: </b> buscarPorPrefijo.<br>
     * <b> Objetivo: </b> Probar que el mátodo buscarPorPrefijo() funciona correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al dar un prefijo que se sabe que está en N archivos se deben obtener la lista con los N archivos. <br>
     * 2. Al dar un prefijo que se sabe que no está en ningán archivo se debe obtener una lista de archivos vacáa.
     */
    public void testBuscarPrefijo( )
    {
        setupEscenario1( );

        // Busca por el prefijo "prue"
        ArrayList archivos = explorador.buscarPorPrefijo( "prue" );

        // Verifica los resultados
        assertEquals( "Debe tener 3 resultados", 3, archivos.size( ) );
        for( int i = 0; i < archivos.size( ); i++ )
        {
            Archivo archivo = ( Archivo )archivos.get( i );
            if( !"texto1.txt".equals( archivo.darNombre( ) ) && !"texto2.txt".equals( archivo.darNombre( ) ) && !"texto3.txt".equals( archivo.darNombre( ) ) )
            {
                fail( "El resultado de la básqueda es inválido" );
            }
        }

        // Busca por el prefijo "nuevo"
        archivos = explorador.buscarPorPrefijo( "nuevo" );

        // Verifica los resultados
        assertEquals( "Debe tener un resultado", 1, archivos.size( ) );
        Archivo archivo = ( Archivo )archivos.get( 0 );
        if( !"texto2.txt".equals( archivo.darNombre( ) ) )
        {
            fail( "El resultado de la básqueda es inválido" );
        }

        // Busca por el prefijo "chao"
        archivos = explorador.buscarPorPrefijo( "chao" );

        // Verifica los resultados
        assertEquals( "Debe tener un resultado", 1, archivos.size( ) );
        archivo = ( Archivo )archivos.get( 0 );
        if( !"texto3.txt".equals( archivo.darNombre( ) ) )
        {
            fail( "El resultado de la básqueda es inválido" );
        }

        // Busca por el prefijo "nunca"
        archivos = explorador.buscarPorPrefijo( "nunca" );

        // Verifica los resultados
        assertEquals( "Debe tener un resultado", 0, archivos.size( ) );
    }

    /**
     * Verifica que el mátodo para crear archivos los genere correctamente. <br>
     * <b> Mátodos a probar: </b> crearArchivo.<br>
     * <b> Objetivo: </b> Probar que el mátodo crearArchivo() sea capaz de crear un archivo en el directorio en el que se encuentra el explorador. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un archivo este debe encontrarse en el directorio actual del explorador.
     */
    public void testCrearArchivo( )
    {
        setupEscenario1( );

        // Crea el archivo
        try
        {
            explorador.crearArchivo( "temporal.txt" );
            File archivo = new File( explorador.darRutaActual( ), "temporal.txt" );
            if( !archivo.exists( ) )
            {
                fail( "El archivo debe existir" );
            }
            archivo.delete( );
        }
        catch( IOException e )
        {
            fail( "Debe poder crear el archivo" );
        }

    }

    // -----------------------------------------------------------------
    // Mátodos Auxiliares
    // -----------------------------------------------------------------

    /**
     * Devuelve el námero del directorio especificado
     * @param nombre es el nombre del directorio
     * @return Námero del directorio en el explorador
     */
    private int darNumeroDirectorio( String nombre )
    {
        for( int i = 0; i < explorador.darSubDirectorios( ).length; i++ )
        {
            Directory dir = explorador.darSubDirectorios( )[ i ];
            if( nombre.equals( dir.getName( ) ) )
            {
                return i;
            }
        }
        fail( "El directorio " + nombre + " no existe!" );
        return -1;
    }
}