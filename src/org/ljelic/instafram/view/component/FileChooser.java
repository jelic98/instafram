package org.ljelic.instafram.view.component;

import java.io.File;

public interface FileChooser {

    enum PathType {
        FILES_ONLY,
        DIRECTORIES_ONLY,
        FILES_AND_DIRECTORIES
    }

    FileChooser setStartPath(String startPath);
    FileChooser setPathType(PathType pathType);
    FileChooser setExtensions(String extensionDescription, String[] extensions);
    File getSingle();
    File[] getMultiple();
}