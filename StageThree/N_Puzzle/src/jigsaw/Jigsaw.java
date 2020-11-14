package jigsaw;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * �ڴ���������㷨�������ƴͼ��Ϸ��N-�������⣩
 *
 * @author yzp
 */
public class Jigsaw {
  JigsawNode beginJNode; // ƴͼ����ʼ״̬�ڵ�
  JigsawNode endJNode; // ƴͼ��Ŀ��״̬�ڵ�
  JigsawNode currentJNode; // ƴͼ�ĵ�ǰ״̬�ڵ�
  private Vector<JigsawNode> openList; // open�� �����Ա����ѷ��ֵ�δ���ʵĽڵ�
  private Vector<JigsawNode> closeList; // close�����Ա����ѷ��ʵĽڵ�
  private Vector<JigsawNode> solutionPath;// ��·�� �����Ա������ʼ״̬����Ŀ��״̬���ƶ�·���е�ÿһ��״̬�ڵ�
  private boolean isCompleted; // ��ɱ�ǣ���ʼΪfalse;�����ɹ�ʱ�����ñ����Ϊtrue
  private int searchedNodesNum; // �ѷ��ʽڵ����� ���Լ�¼���з��ʹ��Ľڵ������

  /**
   * ƴͼ���캯��
   *
   * @param bNode - ��ʼ״̬�ڵ�
   * @param eNode - Ŀ��״̬�ڵ�
   */
  public Jigsaw(JigsawNode bNode, JigsawNode eNode) {
    this.beginJNode = new JigsawNode(bNode);
    this.endJNode = new JigsawNode(eNode);
    this.currentJNode = new JigsawNode(bNode);
    this.openList = new Vector<JigsawNode>();
    this.closeList = new Vector<JigsawNode>();
    this.solutionPath = null;
    this.isCompleted = false;
    this.searchedNodesNum = 0;
  }

  /**
   * �˺������ڴ�ɢƴͼ��������ĳ�ʼ״̬�ڵ�jNode����ƶ�len�����������ɢ���״̬�ڵ�
   *
   * @param jNode - ��ʼ״̬�ڵ�
   * @param len   - ����ƶ��Ĳ���
   * @return ��ɢ���״̬�ڵ�
   */
  public static JigsawNode scatter(JigsawNode jNode, int len) {
    int randomDirection;
    len += (int) (Math.random() * 2);
    JigsawNode jigsawNode = new JigsawNode(jNode);
    for (int t = 0; t < len; t++) {
      int[] movable = jigsawNode.canMove();
      do {
        randomDirection = (int) (Math.random() * 4);
      } while (0 == movable[randomDirection]);
      jigsawNode.move(randomDirection);
    }
    jigsawNode.setInitial();
    return jigsawNode;
  }

  /**
   * ��ȡƴͼ�ĵ�ǰ״̬�ڵ�
   *
   * @return currentJNode - ƴͼ�ĵ�ǰ״̬�ڵ�
   */
  public JigsawNode getCurrentJNode() {
    return currentJNode;
  }

  /**
   * ����ƴͼ�ĳ�ʼ״̬�ڵ�
   *
   * @param jNode - ƴͼ�ĳ�ʼ״̬�ڵ�
   */
  public void setBeginJNode(JigsawNode jNode) {
    beginJNode = jNode;
  }

  /**
   * ��ȡƴͼ�ĳ�ʼ״̬�ڵ�
   *
   * @return beginJNode - ƴͼ�ĳ�ʼ״̬�ڵ�
   */
  public JigsawNode getBeginJNode() {
    return beginJNode;
  }

  /**
   * ����ƴͼ��Ŀ��״̬�ڵ�
   *
   * @param jNode - ƴͼ��Ŀ��״̬�ڵ�
   */
  public void setEndJNode(JigsawNode jNode) {
    this.endJNode = jNode;
  }

