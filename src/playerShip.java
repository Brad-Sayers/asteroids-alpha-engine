import edu.digipen.InputManager;
import edu.digipen.gameobject.GameObject;
import edu.digipen.gameobject.ObjectManager;
import edu.digipen.graphics.Graphics;
import edu.digipen.math.Vec2;

import java.awt.event.KeyEvent;

public class playerShip extends GameObject
{
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
		if (InputManager.isPressed(KeyEvent.VK_W))
		{
			addForce(Vec2.scale(direction,speed),0.016f);
		}

		if (InputManager.isPressed(KeyEvent.VK_S))
		{
			addForce(Vec2.scale(direction,-speed),0.016f);
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
		}
		checkWrap();

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
			//kill();
			//GameLevelManager.restartLevel();
		}
	}
}
