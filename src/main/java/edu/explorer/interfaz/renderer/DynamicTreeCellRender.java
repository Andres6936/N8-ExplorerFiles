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

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (!isRegularFile(value)) {
            try {
                BufferedImage iconDirectory = IconsUtility.getIcon("icons/folder.png");
                setIcon(new ImageIcon(IconsUtility.colorizeImage(IconsUtility.scaleImage(iconDirectory, 16, 16), new Color(174, 185, 192))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    private boolean isRegularFile(Object value) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        return node.getUserObject() instanceof Archivo;
    }
}
