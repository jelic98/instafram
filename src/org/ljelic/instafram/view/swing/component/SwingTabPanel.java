package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.view.component.PopupMenu;
import org.ljelic.instafram.view.component.ScrollPanel;
import org.ljelic.instafram.view.component.TabPanel;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingTabPanel extends JTabbedPane implements TabPanel {

    private PopupMenu menu;

    public SwingTabPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    if(menu != null) {
                        menu.show(e.getX(), e.getY(), e.getComponent());
                    }
                }
            }
        });
    }

    @Override
    public void add(String title, byte[] icon, ScrollPanel panel) {
        addTab(title + " ", new ImageIcon(icon), (SwingScrollPanel) panel);
    }

    @Override
    public void removeAt(int index) {
        removeTabAt(index);
    }

    @Override
    public void setPopupMenu(PopupMenu menu) {
        this.menu = menu;
    }
}
