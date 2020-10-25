import info.gridworld.actor.Actor;
//import info.gridworld.actor.Critter;
//import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.util.ArrayList;

public class ChameleonKid extends ModifiedChameleonCritter//在前面或者是后面的对象更改
{
	 public ArrayList<Actor> getActors(){
		 ArrayList<Actor> actors = new ArrayList<Actor>();//设置一个arraylist。
		 int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };//在头部或者是在后面的的时候变色。
		 for (Location loc : getLocationsInDirections(dirs)){
			 Actor a = getGrid().get(loc);//获得当前的位置
			 if (a != null)
				 actors.add(a);
		 }
		 return actors;//得到的队列返回
	 } 
	 public ArrayList<Location> getLocationsInDirections(int[] directions)//获得可能的方向的位置队列
	 {
		 ArrayList<Location> locs = new ArrayList<Location>();
		 Grid<Actor> gird = getGrid();
		 Location loc = getLocation();//获得当前的位置
		
		 for (int d : directions){//遍历队列得到可能的位置
			 Location neighborloc = loc.getAdjacentLocation(getDirection() + d);
			 
			 if (gird.isValid(neighborloc))//合理就加入到队列
				 locs.add(neighborloc);
		 }
		 return locs;//返回队列
	 }
}
