package org.ljelic.instafram.view.component;

public interface Label extends Component {

    enum Position {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        CENTER
    }

    void setText(String text);
    void setPosition(Position position);
}