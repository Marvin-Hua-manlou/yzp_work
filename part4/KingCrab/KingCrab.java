import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class KingCrab extends CrabCritter{
	//构造函数得到一个颜色的初始化
	 public KingCrab(Color c){
		 setColor(c);
	 }
	 //获得当前坐标逐渐的笛卡尔距离单位
	 public int distance(Location loc1, Location loc2){
		 int x1 = loc1.getRow();
		 int y1 = loc1.getCol();//loc1的坐标
		 int x2 = loc2.getRow();
		 int y2 = loc2.getCol();//loc2的坐标位置
		 double dist = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
		 return (int)Math.floor(dist);//取整
	 }
	//能够移动的可能和移动动作函数
	 private boolean move(Actor alice){
		 ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(alice.getLocation());//记录空间队列
		 for(Location loc:locs){//遍历得到可能的位置
			 if(distance(getLocation(), loc) > 1){//faraway：表示远离->这里设置大于一就好了
				 alice.moveTo(loc);//移动就好了
				 return true;
			 }
		 }
		//遍历结束没有合适的位置，返回错误
		 return false;
	 }
	
	 public void processActors(ArrayList<Actor> actors){//processActors函数，执行动作，类似act()。
		 for (Actor alice : actors){
			 	move(alice);//可以的话就移动
			 if (!move(alice)){//否则移除自己
				 alice.removeSelfFromGrid(); 
			 }
		 }
	 }
}