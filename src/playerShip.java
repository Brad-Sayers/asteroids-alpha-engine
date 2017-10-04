import edu.digipen.InputManager;
import edu.digipen.SoundManager;
import edu.digipen.gameobject.GameObject;
import edu.digipen.gameobject.ObjectManager;
import edu.digipen.graphics.Graphics;
import edu.digipen.level.GameLevelManager;
import edu.digipen.math.Vec2;

import java.awt.event.KeyEvent;

public class playerShip extends GameObject
{
	int timer = 0;
	float timer2;
	int lifeCount = 0 ;
	public Vec2 direction = new Vec2(1.0f,0.0f);

	public playerShip()
	{
		super("PlayerShip",64,64,"Ship.png");
		setRectangleCollider(32,32);
		setZOrder(1000);
	}

	@Override public void update(float dt)
	{
		float speed = 10f;
		float rotSpeed = 5f;
		float radians = (float) Math.toRadians(getRotation());
		direction.setX((float) Math.cos(radians));
		direction.setY((float) Math.sin(radians));


		timer++;
		if (timer / 60 <= 5)
		{

			timer = 0;
		}



		if (InputManager.isPressed(KeyEvent.VK_W))
		{
			addForce(Vec2.scale(direction,speed),0.064f);
		}

		if (InputManager.isPressed(KeyEvent.VK_S))
		{
			addForce(Vec2.scale(direction,-speed),0.064f);
		}

		if (InputManager.isPressed(KeyEvent.VK_D))
		{
			setRotation(getRotation() - rotSpeed);
		}

		if (InputManager.isPressed(KeyEvent.VK_A))
		{
			setRotation(getRotation() + rotSpeed);
		}

		if (InputManager.isTriggered(KeyEvent.VK_SPACE))
		{
			Bullet bullet = new Bullet();
			bullet.fire(direction);
			ObjectManager.addGameObject(bullet);
			bullet.setPosition(getPosition());
			bullet.setZOrder(999);
			SoundManager.playSoundEffect("ShootSound");
		}
		checkWrap();
		System.out.println(lifeCount);


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
		if((getPositionX() + offset) < -halfScreenWidth)
		{
			setPositionX(halfScreenWidth);
		}
		if((getPositionY() - offset) > halfScreenHeight)
		{
			setPositionY(-halfScreenHeight);
		}
		if((getPositionY() + offset) < -halfScreenHeight)
		{
			setPositionY(halfScreenHeight);
		}
	}
	@Override
	public void collisionReaction(GameObject other)
	{
		if (other.getName().equals("asteroid"))
		{
			if (lifeCount == 0)
			{
				lifeCount = 1;
				setVelocity(0, 0);
				clearForces();
				setRotation(0);
				direction.set(1, 0);
				setPosition(0,0);
			}
			else if (lifeCount == 1)
			{
				lifeCount = 2;
				setVelocity(0, 0);
				clearForces();
				setRotation(0);
				direction.set(1, 0);
				setPosition(0,0);
			}
			else
			{
				lifeCount = 3;
				setVelocity(0, 0);
				clearForces();
				setRotation(0);
				direction.set(1, 0);
				setPosition(0,0);
			}
		}
		if (lifeCount == 3)
		{
			ObjectManager.removeAllObjects();
			GameLevelManager.goToLevel(new GameOverLevel());
			SoundManager.stopAllPlayingSounds();
		}
	}
	public void invincibleTimer(float dt)
	{
		timer2++;
		if (timer2 % 30 == 0 && timer2 % 60 != 0)
		{
			setOpacity(0.0f);
		}
		else
		{
			setOpacity(1.0f);
		}
	}
	@Override public void initialize()
	{
		SoundManager.addBackgroundSound("ShootSound", "Ship_Gun_1.wav",false);
	}
}
