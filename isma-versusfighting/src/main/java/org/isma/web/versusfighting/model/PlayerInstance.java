package org.isma.web.versusfighting.model;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.List;

public class PlayerInstance extends AbstractAliveInstance<Player> {
    private List<FighterInstance> fighterInstances;

    public PlayerInstance(Player object, List<FighterInstance> fighterInstances) {
        super(object);
        this.fighterInstances = fighterInstances;
    }

    @Override
    public boolean isAlive() {
        for (FighterInstance fighterInstance : fighterInstances) {
            if (fighterInstance.isAlive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void kill() {
        throw new RuntimeException("not implemented");
    }

    public List<FighterInstance> getFighterInstances() {
        return fighterInstances;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerInstance)) return false;

        PlayerInstance playerInstance = (PlayerInstance) o;

        if (getObject().getId() != playerInstance.getObject().getId()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getObject().getId();
    }

    @Override
    public String toString() {
        //TODO tjs l'histoire de renderer jsp
        return getObject().getLabel();
    }

    public int getRemainingFightersCount() {
        return CollectionUtils.countMatches(fighterInstances, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                return ((FighterInstance) object).isAlive();
            }
        });
    }
}
