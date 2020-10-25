import info.gridworld.actor.Actor;
//import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;
public class BlusterCritter extends Critter{
	 private int numFactor;//��ǰ�������������Ŀ�������ж���Χ�����Ƕ໹����
	 //���ʱ��������ٵ�ʱ��䰵
	
	 public BlusterCritter(int c,Color col){//���캯��
		 super();
		 numFactor = c; //����
		 setColor(col);//��ɫ
	 }
	 
	 //getActor()���������������Χ�Ķ����һ��list(�������)�������ڷ��ؿ�ֵ
	 public ArrayList<Actor> getActors(){
		 ArrayList<Actor> actors = new ArrayList<Actor>();
		
		 Location loc = getLocation();//��õ�ǰ��λ��
		 for(int r = loc.getRow() - 2; r <= loc.getRow() + 2; r++ ){//��Χ������Ԫ�ķ�Χ����
			 for(int c = loc.getCol() - 2; c <= loc.getCol() + 2; c++){
				 Location tempLoc = new Location(r,c);
				 
				 if(getGrid().isValid(tempLoc)){//�ж�����Թ������Ԫ�ĺ�����
					 Actor a = getGrid().get(tempLoc);
					 if(a != null && a != this)
						 actors.add(a);
				 }
			 }
		 }
		 return actors;
	 }
	 
	 public void processActors(ArrayList<Actor> actors){
		 int count = 0;
		 for(Actor a: actors)
			 if(a instanceof Flower)//�����һ�£�����ĿҪ�������ĳɻ�������Ϊ�������ɾ���Ҫ�ƶ������ù۲졣
				 count++;
		 if(count < numFactor)//ִ������ɫ��
			turn_light();
		 else
			 turn_dark();
	 }
	 
	 private void turn_dark(){//�䰵�ĺ���
		 Color c = getColor();
		 int red = c.getRed();
		 int green = c.getGreen();
		 int blue = c.getBlue();
		 if(red > 0) red -= 5;//��ȴ�һ�㣬�仯������
		 if(green > 0) green -= 5;
		 if(blue > 0) blue -= 5;//��ȻRGB���ó�Ϊ5Ҳ���ܱ�����������������Ҫ���������ٴ�һЩ
		 setColor(new Color(red, green, blue));
	 }
	 
	 private void turn_light(){//��������
		 Color color = getColor();
		 int red = color.getRed();//���RGBֵ,�����ٴ�һ�㣬�仯�Ƚ�����
		 int green = color.getGreen();
		 int blue = color.getBlue();
		 if(red < 255) red += 5;//�𽥼Ӵ�RGB��
		 if(green < 255) green += 5;
		 if(blue < 255) blue += 5;
		 setColor(new Color(red, green, blue)); //���RGB��ֵ
	}
}