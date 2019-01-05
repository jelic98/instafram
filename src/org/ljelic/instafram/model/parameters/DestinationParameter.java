package org.ljelic.instafram.model.parameters;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;

public class DestinationParameter extends Parameter {

    public DestinationParameter() {
        super(Res.STRINGS.PARAMETER_DESTINATION, ValueType.FILE);

        setContent(Res.STRINGS.PARAMETER_DESTINATION);
        setEditable(true);
    }
}