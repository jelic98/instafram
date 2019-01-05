package org.ljelic.instafram.model.parameters;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;

public class SourceParameter extends Parameter {

    public SourceParameter() {
        super(Res.STRINGS.PARAMETER_SOURCE, ValueType.FILE);

        setContent(Res.STRINGS.PARAMETER_SOURCE);
        setEditable(true);
        setVisible(false);
    }
}