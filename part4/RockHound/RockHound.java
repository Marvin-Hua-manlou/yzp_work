import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import java.awt.Color;
import java.util.ArrayList;

/*
 * �ú����Ĺ������Ƴ���ǰ�� <code>Rock</code>
 */
public class RockHound extends Critter
{
	RockHound(Color color){
		setColor(color);
	}
	public void processActors(ArrayList<Actor> actors){
		 for (Actor a : actors){//�ڶ��м���
			 if (a instanceof Rock)//ʯͷ�Ļ����Ƴ��ͺ���
				 a.removeSelfFromGrid(); 
		 }
	}
}