package imageProcessing;
import java.awt.Graphics2D;//内置库的调用，用来生成一个dest图像
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;//图像信息的缓冲存储区域
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import imagereader.IImageIO;//接口文件所在的包

public class ImplementImageIO implements IImageIO {//实现接口IImageIO的内容

	@Override
	public Image myRead(String pathname) {//重载与ImageIO名相同的函数，这里是读入图片文件的函数
	    File file = new File(pathname);//文件路径
	    try {
		    @SuppressWarnings("resource")
			FileImageInputStream infile = new FileImageInputStream(file);
		    byte headInfo[] = new byte[54];//读取长度54字节的文件bit
		    infile.read(headInfo, 0, 54);
		    int widthOfImage = (int)((headInfo[21] & 0xff)<<24 | (headInfo[20] & 0xff)<<16  | //将byte类型的数据转化成为char无符号型
		    		                  (headInfo[19] & 0xff)<<8 |(headInfo[18] & 0xff));//用于存储图像的宽度信息
		    int heightOfImage = (int)((headInfo[25] & 0xff)<<24 | (headInfo[24] & 0xff)<<16  | 
		                              (headInfo[23] & 0xff)<<8 |(headInfo[22] & 0xff));
		    int sizeOfImage = (int)((headInfo[37] & 0xff)<<24 | (headInfo[36] & 0xff)<<16  | 
		                            (headInfo[35] & 0xff)<<8 |(headInfo[34] & 0xff));
		    
		    int byteNumOfbmp = (int)( (headInfo[29] & 0xff) << 8 | (headInfo[28] & 0xff) );//图像的字节数目的大小
		    int numOfUsedColor = (int)((headInfo[49] & 0xff)<<24 | (headInfo[48] & 0xff)<<16  | 
                               (headInfo[47] & 0xff)<<8 |(headInfo[46] & 0xff));
		    if (byteNumOfbmp == 24) {
			    //从上到下、从左到右检索图像，
		    	infile.skipBytes(numOfUsedColor*4);//如果是4的倍数的话，直接跳过检索该点的像素值
		    	int sizeOfEmpty = sizeOfImage/heightOfImage - 3*widthOfImage;
			    byte allBytes[] = new byte[sizeOfImage];
			    int arrayOfPix[] = new int[heightOfImage*widthOfImage];//像素点的集合数组的大小
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
			    //从上到下从左到右的检索
		    	infile.skipBytes(numOfUsedColor*4);//跳过4的倍数的像素点
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
		    //采用库内置函数将图像的信息输出到image
		    return image;
	    	}
	    	
	    } 
	    catch (IOException e) {//捕获异常的部分
		    e.printStackTrace();  
	    }
        return (Image)null;//如果异常的话，直接返回一个空指针
	}

	@Override
	public Image myWrite(Image img, String filepath) {//重载write函数
		try{
			File imgFile = new File(filepath);//一样的思路，获得一个文件的路径，用于读取文件的内容
			BufferedImage buffer = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D graph = buffer.createGraphics(); //调用内置的函数进行图像的输出，生成图像的存储空间
			graph.drawImage(img, 0, 0, null);//画出经过处理的图像，当然也可以不是处理的图像，取决于调用的参数的类型
			graph.dispose();
			ImageIO.write(buffer, "bmp", imgFile );
			return img;//返回图像的信息
		}
		catch(Exception e)//抛出异常
		{
			e.printStackTrace();
		}
		return img;//由于需要将图像写入一个文件，所以即使我们最后得到的文件不是目标的文件，
		//正在抛出异常之后，得到一个空的文件图像
	}

}
