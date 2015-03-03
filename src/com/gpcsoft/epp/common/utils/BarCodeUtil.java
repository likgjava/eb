/**
 * 
 */
package com.gpcsoft.epp.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import com.barcodelib.barcode.Linear;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

/**
 * 二维码的帮助类
 * @author dell
 *
 */
public class BarCodeUtil {
	
	/**
	 * FuncName: generateBarCode
	 * Description:用于生成二维码的方法
	 * @param barStr:用于生成二维码的文字
	 * @param filePath:生成二维码图片的绝对路径  示例："c://codabar.gif"
	 * @author: shenjz
	 * @throws Exception 
	 * @Create Date:2011-12-9  上午09:24:59
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-9  上午09:24:59
	 */
	public static void generateBarCode(String barStr,String filePath) throws Exception{
		Linear barcode = new Linear();
        barcode.setType(Linear.CODE11);//设置生成二维码的类型 
        barcode.setData(barStr);//用于生成二维码的文字
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
        barcode.setTextFont(new Font("Arial", 0, 12));//文字格式
        barcode.setRotate(Linear.ANGLE_0);//设置变换比例
        barcode.renderBarcode(filePath);//指定路径生成二维码图片
	}
	
	/**
	 * FuncName: generateBarCode
	 * Description:用于生成二维码的方法
	 * @param barStr:用于生成二维码的文字
	 * @param filePath:生成二维码图片的绝对路径  示例："c://codabar.gif"
	 * @author: shenjz
	 * @throws Exception 
	 * @Create Date:2011-12-9  上午09:24:59
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-9  上午09:24:59
	 */
	public static void generateBarCodeByBar(Linear barcode,String filePath) throws Exception{
        barcode.renderBarcode(filePath);//指定路径生成二维码图片
	}
	
	/**
	 * FuncName: wiriteBarCodeToDocument
	 * Description : 用于将二维码图片写入pdf文档末尾右下方的方法
	 * @param document:文档, filePath:二维码图片的绝对路径 示例："c://codabar.gif"
	 * @author: shenjz
	 * @throws IOException 
	 * @throws DocumentException 
	 * @Create Date:2011-12-9  上午09:24:53
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-9  上午09:24:53
	 */
	public static void wiriteBarCodeToDocument(Document document,String filePath) throws DocumentException, IOException{
		//怕有些人看不懂,PS:一个两列的居右table,图片放入右边表格单.
		BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,  "UniGB-UCS2-H" ,  false );//处理中午
		com.lowagie.text.Font fontContent =  new  com.lowagie.text.Font(bfChinese  ,  12 , com.lowagie.text.Font.NORMAL, Color.BLACK);  //设置字体样式
		PdfPTable table1 = new PdfPTable(2); //表格两列
		table1.setHorizontalAlignment(Element.ALIGN_RIGHT); //表格居右
		table1.setWidthPercentage(100);//表格的宽度为100%
		float[] wid1 ={0.5f,0.3f}; //两列宽度的比例
		table1.setWidths(wid1);//设置table两列宽度 
		table1.getDefaultCell().setBorderWidth(0); //不显示边框
		Image image = Image.getInstance(filePath);//获取要写入的二维码图片
		PdfPCell cell11 = new PdfPCell(new Paragraph("",fontContent));//生成字体样式
		cell11.setBorderWidth(0);//不显示边框
		table1.addCell(cell11);//设置表格td 
		table1.addCell(image);//将图片放入表格td中
		document.add(table1);//将table放入文档
	}
	
	public static void main(String[] args) {
		try {
			Linear barcode = new Linear();
	        barcode.setType(Linear.CODE93);//设置生成二维码的类型 
	        barcode.setData("GK20111216001-201112020000A");//用于生成二维码的文字
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
	        barcode.setTextFont(new Font("Arial", 0, 12));//文字格式
	        barcode.setRotate(Linear.ANGLE_0);//设置变换比例
			BarCodeUtil.generateBarCodeByBar(barcode, "D://weew.gif");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
