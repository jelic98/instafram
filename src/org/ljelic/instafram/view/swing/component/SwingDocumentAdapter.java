package org.ljelic.instafram.view.swing.component;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

abstract class SwingDocumentAdapter implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        onUpdate();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        onUpdate();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        onUpdate();
    }

    public abstract void onUpdate();
}