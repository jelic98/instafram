package org.ljelic.instafram.model.parameters;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;

public class AutorunParameter extends Parameter {

    public AutorunParameter() {
        super(Res.STRINGS.PARAMETER_AUTORUN, ValueType.BOOLEAN);

        setContent(Res.STRINGS.PARAMETER_AUTORUN);
        setEditable(true);
    }
}