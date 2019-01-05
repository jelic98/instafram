package org.ljelic.instafram.model;

import org.ljelic.instafram.core.Res;

public class Module extends AbstractModel {

    public Module(String name) {
        super(name, Res.ICONS.MODULE);
    }

    private Module(AbstractModel original) {
        super(original);
    }

    @Override
    public AbstractModel getClone() {
        return new Module(this);
    }

    @Override
    public AbstractModel getChild(String name) {
        return new Parameter(name, getChildCount());
    }

    @Override
    public String getChildType() {
        return Parameter.class.getSimpleName();
    }
}