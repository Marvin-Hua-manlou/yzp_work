import info.gridworld.grid.*;
import java.util.ArrayList;
/**
 *  <code>UnboundedGrid</code> 是一个没有边界的类，最大行号可以设置一个illegalrange</br>
 *  在整个的图范围之内不断增加网格
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>{
	 private static final int illegalrange = -1;
	 private Object[][] occupantArray;
	 private int dimension; //此时的维度。
	 /**
	 * 构造函数
	 */
	 public UnboundedGrid2(){
		 dimension = 16;
		 occupantArray = new Object[dimension][dimension];
	 }
	 /*
	  * 初始构造
	  */
	 public int getNumRows(){
		 return illegalrange;
	 }
	 /*
	  * 获得行号列号：无界的无所谓
	  */
	 public int getNumCols(){
		 return illegalrange;
	 }
	 /*
	  * 是否超出边界的函数
	  */
	 public boolean isValid(Location loc){
		 return loc.getRow() >= 0 && loc.getCol() >= 0;
	 } 
	 /*
	  * 获得位置的队列函数
	  */
	 public ArrayList<Location> getOccupiedLocations(){
	 ArrayList<Location> theLocations = new ArrayList<Location>();
	 // Look at all grid locations.
		 for (int r = 0; r < dimension; r++){
			 for (int c = 0; c < dimension; c++){
			 // 如若Object存在，放置在Loc的list里面
				 Location loc = new Location(r, c);
				 if (get(loc) != null)
					 theLocations.add(loc);
			 }
		 }
		 return theLocations;
	 }
	 @SuppressWarnings("unchecked")
	 /*
	  * 获得位置信息
	  */
	public E get(Location loc){
		 if (!isValid(loc))
			 throw new IllegalArgumentException("Location " + loc + " is not valid");
		 //在合理的位置但是不在loc的list的时候，返回null
		 if(loc.getRow() >= dimension || loc.getCol() >= dimension)
			 return null;
		 return (E) occupantArray[loc.getRow()][loc.getCol()];
	 }
	 /*
	  * 放置位置信息
	  */
	 public E put(Location loc, E obj){
		 if (loc == null)
			 throw new NullPointerException("loc == null");
		 if (obj == null)
			 throw new NullPointerException("obj == null");
		
		 //Object在合理的位置之外的时候，扩容
		 if (loc.getRow() >= dimension || loc.getCol() >= dimension)
			 resize(loc);
		
		 // 把Object加入到grid.
		 E oldOccupant = get(loc);
		 occupantArray[loc.getRow()][loc.getCol()] = obj;
		 return oldOccupant;
	 } 
	 /*
	  * 移除位置信息
	  */
	 public E remove(Location loc){
		 if (!isValid(loc))
		 throw new IllegalArgumentException("Location " + loc + " is not valid");
		
		 // 在合理的位置但是不在loc的list的时候，返回null
		 if(loc.getRow() >= dimension || loc.getCol() >= dimension)
			 return null;
		 // 移除Object对象
		 E r = get(loc);
		 occupantArray[loc.getRow()][loc.getCol()] = null;
		 return r;
	 }
	
	 private void resize(Location loc){
	 //扩容，这里二倍
		 int size = dimension;
		 while (loc.getRow() >= size || loc.getCol() >= size)
			 size *= 2;
		
		 //申明一个新的array存储对象
		 Object[][] temp = new Object[size][size];
		
		 //赋值之前的grid。
		 for(int r = 0; r < dimension; r++)
			 for(int c = 0; c < dimension; c++)
				 temp[r][c] = occupantArray[r][c];
		
		 //初始化我们的Object的数组和size
		 occupantArray = temp;
		 dimension = size;
	 }
} 