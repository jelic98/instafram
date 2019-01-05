package org.ljelic.instafram.model.parameters;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;

public class LookFeelParameter extends Parameter {

    public LookFeelParameter() {
        super(Res.STRINGS.PARAMETER_LOOKFEEL, ValueType.SELECTION);

        String[] lookFeels = Config.UI.getLookFeels();
        StringBuilder options = new StringBuilder();

        for(int i = 0; i < lookFeels.length; i++) {
            if(i > 0) {
                options.append(",");
            }

            options.append(lookFeels[i]);
        }

        setOptions(options.toString());
        setContent(Res.STRINGS.PARAMETER_LOOKFEEL);
        setEditable(true);
        setVisible(false);
    }
}