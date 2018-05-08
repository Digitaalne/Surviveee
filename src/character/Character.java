package character;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Main;
import main.MainMenu;
import weapons.Weapon;

public class Character {

    /**
     * The width of the Character picture.
     */
    public static int CHARACTER_WIDTH = 50;
    /**
     *  The length of the Character picture.
     */
    public static int CHARACTER_LENGTH = 80;

    /**
     * For tests
     */
    public Character() {
    }


    /**
     *  8 possible directions where character can move.
     */
    public enum direction {
        N, NE, E, SE, S, SW, W, NW
    }

    /**
     *  Current direction, where character is going.
     */
    private direction dir;
    /**
     * The speed of character movement.
     */
    private int speed;
    /**
     * Health of main character.
     */
    private int health = 100;
    //Karakteri 50 laius 80px pikkus
    /**
     * The Rectangle instance of character
     */
    Rectangle character;
    /**
     * The delta x of character
     */
    private double dx;
    /**
     * The delta y of character.
     */
    private double dy;
    /**
     * Current weapon for character.
     */
    Weapon weapon;
    /**
     * Instance of class Main
     */
    private Main main;
    /**
     *
     * @param speed - speed of character
     * @param x Coordinate x
     * @param y Coordinate y
     * @param weapon default Weapon you spawn with
     */
    public Character(int speed, int x, int y, Weapon weapon, Main main) {
        this.weapon = weapon;
        this.speed = speed;
        this.main = main;
        Rectangle charRec;
        charRec = new Rectangle();
        charRec.setHeight(CHARACTER_LENGTH);
        charRec.setWidth(CHARACTER_WIDTH);
        Image img = new Image("resources/right.png");
        charRec.setFill(new ImagePattern(img));
        this.character = charRec;
        this.character.setX(x);
        this.character.setY(y);
        dir = direction.E;
    }

    /**
     * Moves the character
     */
    public void move() {
        character.setX(character.getX() + dx * speed);
        character.setY(character.getY() + dy * speed);
    }

    /**
     *
     * @param dx the delta x
     * @param dy the delta y
     */
    public void setDeltas(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Changes player image and direction relative to deltas
     */
    public void playerDirection() {
        if (dx == 1 && dy == 1 && dir != direction.SE) {
            Image img = new Image("resources/SE.png");
            character.setFill(new ImagePattern(img));
            dir = direction.SE;
        } else if (dx == 1 && dy == 0 && dir != direction.E) {
            Image img = new Image("resources/right.png");
            character.setFill(new ImagePattern(img));
            dir = direction.E;
        } else if (dx == 1 && dy == -1 && dir != direction.NE) {
            Image img = new Image("resources/NE.png");
            character.setFill(new ImagePattern(img));
            dir = direction.NE;
        } else if (dx == 0 && dy == 1 && dir != direction.S) {
            Image img = new Image("resources/down.png");
            character.setFill(new ImagePattern(img));
            dir = direction.S;
        } else if (dx == 0 && dy == -1 && dir != direction.N) {
            Image img = new Image("resources/up.png");
            character.setFill(new ImagePattern(img));
            dir = direction.N;
        } else if (dx == -1 && dy == 1 && dir != direction.SW) {
            Image img = new Image("resources/SW.png");
            character.setFill(new ImagePattern(img));
            dir = direction.SW;
        } else if (dx == -1 && dy == 0 && dir != direction.W) {
            Image img = new Image("resources/left.png");
            character.setFill(new ImagePattern(img));
            dir = direction.W;
        } else if (dx == -1 && dy == -1 && dir != direction.NW) {
            Image img = new Image("resources/NW.png");
            character.setFill(new ImagePattern(img));
            dir = direction.NW;
        }

    }

    /**
     * Controls health displaying and death
     * @param label - death text Label
     * @param healthLabel - Health Label
     * @throws InterruptedException
     */
    public void deathAndHealth(Label label, Label healthLabel, Main main) throws InterruptedException {
        healthLabel.textProperty().setValue(Integer.toString(health));
        if (health <= 0) {
            label.textProperty().setValue("YOU DIED!");
            label.setFont(new Font("Racer", 52));
            main.cleanUp();
        }
    }

    /**
     * Returns Delta x
     * @return double dx
     */
    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    /**
     *
     * @return Character rectangle
     */
    public Rectangle getCharacter() {
        return character;
    }

    /**
     *
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health - health of character
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Calculates the middle point of character on X axis
     * @return double of character mid point on X axis
     */
    public double getCharacterMidX() {
        return character.getX() + CHARACTER_WIDTH / 2;
    }

    /**
     * Calculates the middle point of character on Y axis
     * @return double of character mid point on Y axis
     */
    public double getCharacterMidY() {
        return character.getY() + CHARACTER_LENGTH / 2;
    }

    /**
     *
     * @return current Weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Set new weapon or add bullets if it is the same weapon, also update once the bullets
     * @param weapon new Weapon
     */
    public void setWeapon(Weapon weapon) {
        if (this.weapon.getGun() == weapon.getGun()) {
            this.weapon.setBullets(this.weapon.getBullets() + weapon.getBullets());
        } else {
            this.weapon = weapon;
        }
        main.getBulletLabel().textProperty().setValue(Double.toString(weapon.getBullets()).substring(0,
                Double.toString(weapon.getBullets()).length()-2));
    }

    /**
     * Check if the bullets are 0 and then player gets pistol.
     */
    public void bulletZeroCheck() {
        if (weapon.getBullets() <= 0) {
            weapon = new Weapon(Weapon.guns.PISTOL);
        }
    }


    /**
     *
     * @return dir Player direction
     */
    public direction getDir() {
        return dir;
    }

}
