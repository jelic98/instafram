package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.view.component.Node;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

class SwingTreeModel extends DefaultTreeModel {

    private final JTree tree;

    SwingTreeModel(JTree tree, SwingNode root) {
        super(root);
        this.tree = tree;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if(!(parent instanceof Node)) {
            return null;
        }

        Node model = (Node) parent;

        if(!model.hasChildren()) {
            return null;
        }

        return model.getChildren().toArray()[index];
    }

    @Override
    public int getChildCount(Object parent) {
        Node model = (Node) parent;

        if(!model.hasChildren()) {
            return 0;
        }

        return ((Node) parent).getChildren().size();
    }

    @Override
    public int getIndexOfChild(Object parent, Object node) {
        int index = -1;

        if(parent instanceof Node) {
            Node model = (Node) parent;

            for(Node child : model.getChildren()) {
                index++;

                if(child == node) {
                    break;
                }
            }
        }

        return index;
    }

    @Override
    public void reload() {
        super.reload();

        for(int i = 0; i < tree.getRowCount(); i++) {
            TreePath path = tree.getPathForRow(i);
            Object node = path.getLastPathComponent();

            if(node instanceof Node) {
                tree.expandPath(path);
            }
        }
    }
}