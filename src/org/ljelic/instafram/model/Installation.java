package org.ljelic.instafram.model;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.parameters.*;
import org.ljelic.instafram.util.Machine;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;

public class Installation {

    private List<Parameter> parameters;
    private String title, source, destination;
    private boolean shortcut, autorun;

    public Installation(String title, List<Parameter> parameters) {
        this.title = title;

        this.parameters = new LinkedList<>();

        for(Parameter parameter : parameters) {
            if(parameter.isVisible()) {
                this.parameters.add(parameter);
            }

            if(parameter instanceof SourceParameter) {
                source = parameter.getPath();
            }

            if(parameter instanceof LookFeelParameter) {
                Config.UI.setLookFeel(parameter.getValue());
            }
        }
    }

    public void start() {
        for(Parameter parameter : parameters) {
            if(parameter instanceof DestinationParameter) {
                destination = parameter.getValue();
            }else if(parameter instanceof ShortcutParameter) {
                shortcut = Boolean.valueOf(parameter.getValue());
            }else if(parameter instanceof AutorunParameter) {
                autorun = Boolean.valueOf(parameter.getValue());
            }
        }

        if(destination == null) {
            destination = Machine.userHome()
                    + Machine.fileSeparator()
                    + title;
        }

        destination += Machine.fileSeparator()
                + Paths.get(source).getFileName();

        copy(source, destination);

        if(shortcut) {
            copy(source, Machine.userHome()
                    + Machine.fileSeparator()
                    + "Desktop"
                    + Machine.fileSeparator()
                    + Paths.get(source).getFileName());
        }

        if(autorun) {
            try {
                Desktop.getDesktop().open(new File(destination));
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void copy(String source, String destination) {
        new File(destination).mkdirs();

        try {
            Files.copy(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e) {
            DialogAdapter.error(Res.STRINGS.ERROR_CANNOT_COPY);
        }
    }

    public List<Parameter> getVisibleParameters() {
        return parameters;
    }
}