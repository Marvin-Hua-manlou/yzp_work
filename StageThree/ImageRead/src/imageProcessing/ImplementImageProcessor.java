package imageProcessing;
import java.awt.Image;
import java.awt.Toolkit;//������Ҫ�İ������ṩ����Ŷ�Ǻ����Ĳ���
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import imagereader.IImageProcessor;

/*
 * ʵ�ֽӿ� <code> IImageProcessor </code>�����ݣ��ӿڰ������ļ�imagereader����
 */
public class ImplementImageProcessor implements IImageProcessor {

	@Override//���غ���
	public Image showChanelB(Image image) {//�����ɫͼ��ĺ���
		Filter filter = new Filter("Blue");//�������ݣ���ɫ
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), filter));
		return img;
	}

	@Override//���ظ�������
	public Image showChanelG(Image image) {//�����ɫͼ��ĺ���
		Filter filter = new Filter("Green");
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), filter));
		return img;//����ͼ��
	}

	@Override
	public Image showChanelR(Image image) {
		Filter filter = new Filter("Red");
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), filter));
		return img;//����ͼ��
	}

	@Override
	public Image showGray(Image image) {//�����ɫͼ��ĺ���
		Filter filter = new Filter("Gray");
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), filter));
		return img;//����ͼ��
	}

}

class Filter extends RGBImageFilter {//�����Ҫ��rgbֵ
    private String color;
    public  Filter(String color) {
        this.color = color;//���캯��������RGB��ӳ���ϵ
	}
	@Override
	public int filterRGB(int x, int y, int rgb) {//���ֱ�ӵ�rgb
		try {
			if (color.equals("Red")) {
				return (int)(rgb & 0xffff0000);//λ���㣬������������һ��ָ����RGB
			}
			else if (color.equals("Green")){
				return (int)(rgb & 0xff00ff00);
			}
			else if (color.equals("Blue")){
				return (int)(rgb & 0xff0000ff);//����RGB��ɫ״̬���������ݲ�һ��
			}
			else if (color.equals("Gray")){
				int temp = (int)( ((rgb & 0x00ff0000) >> 16)*0.299 + ((rgb & 0x0000ff00) >> 8 )*0.587  
	                    + ((rgb & 0x000000ff))*0.114 ); //�����ļ������ĻҶ�ת����ʽ�õ��ĻҶȵ�RGB
	            return (rgb & 0xff000000) + (temp << 16) + (temp << 8) + temp;  
			}
			else {
				throw new Exception("Invalid!");//�׳��쳣����
			}
		}
		catch(Exception e) {
			 System.out.println(e.getMessage());//��ӡ������쳣��Ϣ
		}
		return 0;
	}
	
} 
