import edu.digipen.InputManager;
import edu.digipen.level.GameLevel;
import edu.digipen.level.GameLevelManager;
import edu.digipen.text.FontTypes;
import edu.digipen.text.TextObject;

import java.awt.event.KeyEvent;

public class MainMenu extends GameLevel
{
	@Override public void create()
	{
		TextObject enter = new TextObject("start", "Press Enter To Start", FontTypes.ARIAL_64);
		enter.setPosition(-250,0);
	}

	@Override public void initialize()
	{

	}

	@Override public void update(float v)
	{
		if (InputManager.isTriggered(KeyEvent.VK_ENTER))
		{
			GameLevelManager.goToLevel(new ArenaLevel());
		}
	}

	@Override public void uninitialize()
	{

	}

}
