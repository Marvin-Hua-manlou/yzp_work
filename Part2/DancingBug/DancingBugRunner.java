import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class DancingBugRunner
{
 public static void main(String[] args)
 {
	 ActorWorld world = new ActorWorld();
	 int[] direction = {1,1,1,2,0,0,2,0,0,2,1,1};
	 DancingBug alice = new DancingBug(direction);
	 world.add(new Location(3, 4), alice);
	 world.show();
 }
}