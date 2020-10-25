import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
/**
 * ����֪�� <code>BoundedGrid</code>��һ���б߽��Grid,������� </br>
 * <code>SparseBoundedGrid</code> ��һ���ܹ��������ñ߽���࣬����汾��һ������İ汾 </br>
 * ����ִ�е������и�ֵ����
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>{
	 private SparseGridNode[] occupantArray; //����gird����Ķ�������
	 private int numCols;//�кź��к�(��ͼ��)
	 private int numRows;
	 
	 public SparseBoundedGrid(int rows, int cols){
		 if (rows <= 0)
			 throw new IllegalArgumentException("Illegal rows!!");//���벻�Ϸ�
		 if (cols <= 0)
			 throw new IllegalArgumentException("Illegal cols!!");
		 numCols = cols;//�����¼��С
		 numRows = rows;
		 occupantArray = new SparseGridNode[rows];//�кŴ�С�Ķ���������Ϊÿһ��������һ������(��ʼ����ʱ����ڶ��еĸ�ֵ)��
		 //�����Ժ���������
	 }
	 public int getNumRows(){//�����кź��к�
		 return numRows;
	 }
	 public int getNumCols(){
		 return numCols;
	 } 
	 /*
	  * �к��кŴ��ڵ���0����û�г����߽��ʱ��
	  */
	 public boolean isValid(Location loc){
		 return 0 <= loc.getRow() && loc.getRow() < getNumRows() && 0 <= loc.getCol() && loc.getCol() < getNumCols();
	 }
	 /*
	  * 
	  */
	 public ArrayList<Location> getOccupiedLocations(){
		 ArrayList<Location> myLocations = new ArrayList<Location>();
		 //�������ڵ����������λ����Ϣ
		 for (int i = 0; i < getNumRows(); i++){
			 SparseGridNode p = occupantArray[i]; //���������
			 while(p != null){ //����
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
		
		 SparseGridNode p = occupantArray[loc.getRow()]; //�����
		 while(p != null){ //����Ѱ��Ŀ��
		 
			 if(loc.getCol() == p.getCol())
				 return (E)p.getCurrent(); //ע�����ǵ�ǿ��ת��
			 p = p.getNext();
		 }
		 return null;
	 } 
	 public E put(Location loc, E obj){
		 if (!isValid(loc))
			 throw new IllegalArgumentException("Location " + loc + " is not valid");
		 if (obj == null)
			 throw new NullPointerException("obj == null");
		 // �Ƴ��ɵĵ�λ
		 E oldOccupant = remove(loc);
		
		 //����Ĳ��轫���ǵ�Object���뵽���ǵ�Grid����
		
		 //Ϊ�µ�occupant����к�
		 SparseGridNode p = occupantArray[loc.getRow()];
		
		 //������list��ǰ����Object
		 occupantArray[loc.getRow()] = new SparseGridNode(obj,loc.getCol(), p);
		 return oldOccupant;
	 } 
	 public E remove(Location loc){
		 if (!isValid(loc))
		 throw new IllegalArgumentException("Location " + loc + " is not valid");
		
		 //�Ƴ�Object
		 E obj = get(loc);
		 if (obj == null) return null; //�Ҳ�����ʱ�򣬷���null
		
		 SparseGridNode p = occupantArray[loc.getRow()];
		
		 if(p != null){
			 if(p.getCol() == loc.getCol()) //���һ���׽ڵ�
				 occupantArray[loc.getRow()] = p.getNext();
			 else{
			 //q��һ����loc.getCol()����Ѱ��occupant��ָ�����
				 SparseGridNode q = p.getNext();
				 while(q!= null && q.getCol() != loc.getCol())
				 {
					 p = p.getNext();
					 q = q.getNext();
				 }
	
	 //�ҵ��ˣ���ô����loc.getCol()�����Ƴ�Object
				 if(q != null)
					 p.setNext(q.getNext());
			 }
		 }
		 return obj;
	 }
} 