package org.ljelic.instafram.view.adapter.action;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.view.component.Button;
import org.ljelic.instafram.view.component.ToolBar;

public class ToolBarFactory {

	public ToolBar getToolBar(ActionItem[] items) {
        ToolBar bar = Config.UI.getToolBar();
        bar.setFloatable(true);

		for(int i = 0; i < items.length; i++) {
			ActionItem item = items[i];

		    if(i > 0) {
                bar.addSeparator();
            }

			Button button = Config.UI.getButton();
			button.setHint(item.getText());
			button.setIcon(item.getIcon());
			button.addObserver(item.getAction());
			bar.addComponent(button);
		}

		return bar;
	}
}