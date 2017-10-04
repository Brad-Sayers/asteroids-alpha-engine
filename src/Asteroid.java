import edu.digipen.gameobject.GameObject;
import edu.digipen.gameobject.ObjectManager;
import edu.digipen.graphics.Graphics;
import edu.digipen.level.GameLevel;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;

public class Asteroid extends GameObject
{
	float speed = 0.001f;
	int sizeVar;
	boolean Broken = false;
	ArenaLevel levelRef;

	public Asteroid(int size, Vec2 velocity, GameLevel levelRef_)
	{


		super("asteroid",128,128,"CircleLarge.png");
		System.out.println("making a " + size + " asteroid");
		levelRef = (ArenaLevel)levelRef_;
		if (size == 128)
		{
			setCircleCollider(64);
			setVelocity(velocity);
		}
		if (size == 32)
		{
			setScale(.25f,.25f);
			setCircleCollider(16);
			setVelocity(velocity);
		}
		if (size == 16)
		{
			setScale(.125f,.125f);
			setCircleCollider(8);
			setVelocity(velocity);
		}
		sizeVar = size;
	}


	@Override public void update(float dt)
	{

		GameObject playerShip = ObjectManager.getGameObjectByName("PlayerShip");

		Vec2 vector = new Vec2();
		vector.setX(0 - getPositionX());
		vector.setY(0 - getPositionY());

		setPositionX(getPositionX() + vector.getX() * speed);
		setPositionY(getPositionY() + vector.getY() * speed);
		vector.normalize();
		checkWrap();
	}

	@Override public void collisionReaction(GameObject other)
	{
		int velocityrandX;
		int velocityrandY;
		Vec2 velocity;

		Vec2 negNeg = new Vec2(PFRandom.randomRange(100,150)*-1, PFRandom.randomRange(100,150)*-1);
		Vec2 posPos = new Vec2(PFRandom.randomRange(100,150),PFRandom.randomRange(100,150));
		Vec2 posNeg = new Vec2(PFRandom.randomRange(100,150),PFRandom.randomRange(100,150)*-1);
		Vec2 negPos = new Vec2(PFRandom.randomRange(100,150)*-1,PFRandom.randomRange(100,150));

		Vec2 randomVelocity[] = new Vec2[4];
		randomVelocity[0] = negNeg;
		randomVelocity[1] = posPos;
		randomVelocity[2] = posNeg;
		randomVelocity[3] = negPos;

		int randVelChoose = PFRandom.randomRange(0,2);
		int randVelChoose2 = PFRandom.randomRange(2,4);

		if (other.getName().equals("PlayerShip"))
		{
			kill();
		}

		if (other.getName().equals("Bullet"))
		{

			kill();
			if (sizeVar == 128 && !Broken)
			{
				levelRef.changeScore(levelRef.getScore()+100);
				Broken = true;

				velocityrandX = PFRandom.randomRange(100,150)*-1;
				velocityrandY = PFRandom.randomRange(100,150);
				velocity = new Vec2(velocityrandX,velocityrandY);
				GameObject asteroid1 = new Asteroid(32,velocity ,levelRef);
				asteroid1.setPosition(getPosition());

				velocityrandX = PFRandom.randomRange(100,150);
				velocityrandY = PFRandom.randomRange(100,150)*-1;
				velocity = new Vec2(velocityrandX,velocityrandY);
				GameObject asteroid2 = new Asteroid(32, velocity,levelRef);
				asteroid2.setPosition(getPosition());

				velocityrandX = PFRandom.randomRange(100,150);
				velocityrandY = PFRandom.randomRange(100,150);
				velocity = new Vec2(velocityrandX,velocityrandY);
				GameObject asteroid3 = new Asteroid(32, velocity,levelRef);
				asteroid3.setPosition(getPosition());

				velocityrandX = PFRandom.randomRange(100,150)*-1;
				velocityrandY = PFRandom.randomRange(100,150)*-1;
				velocity = new Vec2(velocityrandX,velocityrandY);
				GameObject asteroid4 = new Asteroid(32, velocity ,levelRef);
				asteroid4.setPosition(getPosition());
			}

			if(sizeVar == 32 && !Broken)
			{
				levelRef.changeScore(levelRef.getScore()+100);
				Broken = true;
				kill();
				GameObject asteroid1 = new Asteroid(16,randomVelocity[randVelChoose],levelRef);
				asteroid1.setPosition(getPosition());
				GameObject asteroid2 = new Asteroid(16,randomVelocity[randVelChoose2],levelRef);
				asteroid2.setPosition(getPosition());
			}
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