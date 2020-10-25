import info.gridworld.grid.*;
import java.util.ArrayList;
/**
 *  <code>UnboundedGrid</code> ��һ��û�б߽���࣬����кſ�������һ��illegalrange</br>
 *  ��������ͼ��Χ֮�ڲ�����������
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>{
	 private static final int illegalrange = -1;
	 private Object[][] occupantArray;
	 private int dimension; //��ʱ��ά�ȡ�
	 /**
	 * ���캯��
	 */
	 public UnboundedGrid2(){
		 dimension = 16;
		 occupantArray = new Object[dimension][dimension];
	 }
	 /*
	  * ��ʼ����
	  */
	 public int getNumRows(){
		 return illegalrange;
	 }
	 /*
	  * ����к��кţ��޽������ν
	  */
	 public int getNumCols(){
		 return illegalrange;
	 }
	 /*
	  * �Ƿ񳬳��߽�ĺ���
	  */
	 public boolean isValid(Location loc){
		 return loc.getRow() >= 0 && loc.getCol() >= 0;
	 } 
	 /*
	  * ���λ�õĶ��к���
	  */
	 public ArrayList<Location> getOccupiedLocations(){
	 ArrayList<Location> theLocations = new ArrayList<Location>();
	 // Look at all grid locations.
		 for (int r = 0; r < dimension; r++){
			 for (int c = 0; c < dimension; c++){
			 // ����Object���ڣ�������Loc��list����
				 Location loc = new Location(r, c);
				 if (get(loc) != null)
					 theLocations.add(loc);
			 }
		 }
		 return theLocations;
	 }
	 @SuppressWarnings("unchecked")
	 /*
	  * ���λ����Ϣ
	  */
	public E get(Location loc){
		 if (!isValid(loc))
			 throw new IllegalArgumentException("Location " + loc + " is not valid");
		 //�ں����λ�õ��ǲ���loc��list��ʱ�򣬷���null
		 if(loc.getRow() >= dimension || loc.getCol() >= dimension)
			 return null;
		 return (E) occupantArray[loc.getRow()][loc.getCol()];
	 }
	 /*
	  * ����λ����Ϣ
	  */
	 public E put(Location loc, E obj){
		 if (loc == null)
			 throw new NullPointerException("loc == null");
		 if (obj == null)
			 throw new NullPointerException("obj == null");
		
		 //Object�ں����λ��֮���ʱ������
		 if (loc.getRow() >= dimension || loc.getCol() >= dimension)
			 resize(loc);
		
		 // ��Object���뵽grid.
		 E oldOccupant = get(loc);
		 occupantArray[loc.getRow()][loc.getCol()] = obj;
		 return oldOccupant;
	 } 
	 /*
	  * �Ƴ�λ����Ϣ
	  */
	 public E remove(Location loc){
		 if (!isValid(loc))
		 throw new IllegalArgumentException("Location " + loc + " is not valid");
		
		 // �ں����λ�õ��ǲ���loc��list��ʱ�򣬷���null
		 if(loc.getRow() >= dimension || loc.getCol() >= dimension)
			 return null;
		 // �Ƴ�Object����
		 E r = get(loc);
		 occupantArray[loc.getRow()][loc.getCol()] = null;
		 return r;
	 }
	
	 private void resize(Location loc){
	 //���ݣ��������
		 int size = dimension;
		 while (loc.getRow() >= size || loc.getCol() >= size)
			 size *= 2;
		
		 //����һ���µ�array�洢����
		 Object[][] temp = new Object[size][size];
		
		 //��ֵ֮ǰ��grid��
		 for(int r = 0; r < dimension; r++)
			 for(int c = 0; c < dimension; c++)
				 temp[r][c] = occupantArray[r][c];
		
		 //��ʼ�����ǵ�Object�������size
		 occupantArray = temp;
		 dimension = size;
	 }
} 