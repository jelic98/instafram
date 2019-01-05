package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Paths;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.AbstractModel;
import org.ljelic.instafram.view.component.FileChooser;

import java.io.File;

public class SaveAsAction extends Command {

    private final AbstractModel rootNode;

    public SaveAsAction(AbstractModel rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    void execute() {
        File selected = Config.UI.getFileChooser()
                .setStartPath(Paths.getHomePath())
                .setPathType(FileChooser.PathType.FILES_AND_DIRECTORIES)
                .setExtensions(Res.STRINGS.INFO_APP_FILES, Paths.getExtensions())
                .getSingle();

        if(selected != null) {
            CommandQueue.push(new SaveAction(rootNode, selected.getPath()));
        }
    }
}