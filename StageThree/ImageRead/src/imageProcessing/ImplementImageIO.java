package imageProcessing;
import java.awt.Graphics2D;//���ÿ�ĵ��ã���������һ��destͼ��
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;//ͼ����Ϣ�Ļ���洢����
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import imagereader.IImageIO;//�ӿ��ļ����ڵİ�

public class ImplementImageIO implements IImageIO {//ʵ�ֽӿ�IImageIO������

	@Override
	public Image myRead(String pathname) {//������ImageIO����ͬ�ĺ����������Ƕ���ͼƬ�ļ��ĺ���
	    File file = new File(pathname);//�ļ�·��
	    try {
		    @SuppressWarnings("resource")
			FileImageInputStream infile = new FileImageInputStream(file);
		    byte headInfo[] = new byte[54];//��ȡ����54�ֽڵ��ļ�bit
		    infile.read(headInfo, 0, 54);
		    int widthOfImage = (int)((headInfo[21] & 0xff)<<24 | (headInfo[20] & 0xff)<<16  | //��byte���͵�����ת����Ϊchar�޷�����
		    		                  (headInfo[19] & 0xff)<<8 |(headInfo[18] & 0xff));//���ڴ洢ͼ��Ŀ����Ϣ
		    int heightOfImage = (int)((headInfo[25] & 0xff)<<24 | (headInfo[24] & 0xff)<<16  | 
		                              (headInfo[23] & 0xff)<<8 |(headInfo[22] & 0xff));
		    int sizeOfImage = (int)((headInfo[37] & 0xff)<<24 | (headInfo[36] & 0xff)<<16  | 
		                            (headInfo[35] & 0xff)<<8 |(headInfo[34] & 0xff));
		    
		    int byteNumOfbmp = (int)( (headInfo[29] & 0xff) << 8 | (headInfo[28] & 0xff) );//ͼ����ֽ���Ŀ�Ĵ�С
		    int numOfUsedColor = (int)((headInfo[49] & 0xff)<<24 | (headInfo[48] & 0xff)<<16  | 
                               (headInfo[47] & 0xff)<<8 |(headInfo[46] & 0xff));
		    if (byteNumOfbmp == 24) {
			    //���ϵ��¡������Ҽ���ͼ��
		    	infile.skipBytes(numOfUsedColor*4);//�����4�ı����Ļ���ֱ�����������õ������ֵ
		    	int sizeOfEmpty = sizeOfImage/heightOfImage - 3*widthOfImage;
			    byte allBytes[] = new byte[sizeOfImage];
			    int arrayOfPix[] = new int[heightOfImage*widthOfImage];//���ص�ļ�������Ĵ�С
			    infile.read(allBytes, 0, sizeOfImage);
			    int current = 0;
			    for (int i = heightOfImage-1; i >= 0; i--) {
			    	for (int j = 0; j < widthOfImage; j++) {
			    		arrayOfPix[i*widthOfImage+j] = (int)((0xff)<<24 | (allBytes[current+2] & 0xff)<<16  | 
			                                    (allBytes[current+1] & 0xff)<<8 | (allBytes[current] & 0xff));
			    		current = current +3;
			    	}
			    	current = current + sizeOfEmpty;
			    }
		    infile.close();
		    Image image = Toolkit.getDefaultToolkit().createImage((ImageProducer) new MemoryImageSource(widthOfImage, heightOfImage, arrayOfPix, 0, widthOfImage));
		    
		    return image;
	    	}
		    if (byteNumOfbmp == 8) {
			    //���ϵ��´����ҵļ���
		    	infile.skipBytes(numOfUsedColor*4);//����4�ı��������ص�
		    	int sizeOfEmpty = sizeOfImage/heightOfImage - widthOfImage;
			    byte allBytes[] = new byte[sizeOfImage];
			    int arrayOfPix[] = new int[heightOfImage*widthOfImage];
			    infile.read(allBytes, 0, sizeOfImage);
			    int current = 0;
			    for (int i = heightOfImage-1; i >= 0; i--) {
			    	for (int j = 0; j < widthOfImage; j++) {
			    		arrayOfPix[i*widthOfImage+j] = (int)((0xff << 24) | ((allBytes[current] & 0xff) << 16)|
			    				                        ((allBytes[current] & 0xff) << 8) |(allBytes[current] & 0xff));
			    		current = current + 1;
			    	}
			    	current = current + sizeOfEmpty;
			    }
		    infile.close();
		    Image image = Toolkit.getDefaultToolkit().createImage((ImageProducer) new MemoryImageSource(widthOfImage, heightOfImage, arrayOfPix, 0, widthOfImage));
		    //���ÿ����ú�����ͼ�����Ϣ�����image
		    return image;
	    	}
	    	
	    } 
	    catch (IOException e) {//�����쳣�Ĳ���
		    e.printStackTrace();  
	    }
        return (Image)null;//����쳣�Ļ���ֱ�ӷ���һ����ָ��
	}

	@Override
	public Image myWrite(Image img, String filepath) {//����write����
		try{
			File imgFile = new File(filepath);//һ����˼·�����һ���ļ���·�������ڶ�ȡ�ļ�������
			BufferedImage buffer = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D graph = buffer.createGraphics(); //�������õĺ�������ͼ������������ͼ��Ĵ洢�ռ�
			graph.drawImage(img, 0, 0, null);//�������������ͼ�񣬵�ȻҲ���Բ��Ǵ����ͼ��ȡ���ڵ��õĲ���������
			graph.dispose();
			ImageIO.write(buffer, "bmp", imgFile );
			return img;//����ͼ�����Ϣ
		}
		catch(Exception e)//�׳��쳣
		{
			e.printStackTrace();
		}
		return img;//������Ҫ��ͼ��д��һ���ļ������Լ�ʹ�������õ����ļ�����Ŀ����ļ���
		//�����׳��쳣֮�󣬵õ�һ���յ��ļ�ͼ��
	}

}
