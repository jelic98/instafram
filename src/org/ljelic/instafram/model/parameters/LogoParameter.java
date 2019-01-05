package org.ljelic.instafram.model.parameters;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;

public class LogoParameter extends Parameter {

    public LogoParameter() {
        super(Res.STRINGS.PARAMETER_LOGO, ValueType.IMAGE);

        setContent(Res.STRINGS.PARAMETER_LOGO);
    }
}