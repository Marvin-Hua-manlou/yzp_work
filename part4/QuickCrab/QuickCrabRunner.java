import java.awt.Color;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * 测试的Runner，多声明相应的对象来观察功能(在特定的位置)，我这里主要在 </br>
 * 左上角和有下角各有一个单元明显的测试
 */
public class QuickCrabRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 5), new Rock());//声明各种对象类型来测试
        world.add(new Location(5, 4), new Rock());
        world.add(new Location(9, 7), new Rock());
        world.add(new Location(7, 3), new Rock());
        world.add(new Location(7, 8), new Flower());
        world.add(new Location(2, 2), new Flower());
        world.add(new Location(3, 5), new Flower());
        world.add(new Location(3, 8), new Flower());
        world.add(new Location(6, 5), new Bug());//辅助一个或者两个bug.
        world.add(new Location(5, 3), new Bug());
        world.add(new Location(4, 5), new QuickCrab(Color.RED));
        world.add(new Location(9, 8), new QuickCrab(Color.GREEN));
        //world.add(new Location(7, 4), new CrabCritter());
        world.show();
    }
}