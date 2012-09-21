package org.isma.web.versusfighting.model;

public abstract class AbstractVersusFightingGame {
    private final String id;
    private final String label;
    private AbstractVersusFightingCasting casting;

    public AbstractVersusFightingGame(String id, String label, AbstractVersusFightingCasting casting) {
        this.id = id;
        this.label = label;
        this.casting = casting;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public AbstractVersusFightingCasting getCasting() {
        return casting;
    }



    @Override
    public String toString() {
        //TODO essayer de faire mieux coté renderer jsp
        return label;
    }

}
