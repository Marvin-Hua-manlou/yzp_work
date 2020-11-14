package imageProcessing;
import java.awt.Image;
import java.awt.Toolkit;//导入需要的包名，提供给饿哦们函数的操作
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import imagereader.IImageProcessor;

/*
 * 实现接口 <code> IImageProcessor </code>的内容，接口包含在文件imagereader当中
 */
public class ImplementImageProcessor implements IImageProcessor {

	@Override//重载函数
	public Image showChanelB(Image image) {//输出蓝色图像的函数
		Filter filter = new Filter("Blue");//构造内容，蓝色
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), filter));
		return img;
	}

	@Override//重载各个函数
	public Image showChanelG(Image image) {//输出绿色图像的函数
		Filter filter = new Filter("Green");
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), filter));
		return img;//返回图像
	}

	@Override
	public Image showChanelR(Image image) {
		Filter filter = new Filter("Red");
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), filter));
		return img;//返回图像
	}

	@Override
	public Image showGray(Image image) {//输出灰色图像的函数
		Filter filter = new Filter("Gray");
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), filter));
		return img;//返回图像
	}

}

class Filter extends RGBImageFilter {//获得需要的rgb值
    private String color;
    public  Filter(String color) {
        this.color = color;//构造函数，建立RGB的映射关系
	}
	@Override
	public int filterRGB(int x, int y, int rgb) {//获得直接的rgb
		try {
			if (color.equals("Red")) {
				return (int)(rgb & 0xffff0000);//位运算，掩码用来生成一个指定的RGB
			}
			else if (color.equals("Green")){
				return (int)(rgb & 0xff00ff00);
			}
			else if (color.equals("Blue")){
				return (int)(rgb & 0xff0000ff);//三个RGB颜色状态的掩码内容不一样
			}
			else if (color.equals("Gray")){
				int temp = (int)( ((rgb & 0x00ff0000) >> 16)*0.299 + ((rgb & 0x0000ff00) >> 8 )*0.587  
	                    + ((rgb & 0x000000ff))*0.114 ); //依据文件给出的灰度转换公式得到的灰度的RGB
	            return (rgb & 0xff000000) + (temp << 16) + (temp << 8) + temp;  
			}
			else {
				throw new Exception("Invalid!");//抛出异常函数
			}
		}
		catch(Exception e) {
			 System.out.println(e.getMessage());//打印具体的异常信息
		}
		return 0;
	}
	
} 
