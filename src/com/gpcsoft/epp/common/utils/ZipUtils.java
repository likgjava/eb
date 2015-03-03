package com.gpcsoft.epp.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.dom4j.Document;

import com.gpcsoft.core.utils.FileUtil;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

/**
 * 
 *使用ant下的包 zip的解压缩工具类
 */
public class ZipUtils {
	static final int BUFFER = 1024;

	/**
	 * 用zip格式压缩文件
	 * 
	 * @param zipFileName
	 *            压缩后的文件名 包含路径 如："c:\\test.zip"
	 * @param inputFile
	 *            要压缩的文件 可以是文件或文件夹 如："c:\\test" 或 "c:\\test.doc"
	 * @throws Exception
	 * 
	 *            ant下的zip工具默认压缩编码为UTF-8编码，而winRAR软件压缩是用的windows默认的GBK或者GB2312编码
	 * 
	 *            用ant压缩后放到windows上面会出现中文文件名乱码，用winRAR压缩的文件，用ant解压也会出现乱码，
	 * 
	 *            所以，在用ant处理winRAR压缩的文件时，要设置压缩编码
	 */
	public static void zip(File zipFile, String inputFile) throws Exception {
		ZipOutputStream out = null;
		try {
			File f = new File(inputFile);
			// File temp = new File(zipFileName);
			out = new ZipOutputStream(new FileOutputStream(zipFile));
			// 设置压缩编码
			out.setEncoding("GBK");// 设置为GBK后在windows下就不会乱码了，如果要放到Linux或者Unix下就不要设置了
			zip(out, f, "");// 递归压缩方法
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	private static void zip(ZipOutputStream out, File f, String base)
			throws Exception {
		// System.out.println("Zipping   " + f.getName()); // 记录日志，开始压缩
		if (f.isDirectory()) { // 如果是文件夹，则获取下面的所有文件
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));// 此处要将文件写到文件夹中只用在文件名前加"/"再加文件夹名
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else { // 如果是文件，则压缩
			out.putNextEntry(new ZipEntry(base)); // 生成下一个压缩节点
			FileInputStream in = new FileInputStream(f); // 读取文件内容
			int len;
			byte[] buf = new byte[BUFFER];
			while ((len = in.read(buf, 0, BUFFER)) != -1) {
				out.write(buf, 0, len); // 写入到压缩包
			}
			in.close();
		}
	}

	/**
	 * 解压缩zip文件
	 * 
	 * @param fileName
	 *            要解压的文件名 包含路径 如："c:\\test.zip"
	 * @param filePath
	 *            解压后存放文件的路径 如："c:\\temp"
	 * @throws Exception
	 */
	public static void unZip(String fileName, String filePath) throws Exception {
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(fileName, "GBK"); // 以“GBK”编码创建zip文件，用来处理winRAR压缩的文件。
			Enumeration emu = zipFile.getEntries();
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				if (entry.isDirectory()) {
					new File(filePath + entry.getName()).mkdirs();
					continue;
				}
				bis = new BufferedInputStream(zipFile.getInputStream(entry));

				File file = new File(filePath + entry.getName());
				File parent = file.getParentFile();
				if (parent != null && (!parent.exists())) {
					parent.mkdirs();
				}
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, BUFFER);
				byte[] buf = new byte[BUFFER];
				int len = 0;
				while ((len = bis.read(buf, 0, BUFFER)) != -1) {
					fos.write(buf, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(bos!=null){
				bos.flush();
				bos.close();
				bis.close();
				zipFile.close();
			}
		}
	}

	// 压缩文件夹内指定文件方法
	public static void zipFiles(File zipFile, String inputFile,
			String[] fileNames) throws Exception {
		ZipOutputStream out = null;
		try {
			File f = new File(inputFile);
			out = new ZipOutputStream(new FileOutputStream(zipFile));
			out.setEncoding("GBK");// 设置为GBK后在windows下就不会乱码了，如果要放到Linux或者Unix下就不要设置了
			out.setLevel(0); // 设置压缩级别
			zipByFileNames(out, f, fileNames);// 递归压缩方法
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	private static void zipByFileNames(ZipOutputStream out, File f,
			String[] fileNames) throws Exception {
		// System.out.println("Zipping   " + f.getName()); // 记录日志，开始压缩
		if (f.isDirectory()) { // 如果是文件夹，则获取下面的所有文件
			File[] fl = f.listFiles();
			// out.putNextEntry(new ZipEntry(base + "/"));//
			// 此处要将文件写到文件夹中只用在文件名前加"/"再加文件夹名
			// base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				File file = fl[i];
				for (int j = 0; j < fileNames.length; j++) {
					if (fileNames[j].equals(file.getName())) {
						out.putNextEntry(new ZipEntry(file.getName())); // 生成下一个压缩节点
						FileInputStream in = new FileInputStream(file); // 读取文件内容
						int len;
						byte[] buf = new byte[BUFFER];
						while ((len = in.read(buf, 0, BUFFER)) != -1) {
							out.write(buf, 0, len); // 写入到压缩包
						}
						in.close();
					}
				}
			}
		}
	}

	// 根据文件名称删除文件
	public static void deleteByFileNames(String path, String[] fileNames) {
		File files = new File(path);
		File[] flieList = files.listFiles();
		for (int i = 0; i < flieList.length; i++) {
			String fileName = flieList[i].getName();
			String filePath = flieList[i].getPath();
			for (int j = 0; j < fileNames.length; j++) {
				if (fileName.equals(fileNames[j])) {
					FileUtil.delete(filePath);
				}
			}
		}
	}
	
	/**
	 * FunName: readFileByFileName 
	 * Description : 从压缩包中读取指定的XML文件
	 * @param zipFilePath: 压缩文件绝对路径
	 * @param fileName 指定读取的文件名
	 * @return Document：返回该XML的Document对象，如果未找到该文件则返回null
	 * @throws IOException 
	 * @Author: zhouzhanghe
	 * @Create Date: 2011-07-04 16:16
	 */
	public static Document readXMLFileFromZipFileByfileName(String zipFilePath, String fileName) throws IOException{
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFilePath, "GBK"); // 以“GBK”编码创建zip文件，用来处理winRAR压缩的文件。
			Enumeration emu = zipFile.getEntries();
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				if(entry.getName().indexOf(fileName) > -1){
					org.w3c.dom.Document documentW3c = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(zipFile.getInputStream(entry));
					org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
					return xmlReader.read(documentW3c);
				}else
					continue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			zipFile.close();
		}
		return null;
	}
	

