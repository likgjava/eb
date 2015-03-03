package com.gpcsoft.epp.common.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileViewer {
	public static void search(String path, String[] Extention, boolean isdepth,String Spath) {
    /* param path 搜索目录
     * param Extention 扩展名列表
     * param isdepth 是否要搜索子目录
     * param Spath 存储目录
     */
	 String index="";
	 for(int j=0; j<Extention.length;++j)
	 {
		List arrayList = FileViewer.getListFiles(path,Extention[j],true);
		
		if(arrayList.isEmpty())
		{
		System.out.println("没有符号要求的文件");
		}
		else
		{
		String message = "";
		message += "符号要求的文件数：" + arrayList.size() + "/r/n";
		System.out.println(message);
		
		for (Iterator i = arrayList.iterator(); i.hasNext();)
		{
		String temp = (String) i.next();
		System.out.println(temp);
		message += temp + "/r/n";
		}
		//appendMethod(Spath,message);
		 if(j==Extention.length-1) index+=message;
		}
	 }
	 System.out.println("/n/n/n**************/n"+index);
	 appendMethod(Spath,index);
	}
	
	public static List fileList = new ArrayList();

	/**
	*
	* param path 文件路径
	* param suffix 后缀名
	* param isdepth 是否遍历子目录
	* ;return
	*/
	public static List getListFiles(String path, String suffix, boolean isdepth)
	{
		File file = new File(path);
		return FileViewer.listFile(file ,suffix, isdepth);
	}

	public static List listFile(File f, String suffix, boolean isdepth)
	{
		//是目录，同时需要遍历子目录
		if (f.isDirectory()&& isdepth == true)
		{
		File[] t = f.listFiles();
		for (int i = 0; i < t.length; i++)
		{
		listFile(t[i], suffix, isdepth);
		}
		}
		else
		{
		String filePath = f.getAbsolutePath();
		
		if(suffix !=null)
		{
		int begIndex = filePath.lastIndexOf(".");//最后一个.(即后缀名前面的.)的索引
		String tempsuffix = "";
		
		if(begIndex != -1)//防止是文件但却没有后缀名结束的文件
		{
		tempsuffix = filePath.substring(begIndex + 1, filePath.length());
		}
		
		if(tempsuffix.equals(suffix))
		{
		fileList.add(filePath);
		}
		}
		else
		{
		//后缀名为null则为所有文件
		fileList.add(filePath);
		}
		
		}
		
		return fileList;
	}

	/**
	* 方法追加文件：使用FileWriter
	* param fileName
	* param content
	*/
	public static void appendMethod(String fileName, String content)
	{
		try
		{
			//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content + "/r/n");
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String path="D://gpcsoftfile//gxoa//GXTC-111322//bulletin";
        String spath="D://gpcsoftfile//gxoa//GXTC-111322//bulletin//index.txt";
        boolean isdepth=true;
        String[] extention={"doc","docx"};
        FileViewer fileViewer= new FileViewer();
//        fileViewer.search(path, extention, isdepth, spath);
        
        fileViewer.readDirFiles("D:\\gpcsoftfile\\gxoa\\GXTC-111322\\bulletin\\");
	}
	
		public static List readDirFiles(String dirName) {//dirName目录全路径
			   List fileList=new ArrayList();
			   try {
			    File file=new File(dirName);
			    File[] files=file.listFiles();
			    //FileFilter ff=new FileFilter(files);
			    for (int i = 0; i < files.length; i++) {
			     File tempFile=files[i];
			     if(tempFile.isDirectory()){
			      String subDirName=tempFile.getPath();
			      List list=readDirFiles(subDirName);
			      for (int j = 0; j < list.size(); j++) {
			       fileList.add(list.get(j));
			      }
			     }else{
			      String fileName=tempFile.getName();
			      if (!tempFile.isFile()){
			       continue;
			      }
			      fileList.add(tempFile);
			     }
			     if(i==(files.length-1)){
			      return fileList;
			     }
			    }
			   } catch (Exception e) {
			    e.printStackTrace();
			    return null;
			   }
			   return fileList;
			}
	}
