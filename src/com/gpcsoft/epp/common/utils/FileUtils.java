package com.gpcsoft.epp.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import com.gpcsoft.epp.common.exception.EsException;

/**
 * Comments: <strong>文件处理</strong> <br/>
 * <br/>
 * CopyRright (c)2008-xxxx: 珠海政采软件技术有限公司 <br/>
 * Project: srplatform <br/>
 * Module ID: 权限平台 <br/>
 * Create Date：2010-6-28 下午07:45:13 by wangcl <br/>
 * Modified Date: 2010-6-28 下午07:45:13 by wangcl
 * 
 * @since 0.5
 * @version: 0.5
 */
public class FileUtils {
	/* 定义文件编码 */
	public static final String FILE_ENCODE_UTF_8 = "UTF-8";
	public static final String FILE_ENCODE_GBK = "GBK";

	/**
	 * 把内容写到文件
	 * 
	 * @param filePathName
	 *            文件名
	 * @param List
	 *            <String> 文件内容
	 */
	public static boolean writerFile(String filePathName, String content) {
		boolean flag = false;
		OutputStreamWriter osw = null;
		try {
			if (filePathName != null && !"".equals(filePathName)) {
				osw = new OutputStreamWriter(new FileOutputStream(filePathName), "UTF-8");
			}
		} catch (FileNotFoundException e1) {
			flag = false;
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			flag = false;
			e.printStackTrace();
		}
		if (osw != null) {
			BufferedWriter bw = new BufferedWriter(osw);
			try {
				if (content != null && !"".equals(content)) {
					bw.write(content);
					flag = true;
				}
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
			} finally {
				try {
					bw.close();
					osw.close();
				} catch (IOException e) {
					flag = false;
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 把内容写到文件或追加到文件中
	 * 
	 * @param filePathName
	 *            文件名
	 * @param List
	 *            <String> 文件内容
	 */
	public static boolean writerFileIsAppend(String filePathName, String content) {
		boolean flag = false;
		OutputStreamWriter osw = null;
		try {
			if (filePathName != null && !"".equals(filePathName)) {
				osw = new OutputStreamWriter(new FileOutputStream(filePathName, true));
			}
		} catch (Exception e1) {
			flag = false;
			e1.printStackTrace();
		}
		if (osw != null) {
			BufferedWriter bw = new BufferedWriter(osw);
			try {
				if (content != null && !"".equals(content)) {
					bw.write(content);
					flag = true;
				}
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
			} finally {
				try {
					bw.close();
					osw.close();
				} catch (IOException e) {
					flag = false;
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 读取文件内容
	 * 
	 * @param fileName
	 *            文件名
	 * @param charsetName
	 *            TODO
	 */
	public static String readFileByLines(String fileName, String charsetName) {
		String content = "";
		if (fileName != null && !"".equals(fileName)) {
			File file = new File(fileName);
			InputStreamReader inputread = null;
			BufferedReader reader = null;
			try {
				if (charsetName != null && !"".equals(charsetName)) {
					inputread = new InputStreamReader(new FileInputStream(file), charsetName);
				} else {
					inputread = new InputStreamReader(new FileInputStream(file));
				}
				reader = new BufferedReader(inputread);
				String tempString = null;
				/* 一次读入一行，直到读入null为文件结束 */
				while ((tempString = reader.readLine()) != null) {
					content += tempString;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return content;
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 */
	public static String readFileByChars(String fileName) {
		String content = "";
		Reader reader = null;
		try {
			char[] tempchars = new char[1024 * 1024];// 读取的字节数
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(fileName));
			while ((charread = reader.read(tempchars)) != -1) {// 读入多个字符到字符数组中,charread为一次读取字符数
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
				if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != '\r')) {
					content += tempchars;
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == '\r') {
							continue;
						} else {
							content += tempchars[i];
						}
					}
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return content;
	}

	/**
	 * Description : 读取文件最后一行内容 Create Date: 2010-12-29下午03:06:47 by yangx
	 * Modified Date: 2010-12-29下午03:06:47 by yangx
	 * 
	 * @param fileName
	 *            文件路径名+文件名
	 * @return String
	 * @Exception
	 */
	public static String getfinalLineData(String pathName) {
		RandomAccessFile raf = null;
		String lastLine = "";
		try {
			raf = new RandomAccessFile(pathName, "r");
			long len = raf.length();
			if (len != 0L) {
				long pos = len - 1;
				while (pos > 0) {
					pos--;
					raf.seek(pos);
					if (raf.readByte() == '\n') {
						lastLine = raf.readLine();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e1) {
					e1.getStackTrace();
				}
			}
		}
		return lastLine;
	}

	/**
	 * Description: 将流中某块写入文件
	 * 
	 * @param inputStream
	 *            输入流
	 * @param startPosition
	 *            起始点
	 * @param endPosition
	 *            结束点
	 * @param fileAbsolutePath
	 *            文件存放路径
	 * @return 写入是否成功
	 * @throws Exception
	 *             Create Date: 2011-3-23 14:23 by zhouzh Modifier zhouzhanghe
	 *             Modified Date:2011-7-6 19:27
	 */
	public static boolean randomWriteInputStreamToFile(InputStream inputStream, int saveDataPosition,
			String fileAbsolutePath) throws Exception {
		RandomAccessFile writeRaf = null;
		try {
			// 建立写流
			File uploadFile = new File(fileAbsolutePath);
			writeRaf = new RandomAccessFile(uploadFile, "rw");
			writeRaf.seek(saveDataPosition);
			// int total = startPosition;
			int len = 0;// 存放每次读取的字节数
			// 读取并写入指定范围的数据
			byte[] buffer = new byte[4096];// 建立缓冲区
			BufferedInputStream bi = new BufferedInputStream(inputStream);
			bi.mark(4096);
			// 写入
			while (true) {
				len = bi.read(buffer);
				if (len < 0)
					break;
				writeRaf.write(buffer, 0, len);// 写入上传的文件的某片断
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (writeRaf != null) {
				writeRaf.close();
			}
		}
		return true;
	}

	/**
	 * Description: 将流中某块写入文件，同时获得这个块的MD5值
	 * 
	 * @param inputStream
	 *            输入流
	 * @param startPosition
	 *            起始点
	 * @param endPosition
	 *            结束点
	 * @param fileAbsolutePath
	 *            文件存放路径
	 * @return 写入是否成功
	 * @throws Exception
	 *             Create Date: 2011-3-23 14:23 by zhouzh Modifier zhouzhanghe
	 *             Modified Date:2011-7-6 19:27
	 */
	public static String randomWriteInputStreamToFileAndMd5(InputStream inputStream, int saveDataPosition,
			String fileAbsolutePath) throws Exception {
		MessageDigest messageDigest = MD5Util.getMessagedigest();
		RandomAccessFile writeRaf = null;
		try {
			// 建立写流
			File uploadFile = new File(fileAbsolutePath);
			writeRaf = new RandomAccessFile(uploadFile, "rw");
			writeRaf.seek(saveDataPosition);
			// int total = startPosition;
			int len = 0;// 存放每次读取的字节数
			// 读取并写入指定范围的数据
			byte[] buffer = new byte[4096];// 建立缓冲区
			BufferedInputStream bi = new BufferedInputStream(inputStream);
			bi.mark(4096);
			// 写入
			while (true) {
				len = bi.read(buffer);
				if (len < 0)
					break;
				writeRaf.write(buffer, 0, len);// 写入上传的文件的某片断
				messageDigest.update(buffer, 0, len);
			}
			return MD5Util.bufferToHex(messageDigest.digest());
		}finally {
			if (writeRaf != null) {
				writeRaf.close();
			}
		}		
	}

	/**
	 * Description: 从流中读取部分数据并且写入文件
	 * 
	 * @param inputStream
	 *            输入流
	 * @param startPosition
	 *            读取输入流的起始点
	 * @param endPosition
	 *            读取输入流的结束点
	 * @param saveDataPosition
	 *            保存数据文件起始位置
	 * @param saveDataFileAbsolutePath
	 *            保存读取数据文件的存放路径
	 * @return 写入是否成功
	 * @throws Exception
	 *             Create Date: 2011-7-5 17:59 14:23 by zhouzhanghe
	 */
	public static boolean readPartStreamWriteFile(InputStream inputStream, int startPosition, int endPosition,
			int saveDataPosition, String saveDataFileAbsolutePath) throws Exception {
		// 建立写流
		File uploadFile = new File(saveDataFileAbsolutePath);
		RandomAccessFile writeRaf = new RandomAccessFile(uploadFile, "rw");
		writeRaf.seek(saveDataPosition);// 定位写入位置

		byte[] buffer = new byte[4096];
		int len = 0;// 存放每次读取的字节数
		int readDataCount = endPosition - startPosition;// 读取的总大小
		int cycleCount = readDataCount / buffer.length;// 循环次数

		inputStream.skip(startPosition);
		/*
		 * 按缓冲区大小读取数据流后，再单独读取不足缓冲区大小的剩余部分。
		 * 如:inputStream流中有6个字节，本次读取众0-5个字节，且缓冲区buffer大小为2。
		 * 首先for循环先读取0-2,2-4字节，之后由于剩余1字节小于缓冲区大小
		 * ，则以剩余字节大小创建一个新的缓冲区，最后以新的缓冲区大小读取数据。 这样可以避免读取多余的数据
		 */
		for (int i = 0; i < cycleCount; i++) {
			len = inputStream.read(buffer);
			writeRaf.write(buffer, 0, len);// 写入上传的文件的某片断
		}
		if (readDataCount % buffer.length != 0) {
			buffer = new byte[readDataCount % buffer.length];
			len = inputStream.read(buffer);
			writeRaf.write(buffer, 0, len);// 写入上传的文件的某片断
		}
		writeRaf.close();
		return true;
	}

	/**
	 * 读取文件某块到字节数据
	 * 
	 * @param startPosition
	 *            起始点
	 * @param endPosition
	 *            结束点
	 * @param fileAbsolutePath
	 *            文件存放路径
	 * @return 读取结果
	 * @throws Exception
	 *             Create Date: 2011-3-23 16:35 by zhouzh
	 */
	public static byte[] randomReadFile(int startPosition, int endPosition, String fileAbsolutePath) throws Exception {
		if (endPosition < startPosition) {
			throw new Exception("起始地址不能大于结束位置.");
		}
		RandomAccessFile readRaf = new RandomAccessFile(fileAbsolutePath, "r");

		byte[] result = new byte[endPosition - startPosition];
		readRaf.seek(startPosition);
		readRaf.read(result);

		readRaf.close();
		return result;
	}

	/**
	 * FunName: inputStreamToString Description : 转换流为字符串
	 * 
	 * @param is
	 *            数据流
	 * @param enCoding
	 *            文件编码
	 * @return String：转换后的字符串
	 * @Author: zhouzhanghe
	 * @Create Date: 2011-07-04
	 */
	public static String inputStreamToString(InputStream is, String enCoding) {
		try {
			if (is == null)
				return null;

			byte[] b = new byte[1024];
			StringBuffer sb = new StringBuffer();
			int byteRead = 0;
			while ((byteRead = is.read(b)) != -1) {
				sb.append(new String(b, 0, byteRead, enCoding));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/** 
	 * FuncName : deleteAllFile
	 * Description :  删除路径下所有
	 * Create Date: 2011-9-21下午06:19:18 by yangx  Modified Date: 2011-9-21下午06:19:18 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static void deleteAllFile(String path,String fileName){
		 File file = new File(path);
		 if(file.canRead() && file.isDirectory()){
			 String[] list = file.list();
			 for (int i=0;i<list.length;i++) {
				 String nameHead = null;
				 if (list[i].lastIndexOf(".")!=-1) {
					 nameHead = list[i].substring(0,list[i].lastIndexOf("."));
				 }else{
					 nameHead = list[i];
				 }
				 if (fileName!=null&&fileName.equals(nameHead)) {
					 delete(path + File.separator + list[i]);
				 }
			 }
		 }
	} 
	
	 
	/** 
	 * FuncName : delete
	 * Description :  删除文件
	 * Create Date: 2011-9-21下午09:06:06 by yangx  Modified Date: 2011-9-21下午09:06:06 by yangx
	 * @param   path：文件路径
	 * @return  boolean
	 * @Exception   
	 */
	public static final boolean delete(String path){
        try{
            File file = new File(path);
            file.delete();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
	}
	
	/**
	 * 获取文件扩展名
	 * @param file
	 * @return
	 * @author zhouzhanghe
	 * @created at 2011-10-31 13:43
	 */
	public static final String getFileExtentionName(String fileName){
		if(fileName == null)
			throw new EsException("fileName is null");
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}
	/**
	 * 获取文件名，该文件名不包括扩展名
	 * @param file 文件名
	 * @return
	 * @author zhouzhanghe
	 * @created at 2011-10-31 13:43
	 */
	public static final String getFileNameExclExtnName(String fileName){
		if(fileName == null)
			throw new EsException("fileName is null");
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	 
	public static void main(String args[]) throws Exception {
//		File sourceFile = new File("c:\\1.txt");
//		FileInputStream fileInputStream = new FileInputStream(sourceFile);
		FileUtils f = new FileUtils();
		f.writerFile("\\\\192.168.7.75\\share\\454545", "56456565656");
		
	}
}
