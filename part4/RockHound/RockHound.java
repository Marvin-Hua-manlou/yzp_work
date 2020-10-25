import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import java.awt.Color;
import java.util.ArrayList;

/*
 * 该函数的功能是移除面前的 <code>Rock</code>
 */
public class RockHound extends Critter
{
	RockHound(Color color){
		setColor(color);
	}
	public void processActors(ArrayList<Actor> actors){
		 for (Actor a : actors){//在队列检索
			 if (a instanceof Rock)//石头的话，移除就好了
				 a.removeSelfFromGrid(); 
		 }
	}
}