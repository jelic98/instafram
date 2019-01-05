package org.ljelic.instafram.view.adapter.tab;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.view.adapter.ParameterViewFactory;
import org.ljelic.instafram.view.component.*;

public class ParameterTab {

    private Panel panel;

    public ParameterTab(Parameter parameter) {
        panel = Config.UI.getPanel();

        if(parameter.isCustom()) {
            ComboBox<ValueType> cbType = Config.UI.getComboBox();
            cbType.addObserver(new ChangeObserverAdapter() {
                @Override
                public void onChange() {
                    parameter.setValueType((ValueType) cbType.getSelectedItem());
                }
            });

            for(ValueType type : ValueType.values()) {
                cbType.addItem(type);
            }

            panel.addComponent(cbType);

            Label lblContent = Config.UI.getLabel();
            lblContent.setText(Res.STRINGS.INPUT_CONTENT);
            panel.addComponent(lblContent);

            TextField tfContent = Config.UI.getTextField();
            tfContent.setText(parameter.getContent());
            tfContent.setColumns(20);
            tfContent.addObserver(new ChangeObserverAdapter() {
                @Override
                public void onChange() {
                    parameter.setContent(tfContent.getText());
                }
            });
            panel.addComponent(tfContent);
        }

        Label lblValue = Config.UI.getLabel();
        lblValue.setText(Res.STRINGS.INPUT_VALUE);
        panel.addComponent(lblValue);

        TextField tfValue = Config.UI.getTextField();
        tfValue.setText(parameter.getValue());
        tfValue.setColumns(20);
        tfValue.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange() {
                parameter.setValue(tfValue.getText());
            }
        });
        panel.addComponent(tfValue);

        if(parameter.getValueType() == ValueType.SELECTION) {
            Label lblOptions = Config.UI.getLabel();
            lblOptions.setText(Res.STRINGS.INPUT_OPTIONS);
            panel.addComponent(lblOptions);

            TextField tfOptions = Config.UI.getTextField();
            tfOptions.setText(parameter.getOptions());
            tfOptions.setColumns(20);
            tfOptions.addObserver(new ChangeObserverAdapter() {
                @Override
                public void onChange() {
                    parameter.setOptions(tfOptions.getText());
                }
            });
            panel.addComponent(tfOptions);

            panel.addComponent(new ParameterViewFactory().getView(parameter));
        }
    }

    public Component getView() {
        return panel;
    }
}