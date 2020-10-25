import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.awt.Color;//加入包
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 *扩展我们的 <code>Critter</code> 类，来实现一个功能上能够变黑的类
 */
public class ModifiedChameleonCritter extends Critter
{
	private static final double DARKENING_FACTOR = 0.10; //加入常变量调节RGB
    /**
     * 随机选择一个邻近的对象将颜色改变成它的，如果没有的话，我们就变黑 </br>
     * 该函数主要模仿之前的 <code>ChameleonCritter</code> 的行为
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0) {
        	change_dark();//加变灰部分 
        	return;
        }  
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());//随机设置的颜色
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc)); //将对象的方向设置成为loc所在位置的方向
        super.makeMove(loc);
    }
   /*
    * 此函数完成类似 <code>Flower</code> 的变灰的步骤，也就是逐渐减少RGB的数值 </br>
    * 朝着黑色变化
    */
    private void change_dark()
    {
	     Color color = getColor();
	     int red = (int) (color.getRed() * (1 - DARKENING_FACTOR));//常变量可以设置大一点，变化明显
	     int green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
	     int blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
	     setColor(new Color(red, green, blue)); 
    }
}
