package Runner;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import java.io.IOException;
import java.util.Scanner;

public class RunnerPart2 {
  /**
   * ���Խű�-2 ʵ�����������������ʽ������������5*5ƴͼ��24-�������⣩
   * ע�⣺����ǰҪ�޸Ľڵ�ά������JigsawNode���е�dimension��Ϊ5 Ҫ�󣺲��޸Ľű����ݣ������ܹ����У��ҵó�Ԥ�ڽ��
   *
   * @param args
   * @throws IOException
   */
//  public static void main(String[] args) throws IOException {
//
//    // ���ڵ�ά���Ƿ�Ϊ5
//    if (JigsawNode.getDimension() != 5) {
//      System.out.print("�ڵ�ά������ȷ���뽫JigsawNode���ά��dimension��Ϊ5");
//      return;
//    }
//
//    int search[] = new int[caseNum];
//    for (int i = 0; i < caseNum; i++) {
//      // ����Ŀ��״̬����destNode:
//      // {25,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0};
//      JigsawNode destNode = new JigsawNode(new int[]{25, 1, 2, 3, 4, 5, 6, 7,
//              8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0});
//
//      // ���������ʼ״̬����startNode����Ŀ��״̬��ɢ�����ɿɽ�������ʼ״̬
//      //JigsawNode startNode = Jigsaw.scatter(destNode, 1000);
//       JigsawNode startNode = new JigsawNode(new
//       int[]{19,8,7,9,23,10,3,19,5,4,14,2,20,11,6,15,22,13,16,0,1,21,12,18,24,17});
//
//      // ����jigsaw�������ó�ʼ״̬�ڵ�startNode��Ŀ��״̬�ڵ�destNode
//      Jigsaw jigsaw = new Jigsaw(startNode, destNode);
//
//      // ִ������ʽ����ʾ���㷨
//      jigsaw.ASearch();
//      search[i] = jigsaw.getSearchedNodesNum();
//    }
//  
//    int sum = 0;
//    int success = 0;
//    String filePath = "ASearchIO.txt";
//    PrintWriter pw = new PrintWriter(new FileWriter(filePath));
//    for (int i = 0; i < caseNum; i++) {
//      if (search[i] < 30000) {
//        sum += search[i];
//        success++;
//      }
//      pw.append("case " + Integer.toString(i + 1) + " searched nodes: "
//              + Integer.toString(search[i]) + "\n");
//    }
//    int average = sum / success;
//    pw.append("average search nodes: " + Integer.toString(average) + "\n");
//    pw.append("pass cases: " + Integer.toString(success) + "\n");
//    pw.close();
//  }
  public static void main(String[] args) throws IOException {
	  Scanner sc = new Scanner(System.in);
	  System.out.println("������ά��(dimension):");
	  Integer num =sc.nextInt(); //����ά��
	  sc.close();
	  
	//����ָ���ĳ�ʼ״̬�ڵ�{23, 1, 2, 3, 4, 7, 5, 6,8,23, 10, 11, 12, 13, 14, 24, 16, 17, 18, 19, 20, 21, 22, 0,9, 15}
	    JigsawNode startNode = new JigsawNode(new int[]{23, 1, 2, 3, 4, 7, 5, 6,
	    		8,23, 10, 11, 12, 13, 14, 24, 16, 17, 18, 19, 20, 21, 22, 0,9, 15},num);
	    // ���ڵ�ά���Ƿ�Ϊ5
	    if (JigsawNode.getDimension() != 5) {
	      System.out.print("�ڵ�ά������ȷ���뽫JigsawNode���ά��dimension��Ϊ5");
	      return;
	    }
	    // ����Ŀ��״̬destNode��{25, 1, 2, 3, 4, 5, 6, 7,8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0}
	    JigsawNode destNode = new JigsawNode(new int[]{25, 1, 2, 3, 4, 5, 6, 7,
              8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0},num);
	    
	    // ����jigsaw�������ó�ʼ״̬�ڵ�startNode��Ŀ��״̬�ڵ�destNode
	    Jigsaw j = new Jigsaw(startNode, destNode);

	    // ִ�й�����������㷨
	    j.ASearch(num.toString());
	  }
}
