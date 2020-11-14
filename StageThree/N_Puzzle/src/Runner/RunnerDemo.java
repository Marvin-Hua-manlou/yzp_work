package Runner;
import java.util.Scanner;
import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import java.io.IOException;

public class RunnerDemo {

  /**
   * ��ʾ�ű�������ʽ����-ʾ���㷨-������3*3ƴͼ��8-�������⣩
   *
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
	  Scanner sc = new Scanner(System.in);
	  System.out.println("������ά��(dimension):");
	  Integer num =sc.nextInt(); //����ά��
	  sc.close();
	  // ���������ʼ״̬����startNode����Ŀ��״̬��ɢ�����ɿɽ�������ʼ״̬
//		JigsawNode startNode = Jigsaw.scatter(destNode, 1000);
	  //����ָ���Ľڵ㿪ʼ״̬
	JigsawNode startNode = new JigsawNode(new int[]{5, 1, 5, 2, 7, 0, 4, 6, 3, 8},num);
    // ���ڵ�ά���Ƿ�Ϊ3
    if (JigsawNode.getDimension() != 3) {
      System.out.print("�ڵ�ά������ȷ���뽫JigsawNode���ά��dimension��Ϊ3");
      return;
    }

    // ����Ŀ��״̬����destNode: {9,1,2,3,4,5,6,7,8,0}
    JigsawNode destNode = new JigsawNode(new int[]{9, 1, 2, 3, 4, 5, 6, 7, 8, 0},num);

    // ����jigsaw�������ó�ʼ״̬�ڵ�startNode��Ŀ��״̬�ڵ�destNode
    Jigsaw jigsaw = new Jigsaw(startNode, destNode);

    // ִ������ʽ����ʾ���㷨
    jigsaw.ASearch(num.toString());
  }

}
