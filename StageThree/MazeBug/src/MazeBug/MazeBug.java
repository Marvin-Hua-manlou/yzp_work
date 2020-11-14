package MazeBug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.MazeFlower;//��info.gridworld.actor��������һ��ר�õ�MazeFLower������ʾ·�����Ƚ�����
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
//import java.util.Random;
import java.util.Set;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *<code>MazeBug</code>�������ҵ�һ������bug�������ڼ�Ŀ��ܵ�����·����ʹ�õ��㷨����������㷨. <br />
 * ����һ������Bug�����չ�࣬��Ҫʵ�ֵĹ�����������������㷨ִ��һ���߳��Թ������ӣ�ע��ʹ���˸��ʹ��ƺ���
 */
/**
 * ���㷨��Ӧ�޻�·�Թ������ṹ��
	�Ƚ��������нڵ���Ϊ��δ���ʡ�״̬��
	�����ʼ�ڵ㣬����ʼ�ڵ���Ϊ���ѷ��ʡ�״̬��
	����ʼ�ڵ���ջ��
	��ջ�ǿ�ʱ�ظ�ִ�����²��裺
	
	a. ȡ��ǰջ���ڵ㡣
	b. �����ǰջ���ڵ��ǽ����ڵ㣨�Թ����ڣ�������ýڵ㣬����������
	c. �����ǰջ���ڵ���ڡ�δ���ʡ�״̬���ڽӽڵ㣬��ѡ��һ��δ���ʽڵ㣬��Ϊ���ѷ��ʡ�״̬����������ջ����������a��
	d. �����ǰջ���ڵ㲻���ڡ�δ���ʡ�״̬���ڽӽڵ㣬��ջ���ڵ��ջ����������a��
 */
public class MazeBug extends Bug {
	public Location next;//��¼λ�õı������ֱ���֮ǰ��һ��λ�úͿ��ܵ�֮���һ��λ��
	public Location last;
	public boolean isEnd = false;//�Ƿ񵽴��˺�ɫ��Rock
	public Stack<Location> crossLocation = new Stack<Location>();//������Žڵ�״̬��ջ
	public Set<Location> MazeFlowerLocations = new HashSet<Location>();//�������MazeFlowerλ�õ�һ����ϣ��
	public Integer stepCount = 0;//���㲽��
	boolean hasShown = false;

	/**
	 * ���캯��������������Ŀ����ڣ����������Ͻǵ�λ�ã�ָ����ɫΪ<code>Location.YELLOW</code> </br>
	 * ����һ��MazeBug�����Ͷ���
	 */
	public MazeBug() {
		setColor(Color.YELLOW);
		last = new Location(0, 0);
	}

	/**
	 * ����act()�������ƶ������ܵ�λ�õ���һ��λ��
	 */
	public void act() {
		isEnd = reachRedRock();//��ʼ����reachRedRock()����һ��boolean�ı������ͣ������ж��Ƿ񵽴�ĩβָ����λ��
		if (isEnd) {
		//���������ĩβ��λ�ã�չʾ����֮ǰ��·��	
			if (!hasShown) {
				String massege = stepCount.toString() + " steps";
				showPath();
				JOptionPane.showMessageDialog(null, massege);
				hasShown = true;
			}
		}
		else {//���˵û�е���ָ����λ�ã�Ҳ����RedRock�Ļ�
			ArrayList<Location> temp = getUnvisitedLocation();//���û�з��ʹ���λ���б�
			if (temp.size() == 0) {//������δ���ʵ����ڽڵ㣬��ջ
				next = crossLocation.pop();
				move();//Ѱ����һ���ڵ�
			}
			else {//����������ڵ�δ�����ʵĽڵ�Ļ�
				crossLocation.push(getLocation());//ѡ��δ�����ʵĽڵ㣬��ջ���ұ�ǽڵ�״̬Ϊ�Ѿ�����
				next = selectLocation(SelectDirection(crossLocation), temp);
				move();
				
			}
			//ÿһ��ִ���ƶ�����֮����ֵ����
			stepCount++;
		} 
	}
	
