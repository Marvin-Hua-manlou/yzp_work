import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
public class DancingBug extends Bug{
	 private int[] Direction_list_arr;
	 private int step;
	
	 public DancingBug(int[] face_to){
		 Direction_list_arr = face_to;
		 step = 0;
	 }
	 public void control_turn(int index){
		 for(int i = 1; i <= index; i++){
			 turn();
		 }
	 }

	 public void act(){
		 if(step == Direction_list_arr.length) {
			 setDirection(Location.NORTH);
			 step = 0;
		 }

		 control_turn(Direction_list_arr[step]);
		 step++;
		 super.act();
	 }
}