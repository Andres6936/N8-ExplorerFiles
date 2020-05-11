package edu.explorer.mundo;

import java.io.File;
import java.io.IOException;

import edu.explorer.mundo.Archivo;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar que los mátodos de la clase Archivo están correctamente implementados
 */
public class ArchivoTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Archivo archivo;

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Construye el archivo de un archivo de texto
     */
    private void setupEscenario1( )
    {
        archivo = new Archivo( new File( "test" + File.separator + "data" + File.separator + "texto1.txt" ).getAbsolutePath( ) );
    }

    /**
     * Construye el archivo de un archivo nuevo temporal
     */
    private void setupEscenario2( )
    {
        // Crea un temporal para borrar al cerrar Java
        try
        {
            File file = File.createTempFile( "tmp", "tmp" );
            file.deleteOnExit( );
            archivo = new Archivo( file.getAbsolutePath( ) );
        }
        catch( IOException e )
        {
            fail( "Debe poder crear el archivo temporal" );
        }
    }

    /**
     * Verifica que los mátodos que dan la informacián de un archivo lo hagan de forma correcta. <br>
     * <b> Mátodos a probar: </b> darNombre, darTamanio, darTamanioString, darRuta, esTexto.<br>
     * <b> Objetivo: </b> Probar que los mátodos darNombre(), darTamanio(), darTamanioString(), darRuta() y esTexto() retornen la informacián correspondiente al archivo real.
     * <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la informacián de un archivo (tamaáo, tamaáo en String, nombre, ruta y si es texto) ásta debe corresponder a la informacián real.
     */
    public void testInformacion( )
    {
        setupEscenario1( );

        // Prueba la informacián del archivo
        File ruta = new File( "test" + File.separator + "data" + File.separator + "texto1.txt" );
        String path = ruta.getAbsolutePath( );
        assertEquals( "El nombre es inválido", "texto1.txt", archivo.darNombre( ) );
        assertEquals( "El tamaáo es inválido", 31, archivo.darTamanio( ) );
        assertEquals( "El tamaáo en String es inválido", "31 Bytes", archivo.darTamanioString( ) );
        assertEquals( "La ruta no es inválida", path, archivo.darRuta( ) );
        assertTrue( "Deberáa ser un archivo de texto", archivo.esTexto( ) );
    }

    /**
     * Verifica que el mátodo para escribir a un archivo funcione correctamente. <br>
     * <b> Mátodos a probar: </b> escribirArchivo.<br>
     * <b> Objetivo: </b> Probar que el mátodo escribirArchivo() sea capaz de escribir el contenido dado por el usuario en un archivo real. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el tamaáo de un archivo en el que se acabo de escribir su tamaáo debe ser igual al tamaáo del String que se le escribiá.
     */
    public void testEscribirArchivo( )
    {

        // Crea un archivo temporal vacáo
        setupEscenario2( );
        assertEquals( "El tamaáo es inválido", 0, archivo.darTamanio( ) );
        assertEquals( "El tamaáo en String es inválido", "0 Bytes", archivo.darTamanioString( ) );
        //
        // Escribe el archivo
        try
        {
            archivo.escribirArchivo( "Hola Mundo!" );
            assertEquals( "El tamaáo es inválido", 13, archivo.darTamanio( ) );
            assertEquals( "El tamaáo en String es inválido", "13 Bytes", archivo.darTamanioString( ) );
        }
        catch( IOException e )
        {
            fail( "Debe poder escribir el archivo" );
        }
    }

}
