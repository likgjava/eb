package com.gpcsoft.epp.webService.webService.ue.http;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.common.utils.MD5Util;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

public class DownloadFileHttpServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4669610840024302866L;

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private AttachmentService attachmentService;
	
	private FrameMessageResource  messageSource;
	
	private FrameMessageResource getMessageSource() {
		if(null == this.messageSource){
			this.messageSource = (FrameMessageResource)FrameBeanFactory.getBean("frameMessageResource");
		}
		return this.messageSource;
	}

	private AttachmentService getAttachmentService(){
		if(null == this.attachmentService){
			this.attachmentService = (AttachmentService)FrameBeanFactory.getBean("attachmentServiceImpl");
		}
		return this.attachmentService;
	}
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String attachmentObjId = request.getParameter("attachmentObjId");
		String fileType = request.getParameter("fileType");
		String filePathR = null;
		if(request.getParameter("filePath")!=null){
			filePathR = new String(request.getParameter("filePath").getBytes("ISO-8859-1"), "UTF-8");//文件相对路径
		}
		String rangePath = "";
		String md5 ="";
		Attachment attachment = null;
		if(attachmentObjId == null && filePathR == null){//如果没有附件ID（表示在数据库里有相对路径）或文件相对路径，则返回
			return;
		}else if(attachmentObjId != null){//如果有附件ID，则去附件库里找相关的附件信息
			List<Attachment> list = this.getAttachmentService().getAttachmentsByRealID(attachmentObjId);
			if(list == null || list.isEmpty()){
				return ;
			}else{
				attachment = (Attachment)list.get(0);
			}
			if(attachment == null){
				return;
			}
		}
		//获取文件大小及md5值
		byte[] readResult;
		String newPath = getMessageSource().getMessage("defaultUrl");
		if(CommonEnum.FILE_TYPE_BID.equals(fileType)){//
			String bidFilePath = this.getMessageSource().getMessage("epp.projectAttaPath");
			newPath = bidFilePath+File.separator;
		}else if(CommonEnum.FILE_TYPE_PURCHASE.equals(fileType)){//
			String epp_projectAttaPath = this.getMessageSource().getMessage("epp.projectAttaPath");
			newPath = epp_projectAttaPath +File.separator;
		}else if (CommonEnum.FILE_TYPE_RECEIPT.equals(fileType)){
			String receiptPath = this.getMessageSource().getMessage("epp.projectAttaPath");
			newPath = receiptPath + File.separator;
		}
		String filePath = "";
		if(filePathR != null && !"".equals(filePathR)){//如果有文件相对路径，则拼装路径;否则从附件对象里获取
			filePath = filePathR;
		}else if(attachment != null){
			filePath = newPath+((attachment.getPath()==null)?"":attachment.getPath())+File.separator+attachment.getFileName();
		}else{
			return;
		}
		
		//验证下载范围
		String range = request.getHeader("Range");//当次下载范围
		if(range==null){
			rangePath = "0-0";
		}else if(range.indexOf("=")!=-1){  //截取bytes=字符串
			rangePath = range.substring(range.indexOf("=")+1);
		}
		String[] parts = rangePath.split("-");
		int startPosition = Integer.parseInt(parts[0].trim());//读取文件块的起始位置
		int endPosition = Integer.parseInt(parts[1].trim());//读取文件块的结束位置
		if(startPosition < 0 || endPosition < 0 || startPosition > endPosition){
			logger.error("upload range is error," + range);
			return;
		}
		
		
		File file = new File(filePath);
		try {
			if("0-0".equals(rangePath)){
				md5 = MD5Util.getFileMD5String(file);//读取整个文件MD5值
				readResult = FileUtils.randomReadFile(startPosition, endPosition, filePath);
				//将读取的内容放入输出流
				response.setContentType("application/octet-stream");
				//response.setHeader("Content-Disposition", "attachment;filename="+ new String(attachment.getFileName().getBytes(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") >0?"gbk":"utf-8"), "ISO8859-1" ));  //火狐和Ie的下载文件名乱码问题  
				String length = String.valueOf(file.length());
				response.addIntHeader("Content-Length",Integer.parseInt(length));    //增加这一行
				response.addHeader("MD5", md5);
			}else{
				if(startPosition < 0 || endPosition < 0 || startPosition >= endPosition){
					logger.error("upload range is error," + range);
					return;
				}
				 md5 = MD5Util.getFileMD5String(file, startPosition, endPosition);//转换文件某块为MD5值
				readResult = FileUtils.randomReadFile(startPosition, endPosition, filePath);
				//将读取的内容放入输出流
				response.setContentType("application/octet-stream");
				//response.setHeader("Content-Disposition", "attachment;filename="+ new String(attachment.getFileName().getBytes(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") >0?"gbk":"utf-8"), "ISO8859-1" ));  //火狐和Ie的下载文件名乱码问题  
				response.addHeader("MD5", md5);
				response.addIntHeader("Content-Length",readResult.length);    //增加这一行
			}
			response.getOutputStream().write(readResult);
			response.getOutputStream().flush();
		} catch (Exception e) {
			response.addHeader("operTag", "N");  //返回文件未找到标示
			response.addHeader("operDesc", EsExceptionEnum.ES_SYSTEM_ERROR); //文件未找到错误
			e.printStackTrace();
		}finally{
			response.getOutputStream().close();	
		}

	
	}

}
