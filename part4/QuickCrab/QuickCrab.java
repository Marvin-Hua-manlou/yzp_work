import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter{//���캯������һ����ɫ
	 public QuickCrab(Color c){
		 setColor(c);
	 }
	 /*
	  * ��ÿ��Ե��������λ�õ���Ϣ������һ������
	  */
	 public ArrayList<Location> getMoveLocations(){
		 ArrayList<Location> locs = new ArrayList<Location>();
	
		 moveTwoStep(locs,getDirection() + Location.LEFT);//��������ƶ��ĺ�����ע��������
		 moveTwoStep(locs,getDirection() + Location.RIGHT);
	
		 if (locs.size() == 0)
			 return super.getMoveLocations();
	
		 return locs;
	 }
	
	 private void moveTwoStep(ArrayList<Location> locs,int dir){//�ƶ������ĺ�������
		 Grid<Actor> g = getGrid();
		 Location loc = getLocation();//��õ�ǰλ��
		 Location temp = loc.getAdjacentLocation(dir);//���ݽǶ�ֵ��÷�λ
		
		 if(g.isValid(temp) && g.get(temp) == null){//���˵��һ��λ�ú�������һ���ո�Ҳ����(û�ж���)�Ļ�
			 Location loc2 = temp.getAdjacentLocation(dir);//�����һ���ո���ھӿ���λ��
			 
			 if(g.isValid(loc2) && g.get(loc2)== null)//���غ����λ�ö���valid��������δ�ܳ������ޣ�get����λ��û�ж���
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