package com.gpcsoft.epp.common.utils;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;

import com.barcodelib.barcode.Linear;
import com.gpcsoft.core.utils.RandomUtil;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

/**
 * HTML转PDF
 * 
 * @author kuangxw
 * @create 08-10-31
 */
public class Html2PDFConverter {

	/**
	 * html字符串转成PDF文件(只能在web容器启动时调用)
	 * @param htmlContent
	 * @return 文件名称
	 * @throws Exception
	 */
	public static String Html2PDF(String htmlContent) throws Exception {
		String fileName = "";
		try {
			CYaHPConverter converter = new CYaHPConverter();
			List headerFooterList = new ArrayList();
			Map properties = new HashMap();
			// 设置字体
			properties.put(IHtmlToPdfTransformer.HANDLE_CHAR_AS_GLYPH, "true");
			String fileSaveDir = "GPCSOFT";
			fileName = fileSaveDir + RandomUtil.randomString(12) + ".pdf";
			FileOutputStream out = new FileOutputStream(fileName);
			// html必须的内容
			htmlContent = htmlContent.replaceAll("<!--head-->", "<head>").replaceAll("<!--/head-->", "</head>").replaceAll("<!--body-->", "<body>").replaceAll("<!--/body-->","</body>");
			converter.convertToPdf(htmlContent, IHtmlToPdfTransformer.A4P,headerFooterList, "http://www.allcolor.org/xmlns/yahp",out, properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	/**
	 * html字符串转成PDF文件
	 * @param htmlContent
	 * @param fileSaveDir
	 * @return
	 * @throws Exception
	 */
	public static String Html2PDF(String htmlContent,String fileSaveDir,String filePathName) throws Exception {
		String fileName = filePathName;
		Document document = null;
		FileOutputStream out = null;
		try {
			document = new Document();   
			StyleSheet st = new StyleSheet();   
			st.loadTagStyle("body", "leading", "16,0");  
			out = new FileOutputStream(fileSaveDir+File.separator+fileName);
			PdfWriter.getInstance(document,out);   
			document.open();
			Paragraph pf=new Paragraph();
			BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,  "UniGB-UCS2-H" ,  false );//处理中午
			Font fontContent =  new  Font(bfChinese  ,  12 , Font.NORMAL, Color.BLACK);   
			pf.add(new Paragraph(htmlContent,fontContent));   
			document.add(pf);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			document.close(); 
			out.close();
			System.gc();
		}
		return fileName;

	}
	
	/**
	 * 匿名投标字符串转成PDF文件
	 * @param htmlContent
	 * @param fileSaveDir
	 * @return
	 * @throws Exception
	 */
	public static String Html2PDF(String htmlContent,String fileSaveDir,String filePathName,String bidNo) throws Exception {
		String fileName = filePathName;
		Document document = null;
		FileOutputStream out = null;
		String imagePath = fileSaveDir+File.separator+bidNo+".gif";
		try {
			document = new Document();   
			StyleSheet st = new StyleSheet();   
			st.loadTagStyle("body", "leading", "16,0");  
			out = new FileOutputStream(fileSaveDir+File.separator+fileName);
			PdfWriter.getInstance(document,out);   
			document.open();
			Paragraph pf=new Paragraph();
			BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,  "UniGB-UCS2-H" ,  false );//处理中午
			Font fontContent =  new  Font(bfChinese  ,  12 , Font.NORMAL, Color.BLACK);   
			pf.add(new Paragraph(htmlContent,fontContent));   
			document.add(pf);
			Linear barcode = new Linear();
	        barcode.setType(Linear.CODE93);//设置生成二维码的类型 
	        barcode.setData(bidNo);//用于生成二维码的文字
	        barcode.setN(3);//设置单品单位
	        barcode.setUOM(Linear.UOM_PIXEL);//设置单位尺寸
	        barcode.setX(1f);//设置图片宽度
	        barcode.setY(25f);//设置图片高度
	        barcode.setLeftMargin(0f);//左边空白
	        barcode.setRightMargin(0f);//右边空白
	        barcode.setTopMargin(0f);//上方空白
	        barcode.setBottomMargin(0f);//下方空白
	        barcode.setResolution(24);//设置分辨率
	        barcode.setShowText(true);//是否在二维码下方显示文字
	        barcode.setTextFont(new java.awt.Font("Arial", 0, 12));//文字格式
	        barcode.setRotate(Linear.ANGLE_0);//设置变换比例
	        BarCodeUtil.generateBarCodeByBar(barcode, imagePath);
			BarCodeUtil.wiriteBarCodeToDocument(document,imagePath);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			document.close(); 
			out.close();
			System.gc();
		}
		return fileName;

	}
	
	/**
	 * html字符串转成PDF文件
	 * @param htmlContent    文件内容
	 * @param fileSaveDir    文件保存路径
	 * @param fileName   文件名称
	 * @return
	 * @throws Exception
	 */
	public static String Html2PDFByDoc(String htmlContent,String fileSaveDir,String fileName) throws Exception {
		Document document = new Document();   
		StyleSheet st = new StyleSheet();   
		st.loadTagStyle("body", "leading", "16,0");   
		PdfWriter.getInstance(document, new FileOutputStream(fileSaveDir+File.separator+fileName+".pdf"));   
		document.open();
		Paragraph pf=new Paragraph();
		BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,  "UniGB-UCS2-H" ,  false );//处理中午
		Font fontContent =  new  Font(bfChinese  ,  12 , Font.NORMAL, Color.BLACK);   
		pf.add(new Paragraph(htmlContent,fontContent));   
		document.add(pf);
		document.close(); 
		return fileName;

	}
	
