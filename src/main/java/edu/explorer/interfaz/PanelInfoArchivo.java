package edu.explorer.interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import edu.explorer.mundo.*;

/**
 * Panel con la informacián de un archivo
 */
public class PanelInfoArchivo extends JPanel
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pArchivo es el archivo a visualizar
     */
    public PanelInfoArchivo( Archivo pArchivo )
    {
        // Archivo en visualizacián
        setLayout( new GridLayout( 3, 2 ) );
        setBorder( new TitledBorder( "Informacián Archivo" ) );

        // Nombre del archivo

        // Etiqueta del nombre
        JLabel etiquetaNombre = new JLabel("Nombre:");
        add(etiquetaNombre);

        // Nombre del archivo
        JLabel nombre = new JLabel(pArchivo.darNombre());
        add(nombre);

        // Tamaáo

        // Etiqueta del tamaáo
        JLabel etiquetaTamanio = new JLabel("Tamaáo:");
        add(etiquetaTamanio);

        // Tamaáo del archivo
        JLabel tamanio = new JLabel(pArchivo.darTamanioString());
        add(tamanio);

        // Fecha Ultima Modificacián

        // Etiqueta de fecha áltima modificacián
        JLabel etiquetaFecha = new JLabel("Ultima Modificacián:");
        add(etiquetaFecha);

        // Ultima Modificacián del archivo
        JLabel fecha = new JLabel(pArchivo.darFechaUltimaModificacion().toString());
        add(fecha);
    }
}
