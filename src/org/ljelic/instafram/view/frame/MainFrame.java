package org.ljelic.instafram.view.frame;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.ljelic.instafram.observer.command.*;
import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Paths;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.core.Transfer;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.observer.ChangeObservable;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.model.*;
import org.ljelic.instafram.util.Machine;
import org.ljelic.instafram.util.ObservableSet;
import org.ljelic.instafram.view.adapter.action.*;
import org.ljelic.instafram.util.DelayRunner;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.adapter.tab.TabManager;
import org.ljelic.instafram.view.adapter.tab.TabItem;
import org.ljelic.instafram.view.component.*;

public class MainFrame extends FrameTemplate {

    private Root root;
    private ObservableSet<TabItem> tabs;

    public MainFrame() {
        super(Res.STRINGS.FRAME_MAIN);
    }

	@Override
	protected void configure() {
        setSizeRatio(0.75f);

        root = new Root();
        tabs = new ObservableSet<>();

        frame.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange(ChangeType type) {
                if(type == ChangeType.CLOSE) {
                    CommandQueue.push(new ExitAction(root));
                }
            }
        });
	}

	@Override
	protected void populate() {
        addMenuBar();
        addToolBar();
        addMainPane();
        addStatusBar();
        loadState();
	}

	private void addMenuBar() {
        frame.setMenuBar(new MenuBarFactory().getMenuBar(new CompositeActionItem[] {
                new CompositeActionItem(Res.STRINGS.MENU_FILE, 'F', null).children(new CompositeActionItem[] {
                        new CompositeActionItem(Res.STRINGS.MENU_NEW, Res.ICONS.NEW, 'N', new AddAction(root))
                                .separate(),
                        new CompositeActionItem(Res.STRINGS.MENU_OPEN, Res.ICONS.OPEN, 'O', new OpenAction(root))
                                .separate(),
                        new CompositeActionItem(Res.STRINGS.MENU_SAVE, Res.ICONS.SAVE, 'S', new SaveAction(root)),
                        new CompositeActionItem(Res.STRINGS.MENU_SAVE_AS, Res.ICONS.SAVE_AS, 'A', new SaveAsAction(root))
                                .separate(),
                        new CompositeActionItem(Res.STRINGS.MENU_RUN, Res.ICONS.RUN, 'R', new RunAction(false)),
                        new CompositeActionItem(Res.STRINGS.MENU_BUILD, Res.ICONS.BUILD, 'B', new RunAction(true))
                                .separate(),
                        new CompositeActionItem(Res.STRINGS.MENU_EXIT, Res.ICONS.EXIT, 'Q', new ExitAction(root))
                }),
                new CompositeActionItem(Res.STRINGS.MENU_EDIT, 'E', null).children(new CompositeActionItem[] {
                        new CompositeActionItem(Res.STRINGS.MENU_UNDO, Res.ICONS.UNDO, 'Z', new UndoAction()),
                        new CompositeActionItem(Res.STRINGS.MENU_REDO, Res.ICONS.REDO, 'Y', new RedoAction())
                                .separate(),
                        new CompositeActionItem(Res.STRINGS.MENU_CUT, Res.ICONS.CUT, 'X', new CutAction()),
                        new CompositeActionItem(Res.STRINGS.MENU_COPY, Res.ICONS.COPY, 'C', new CopyAction()),
                        new CompositeActionItem(Res.STRINGS.MENU_PASTE, Res.ICONS.PASTE, 'V', new PasteAction())
                }).separate(),
                new CompositeActionItem(Res.STRINGS.MENU_SETTINGS, 'P', new SettingsAction()),
                new CompositeActionItem(Res.STRINGS.MENU_ACCOUNT, 'A', null).children(new CompositeActionItem[] {
                        new CompositeActionItem(Res.STRINGS.MENU_LOGOUT, Res.ICONS.LOGOUT, 'L', new LogoutAction(this)),
                }),
                new CompositeActionItem(Res.STRINGS.MENU_HELP, 'H', null).children(new CompositeActionItem[] {
                        new CompositeActionItem(Res.STRINGS.MENU_OFFLINE, Res.ICONS.OFFLINE, 'F', new HelpOfflineAction()),
                        new CompositeActionItem(Res.STRINGS.MENU_ONLINE, Res.ICONS.ONLINE, 'I', new HelpOnlineAction())
                                .separate(),
                        new CompositeActionItem(Res.STRINGS.MENU_ABOUT, Res.ICONS.ABOUT, 'T', new AboutAction())
                })
        }));
    }

    private void addToolBar() {
        frame.addComponent(new ToolBarFactory().getToolBar(new ActionItem[] {
                new ActionItem(Res.STRINGS.MENU_NEW, Res.ICONS.NEW, new AddAction(root)),
                new ActionItem(Res.STRINGS.MENU_OPEN, Res.ICONS.OPEN, new OpenAction(root)),
                new ActionItem(Res.STRINGS.MENU_SAVE, Res.ICONS.SAVE, new SaveAction(root)),
                new ActionItem(Res.STRINGS.MENU_EXIT, Res.ICONS.EXIT, new ExitAction(root))
        }), Label.Position.TOP);
    }

    private void addMainPane() {
	    Tree tree = getTree();

        ScrollPanel treePanel = Config.UI.getScrollPanel();
        treePanel.addComponent(tree);

        SplitPanel splitMain = Config.UI.getSplitPanel();
        splitMain.setLeft(treePanel);
        splitMain.setRight(getWorkspacePanel(tree));
        splitMain.setResizeWeight(0.25);
        frame.addComponent(splitMain, Label.Position.CENTER);
    }

    private Tree getTree() {
        Tree tree = Config.UI.getTree();
        tree.setRoot(root);
        tree.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange(ChangeType type, Object bundle) {
                if(bundle instanceof Node) {
                    if(type == ChangeType.SELECTION) {
                        Transfer.instance().tree.addSelectedNode((Node) bundle);
                    }else if(type == ChangeType.DESELECTION) {
                        Transfer.instance().tree.removeSelectedNode((Node) bundle);
                    }
                }
            }
        });
        tree.setPopupMenu(new PopupMenuFactory().getPopupMenu(new CompositeActionItem[] {
                new CompositeActionItem(Res.STRINGS.MENU_LOAD, new LoadAction(root)),
                new CompositeActionItem(Res.STRINGS.MENU_SAVE, new SaveAction(root)).separate(),
                new CompositeActionItem(Res.STRINGS.MENU_ADD, new AddAction(root, false)),
                new CompositeActionItem(Res.STRINGS.MENU_REMOVE, new RemoveAction(tabs)).separate(),
                new CompositeActionItem(Res.STRINGS.MENU_DETAILS, new DetailsAction(tabs))
        }));

        return tree;
    }

    private Panel getWorkspacePanel(ChangeObservable observable) {
        Panel workspace = Config.UI.getPanel();
        workspace.setLayout(Panel.Layout.EXPAND);
        workspace.addComponent(new ToolBarFactory().getToolBar(new ActionItem[] {
                new ActionItem(Res.STRINGS.MENU_NEW, Res.ICONS.NEW, new AddAction(root)),
                new ActionItem(Res.STRINGS.MENU_OPEN, Res.ICONS.OPEN, new OpenAction(root)),
                new ActionItem(Res.STRINGS.MENU_SAVE, Res.ICONS.SAVE, new SaveAction(root))
        }), Label.Position.TOP);

        TabManager tabManager = new TabManager();
        tabManager.addObserver(new ChangeObserverAdapter<TabItem>() {
            @Override
            public void onChange(ChangeType type, TabItem bundle) {
                if(type == ChangeType.REMOVAL) {
                    CommandQueue.push(new CloseTabAction(bundle.getModel(), tabs));
                }
            }
        });

        tabs.addObserver(new ChangeObserverAdapter<TabItem>() {
            @Override
            public void onChange(ChangeType type, TabItem bundle) {
                if(type == ChangeType.ADDITION) {
                    tabManager.addTab(bundle);
                }else if(type == ChangeType.REMOVAL) {
                    tabManager.removeTab(bundle);
                }
            }
        });

        SplitPanel split = Config.UI.getSplitPanel();
        split.setTop(tabManager.getPanel());
        split.setBottom(getDetailsPanel(observable));
        split.setResizeWeight(0.75);

        workspace.addComponent(split);

        return workspace;
    }

    private Panel getDetailsPanel(ChangeObservable observable) {
        Panel details = Config.UI.getPanel();
        details.setLayout(Panel.Layout.NORMAL);

        Label name = Config.UI.getLabel();
        details.addComponent(name);

        Label content = Config.UI.getLabel();
        details.addComponent(content);

        Label parentName = Config.UI.getLabel();
        details.addComponent(parentName);

        Label childCount = Config.UI.getLabel();
        details.addComponent(childCount);

        Label leafCount = Config.UI.getLabel();
        details.addComponent(leafCount);

        if(observable != null) {
            observable.addObserver(new ChangeObserverAdapter<Node>() {
                @Override
                public void onChange(ChangeType type, Node bundle) {
                    if(type == ChangeType.SELECTION || type == ChangeType.UPDATE) {
                        AbstractModel model = (AbstractModel) bundle.getDelegateModel();
                        model.addObserver(this);

                        ModelUtils utils = new ModelUtils();

                        name.setText(Res.STRINGS.INFO_NODE_NAME + ": " + utils.getName(model));
                        content.setText(Res.STRINGS.INFO_NODE_CONTENT + ": " + utils.getContent(model));
                        parentName.setText(Res.STRINGS.INFO_NODE_PARENT_NAME + ": " + utils.getParentName(model));
                        childCount.setText(Res.STRINGS.INFO_NODE_CHILD_COUNT + ": " + String.valueOf(utils.getChildCount(model)));
                        leafCount.setText(Res.STRINGS.INFO_NODE_LEAF_COUNT + ": " + String.valueOf(utils.getLeafCount(model)));
                    }
                }
            });
        }

        return details;
    }

    private void addStatusBar() {
	    Label statusTime = Config.UI.getLabel();
	    statusTime.setPosition(Label.Position.LEFT);

        new DelayRunner(new ChangeObserverAdapter() {
            @Override
            public void onChange() {
                statusTime.setText(new SimpleDateFormat(Res.FORMATS.DATETIME).format(new Date()));
            }
        }, 1000).run();

        Label statusSaved = Config.UI.getLabel();
        statusSaved.setPosition(Label.Position.CENTER);

        CommandQueue.observe(new ChangeObserverAdapter<Command>() {
            @Override
            public void onChange(ChangeType type, Command bundle) {
                if(bundle.hasStatus()) {
                    statusSaved.setText(bundle.getStatus());
                }
            }
        });

        Label statusPlatform = Config.UI.getLabel();
        statusPlatform.setPosition(Label.Position.RIGHT);
        statusPlatform.setText(Machine.user() + " @ " + Machine.platform());

        Panel statusBar = Config.UI.getPanel();
        statusBar.setLayout(Panel.Layout.SHRINK);
        statusBar.setPadding(0, 10, 2, 10);
        statusBar.addComponent(statusTime);
        statusBar.addComponent(statusSaved);
        statusBar.addComponent(statusPlatform);
        frame.addComponent(statusBar, Label.Position.BOTTOM);
    }

    private void loadState() {
	    try {
            Paths.initialize();
        }catch(IOException e) {
            DialogAdapter.error(Res.STRINGS.ERROR_INITIALIZE_FAILED);
        }

        CommandQueue.push(new WriteSettingsAction());
        CommandQueue.push(new LoadAction(root));
    }
}