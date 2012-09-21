package org.isma.web.versusfighting.model;

import org.isma.utils.Identifiable;

public abstract class AbstractAliveInstance<I extends Identifiable> {
    private I object;
    private boolean alive = true;
    private boolean active = false;

    protected AbstractAliveInstance(I object) {
        this.object = object;

    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isActive() {
        return active;
    }

    public void active() {
        active = true;
    }

    public void unActive() {
        active = false;
    }

    public void kill() {
        alive = false;
        unActive();
    }

    public I getObject() {
        return object;
    }

    public String getLabel() {
        return getObject().getLabel();
    }

}