	public static String CreatePDF(String htmlContent,String fileSaveDir) throws Exception {
		String fileName = "";
		try {
			CYaHPConverter converter = new CYaHPConverter();
			List headerFooterList = new ArrayList();
			Map properties = new HashMap();
			// 设置字体
			properties.put(IHtmlToPdfTransformer.HANDLE_CHAR_AS_GLYPH, "true");
			fileName = fileSaveDir.substring(fileSaveDir.lastIndexOf("/")+1,fileSaveDir.length());
			FileOutputStream out = new FileOutputStream(fileSaveDir);
			// html必须的内容					
			System.out.println("+++生成pdf文件之前++htmlContent+++:"+htmlContent);

			htmlContent = htmlContent.replaceAll("<!--head-->", "<head>").replaceAll("<!--/head-->", "</head>").replaceAll("<!--body-->", "<body>").replaceAll("<!--/body-->","</body>");
			converter.convertToPdf(htmlContent, IHtmlToPdfTransformer.A4P,headerFooterList, "http://www.allcolor.org/xmlns/yahp",out, properties);
			System.out.println("+++生成pdf文件之后++htmlContent+++:"+htmlContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;

	}
	
	/** 删除文件
	 * @param fileContent String 文件路径及名称 如c:/a.txt
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			if (myDelFile.exists()) {
				myDelFile.delete();
			}
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();

		}
	}


	public static void main(String[] args) throws Exception {
		//String content = new String(Html2PDFConverter.readFile(""),"UTF-8");
		System.out.println(Html2PDFConverter.Html2PDF("投标回执测试","d:/","test111"));
		System.out.println("end");
	}


	/**
	 * 读取文件流内容
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFile(String filename) throws IOException {
		File file = new File(filename);
		if (filename == null || filename.equals("")) {
			throw new NullPointerException("无效的文件路径");
		}
		long len = file.length();
		byte[] bytes = new byte[(int) len];

		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(file));
		int r = bufferedInputStream.read(bytes);
		if (r != len)
			throw new IOException("读取文件不正确");
		bufferedInputStream.close();
		return bytes;
	}
}
