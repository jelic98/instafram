package org.ljelic.instafram.model.parameters;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;

public class AuthorParameter extends Parameter {

    public AuthorParameter() {
        super(Res.STRINGS.PARAMETER_AUTHOR, ValueType.TEXT);

        setContent(Res.STRINGS.PARAMETER_AUTHOR);
    }
}