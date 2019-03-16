package org.ljelic.instafram.view.swing;

import org.ljelic.instafram.util.log.Log;
import org.ljelic.instafram.view.AbstractUI;
import org.ljelic.instafram.view.component.*;
import org.ljelic.instafram.view.component.Button;
import org.ljelic.instafram.view.component.Dialog;
import org.ljelic.instafram.view.component.Frame;
import org.ljelic.instafram.view.component.Label;
import org.ljelic.instafram.view.component.Menu;
import org.ljelic.instafram.view.component.MenuBar;
import org.ljelic.instafram.view.component.MenuItem;
import org.ljelic.instafram.view.component.Panel;
import org.ljelic.instafram.view.component.PopupMenu;
import org.ljelic.instafram.view.component.TextArea;
import org.ljelic.instafram.view.component.TextField;
import org.ljelic.instafram.view.swing.component.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SwingUI extends AbstractUI {

    public static final int KEY_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

    public SwingUI() {
        UIManager.getDefaults().put("SwingButton.showMnemonics", true);

        setLookFeel(null);
    }

    @Override
    public Label getLabel() {
        return new SwingLabel();
    }

    @Override
    public Panel getPanel() {
        return new SwingPanel();
    }

    @Override
    public ScrollPanel getScrollPanel() {
        return new SwingScrollPanel();
    }

    @Override
    public SplitPanel getSplitPanel() {
        return new SwingSplitPanel();
    }

    @Override
    public ImageLabel getImageLabel(byte[] bytes) throws IOException {
        return new SwingImageLabel(bytes);
    }

    @Override
    public TabPanel getTabPanel() {
        return new SwingTabPanel();
    }

    @Override
    public Button getButton() {
        return new SwingButton();
    }

    @Override
    public TextField getTextField() {
        return new SwingTextField();
    }

    @Override
    public PasswordField getPasswordField() {
        return new SwingPasswordField();
    }

    @Override
    public TextArea getTextArea() {
        return new SwingTextArea();
    }

    @Override
    public CheckBox getCheckBox() {
        return new SwingCheckBox();
    }

    @Override
    public ComboBox getComboBox() {
        return new SwingComboBox();
    }

    @Override
    public ToolBar getToolBar() {
        return new SwingToolBar();
    }

    @Override
    public MenuBar getMenuBar() {
        return new SwingMenuBar();
    }

    @Override
    public MenuItem getMenuItem() {
        return new SwingMenuItem();
    }

    @Override
    public Menu getMenu() {
        return new SwingMenu();
    }

    @Override
    public PopupMenu getPopupMenu() {
        return new SwingPopupMenu();
    }

    @Override
    public Node getNode(String name, byte[] icon) {
        return new SwingNode(name, icon);
    }

    @Override
    public Tree getTree() {
        return new SwingTree();
    }

    @Override
    public Frame getFrame() {
        return new SwingFrame();
    }

    @Override
    public Dialog getDialog() {
        return new SwingDialog();
    }

    @Override
    public FileChooser getFileChooser() {
        return new SwingFileChooser();
    }

    @Override
    public String[] getLookFeels() {
        UIManager.LookAndFeelInfo[] installed = UIManager.getInstalledLookAndFeels();
        String[] lookFeels = new String[installed.length];

        for(int i = 0; i < installed.length; i++) {
            lookFeels[i] = installed[i].getClassName();
        }

        return lookFeels;
    }

    @Override
    public void setLookFeel(String lookFeel) {
        if(lookFeel == null) {
            lookFeel = UIManager.getSystemLookAndFeelClassName();
        }

        try {
            UIManager.setLookAndFeel(lookFeel);
        }catch(Exception e) {
            Log.e(e.getMessage());
        }
    }
}