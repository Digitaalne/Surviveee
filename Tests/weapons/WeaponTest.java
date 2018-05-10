package weapons;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class WeaponTest {
    @Test
    void rightFiringRatio()
    {
        Weapon rifle = new Weapon(Weapon.guns.RIFLE);
        Weapon sub = new Weapon(Weapon.guns.SUBMACHINE);
        Weapon pistol = new Weapon(Weapon.guns.PISTOL);
        Weapon bolt = new Weapon(Weapon.guns.BOLTACTION);
        assertTrue((sub.getShootingSpeed() < pistol.getShootingSpeed() && rifle.getShootingSpeed() < bolt.getShootingSpeed() && rifle.getShootingSpeed() < pistol.getShootingSpeed()));
    }
    @Test
    void setBulletsTest()
    {
        Weapon rifle = new Weapon(Weapon.guns.RIFLE);
        rifle.setBullets(1);
        assertEquals(rifle.getBullets(),1);
        rifle.setBullets(rifle.getBullets() + 1);
        assertEquals(rifle.getBullets(), 2);
    }
    @Test
    void rightEnums()
    {
        Weapon rifle = new Weapon(Weapon.guns.RIFLE);
        Weapon sub = new Weapon(Weapon.guns.SUBMACHINE);
        Weapon pistol = new Weapon(Weapon.guns.PISTOL);
        Weapon bolt = new Weapon(Weapon.guns.BOLTACTION);
        assertEquals(rifle.getGun(), Weapon.guns.RIFLE);
        assertEquals(sub.getGun(), Weapon.guns.SUBMACHINE);
        assertEquals(pistol.getGun(), Weapon.guns.PISTOL);
        assertEquals(bolt.getGun(), Weapon.guns.BOLTACTION);
    }
}
