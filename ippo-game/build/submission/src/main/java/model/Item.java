// IPPO Assignment 2, Version 19.11, §PUBDATE
package model;

import javafx.scene.image.Image;

/**
 * A class for items in MyWorld.
 *
 * @author Yadi Cong &lt;s1910257@ed.ac.uk&gt;
 * @version 19.11, §PUBDATE
 */
public class Item {
    private Image itemImage;

    /**
     * Set the image of the item.
     */
    public Item(String imagename) {
        this.itemImage = new Image(imagename);
    }

    /**
     * Get the image of the item.
     */
    public Image getItemImage() {
        return itemImage;
    }
}
