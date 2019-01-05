package org.ljelic.instafram.model;

import org.ljelic.instafram.core.Res;

public class Root extends AbstractModel {

    public Root() {
        super(Res.STRINGS.INFO_ADD_NODE, null);
    }

    @Override
    public AbstractModel getClone() {
        return null;
    }

    @Override
    public AbstractModel getChild(String name) {
        return new Company(name);
    }

    @Override
    public String getChildType() {
        return Company.class.getSimpleName();
    }
}