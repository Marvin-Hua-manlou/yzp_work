import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;
/**
 * 能够移除眼前的 <code>Rock</code>，功能多声明几个Rock来观察
 */
public class RockHoundRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());//原来的部署
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Rock(Color.WHITE));//对象的声明
        world.add(new Location(5, 5), new Rock(Color.PINK));
        world.add(new Location(1, 5), new Rock(Color.RED));//因为是一个移除石头的函数，所以我们只需要声明石头就可以了
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.add(new Location(4, 4), new RockHound(Color.RED));
        world.add(new Location(5, 8), new RockHound(Color.ORANGE));
        world.show();
    }
}