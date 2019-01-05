package org.ljelic.instafram.view.component;

import java.io.Serializable;
import java.util.Set;

public interface Node extends Serializable, Comparable<Node> {

    long serialVersionUID = 1L;

    String getName();
    String getIcon();
    void setName(String name);
    void setIcon(String icon);
    boolean addChild(Node node);
    void removeChildren();
    boolean hasChildren();
    Set<Node> getChildren();
    int getChildCount();
    Node getAncestor();
    void setAncestor(Node node);
    void removeFromAncestor();
    Node getDelegateNode();
    Node getDelegateModel();
    void setDelegateModel(Node node);
}