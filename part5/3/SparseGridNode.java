/*
 * 头结点对象
 */
public class SparseGridNode
{
	 private Object current;//Object对象
	 private int col;//列号
	 private SparseGridNode next;//下一个指针的位置
	/*
	 * 构造函数
	 */
	 public SparseGridNode(Object pos, int colNum,SparseGridNode mynext){
		 current = pos;
		 col = colNum;
		 next = mynext;
	 }
	 /*
	  * 返回Object对象
	  */
	 public Object getCurrent(){
		 return current;
	 }
	 /*
	  * 获得列号
	  */
	 public int getCol(){
		 return col;
	 }
	 /*
	  * 获得下一个指针的位置
	  */
	 public SparseGridNode getNext(){
		 return next;
	 }
	 public void setCurrent(Object pos){
		 current = pos;
	 }
	 /*
	  * 指针赋值
	  */
	 public void setNext(SparseGridNode newNext){
		 next = newNext;
	 }
} 