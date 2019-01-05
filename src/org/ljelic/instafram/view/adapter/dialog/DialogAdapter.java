package org.ljelic.instafram.view.adapter.dialog;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.util.Bundle;
import org.ljelic.instafram.util.log.Log;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.view.component.*;

public class DialogAdapter {

    public static final Dialog dialog = Config.UI.getDialog();

    public static void error(String message) {
        dialog.error(message, Res.STRINGS.DIALOG_ERROR, Res.ICONS.ERROR);
        Log.e(message);
    }

    public static void info(String message) {
        dialog.info(message, Res.STRINGS.DIALOG_INFO, Res.ICONS.INFO);
        Log.i(message);
    }

    public static void question(Question question) {
        question.onAction(dialog.question(question.getMessage(),
                Res.STRINGS.DIALOG_QUESTION,
                Res.ICONS.QUESTION,
                question.getOptions()));
    }

    public static String input(Input input) {
        return dialog.input(input.getMessage(), input.getTitle());
    }

    public static void selection(Question question, String[] values, Bundle result) {
        ComboBox<String> combo = Config.UI.getComboBox();
        combo.setItems(values);
        combo.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange(ChangeType type) {
                if(type == ChangeType.SELECTION) {
                    result.setValue(combo.getSelectedItem());
                }
            }
        });

        Label lblMessage = Config.UI.getLabel();
        lblMessage.setPosition(Label.Position.LEFT);
        lblMessage.setText(question.getMessage());

        Panel panel = Config.UI.getPanel();
        panel.setLayout(Panel.Layout.EXPAND);
        panel.addComponent(lblMessage, Label.Position.TOP);
        panel.addComponent(combo, Label.Position.CENTER);

        question.onAction(dialog.selection(question.getMessage(),
                Res.STRINGS.DIALOG_QUESTION,
                Res.ICONS.QUESTION,
                question.getOptions(),
                panel));
    }
}