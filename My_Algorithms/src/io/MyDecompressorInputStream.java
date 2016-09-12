package io;

import java.io.IOException;
import java.io.InputStream;

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