	//判断文件是否存在
	public static boolean fileIsExist(String path,String fileName){
		boolean isExist = false;
		String[] fileList =	FileUtil.listFiles(path);
		if(fileList != null && fileList.length > 0){
			for(int i=0;i<fileList.length;i++){
				if(fileName.equals(fileList[i])){
					isExist = true;
				}
			}
		}
		return isExist;
	}

	/**
	 * FunName: whetherTheFileExistsInZipFile 
	 * Description : 判断压缩包中是否存在指定文件
	 * @param zipFilePath: 压缩文件绝对路径
	 * @param fileName 查找的文件名
	 * @return boolean：是否存在该文件
	 * @throws IOException 
	 * @Author: zhouzhanghe
	 * @Create Date: 2011-07-08 15:02
	 */
	public static boolean whetherTheFileExistsInZipFile(String zipFilePath, String fileName) throws IOException{
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFilePath, "GBK"); // 以“GBK”编码创建zip文件，用来处理winRAR压缩的文件。
			Enumeration emu = zipFile.getEntries();
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				if(entry.getName().indexOf(fileName) > -1){
					return true;
				}else
					continue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			zipFile.close();
		}
		return false;
	}
	
	/**
	 * 解压rar格式压缩包。
	 * 对应的是java-unrar-0.3.jar，但是java-unrar-0.3.jar又会用到commons-logging-1.1.1.jar
	 */
	public static void unrar(String sourceRar,String destDir) throws Exception{
		Archive a = null;
		FileOutputStream fos = null;
		try{
			a = new Archive(new File(sourceRar));
			FileHeader fh = a.nextFileHeader();
			while(fh!=null){
				if(!fh.isDirectory()){
					//1 根据不同的操作系统拿到相应的 destDirName 和 destFileName
					String compressFileName = fh.getFileNameString().trim();
					String destFileName = "";
					String destDirName = "";
					//非windows系统
					if(File.separator.equals("/")){
						destFileName = destDir + compressFileName.replaceAll("\\\\", "/");
						destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));
					//windows系统	
					}else{
						destFileName = destDir + compressFileName.replaceAll("/", "\\\\");
						destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));
					}
					//2创建文件夹
					File dir = new File(destDirName);
					if(!dir.exists()||!dir.isDirectory()){
						dir.mkdirs();
					}
					//3解压缩文件
					fos = new FileOutputStream(new File(destFileName));
					a.extractFile(fh, fos);
					fos.close();
					fos = null;
				}
				fh = a.nextFileHeader();
			}
			a.close();
			a = null;
		}catch(Exception e){
			throw e;
		}finally{
			if(fos!=null){
				try{fos.close();fos=null;}catch(Exception e){e.printStackTrace();}
			}
			if(a!=null){
				try{a.close();a=null;}catch(Exception e){e.printStackTrace();}
			}
		}
	}
	

}
