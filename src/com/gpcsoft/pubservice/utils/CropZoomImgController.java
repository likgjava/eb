package com.gpcsoft.pubservice.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.FileOperateUtil;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.ImageScale;

@Controller
@Scope("request")
@SessionAttributes(types={GpcBaseObject.class})
@RequestMapping("/CropZoomImgController.do")
public class CropZoomImgController extends AnnotationMultiController<GpcBaseObject> {
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	/** 
	 * Description :  上传源图片（未裁剪之前的图片）
	 * Create Date: 2011-9-22下午06:55:33 by likg  Modified Date: 2011-9-22下午06:55:33 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=uploadSourceImg")
	public ModelAndView uploadSourceImg(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		if(!(request instanceof MultipartHttpServletRequest)) {
			throw new IllegalArgumentException(((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_can_not_empty"));
		}
		
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("attachFile");
		if(file == null) {
    		throw new IllegalArgumentException(((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_can_not_empty"));
		}
		
		if(file.getSize() > 0) {
			try {
				//获取源图片的保存路径(相对路径和绝对路径)
				Properties properties = new Properties();
		        properties.load(CropZoomImgController.class.getClassLoader().getResourceAsStream("sys/attachment.properties"));
				String relativePath = properties.getProperty("cropZoomImgPath");
				String absolutePath = Constants.ROOTPATH + relativePath;
				
				//获取图片的后缀名
				String suffixName = "";
				int lastDot = file.getOriginalFilename().lastIndexOf(".");
				if(lastDot != -1){
					suffixName = file.getOriginalFilename().substring(lastDot);
				}
				
				//源图片保存的文件名
				String saveName = java.util.UUID.randomUUID().toString() + suffixName;
				
				//如果没有目录则先创建目录
				File distFile = new File((absolutePath+saveName).replace("/", File.separator));
				if(!distFile.getParentFile().exists()) {
					distFile.getParentFile().mkdirs(); 
				}
				
				//保存图片
				attachmentService.saveFileByInputStream(file.getInputStream(), absolutePath, saveName);
				
				//返回源图片的路径
				model.put("sourceImgPath", relativePath+saveName);
				
				//返回源图片的宽和高
				BufferedImage srcBufferImage = javax.imageio.ImageIO.read(distFile);
				model.put("sourceImgWidth", srcBufferImage.getWidth());
				model.put("sourceImgHeight", srcBufferImage.getHeight());
			} catch (IOException e) {
	    		throw new IllegalArgumentException(((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.upload_failer"));
			}
		} else {
    		throw new IllegalArgumentException(((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_can_not_empty"));
		}
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/** 
	 * Description :  上传裁剪后的图片
	 * Create Date: 2011-9-22下午07:36:09 by likg  Modified Date: 2011-9-22下午07:36:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=uploadCropImg")
	public ModelAndView uploadCropImg(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		String fileWebroot = Constants.ROOTPATH;
		String imageSource = request.getParameter("imageSource"); //图片源
		String imageW = request.getParameter("imageW"); //图片宽
		String imageH = request.getParameter("imageH"); //图片高
		String imageX = request.getParameter("imageX"); //图片X位置
		String imageY = request.getParameter("imageY"); //图片Y位置
		String selectorX =  request.getParameter("selectorX"); //选中区位置X
		String selectorY = request.getParameter("selectorY"); //选中区位置Y
		String selectorW = request.getParameter("selectorW"); //选中区位置宽
		String selectorH = request.getParameter("selectorH"); //选中区位置高
//		String imageRotate = request.getParameter("imageRotate"); //图片的翻转角度
//		String viewPortW = request.getParameter("viewPortW");
//		String viewPortH = request.getParameter("viewPortH");

		//获取图片后缀名
		String suffixName = imageSource.substring(imageSource.lastIndexOf(".")+1);
		String cropImgName = System.currentTimeMillis() + "." + suffixName;
		String relativePath = imageSource.substring(0, imageSource.lastIndexOf("."));

		//加载源图
		CropZoomImgUtil imgUtil = CropZoomImgUtil.loadImg(fileWebroot + imageSource);
		//缩放源图
		imgUtil.zoomImg((int)Double.parseDouble(imageW), (int)Double.parseDouble(imageH));
		//裁剪图片
		Rectangle sourceImgRec = new Rectangle((int)Double.parseDouble(imageX),(int)Double.parseDouble(imageY),(int)Double.parseDouble(imageW),(int)Double.parseDouble(imageH));
		Rectangle cropImgRec = new Rectangle((int)Double.parseDouble(selectorX),(int)Double.parseDouble(selectorY),(int)Double.parseDouble(selectorW),(int)Double.parseDouble(selectorH));
		imgUtil.cutImg(sourceImgRec, cropImgRec);
		
		//保存裁剪后的图片
		imgUtil.saveCropImg(fileWebroot + relativePath + cropImgName, suffixName);
		
		//返回裁剪后图片的路径
		File cutImgfile = new File(fileWebroot + relativePath + cropImgName);
		if(cutImgfile.exists()){
			model.put("cropImgPath", relativePath + cropImgName);
		}
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  上传正式图片(包括生成多种规格的图片)
	 * Create Date: 2011-9-22下午07:36:09 by likg  Modified Date: 2011-9-22下午07:36:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=uploadNormalImg")
	public ModelAndView uploadNormalImg(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取剪切后的图片文件
		String cropImgPath = request.getParameter("cropImgPath");
		File cropImgFile = new File((Constants.ROOTPATH+cropImgPath).replace("/", File.separator));
		
		String saveName=java.util.UUID.randomUUID().toString();
		String uploadPath = attachmentService.getAttachmentUrl("uploadUrl");
		File distFile = new File((uploadPath + saveName).replace("/", File.separator));
		//如果没有目录则先创建目录
		if(!distFile.getParentFile().exists()) {
			distFile.getParentFile().mkdirs();
		}
		
		//复制原图
		FileOperateUtil fu = new FileOperateUtil();
		fu.copyFile(Constants.ROOTPATH+cropImgPath, uploadPath + saveName);
		
		/****************************上传规格图片*****************************************/
		String pic_WH_rule_str = request.getParameter("pic_WH_rule_str");
		if(StringUtils.hasLength(pic_WH_rule_str)){
			String pic_WH_rule_str_enum = pic_WH_rule_str;						
										
			// 获得规则枚举数据
			EnumService enumService = (EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME);
			List<String> list = enumService.getEnum(pic_WH_rule_str_enum);
			for (String str : list) {
				String width_height = str.split(":")[1];//规则宽高
				String [] width_height_ValueArr = width_height.split("#");//获取宽和高的值		
				
				File diskFile = new File((uploadPath  +  saveName+"_"+width_height).replace("/", File.separator));
				if(!diskFile.getParentFile().exists()){
					diskFile.getParentFile().mkdirs();
				}
				
				try{
					//上传并压缩临时文件
					ImageScale.resizeFix(cropImgFile, diskFile, Integer.parseInt(width_height_ValueArr[0]), Integer.parseInt(width_height_ValueArr[1]));
				}catch(Exception ce) {
					throw ce ;
				}
			}
		}
		/****************************end 上传规格图片*****************************************/
		
