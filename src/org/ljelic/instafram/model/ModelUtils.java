package org.ljelic.instafram.model;

import org.ljelic.instafram.view.component.Node;

public class ModelUtils {

    public String getContent(AbstractModel model) {
        if(model == null) {
            return "/";
        }

        return model.getContent();
    }

    public String getName(Node model) {
        if(model == null) {
            return "/";
        }

        return model.getName();
    }

    public String getParentName(Node model) {
        return getName(model.getAncestor());
    }

    public int getChildCount(Node model) {
        if(model == null) {
            return 0;
        }

        return model.getChildren().size();
    }

    public int getLeafCount(Node model) {
        if(model == null) {
            return 0;
        }

        if(!model.hasChildren()) {
            return 1;
        }

        int count = 0;

        for(Node child : model.getChildren()) {
            count += getLeafCount(child);
        }

        return count;
    }
}