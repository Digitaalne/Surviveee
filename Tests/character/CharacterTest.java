package character;

import org.junit.jupiter.api.Test;
import weapons.Bullet;
import weapons.Weapon;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {
    class CharacterTest1 extends Character {
        public CharacterTest1(Weapon weapon) {
            this.weapon = weapon;
        }

        @Override
        public void setWeapon(Weapon weapon) {
            if (this.weapon.getGun() == weapon.getGun()) {
                this.weapon.setBullets(this.weapon.getBullets() + weapon.getBullets());
            } else {
                this.weapon = weapon;
            }
        }
        @Override
        public void bulletZeroCheck() {
            if (weapon.getBullets() <= 0) {
                setWeapon(new WeaponTester(Weapon.guns.PISTOL));
            }

        }

    }
    public static class WeaponTester extends Weapon{
        public WeaponTester(guns gun) {
            this.gun = gun;
            if (gun.equals(guns.PISTOL)) {
                bulletType = Bullet.bulletType.SEVEN_MM;
                bullets = Double.POSITIVE_INFINITY;
                shootingSpeed = 500;
            } else if (gun.equals(guns.BOLTACTION)) {
                bulletType = Bullet.bulletType.WINCHESTER;
                shootingSpeed = 1250;
                bullets = 10;
            } else if (gun.equals(guns.RIFLE)) {
                bulletType = Bullet.bulletType.NATO;
                shootingSpeed = 450;
                bullets = 25;
            } else if (gun.equals(guns.SUBMACHINE)) {
                bulletType = Bullet.bulletType.FORTY_FIVE;
                shootingSpeed = 120;
                bullets = 150;
            }
        }
    }

    @Test
    void addingSameWeapon() {
        CharacterTest1 character = new CharacterTest1(new WeaponTester(Weapon.guns.RIFLE));
        character.setWeapon(new WeaponTester(Weapon.guns.SUBMACHINE));
        assertEquals(character.getWeapon().getBullets(), 150);
        character.setWeapon(new WeaponTester(Weapon.guns.SUBMACHINE));
        assertEquals(character.getWeapon().getBullets(), 300);
    }

    @Test
    void pickingUpDifferentWeapon() {
        CharacterTest1 character = new CharacterTest1(new WeaponTester(Weapon.guns.RIFLE));
        character.setWeapon(new WeaponTester(Weapon.guns.SUBMACHINE));
        assertEquals(character.getWeapon().getBullets(), 150);
        assertEquals(character.getWeapon().getGun(), (Weapon.guns.SUBMACHINE));
    }

    @Test
    void loseHealth() {
        CharacterTest1 character = new CharacterTest1(new WeaponTester(Weapon.guns.RIFLE));
        character.setHealth(90);
        assertEquals(character.getHealth(), 90);
    }

    @Test
    void BulletsZeroSwitchToPistol() {
        CharacterTest1 character = new CharacterTest1(new WeaponTester(Weapon.guns.RIFLE));
        character.getWeapon().setBullets(0);
        character.bulletZeroCheck();
        assertEquals(character.getWeapon().getGun(), Weapon.guns.PISTOL);
    }

    @Test
    void deltaCheck() {
        CharacterTest1 character = new CharacterTest1(new WeaponTester(Weapon.guns.RIFLE));
        character.setDeltas(1, 1);
        assertEquals(character.getDx(), 1);
        assertEquals(character.getDy(), 1);
        character.setDeltas(character.getDx(), -1);
        assertEquals(character.getDx(), 1);
        assertEquals(character.getDy(), -1);
    }

}
