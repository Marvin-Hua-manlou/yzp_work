import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter{//构造函数，给一个颜色
	 public QuickCrab(Color c){
		 setColor(c);
	 }
	 /*
	  * 获得可以到达的所有位置的信息，范湖一个队列
	  */
	 public ArrayList<Location> getMoveLocations(){
		 ArrayList<Location> locs = new ArrayList<Location>();
	
		 moveTwoStep(locs,getDirection() + Location.LEFT);//左右随机移动的函数，注意是两步
		 moveTwoStep(locs,getDirection() + Location.RIGHT);
	
		 if (locs.size() == 0)
			 return super.getMoveLocations();
	
		 return locs;
	 }
	
	 private void moveTwoStep(ArrayList<Location> locs,int dir){//移动两步的函数泛型
		 Grid<Actor> g = getGrid();
		 Location loc = getLocation();//获得当前位置
		 Location temp = loc.getAdjacentLocation(dir);//依据角度值获得方位
		
		 if(g.isValid(temp) && g.get(temp) == null){//如果说下一个位置合理，下下一个空格也合理(没有对象)的话
			 Location loc2 = temp.getAdjacentLocation(dir);//获得下一个空格的邻居可能位置
			 
			 if(g.isValid(loc2) && g.get(loc2)== null)//返回合理的位置队列valid返回坐标未能超出界限，get返回位置没有对象
				 locs.add(loc2); 
		 }
//		 else if(g.isValid(temp) && g.get(temp) == null) {
//			 Location loc2 = temp.getAdjacentLocation(dir);
//			 
//			 if(!(g.isValid(loc2) && g.get(loc2)== null))
//				 locs.add(temp); 
//		 }
	 }
}