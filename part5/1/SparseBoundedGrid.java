import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
/**
 * 我们知道 <code>BoundedGrid</code>是一个有边界的Grid,下面的类 </br>
 * <code>SparseBoundedGrid</code> 是一个能够自行设置边界的类，这个版本是一个链表的版本 </br>
 * 我们执行的是逐列赋值放置
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>{
	 private SparseGridNode[] occupantArray; //储存gird对象的队列数组
	 private int numCols;//行号和列号(地图的)
	 private int numRows;
	 
	 public SparseBoundedGrid(int rows, int cols){
		 if (rows <= 0)
			 throw new IllegalArgumentException("Illegal rows!!");//输入不合法
		 if (cols <= 0)
			 throw new IllegalArgumentException("Illegal cols!!");
		 numCols = cols;//否则记录大小
		 numRows = rows;
		 occupantArray = new SparseGridNode[rows];//行号大小的对象数组因为每一个对象是一个链表(初始化的时候存在对列的赋值)，
		 //决定以后做几个列
	 }
	 public int getNumRows(){//最大的行号和列号
		 return numRows;
	 }
	 public int getNumCols(){
		 return numCols;
	 } 
	 /*
	  * 行号列号大于等于0而且没有超出边界的时候
	  */
	 public boolean isValid(Location loc){
		 return 0 <= loc.getRow() && loc.getRow() < getNumRows() && 0 <= loc.getCol() && loc.getCol() < getNumCols();
	 }
	 /*
	  * 
	  */
	 public ArrayList<Location> getOccupiedLocations(){
		 ArrayList<Location> myLocations = new ArrayList<Location>();
		 //检索存在的所有网格的位置信息
		 for (int i = 0; i < getNumRows(); i++){
			 SparseGridNode p = occupantArray[i]; //获得行链表
			 while(p != null){ //遍历
				 Location loc = new Location(i, p.getCol());
				 myLocations.add(loc);
				 p = p.getNext();
			 }
		 }
		 return myLocations;
	 }
	@SuppressWarnings("unchecked")
	public E get(Location loc){
		 if (!isValid(loc))
			 throw new IllegalArgumentException("Location " + loc + " is Illegal!!");
		
		 SparseGridNode p = occupantArray[loc.getRow()]; //获得行
		 while(p != null){ //遍历寻找目标
		 
			 if(loc.getCol() == p.getCol())
				 return (E)p.getCurrent(); //注意我们的强制转换
			 p = p.getNext();
		 }
		 return null;
	 } 
	 public E put(Location loc, E obj){
		 if (!isValid(loc))
			 throw new IllegalArgumentException("Location " + loc + " is not valid");
		 if (obj == null)
			 throw new NullPointerException("obj == null");
		 // 移除旧的单位
		 E oldOccupant = remove(loc);
		
		 //下面的步骤将我们的Object加入到我们的Grid里面
		
		 //为新的occupant获得行号
		 SparseGridNode p = occupantArray[loc.getRow()];
		
		 //在整个list在前放置Object
		 occupantArray[loc.getRow()] = new SparseGridNode(obj,loc.getCol(), p);
		 return oldOccupant;
	 } 
	 public E remove(Location loc){
		 if (!isValid(loc))
		 throw new IllegalArgumentException("Location " + loc + " is not valid");
		
		 //移除Object
		 E obj = get(loc);
		 if (obj == null) return null; //找不到的时候，返回null
		
		 SparseGridNode p = occupantArray[loc.getRow()];
		
		 if(p != null){
			 if(p.getCol() == loc.getCol()) //检查一个首节点
				 occupantArray[loc.getRow()] = p.getNext();
			 else{
			 //q是一个在loc.getCol()当中寻找occupant的指针变量
				 SparseGridNode q = p.getNext();
				 while(q!= null && q.getCol() != loc.getCol())
				 {
					 p = p.getNext();
					 q = q.getNext();
				 }
	
	 //找到了，那么就在loc.getCol()当中移除Object
				 if(q != null)
					 p.setNext(q.getNext());
			 }
		 }
		 return obj;
	 }
} 