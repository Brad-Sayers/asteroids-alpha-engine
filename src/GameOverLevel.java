import edu.digipen.Game;
import edu.digipen.InputManager;
import edu.digipen.level.GameLevelManager;
import edu.digipen.text.FontTypes;
import edu.digipen.text.TextObject;

import java.awt.event.KeyEvent;

public class GameOverLevel extends ArenaLevel
{
	ArenaLevel levelref;
	@Override public void create()
	{
		TextObject Gameover = new TextObject("gameover","Game Over!", FontTypes.ARIAL_32);
		Gameover.setPosition(-150,0);
		TextObject Score = new TextObject("score","You Scored: " + ArenaLevel.score +" points" ,FontTypes.ARIAL_32);
		Score.setPosition(-150,-32);
		TextObject Restart = new TextObject("restart","Press R to play again or E to exit.",FontTypes.ARIAL_32);
		Restart.setPosition(-150,-64);
	}

	@Override public void initialize()
	{

	}

	@Override public void update(float v)
	{
		if (InputManager.isTriggered(KeyEvent.VK_R))
		{
			GameLevelManager.goToLevel(new MainMenu());
			ArenaLevel.score = 0;
		}
		if(InputManager.isTriggered(KeyEvent.VK_E))
		{
			Game.quit();
			Game.destroy();
		}
	}

	@Override public void uninitialize()
	{

	}
}
