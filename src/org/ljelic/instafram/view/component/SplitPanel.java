package org.ljelic.instafram.view.component;


public interface SplitPanel extends Component {

    void setTop(Component component);
    void setBottom(Component component);
    void setLeft(Component component);
    void setRight(Component component);
    void setResizeWeight(double weight);
}