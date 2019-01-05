package org.ljelic.instafram.view.frame;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.view.component.Frame;

public abstract class FrameTemplate {

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    protected Frame frame;
    protected String title;
    private Orientation orientation = Orientation.HORIZONTAL;
    private int width, height;
    private boolean lazy;

    FrameTemplate(String title) {
        this(title, false);
    }

	FrameTemplate(String title, boolean lazy) {
        this.title = title;
        this.lazy = lazy;

        if(!lazy) {
            setup();
        }
	}

	private void setup() {
        frame = Config.UI.getFrame();
        frame.setTitle(title);
        frame.setIcon(Res.ICONS.APP);
        frame.setResizable(false);

        width = frame.getInitialWidth();
        height = frame.getInitialHeight();

        configure();
        populate();
    }
	
	protected abstract void configure();
	protected abstract void populate();

    final void setOrientation(Orientation orientation) {
        this.orientation = orientation;

        checkOrientation();
    }

	final void setSizeRatio(float ratio) {
        width *= ratio;
        height *= ratio;

        checkOrientation();
	}

    private void checkOrientation() {
        if(shouldResize()) {
            width = width + height;
            height = width - height;
            width = width - height;
        }

        frame.setSize(width, height);
    }

    private boolean shouldResize() {
        boolean horizontal = orientation == Orientation.HORIZONTAL && width < height;
        boolean vertical = orientation == Orientation.VERTICAL && width > height;

        return horizontal || vertical;
    }

    public void open() {
        if(lazy) {
            setup();
        }

        frame.setVisible(true);
        frame.setFocused(true);
    }

    public void close() {
        frame.setVisible(false);
        frame.setFocused(false);
        frame.close();
    }
}