package enemy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyBossTest {
    class EnemyBossTest1 extends EnemyBoss
    {
        public EnemyBossTest1()
        {
            this.type = Type.BOSS;
            speed = 0.5;
            health = 100;
            damage = 20;
        }
    }
    @Test
    void typeTest()
    {
        EnemyBossTest1 enemyBoss = new EnemyBossTest1();
        assertEquals(enemyBoss.getType(), Enemy.Type.BOSS);
    }
    @Test
    void HealthTest()
    {
        EnemyBossTest1 enemyBoss = new EnemyBossTest1();
        assertEquals(enemyBoss.getHealth(), 100);
    }
    @Test
    void setHealthTest()
    {
        EnemyBossTest1 enemyBoss = new EnemyBossTest1();
        enemyBoss.setHealth(1);
        assertEquals(enemyBoss.getHealth(), 1);
    }
    @Test
    void testDeltas()
    {
        EnemyBossTest1 enemyBoss = new EnemyBossTest1();
        enemyBoss.setDeltas(1, 1);
        assertEquals(enemyBoss.getDx(), 1);
        assertEquals(enemyBoss.getDy(), 1);
        enemyBoss.setDeltas(enemyBoss.getDx(), -1);
        assertEquals(enemyBoss.getDx(), 1);
        assertEquals(enemyBoss.getDy(), -1);
    }
}
