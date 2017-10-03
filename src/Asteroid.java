import edu.digipen.gameobject.GameObject;
import edu.digipen.gameobject.ObjectManager;
import edu.digipen.graphics.Graphics;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;

public class Asteroid extends GameObject
{
	float speed = 0.001f;
	int sizeVar;

	public Asteroid(int size, int velocity)
	{
		super("asteroid",128,128,"CircleLarge.png");
		if (size == 128)
		{
			setCircleCollider(64);
		}
		if (size == 32)
		{
			setScale(.25f,.25f);
			setCircleCollider(16);
		}
		if (size == 16)
		{
			setScale(.125f,.125f);
			setCircleCollider(8);
		}
		sizeVar = size;
	}

	@Override public void update(float dt)
	{

		GameObject playerShip = ObjectManager.getGameObjectByName("PlayerShip");

		Vec2 vector = new Vec2();
		vector.setX(playerShip.getPositionX() - getPositionX());
		vector.setY(playerShip.getPositionY() - getPositionY());

		setPositionX(getPositionX() + vector.getX() * speed);
		setPositionY(getPositionY() + vector.getY() * speed);
		vector.normalize();
		checkWrap();
	}

	@Override public void collisionReaction(GameObject other)
	{
		int velocity = PFRandom.randomRange(100,150);

		if (other.getName().equals("Bullet") && sizeVar == 128)
		{
			float xPos = other.getPositionX();
			float yPos = other.getPositionY();

			int count = 0;
			while (count != 4)
			{
				GameObject asteroidMed = new Asteroid(32, velocity);
				ObjectManager.addGameObject(asteroidMed);
				setPosition(xPos,yPos);
				count++;
				setVelocity(velocity,velocity);
			}

			kill();
		}
		if (other.getName().equals("Bullet") && sizeVar == 32)
		{
			kill();
		}
		if (other.getName().equals("Bullet") && sizeVar == 16)
		{
			kill();
		}
	}

	public void checkWrap()
	{
		float halfScreenWidth = Graphics.getWindowWidth() / 2;
		float halfScreenHeight = Graphics.getWindowHeight() / 2;
		float offset = 32;//Half the width
		if ((getPositionX() - offset) > halfScreenWidth)
		{
			setPositionX(-halfScreenWidth);
		}
		if ((getPositionX() + offset) < -halfScreenWidth)
		{
			setPositionX(halfScreenWidth);
		}
		if ((getPositionY() - offset) > halfScreenHeight)
		{
			setPositionY(-halfScreenHeight);
		}
		if ((getPositionY() + offset) < -halfScreenHeight)
		{
			setPositionY(halfScreenHeight);
		}
	}

}