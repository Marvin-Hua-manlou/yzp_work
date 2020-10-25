import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;
/**
 * 测试 <code>Runner</code> 类，声明一个 <code>Rock</code> 和一个<code>Critter</code> </br>
 * 让我们能看清变黑的过程
 */
public class ModifiedChameleonRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
//        world.add(new Location(7, 8), new Rock());//原来的部署
//        world.add(new Location(3, 3), new Rock());
//        world.add(new Location(2, 8), new Rock(Color.BLUE));
//        world.add(new Location(5, 5), new Rock(Color.PINK));
//        world.add(new Location(1, 5), new Rock(Color.RED));
//        world.add(new Location(7, 2), new Rock(Color.YELLOW));
//        world.add(new Location(4, 4), new ChameleonCritter());
        //world.add(new Location(5, 8), new ChameleonCritter());
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.add(new Location(4, 4), new ModifiedChameleonCritter());
        world.show();
    }
}