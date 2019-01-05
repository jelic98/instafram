package org.ljelic.instafram.view.component;

public interface TabPanel extends Component {

    void add(String title, String icon, ScrollPanel panel);
    void removeAt(int index);
    int getSelectedIndex();
    void setPopupMenu(PopupMenu menu);
}