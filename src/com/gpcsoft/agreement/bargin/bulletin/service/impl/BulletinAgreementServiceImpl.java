package com.gpcsoft.agreement.bargin.bulletin.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gpcsoft.agreement.bargin.bulletin.dao.BulletinShowDao;
import com.gpcsoft.agreement.bargin.bulletin.manager.BulletinAgreementManager;
import com.gpcsoft.agreement.bargin.bulletin.service.BulletinAgreementService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.Query;
import com.gpcsoft.core.dao.hibernate.query.Restrictions;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.epp.bulletin.dao.BulletinDao;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.bulletin.service.impl.BulletinServiceImpl;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.dao.AttachmentDao;
import com.gpcsoft.srplatform.auth.domain.Attachment;

@Service 
public class BulletinAgreementServiceImpl extends BulletinServiceImpl implements BulletinAgreementService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("bulletinManagerImpl")
	private BulletinManager bulletinManager;

	@Autowired(required=true)  @Qualifier("bulletinDaoHibernate")
	private BulletinDao bulletinDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("bulletinShowDaoHibernate")
	private BulletinShowDao bulletinShowDao;
	
	@Autowired(required=true)  @Qualifier("attachmentDaoHibernate")
	private AttachmentDao attachmentDao;
	
	@Autowired(required=true)  @Qualifier("bulletinAgreementManagerImpl")
	private BulletinAgreementManager bulletinAgreementManager;
	
	/** 
	 * Description : 保存公告
	 * Create Date: 2010-10-8下午05:19:42 by guoyr  Modified Date: 2010-10-8下午05:19:42 by guoyr
	 * @param   
	 * @return  
	 * @throws IOException 
	 * @Exception   
	 */
	public Bulletin saveBulletinAgreement(Bulletin bulletin) throws IOException {
		return bulletinAgreementManager.saveBulletinAgreement(bulletin);
	}
	
	/** 
	 * Description : 保存公告（接口）
	 * Create Date: 2010-10-8下午05:19:42 by guoyr  Modified Date: 2010-10-8下午05:19:42 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Bulletin saveBulletinAgreementInterface(Project project)  throws Exception{
		return bulletinAgreementManager.saveBulletinAgreementInterface(project);
	}
	
	/** 
	 * Description : 根据项目ID和项目的类型获得项目的公告 
	 * Create Date: 2010-12-13下午02:41:35 by guoyr  Modified Date: 2010-12-13下午02:41:35 by guoyr
	 * @param   projectId 项目id
	 * @param   bullType 项目类型
	 * @return  
	 * @Exception   
	 */
	public Bulletin getProjectBulletin(String projectId,String bullType){
		Query query = new Query(Bulletin.class);
		query.addExpression(Restrictions.eq("project.objId", projectId));
		query.addExpression(Restrictions.eq("bullType", bullType));
		List<Bulletin> list = bulletinDaoHibernate.findByQuery(query);
		Bulletin bulletin = null;
		if(null != list && list.size() > 0){
			bulletin = list.get(0);
		}
		return bulletin;
	}
	
	/** 
	 * Description : 根据项目获得模板所需的数据 
	 * Create Date: 2010-12-13下午03:30:43 by guoyr  Modified Date: 2010-12-13下午03:30:43 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getBulletinTemplateMap(Project project) throws Exception{
		return bulletinAgreementManager.getBulletinTemplateMap(project);
	}

	/** 
	 * Description :  批量更新状态
	 * Create Date: 2011-3-11下午10:51:30 by yucy  Modified Date: 2011-3-11下午10:51:30 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateStatus(String objIds, Map<String, Object> param) throws Exception {
		return bulletinShowDao.updateStatus(objIds,param);
	}

	/** 
	 * Description :  读取excel内容，批量保存采购公告
	 * Create Date: 2011-3-17下午03:38:55 by sunl  Modified Date: 2011-3-17下午03:38:55 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("deprecation")
	public String saveBulletinBatch(HttpServletRequest request,MultipartFile file) throws IOException {
		/**
		 * 1. 验证传入的MultipartFile 文件对象，然后从文件对象获取 文件输入流以 POIFSFileSystem 对于MSOffice文档文件结构的访问。
		 * 2. 遍历导入的Excel的每一个Sheet工作表，在当前Sheet工作表遍历所有行
		 * 3. 开始解析Excel，首先获取采购计划信息的项目属性，获取项目属性后才能继续解析采购计划信息。
		 * 4. 解析公司采购计划信息，由于Excel可以多行合并，先解析第一行，如果解析到空值那么获取上一次获取的数据。
		 * 5. 解析公司采购计划条目,由于Excel可以多行合并，先解析第一行，如果解析到空值那么获取上一次获取的数据。
		 * 6. Excel解析完毕以后，进行保存数据.
		 */
		try {
			/**
			 * 1.验证传入的MultipartFile 文件对象，然后从文件对象获取 文件输入流以 POIFSFileSystem 对于MSOffice文档文件结构的访问。
			 */
			if(file.isEmpty()){
				return "导入的文件不能为空！";
			}
			String fileName = file.getOriginalFilename().toString().trim();
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
			if(!"xls".equals(fileType)){//判断文件类型
				return "导入文件格式错误！";
			}else if("xlsx".equals(fileType)){
				return "目前尚未支持office excel2007版本!";
			}
			POIFSFileSystem poifs = new POIFSFileSystem(file.getInputStream());				//POIFSFileSystem 对于MSOffice文档文件结构的访问,需要文件输入流
			HSSFWorkbook wb;
			int sheetNum;
			try {
				wb = new HSSFWorkbook(poifs);												//使用POIFS以流的形式创建或打开文档然后将其连接,获得了Workbook对象
				sheetNum = wb.getSelectedTab();
			} catch (Exception e) {
				return "Excel导入失败,请去掉Excel的数据有效性！";
			}										
			
			//读取模板内容
			String message = getTemplateContent();
			String content = "";
			/**
			 * 2.遍历导入的Excel的每一个Sheet工作表，在当前Sheet工作表遍历所有行
			 */
			for(int sheetNumTemp = 0;sheetNumTemp <= sheetNum;sheetNumTemp++){				//循环所有导入的Excel Sheet工作表。
				HSSFSheet sheet = wb.getSheetAt(sheetNumTemp);	
				
				for(int i=1;i<=sheet.getLastRowNum();i++){									//循环导入的Excel每一行
					HSSFRow row = sheet.getRow(i);											//获取当前行
					Bulletin bulletin = new Bulletin();
					
					/**
					 * 4.开始解析Excel-解析公告信息
					 */
					String cell_0 = row.getCell((short)0).getStringCellValue().trim();	//标题
					String cell_1 = row.getCell((short)1).getStringCellValue().trim();	//采购单位
					String cell_2 = row.getCell((short)2).getStringCellValue().trim();	//采购商品
					
					String cell_3 = "";	//处理采购数量输入为数字的情况
					String cell_7 = ""; //处理联系方式输入为手机的情况
					String cell_4 = ""; //处理采购日期输入为日期的情况
					if(row.getCell((short)3).getCellType()==HSSFCell.CELL_TYPE_NUMERIC) {
						cell_3 = String.valueOf(row.getCell((short)3).getNumericCellValue());
					} else {
						cell_3 = row.getCell((short)3).getStringCellValue().trim();
					}
					if(row.getCell((short)7).getCellType()==HSSFCell.CELL_TYPE_NUMERIC) {
						cell_7 = String.valueOf(row.getCell((short)7).getNumericCellValue());
						String temp = cell_7.replace('.', ' ').replaceAll("\\s+", "");
						cell_7 = temp.substring(0, 11);
					} else {
						cell_7 = row.getCell((short)7).getStringCellValue().trim();
					}
					if(row.getCell((short)4).getCellType()==HSSFCell.CELL_TYPE_NUMERIC) {
						Date d = row.getCell((short)4).getDateCellValue();     
					    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");     
					    System.out.print(formater.format(d));   
					    cell_4 = formater.format(d);
					} else {
						cell_4 = row.getCell((short)4).getStringCellValue().trim();
					}
					
					String cell_5 = row.getCell((short)5).getStringCellValue().trim();	//采购流程要求
					String cell_6 = row.getCell((short)6).getStringCellValue().trim();	//联系人
					bulletin.setBullType(BulletinTypeEnum.PURCHASE_PREVIEW);//类型
					bulletin.setBullTitle(cell_0);//标题
					bulletin.setAuditStatus("01");//审核通过
					bulletin.setRelStatus("02");//已发布
					bulletin.setUseStatus("01");//有效
					bulletin.setCreateTime(new Date());
					bulletin.setCreateUser(AuthenticationHelper.getCurrentUser());
					
					content = message;
					//替换模板里的参数
					content = content.replace("XXXXXXtitleXX", cell_0);//标题
					content = content.replace("XXXXXXcreatTimeXX", DateUtil.getCurDateTime());//发布时间
					content = content.replace("XXXXXXbuyerXX", cell_1);//采购单位
					content = content.replace("XXXXXXproductXX", cell_2);//采购商品
					content = content.replace("XXXXXXbuyTimeXX", cell_4);//采购时间
					content = content.replace("XXXXXXbuyCountXX", cell_3);//采购数量
					content = content.replace("XXXXXXrequireXX", cell_5);//采购流程要求
					content = content.replace("XXXXXXcontactXX", cell_6);//联系人
					content = content.replace("XXXXXXtelXX", cell_7);//联系方式
					
					/** 公告文件的存储路径 */
					Properties properties = new Properties();
			        InputStream in =BulletinAgreementServiceImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
			        properties.load(in);
			        String bulletinUrl = properties.getProperty("bulletinUrl") ;
			        
					String saveName=java.util.UUID.randomUUID().toString();
					File bulletinFile = new File((bulletinUrl+DateUtil.getCurDate()+File.separator+saveName).replace("/", File.separator)); 
					Attachment attachment = new Attachment();
					attachment.setCreator(AuthenticationHelper.getCurrentUser());
					attachment.setRelationId(java.util.UUID.randomUUID().toString().replace('-', '_'));
					attachment.setPath(DateUtil.getCurDate()+File.separator);//相对路径
					attachment.setUploadTime(new Date());
					attachment.setFileName(saveName);
					attachment.setIsUsed(false);//此时还没有被引用
					attachment = attachmentDao.save(attachment);
					//写入文件到指定路径
					if (!bulletinFile.getParentFile().exists()) {
						bulletinFile.getParentFile().mkdirs(); 
					}
					FileUtil.writerFile(bulletinUrl+File.separator+DateUtil.getCurDate()+File.separator+attachment.getFileName(),content,"UTF-8");
					bulletin.setContent(attachment);
					bulletinDaoHibernate.save(bulletin);
				}
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入Excel文件失败！";
		}
	}
	
	//读取公告html模板内容
	private String getTemplateContent() throws Exception {
		String path = Constants.ROOTPATH+"WEB-INF"+File.separator+"conf"+File.separator+"template"+File.separator+"bulletin.htm";
		String message = "";
		StringBuffer stringBuffer = new StringBuffer();
		Reader reader = null;
		int ch = 0;
		reader = new InputStreamReader(new FileInputStream(path), "GB2312");
		while ((ch = reader.read()) != -1) {
			stringBuffer.append((char)ch);
		}
		reader.close();
		message = stringBuffer.toString();
		return message;
	}
}