		//保存一条Attachment对象的记录
		Attachment attachment = new Attachment();
		attachment.setCreator(AuthenticationHelper.getCurrentUser());
		attachment.setRelationId(java.util.UUID.randomUUID().toString().replace('-', '_'));
		attachment.setFileSize(Long.valueOf(cropImgFile.length()));
		//attachment.setFileType(cropImgFile.g);
		attachment.setViewName(System.currentTimeMillis() + cropImgPath.substring(cropImgPath.lastIndexOf(".")));
		attachment.setUploadTime(new Date());
		attachment.setFileName(saveName);
		attachmentService.saveAttachment(attachment);
		
		model.put("attachmentObjId", attachment.getObjId());
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  复制原来的图片到裁剪图片的文件夹中
	 * Create Date: 2011-9-24下午02:57:50 by likg  Modified Date: 2011-9-24下午02:57:50 by likg
	 * @param   objId:原来图片的Attachment的objId
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=copyOldImgToCropFolder")
	public ModelAndView copyOldImgToCropFolder(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		boolean isFindOldImg = true; //标记原图是否存在
		
		Attachment attachment = attachmentService.get(objId);
		if(attachment != null) {
			String filePath = messageSource.getMessage("uploadUrl");
			String attchPath = (attachment.getPath()==null ? "" : attachment.getPath());
			String suffixName = attachment.getViewName().substring(attachment.getViewName().lastIndexOf("."));
			File oldImgFile = new File((filePath+attchPath+attachment.getFileName()).replace("/", File.separator));
			
			//判断原图片是否存在
			if(oldImgFile.exists()){
				//把原图片复制到裁剪图片的文件夹中
				Properties properties = new Properties();
		        properties.load(CropZoomImgController.class.getClassLoader().getResourceAsStream("sys/attachment.properties"));
				String relativePath = properties.getProperty("cropZoomImgPath") + System.currentTimeMillis() + suffixName;
				String absolutePath = Constants.ROOTPATH + relativePath;
				
				//如果没有目录则先创建目录
				File distFile = new File((absolutePath).replace("/", File.separator));
				if(!distFile.getParentFile().exists()) {
					distFile.getParentFile().mkdirs();
				}
				
				//复制图片
				new FileOperateUtil().copyFile(oldImgFile.getAbsolutePath(), absolutePath);
				
				//返回源图片的路径、宽、高
				model.put("sourceImgPath", relativePath);
				BufferedImage srcBufferImage = javax.imageio.ImageIO.read(distFile);
				model.put("sourceImgWidth", srcBufferImage.getWidth());
				model.put("sourceImgHeight", srcBufferImage.getHeight());
			} else {
				isFindOldImg = false;
			}
		} else {
			isFindOldImg = false;
		}
		
		model.put("isFindOldImg", isFindOldImg);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
}
