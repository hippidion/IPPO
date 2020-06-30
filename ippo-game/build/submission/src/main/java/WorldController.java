// IPPO Assignment 2, Version 19.11, §PUBDATE
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

import model.Item;
import model.Player;
import model.Room;
import model.Wall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A controller for the MyWorld application.
 *
 * MyWorld is a simple game. Users can walk around some rooms, and can pick
 * up and put down things.
 *
 * This class creates and initialises all the others:it crates all rooms,
 * creates the player,creats the item and starts the game.
 *
 * @author Yadi Cong &lt;s1910257@ed.ac.uk&gt;
 * @version 19.11, §PUBDATE
 */
public class WorldController {
    @FXML
    public ImageView imageView,burgerView,rabbitView;
    @FXML
    public TextField x,y;

    private Room currentroom;
    private Player player;
    //create a list to hold the x-axis and y-axis coordinates of the user input.
    private List<Integer> coordinate;
    //declear a HashMap to store the relationship between the item and the room and direction(the wall).
    private HashMap<Item, Wall> map =new HashMap<>();
    //create a HashMap to store the relationship between item and its imageView.
    private HashMap<Item,ImageView> namemap=new HashMap<>();
    //create a list to store the order of the four directions.
    private List<String> directions = new ArrayList<>();

    private Item burger=new Item("burger.png");
    private Item rabbit=new Item("rabbit.png");

    /**
     * Create the game and initialise everything in it.
     */
    public void Initialise() {
        //Order of direction
        directions.add("north");
        directions.add("east");
        directions.add("south");
        directions.add("west");
        //the relationship between the item and the image view.
        namemap.put(rabbit,rabbitView);
        namemap.put(burger,burgerView);

        //create the map
        createRooms();

        //Let the player hold the two items.
        player = new Player(currentroom, "north");
        player.pickItems(burger);
        player.pickItems(rabbit);

        //show initial interface.
        imageView.setImage(player.getCurrentRoom().getaWall(player.getDirection()).getWallImage());
    }

    /**
     * Create all the rooms and link their exists and paths together
     */
    private void createRooms() {

        Room outside = new Room();
        Room inside1 = new Room();
        Room inside2 = new Room();
        Wall wall1, wall2, wall3, wall4, wall5, wall6, wall7, wall8, wall9, wall10, wall11, wall12;

        wall1 = new Wall("north3.jpeg", outside);
        wall2 = new Wall("south3.jpeg", outside);
        wall3 = new Wall("east3.jpeg", outside);
        wall4 = new Wall("west3.jpeg", outside);
        wall5 = new Wall("north1.jpeg", inside1);
        wall6 = new Wall("south1.jpeg", inside1);
        wall7 = new Wall("east1.jpeg", inside1);
        wall8 = new Wall("west1.jpeg", inside1);
        wall9 = new Wall("north2.jpeg", inside2);
        wall10 = new Wall("south2.jpeg", inside2);
        wall11 = new Wall("east2.jpeg", inside2);
        wall12 = new Wall("west2.jpeg", inside2);

        wall2.setExists(wall6);
        wall5.setExists(wall1);
        wall6.setPath(wall10);
        wall9.setPath(wall5);

        outside.addWall(wall1, wall2, wall3, wall4);
        inside1.addWall(wall5, wall6, wall7, wall8);
        inside2.addWall(wall9, wall10, wall11, wall12);

        currentroom = inside1;
    }

    /**
     * The user can turn right by clicking the button.
     */
    public void turnRight(ActionEvent event) {
        //If the current direction is west, reset it to the north.
        if (player.getDirection().equals("west")) {
            player.setDirection("north");
        } else {
            player.setDirection(directions.get(directions.indexOf(player.getDirection()) + 1));
        }

        imageView.setImage(player.getCurrentRoom().getaWall(player.getDirection()).getWallImage());
        showBurger(player.getWall());
        showRabbit(player.getWall());
    }

    /**
     * The user can turn left by clicking the button.
     */
    public void turnLeft(ActionEvent event) {
        //If the current direction is north, reset it to the west.
        if (player.getDirection().equals("north")) {
            player.setDirection("west");
        } else {
            player.setDirection(directions.get(directions.indexOf(player.getDirection()) - 1));
        }

        imageView.setImage(player.getCurrentRoom().getaWall(player.getDirection()).getWallImage());
        showBurger(player.getWall());
        showRabbit(player.getWall());

    }

