package MazeBug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * 测试MazeBug类的测试函数类. <br />
 * 初始化一个简单的例子，能够大致看清楚深度搜索的思路
 */
public class MazeBugRunner
{
    public static void main(String[] args)
    {
        
        ActorWorld world = new ActorWorld(); //加入测试的样例
        world.add(new Location(0,0), new MazeBug());
        world.add(new Location(1,0),new Rock());
        world.add(new Location(1,1),new Rock());//岩石放置，设施一个简单的例子
        world.add(new Location(1,2),new Rock());
        world.add(new Location(1,3),new Rock());/*如何放置*/
        world.add(new Location(1,4),new Rock());
        world.show();
    }
}