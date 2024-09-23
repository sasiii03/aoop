// Singleton Pattern - Game State Manager
class GameState {
    private static GameState instance;
    private int level;
    private String difficulty;

    private GameState() {
        level = 1;
        difficulty = "Easy";
    }

    public static synchronized GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}

// Factory Method Pattern - Enemy Creation
abstract class Enemy {
    public abstract void attack();
}

class EasyEnemy extends Enemy {
    public void attack() {
        System.out.println("Easy enemy attacks!");
    }
}

class HardEnemy extends Enemy {
    public void attack() {
        System.out.println("Hard enemy attacks!");
    }
}

abstract class EnemyFactory {
    public abstract Enemy createEnemy();
}

class EasyEnemyFactory extends EnemyFactory {
    public Enemy createEnemy() {
        return new EasyEnemy();
    }
}

class HardEnemyFactory extends EnemyFactory {
    public Enemy createEnemy() {
        return new HardEnemy();
    }
}

// Abstract Factory Pattern - Weapons and Power-ups
interface Weapon {
    void use();
}

class Sword implements Weapon {
    public void use() {
        System.out.println("Swinging a sword!");
    }
}

class Gun implements Weapon {
    public void use() {
        System.out.println("Shooting with a gun!");
    }
}

interface PowerUp {
    void activate();
}

class HealthPowerUp implements PowerUp {
    public void activate() {
        System.out.println("Health power-up activated!");
    }
}

class SpeedPowerUp implements PowerUp {
    public void activate() {
        System.out.println("Speed power-up activated!");
    }
}

abstract class AbstractFactory {
    public abstract Weapon createWeapon();

    public abstract PowerUp createPowerUp();
}

class EasyLevelFactory extends AbstractFactory {
    public Weapon createWeapon() {
        return new Sword();
    }

    public PowerUp createPowerUp() {
        return new HealthPowerUp();
    }
}

class HardLevelFactory extends AbstractFactory {
    public Weapon createWeapon() {
        return new Gun();
    }

    public PowerUp createPowerUp() {
        return new SpeedPowerUp();
    }
}

// Main Class to Run the Game
public class Game {
    public static void main(String[] args) {
        GameState gameState = GameState.getInstance();
        gameState.setLevel(2);
        gameState.setDifficulty("Hard");

        EnemyFactory enemyFactory;
        AbstractFactory levelFactory;

        if (gameState.getDifficulty().equals("Easy")) {
            enemyFactory = new EasyEnemyFactory();
            levelFactory = new EasyLevelFactory();
        } else {
            enemyFactory = new HardEnemyFactory();
            levelFactory = new HardLevelFactory();
        }

        Enemy enemy = enemyFactory.createEnemy();
        Weapon weapon = levelFactory.createWeapon();
        PowerUp powerUp = levelFactory.createPowerUp();

        enemy.attack();
        weapon.use();
        powerUp.activate();
    }
}
