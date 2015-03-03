package com.gpcsoft.bizplatform.base.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.springframework.security.AccessDeniedException;

/**
 * CompressUtil 文件操作工具类
 * 
 * @author liqy date 2009-12-10
 */
public class FileOperateUtil {
	public FileOperateUtil() {
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/Myfile
	 * @return boolean
	 */
	public void newFolder(String folderPath) throws Exception {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			System.out.println("新建目录操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/Myfile.txt
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 */
	public void newFile(String filePathAndName, String fileContent)
			throws Exception {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();
		} catch (Exception e) {
			System.out.println("新建目录操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/Myfile.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public void delFile(String filePathAndName) throws Exception {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param filePathAndName
	 *            String 文件夹路径及名称 如c:/Myfile
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public void delFolder(String folderPath) throws Exception {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
			System.out.println("删除文件夹操作成功！");
		} catch (Exception e) {
			System.out.println("删除文件夹操作出错！");
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/Myfile
	 */
	public void delAllFile(String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/Myfile.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/Myfile.txt
	 * @return boolean
	 */
	public void copyFile(String oldPath, String newPath) throws Exception {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.flush();
				fs.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/Myfile
	 * @param newPath
	 *            String 复制后路径 如：f:/Myfile/file
	 * @return boolean
	 */
	public void copyFolder(String oldPath, String newPath) throws Exception {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/Myfile.txt
	 * @param newPath
	 *            String 如：d:/Myfile.txt
	 */
	public void moveFile(String oldPath, String newPath) throws Exception {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/Myfile.txt
	 * @param newPath
	 *            String 如：d:/Myfile.txt
	 */
	public void moveFolder(String oldPath, String newPath) throws Exception {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 读取文件成字符流输出
	 * 
	 * @param Path
	 *            String 如：c:/Myfile.txt
	 * @return
	 */
	public String readFileByString(String Path) throws Exception {

		File myFile = new File(Path);
		String str = null;
		StringBuffer sb = new StringBuffer();
		if (!myFile.exists()) {// 读文件内容
			throw new AccessDeniedException("文件路径请求错误!");
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(myFile));

			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
    
	/**
	 * 读取文件成输出
	 * 
	 * @param Path
	 *            String 如：c:/Myfile.txt
	 * @return
	 */
	public static String readFileByLines(String fileName) {
		String content = "";
		if ((fileName != null) && (!"".equals(fileName))) {
			BufferedReader reader = null;
			try {
				FileInputStream file = new FileInputStream(fileName);

				InputStreamReader ins = new InputStreamReader(file, "UTF-8");

				reader = new BufferedReader(ins);
				String tempString = null;

				while ((tempString = reader.readLine()) != null) {
					content = content + tempString + "\n";
				}
			} catch (IOException e) {
				content = e.getMessage();

				if (reader != null)
					try {
						reader.close();
					} catch (IOException e1) {
						content = e1.getMessage();
					}
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
						content = e1.getMessage();
					}
				}
			}
		}
		return content;
	}

}
