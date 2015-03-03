package com.gpcsoft.pubservice.application.staticpage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

public class MakeStaticPageUtil {

	final static Object lock = new Object();
	private static AttachmentService attachmentService;
	private static FrameMessageResource messageSource;
	
	public static final String STATIC_INDEX = "index";
	public static final String STATIC_BIDDING_INDEX = "biddingIndex";
	public static final String STATIC_BARGAIN_INDEX = "bargainIndex";
	public static final String STATIC_GOODS_INDEX = "goodsIndex";
	public static final String STATIC_SUPPLIER_INDEX = "supplierIndex";
	public static final String STATIC_ALL = "all";
	
	static {
		messageSource =  (FrameMessageResource) FrameBeanFactory.getBean("frameMessageResource");
		attachmentService =(AttachmentService) FrameBeanFactory.getBean("attachmentServiceImpl");
	}
	
	/** 
	 * Description :  根据页面类型，生成指定URL的静态页面
	 * Create Date: 2011-10-28下午05:17:03 by likg  Modified Date: 2011-10-28下午05:17:03 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static void makeStaticPage(String pageType) {
		if(MakeStaticPageUtil.STATIC_ALL.equalsIgnoreCase(pageType)) {
			MakeStaticPageUtil.makeHtmlFromUrl(messageSource.getMessage("static.index"), messageSource.getMessage("static.sourceUrl.index"), "UTF-8");
			MakeStaticPageUtil.makeHtmlFromUrl(messageSource.getMessage("static.biddingIndex"), messageSource.getMessage("static.sourceUrl.biddingIndex"), "UTF-8");
			MakeStaticPageUtil.makeHtmlFromUrl(messageSource.getMessage("static.bargainIndex"), messageSource.getMessage("static.sourceUrl.bargainIndex"), "UTF-8");
			MakeStaticPageUtil.makeHtmlFromUrl(messageSource.getMessage("static.goodsIndex"), messageSource.getMessage("static.sourceUrl.goodsIndex"), "UTF-8");
			MakeStaticPageUtil.makeHtmlFromUrl(messageSource.getMessage("static.supplierIndex"), messageSource.getMessage("static.sourceUrl.supplierIndex"), "UTF-8");
		}else if(MakeStaticPageUtil.STATIC_INDEX.equalsIgnoreCase(pageType)) {
			MakeStaticPageUtil.makeHtmlFromUrl(messageSource.getMessage("static.index"), messageSource.getMessage("static.sourceUrl.index"), "UTF-8");
		}else if(MakeStaticPageUtil.STATIC_BIDDING_INDEX.equalsIgnoreCase(pageType)) {
			MakeStaticPageUtil.makeHtmlFromUrl(messageSource.getMessage("static.biddingIndex"), messageSource.getMessage("static.sourceUrl.biddingIndex"), "UTF-8");
		}else if(MakeStaticPageUtil.STATIC_BARGAIN_INDEX.equalsIgnoreCase(pageType)) {
			MakeStaticPageUtil.makeHtmlFromUrl(messageSource.getMessage("static.bargainIndex"), messageSource.getMessage("static.sourceUrl.bargainIndex"), "UTF-8");
		}else if(MakeStaticPageUtil.STATIC_GOODS_INDEX.equalsIgnoreCase(pageType)) {
			MakeStaticPageUtil.makeHtmlFromUrl(messageSource.getMessage("static.goodsIndex"), messageSource.getMessage("static.sourceUrl.goodsIndex"), "UTF-8");
		}else if(MakeStaticPageUtil.STATIC_SUPPLIER_INDEX.equalsIgnoreCase(pageType)) {
			MakeStaticPageUtil.makeHtmlFromUrl(messageSource.getMessage("static.supplierIndex"), messageSource.getMessage("static.sourceUrl.supplierIndex"), "UTF-8");
		}
	}

	/** 
	 * Description :  生成指定URL网页的内容
	 * Create Date: 2011-10-28上午09:40:06 by likg  Modified Date: 2011-10-28上午09:40:06 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private static void makeHtmlFromUrl(String filePath, String url, String chartset) {
		synchronized (lock) {
			HttpURLConnection huc = null;
			BufferedReader br = null;
			BufferedWriter bw = null;
			try {
				//获取网页资源
				huc = (HttpURLConnection) new URL(url).openConnection();
				System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
				System.setProperty("sun.net.client.defaultReadTimeout", "30000");
				huc.connect();
				InputStream stream = huc.getInputStream();
				
				//保存网页内容
				br = new BufferedReader(new InputStreamReader(stream, chartset));
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constants.ROOTPATH+filePath), chartset));
				String line;
				//bw.write("<%@ page pageEncoding=\"UTF-8\"%>");
				while((line=br.readLine()) != null) {
					if(line.trim().length() > 0) {
						//如果包含图片标签，则替换图片路径
						if(line.indexOf("AttachmentController.do") != -1) {
							line = MakeStaticPageUtil.replaceImgSrc(line);
						}
						
						bw.write(line);
						bw.newLine();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bw.close();
					huc.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/** 
	 * Description :  替换图片的src路径
	 * Create Date: 2011-10-28上午09:42:42 by likg  Modified Date: 2011-10-28上午09:42:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private static String replaceImgSrc(String htmlLine) {
		String replacedLine = htmlLine; //替换后的一行html内容
		String[] htmls = htmlLine.split("\"");
		for(int i=0; i<htmls.length; i++) {
			String html = htmls[i];
			//包含图片标签
			if(html.indexOf("AttachmentController.do") != -1) {
				String[] imgAtts = html.split("&");
				String objId = null;
				String fileNameSuffix = null;
				for(String imgAtt : imgAtts) {
					if(imgAtt.indexOf("objId=") != -1) {
						objId = imgAtt.substring(imgAtt.indexOf("objId=")+6);
					} else if(imgAtt.indexOf("fileNameSuffix=") != -1) {
						fileNameSuffix = imgAtt.substring(imgAtt.indexOf("fileNameSuffix=")+15);
					}
				}
				
				//替换img标签的src内容
				int index1 = replacedLine.indexOf(html);
				replacedLine = replacedLine.substring(0, index1) + MakeStaticPageUtil.getImgPathByAttachmentId(objId, fileNameSuffix) + replacedLine.substring(index1+html.length());
			}
		}
		
		return replacedLine;
	}
	
	/** 
	 * Description :  根据AttachmentId和图片规则，获取图片路径
	 * Create Date: 2011-10-28下午03:05:28 by likg  Modified Date: 2011-10-28下午03:05:28 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private static String getImgPathByAttachmentId(String objId, String fileNameSuffix) {
		//如图片不存在，则返回404.gif
		String imgPath = Constants.ROOTPATH + "view/resource/images/404.gif";
		
		//获取图片信息
		Attachment attachment = attachmentService.get(objId);
		if(attachment != null) {
			File file = null;
			String filePath = messageSource.getMessage("uploadUrl");
			String attchPath = attachment.getPath()==null ? "" : attachment.getPath();
			
			//获取规则图片
			if(StringUtils.hasLength(fileNameSuffix)) {
				fileNameSuffix = fileNameSuffix.replace("*", "#");
				file = new File((filePath+attchPath+attachment.getFileName()+fileNameSuffix).replace("/", File.separator));
			}
			//若所需规则图片不存在则读取原图片
			if(!StringUtils.hasLength(fileNameSuffix) || !file.exists()) {
				file = new File((filePath+attchPath+attachment.getFileName()).replace("/", File.separator));
			}
			//如果存在图片，则返回图片服务器的图片路径
			if(file.exists()) {
				imgPath = messageSource.getMessage("imgServiceName") + filePath.substring(filePath.indexOf("/gpcsoftfile")+13) + attchPath+attachment.getFileName();
			}
		}
		
		return imgPath;
	}

}
