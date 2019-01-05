package org.ljelic.instafram.observer.command;

import java.util.Stack;

class CommandHistory {

    private final Stack<Command> undoStack, redoStack;

    CommandHistory() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    void pushUndo(Command command) {
        undoStack.push(command);
    }

    Command popUndo() {
        if(undoStack.empty()) {
            return null;
        }

        return undoStack.pop();
    }

    void pushRedo(Command command) {
        redoStack.push(command);
    }

    Command popRedo() {
        if(redoStack.empty()) {
            return null;
        }

        return redoStack.pop();
    }
}