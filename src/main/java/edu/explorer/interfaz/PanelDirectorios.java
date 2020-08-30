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
        // Change the look and feel of JTree
        tree.putClientProperty("JTree.lineStyle", "None");

        ClassLoader loader = getClass().getClassLoader();

        try {
            BufferedImage leafIcon = ImageIO.read(new File(Objects.requireNonNull(loader.getResource("icons/folder.png")).getFile()));
            var cellRenderer = new DefaultTreeCellRenderer();
            cellRenderer.setLeafIcon(new ImageIcon(colorizeImage(scaleImage(leafIcon, 16, 16), new Color(127, 127, 127))));
            tree.setCellRenderer(cellRenderer);
        } catch (IOException e) {
            System.err.println("Cannot found the image");
        }

        JScrollPane treeView = new JScrollPane(tree);
        treeView.setPreferredSize(new Dimension(250, 200));
        treeView.setViewportView(tree);
        add(treeView, BorderLayout.NORTH);

        setPreferredSize(new Dimension(300, 230));
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    private static BufferedImage scaleImage(final BufferedImage img, final int width, final int height) {
        Image resize = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = buffer.createGraphics();

        graphics2D.drawImage(resize, 0, 0, null);
        graphics2D.dispose();

        return buffer;
    }

    private static BufferedImage colorizeImage(final BufferedImage img, Color color) {
        BufferedImage buffer = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = buffer.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.setComposite(AlphaComposite.SrcAtop);
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, img.getWidth(), img.getHeight());
        graphics2D.dispose();

        return buffer;
    }

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
        // Clear the content of parent node
        top.removeAllChildren();

        for (Directory directory : directories) {
            top.add(new DefaultMutableTreeNode(directory.getName()));
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
