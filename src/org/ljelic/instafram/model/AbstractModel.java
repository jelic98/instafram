package org.ljelic.instafram.model;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.observer.ChangeObservable;
import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.component.Node;
import java.util.*;

public abstract class AbstractModel implements Node, ChangeObservable<AbstractModel> {

    private Node node;
    private String content, prefix;
    private transient ChangeObservableDelegate<Node> delegate;

    AbstractModel(String name, byte[] icon) {
        node = Config.UI.getNode(name, icon);

        setDelegateModel(this);
    }

    AbstractModel(AbstractModel original) {
        this(original.getName(), original.getIcon());
    }

    public abstract AbstractModel getClone();
    public abstract AbstractModel getChild(String name);
    public abstract String getChildType();

    public void setName(String name) {
        node.setName(name);

        notifyObservers(ChangeType.UPDATE, this);
    }

    public String getPrefix() {
        if(prefix == null) {
            prefix = "";
        }

        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean addChild(Node node) {
        boolean result = this.node.addChild(node);

        notifyObservers(ChangeType.UPDATE, this);

        return result;
    }

    @Override
    public void removeChildren() {
        node.removeChildren();

        notifyObservers(ChangeType.REMOVAL, this);
    }

    @Override
    public int compareTo(Node node) {
        return this.node.compareTo(node);
    }

    public String getContent() {
        if(content == null) {
            content = Config.DEFAULT_NODE_CONTENT;
        }

        return content;
    }

    public void setContent(String content) {
        this.content = content;

        notifyObservers(ChangeType.UPDATE, this);
    }

    public String getType() {
        return getClass().getSimpleName();
    }

    public List<String> getProperties() {
        List<String> properties = new LinkedList<>();
        properties.add(getType());
        properties.add(getName());
        properties.add(getContent());

        return properties;
    }

    private void lazyLoadDelegate() {
        if(delegate == null) {
            delegate = new ChangeObservableDelegate<>();
        }
    }

    @Override
    public void addObserver(ChangeObserver observer) {
        lazyLoadDelegate();

        delegate.addObserver(observer);
    }

    @Override
    public void notifyObservers(ChangeType type, AbstractModel bundle) {
        lazyLoadDelegate();

        delegate.notifyObservers(type, bundle);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof AbstractModel)) {
            return false;
        }

        AbstractModel model = (AbstractModel) obj;

        return getType().equals(model.getType()) && getName().equals(model.getName());
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        return node.getName();
    }

    @Override
    public byte[] getIcon() {
        return node.getIcon();
    }

    @Override
    public void setIcon(byte[] icon) {
        node.setIcon(icon);
    }

    @Override
    public boolean hasChildren() {
        return node.hasChildren();
    }

    @Override
    public Set<Node> getChildren() {
        return node.getChildren();
    }

    @Override
    public int getChildCount() {
        return node.getChildCount();
    }

    @Override
    public Node getAncestor() {
        return node.getAncestor();
    }

    @Override
    public void setAncestor(Node node) {
        this.node.setAncestor(node);
    }

    @Override
    public void removeFromAncestor() {
        node.removeFromAncestor();
    }

    @Override
    public Node getDelegateNode() {
        return node.getDelegateNode();
    }

    @Override
    public Node getDelegateModel() {
        return this;
    }

    @Override
    public void setDelegateModel(Node node) {
        this.node.setDelegateModel(node);
    }
}