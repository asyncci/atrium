package com.example.atrium.models.nondb;

public class TopicForm {

    private String title;
    private String argument;

    public TopicForm(String title, String argument) {
        this.title = title;
        this.argument = argument;
    }

    public TopicForm(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
