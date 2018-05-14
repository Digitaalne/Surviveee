package weapons;

import character.CharacterTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {
    @Test
    void rightFiringRatio() {
        CharacterTest.WeaponTester rifle = new CharacterTest.WeaponTester(Weapon.guns.RIFLE);
        CharacterTest.WeaponTester sub = new CharacterTest.WeaponTester(Weapon.guns.SUBMACHINE);
        CharacterTest.WeaponTester pistol = new CharacterTest.WeaponTester(Weapon.guns.PISTOL);
        CharacterTest.WeaponTester bolt = new CharacterTest.WeaponTester(Weapon.guns.BOLTACTION);
        assertTrue((sub.getShootingSpeed() < pistol.getShootingSpeed() && rifle.getShootingSpeed() < bolt.getShootingSpeed() && rifle.getShootingSpeed() < pistol.getShootingSpeed()));
    }

    @Test
    void setBulletsTest() {
        CharacterTest.WeaponTester rifle = new CharacterTest.WeaponTester(Weapon.guns.RIFLE);
        rifle.setBullets(1);
        assertEquals(rifle.getBullets(), 1);
        rifle.setBullets(rifle.getBullets() + 1);
        assertEquals(rifle.getBullets(), 2);
    }

    @Test
    void rightEnums() {
        CharacterTest.WeaponTester rifle = new CharacterTest.WeaponTester(Weapon.guns.RIFLE);
        CharacterTest.WeaponTester sub = new CharacterTest.WeaponTester(Weapon.guns.SUBMACHINE);
        CharacterTest.WeaponTester pistol = new CharacterTest.WeaponTester(Weapon.guns.PISTOL);
        CharacterTest.WeaponTester bolt = new CharacterTest.WeaponTester(Weapon.guns.BOLTACTION);
        assertEquals(rifle.getGun(), Weapon.guns.RIFLE);
        assertEquals(sub.getGun(), Weapon.guns.SUBMACHINE);
        assertEquals(pistol.getGun(), Weapon.guns.PISTOL);
        assertEquals(bolt.getGun(), Weapon.guns.BOLTACTION);
    }
}
