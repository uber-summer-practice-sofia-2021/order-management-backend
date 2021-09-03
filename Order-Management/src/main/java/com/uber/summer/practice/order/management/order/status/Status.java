package com.uber.summer.practice.order.management.order.status;

import com.uber.summer.practice.order.management.order.status.state.State;

public class Status {
    private State state;
    private boolean assigned;

    public Status(State state, boolean assigned) {
        this.state = state;
        this.assigned = assigned;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Status{" +
                "state=" + state +
                ", assigned=" + assigned +
                '}';
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
