import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;//���ö�����
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
//import java.util.Scanner;  

//import java.awt.Color;

public class SpiralBugRunner
{
    public static void main(String[] args)
    {
    	UnboundedGrid<Actor> ans = new UnboundedGrid<Actor>();
    	ActorWorld world = new ActorWorld(ans);
    	
        SpiralBug alice = new SpiralBug(1);//��ʼ�߳�Ϊһ
        //alice.setColor(Color.ORANGE);
        //SpiralBug bob = new SpiralBug(2);
        world.add(new Location(17,17), alice);//��ʼλ�����꣨17,17���պ��ڻ�������
        //world.add(new Location(5, 5), bob);
        world.show();
    }
}