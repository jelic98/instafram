package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.model.AbstractModel;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.adapter.dialog.Question;

public class ExitAction extends Command {

    private final AbstractModel rootNode;

    public ExitAction(AbstractModel rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    void execute() {
        DialogAdapter.question(new Question(Res.STRINGS.QUESTION_UNSAVED)
                .addOption(Res.STRINGS.OPTION_SAVE_EXIT, new Command() {
                    @Override
                    void execute() {
                        CommandQueue.push(new SaveAction(rootNode), ThreadOptions.SINGLE_THREAD);
                        System.exit(0);
                    }
                })
                .addOption(Res.STRINGS.OPTION_YES, new Command() {
                    @Override
                    void execute() {
                        System.exit(0);
                    }
                })
                .addOption(Res.STRINGS.OPTION_NO));
    }
}