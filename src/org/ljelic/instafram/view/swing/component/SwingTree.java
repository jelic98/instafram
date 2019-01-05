package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.core.Transfer;
import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.util.log.Log;
import org.ljelic.instafram.view.adapter.NodeTransferable;
import org.ljelic.instafram.view.component.Node;
import org.ljelic.instafram.view.component.PopupMenu;
import org.ljelic.instafram.view.component.Tree;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SwingTree extends JTree implements Tree {

    private PopupMenu menu;
    private Node root;
    private List<Node> selectedNodes;
    private ChangeObservableDelegate<Node> delegate;
    private Clipboard clipboard;

    public SwingTree() {
        selectedNodes = new LinkedList<>();
        delegate = new ChangeObservableDelegate<>();
        clipboard = new Clipboard(Res.STRINGS.APP_NAME);

        getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        setCellRenderer(new SwingCellRenderer());
        setCellEditor(new SwingTreeCellEditor(this, new DefaultTreeCellRenderer()));
        setEditable(true);

        addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath[] paths = e.getPaths();

                for(int i = 0; i < paths.length; i++) {
                    if(e.isAddedPath(i)) {
                        notifyObservers(ChangeType.SELECTION, ((Node) e.getPath().getLastPathComponent()));
                    }else {
                        notifyObservers(ChangeType.DESELECTION, ((Node) paths[i].getLastPathComponent()));
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    int x = e.getX();
                    int y = e.getY();

                    setSelectionRow(getClosestRowForLocation(x, y));

                    if(menu != null) {
                        menu.show(x, y, e.getComponent());
                    }
                }
            }
        });

        Transfer.instance().tree = this;
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public void setRoot(Node root) {
        this.root = root;

        setModel(new SwingTreeModel(this, (SwingNode) root.getDelegateNode()));
    }

    @Override
    public void removeChildren() {
        getRoot().removeChildren();
    }

    @Override
    public void addSelectedNode(Node selectedNode) {
        if(selectedNodes.contains(selectedNode)) {
            return;
        }

        selectedNodes.add(0, selectedNode);
    }

    @Override
    public void removeSelectedNode(Node selectedNode) {
        Iterator<Node> i = selectedNodes.iterator();

        while(i.hasNext()) {
            if(i.next() == selectedNode) {
                i.remove();
                break;
            }
        }
    }

    @Override
    public List<Node> getSelectedNodes() {
        return selectedNodes;
    }

    @Override
    public Node getSelectedNode() {
        if(selectedNodes.isEmpty()) {
            return null;
        }

        return selectedNodes.get(0);
    }

    @Override
    public void addClipboardNode(Node clipboardNode) {
        List<Node> currentNodes = getClipboardNodes();

        if(currentNodes.contains(clipboardNode)) {
            return;
        }

        currentNodes.add(0, clipboardNode);

        clipboard.setContents(new NodeTransferable(currentNodes), this);
    }

    @Override
    public List<Node> getClipboardNodes() {
        try {
            return (List<Node>) clipboard.getContents(this).getTransferData(NodeTransferable.flavor);
        }catch(Exception e) {
            return new ArrayList<>(0);
        }
    }

    @Override
    public void clearClipboard() {
        clipboard.setContents(new NodeTransferable(new ArrayList<>(0)), this);
    }

    @Override
    public void addObserver(ChangeObserver observer) {
        delegate.addObserver(observer);
    }

    @Override
    public void notifyObservers(ChangeType type, Node bundle) {
        delegate.notifyObservers(type, bundle);
    }

    @Override
    public void setPopupMenu(PopupMenu menu) {
        this.menu = menu;
    }

    @Override
    public void reload() {
        ((DefaultTreeModel) getModel()).reload();
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        Log.d("Violated ISP!");
    }
}