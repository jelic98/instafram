package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.observer.ChangeObservable;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;

public class CommandQueue implements ChangeObservable<Command> {

    private static volatile CommandQueue instance;

    private final CommandHistory history;
    private final CommandConsumer consumer;
    private final ChangeObservableDelegate<Command> delegate;

    private CommandQueue() {
        history = new CommandHistory();
        consumer = new CommandConsumer();
        delegate = new ChangeObservableDelegate<>();

    }

    public static void push(Command command) {
        push(command, ThreadOptions.MULTI_THREAD);
    }

    public static void push(Command command, ThreadOptions threadOptions) {
        if(command == null) {
            return;
        }

        if(command.canUndo()) {
            command = ((UndoableCommand) command).getClone();
        }

        instance().consumer.consume(command, threadOptions);

        instance().notifyObservers(ChangeType.ADDITION, command);

        if(command.isSkipHistory() || !command.changesHistory()) {
            return;
        }

        instance().history.pushUndo(command);
    }

    public static void undo() {
        Command command = instance().history.popUndo();

        if(command == null) {
            DialogAdapter.error(Res.STRINGS.ERROR_CANNOT_UNDO);
            return;
        }

        if(!command.canUndo()) {
            DialogAdapter.error(Res.STRINGS.ERROR_CANNOT_UNDO);
            instance().history.pushUndo(command);
            return;
        }

        ((UndoableCommand) command).setState(UndoableCommand.ExecutionState.UNDO);

        instance().consumer.consume(command, ThreadOptions.MULTI_THREAD);
        instance().history.pushRedo(command);
    }

    public static void redo() {
        Command command = instance().history.popRedo();

        if(command == null) {
            DialogAdapter.error(Res.STRINGS.ERROR_CANNOT_REDO);
            return;
        }

        ((UndoableCommand) command).setState(UndoableCommand.ExecutionState.REDO);

        instance().consumer.consume(command, ThreadOptions.MULTI_THREAD);
        instance().history.pushUndo(command);
    }

    private static CommandQueue instance() {
        if(instance == null) {
            CommandQueue.synchronize();
        }

        return instance;
    }
    
    private static synchronized void synchronize() {
        if(instance == null) {
            instance = new CommandQueue();
        }
    }

    public static void observe(ChangeObserver observer) {
        instance().addObserver(observer);
    }

    @Override
    public void addObserver(ChangeObserver observer) {
        delegate.addObserver(observer);
    }

    @Override
    public void notifyObservers(ChangeType type, Command bundle) {
        delegate.notifyObservers(type, bundle);
    }
}