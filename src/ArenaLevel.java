import edu.digipen.InputManager;
import edu.digipen.SoundManager;
import edu.digipen.gameobject.GameObject;
import edu.digipen.gameobject.ObjectManager;
import edu.digipen.graphics.Graphics;
import edu.digipen.level.GameLevel;
import edu.digipen.level.GameLevelManager;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;
import edu.digipen.text.FontTypes;
import edu.digipen.text.TextObject;

import java.awt.event.KeyEvent;

public class ArenaLevel extends GameLevel
{
	int timer = 0;
	TextObject text;
	public static int score;

	@Override public void create()
	{
		GameObject Ship = new playerShip();
		ObjectManager.addGameObject(Ship);
		Graphics.setDrawCollisionData(true);
		text = new TextObject("text","Score : " + score , FontTypes.ARIAL_16);
		text.destroy();
	}

	@Override public void initialize()
	{
		SoundManager.addBackgroundSound("LevelMusic","LevelMusic.wav",true);
		SoundManager.playBackgroundSound("LevelMusic");
		score = 0;
	}

	@Override public void update(float dt)
	{
		text.setPosition(-350,250);
		text.setZOrder(1001);
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
		//System.out.println(score);

	}

	@Override public void uninitialize()
	{
		ObjectManager.removeAllObjectsByName("asteroid");
		ObjectManager.removeAllObjectsByName("Bullet");
	}

	public void changeScore(int amount)
	{
		score = amount;
		text.setText("Score : " + score);
	}
	public int getScore()
	{
		return score;
	}


	public void spawnAsteroid()
	{
		Vec2 negNeg = new Vec2(PFRandom.randomRange(100,150)*-1, PFRandom.randomRange(100,150)*-1);
		Vec2 posPos = new Vec2(PFRandom.randomRange(100,150),PFRandom.randomRange(100,150));
		Vec2 posNeg = new Vec2(PFRandom.randomRange(100,150),PFRandom.randomRange(100,150)*-1);
		Vec2 negPos = new Vec2(PFRandom.randomRange(100,150)*-1,PFRandom.randomRange(100,150));

		Vec2 randomVelocity[] = new Vec2[4];
		randomVelocity[0] = negNeg;
		randomVelocity[1] = posPos;
		randomVelocity[2] = posNeg;
		randomVelocity[3] = negPos;


		int randVelChoose = PFRandom.randomRange(0,4);
		int randVelChoose2 = PFRandom.randomRange(0,4);
		int randVelChoose3 = PFRandom.randomRange(0,4);
		int randVelChoose4 = PFRandom.randomRange(0,4);

		int[] randomSizeArray = new int[3];
		randomSizeArray[0]=128;
		randomSizeArray[1]=32;
		randomSizeArray[2]=16;

		int randomSize = PFRandom.randomRange(0,3);

		int randomNum = PFRandom.randomRange(1,5);



		if (randomNum == 1)
		{
			Asteroid asteroid = new Asteroid(128,randomVelocity[randVelChoose],this);
			asteroid.setPositionY(PFRandom.randomRange(300, 538));
			ObjectManager.addGameObject(asteroid);
		}
		else if (randomNum == 2)
		{
			Asteroid asteroid = new Asteroid(128,randomVelocity[randVelChoose2],this);
			asteroid.setPositionY(PFRandom.randomRange(-538, -300));
			ObjectManager.addGameObject(asteroid);
		}
		else if(randomNum == 3)
		{
			Asteroid asteroid = new Asteroid(128,randomVelocity[randVelChoose3],this);
			asteroid.setPositionX(PFRandom.randomRange(422, 800));
			ObjectManager.addGameObject(asteroid);
		}
		else
		{
			Asteroid asteroid = new Asteroid(128,randomVelocity[randVelChoose4],this);
			asteroid.setPositionX(PFRandom.randomRange(-500, -382));
			ObjectManager.addGameObject(asteroid);
		}
		System.out.println(randomNum);

	}

}
