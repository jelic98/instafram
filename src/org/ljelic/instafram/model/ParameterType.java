package org.ljelic.instafram.model;

import org.ljelic.instafram.model.parameters.*;

public enum ParameterType {

    CUSTOM(CustomParameter.class),
    AUTHOR(AuthorParameter.class),
    AUTORUN(AutorunParameter.class),
    DESTINATION(DestinationParameter.class),
    LOGO(LogoParameter.class),
    LOOKFEEL(LookFeelParameter.class),
    NAME(NameParameter.class),
    SHORTCUT(ShortcutParameter.class),
    SOURCE(SourceParameter.class),
    TERMS(TermsParameter.class);

    private Class<? extends Parameter> blueprint;

    ParameterType(Class<? extends Parameter> blueprint) {
        this.blueprint = blueprint;
    }

    public Parameter getParameter() throws Exception {
        return blueprint.getConstructor().newInstance();
    }
}