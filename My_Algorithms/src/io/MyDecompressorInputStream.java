package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream{
	
	InputStream in;
	
	public MyDecompressorInputStream(InputStream in) {
		super();
		this.in = in;
	}

	/**
	 * this method is read from the file array of bytes and bring us what he read
	 * @param b is the array we want to read
	 * @return int
	 */
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
	
	/**
	 * this method is read from the file and bring us what he get
	 * @return int
	 */
	@Override
	public int read() throws IOException 
	{		
		return (int)in.read();
		
	}

}
