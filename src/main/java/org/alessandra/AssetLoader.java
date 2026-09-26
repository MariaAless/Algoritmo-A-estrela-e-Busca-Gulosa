package org.alessandra;

import javafx.scene.image.Image;

public class AssetLoader {
    public static final Image FLOOR = load("nuvem.jpeg");
    public static final Image WALL  = load("fire.jpeg");
    public static final Image START = load("portal.png");
    public static final Image END = load("potion.png");
    public static final Image AGENT = load("cute_cat.png");
    public static final Image TRANSFORMED = load("Monster.png");

    private static Image load(String filename) {
        return new Image(AssetLoader.class.getResourceAsStream("/org.alessandra/images/" + filename));
    }
}