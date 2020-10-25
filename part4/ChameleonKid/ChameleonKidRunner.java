import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;
/**
 * 对应的Kid类的Runner函数，特意设置一个前后的位置的Actor，来测试单元功能
 */
public class ChameleonKidRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();//声明一个世界
//        world.add(new Location(7, 8), new Rock());//原来的部署
//        world.add(new Location(3, 3), new Rock());
//        world.add(new Location(2, 8), new Rock(Color.BLUE));
//        world.add(new Location(5, 5), new Rock(Color.PINK));
//        world.add(new Location(1, 5), new Rock(Color.RED));
//        world.add(new Location(7, 2), new Rock(Color.YELLOW));
//        world.add(new Location(4, 4), new ChameleonCritter());
        //world.add(new Location(5, 8), new ChameleonCritter());
        world.add(new Location(3, 4), new Rock(Color.YELLOW));
        world.add(new Location(4, 6), new Rock(Color.RED));
        world.add(new Location(4, 4), new ModifiedChameleonCritter());//我们为了测试找一个特定的位置和对象
        world.show();
    }
}