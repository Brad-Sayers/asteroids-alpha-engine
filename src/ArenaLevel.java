import edu.digipen.InputManager;
import edu.digipen.gameobject.GameObject;
import edu.digipen.gameobject.ObjectManager;
import edu.digipen.graphics.Graphics;
import edu.digipen.level.GameLevel;
import edu.digipen.level.GameLevelManager;
import edu.digipen.math.PFRandom;

import java.awt.event.KeyEvent;

public class ArenaLevel extends GameLevel
{
	int timer = 0;
	@Override public void create()
	{
		GameObject Ship = new playerShip();
		ObjectManager.addGameObject(Ship);
		Graphics.setDrawCollisionData(true);
	}

	@Override public void initialize()
	{

	}

	@Override public void update(float dt)
	{
		if (InputManager.isPressed(KeyEvent.VK_R))
		{
			GameLevelManager.restartLevel();
		}
		timer++;
		if (timer / 60 == 5)
		{
			spawnAsteroid();
			timer = 0;
		}
		GameObject player = ObjectManager.getGameObjectByName("PlayerShip");
		//System.out.println(player.getPosition());
	}

	@Override public void uninitialize()
	{
		ObjectManager.removeAllObjectsByName("asteroid");
		ObjectManager.removeAllObjectsByName("Bullet");
	}

	public void spawnAsteroid()
	{
		int[] randomSizeArray = new int[3];
		randomSizeArray[0]=128;
		randomSizeArray[1]=32;
		randomSizeArray[2]=16;

		int randomSize = PFRandom.randomRange(0,3);

		int randomNum = PFRandom.randomRange(1,5);
		int velocity = PFRandom.randomRange(100,150);

		if (randomNum == 1)
		{
			Asteroid asteroid = new Asteroid(randomSizeArray[randomSize],velocity);
			asteroid.setPositionY(PFRandom.randomRange(300, 538));
			ObjectManager.addGameObject(asteroid);
		}
		else if (randomNum == 2)
		{
			Asteroid asteroid = new Asteroid(randomSizeArray[randomSize],velocity);
			asteroid.setPositionY(PFRandom.randomRange(-538, -300));
			ObjectManager.addGameObject(asteroid);
		}
		else if(randomNum == 3)
		{
			Asteroid asteroid = new Asteroid(randomSizeArray[randomSize],velocity);
			asteroid.setPositionX(PFRandom.randomRange(422, 800));
			ObjectManager.addGameObject(asteroid);
		}
		else
		{
			Asteroid asteroid = new Asteroid(randomSizeArray[randomSize],velocity);
			asteroid.setPositionX(PFRandom.randomRange(-500, -382));
			ObjectManager.addGameObject(asteroid);
		}
		System.out.println(randomNum);

	}

}
