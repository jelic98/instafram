package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.view.component.FileChooser;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class SwingFileChooser implements FileChooser {

    private File startPath;
    private PathType pathType;
    private String extensionDescription;
    private String[] extensions;

    public SwingFileChooser() {
        startPath = FileSystemView.getFileSystemView().getHomeDirectory();
        pathType = PathType.FILES_AND_DIRECTORIES;
    }

    public FileChooser setStartPath(String startPath) {
        this.startPath = new File(startPath);

        return this;
    }

    public FileChooser setPathType(PathType pathType) {
        this.pathType = pathType;

        return this;
    }

    public FileChooser setExtensions(String extensionDescription, String[] extensions) {
        this.extensionDescription = extensionDescription;
        this.extensions = extensions;

        for(int i = 0; i < extensions.length; i++) {
            extensions[i] = extensions[i].replace(".", "");
        }

        return this;
    }

    public File getSingle() {
        File[] files = open(false);

        if(files != null) {
            return files[0];
        }

        return null;
    }

    public File[] getMultiple() {
        return open(true);
    }

    private File[] open(boolean multiSelection) {
        JFileChooser chooser = new JFileChooser(startPath);
        chooser.setDialogTitle(Res.STRINGS.INFO_CHOOSE_PATH);
        chooser.setMultiSelectionEnabled(multiSelection);
        chooser.setFileSelectionMode(getPathType());

        if(extensions != null && extensions.length > 0) {
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(extensionDescription, extensions);
            chooser.addChoosableFileFilter(filter);
        }

        File[] selection = null;

        int result = chooser.showDialog(null, Res.STRINGS.OPTION_OK);

        if(result == JFileChooser.APPROVE_OPTION) {
            selection = chooser.getSelectedFiles();

            if(!multiSelection) {
                selection = new File[] {chooser.getSelectedFile()};
            }
        }

        return selection;
    }

    private int getPathType() {
        switch(pathType) {
            case FILES_ONLY:
                return JFileChooser.FILES_ONLY;
            case DIRECTORIES_ONLY:
                return JFileChooser.DIRECTORIES_ONLY;
            default:
                return JFileChooser.FILES_AND_DIRECTORIES;
        }
    }
}