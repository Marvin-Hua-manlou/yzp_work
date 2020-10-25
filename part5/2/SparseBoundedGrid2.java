import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * 一样的自己赋值边界的类，是实现的函数跟之前不一样，使用到了图算法
 */
//E是一个泛类
public class SparseBoundedGrid2<E> extends AbstractGrid<E>{
	 private Map<Location, E> occupantMap;//图算法
	 private int numRows;
	 private int numCols;
	 /**
	 * 构造函数
	 */
	 public SparseBoundedGrid2(int rows, int cols){
		 occupantMap = new HashMap<Location, E>();//哈希图
		 numRows = rows;
		 numCols = cols;
	 }
	 public int getNumRows(){//获得最大行号
		 return numRows;
	 } 
	 public int getNumCols(){//获得最大列号
		 return numCols;
	 }
	 public boolean isValid(Location loc){//重载isValid()函数
		 return 0 <= loc.getRow() && loc.getRow() < getNumRows() && 0 <= loc.getCol() && loc.getCol() < getNumCols();
	 }
	 public ArrayList<Location> getOccupiedLocations(){//获得占据的位置
		 ArrayList<Location> a = new ArrayList<Location>();
		 for (Location loc : occupantMap.keySet())//集合当中的映射反映，遍历整个图的可能位置
			 a.add(loc);
		 return a;
	 }
	 public E get(Location loc){//获得可能的对象
		 if (loc == null)
			 throw new NullPointerException("loc is null!!");
		 return occupantMap.get(loc);
	 }
	 public E put(Location loc, E obj){//放置对象
		 if (loc == null)
			 throw new NullPointerException("loc is null!!");
		 if (obj == null)
			 throw new NullPointerException("loc is null!!");
		 return occupantMap.put(loc, obj);
	 }
	 public E remove(Location loc){//移除位置
	 if (loc == null)
		 throw new NullPointerException("loc is null!!");
	 return occupantMap.remove(loc);
	 }
} 