    /**
     * The user can move forward by clicking the button.
     */
    public void moveForward(ActionEvent event) {
        if (player.getWall().havePath()) {
            currentroom=player.getWall().getPathWall(0).getCurrentroom();
            player.resetRoom(currentroom);
            String d = player.getDirection();
            imageView.setImage(currentroom.getaWall(d).getWallImage());
        } else {
            System.out.println("There is no way to go.");
        }

        showBurger(player.getWall());
        showRabbit(player.getWall());
    }

    /**
     * The user can exit through the door by clicking the button.
     */
    public void exit(ActionEvent event) {
        if (player.getWall().haveExit()) {
            currentroom=player.getWall().getExistWall(0).getCurrentroom();
            player.resetRoom(currentroom);
            String d = player.getDirection();
            imageView.setImage(currentroom.getaWall(d).getWallImage());
        } else {
            System.out.println("There is no way to go.");
        }
        showBurger(player.getWall());
        showRabbit(player.getWall());
    }

    /**
     * The user can exit pick up items by clicking the button.
     * @param item is the item the user select in the menu.
     */
    private void pickItem(Item item) {
        if(player.getWall()==map.get(item)){
            currentroom.deletItems(item);
            map.remove(item);
            player.pickItems(item);
            namemap.get(item).setImage(null);
        }else{
            System.out.println("I cannot find this item.");
        }
    }

    /**
     * The user can exit put down items by clicking the button.
     * @param item is the item the user select in the menu.
     */
    private void putItem(Item item){
        if(player.haveItems().contains(item)){
            player.putItems(item);
            currentroom.addItems(player.getDirection(),item);
            map.put(item,player.getWall());
            namemap.get(item).setImage(item.getItemImage());
        }else{
            System.out.println("I do not have this item");
        }
    }

    /**
     * Determine whether to display the item burger.
     * @param wall represents a combination of direction and room.
     */
    public void showBurger(Wall wall){
        if(map.get(burger)==wall) {
            burgerView.setImage(burger.getItemImage());
        }else {
            burgerView.setImage(null);
        }
    }

    /**
     * Determine whether to display the item rabbit.
     * @param wall represents a combination of direction and room。
     */
    public void showRabbit(Wall wall){
        if(map.get(rabbit)==wall) {
            rabbitView.setImage(rabbit.getItemImage());
        }else {
            rabbitView.setImage(null);
        }
    }

    /**
     *Receive coordinates entered by the user.
     */
    public void getCoordinate(ActionEvent event) {
        coordinate=new ArrayList<>();//reset each time before entering.

        if(!x.getText().trim().equals("")&&!y.getText().trim().equals("")) {
            if(isNumber(x.getText().trim())&&isNumber(y.getText().trim())) {
                int xn = Integer.valueOf(x.getText().trim());
                int yn = Integer.valueOf(y.getText().trim());
                coordinate.add(xn);
                coordinate.add(yn);
            }else {
                System.out.println("Please enter numbers.");
            }
        }else{
            System.out.println("Please enter something.");
        }
    }

    /**
     * Detect whether the value entered by the user is numbers.
     * @param str is the string entered by the user through the textField.
     */
    private boolean isNumber(String str){
        Pattern pattern=Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if(!isNum.matches()){return false;}
        return true;
    }

    /**
     * Move items based on the coordinates entered。
     * @param item is is the item the user select by the button.
     */
    private void MoveItem(Item item){
        if(player.getWall()==map.get(item)) {
            if (coordinate.get(0) <= 400 && coordinate.get(1) <= 370) {
                namemap.get(item).setLayoutX(coordinate.get(0));
                namemap.get(item).setLayoutY(coordinate.get(1));
            } else {
                System.out.println("Do not have this coordinate.");
            }
        }else{
            System.out.println("I cannot move it.");
        }
    }

    /**
     * Operate two items spearately.
     */
    public void moveBurger(){
        MoveItem(burger);
    }
    public void moveRabbit(){
        MoveItem(rabbit);
    }
    public void putBurger(ActionEvent event){
        putItem(burger);
    }
    public void putRabbit(ActionEvent event){
        putItem(rabbit);
    }
    public void pickBurger(ActionEvent event){
        pickItem(burger);
    }
    public void pickRabbit(ActionEvent event){
        pickItem(rabbit);
    }

}