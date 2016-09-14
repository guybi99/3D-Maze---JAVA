package io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public class MyDecompressorInputStream extends InputStream{
	
	InputStream in;
	
	/**
	 * Constructor
	 * @param in InputStream This is the input stream to read from
	 */
	public MyDecompressorInputStream(InputStream in) {
		super();
		this.in = in;
	}
	
	public int getSize() throws IOException{
		byte[] b_size = new byte[4];
		for(int j = 0 ; j < 4 ; j++){
			b_size[j] = new Integer(in.read()).byteValue();
		}
		
		int size = ByteBuffer.wrap(b_size).getInt();
		
		return size;
	}

	@Override
	public int read(byte[] b) throws IOException
	{
		byte by;
		int temp;
		int count = 0;
		int i = 0;
		
		while( (temp = in.read()) != -1) {
			by =  new Integer(temp).byteValue();
			count = in.read();
//			System.out.println(by);
//			System.out.println(count);
			while(count>0)
			{
				b[i] = by;
				count--;
				i++;
			}
		}
		
		return 1;
	}

	@Override
	public int read() throws IOException 
	{		
		return (int)in.read();
		
	}

}
