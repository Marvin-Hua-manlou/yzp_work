/*
 * ͷ������
 */
public class SparseGridNode
{
	 private Object current;//Object����
	 private int col;//�к�
	 private SparseGridNode next;//��һ��ָ���λ��
	/*
	 * ���캯��
	 */
	 public SparseGridNode(Object pos, int colNum,SparseGridNode mynext){
		 current = pos;
		 col = colNum;
		 next = mynext;
	 }
	 /*
	  * ����Object����
	  */
	 public Object getCurrent(){
		 return current;
	 }
	 /*
	  * ����к�
	  */
	 public int getCol(){
		 return col;
	 }
	 /*
	  * �����һ��ָ���λ��
	  */
	 public SparseGridNode getNext(){
		 return next;
	 }
	 public void setCurrent(Object pos){
		 current = pos;
	 }
	 /*
	  * ָ�븳ֵ
	  */
	 public void setNext(SparseGridNode newNext){
		 next = newNext;
	 }
} 