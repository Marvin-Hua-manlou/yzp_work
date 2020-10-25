//import info.gridworld.actor.Actor;
//import java.awt.Color;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
/**
 * 这个类给一个选择加载的选项来实现一个图，调用<code>ActorWorld</code> 里面的/br>
 * <code>addGridClass</code>方法
 */
public class SparseGridRunner
{
    public static void main(String[] args)
    {
    	final int[] direction = {1,1,1,2,0,0,2,0,0,2,1,1};
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");//利用之前的ActorWorld的方法来加载类
        world.addGridClass("SparseBoundedGrid2");//实现方式不同的一个类
        world.addGridClass("UnboundedGrid");//按照类名字实现
        world.addGridClass("UnboundedGrid2");
        world.addGridClass("BoundedGrid");
        world.add(new Location(2, 4), new DancingBug(direction));//在合理的位置放置一个对象
        world.show();
    }
}
