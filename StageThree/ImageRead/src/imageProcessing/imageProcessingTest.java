package imageProcessing;
import static org.junit.Assert.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import org.junit.Before;
import org.junit.Test;

public class imageProcessingTest {
	private ImplementImageIO io = new ImplementImageIO();
	private ImplementImageProcessor processor = new ImplementImageProcessor();
	//��ȡ����ͼƬ��·�����������κε�ͼƬ����������Ҫbmp�ĸ�ʽ
	private Image img1 = io.myRead("F:\\eclipse-Worksapce\\ImageRead\\bin\\imageProcessing\\1.bmp");
	private Image img2 = io.myRead("F:\\eclipse-Worksapce\\ImageRead\\bin\\imageProcessing\\2.bmp");
	@Before
	public void setUp() throws Exception {	
		;
	}

	@Test
	public void greenTest() {//����showChanelG����
		Image img1Test = processor.showChanelG(img1);
		Image img2Test = processor.showChanelG(img2);
		testMethod("green",img1Test,img2Test);
	}
	
	@Test
	public void redTest() {//����showChanelR����
		Image img1Test = processor.showChanelR(img1);
		Image img2Test = processor.showChanelR(img2);
		testMethod("red",img1Test,img2Test);
	}
	
	@Test
	public void blueTest() {//����showChanelB����
		Image img1Test = processor.showChanelB(img1);
		Image img2Test = processor.showChanelB(img2);
		testMethod("blue",img1Test,img2Test);
	}
	
	@Test
	public void grayTest() {//����showGray����
		Image img1Test = processor.showGray(img1);
		Image img2Test = processor.showGray(img2);
		testMethod("gray",img1Test,img2Test);
	}
	
	
	public static int[] getPixes(BufferedImage bimg) {//��õ�ǰͼ������ص���Ϣ
		int w = bimg.getWidth();
		int h = bimg.getHeight();
		int[] rgbs = new int[h * w];
		bimg.getRGB(0, 0, w, h, rgbs, 0, w);//���ÿ�����ص�rgb����Ϣ
		return rgbs;
	}
	
	public void testMethod(String color, Image img1Test, Image img2Test) {
		//��ͼƬ�Ĵ洢·�������ȡͼƬ������
		Image img1Goal = io.myRead("F:\\eclipse-Worksapce\\ImageRead\\bin\\imageProcessing\\goal\\1_"+color+"_goal.bmp");
		Image img2Goal = io.myRead("F:\\eclipse-Worksapce\\ImageRead\\bin\\imageProcessing\\goal\\2_"+color+"_goal.bmp");	
		
		//Test image's width and height
		BufferedImage bufferTest1 = toBufferedImage(img1Test);//������ǵĺ��������ͼ�������Ŀ���ͼ���Ƿ�һ��
		BufferedImage bufferGoal1 = toBufferedImage(img1Goal);
		assertEquals(bufferTest1.getWidth(null), bufferGoal1.getWidth(null));//�׳���һ�µ���Ϣ
		assertEquals(bufferTest1.getHeight(), bufferGoal1.getHeight());
		
		BufferedImage bufferTest2 = toBufferedImage(img2Test);
		BufferedImage bufferGoal2 = toBufferedImage(img2Goal);
		assertEquals(bufferTest2.getWidth(null), bufferGoal2.getWidth(null));
		assertEquals(bufferTest2.getHeight(), bufferGoal2.getHeight());
		
		//����ÿһ�����ص�
		int pixTest1[] = getPixes(bufferTest1);
		int pixGoal1[] = getPixes(bufferGoal1);
		for (int i = 0; i < pixTest1.length; i++) {
			assertEquals(pixTest1[i], pixGoal1[i]);
		}
		//����ÿһ�����ص����Ϣ
		int pixTest2[] = getPixes(bufferTest1);
		int pixGoal2[] = getPixes(bufferGoal1);
		for (int i = 0; i < pixTest2.length; i++) {
			assertEquals(pixTest2[i], pixGoal2[i]);
		}
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    //����һ��buffer��������洢ͼ��
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    //�������������Ļ��浱�л������Ǿ��������ͼ��
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    //����ͼ�����Ϣ
	    return bimage;
	}

}
