package org.ljelic.instafram.view.adapter;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.util.log.Log;
import org.ljelic.instafram.view.component.ImageLabel;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageLabelFactory {

    public ImageLabel getImageLabel(String path) {
        byte[] bytes;

        try {
            InputStream is = new FileInputStream(path);
            bytes = new byte[is.available()];
            new DataInputStream(is).readFully(bytes);
        }catch(IOException e) {
            bytes = new byte[0];
        }

        return getImageLabel(bytes, true);
    }

    public ImageLabel getImageLabel(byte[] bytes) {
        return getImageLabel(bytes, true);
    }

    private ImageLabel getImageLabel(byte[] bytes, boolean firstCall) {
        ImageLabel image;

        try {
            image = Config.UI.getImageLabel(bytes);
        }catch(IOException e) {
            Log.e(e.getMessage());

            if(firstCall) {
                image = getImageLabel(Res.ICONS.IMAGE_DEFAULT, false);
            }else {
                Log.e(Res.STRINGS.ERROR_NO_DEFAULT_IMAGE);

                image = getImageLabel(null, false);
            }
        }

        return image;
    }
}