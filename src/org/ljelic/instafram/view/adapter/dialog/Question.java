package org.ljelic.instafram.view.adapter.dialog;

import java.util.LinkedList;
import java.util.List;
import org.ljelic.instafram.observer.command.Command;
import org.ljelic.instafram.observer.command.CommandQueue;
import org.ljelic.instafram.observer.command.ThreadOptions;

public class Question {

	private final String message;
	private final List<Option> options;

	public Question(String message) {
	    this.message = message;

	    options = new LinkedList<>();
    }

    public Question addOption(String text) {
        options.add(new Option(text, null));

        return this;
    }

    public Question addOption(String text, Command action) {
	    options.add(new Option(text, action));

	    return this;
    }

    public String getMessage() {
        return message;
    }

    public String[] getOptions() {
	    String[] res = new String[options.size()];

        for(int i = 0; i < options.size(); i++) {
	        res[i] = options.get(i).getText();
        }

        return res;
    }

    public void onAction(int response) {
	    for(int i = 0; i < options.size(); i++) {
	        if(i == response) {
	            Command action = options.get(i).getAction();

	            if(action != null) {
                    CommandQueue.push(action, ThreadOptions.SINGLE_THREAD);
                }

	            break;
            }
        }
    }

    private static class Option {
		
		private final String text;
		private final Command action;
		
		Option(String text, Command action) {
			this.text = text;
			this.action = action;
		}

        String getText() {
            return text;
        }

        Command getAction() {
            return action;
        }
    }
}