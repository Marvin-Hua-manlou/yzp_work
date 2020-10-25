import info.gridworld.actor.Actor;
//import info.gridworld.actor.Critter;
//import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.util.ArrayList;

public class ChameleonKid extends ModifiedChameleonCritter//��ǰ������Ǻ���Ķ������
{
	 public ArrayList<Actor> getActors(){
		 ArrayList<Actor> actors = new ArrayList<Actor>();//����һ��arraylist��
		 int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };//��ͷ���������ں���ĵ�ʱ���ɫ��
		 for (Location loc : getLocationsInDirections(dirs)){
			 Actor a = getGrid().get(loc);//��õ�ǰ��λ��
			 if (a != null)
				 actors.add(a);
		 }
		 return actors;//�õ��Ķ��з���
	 } 
	 public ArrayList<Location> getLocationsInDirections(int[] directions)//��ÿ��ܵķ����λ�ö���
	 {
		 ArrayList<Location> locs = new ArrayList<Location>();
		 Grid<Actor> gird = getGrid();
		 Location loc = getLocation();//��õ�ǰ��λ��
		
		 for (int d : directions){//�������еõ����ܵ�λ��
			 Location neighborloc = loc.getAdjacentLocation(getDirection() + d);
			 
			 if (gird.isValid(neighborloc))//����ͼ��뵽����
				 locs.add(neighborloc);
		 }
		 return locs;//���ض���
	 }
}
