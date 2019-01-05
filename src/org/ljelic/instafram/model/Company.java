package org.ljelic.instafram.model;

import org.ljelic.instafram.core.Res;

public class Company extends AbstractModel {

    public Company(String name) {
        super(name, Res.ICONS.COMPANY);
    }

    private Company(AbstractModel original) {
        super(original);
    }

    @Override
    public AbstractModel getClone() {
        return new Company(this);
    }

    @Override
    public AbstractModel getChild(String name) {
        Product product = new Product(name);
        AbstractModel module = product.getChild(product.getChildType() + "_1");
        product.addChild(module);

        try {
            module.addChild(ParameterType.SOURCE.getParameter());
            module.addChild(ParameterType.DESTINATION.getParameter());
        }catch(Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public String getChildType() {
        return Product.class.getSimpleName();
    }
}