package org.ljelic.instafram.view.frame;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.Installation;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.view.adapter.ParameterViewFactory;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.component.*;

import java.util.LinkedList;
import java.util.List;

public class InstallationFrame extends FrameTemplate {

    private List<Parameter> parameters;
    private Installation installation;
    private ParameterViewFactory viewFactory;
    private Panel page;
    private int pageIndex;
    private boolean exitOnClose;

    public InstallationFrame(String title, List<Parameter> parameters, boolean exitOnClose) {
        super(title, true);

        installation = new Installation(title, parameters);
        viewFactory = new ParameterViewFactory();
        page = Config.UI.getPanel();
        page.setLayout(Panel.Layout.EXPAND);
        pageIndex = 0;

        this.parameters = installation.getVisibleParameters();
        this.exitOnClose = exitOnClose;
    }

	@Override
	protected void configure() {
        setOrientation(Orientation.VERTICAL);
        setSizeRatio(0.4f);

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
        if(this.parameters.isEmpty()) {
            close();
        }

        updatePage();
        frame.addComponent(page);
	}

    private void updatePage() {
        page.clear();
        page.addComponent(getParameter(), Label.Position.CENTER);
        page.addComponent(getNavigation(), Label.Position.BOTTOM);

        frame.refresh();
    }

    private Panel getParameter() {
        Label lblContent = Config.UI.getLabel();
        lblContent.setText(parameters.get(pageIndex).getContent());

        Panel parameter = Config.UI.getPanel();
        parameter.setLayout(Panel.Layout.NORMAL);
        parameter.addComponent(lblContent, Label.Position.TOP);
        parameter.addComponent(viewFactory.getView(parameters.get(pageIndex)), Label.Position.CENTER);

        return parameter;
    }

	private Panel getNavigation() {
        Panel navigation = Config.UI.getPanel();

        if(pageIndex > 0) {
            Button btnBack = Config.UI.getButton();
            btnBack.setText(Res.STRINGS.OPTION_BACK);
            btnBack.addObserver(new ChangeObserverAdapter() {
                @Override
                public void onChange(ChangeType type) {
                    if(type == ChangeType.ACTIVATE) {
                        pageIndex--;
                        updatePage();
                    }
                }
            });
            navigation.addComponent(btnBack);
        }

        if(pageIndex < parameters.size() - 1) {
            Button btnNext = Config.UI.getButton();
            btnNext.setText(Res.STRINGS.OPTION_NEXT);
            btnNext.addObserver(new ChangeObserverAdapter() {
                @Override
                public void onChange(ChangeType type) {
                    if(type == ChangeType.ACTIVATE) {
                        pageIndex++;
                        updatePage();
                    }
                }
            });
            navigation.addComponent(btnNext);
        }else {
            Button btnFinish = Config.UI.getButton();
            btnFinish.setText(Res.STRINGS.OPTION_FINISH);
            btnFinish.addObserver(new ChangeObserverAdapter() {
                @Override
                public void onChange(ChangeType type) {
                    installation.start();
                    close();
                }
            });
            navigation.addComponent(btnFinish);
        }

        return navigation;
    }

    @Override
    public void close() {
        Config.UI.setLookFeel(null);

        super.close();

        if(exitOnClose) {
            System.exit(0);
        }
    }
}