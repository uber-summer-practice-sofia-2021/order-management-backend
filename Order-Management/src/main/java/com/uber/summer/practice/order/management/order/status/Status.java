package com.uber.summer.practice.order.management.order.status;

import com.uber.summer.practice.order.management.order.status.state.State;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Status implements Serializable {
    private State state;
    private boolean assigned;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID orderStatusID;

    public Status(State state, boolean assigned) {
        this.state = state;
        this.assigned = assigned;
    }

    public Status() {
          this(State.OPEN,false);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return assigned == status.assigned && state == status.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, assigned);
    }
}
