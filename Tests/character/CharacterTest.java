package character;
import org.junit.jupiter.api.Test;
import weapons.Weapon;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {
    class CharacterTest1 extends Character {
        public CharacterTest1(Weapon weapon) {
            this.weapon = weapon;
        }

        @Override
        public void setWeapon(Weapon weapon)
        {
            if (this.weapon.getGun() == weapon.getGun()) {
                this.weapon.setBullets(this.weapon.getBullets() + weapon.getBullets());
            } else {
                this.weapon = weapon;
            }
        }
    }

    @Test
    void addingSameWeapon() {
        CharacterTest1 character = new CharacterTest1(new Weapon(Weapon.guns.RIFLE));
        character.setWeapon( new Weapon(Weapon.guns.SUBMACHINE));
        assertEquals(character.getWeapon().getBullets(), 250);
        character.setWeapon( new Weapon(Weapon.guns.SUBMACHINE));
        assertEquals(character.getWeapon().getBullets(), 500);
    }

    @Test
    void pickingUpDifferentWeapon()
    {
        CharacterTest1 character = new CharacterTest1(new Weapon(Weapon.guns.RIFLE));
        character.setWeapon( new Weapon(Weapon.guns.SUBMACHINE));
        assertEquals(character.getWeapon().getBullets(), 250);
        assertEquals(character.getWeapon().getGun(), (Weapon.guns.SUBMACHINE));
    }

    @Test
    void loseHealth()
    {
        CharacterTest1 character = new CharacterTest1(new Weapon(Weapon.guns.RIFLE));
        character.setHealth(90);
        assertEquals(character.getHealth(), 90);
    }

    @Test
    void BulletsZeroSwitchToPistol()
    {
        CharacterTest1 character = new CharacterTest1(new Weapon(Weapon.guns.RIFLE));
        character.getWeapon().setBullets(0);
        character.bulletZeroCheck();
        assertEquals(character.getWeapon().getGun(), Weapon.guns.PISTOL);
    }

    @Test
    void deltaCheck()
    {
        CharacterTest1 character = new CharacterTest1(new Weapon(Weapon.guns.RIFLE));
        character.setDeltas(1, 1);
        assertEquals(character.getDx(), 1);
        assertEquals(character.getDy(), 1);
        character.setDeltas(character.getDx(), -1);
        assertEquals(character.getDx(), 1);
        assertEquals(character.getDy(), -1);
    }

}
