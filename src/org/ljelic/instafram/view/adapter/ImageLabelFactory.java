package org.ljelic.instafram.view.adapter;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.util.log.Log;
import org.ljelic.instafram.view.component.ImageLabel;
import java.io.IOException;

public class ImageLabelFactory {

    public ImageLabel getImageLabel(String path) {
        return getImageLabel(path, true);
    }

    private ImageLabel getImageLabel(String path, boolean firstCall) {
        ImageLabel image;

        try {
            image = Config.UI.getImageLabel(path);
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