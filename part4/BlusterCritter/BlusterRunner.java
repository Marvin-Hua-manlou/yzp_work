import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;//������Ҫ�õ��ı���

import java.awt.Color;

/**
 * This class runs a world that contains critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BlusterRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(3, 4), new Flower());//�����������͡�
        world.add(new Location(3, 3), new Flower());
        world.add(new Location(3, 5), new Flower());//���������һ��(��Ҳ���Է��Ǹ�һ��for�����ƶ���)��
        world.add(new Location(4, 3), new Flower());
        world.add(new Location(4, 5), new Flower());
        world.add(new Location(5, 3), new Flower(Color.BLUE));//��Ĭ�Ϲ��캯����
        world.add(new Location(5, 5), new Flower(Color.PINK));
        world.add(new Location(5, 4), new Flower());
        world.add(new Location(4, 4), new BlusterCritter(10,Color.RED));
        //world.add(new Location(4, 6), new BlusterCritter(2,Color.GREEN));
        //world.add(new Location(5, 8), new Critter());
        world.show();
    }
}