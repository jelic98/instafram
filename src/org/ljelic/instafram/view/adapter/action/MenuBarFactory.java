package org.ljelic.instafram.view.adapter.action;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.observer.command.CommandQueue;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.Menu;
import org.ljelic.instafram.view.component.MenuBar;
import org.ljelic.instafram.view.component.MenuItem;

public class MenuBarFactory {

	public MenuBar getMenuBar(CompositeActionItem[] items) {
        MenuBar bar = Config.UI.getMenuBar();

        for(CompositeActionItem item : items) {
            bar.addComponent(getItems(item, true));

            if(item.isSeparated()) {
                bar.addGlue();
            }
        }

        return bar;
	}

	private Component getItems(CompositeActionItem item, boolean topLevel) {
	    if(item.isEmpty() && !topLevel) {
            MenuItem menuItem = Config.UI.getMenuItem();
            menuItem.setText(item.getText());
            menuItem.setIcon(item.getIcon());
            menuItem.setMnemonic(item.getMnemonic());
            menuItem.setAccelerator(item.getAccelerator());
            menuItem.addObserver(item.getAction());
            menuItem.setPadding(10, 10, 10, 0);
            return menuItem;
        }

        Menu menu = Config.UI.getMenu();
	    menu.setText(item.getText());
	    menu.setMnemonic(item.getMnemonic());
        menu.setIcon(item.getIcon());
        
        if(item.getAction() != null) {
        	menu.addObserver(new ChangeObserverAdapter() {
                @Override
                public void onChange() {
                    CommandQueue.push(item.getAction());
                }
            });
        }

	    for(CompositeActionItem child : item.getChildren()) {
	        menu.addComponent(getItems(child, false));

            if(child.isSeparated()) {
                menu.addSeparator();
            }
        }

        return menu;
    }
}