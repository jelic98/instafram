package org.ljelic.instafram.model.parameters;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;

public class NameParameter extends Parameter {

    public NameParameter() {
        super(Res.STRINGS.PARAMETER_NAME, ValueType.TEXT);

        setContent(Res.STRINGS.PARAMETER_NAME);
    }
}