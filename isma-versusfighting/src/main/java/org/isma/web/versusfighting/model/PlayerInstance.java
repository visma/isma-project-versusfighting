package org.isma.web.versusfighting.model;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.List;

public class PlayerInstance extends AbstractAliveInstance<Player> {
    private List<FighterInstance> fighterInstanceList;

    public PlayerInstance(Player object, List<FighterInstance> fighterInstanceList) {
        super(object);
        this.fighterInstanceList = fighterInstanceList;
    }

    @Override
    public boolean isAlive() {
        for (FighterInstance fighterInstance : fighterInstanceList) {
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

    public List<FighterInstance> getFighterInstanceList() {
        return fighterInstanceList;
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
        return CollectionUtils.countMatches(fighterInstanceList, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                return ((FighterInstance) object).isAlive();
            }
        });
    }
}
