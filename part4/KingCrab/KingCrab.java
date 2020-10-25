import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class KingCrab extends CrabCritter{
	//���캯���õ�һ����ɫ�ĳ�ʼ��
	 public KingCrab(Color c){
		 setColor(c);
	 }
	 //��õ�ǰ�����𽥵ĵѿ������뵥λ
	 public int distance(Location loc1, Location loc2){
		 int x1 = loc1.getRow();
		 int y1 = loc1.getCol();//loc1������
		 int x2 = loc2.getRow();
		 int y2 = loc2.getCol();//loc2������λ��
		 double dist = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
		 return (int)Math.floor(dist);//ȡ��
	 }
	//�ܹ��ƶ��Ŀ��ܺ��ƶ���������
	 private boolean move(Actor alice){
		 ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(alice.getLocation());//��¼�ռ����
		 for(Location loc:locs){//�����õ����ܵ�λ��
			 if(distance(getLocation(), loc) > 1){//faraway����ʾԶ��->�������ô���һ�ͺ���
				 alice.moveTo(loc);//�ƶ��ͺ���
				 return true;
			 }
		 }
		//��������û�к��ʵ�λ�ã����ش���
		 return false;
	 }
	
	 public void processActors(ArrayList<Actor> actors){//processActors������ִ�ж���������act()��
		 for (Actor alice : actors){
			 	move(alice);//���ԵĻ����ƶ�
			 if (!move(alice)){//�����Ƴ��Լ�
				 alice.removeSelfFromGrid(); 
			 }
		 }
	 }
}