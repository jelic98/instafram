package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.view.component.ImageLabel;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SwingImageLabel extends JPanel implements ImageLabel {

    private Image image;

    public SwingImageLabel(byte[] bytes) throws IOException {
        if(bytes == null || bytes.length == 0) {
            throw new IOException("Image does not exist");
        }

        image = new ImageIcon(bytes).getImage();

        setSize(image.getWidth(null), image.getHeight(null));
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}