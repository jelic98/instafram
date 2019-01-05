package org.ljelic.instafram.view.frame;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.adapter.ImageLabelFactory;
import org.ljelic.instafram.view.component.Label;
import org.ljelic.instafram.view.component.Panel;

public class AboutFrame extends FrameTemplate {

    public AboutFrame() {
        super(Res.STRINGS.FRAME_ABOUT);
    }

	@Override
	protected void configure() {
        setOrientation(Orientation.VERTICAL);
        setSizeRatio(0.3f);

        frame.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange(ChangeType type) {
                if(type == ChangeType.CLOSE) {
                    close();
                }
            }
        });
	}
	
	@Override
	protected void populate() {
        Panel layout = Config.UI.getPanel();
        layout.setPadding(20, 20, 20, 20);
        layout.setLayout(Panel.Layout.NORMAL);

        layout.addComponent(new ImageLabelFactory().getImageLabel(Res.ICONS.IMAGE_AUTHOR));

        Label lblInfo = Config.UI.getLabel();
        lblInfo.setText(getAboutInfo());
        layout.addComponent(lblInfo);

        frame.addComponent(layout);
	}

	private String getAboutInfo() {
	    StringBuilder builder = new StringBuilder();

	    builder.append("<html>");

        builder.append("<p>");
        builder.append("<b>");
        builder.append(Res.STRINGS.ABOUT_AUTHOR);
        builder.append("</b>");
        builder.append(Config.AUTHOR);
        builder.append("</p>");
        builder.append("<br/>");

        builder.append("<p>");
        builder.append("<b>");
        builder.append(Res.STRINGS.ABOUT_VERSION);
        builder.append("</b>");
        builder.append(Config.VERSION);
        builder.append("</p>");
        builder.append("<br/>");

        builder.append("<p>");
        builder.append(Res.STRINGS.ABOUT_DESCRIPTION);
        builder.append("</p>");

        builder.append("</html>");

	    return builder.toString();
    }
}