package edu.explorer.interfaz.renderer;

import edu.explorer.interfaz.IconsUtility;
import edu.explorer.mundo.Archivo;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DynamicTreeCellRender extends DefaultTreeCellRenderer {

    private ImageIcon folderIcon = null;

    public DynamicTreeCellRender() {
        // Call to parent construct
        super();

        // The strategy is cache the result of load the image for better performance
        try {
            BufferedImage folderIcon = IconsUtility.getIcon("icons/folder.png");
            this.folderIcon = new ImageIcon(IconsUtility.colorizeImage(IconsUtility.scaleImage(folderIcon, 16, 16), new Color(174, 185, 192)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (!isRegularFile(value)) {
            if (folderIcon != null) setIcon(folderIcon);
        }

        return this;
    }

    private boolean isRegularFile(Object value) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        return node.getUserObject() instanceof Archivo;
    }
}
