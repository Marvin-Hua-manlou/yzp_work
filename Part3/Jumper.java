import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
//import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

//一个类遇到石头将会跳过去
public class Jumper extends Actor{
	//private Location location;
    //private Color color;
	 
	//初始化颜色
	 public Jumper(){
		 setColor(Color.GREEN);
	 }
	 
	 //给定颜色
	 public Jumper(Color Color_of_Jumper){
		 setColor(Color_of_Jumper);
	 }
	 
	 //重载act函数
	 public void act(){
		 if (canJump_to_next())
			 jump_to_next();
		 else
			 turns();
	 }
	 
	 //模拟之前Bug的动作，不过是向左转
	 public void turns(){
		 setDirection(getDirection() + Location.HALF_LEFT);
	 } 
	 
	 //控制在下一个位置出现的对象应该是跳两个格子。
	 public void jump_to_next(){
		 Grid<Actor> gird = getGrid();
		 if (gird == null)
			 return;
		 
		 Location pass = getLocation().getAdjacentLocation(getDirection());
		 Location aim_next = pass.getAdjacentLocation(getDirection());
		 
		 if (gird.isValid(aim_next))
			 moveTo(aim_next);
		 else
			 removeSelfFromGrid();
//		 Flower flower = new Flower(getColor());
//	     flower.putSelfInGrid(gr, loc);
		 //去掉花儿出现的行为
	 }
	 
	 //判断是否能跳到下一个位置
	 public boolean canJump_to_next(){
		 Grid<Actor> gird = getGrid();
		 if (gird == null)
			 return false;
		 
		 Location pass_prv = getLocation();
		 Location next_prv = pass_prv.getAdjacentLocation(getDirection());
		 if (!gird.isValid(next_prv))
			 return false;
		 
		 Location next = next_prv.getAdjacentLocation(getDirection());
		 if (!gird.isValid(next))
			 return false;
		 
		 Actor neighbor = gird.get(next);
		 return (neighbor == null) || (neighbor instanceof Flower);
//		 if (!((neighbor == null) || (neighbor instanceof Flower)|| (neighbor instanceof Rock)))
//		 		return false;
//		 	
		 
		
		 //neighbor = gr.get(twoAway);
		 
	 }
} 