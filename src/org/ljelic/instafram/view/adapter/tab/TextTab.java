package org.ljelic.instafram.view.adapter.tab;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.model.AbstractModel;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.TextArea;

public class TextTab {

    private TextArea textArea;

    public TextTab(AbstractModel model) {
        textArea = Config.UI.getTextArea();

        textArea.setText(model.getContent());
        textArea.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange() {
                model.setContent(textArea.getText());
            }
        });
    }

    public Component getView() {
        return textArea;
    }
}