  /**
   * ��ȡƴͼ��Ŀ��״̬�ڵ�
   *
   * @return endJNode - ƴͼ��Ŀ��״̬�ڵ�
   */
  public JigsawNode getEndJNode() {
    return endJNode;
  }

  /**
   * ��ȡƴͼ�����״̬
   *
   * @return isCompleted - ƴͼ�ѽ�Ϊtrue��ƴͼδ��Ϊfalse
   */
  public boolean isCompleted() {
    return isCompleted;
  }

  /**
   * ������·��
   *
   * @return ���н⣬�򽫽��������solutionPath�У�����true; ���޽⣬�򷵻�false
   */
  private boolean calSolutionPath() {
    if (!this.isCompleted()) {
      return false;
    } else {
      JigsawNode jNode = this.currentJNode;
      solutionPath = new Vector<JigsawNode>();
      while (jNode != null) {
        solutionPath.addElement(jNode);
        jNode = jNode.getParent();
      }
      return true;
    }
  }

  /**
   * ��ȡ��·���ı�
   *
   * @return ��·��solutionPath���ַ��������н⣬����м�¼�ӳ�ʼ״̬����Ŀ��״̬���ƶ�·���е�ÿһ��״̬�ڵ㣻
   * ��δ����޽⣬�򷵻���ʾ��Ϣ��
   */
  public String getSolutionPath() {
    String Str = new String();
    int flag = 0;
    Str += "Begin::->\r\n";
    if (this.isCompleted) {
    	//vector�ǵ���ѹ��ȥ��
      for (int i = solutionPath.size() - 1; i >= 0; i--) {
        Str += solutionPath.elementAt(i).toString() + "->";
        flag++;
        if(flag == 4) {
        	Str += "\r\n";
        	flag = 0;
        }
      }
      Str += "\r\n->::End";
    } else
      Str = "Jigsaw is not completed,please cheak.";
    return Str;
  }

  /**
   * ��ȡ���ʹ��Ľڵ���searchedNodesNum
   *
   * @return ���������ѷ��ʹ��Ľڵ�����
   */
  public int getSearchedNodesNum() {
    return searchedNodesNum;
  }

  /**
   * ���������д���ļ��У�ͬʱ��ʾ�ڿ���̨ ������ʧ�ܣ�����ʾ�����޽⣬����ѷ��ʽڵ�����
   * �������ɹ����������ʼ״̬beginJnode��Ŀ��״̬endJNode
   * ���ѷ��ʽڵ���searchedNodesNum��·�����nodeDepth�ͽ�·��solutionPath��
   *
   * @param pw - �ļ����PrintWriter��������pwΪnull����д�뵽Result.txt
   * @throws IOException
   */
  public void printResult(PrintWriter pw) throws IOException {
    boolean flag = false;
    if (pw == null) {
      pw = new PrintWriter(new FileWriter("F:\\eclipse-Worksapce\\N_Puzzle\\text\\Result.txt"));
      // ����������д��:Result.txt
      flag = true;
    }
    if (this.isCompleted == true) {
      // д���ļ�
      pw.println("Jigsaw completed");
      pw.println("Begin state:" + this.getBeginJNode().toString());
      pw.println("End state:" + this.getEndJNode().toString());
      pw.println("Solution Path is: ");
      pw.println(this.getSolutionPath());
      pw.println("The number of searched nodes are:" + this.getSearchedNodesNum());
      pw.println("The size of the solution path is:"
              + this.getCurrentJNode().getNodeDepth());

      // ���������̨
      System.out.println("Jigsaw completed");
      System.out.println("Begin state:" + this.getBeginJNode().toString());
      System.out.println("End state:" + this.getEndJNode().toString());
      System.out.println("Solution Path is: ");
      System.out.println(this.getSolutionPath());
      System.out.println("The all number of searched nodes are:"
              + this.getSearchedNodesNum());
      System.out.println("The size of the solution path is:"
              + this.getCurrentJNode().getNodeDepth());

    } else {
      // д���ļ�
      pw.println("No solution. Jigsaw is not completed,please cheak.");
      pw.println("Begin state:" + this.getBeginJNode().toString());
      pw.println("End state:" + this.getEndJNode().toString());
      pw.println("The all number of searched nodes are:" + this.getSearchedNodesNum());

      // ���������̨
      System.out.println("No solution. Jigsaw is not completed,pleased cheak.");
      System.out.println("Begin state:" + this.getBeginJNode().toString());
      System.out.println("End state:" + this.getEndJNode().toString());
      System.out.println("The number of searched nodes is:"
              + this.getSearchedNodesNum());
    }
    if (flag)
      pw.close();
  }

