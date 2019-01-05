package org.ljelic.instafram.view.adapter.action;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.Menu;
import org.ljelic.instafram.view.component.MenuItem;
import org.ljelic.instafram.view.component.PopupMenu;

public class PopupMenuFactory {

	public PopupMenu getPopupMenu(CompositeActionItem[] items) {
        PopupMenu menu = Config.UI.getPopupMenu();

        for(CompositeActionItem item : items) {
            menu.addComponent(getItems(item));

            if(item.isSeparated()) {
                menu.addSeparator();
            }
        }

        return menu;
	}

    private Component getItems(CompositeActionItem item) {
        if(item.isEmpty()) {
            MenuItem menuItem = Config.UI.getMenuItem();
            menuItem.setText(item.getText());
            menuItem.addObserver(item.getAction());
            menuItem.setPadding(10, 10, 10, 0);
            return menuItem;
        }

        Menu menu = Config.UI.getMenu();
        menu.setText(item.getText());

        for(CompositeActionItem child : item.getChildren()) {
            menu.addComponent(getItems(child));

            if(child.isSeparated()) {
                menu.addSeparator();
            }
        }

        return menu;
    }
}