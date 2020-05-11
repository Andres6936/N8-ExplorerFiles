package edu.explorer.mundo;

import java.io.*;
import java.util.*;

/**
 * Representa un explorador de archivos. <br>
 * <b>inv:</b> <br>
 * dirActual != null <br>
 * subdirectorios != null <br>
 * archivos != null <br>
 * 
 */
public class Explorador
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Directorio actual del explorador de archivos
     */
    private Directory dirActual;

    /**
     * Subdirectorios del directorio actual
     */
    private ArrayList<Directory> subdirectorios;

    /**
     * Archivos en el directorio actual
     */
    private ArrayList<Archivo> archivos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del explorador
     */
    public Explorador( )
    {
        // Define el directorio actual como la raáz del disco C
        dirActual = new Directory( );
        actualizarInformacion( );
    }

    /**
     * Constructor del explorador en un directorio dado
     * @param nombreDirectorio es el nombre del directorio - nombreDirectorio != null, nombreDirectorio comienza por "C:\"
     */
    public Explorador( String nombreDirectorio )
    {
        // Define el directorio actual en la ruta definida que llega como parámetro
        dirActual = new Directory( nombreDirectorio );
        actualizarInformacion( );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ruta del directorio actual
     * @return Ruta actual del explorador
     */
    public String darRutaActual( )
    {
        return dirActual.getPath( );
    }

    /**
     * Sube el directorio actual del explorador al directorio padre.<br>
     * Si se encuentra en el directorio raáz, no hace nada
     * 
     */
    public void subirDirectorio( )
    {
        dirActual.subirNivel( );
        // Actualiza la informacián de archivos y sub-directorios
        actualizarInformacion( );
    }

    /**
     * El directorio especificado se convierte en el directorio actual
     * @param numDir es el námero de directorio a navegar
     */
    public void irDirectorio( int numDir )
    {
        // Verifica que tenga acceso al directorio especificado
        dirActual = (Directory)subdirectorios.get( numDir );
        // Actualiza la informacián de archivos y sub-directorios
        actualizarInformacion( );
    }

    /**
     * Busca en la lista de archivos, el archivo con el nombre dado
     * @param nombre es el nombre del archivo - nombre!=null
     * @return El archivo con el nombre dado
     */
    public Archivo buscarArchivo( String nombre )
    {
        for (Archivo archivo : archivos) {
            if (archivo.darNombre().equals(nombre))
                return archivo;
        }
        return null;
    }

    /**
     * Crea el archivo especificado en la ruta especificada
     * @param nombre es el nombre para el archivo
     * @return Archivo creado. Si el archivo ya existe, devuelve el existente
     * @throws IOException si no tiene acceso para crear el archivo
     */
    public Archivo crearArchivo( String nombre ) throws IOException
    {
        Archivo arch = buscarArchivo( nombre );
        if( arch == null )
        {
            File file = new File( dirActual.getPath( ) + File.separator + nombre );
            file.createNewFile( );
            actualizarInformacion( );
        }
        return buscarArchivo( nombre );
    }

    /**
     * Busca en los archivos de texto del directorio actual los archivos que contienen una palabra que comienza con el prefijo especificado
     * @param prefijo es el prefijo para la básqueda
     * @return Archivos que contienen el prefijo
     */
    public ArrayList<Archivo> buscarPorPrefijo( String prefijo )
    {
        ArrayList<Archivo> respuesta = new ArrayList<>( );
        // Recorre todos los archivos del directorio actual
        for (Archivo archivo : archivos) {
            try {
                if (archivo.esTexto() && archivo.contienePrefijo(prefijo))
                    respuesta.add(archivo);
            } catch (IOException e) {
                // En caso de error de lectura no se hace nada, simplemente no se incluye
            }
        }
        // Devuelve los archivos encontrados
        return respuesta;
    }

    /**
     * Devuelve los archivos del directorio actual
     * @return Archivos del directorio actual
     */
    public Archivo[] darArchivos( )
    {
        Archivo[] archs = new Archivo[archivos.size( )];
        archivos.toArray( archs );
        return archs;
    }

    /**
     * Devuelve los sub-directorios del directorio actual
     * @return Sub-directorios del directorio actual
     */
    public Directory[] darSubDirectorios( )
    {
        Directory[] subdirs = new Directory[subdirectorios.size( )];
        subdirectorios.toArray( subdirs );
        return subdirs;
    }

    /**
     * Actualiza la informacián de la ruta actual
     */
    private void actualizarInformacion( )
    {
        subdirectorios = new ArrayList<>();
        archivos = new ArrayList<>();

        // Saca la lista de archivos de directorio
        File directorio = new File( dirActual.getPath( ) );
        File[] elementos = directorio.listFiles( );
        if( elementos != null )
        {
            for (File elemento : elementos) {
                // Verifica si es directorio o si es archivo
                if (elemento.isDirectory()) {
                    subdirectorios.add(new Directory(elemento.getAbsolutePath()));
                } else
                    if (elemento.isFile()) {
                        archivos.add(new Archivo(elemento.getAbsolutePath()));
                    }
            }
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensián
    // -----------------------------------------------------------------

    /**
     * Mátodo para la extensián 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Mátodo para la extensián2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

    /**
     * Mátodo para la extensián3
     * @param archivo Archivo para la operacián
     * @return respuesta3
     */
    public String metodo3( Archivo archivo )
    {
        return "Respuesta 3 - " + archivo.darNombre( );
    }

    /**
     * Mátodo para la extensián4
     * @param archivo Archivo para la operacián
     * @return respuesta4
     */
    public String metodo4( Archivo archivo )
    {
        return "Respuesta 4 - " + archivo.darNombre( );
    }
}