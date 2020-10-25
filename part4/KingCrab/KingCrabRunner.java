import java.awt.Color;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains crab critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrabRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(8, 8), new Rock());//��������
        world.add(new Location(8, 9), new Rock());
        world.add(new Location(7, 8), new Flower());
        world.add(new Location(2, 2), new Flower());//������ʯͷ���ǿ����ƶ���
        world.add(new Location(0, 0), new Rock());
        world.add(new Location(3, 5), new Flower());
        world.add(new Location(4, 5), new KingCrab(Color.BLUE));//�༸����λ����з���ۿ�����
        world.add(new Location(1, 0), new KingCrab(Color.GREEN));
        world.add(new Location(9,8), new KingCrab(Color.white));
        world.show();
    }
}