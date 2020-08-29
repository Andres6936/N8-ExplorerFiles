package edu.explorer.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import edu.explorer.mundo.Directory;

/**
 * Panel con los subdirectorios actuales
 */
public class PanelDirectorios extends JPanel implements ListSelectionListener {
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

    private final DefaultMutableTreeNode top = new DefaultMutableTreeNode("/");

    private final JTree tree;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     *
     * @param pPrincipal es la ventana principal de la aplicacián
     */
    public PanelDirectorios(InterfazExploradorArchivos pPrincipal) {
        principal = pPrincipal;
        setBorder(new TitledBorder("Directories"));
        setLayout(new BorderLayout());

        // Agrega los elementos
        listaDirectorios = new JList<>();
        listaDirectorios.addListSelectionListener(this);

        tree = new JTree(top);

        JScrollPane treeView = new JScrollPane(tree);
        treeView.setPreferredSize(new Dimension(250, 100));
        treeView.setViewportView(tree);
        add(treeView, BorderLayout.NORTH);

        // Scroll para la lista
        JScrollPane scroll = new JScrollPane(listaDirectorios);
        scroll.setPreferredSize(new Dimension(250, 180));
        add(scroll, BorderLayout.CENTER);

        setPreferredSize(new Dimension(300, 230));
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Update the JTree for render the changes realized to nodes.
     */
    private void notifyTreeOfChanges() {
        // Patter MVC, is needed notify to model of changes in the view
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.reload();
    }

    /**
     * Actualiza la informacián de los directorios
     *
     * @param directories son los directorios nuevos
     */
    public void refrescar(Directory[] directories) {
        listaDirectorios.setListData(directories);

        // Clear the content of parent node
        top.removeAllChildren();

        for (Directory directory : directories) {
            top.add(new DefaultMutableTreeNode(directory.getName()));
        }

        notifyTreeOfChanges();
    }

    public void updateActualPath(final String actualPath) {
        top.setUserObject(actualPath);

        notifyTreeOfChanges();
    }

    // -----------------------------------------------------------------
    // Eventos
    // -----------------------------------------------------------------

    /**
     * Accián ejecutada cuando la lista cambia de valor
     *
     * @param e es el evento de cambio
     */
    public void valueChanged(ListSelectionEvent e) {
        // Seleccián de un elemento de la lista
        int indice = listaDirectorios.getSelectedIndex();
        if (indice != -1) {
            principal.navegar(indice);
        }
    }
}