	/**
	 * ����move()��������move()��������Ϊ�հ�����ͺ��ˣ�һ������֮ǰ�߹��ĵط�����һ��С�������������Ϊ��֮ǰ��һ���ģ�����Ҳ������
	 * act()����ֱ�ӵ���super.move()����������
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} 
//		else if(gr.get(next) instanceof Rock) {//
//			gr.get(next).removeSelfFromGrid();
//			setDirection(getLocation().getDirectionToward(next));
//			moveTo(next);
//		}
		else  
			removeSelfFromGrid();
		MazeFlower MazeFlower = new MazeFlower(getColor());
		MazeFlower.putSelfInGrid(gr, loc);
		MazeFlowerLocations.add(loc);
	}
	

	//�ж��Ƿ񵽴��ɫ��ʯͷ�ĺ�������һ�������õľ���ķ���
	public boolean reachRedRock() {
		ArrayList<Location> locs = getValidAdjacentLocations();//��õ�ǰ�Ŀ��õ�λ�õĶ���
		Grid<Actor> gr = getGrid();
		if (locs.size() == 0) {//���˵û�п����õ�λ�õĻ�
			return false;
		}
		for (Location temp: locs) {//�ھ���ʯͷ�����Ǻ�ɫ�Ļ�
			if ((gr.get(temp) instanceof Rock) && (gr.get(temp).getColor()).equals(Color.RED)) {
				return true;
			}
		}
		return false;//����Ļ�������һ��false�ĳ���
	}
	
	//Ѱ����getValidAdjacentLocations()�������в������ʹ��Ľڵ�λ��
	public ArrayList<Location> getUnvisitedLocation() {//Ѱ���ڽ�δ���ʽڵ�λ��
		ArrayList<Location> locs = getValidAdjacentLocations();
		ArrayList<Location> unvisited = new ArrayList<Location>();//����һ���յ�λ�õĶ���
		Grid<Actor> gr = getGrid();
		if (locs.size() == 0) {
			return unvisited;
		}
		for (Location temp:locs)  {//������ͼ��ÿһ��λ�ã����˵��λ����һ�����õ�λ�ã����뵽Unvisited���е���
			if (!(gr.get(temp) instanceof Rock) && !(gr.get(temp) instanceof MazeFlower)) {
			    unvisited.add(temp);
			}
		}
		return unvisited;//����һ�����յĶ���
	}
	
	//��ö����ϱ�4������Ŀ��ܵ�λ��
	 public ArrayList<Location> getValidAdjacentLocations()//��õ�ǰ��λ�ÿ��õ�λ��
     {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        int d = Location.NORTH;
        for (int i = 0; i < Location.FULL_CIRCLE / Location.RIGHT; i++)//forѭ������4�ε�ת���λ�ã���Ϊ�����ϱ�����4���涨�ķ���
        {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
            d = d + Location.RIGHT;
        }
        return locs;
     }
	 
	 //��ӡ�����ǵ������ҵ��ĳ��Թ���·��
	 public void showPath() {
		 if (MazeFlowerLocations.size() == 0 || crossLocation.size() == 0) {//���˵��ǰ���κ�һ��λ��(������һ��������˵���ܵ���һ�������λ�ã����Ǿ���Ҫ
			 return;													//ִ�и���䣬size == 0˵�������ܳ��ֺ����λ��
		 }
		 //���֮ǰ�����ڳ��Ӻ�����ֵĻ���
		 Grid<Actor> gr = getGrid();
		 for (Location temp:MazeFlowerLocations) {
			 gr.get(temp).removeSelfFromGrid();
		 }
		 //�����������Թ���·��������ÿһ��Bug����ʾ������·��
		 for (Location temp:crossLocation) {
			 gr.put(temp, new MazeFlower(Color.red));//��ӡ����ǰ��һ��crossLocation��·��
		 }
		 setColor(Color.red);//�����ĺ�ɫ·����ӡ����
	 }
	 
	 
	 
	 public int[] SelectDirection(Stack<Location> crossPath) {//ת��ķ����ջ
		 //Location.NORTH Location.EAST Location.SOUTH �� Location.WEST
		 int[] direction = {1, 1, 1, 1};
		 if (crossPath.size() == 0) {//·����������ڵĻ���ֱ�ӷ��ص�ǰ�ĳ�ʼ����PATH
			 return direction;
		 }
		 else {
			 int sizeOfStack = crossPath.size();//��¼��ǰ��crossPATH�ĳ��ȣ�Ϊ����Ĵ�ӡ��һ��׼������
			 for (int i = 0; i < sizeOfStack-1; i++) {
			     int tempDir = crossPath.elementAt(i).getDirectionToward(crossPath.elementAt(i+1));
			     if (tempDir == 0) {
			    	 direction[0]++;//�����ϱ��ĸ���λ�õ�ͶƱ������ͶƱ�Ķ��٣����ǽ���һ��Ԥ��Ĺ���
			     }
			     if (tempDir == 90) {
			    	 direction[1]++;
			     }
			     if (tempDir == 180) {
			    	 direction[2]++;
			     }
			     if (tempDir == 270) {
			    	 direction[3]++;
			     }
			 }
			 int tempDir = crossPath.elementAt(sizeOfStack-1).getDirectionToward(this.getLocation());
			 if (tempDir == 0) {
		    	 direction[0]++;//���һ���ĵ����жϵĺ�������Ҫʵ�ֵ������ǣ�������Ҫ��ĩβ����¼һ�����ܵ�ת���λ�ã�����һ��������ͶƱ
		     }
		     if (tempDir == 90) {
		    	 direction[1]++;
		     }
		     if (tempDir == 180) {
		    	 direction[2]++;
		     }
		     if (tempDir == 270) {//
		    	 direction[3]++;
		     }
		     return direction;
		 }
	 }
	 
	 public  Location selectLocation(int[] dirs, ArrayList<Location> locs) {
		 if (locs.size() == 1)
			 return locs.get(0);
		 Location temp = locs.get(0);//
		 for (Location i : locs) {
			 int index = this.getLocation().getDirectionToward(i)/90;//Location��ÿ����׼������90drgee��һ���Ȳ�������ʽ
			 int index2 = this.getLocation().getDirectionToward(temp)/90;
			 if (dirs[index] > dirs[index2]) {
				 temp = i;
			 }
		 }
		 return temp;
	 } 
}


