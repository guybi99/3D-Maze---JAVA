package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		super();
		this.out = out;
	}
	
	@Override
	public void write(byte[] byte_array){
		int counter = 0;
		byte last = 0;
		
		for (int i = 0; i < byte_array.length; i++){
			if(byte_array[i] != last){
				
				try {
					if(counter > 0){
						out.write(last);
						out.write(counter);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				last = byte_array[i];
				counter = 1;
				
			}else{
				counter++;
				
				if(counter == 255){
					try {
						out.write(last);
						out.write(counter);
					} catch (IOException e) {
						e.printStackTrace();
					}
				
					counter = 0;
				}
			}
		}
		
		if(counter > 0){
			try {
				out.write(last);
				out.write(counter);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void write(int b) throws IOException {
		out.write((byte) b);
	}
}