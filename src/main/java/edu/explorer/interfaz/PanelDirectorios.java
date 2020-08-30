package edu.explorer.interfaz;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import edu.explorer.interfaz.renderer.DynamicTreeCellRender;
import edu.explorer.mundo.Archivo;
import edu.explorer.mundo.Directory;

/**
 * Panel con los subdirectorios actuales
 */
public class PanelDirectorios extends JPanel implements TreeSelectionListener {
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

        tree = new JTree(top);
        // Only allow that user select a unique element
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        // Listen for when the selection changes.
        tree.addTreeSelectionListener(this);
        // Change the style of line of JTree
        tree.putClientProperty("JTree.lineStyle", "None");
        // Dynamic look and feel of JTree nodes
        tree.setCellRenderer(new DynamicTreeCellRender());

        JScrollPane treeView = new JScrollPane(tree);
        treeView.setPreferredSize(new Dimension(250, 200));
        treeView.setViewportView(tree);
        add(treeView, BorderLayout.NORTH);

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
     * Update the list of directories
     *
     * @param directories The new directories
     */
    public void updateListDirectories(Directory[] directories, Archivo[] archivos) {
        // Clear the content of parent node
        top.removeAllChildren();

        for (Directory directory : directories) {
            top.add(new DefaultMutableTreeNode(directory));
        }

        for (Archivo archivo : archivos) {
            top.add(new DefaultMutableTreeNode(archivo));
        }

        notifyTreeOfChanges();
    }

    /**
     * Update the name that root node show to user.
     *
     * @param actualPath Name of actual path (directory)
     */
    public void updateActualPath(final String actualPath) {
        top.setUserObject(actualPath);

        notifyTreeOfChanges();
    }

    // -----------------------------------------------------------------
    // Eventos
    // -----------------------------------------------------------------

    /**
     * Action execute when the user select a node of tree
     *
     * @param e Event that notify the change
     */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        // Returns the last path element of the selection.
        // This method is useful only when the selection model allows a single selection.
        var node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        // Nothing is selected.
        if (node == null) return;

        if (node.isLeaf()) {
            principal.navegar(top.getIndex(node));
        }
    }
}
