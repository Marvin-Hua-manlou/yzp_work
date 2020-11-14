public class test {
    public static void main(String[] args) {
    	byte a[] = new byte[5];
    	a[0]= 0x9;
    	a[1] = 0x2;
		System.out.println(((a[0]&0xff) << 16));
	}
}
