package com.ch_vadim.todoServer.store.entities;


public enum TaskState {
    CREATED("created"),
    COMPLETED("done");

    private String state;

    TaskState (String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}