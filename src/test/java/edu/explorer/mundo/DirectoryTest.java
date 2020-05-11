package edu.explorer.mundo;

import java.io.File;

import junit.framework.TestCase;
import edu.explorer.mundo.Directory;

/**
 * Esta es la clase usada para verificar que los mátodos de la clase Directorio están correctamente implementados
 */
public class DirectoryTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Directory directory;

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Construye Directorio en la ruta por defecto
     */
    private void setupEscenario1( )
    {
        directory = new Directory( );
    }

    /**
     * Construye Directorio en la ruta de pruebas
     */
    private void setupEscenario2( )
    {
        directory = new Directory( new File( "test" + File.separator + "data" ).getAbsolutePath( ) );
    }

    /**
     * Verifica que el constructor del directorio sin parámetros recupere la informacián del sistema de archivos de forma correcta. <br>
     * <b> Mátodos a probar: </b> Directorio (constructor), darRuta, darNombre, esRaiz.<br>
     * <b> Objetivo: </b> Probar que el constructor sin parámetros sea capaz de recuperar la informacián del sistema de archivos asociada con el directorio. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear directorio con el constructor sin parámetros, áste debe quedar situado en la raáz del sistema de archivos. <br>
     */
    public void testConstruirDirectorio1( )
    {
        setupEscenario1( );
        assertEquals( "La ruta es inválida", "C:" + File.separator, directory.getPath( ) );
        assertEquals( "La ruta es inválida", "", directory.getName( ) );
        assertTrue( "Debe ser raáz", directory.isRoot( ) );
    }

    /**
     * Verifica que el constructor del directorio con parámetros recupere la informacián del sistema de archivos de forma correcta. <br>
     * <b> Mátodos a probar: </b> Directorio (constructor), darRuta, darNombre, esRaiz.<br>
     * <b> Objetivo: </b> Probar que el constructor con parámetros sea capaz de recuperar la informacián del sistema de archivos asociada con el directorio. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear directorio con el constructor con parámetros . <br>
     */
    public void testConstruirDirectorio2( )
    {
        setupEscenario2( );
        assertEquals( "La ruta es inválida", new File( "test" + File.separator + "data" ).getAbsolutePath( ), directory.getPath( ) );
        assertEquals( "La ruta es inválida", "data", directory.getName( ) );
        assertFalse( "No debe ser raáz", directory.isRoot( ) );
    }

    /**
     * Verifica que el mátodo para subir un nivel en la jerarquáa funcione correctamente. <br>
     * <b> Mátodos a probar: </b> subirNivel.<br>
     * <b> Objetivo: </b> Probar que el mátodo subirNivel() sea capaz de subir un nivel en la jerarquáa de archivos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al subir un nivel la nueva ruta del directorio debe quedar en la ruta del directorio padre.
     */
    public void testSubirDirectorio( )
    {
        setupEscenario2( );
        directory.subirNivel( );
        assertEquals( "La ruta es inválida", new File( "test" ).getAbsolutePath( ), directory.getPath( ) );
    }

}
