package imageProcessing;
//import javax.imageio.ImageReader;
import imagereader.Runner;

public class ImageRunner {
    public static void main(String[] args) {
        ImplementImageIO IO = new ImplementImageIO();//����ͼ��
        ImplementImageProcessor processor = new ImplementImageProcessor();//ͼ��Ĵ���
        Runner.run(IO, processor);//��ͼ�����Run���ǵĵõ��Ľ��
	} 		
    
}
