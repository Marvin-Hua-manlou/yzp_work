//import java.awt.Graphics2D;  
//import java.awt.Image;  
//import java.awt.Toolkit;  
//import java.awt.image.BufferedImage;  
//import java.awt.image.ImageProducer;  
//import java.awt.image.MemoryImageSource;  
//import java.io.File;  
////import java.io.FileInputStream;  
////import java.io.FileOutputStream;       
//import java.io.IOException;
//import javax.imageio.ImageIO;  
//import javax.imageio.stream.FileImageInputStream;
//import imagereader.IImageIO;  
//      
//    public class myIImageIO implements IImageIO{  
//     // Image img;  
//        
//        public Image myRead(String filePath){
//            File file = new File(filePath);
//    	    try {
//    		    FileImageInputStream infile = new FileImageInputStream(file);
//    		    byte headInfo[] = new byte[54];
//    		    infile.read(headInfo, 0, 54);
//    		    int widthOfImage = (int)((headInfo[21] & 0xff)<<24 | (headInfo[20] & 0xff)<<16  | 
//    		    		                  (headInfo[19] & 0xff)<<8 |(headInfo[18] & 0xff));
//    		    int heightOfImage = (int)((headInfo[25] & 0xff)<<24 | (headInfo[24] & 0xff)<<16  | 
//    		                              (headInfo[23] & 0xff)<<8 |(headInfo[22] & 0xff));
//    		    int sizeOfImage = (int)((headInfo[37] & 0xff)<<24 | (headInfo[36] & 0xff)<<16  | 
//    		                            (headInfo[35] & 0xff)<<8 |(headInfo[34] & 0xff));
//    		    int sizeOfEmpty = sizeOfImage/heightOfImage - 3*widthOfImage;
//    		    if (sizeOfEmpty == 4)
//    		    	sizeOfEmpty = 0;
//    		    
//    		    //from down to top and from left to right
//    		    byte allBytes[] = new byte[sizeOfImage];
//    		    int arrayOfPix[] = new int[heightOfImage*widthOfImage];
//    		    infile.read(allBytes, 0, sizeOfImage);
//    		    int current = 0;
//    		    for (int i = heightOfImage-1; i >= 0; i--) {
//    		    	for (int j = 0; j < widthOfImage; j++) {
//    		    		arrayOfPix[i*widthOfImage+j] = (int)((0xff)<<24 | (allBytes[current+2] & 0xff)<<16  | 
//    		                                    (allBytes[current+1] & 0xff)<<8 | (allBytes[current] & 0xff));
//    		    		current = current +3;
//    		    	}
//    		    	current = current + sizeOfEmpty;
//    		    }
//    		    infile.close();
//    		    Image image = Toolkit.getDefaultToolkit().createImage((ImageProducer) new MemoryImageSource(widthOfImage, heightOfImage, arrayOfPix, 0, widthOfImage));
//    		    return image;
//    	    } 
//    	    catch (IOException e) {
//    		    e.printStackTrace();  
//    	    }
//            return (Image)null;
//        }  
//       
//
//		@Override
//		public Image myWrite (Image img, String filepath)
//		{
//			try{
//				File imgFile = new File(filepath + "bmp");
//				BufferedImage buffer = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
//				Graphics2D graph = buffer.createGraphics(); 
//				graph.drawImage(img, 0, 0, null);
//				graph.dispose();
//				ImageIO.write(buffer, "bmp", imgFile );
//				return img;
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//			return img;
//		} 
//}