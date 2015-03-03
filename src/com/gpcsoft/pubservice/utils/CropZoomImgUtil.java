package com.gpcsoft.pubservice.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片裁剪工具类
 */
public class CropZoomImgUtil {

	private CropZoomImgUtil(){}
	
	private BufferedImage image = null;
	
	private void load(File imageFile) throws IOException {
		image = ImageIO.read(imageFile);
	}
	
	/** 
	 * Description :  加载图片
	 * Create Date: 2011-9-22下午07:45:26 by likg  Modified Date: 2011-9-22下午07:45:26 by likg
	 * @param   filePath:图片路径
	 * @return  
	 * @Exception   
	 */
	public static CropZoomImgUtil loadImg(String filePath) throws IOException {
		File file = new File(filePath);
		CropZoomImgUtil utils = new CropZoomImgUtil();
		utils.load(file);
		return utils;
	}

	/** 
	 * Description :  裁剪图片
	 * Create Date: 2011-9-25下午08:45:36 by likg  Modified Date: 2011-9-25下午08:45:36 by likg
	 * @param   sourceImgRec:源图片的坐标	cropImgRec:选择框的坐标
	 * @return  
	 * @Exception   
	 */
	public void cutImg(Rectangle sourceImgRec, Rectangle cropImgRec) throws FileNotFoundException {
		//源图片中被选中的区域
		Rectangle selectedImgRec = new Rectangle();
		selectedImgRec.x = cropImgRec.x;
		selectedImgRec.y = cropImgRec.y;
		selectedImgRec.width = cropImgRec.width;
		selectedImgRec.height = cropImgRec.height;
		
		//标记是否有交叉区域
		boolean hasCrossArea = true;
		
		//裁剪区域与源图没有交叉区域
		if((cropImgRec.x+cropImgRec.width<sourceImgRec.x) || (sourceImgRec.x+sourceImgRec.width<cropImgRec.x) || (sourceImgRec.y+sourceImgRec.height<cropImgRec.y) || (cropImgRec.y+cropImgRec.height<sourceImgRec.y)) {
			hasCrossArea = false;
		}
		//裁剪区域与源图有交叉区域
		else {
			//左面、右面有空白
			if((cropImgRec.x<sourceImgRec.x) && (cropImgRec.x+cropImgRec.width>sourceImgRec.x+sourceImgRec.width)) {
				selectedImgRec.x = sourceImgRec.x;
				selectedImgRec.width = sourceImgRec.width;
			}
			//左面有空白
			else if(cropImgRec.x<sourceImgRec.x) {
				selectedImgRec.x = sourceImgRec.x;
				selectedImgRec.width = cropImgRec.x + cropImgRec.width - sourceImgRec.x;
			}
			//右面有空白
			else if(cropImgRec.x+cropImgRec.width>sourceImgRec.x+sourceImgRec.width) {
				selectedImgRec.x = cropImgRec.x;
				selectedImgRec.width = sourceImgRec.x + sourceImgRec.width - cropImgRec.x;
			}
			
			//上面、下面有空白
			if((cropImgRec.y<sourceImgRec.y) && (cropImgRec.y+cropImgRec.height>sourceImgRec.y+sourceImgRec.height)) {
				selectedImgRec.y = sourceImgRec.y;
				selectedImgRec.height = sourceImgRec.height;
			}
			//上面有空白
			else if(cropImgRec.y<sourceImgRec.y) {
				selectedImgRec.y = sourceImgRec.y;
				selectedImgRec.height = cropImgRec.y + cropImgRec.height - sourceImgRec.y;
			}
			//下面有空白
			else if(cropImgRec.y+cropImgRec.height>sourceImgRec.y+sourceImgRec.height) {
				selectedImgRec.y = cropImgRec.y;
				selectedImgRec.height = sourceImgRec.y + sourceImgRec.height - cropImgRec.y;
			}
		}
		
		//画图
		BufferedImage cropImg = new BufferedImage(cropImgRec.width, cropImgRec.height, 1);
		Graphics g = cropImg.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, cropImgRec.width, cropImgRec.height);
		
		//有交叉区域
		if(hasCrossArea) {
			BufferedImage selectedImg = new BufferedImage(selectedImgRec.width, selectedImgRec.height, 1);
			selectedImg = image.getSubimage(selectedImgRec.x-sourceImgRec.x, selectedImgRec.y-sourceImgRec.y, selectedImgRec.width, selectedImgRec.height);
			g.drawImage(selectedImg, selectedImgRec.x-cropImgRec.x, selectedImgRec.y-cropImgRec.y, null);
		}
		
		g.dispose();
		this.image = cropImg;
	}

	/** 
	 * Description :  图片缩放，不生成新的图片
	 * Create Date: 2011-9-22下午07:55:40 by likg  Modified Date: 2011-9-22下午07:55:40 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void zoomImg(int tarWidth, int tarHeight) {
		//缩放图像
		BufferedImage tagImage = new BufferedImage(tarWidth, tarHeight, BufferedImage.TYPE_INT_RGB);
		Image image = this.image.getScaledInstance(tarWidth, tarHeight, Image.SCALE_SMOOTH);
		
		//绘制目标图
		Graphics g = tagImage.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		this.image = tagImage;
	}

	/** 
	 * Description :  保存裁剪后的图片
	 * Create Date: 2011-9-22下午07:59:51 by likg  Modified Date: 2011-9-22下午07:59:51 by likg
	 * @param   pathAndFileName:路径和文件名	suffixName:图片后缀名
	 * @return  
	 * @Exception   
	 */
	public void saveCropImg(String pathAndFileName, String suffixName) throws IOException {
		FileOutputStream out = null;
		try {
			//写文件
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(this.image, suffixName, bos);
			out = new FileOutputStream(pathAndFileName);
			out.write(bos.toByteArray());
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if(out != null) out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}