import edu.digipen.gameobject.GameObject;
import edu.digipen.math.Vec2;

public class Bullet extends GameObject
{
	public Bullet()
	{
		super("Bullet", 16, 16, "CircleSmall.png");
		setCircleCollider(8);
	}
	float speed = 250.0f;

	public void fire(Vec2 direction)
	{
		setVelocity(Vec2.scale(direction,speed));
	}

	@Override public void update(float dt)
	{
		if (isInViewport() == false)
		{
			kill();
		}
	}
	@Override public void collisionReaction(GameObject other)
	{
		if (other.getName().equals("asteroid"))
		{
			kill();
		}
	}

}
