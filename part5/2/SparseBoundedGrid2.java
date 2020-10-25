import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * һ�����Լ���ֵ�߽���࣬��ʵ�ֵĺ�����֮ǰ��һ����ʹ�õ���ͼ�㷨
 */
//E��һ������
public class SparseBoundedGrid2<E> extends AbstractGrid<E>{
	 private Map<Location, E> occupantMap;//ͼ�㷨
	 private int numRows;
	 private int numCols;
	 /**
	 * ���캯��
	 */
	 public SparseBoundedGrid2(int rows, int cols){
		 occupantMap = new HashMap<Location, E>();//��ϣͼ
		 numRows = rows;
		 numCols = cols;
	 }
	 public int getNumRows(){//�������к�
		 return numRows;
	 } 
	 public int getNumCols(){//�������к�
		 return numCols;
	 }
	 public boolean isValid(Location loc){//����isValid()����
		 return 0 <= loc.getRow() && loc.getRow() < getNumRows() && 0 <= loc.getCol() && loc.getCol() < getNumCols();
	 }
	 public ArrayList<Location> getOccupiedLocations(){//���ռ�ݵ�λ��
		 ArrayList<Location> a = new ArrayList<Location>();
		 for (Location loc : occupantMap.keySet())//���ϵ��е�ӳ�䷴ӳ����������ͼ�Ŀ���λ��
			 a.add(loc);
		 return a;
	 }
	 public E get(Location loc){//��ÿ��ܵĶ���
		 if (loc == null)
			 throw new NullPointerException("loc is null!!");
		 return occupantMap.get(loc);
	 }
	 public E put(Location loc, E obj){//���ö���
		 if (loc == null)
			 throw new NullPointerException("loc is null!!");
		 if (obj == null)
			 throw new NullPointerException("loc is null!!");
		 return occupantMap.put(loc, obj);
	 }
	 public E remove(Location loc){//�Ƴ�λ��
	 if (loc == null)
		 throw new NullPointerException("loc is null!!");
	 return occupantMap.remove(loc);
	 }
} 
