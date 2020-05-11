package edu.explorer.mundo;

import java.io.*;
import java.util.*;

/**
 * Representa un archivo en el disco duro. <br>
 * inv: archivo != null <br>
 * archivo.getAbsolutePath( ).startsWith( Directorio.RAIZ ) <br>
 */
public class Archivo
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Archivo actual
     */
    private final File archivo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del archivo
     * @param pRuta Ruta del archivo - pRuta != null
     */
    public Archivo( String pRuta )
    {
        // Crea el archivo
        archivo = new File( pRuta );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el tamaáo del archivo
     * @return Tamaáo del archivo
     */
    public long darTamanio( )
    {
        return archivo.length( );
    }

    /**
     * Indica si es un archivo de texto (el sufijo del nombre es .txt)
     * @return True si es un archivo de texto o false en caso contrario
     */
    public boolean esTexto( )
    {
        String nombre = archivo.getName( );
        return nombre.toUpperCase( ).endsWith( ".TXT" );
    }

    /**
     * Devuelve el tamaáo del archivo formateado como debe ser (bytes, KB, MB, GB o TB)
     * @return Cadena de caracteres con el tamaáo del archivo
     */
    public String darTamanioString( )
    {
        long tamanio = darTamanio( );
        int numVeces = 0;
        while( tamanio > 1024 && numVeces < 4 )
        {
            tamanio = tamanio / 1024;
            numVeces++;
        }
        return Long.toString( tamanio ) + tipoDatos( numVeces );
    }

    /**
     * Devuelve la ruta completa del archivo
     * @return Ruta completa del archivo
     */
    public String darRuta( )
    {
        return archivo.getAbsolutePath( );
    }

    /**
     * Nombre del archivo
     * @return Nombre del archivo
     */
    public String darNombre( )
    {
        return archivo.getName( );
    }

    /**
     * Devuelve la fecha de la áltima modificacián del archivo
     * @return Fecha de la áltima modificacián del archivo
     */
    public Date darFechaUltimaModificacion( )
    {
        return new Date( archivo.lastModified( ) );
    }

    /**
     * Escribe en el archivo el contenido especificado
     * @param contenido es el contenido a escribir
     * @throws IOException error al escribir el archivo
     */
    public void escribirArchivo( String contenido ) throws IOException
    {
        // Utiliza un PrintWriter para escribir el contenido
        PrintWriter escritor = new PrintWriter( archivo );
        escritor.println( contenido );
        escritor.close( );
    }

    /**
     * Verifica si alguna de las palabras que contiene el archivo comienza con el prefijo especificado
     * @param prefijo es el prefijo para la verificacián
     * @return True si contiene el prefijo, False si no
     * @throws IOException error de lectura del archivo
     */
    public boolean contienePrefijo( String prefijo ) throws IOException
    {
        // Comienza suponiendo que no se encuentra
        boolean contiene = false;

        // Abre el archivo utilizando un FileReader
        FileReader reader = new FileReader( archivo );
        // Utiliza un BufferedReader para leer por láneas
        BufferedReader lector = new BufferedReader( reader );

        // Lee lánea por lánea del archivo
        String linea = lector.readLine( );
        while( linea != null && !contiene )
        {
            contiene = lineaContiene( linea, prefijo );
            linea = lector.readLine( );
        }

        // Cierra los lectores y devuelve el resultado
        lector.close( );
        reader.close( );
        return contiene;
    }

    /**
     * Verifica si la lánea especificada contiene el prefijo que llega como parámetro
     * @param linea es la lánea a verificar
     * @param prefijo es el prefijo a buscar
     * @return True si lo contiene, false en caso contrario
     */
    private boolean lineaContiene( String linea, String prefijo )
    {
        // Suprime de la lánea todos los caracteres de puntuacián, remplazándolos por un carácter blanco
        linea = limpiarLinea( linea );

        // Parte la lánea en palabras
        String[] palabras = linea.split( " " );

        // Busca palabra por palabra
        for (String palabra : palabras) {
            if (palabra.toLowerCase().startsWith(prefijo.toLowerCase()))
                return true;
        }
        return false;
    }

    /**
     * Suprime de la lánea todos los caracteres de puntuacián, remplazándolos por un carácter blanco
     * @param linea es la lánea de entrada
     * @return Lánea procesada
     */
    private String limpiarLinea( String linea )
    {
        // Elimina los signos de puntuacián y los tabs
        linea = linea.replace( '\t', ' ' );
        linea = linea.replace( '/', ' ' );
        linea = linea.replace( '.', ' ' );
        linea = linea.replace( ',', ' ' );
        linea = linea.replace( ':', ' ' );
        linea = linea.replace( ';', ' ' );
        linea = linea.replace( '!', ' ' );
        linea = linea.replace( '\"', ' ' );
        linea = linea.replace( '*', ' ' );
        linea = linea.replace( '(', ' ' );
        linea = linea.replace( ')', ' ' );
        linea = linea.replace( '[', ' ' );
        linea = linea.replace( ']', ' ' );
        linea = linea.replace( '{', ' ' );
        linea = linea.replace( '}', ' ' );
        linea = linea.replace( '\'', ' ' );
        linea = linea.replace( '\\', ' ' );

        // Devuelve la lánea sin espacios iniciales/finales
        return linea.trim( );
    }

    /**
     * Representacián del archivo en string
     * @return Representacián del archivo
     */
    public String toString( )
    {
        return darNombre( ) + " - " + darTamanioString( );
    }

    /**
     * Devuelve el tipo de datos segán el tamaáo
     * @param numVeces es el námero de veces dividido
     * @return Tipo de datos
     */
    private String tipoDatos( int numVeces )
    {
        switch( numVeces )
        {
            case 0:
                return " Bytes";
            case 1:
                return " KB";
            case 2:
                return " MB";
            case 3:
                return " GB";
            case 4:
                return " TB";
            default:
                return " Bytes";
        }
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica que el invariante de la clase se cumpla. Si algo falla, lanza un AssertError. <br>
     * <b>inv: </b> <br>
     * archivo != null <br>
     * archivo.getAbsolutePath( ).startsWith( Directorio.RAIZ )
     */
    private void verificarInvariante( )
    {
        assert archivo != null : "Archivo nulo";
        assert archivo.getAbsolutePath( ).startsWith( Directory.ROOT) : "Archivo no pertenece al disco C:";
    }
}
