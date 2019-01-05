package org.ljelic.instafram.view.adapter.action;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.view.component.Button;

public class ButtonFactory {

	public Button getButton(ActionItem item) {
		Button button = Config.UI.getButton();
		button.setText(item.getText());
		button.setIcon(item.getIcon());
		button.addObserver(item.getAction());

		return button;
	}
}