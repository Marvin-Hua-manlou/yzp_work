import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
//import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

//һ��������ʯͷ��������ȥ
public class Jumper extends Actor{
	//private Location location;
    //private Color color;
	 
	//��ʼ����ɫ
	 public Jumper(){
		 setColor(Color.GREEN);
	 }
	 
	 //������ɫ
	 public Jumper(Color Color_of_Jumper){
		 setColor(Color_of_Jumper);
	 }
	 
	 //����act����
	 public void act(){
		 if (canJump_to_next())
			 jump_to_next();
		 else
			 turns();
	 }
	 
	 //ģ��֮ǰBug�Ķ���������������ת
	 public void turns(){
		 setDirection(getDirection() + Location.HALF_LEFT);
	 } 
	 
	 //��������һ��λ�ó��ֵĶ���Ӧ�������������ӡ�
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
		 //ȥ���������ֵ���Ϊ
	 }
	 
	 //�ж��Ƿ���������һ��λ��
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