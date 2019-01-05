package org.ljelic.instafram.model.parameters;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;

public class ShortcutParameter extends Parameter {

    public ShortcutParameter() {
        super(Res.STRINGS.PARAMETER_SHORTCUT, ValueType.BOOLEAN);

        setContent(Res.STRINGS.PARAMETER_SHORTCUT);
        setEditable(true);
    }
}