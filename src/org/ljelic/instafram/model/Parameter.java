package org.ljelic.instafram.model;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.component.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Parameter extends AbstractModel {

    private int order;
    private boolean editable, visible;
    private String value, options;
    private byte[] file;
    private ValueType valueType;

    public Parameter(String name, ValueType valueType) {
        super(name, Res.ICONS.PARAMETER);

        this.valueType = valueType;

        editable = false;
        visible = true;
    }

    public Parameter(String name, int order) {
        super(name, Res.ICONS.PARAMETER);

        this.order = order;

        editable = false;
        visible = true;
    }

    private Parameter(AbstractModel original, int order) {
        super(original);

        this.order = order;

        editable = false;
        visible = true;
    }

    @Override
    public AbstractModel getClone() {
        return new Parameter(this, order);
    }

    @Override
    public AbstractModel getChild(String name) {
        return null;
    }

    @Override
    public String getChildType() {
        return null;
    }

    @Override
    public String getType() {
        return Parameter.class.getSimpleName();
    }

    @Override
    public int compareTo(Node node) {
        if(node != null && node instanceof Parameter) {
            return order - ((Parameter) node).order;
        }

        return super.compareTo(node);
    }

    @Override
    public List<String> getProperties() {
        List<String> properties = super.getProperties();
        properties.add(getValue() == null ? Config.DEFAULT_PARAMETER_VALUE : getValue());
        properties.add(getValueType().toString());

        return properties;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        setValue(value, false);
    }

    public void setValue(String value, boolean notify) {
        this.value = value;

        if(valueType == ValueType.FILE || valueType == ValueType.IMAGE) {
            try {
                File file = new File(value);

                if(file.exists() && file.isFile()) {
                    this.file = Files.readAllBytes(Paths.get(value));
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        if(notify) {
            notifyObservers(ChangeType.UPDATE, this);
        }
    }

    public String getPath() {
        Path path = Paths.get(value);

        try {
            Files.write(path, file);

            return path.toString();
        }catch(Exception e) {
            return null;
        }
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        setOptions(options, false);
    }

    public void setOptions(String options, boolean notify) {
        this.options = options;

        if(notify) {
            notifyObservers(ChangeType.UPDATE, this);
        }
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public boolean isCustom() {
        return false;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}