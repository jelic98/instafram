package org.ljelic.instafram.view;

import org.ljelic.instafram.view.component.*;
import java.io.IOException;

public abstract class AbstractUI {

    public abstract Label getLabel();
    public abstract Panel getPanel();
    public abstract ScrollPanel getScrollPanel();
    public abstract SplitPanel getSplitPanel();
    public abstract ImageLabel getImageLabel(byte[] bytes) throws IOException;
    public abstract TabPanel getTabPanel();
    public abstract Button getButton();
    public abstract TextField getTextField();
    public abstract PasswordField getPasswordField();
    public abstract TextArea getTextArea();
    public abstract CheckBox getCheckBox();
    public abstract ComboBox getComboBox();
    public abstract ToolBar getToolBar();
    public abstract MenuBar getMenuBar();
    public abstract MenuItem getMenuItem();
    public abstract Menu getMenu();
    public abstract PopupMenu getPopupMenu();
    public abstract Node getNode(String name, byte[] icon);
    public abstract Tree getTree();
    public abstract Frame getFrame();
    public abstract Dialog getDialog();
    public abstract FileChooser getFileChooser();
    public abstract String[] getLookFeels();
    public abstract void setLookFeel(String lookFeel);
}