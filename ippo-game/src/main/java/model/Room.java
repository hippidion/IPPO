package model;
// IPPO Assignment 2, Version 19.11, §PUBDATE
import java.util.HashMap;

/**
 * A class for the room in MyWorld.
 *
 * @author Yadi Cong &lt;s1910257@ed.ac.uk&gt;
 * @version 19.11, §PUBDATE
 */
public class Room {
    //stores the pair of distance and wall.
    private HashMap<String, Wall> walls = new HashMap<>();
    //stores the pair of items in this room and direction.
    private HashMap<Item,String> items =new HashMap<>();

    /**
     * Add walls to the room.
     * @param wallname1 north wall.
     * @param wallname2 south wall.
     * @param wallname3 east wall.
     * @param wallname4 west wall.
     */
    public void addWall(Wall wallname1, Wall wallname2, Wall wallname3, Wall wallname4){
        walls.put("north",wallname1);
        walls.put("south",wallname2);
        walls.put("east",wallname3);
        walls.put("west",wallname4);
    }

    /**
     * Get a wall from one direction in the room.
     * @param direction The direction the wall is in.
     */
    public Wall getaWall(String direction) {
        return walls.get(direction);
    }

    /**
     * Add items to the room.
     * @param direction The direction the item is in.
     * @param item The item that users want to add.
     */
    public void addItems(String direction,Item item){
        items.put(item,direction);
    }

    /**
     * Delete items in the room.
     * @param direction The direction the item is in.
     * @param item The item that users want to delete   .
     */
    public void deletItems(Item item){ items.remove(item);}

    /**
     * Add walls to the room.
     */
    public HashMap<Item,String> getItems(){
        return items;
    }
}
