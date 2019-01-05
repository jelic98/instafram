package org.ljelic.instafram.view.adapter;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Paths;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.view.component.*;
import java.io.File;

public class ParameterViewFactory {

    private static final String SPLITTER = ",";

    public Component getView(Parameter parameter) {
        Panel panel = Config.UI.getPanel();

        switch(parameter.getValueType()) {
            case TEXT:
                TextField textField = Config.UI.getTextField();
                textField.setColumns(15);
                textField.setText(parameter.getValue());
                textField.setEditable(parameter.isEditable());
                textField.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        parameter.setValue(textField.getText(), false);
                    }
                });

                parameter.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        textField.setText(parameter.getValue());
                    }
                });

                panel.addComponent(textField);
                break;
            case TEXT_LONG:
                TextArea textArea = Config.UI.getTextArea();
                textArea.setRows(5);
                textArea.setRows(15);
                textArea.setText(parameter.getValue());
                textArea.setEditable(parameter.isEditable());
                textArea.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        parameter.setValue(textArea.getText(), false);
                    }
                });

                parameter.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        textArea.setText(parameter.getValue());
                    }
                });

                ScrollPanel scrollPanel = Config.UI.getScrollPanel();
                scrollPanel.addComponent(textArea);

                panel.addComponent(scrollPanel);
                break;
            case BOOLEAN:
                CheckBox checkBox = Config.UI.getCheckBox();
                checkBox.setChecked(Boolean.valueOf(parameter.getValue()));
                checkBox.setEnabled(parameter.isEditable());
                checkBox.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        parameter.setValue(String.valueOf(checkBox.isChecked()), false);
                    }
                });

                parameter.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        checkBox.setChecked(Boolean.valueOf(parameter.getValue()));
                    }
                });

                panel.addComponent(checkBox);
                break;
            case SELECTION:
                ComboBox<String> comboBox = new Config().UI.getComboBox();
                comboBox.setEnabled(parameter.isEditable());
                comboBox.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        Object selected = comboBox.getSelectedItem();

                        if(selected != null) {
                            parameter.setValue(selected.toString(), false);
                        }
                    }
                });

                parameter.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        populateComboBox(comboBox, parameter.getOptions());
                    }
                });

                populateComboBox(comboBox, parameter.getOptions());

                panel.addComponent(comboBox);
                break;
            case FILE:
                TextField tfPath = Config.UI.getTextField();
                tfPath.setColumns(15);
                tfPath.setEditable(parameter.isEditable());
                tfPath.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        parameter.setValue(tfPath.getText(), false);
                    }
                });

                parameter.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        tfPath.setText(parameter.getValue());
                    }
                });

                Button btnBrowse = Config.UI.getButton();
                btnBrowse.setEnabled(parameter.isEditable());
                btnBrowse.setText(Res.STRINGS.OPTION_BROWSE);
                btnBrowse.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        File selected = Config.UI.getFileChooser()
                                .setStartPath(Paths.getHomePath())
                                .setPathType(FileChooser.PathType.DIRECTORIES_ONLY)
                                .getSingle();

                        if(selected != null) {
                            tfPath.setText(selected.getPath());
                        }
                    }
                });

                panel.addComponent(tfPath);
                panel.addComponent(btnBrowse);
                break;
            case IMAGE:
                parameter.addObserver(new ChangeObserverAdapter() {
                    @Override
                    public void onChange() {
                        panel.clear();
                        panel.addComponent(new ImageLabelFactory().getImageLabel(parameter.getPath()));
                    }
                });

                panel.setLayout(Panel.Layout.EXPAND);
                panel.addComponent(new ImageLabelFactory().getImageLabel(parameter.getPath()));
                break;
        }

        return panel;
    }

    private void populateComboBox(ComboBox comboBox, String options) {
        comboBox.clear();

        if(options == null) {
            return;
        }

        String[] items = options.split(SPLITTER);

        for(String item : items) {
            comboBox.addItem(item);
        }
    }
}