  /**
   * ̽��������jNode�ڽ�(�ϡ��¡�����)��δ�������ʵĽڵ�
   *
   * @param jNode - Ҫ̽���Ľڵ�
   * @return ����������jNode�ڽ���δ�������ʵĽڵ��Vector<JigsawNode>����
   */
  private Vector<JigsawNode> findFollowJNodes(JigsawNode jNode) {
    Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();
    JigsawNode tempJNode;
    for (int i = 0; i < 4; i++) {
      tempJNode = new JigsawNode(jNode);
      if (tempJNode.move(i) && !this.closeList.contains(tempJNode)
              && !this.openList.contains(tempJNode))
        followJNodes.addElement(tempJNode);
    }
    return followJNodes;
  }

  /**
   * �������openList�����սڵ�Ĵ��۹�ֵ��estimatedValue�����ڵ����openList�У���ֵС�Ŀ�ǰ��
   *
   * @param jNode - Ҫ�����״̬�ڵ�
   */
  private void sortedInsertOpenList(JigsawNode jNode) {
    this.estimateValue(jNode);
    for (int i = 0; i < this.openList.size(); i++) {
      if (jNode.getEstimatedValue() < this.openList.elementAt(i)
              .getEstimatedValue()) {
        this.openList.insertElementAt(jNode, i);
        return;
      }
    }
    this.openList.addElement(jNode);
  }

  // ****************************************************************
  // *************************ʵ������************************
  /**
   * ʵ������һ��������������㷨����ָ��3*3ƴͼ��8-�������⣩�����Ž�
   * Ҫ����������������㷨BFSearch()��ִ�в��Խű�RunnerPart1 ��Ҫ�漰������BFSearch()
   */
  /**
   * ʵ�������������ʽ�����㷨��������5*5ƴͼ��24-�������⣩
   * Ҫ��1.�޸�����ʽ�����㷨ASearch()�ʹ��۹��ƺ���estimateValue()��ִ�в��Խű�RunnerPart2
   * 2.���ʽڵ�����������29000������Ҫ�漰������ASearch()��estimateValue()
   */
  // ****************************************************************

  /**
   * ��ʵ��һ��������������㷨�����ָ��3*3ƴͼ��8-�������⣩�����Ž⡣ Ҫ���������� 1,isCompleted��¼��������״̬��
   * 2,closeList��¼�����з��ʹ��Ľڵ㣻 3,searchedNodesNum��¼�˷��ʹ��Ľڵ����� 4,solutionPath��¼�˽�·����
   * ����ʼ�ڵ����һ��open�б��С�</br>
	���open�б�Ϊ�գ�������ʧ�ܣ������޽⣻�����ظ����²��裺
	a. ����open�б��еĵ�һ���ڵ�v����vΪĿ��ڵ㣬�������ɹ����˳���
	b. ��open�б���ɾ���ڵ�v������close�б��С�
	c. ��������v�ڽ���δ�������ʵĽڵ����open�б��С�
   *
   * @return isCompleted, �����ɹ�ʱΪtrue,ʧ��Ϊfalse
   * @throws IOException
   * 
   */
  public boolean BFSearch() throws IOException {
    // ����������д��:BFSearch.txt
    String filePath = "F:\\eclipse-Worksapce\\N_Puzzle\\text\\BFSearch.txt";
    PrintWriter pw = new PrintWriter(new FileWriter(filePath));
    // *************************************

    // must update the currentNode
    currentJNode = beginJNode;
    openList.addElement(currentJNode);
    while (!openList.isEmpty()) {
      // can't use equal operator '=' for it compares pointer rather than the
      // object
      if (currentJNode.equals(endJNode)) {
        isCompleted = true;
        calSolutionPath();
        break;
      } else {
        Vector<JigsawNode> child = findFollowJNodes(currentJNode);
        for (JigsawNode ch : child) {
          openList.addElement(ch);
        }
        openList.removeElement(currentJNode);
        closeList.addElement(currentJNode);
        currentJNode = openList.firstElement();
        searchedNodesNum++;
      }
    }

    // *************************************
    this.printResult(pw);
    pw.close();
    System.out.println("Result writting into " + filePath);
    return isCompleted;
  }

