import java.awt.Color;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * ���Ե�Runner����������Ӧ�Ķ������۲칦��(���ض���λ��)����������Ҫ�� </br>
 * ���ϽǺ����½Ǹ���һ����Ԫ���ԵĲ���
 */
public class QuickCrabRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 5), new Rock());//�������ֶ�������������
        world.add(new Location(5, 4), new Rock());
        world.add(new Location(9, 7), new Rock());
        world.add(new Location(7, 3), new Rock());
        world.add(new Location(7, 8), new Flower());
        world.add(new Location(2, 2), new Flower());
        world.add(new Location(3, 5), new Flower());
        world.add(new Location(3, 8), new Flower());
        world.add(new Location(6, 5), new Bug());//����һ����������bug.
        world.add(new Location(5, 3), new Bug());
        world.add(new Location(4, 5), new QuickCrab(Color.RED));
        world.add(new Location(9, 8), new QuickCrab(Color.GREEN));
        //world.add(new Location(7, 4), new CrabCritter());
        world.show();
    }
}