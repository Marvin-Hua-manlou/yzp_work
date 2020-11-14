package Runner;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.io.IOException;
import java.util.Scanner;

public class RunnerPart1 {
  /**
   * ���Խű�-1 ʵ������һ�����ù��������������ָ��3*3ƴͼ��8-�������⣩�����Ž� Ҫ�󣺲��޸Ľű����ݣ������ܹ����У��ҵó�Ԥ�ڽ��
   *
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    // ���ڵ�ά���Ƿ�Ϊ3
	  Scanner sc = new Scanner(System.in);
	  System.out.println("������ά��(dimension):");
	  Integer num =sc.nextInt(); //����ά��
	  sc.close();
	  
	// ����ָ����ʼ״̬startNode��{5,1,5,2,7,0,4,6,3,8}
	  JigsawNode startNode = new JigsawNode(new int[]{5, 1, 5, 2, 7, 0, 4, 6,3, 8},num);
	  
    if (JigsawNode.getDimension() != 3) {
      System.out.print("�ڵ�ά������ȷ���뽫JigsawNode���ά��dimension��Ϊ3");
      return;
    }

    // ����Ŀ��״̬destNode��{9,1,2,3,4,5,6,7,8,0}
    JigsawNode destNode = new JigsawNode(new int[]{9, 1, 2, 3, 4, 5, 6, 7, 8,0},num);
    // JigsawNode destNode = new JigsawNode(new int[]{2,1,0,2,7,5,4,6,3,8});
   
    // ����jigsaw�������ó�ʼ״̬�ڵ�startNode��Ŀ��״̬�ڵ�destNode
    Jigsaw j = new Jigsaw(startNode, destNode);

    // ִ�й�����������㷨
    j.BFSearch();
  }
}
