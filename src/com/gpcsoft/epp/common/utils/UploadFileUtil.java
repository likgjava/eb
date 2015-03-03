
package com.gpcsoft.epp.common.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;

/** 
 * @Description: 附件上传工具类 
 * @version V1.0
 * @Create Date 2011-3-14 下午07:14:20 By wanghz   
 */
public class UploadFileUtil {
	
	public UploadFileResult getDataObject(HttpServletRequest request,String viewName,String[] fileType){
		UploadFileResult result = new UploadFileResult();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(viewName);

		if (file == null) {
			result.getMessage().append(this.getFrameMessageResource().getMessage("srplatform.auth.attachment.file_can_not_empty"));
			return result;
		}
		boolean fileTypeIsCurrent = false;
		for (String type:fileType) {
			if (type.equals(file.getContentType())) {
				fileTypeIsCurrent = true;
				break;
			}
		}
		if (!fileTypeIsCurrent) {
			result.getMessage()
			.append(this.getFrameMessageResource().getMessage("common.uploadfile.upload_file_type_is_error"))
			.append(fileType.toString())
			.append(this.getFrameMessageResource().getMessage("common.uploadfile.please_upload_file_type"));
			return result;
		}
		
		if (file.getContentType().equals(UploadFileResult.FILE_TYPE_XML)) {
			result = this.getDocument(file);
		}
		if (file.getContentType().equals(UploadFileResult.FILE_TYPE_EXCEL_MS) || file.getContentType().equals(UploadFileResult.FILE_TYPE_EXCEL_OS)) {
			result = this.getHSSFWorkbook(file);
		}
		return result;
	}
	
	private FrameMessageResource messageResource = null;
	private FrameMessageResource getFrameMessageResource(){
		if (null == messageResource) {
			this.messageResource = (FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME);
		}
		return this.messageResource;
	}
	
	/**
	 * @Description: 获取附件Document文档
	 * @param viewName Form表单 文件筐Name名称
	 * @param fileType 允许的文件类型
	 * @param 
	 * @return UploadFileResult
	 * @throws Exception
	 * @Create Date 2011-3-14 下午07:29:49 By wanghz
	 */
	private UploadFileResult getDocument(CommonsMultipartFile file){
		UploadFileResult result = new UploadFileResult();


		String xml = new String(file.getBytes());
		if (null == xml || "".equals(xml)){
			result.getMessage().append(this.getFrameMessageResource().getMessage("common.uploadfile.file_content_is_empty"));
			return result;
		}
		try {
			org.w3c.dom.Document documentW3c = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file.getInputStream());
			org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
			org.dom4j.Document documentDom4j = xmlReader.read(documentW3c);
			result.setObject(documentDom4j);
			result.setResult(true);
		}catch (Exception e) {
			result.setE(e);
			result.getMessage().append(this.getFrameMessageResource().getMessage("common.uploadfile.parse_xml_is_error"));
		}
		return result;
	}
	
	public UploadFileResult getHSSFWorkbook(CommonsMultipartFile file){
		UploadFileResult result = new UploadFileResult();
		POIFSFileSystem poifs = null;
		try {
			poifs = new POIFSFileSystem(file.getInputStream());
			
			result.setObject(new HSSFWorkbook(poifs));
			result.setFielType(file.getContentType());
			result.setResult(true);
		} catch (IOException e) {
			result.setE(e);
		}
		try {
			result.setObject(new HSSFWorkbook(poifs));
		} catch (IOException e) {
			result.setE(e);
		}
		return result;
	}
}
