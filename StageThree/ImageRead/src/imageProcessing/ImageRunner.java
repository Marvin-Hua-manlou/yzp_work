package imageProcessing;
//import javax.imageio.ImageReader;
import imagereader.Runner;

public class ImageRunner {
    public static void main(String[] args) {
        ImplementImageIO IO = new ImplementImageIO();//输入图像
        ImplementImageProcessor processor = new ImplementImageProcessor();//图像的处理
        Runner.run(IO, processor);//在图像界面Run我们的得到的结果
	} 		
    
}
