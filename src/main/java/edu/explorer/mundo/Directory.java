package edu.explorer.mundo;

import java.io.*;

/**
 * Representa un directorio en el disco <br>
 * <b>inv:</b><br>
 * ruta != null <br>
 * la ruta comienza por la RAIZ
 */
public class Directory
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Raáz de los archivos
     */
    public static final String ROOT = File.separator;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ruta completa del directorio
     */
    private String path;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de un directorio en la raáz del disco duro
     */
    public Directory( )
    {
        path = ROOT;
        verificarInvariante( );
    }

    /**
     * Constructor del directorio dada la ruta del mismo
     * @param pRuta es la ruta del directorio
     */
    public Directory(String pRuta )
    {
        path = pRuta;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ruta completa del directorio
     * @return Ruta completa del directorio
     */
    public String getPath( )
    {
        return path;
    }

    /**
     * Devuelve el nombre del directorio
     * @return Nombre del directorio sin la ruta del padre
     */
    public String getName( )
    {
        if( isRoot( ) )
            return ROOT;
        else
        {
            // Calcula la áltima posicián en la que aparece el separador de directorios
            int posicion = path.lastIndexOf( File.separator );
            // Devuelve solo el nombre del directorio
            return path.substring( posicion + 1 );
        }
    }

    /**
     * Sube un nivel en la jerarquáa de directorio, si no es la raáz
     */
    public void subirNivel( )
    {
        if( !isRoot( ) )
        {
            int posicion = path.lastIndexOf( File.separator );
            path = path.substring( 0, posicion );
            if(!path.contains(File.separator))
            {
                path += File.separator;
            }
            verificarInvariante( );
        }
    }

    /**
     * Indica si el directorio corresponde a la raáz del sistema de archivos
     * @return True si el directorio es la raáz del sistema de archivos o false en caso contrario
     */
    public boolean isRoot( )
    {
        return path.equals(ROOT);
    }

    /**
     * Devuelve el nombre del directorio
     * @return Nombre del directorio
     */
    public String toString( )
    {
        return getName( );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica que el invariante de la clase se cumpla. Si algo falla, lanza un AssertError. <br>
     * <b>inv: </b> <br>
     * ruta != null <br>
     * la ruta comienza por la RAIZ
     */
    private void verificarInvariante( )
    {
        assert path != null : "Ruta nula";
        assert path.startsWith(ROOT) : "Ruta inválida ";
    }
}
