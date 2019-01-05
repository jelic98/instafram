package org.ljelic.instafram.util.io.stream.serial;

import org.ljelic.instafram.model.AbstractModel;
import org.ljelic.instafram.util.io.stream.Streamable;

public class SerialModel implements SerialStreamable<AbstractModel> {

    private AbstractModel model;

    @Override
    public void handle(Object obj) {
        if(obj instanceof AbstractModel) {
            this.model.addChild((AbstractModel) obj);
        }
    }

    @Override
    public Streamable<AbstractModel> from(AbstractModel streamable) {
        model = streamable;

        return this;
    }

    @Override
    public AbstractModel get() {
        return model;
    }
}