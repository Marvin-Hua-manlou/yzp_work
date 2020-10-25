import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.awt.Color;//�����
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 *��չ���ǵ� <code>Critter</code> �࣬��ʵ��һ���������ܹ���ڵ���
 */
public class ModifiedChameleonCritter extends Critter
{
	private static final double DARKENING_FACTOR = 0.10; //���볣��������RGB
    /**
     * ���ѡ��һ���ڽ��Ķ�����ɫ�ı�����ģ����û�еĻ������Ǿͱ�� </br>
     * �ú�����Ҫģ��֮ǰ�� <code>ChameleonCritter</code> ����Ϊ
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0) {
        	change_dark();//�ӱ�Ҳ��� 
        	return;
        }  
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());//������õ���ɫ
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc)); //������ķ������ó�Ϊloc����λ�õķ���
        super.makeMove(loc);
    }
   /*
    * �˺���������� <code>Flower</code> �ı�ҵĲ��裬Ҳ�����𽥼���RGB����ֵ </br>
    * ���ź�ɫ�仯
    */
    private void change_dark()
    {
	     Color color = getColor();
	     int red = (int) (color.getRed() * (1 - DARKENING_FACTOR));//�������������ô�һ�㣬�仯����
	     int green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
	     int blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
	     setColor(new Color(red, green, blue)); 
    }
}
