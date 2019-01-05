package org.ljelic.instafram.view.component;

import org.ljelic.instafram.observer.ChangeObservable;
import java.awt.datatransfer.ClipboardOwner;
import java.util.List;

public interface Tree extends Component, ClipboardOwner, ChangeObservable<Node> {

    Node getRoot();
    void setRoot(Node root);
    void setPopupMenu(PopupMenu menu);
    void removeChildren();
    void reload();
    List<Node> getSelectedNodes();
    Node getSelectedNode();
    void addSelectedNode(Node selectedNode);
    void removeSelectedNode(Node selectedNode);
    List<Node> getClipboardNodes();
    void addClipboardNode(Node clipboardNode);
    void clearClipboard();
}