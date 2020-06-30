// IPPO Assignment 2, Version 19.11, §PUBDATE
package model;

import java.util.HashSet;
import java.util.Set;

/**
 * A class for the player in MyWorld.
 *
 * @author Yadi Cong &lt;s1910257@ed.ac.uk&gt;
 * @version 19.11, §PUBDATE
 */
public class Player {
    private Room room;
    private String direction;
    //declear a set to store items hold by player
    public Set<Item> items=new HashSet<>();

    /**
     * Initialize the player.
     */
    public Player(Room room, String direction){
        this.room= room;
        this.direction=direction;
    }

    /**
     * Get the wall which the player face to.
     */
    public Wall getWall(){
        return room.getaWall(direction);
    }

    /**
     * Change the room where the player is located.
     */
    public void resetRoom(Room room){
        this.room=room;
    }

    /**
     * Change the direction of the player.
     */
    public void setDirection(String direction){
        this.direction=direction;
    }

    /**
     * Get the direction of the player.
     */
    public String getDirection(){
        return direction;
    }

    /**
     * Get the room where the player is located.
     */
    public Room getCurrentRoom(){
        return room;
    }

    /**
     * Add items to the set of items held by the player.
     */
    public void pickItems(Item item) {
        items.add(item);
    }

    /**
     * Get the collection of items the player is holding.
     */
    public Set<Item> haveItems(){
        return items;
    }

    /**
     * Remove items to the set of items held by the player.
     */
    public void putItems(Item it) {
        items.remove(it);
    }
}

