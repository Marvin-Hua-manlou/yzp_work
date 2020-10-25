//import info.gridworld.actor.Actor;
//import java.awt.Color;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
/**
 * ������һ��ѡ����ص�ѡ����ʵ��һ��ͼ������<code>ActorWorld</code> �����/br>
 * <code>addGridClass</code>����
 */
public class SparseGridRunner
{
    public static void main(String[] args)
    {
    	final int[] direction = {1,1,1,2,0,0,2,0,0,2,1,1};
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");//����֮ǰ��ActorWorld�ķ�����������
        world.addGridClass("SparseBoundedGrid2");//ʵ�ַ�ʽ��ͬ��һ����
        world.addGridClass("UnboundedGrid");//����������ʵ��
        world.addGridClass("UnboundedGrid2");
        world.addGridClass("BoundedGrid");
        world.add(new Location(2, 4), new DancingBug(direction));//�ں����λ�÷���һ������
        world.show();
    }
}
