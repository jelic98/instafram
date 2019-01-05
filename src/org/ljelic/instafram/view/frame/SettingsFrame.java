package org.ljelic.instafram.view.frame;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.observer.command.ApplySettingsAction;
import org.ljelic.instafram.observer.command.DiscardSettingsAction;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.view.adapter.action.ActionItem;
import org.ljelic.instafram.view.adapter.action.ButtonFactory;
import org.ljelic.instafram.view.component.Label;
import org.ljelic.instafram.view.component.Panel;
import org.ljelic.instafram.view.component.TextField;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SettingsFrame extends FrameTemplate {

    public SettingsFrame() {
        super(Res.STRINGS.FRAME_SETTINGS);
    }

	@Override
	protected void configure() {
        setOrientation(Orientation.VERTICAL);
        setSizeRatio(0.4f);

        frame.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange(ChangeType type) {
                if(type == ChangeType.CLOSE) {
                    close();
                }
            }
        });
	}
	
	@Override
	protected void populate() {
	    Set<String> keys = Config.MODEL.getKeys();
        Map<String, String> draft = new HashMap<>(keys.size());

        Panel layout = Config.UI.getPanel();
        layout.setPadding(20, 20, 20, 20);
        layout.setLayout(Panel.Layout.NORMAL);

		for(String key : keys) {
            Panel row = Config.UI.getPanel();
            row.setLayout(Panel.Layout.SHRINK);

		    String value = Config.MODEL.get(key);

		    draft.put(key, value);

            Label name = Config.UI.getLabel();
            name.setText(key.replace('_', ' '));
		    row.addComponent(name);

            TextField input = Config.UI.getTextField();
            input.setText(value);
            input.setColumns(10);
            input.addObserver(new ChangeObserverAdapter() {
                @Override
                public void onChange() {
                    draft.put(key, input.getText());
                }
            });
            row.addComponent(input);

			layout.addComponent(row);
		}

		ButtonFactory factory = new ButtonFactory();

        Panel row = Config.UI.getPanel();
        row.setLayout(Panel.Layout.SHRINK);
        row.addComponent(factory.getButton(new ActionItem(Res.STRINGS.OPTION_CANCEL, new DiscardSettingsAction(this))));
        row.addComponent(factory.getButton(new ActionItem(Res.STRINGS.OPTION_OK, new ApplySettingsAction(keys, draft, this))));
        layout.addComponent(row);

        frame.addComponent(layout);
        frame.pack();
	}
}