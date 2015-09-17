package edu.iiip.preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Writer {
    
	private PrintWriter pw;
	
    public Writer(){
    	File file = new File("./data/result/train.txt");
    	try {
			pw =  new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public void append(String msg){
    	pw.println(msg);
    }
    
    public void close(){
    	pw.close();
    }
    
//    public static void main(String[] args) {
//		Writer write = new Writer();
//		write.append("1234567");
//		write.close();
//	}
}
