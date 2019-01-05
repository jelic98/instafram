package org.ljelic.instafram.model.parameters;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;

public class TermsParameter extends Parameter {

    public TermsParameter() {
        super(Res.STRINGS.PARAMETER_TERMS, ValueType.TEXT_LONG);

        setContent(Res.STRINGS.PARAMETER_TERMS);
    }
}