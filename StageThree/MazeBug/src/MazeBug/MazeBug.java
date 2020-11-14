package MazeBug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.MazeFlower;//在info.gridworld.actor里面增加一个专用的MazeFLower类来表示路径，比较清晰
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
 *<code>MazeBug</code>类用来找到一个函数bug在生存期间的可能的生成路径，使用的算法是深度优先算法. <br />
 * 这是一个基于Bug类的扩展类，需要实现的功能是利用深度优先算法执行一个走出迷宫的例子，注意使用了概率估计函数
 */
/**
 * 此算法对应无环路迷宫的树结构。
	先将树的所有节点标记为“未访问”状态。
	输出起始节点，将起始节点标记为”已访问”状态。
	将起始节点入栈。
	当栈非空时重复执行以下步骤：
	
	a. 取当前栈顶节点。
	b. 如果当前栈顶节点是结束节点（迷宫出口），输出该节点，结束搜索。
	c. 如果当前栈顶节点存在”未访问”状态的邻接节点，则选择一个未访问节点，置为”已访问”状态，并将它入栈，继续步骤a。
	d. 如果当前栈顶节点不存在”未访问”状态的邻接节点，则将栈顶节点出栈，继续步骤a。
 */
public class MazeBug extends Bug {
	public Location next;//记录位置的变量，分别是之前的一个位置和可能的之后的一个位置
	public Location last;
	public boolean isEnd = false;//是否到达了红色的Rock
	public Stack<Location> crossLocation = new Stack<Location>();//用来存放节点状态的栈
	public Set<Location> MazeFlowerLocations = new HashSet<Location>();//用来存放MazeFlower位置的一个哈希表
	public Integer stepCount = 0;//计算步数
	boolean hasShown = false;

	/**
	 * 构造函数，给定函数的目标出口，设置在左上角的位置，指定颜色为<code>Location.YELLOW</code> </br>
	 * 构造一个MazeBug的类型对象
	 */
	public MazeBug() {
		setColor(Color.YELLOW);
		last = new Location(0, 0);
	}

	/**
	 * 重载act()函数，移动到可能的位置的下一个位置
	 */
	public void act() {
		isEnd = reachRedRock();//初始化，reachRedRock()返回一个boolean的变量类型，用来判断是否到达末尾指定的位置
		if (isEnd) {
		//如果到达了末尾的位置，展示出来之前的路径	
			if (!hasShown) {
				String massege = stepCount.toString() + " steps";
				showPath();
				JOptionPane.showMessageDialog(null, massege);
				hasShown = true;
			}
		}
		else {//如果说没有到达指定的位置，也就是RedRock的话
			ArrayList<Location> temp = getUnvisitedLocation();//获得没有访问过的位置列表
			if (temp.size() == 0) {//不存在未访问的相邻节点，出栈
				next = crossLocation.pop();
				move();//寻找下一个节点
			}
			else {//如果存在相邻的未曾访问的节点的话
				crossLocation.push(getLocation());//选择未曾访问的节点，入栈并且标记节点状态为已经访问
				next = selectLocation(SelectDirection(crossLocation), temp);
				move();
				
			}
			//每一次执行移动函数之后步数值增加
			stepCount++;
		} 
	}
	
	/**
	 * 重载move()函数，将move()函数的行为照搬过来就好了，一样的在之前走过的地方放置一朵小花，这个函数行为跟之前的一样的，不过也可以在
	 * act()里面直接调用super.move()，不用重载
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
	

	//判断是否到达红色的石头的函数，是一个被调用的具体的方法
	public boolean reachRedRock() {
		ArrayList<Location> locs = getValidAdjacentLocations();//获得当前的可用的位置的队列
		Grid<Actor> gr = getGrid();
		if (locs.size() == 0) {//如果说没有可以用的位置的话
			return false;
		}
		for (Location temp: locs) {//邻居是石头并且是红色的话
			if ((gr.get(temp) instanceof Rock) && (gr.get(temp).getColor()).equals(Color.RED)) {
				return true;
			}
		}
		return false;//否则的话，返回一个false的常量
	}
	
	//寻找在getValidAdjacentLocations()方法当中不曾访问过的节点位置
	public ArrayList<Location> getUnvisitedLocation() {//寻找邻近未访问节点位置
		ArrayList<Location> locs = getValidAdjacentLocations();
		ArrayList<Location> unvisited = new ArrayList<Location>();//声明一个空的位置的队列
		Grid<Actor> gr = getGrid();
		if (locs.size() == 0) {
			return unvisited;
		}
		for (Location temp:locs)  {//检索地图的每一个位置，如果说该位置是一个可用的位置，加入到Unvisited队列当中
			if (!(gr.get(temp) instanceof Rock) && !(gr.get(temp) instanceof MazeFlower)) {
			    unvisited.add(temp);
			}
		}
		return unvisited;//返回一个最终的队列
	}
	
	//获得东西南北4个方向的可能的位置
	 public ArrayList<Location> getValidAdjacentLocations()//获得当前的位置可用的位置
     {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        int d = Location.NORTH;
        for (int i = 0; i < Location.FULL_CIRCLE / Location.RIGHT; i++)//for循环设置4次的转向的位置，因为东西南北就是4个规定的方向
        {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
            d = d + Location.RIGHT;
        }
        return locs;
     }
	 
	 //打印出我们的最终找到的出迷宫的路径
	 public void showPath() {
		 if (MazeFlowerLocations.size() == 0 || crossLocation.size() == 0) {//如果说当前的任何一个位置(不管是一个花还是说可能的下一个合理的位置，我们就需要
			 return;													//执行该语句，size == 0说明不可能出现合理的位置
		 }
		 //清除之前所有在虫子后面出现的花儿
		 Grid<Actor> gr = getGrid();
		 for (Location temp:MazeFlowerLocations) {
			 gr.get(temp).removeSelfFromGrid();
		 }
		 //遍历整个出迷宫的路径，放置每一个Bug，表示出来的路径
		 for (Location temp:crossLocation) {
			 gr.put(temp, new MazeFlower(Color.red));//打印出当前的一个crossLocation的路径
		 }
		 setColor(Color.red);//整个的红色路径打印出来
	 }
	 
	 
	 
	 public int[] SelectDirection(Stack<Location> crossPath) {//转向的方向的栈
		 //Location.NORTH Location.EAST Location.SOUTH 和 Location.WEST
		 int[] direction = {1, 1, 1, 1};
		 if (crossPath.size() == 0) {//路径如果不存在的话，直接返回当前的初始化的PATH
			 return direction;
		 }
		 else {
			 int sizeOfStack = crossPath.size();//记录当前的crossPATH的长度，为后面的打印做一个准备工作
			 for (int i = 0; i < sizeOfStack-1; i++) {
			     int tempDir = crossPath.elementAt(i).getDirectionToward(crossPath.elementAt(i+1));
			     if (tempDir == 0) {
			    	 direction[0]++;//东西南北的各个位置的投票，根据投票的多少，我们进行一个预测的工作
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
		    	 direction[0]++;//最后一步的单独判断的函数，需要实现的内容是，我们需要在末尾来记录一个可能的转向的位置，方法一样，还是投票
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
			 int index = this.getLocation().getDirectionToward(i)/90;//Location的每个标准方向是90drgee的一个等差数列形式
			 int index2 = this.getLocation().getDirectionToward(temp)/90;
			 if (dirs[index] > dirs[index2]) {
				 temp = i;
			 }
		 }
		 return temp;
	 } 
}


