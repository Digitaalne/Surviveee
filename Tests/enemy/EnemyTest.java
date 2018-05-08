package enemy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class EnemyTest {
    class EnemyTest1 extends Enemy
    {
        public EnemyTest1()
        {
            this.type = Type.BASIC;
            health = 50;
            damage = 10;
            speed = 0.5;
        }
    }
    @Test
    void getDamageTest()
    {
        EnemyTest1 enemyTest1 = new EnemyTest1();
        assertEquals(enemyTest1.getDamage(), 10);
    }
    @Test
    void getHealthTest()
    {
        EnemyTest1 enemyTest1 = new EnemyTest1();
        assertEquals(enemyTest1.getHealth(), 50);
    }
    @Test
    void getTypeTest()
    {
        EnemyTest1 enemyTest1 = new EnemyTest1();
        assertEquals(enemyTest1.getType(), Enemy.Type.BASIC);
    }
    @Test
    void setHealthTest()
    {
        EnemyTest1 enemyTest1 = new EnemyTest1();
        enemyTest1.setHealth(1);
        assertEquals(enemyTest1.getHealth(), 1);
    }
}
