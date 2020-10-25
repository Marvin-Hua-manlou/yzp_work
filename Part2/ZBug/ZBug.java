
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
/*
 * <code>ZBug</code>函数
 */
public class ZBug extends Bug
{
	 //表示<code>Z</code>能够到达的最大size
	 public static final int maxz = 100;
 	 
	 private int line_len; // 指定的Z字母的长度(也就是第一条横向边的长)
	 private int step; //记录步长
	 private int key;//记录转角(方向的重置)
	 
	//初始化，给定 <code>字母Z</code>的各个信息
	 public ZBug(int len){
		 step = 0;
		 line_len = len;
		 setDirection(Location.EAST);//初始朝东方
		 key = 1;
	 }
	 public void act(){
		 int pos = step+1;//记录位置
		 if (pos < line_len){
			 if (canMove()){
				 move();
				 step++;
			 }
		 }
		 else if (key == 1 ){//第一次转角。应该走Z的“撇”。
			 step = 0;
			 setDirection(Location.SOUTHWEST);
			 key++;
		 }
		 else if (key == 2){//第二次转角，走Z的最后一步。
			 step = 0;
			 setDirection(Location.EAST);
			 key++;
		 }
		 else {
			 ;//do nothing
			 //key = 0 重置可以连续写Z
		 }
	}
}