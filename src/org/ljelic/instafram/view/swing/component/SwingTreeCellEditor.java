package org.ljelic.instafram.view.swing.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

class SwingTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private static final int EDIT_CLICK_COUNT = 3;

    private SwingNode node = null;

    SwingTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
		if(value instanceof SwingNode) {
			node = (SwingNode) value;

			JTextField nameField = new JTextField(value.toString());
			nameField.addActionListener(this);

			return nameField;
		}

		return super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
	}

	@Override
	public boolean isCellEditable(EventObject event) {
		return event instanceof MouseEvent && ((MouseEvent) event).getClickCount() == EDIT_CLICK_COUNT;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
    	node.setName(e.getActionCommand());
	}
}