package org.isma.web.versusfighting.model.impl;

import org.isma.web.versusfighting.model.AbstractVersusFightingCasting;
import org.isma.web.versusfighting.model.Fighter;
import org.isma.web.versusfighting.model.Grid;

import java.awt.*;

public class SuperStreetFighter4ArcadeEditionCasting extends AbstractVersusFightingCasting {

    private enum CastingEnum {
        RYU("ryu", 0, 0),
        KEN("ken", 1, 0),
        EHONDA("ehonda", 2, 0),
        IBUKI("ibuki", 3, 0),
        MAKOTO("makoto", 4, 0),
        DUDLEY("dudley", 5, 0),
        SETH("seth", 6, 0),
        GOUKEN("gouken", 7, 0),
        AKUMA("akuma", 8, 0),
        GEN("gen", 9, 0),
        DAN("dan", 10, 0),
        SAKURA("sakura", 11, 0),
        ONI("oni", 12, 0),

        YUN("yun", 0, 1),
        JURI("juri", 1, 1),
        CHUNLI("chunli", 2, 1),
        DHALSIM("dhalsim", 3, 1),
        ABEL("abel", 4, 1),
        CVIPER("cviper", 5, 1),
        BISON("bison", 6, 1),
        SAGAT("sagat", 7, 1),
        CAMMY("cammy", 8, 1),
        DEEJAY("deejay", 9, 1),
        CODY("cody", 10, 1),
        GUY("guy", 11, 1),
        HAKAN("hakan", 12, 1),
        YANG("yang", 13, 1),

        EVILRYU("evilryu", 0, 2),
        GUILE("guile", 1, 2),
        BLANKA("blanka", 2, 2),
        ZANGIEF("zangief", 3, 2),
        RUFUS("rufus", 4, 2),
        ELFUERTE("elfuerte", 5, 2),
        VEGA("vega", 6, 2),
        BALROG("balrog", 7, 2),
        FEILONG("feilong", 8, 2),
        THAWK("thawk", 9, 2),
        ADON("adon", 10, 2),
        ROSE("rose", 11, 2);

        private Fighter fighter;
        private Point point;

        CastingEnum(String name, int x, int y) {
            fighter = new Fighter(name, name + ".gif");
            point = new Point(x, y);
        }

        @SuppressWarnings("UnusedDeclaration")
        public String getImageFileName() {
            return fighter.getLabel() + ".gif";
        }
    }

    @Override
    protected void feedGrid(Grid<Fighter> grid) {
        for (CastingEnum castingEnum : CastingEnum.values()) {
            grid.put(castingEnum.point, castingEnum.fighter);
        }
    }
}