  /**
   * ��Demo+ʵ���������ʽ������
   * ����ʽ��������A*�㷨��:</br>
   * ��������ӵ�е�������Ϣ��������������̬��ȷ�������ڵ�������Դﵽ����������Χ���������⸴�Ӷȵ�Ŀ�ġ�
   * ��N-���������У�ÿ������ÿһ���ڵ�ʱ��ͨ�������ۺ������Ըýڵ����"��������Ȼ�����ȷ��ʡ����������ڵ���ڽӽڵ㣬�ܹ�����������ʱ�䡣</br>
   * ���ʽڵ�������30000������Ϊ����ʧ�ܡ� ����������isCompleted��¼��������״̬��
   * closeList��¼�����з��ʹ��Ľڵ㣻 searchedNodesNum��¼�˷��ʹ��Ľڵ����� solutionPath��¼�˽�·����
   * �������̺ͽ�����¼��:ASearch.txt�С�
   *
   * @return �����ɹ�����true, ʧ�ܷ���false
   * @throws IOException
   */
  public boolean ASearch(String dimension) throws IOException {
    // ����������д��ASearch.txt
    String filePath = "F:\\eclipse-Worksapce\\N_Puzzle\\text\\ASearch_"+dimension+"x"+dimension+".txt";
    PrintWriter pw = new PrintWriter(new FileWriter(filePath));

    // ���ʽڵ�������29000������Ϊ����ʧ��
    int maxNodesNum = 29000;

    // ���Դ��ĳһ�ڵ���ڽӽڵ�
    Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();

    // ���������ɱ��Ϊfalse
    isCompleted = false;

    // (1)����ʼ�ڵ����openList��
    this.sortedInsertOpenList(this.beginJNode);

    // (2) ���openListΪ�գ����߷��ʽڵ�������maxNodesNum����������ʧ�ܣ������޽�;����ѭ��ֱ�����ɹ�
    while (!this.openList.isEmpty() && searchedNodesNum <= maxNodesNum) {

      // (2-1)����openList�ĵ�һ���ڵ�N����Ϊ��ǰ�ڵ�currentJNode
      // ��currentJNodeΪĿ��ڵ㣬�������ɹ���������ɱ��isCompletedΪtrue�������·�����˳���
      this.currentJNode = this.openList.elementAt(0);
      if (this.currentJNode.equals(this.endJNode)) {
        isCompleted = true;
        this.calSolutionPath();
        break;
      }

      // (2-2)��openList��ɾ���ڵ�N,���������closeList�У���ʾ�Է��ʽڵ�
      this.openList.removeElementAt(0);
      this.closeList.addElement(this.currentJNode);
      searchedNodesNum++;

      // ��¼����ʾ��������
      pw.println("Searching the "+this.closeList.size()+"th times nodes:"
              +"   Current state:"
              + this.currentJNode.toString());
      System.out.println("Searching the "+this.closeList.size()+"th times nodes:"
              + "   Current state:"
              + this.currentJNode.toString());

      // (2-3)Ѱ��������currentJNode�ڽ���δ�������ʵĽڵ㣬�����ǰ����۹�ֵ��С�����������openList��
      followJNodes = this.findFollowJNodes(this.currentJNode);
      while (!followJNodes.isEmpty()) {
        this.sortedInsertOpenList(followJNodes.elementAt(0));
        followJNodes.removeElementAt(0);
      }
    }

    this.printResult(pw); // ��¼�������
    pw.close(); // �ر�����ļ�
    System.out.println("Result writting into " + filePath);
    return isCompleted;
  }

