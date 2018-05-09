package enemy;

import javafx.scene.shape.Rectangle;

public class EnemyBoss extends Enemy {
    /**
     * creation of enemy pos
     * @param posx creation coordinate x
     * @param posy creation coordinate y
     */
    public EnemyBoss(int posx, int posy) {
        super(posx, posy);
        this.type = Type.BOSS;
        enemyRect = new Rectangle();
        enemyRect.setY(posy);
        enemyRect.setX(posx);
        enemyRect.setWidth(100);
        enemyRect.setHeight(100);
        speed = 0.5;
        health = 100;
        damage = 20;
    }

    /**
     * For testing purposes
     */
    public EnemyBoss()
    {
    }
}
