package com.ch_vadim.todoServer.store.entities;

public enum NotificationState {
    NOTHING("nothing"),
    EMAIL("email"),
    TG("tg"),
    ALL("all");

    private String state;

    NotificationState (String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public NotificationState changeState(NotificationState state) {
        if (this.equals(NotificationState.NOTHING)) return state;
        if (this.equals(NotificationState.TG) && state.equals(NotificationState.EMAIL) ||
                this.equals(NotificationState.EMAIL) && state.equals(NotificationState.TG)
        ) return NotificationState.ALL;
        return this;
    }
}