  /**
   * ��Demo+ʵ��������㲢�޸�״̬�ڵ�jNode�Ĵ��۹���ֵ:</br>
   * ʹ�÷�����
   * 1.�����ڵ㲻��ȷ���������:uncorrect(n)</br>
   * 2.���зŴ�λ������������ȷλ�õľ���֮��:depth(n)</br>
   * 3.��ǰλ�ýڵ㵽ĩλλ�ýڵ�������پ���manhattan(n)</br>
   * f(n)=uncorrect(n)+depth(n)+Manhattan(n).
   *
   * @param jNode - Ҫ������۹���ֵ�Ľڵ㣻�˺�����ı�ýڵ��estimatedValue����ֵ��
   */
  
  //Ԥ������ĺ���
  private void estimateValue(JigsawNode jNode) {
    // �����ڵ㲻��ȷ���������
    int uncorrectAfter = 0;
    int dimension = JigsawNode.getDimension();
    for (int index = 1; index < dimension * dimension; index++) {
      if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1])//��ǰλ�õĺͺ����������ȷ
        uncorrectAfter++;
    }

    //��ǰ��λ�ýڵ����
    int uncorrectPiece = 0;
    for (int index = 1; index < dimension * dimension; index++) {
      if (jNode.getNodesState()[index] != endJNode.getNodesState()[index])
        uncorrectPiece++;
    }
    // �ҵ������پ��룬ע����ÿ��λ�õ������پ���ĺ�
    int manhattanDistance = 0;
    int distance = 0;
    for (int currentIndex = 1; currentIndex < dimension * dimension; currentIndex++) {
      for (int targetIndex = 1; targetIndex < dimension * dimension; targetIndex++) {
        // ����ѭ�����ҵ�ÿһ��λ�õľ��룬��(0,0)�����һ���ڵ�
        int currentPiece = jNode.getNodesState()[currentIndex];
        int targetPiece = endJNode.getNodesState()[targetIndex];
        
        /*
         * ���������Ŀ��˼�������Կ϶����ǣ�����0ֵ�ýڵ�һ������ƴͼ��ĩβ��λ�ã�Ҳ������endNode��list��ĩβ��λ�ã�
         * ���Ե�һ��Ŀ���Ȼ������0����ʼ�������㣺</br>
         */
        if (currentPiece != 0 && currentPiece == targetPiece) {
          int currentX = (currentIndex) / dimension;//��һά�������ﾭ������õ���ά������������
          int currentY = (currentIndex) % dimension;//�򵥵�����ת���ɷ���������ķ���
          int targetX = (targetIndex) / dimension;
          int targetY = (targetIndex) % dimension;
          int dx = Math.abs(currentX - targetX);
          int dy = Math.abs(currentY - targetY);
          manhattanDistance += (dx + dy);
          distance += Math.sqrt(dx * dx + dy * dy);
          break;
        }
      }
    }
    
    /*
     * ����Ȩ�ص�ʱ��ѡ�����Ȩ�صĻ��ʣ����⿼�ǵ������پ����ڲ�����λ�ýڵ�֮���Ȩ�ؼ����ڰ��ף�����Ȧ������΢��һ�� </br>
     * �����Ȩ�����⸳ֵ�����Ƕ�Ҫ����Ȩ�ص���������ɣ����Ӱ�쵽������㷨Ч��
     */
    int estimate = (int) (uncorrectAfter * 383 + manhattanDistance * 617 + distance * 201 + uncorrectPiece * 23);
    jNode.setEstimatedValue(estimate);
  }

}
