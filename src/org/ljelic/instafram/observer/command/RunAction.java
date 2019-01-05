package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.*;
import org.ljelic.instafram.model.*;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.frame.InstallationFrame;
import org.ljelic.instafram.view.component.Node;
import java.util.LinkedList;
import java.util.List;

public class RunAction extends Command {

    private final boolean build;
    private String path;
    private InstallationFrame frame;

    public RunAction(String path) {
        this(false);

        this.path = path;
    }

    public RunAction(boolean build) {
        this.build = build;

        setStatus(Res.STRINGS.STATUS_RUN);
    }

    @Override
    void execute() {
        Product product = null;

        if(path == null) {
            try {
                Node selectedNode = Transfer.instance().tree.getSelectedNode();

                if(selectedNode != null) {
                    product = (Product) selectedNode.getDelegateModel();
                }
            }catch(ClassCastException e) {
                DialogAdapter.error(Res.STRINGS.ERROR_PRODUCT_NOT_SELECTED);
                return;
            }
        }else {
            Root root = new Root();

            try {
                Config.STREAM.read(Config.STREAMABLE.from(root), path);
            }catch(Exception e) {
                DialogAdapter.error(Res.STRINGS.ERROR_CANNOT_READ);
            }

            if(root.hasChildren()) {
                try {
                    product = (Product) root.getChildren().iterator().next().getDelegateModel();
                }catch(ClassCastException e) {
                    DialogAdapter.error(Res.STRINGS.ERROR_PRODUCT_NOT_SELECTED);
                    return;
                }
            }
        }

        if(product == null) {
            DialogAdapter.error(Res.STRINGS.ERROR_PRODUCT_NOT_SELECTED);
            return;
        }

        List<Parameter> parameters = getParameters(product, new LinkedList<>());

        if(parameters.isEmpty()) {
            DialogAdapter.error(Res.STRINGS.ERROR_NO_PARAMETERS);
            return;
        }

        run(product, parameters);
    }

    private void run(Product product, List<Parameter> parameters) {
        if(frame != null) {
            frame.close();
        }

        if(build) {
            try {
                Config.STREAM.write(Config.STREAMABLE.from(product), Config.MODEL.get(Parameters.BUILD_PATH));
            }catch(Exception e) {
                DialogAdapter.error(Res.STRINGS.ERROR_CANNOT_BUILD);
            }
        }

        frame = new InstallationFrame(product.getName(), parameters, path != null);
        frame.open();
    }

    private List<Parameter> getParameters(AbstractModel model, List<Parameter> parameters) {
        if(model != null) {
            for(Node child : model.getChildren()) {
                Node delegateModel = child.getDelegateModel();

                if(delegateModel instanceof AbstractModel) {
                    if(delegateModel instanceof Parameter) {
                        parameters.add((Parameter) delegateModel);
                        continue;
                    }

                    getParameters((AbstractModel) delegateModel, parameters);
                }
            }
        }

        return parameters;
    }
}