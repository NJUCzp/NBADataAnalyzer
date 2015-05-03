package databaseservice;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataBaseServiceImpl<T> implements DataBaseService{
	String filepath;
	ArrayList<T> arr;
	//ArrayList<String> strarr;
	//int numberOfFile=0;
	
	public DataBaseServiceImpl(String filepath,ArrayList<T> arr){
		this.filepath=filepath;
		this.arr=arr;
	}
	
	/*public DataBaseServiceImpl(String filepath,ArrayList<String> strarr){
		this.filepath=filepath;
		this.strarr=strarr;
	}*/
	
	/*public DataBaseServiceImpl(String filepath,ArrayList<T> arr,int numberOfFile){
		this.filepath=filepath;
		this.arr=arr;
		this.numberOfFile=numberOfFile;
	}*/
	//去除无用字符
	public static String encoding(String src){
		String result = "";
		for(int i=0;i<src.length();i++){
			if(src.charAt(i)=='│'||src.charAt(i)=='-')
				result=result+(src.charAt(i));
			else{
				if((int)src.charAt(i)>=48&&(int)src.charAt(i)<=125)
					result=result+(src.charAt(i));
			}				
		}
		return result;
	}
	
	//从临时数据文件中读取
	public void readFromTemp() {
		try{
			arr.clear();
			FileInputStream fileStream=new FileInputStream(filepath);
			ObjectInputStream is=new ObjectInputStream(fileStream);
			while(true){
				try{
					T po=(T)is.readObject();
					arr.add((T) po);
				}catch (EOFException e) {
					break;//if EOF end loop
				}
			}
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}// TODO Auto-generated method stub
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		try{
			FileOutputStream fileStream=new FileOutputStream(filepath);
			ObjectOutputStream os=new ObjectOutputStream(fileStream);
			for(T po:arr)
				os.writeObject(po);
			os.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void readFromfile() {
		try {
			String temp;
    		File file=new File(filepath);
    		/*if(file.isDirectory()){
        		String[] filelist=file.list();
        		for(int i=0;i<9;i++){
        			System.out.println(filelist[i]);
        			File detailFile=new File(filepath+"/"+filelist[i]);
        			FileInputStream fis=new FileInputStream(detailFile);
            		InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
            		BufferedReader br=new BufferedReader(isr);

            		while((temp=br.readLine())!=null){
            			String tempinfo=encoding(temp);
            			if(tempinfo.length()>0){
            				arr.add(tempinfo);
            			}
            		}
        			br.close();
        		}
    		}*/
    		
    		FileInputStream fis=new FileInputStream(file);
    		InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
    		BufferedReader br=new BufferedReader(isr);

    		while((temp=br.readLine())!=null){
    			String tempinfo=encoding(temp);
    			if(tempinfo.length()>0){
    				arr.add((T)tempinfo);
    			}
    		}
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// TODO Auto-generated method stub
		
	}

}
