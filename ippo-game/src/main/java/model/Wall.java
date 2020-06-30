// IPPO Assignment 2, Version 19.11, §PUBDATE
package model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for the wall in MyWorld.
 *
 * @author Yadi Cong &lt;s1910257@ed.ac.uk&gt;
 * @version 19.11, §PUBDATE
 */
public class Wall {
    private Image image;
    private Room currentroom;
    //stores exits of this wall
    private List<Wall> exits = new ArrayList<>();
    //stores paths of this wall
    private List<Wall> path = new ArrayList<>();

    private boolean haveDoor=false;
    private boolean havePath=false;

    /**
     * Initialize the wall.
     */
    public Wall(String imagename,Room room) {
        this.image = new Image(imagename);
        this.currentroom=room;
    }

    /**
     * Define an exit from this wall.
     * @param neighbor The wall players can see go through the door.
     */
    public void setExists(Wall neighbor) {
        exits.add(neighbor);
        haveDoor=true;
    }

    /**
     * Define a path from this wall.
     * @param neighbor The wall players can see go through the path.
     */
    public void setPath(Wall neighbor) {
        path.add(neighbor);
        havePath=true;
    }

    /**
     * Get the image of the wall.
     */
    public Image getWallImage(){
        return image;
    }

    /**
     * Get the exit walls of the current wall.
     * @param i The number of the wall exit.
     */
    public Wall getExistWall(int i){
        return exits.get(i);
    }

    /**
     * Get the path walls of the current wall.
     * @param i The number of the wall path.
     */
    public Wall getPathWall(int i){
        return path.get(i);
    }

    /**
     * Test wall has exits.
     */
    public boolean haveExit(){
        return haveDoor;
    }

    /**
     * Test wall has paths.
     */
    public boolean havePath(){
        return havePath;
    }

    /**
     * Test wall has exits.
     */
    public Room getCurrentroom(){
        return currentroom;
    }
}
