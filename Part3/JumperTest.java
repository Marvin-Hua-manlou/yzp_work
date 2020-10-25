import java.util.Scanner;
import org.junit.jupiter.api.Test;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

class JumperTest {

	@Test
	void test() {
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
		 world.show();
		 System.out.print("The program compiled and ran successfully!!");
		 System.out.println();
	}

}
