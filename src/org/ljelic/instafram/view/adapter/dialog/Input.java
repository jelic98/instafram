package org.ljelic.instafram.view.adapter.dialog;

public class Input {

	private final String title, message;

	public Input(String title, String message) {
	    this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}