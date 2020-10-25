
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
/*
 * <code>ZBug</code>����
 */
public class ZBug extends Bug
{
	 //��ʾ<code>Z</code>�ܹ���������size
	 public static final int maxz = 100;
 	 
	 private int line_len; // ָ����Z��ĸ�ĳ���(Ҳ���ǵ�һ������ߵĳ�)
	 private int step; //��¼����
	 private int key;//��¼ת��(���������)
	 
	//��ʼ�������� <code>��ĸZ</code>�ĸ�����Ϣ
	 public ZBug(int len){
		 step = 0;
		 line_len = len;
		 setDirection(Location.EAST);//��ʼ������
		 key = 1;
	 }
	 public void act(){
		 int pos = step+1;//��¼λ��
		 if (pos < line_len){
			 if (canMove()){
				 move();
				 step++;
			 }
		 }
		 else if (key == 1 ){//��һ��ת�ǡ�Ӧ����Z�ġ�Ʋ����
			 step = 0;
			 setDirection(Location.SOUTHWEST);
			 key++;
		 }
		 else if (key == 2){//�ڶ���ת�ǣ���Z�����һ����
			 step = 0;
			 setDirection(Location.EAST);
			 key++;
		 }
		 else {
			 ;//do nothing
			 //key = 0 ���ÿ�������дZ
		 }
	}
}