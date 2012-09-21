package org.isma.web.versusfighting.model;

import org.isma.utils.Identifiable;

public class Fighter implements Identifiable {
    private String name;
    private String imageFileName;

    public Fighter(String name, String imageFileName) {
        this.name = name;
        this.imageFileName = imageFileName;
    }

    @Override
    public int getId() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String getLabel() {
        return name;
    }

    public String getImageFileName() {
        return imageFileName;
    }
}
