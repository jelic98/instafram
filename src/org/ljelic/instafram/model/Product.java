package org.ljelic.instafram.model;

import org.ljelic.instafram.core.Res;

public class Product extends AbstractModel {

    public Product(String name) {
        super(name, Res.ICONS.PRODUCT);
    }

    private Product(AbstractModel original) {
        super(original);
    }

    @Override
    public AbstractModel getClone() {
        return new Product(this);
    }

    @Override
    public AbstractModel getChild(String name) {
        return new Module(name);
    }

    @Override
    public String getChildType() {
        return Module.class.getSimpleName();
    }
}