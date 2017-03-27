package text_process;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Textread {
	Dataconfig dataconfig = new Dataconfig();
	String datadir = dataconfig.datadir;
	ArrayList<ArrayList<String>> docs;
	ArrayList<String> stopwords;
	
	public Textread(){
		stop_words sw = new stop_words();
		stopwords = sw.stopWords;
		//to do...
		docs = new ArrayList<ArrayList<String>>();
	}
	
	public void readDocs() throws IOException{// get all names of docs
		String docsPath = this.datadir;
		for(File docFile : new File(docsPath).listFiles()){
			BufferedReader reader = null;
	        try {
	            //System.out.println("以行为单位读取文件内容，一次读一整行：");
	            reader = new BufferedReader(new FileReader(docFile));
	            String tempString = null;
	            //int line = 1;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	            	if(tempString.length()<5){
	            		continue;
	            	}
	                String str = tempString.substring(0, 3);
	                System.out.println(str);
	            	if(str.equals("摘要:")){
	            		String temp = tempString.substring(3);
	            		String []strs = temp.split("[ ,.?!()]");
	            		ArrayList<String> tempArray = new ArrayList<String>();
	            		for(String s:strs){
	            			s=s.toLowerCase();
	            			tempArray.add(s);
	            		}
	            		docs.add(tempArray);
	            	}
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
		}
	}
	public static void main(String args[]) throws IOException{
		Textread textread = new Textread();
		textread.readDocs();
		ArrayList<ArrayList<String>> temp = textread.docs;
		int c=0,lc=0;
		for(ArrayList<String> arr:temp){
			for(String s:arr){
				c++;
				System.out.print(s+" ");
			}
			lc++;
			System.out.println();
		}
		System.out.println(c+" "+lc);
	}

}
