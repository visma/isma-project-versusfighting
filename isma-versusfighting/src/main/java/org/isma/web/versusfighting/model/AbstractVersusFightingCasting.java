package org.isma.web.versusfighting.model;

import java.util.List;

public abstract class AbstractVersusFightingCasting {
    private Grid<Fighter> grid = new Grid<Fighter>();

    public AbstractVersusFightingCasting() {
        feedGrid(grid);
    }

    protected abstract void feedGrid(Grid<Fighter> grid);


    public List<List<Fighter>> buildGridData() {
        return grid.getData();
    }

    public int getFighterCount(){
        return grid.size();
    }


}
