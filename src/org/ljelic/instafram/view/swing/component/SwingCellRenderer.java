package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.view.component.Node;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

class SwingCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object item, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, item, sel, expanded, leaf, row, hasFocus);

        byte[] icon = ((Node) item).getIcon();

        if(icon != null) {
            setIcon(new ImageIcon(icon));
        }else {
            setIcon(new ImageIcon(""));
        }

        return this;
    }
}