import java.util.Scanner;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
//import java.util.Scanner; 

//≤‚ ‘Jumper
public class JumperRunner
{
	public static void main(String[] args)
	{
		 @SuppressWarnings("resource")
		 Scanner src = new Scanner(System.in);
		 System.out.print("Please iput a number stands for how many Rocks you want:");
		 System.out.println();
		 int len = src.nextInt();
		 ActorWorld world = new ActorWorld();
		 for(int i = 0; i < len; i++) {
			 world.add(new Rock());
		 }
		 world.add(new Jumper());
		 world.add(new Bug());
		 world.add(new Flower());
//		 int[] direction = {1,1,1,2,0,0,2,0,0,2,1,1};
//		 DancingBug alice = new DancingBug(direction);
//		 world.add(new Location(3, 4), alice);
		 world.show();
	}
}