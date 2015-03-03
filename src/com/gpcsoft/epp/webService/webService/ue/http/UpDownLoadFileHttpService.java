package com.gpcsoft.epp.webService.webService.ue.http;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.common.utils.MD5Util;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;


@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/UpDownLoadFileHttpService.do")//页面请求路径,可修改
public class UpDownLoadFileHttpService extends AnnotationMultiController {

	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	

 
	@RequestMapping(params="method=securityUploadFileBlock")
	public void securityUploadFileBlock(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("gb2312");//设置输出流编码
		//验证用户名密码
		/*String Account = request.getParameter("Account");
		String Password = request.getParameter("Password");
		if(! userApiService.findValidateUserByPssw(Account, Password)){
			write("{'success':false,'msg':'用户名或密码不正确'}", response);
			return;
		}*/
		//验证上传范围
		String fileSize = request.getHeader("FileSize");//文件大小
		String signature = request.getHeader("Signature");//接入系统标识
		String fileType = request.getParameter("FileType");//文件类型
		String fileName = request.getParameter("FileName");//文件名称
		String range = request.getHeader("Range");//当次上传范围
		if(range == null || range.split("-").length != 2){
			logger.error("无效的上传范围," + range);
			return;
		}
		//验证输入参数
		if(signature == null || fileType == null || fileName == null){
			logger.error("Signature、fileType、fileName不能为空,signature=" + signature + ",fileType=" + fileType + ",fileName=" + fileName);
			return;
		}
		//创建文件上传路径
		String uploadUrl = messageSource.getMessage("uploadUrl");//获取文件默认上传路径
		String subDirectory = (uploadUrl.endsWith("/") ? "" : "/") + signature + "/" + fileType + "/" + String.valueOf(DateUtil.getYear()) + "/" + String.valueOf(DateUtil.getMonth()) + "/" + String.valueOf(DateUtil.getDate()) + "/";//子目录
		File abstractPathFile = new File(uploadUrl + subDirectory,fileName);
		// 如果目录不存在，则创建目录
		if (!abstractPathFile.getParentFile().exists()) {
			abstractPathFile.getParentFile().mkdirs(); 
		}
		String md5 = "";//存放上传文件的MD5值
		//处理结束标志
		if("0-0".equals(range)){
			md5 = MD5Util.getFileMD5String(abstractPathFile);//读取整个文件MD5值
			//保存上传记录到数据库
			Attachment attachment = new Attachment();
			attachment.setFileSize((Long.parseLong(fileSize == null ? "0" : fileSize)));
			attachment.setFileName(fileName);
			attachment.setPath(subDirectory);
			attachment = (Attachment) attachmentService.save(attachment, Attachment.class);
			response.setHeader("FileId", attachment.getObjId());
		}//处理非结束标志
		else{
			String[] parts = range.split("-");
			int startPosition = Integer.parseInt(parts[0].trim());
			int endPosition = Integer.parseInt(parts[1].trim());
			if(startPosition < 0 || endPosition < 0 || startPosition >= endPosition){
				logger.error("upload range is error," + range);
				return;
			}
			//上传文件
			//FileUtils.randomWriteFile(request.getInputStream(), startPosition, endPosition, uploadUrl + subDirectory + fileName);
			md5 = MD5Util.getFileMD5String(abstractPathFile, startPosition, endPosition);//转换文件某块为MD5值
		}
		response.setHeader("MD5", md5);
	}
	
	/**
	 * 下载文件块
	 * @param request 
	 * @param response
	 * @throws Exception
	 * @author zhouzh
	 * @since 2011.3.23
	 */
	@RequestMapping(params = "method=securityDownloadFileBlock")
	public void securityDownloadFileBlock(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String attachmentObjId = request.getParameter("attachmentObjId");
		if(attachmentObjId == null){
			return;
		}
		Attachment attachment = attachmentService.get(attachmentObjId);
		if(attachment == null){
			return;
		}
		//验证下载范围
		String range = request.getHeader("Range");//当次下载范围
		if(range == null || range.split("-").length != 2){
			logger.error("无效的下载范围," + range);
			return;
		}
		String[] parts = range.split("-");
		int startPosition = Integer.parseInt(parts[0].trim());//读取文件块的起始位置
		int endPosition = Integer.parseInt(parts[1].trim());//读取文件块的结束位置
    	
		if(startPosition < 0 || endPosition < 0 || startPosition >= endPosition){
			logger.error("upload range is error," + range);
			return;
		}
		String uploadUrl = messageSource.getMessage("uploadUrl");//获取文件默认上传路径
		//读取文件块
		byte[] readResult = FileUtils.randomReadFile(startPosition, endPosition, uploadUrl + attachment.getPath() + attachment.getFileName());
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment;filename="+ new String(attachment.getFileName().getBytes(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") >0?"gbk":"utf-8"), "ISO8859-1" ));  //火狐和Ie的下载文件名乱码问题  
//        response.addIntHeader("Content-Length",readResult.length);    //增加这一行
		//将读取的内容放入输出流
		response.getOutputStream().write(readResult);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	public static void main(String[] args) {
		File file = new File("F:/Downloads/妈妈再爱我一次-CD2.rmvb");
		System.out.println(file.length());
	}
}
