package com.gpcsoft.epp.common.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.dao.UserApiDao;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.manager.UserApiManager;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.common.utils.MD5Util;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.EmployeeManager;

@Service 
@SuppressWarnings("unchecked")
public class UserApiServiceImpl extends BaseGenericServiceImpl implements UserApiService {

	@Autowired(required=true)  @Qualifier("userApiDaoHibernate")
	private UserApiDao userApiDaoHibernate;
	
	@Autowired(required=true) @Qualifier("userApiManagerImpl")
	private UserApiManager userApiManager;
	
	private OrgInfoManager orgInfoManagerImpl;
	
	private OrgInfoManager getOrgInfoManagerImpl(){
		if(null == this.orgInfoManagerImpl){
			this.orgInfoManagerImpl = (OrgInfoManager)FrameBeanFactory.getBean("orgInfoManagerImpl");
		}
		return this.orgInfoManagerImpl;
	}
	
	@Autowired(required=true) @Qualifier("employeeManagerImpl")
	private EmployeeManager employeeManager;

	/** 
	 * Description :  根据部门ID获取对应的所有员工信息
	 * Create Date: 2010-6-20下午04:55:34 by Administrator  Modified Date: 2010-6-20下午04:55:34 by Administrator
	 * @param depId
	 * @return
	 */
	public List<Employee> getEmpListByCurUserDepId(String depId) {
		return userApiManager.getEmpListByCurUserDepId(depId);
	}

	/** 
	 * Description :  根据机构ID获得该机构下面的所有员工信息
	 * Create Date: 2010-6-20下午05:14:13 by Administrator  Modified Date: 2010-6-20下午05:14:13 by Administrator
	 * @param objId
	 * @return
	 */
	public List<Employee> getEmpListByCurUserOrgId(String orgId) {
		return userApiManager.getEmpListByCurUserOrgId(orgId);
	}

	/** 
	 * Description :  根据用户ID获取对应的部门信息
	 * Create Date: 2010-6-21上午09:51:10 by Administrator  Modified Date: 2010-6-21上午09:51:10 by Administrator
	 * @param userId
	 * @return
	 */
	public Department getDepartmentByUserId(String userId) {
		return userApiManager.getDepartmentByUserId(userId);
	}

	public List<Employee> getAllEmpListByCurUserEmpId(String empId) {
		return userApiManager.getAllEmpListByCurUserEmpId(empId);
	}

	/** 
	 * Description :  根据当前用户判断角色类型
	 * Create Date: 2010-6-18下午05:27:46 by Administrator  Modified Date: 2010-6-18下午05:27:46 by Administrator
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String checkRole(OrgInfo orgInfo)throws Exception{
		return userApiManager.checkRole(orgInfo);
	}
	/**
	 * 
	 * Description :获得所有代理机构  
	 * Create Date: 2010-7-9下午04:15:11 by liuke  Modified Date: 2010-7-9下午04:15:11 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<OrgInfo> getAllAgents(QueryObject queryObject) {
		return userApiManager.getAllAgents(queryObject);
	}

	/** 
	 * Description :  根据用户ID获取对应的机构信息
	 * Create Date: 2010-6-21上午09:51:10 by Administrator  Modified Date: 2010-6-21上午09:51:10 by Administrator
	 * @param userId
	 * @return
	 */
	public OrgInfo getOrgInfoByUser(User user) throws Exception{
		return userApiManager.getOrgInfoByUserId(user.getObjId());
	}

	
	/**
	 * 
	 * Description : 获得所有供应商列表
	 * Create Date: 2010-8-17上午09:57:30 by liuke  Modified Date: 2010-8-17上午09:57:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<OrgInfo> getAllSuppliers(QueryObject queryObject) {
		return userApiManager.getAllSuppliers(queryObject);
	}	
	/**
	 * 
	 * Description :  根据机构Id查询员工列表
	 * Create Date: 2010-8-31下午03:09:10 by shenjianzhuang  Modified Date: 2010-8-31下午03:09:10 by shenjianzhuang
	 * @param queryObject
	 * @param page
	 * @return
	 * @return  Page<Employee>
	 * @Exception 
	 */	 
	public Page<Employee> searchEmployee(final QueryObject queryObject,final Page<Employee> page) {
		return userApiManager.searchEmployee(queryObject, page);
	}
	
	/** 
	 * Description :  根据公司Id获取员工列表
	 * Create Date: 2010-10-22下午04:16:09 by yangx  Modified Date: 2010-10-22下午04:16:09 by yangx
	 * @param   companyId：公司Id;empIds：员工Id
	 * @return  List<Employee>
	 * @Exception   
	 */
	public List<Employee> getEmpListByCompanyId(String companyId,String empIds){
		if (empIds!=null&&!"".equals(empIds)) {
			empIds = empIds.replaceAll(",","','");
		}
		return userApiDaoHibernate.getEmpListByCompanyId(companyId,empIds);
	}
	
	/** 
	 * Description :  根据员工Id获取员工的账号信息
	 * Create Date: 2010-11-8下午05:14:33 by yangx  Modified Date: 2010-11-8下午05:14:33 by yangx
	 * @param   empId：员工Id
	 * @return  User
	 * @Exception   
	 */
	public User getUserByEmpId(String empId){
		return userApiManager.getUserByEmpId(empId);
	}
	
	/** 
	 * Description :  根据查询条件查询相关部门信息
	 * Create Date: 2010-11-16上午10:23:04 by yangx  Modified Date: 2010-11-16上午10:23:04 by yangx
	 * @param   query：查询条件
	 * @return  List
	 */
	public List getDepartmentByQueryObject(QueryObject query){
		List<Object[]> list = userApiManager.findByQuery(query);
		return list;
	}
	
	/** 
	 * Description :  获取大宗设置的代理机构
	 * Create Date: 2010-11-23上午10:22:59 by yangx  Modified Date: 2010-11-23上午10:22:59 by yangx
	 * @param   query:查询条件,page:分页对象
	 * @return  Page<OrgInfo>
	 * @Exception   
	 */
	public Page<OrgInfo> getAllAgentForBlockTrade(Page page){
		return this.getBlockTradeAgent(page);
	}
	
	private Page<OrgInfo> getBlockTradeAgent(Page page){
		String filePath = messageSource.getMessage("takeAgentUrl");
		String fileName = SeparationEnum.AGENT_BLOCKTRADE;
		String path=filePath.replaceAll("\\$\\{rootPath}", Constants.ROOTPATH);
		List<OrgInfo> orgInfoList = new ArrayList<OrgInfo>();
		int k=0;//定义数据条数变量
		if((path+fileName)!=null&&!"".equals(path+fileName)){
			File file = new File(path+fileName);
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String tempString = null;
				while ((tempString=reader.readLine())!= null) {//一次读入一行，直到读入null为文件结束
					if (page.getStart()<=k&&(k<page.getStart()+page.getPageSize())) {//判断是否是在抓取的数据范围内
						String[] contents = tempString.split(SeparationEnum.NUM);//将格式:objId#name数据分解
						OrgInfo orgInfo = new OrgInfo();
						orgInfo.setOrgName(contents[1]);
						orgInfo.setObjId(contents[0]);
						orgInfoList.add(orgInfo);
					}
					k++;
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
		page.setData(orgInfoList);//设置数据
		page.setTotalRowNum(k);//设置长度
		return page;
	}
	
	/** 
	 * Description :  保存代理机构
	 * Create Date: 2010-11-23下午01:07:21 by yangx  Modified Date: 2010-11-23下午01:07:21 by yangx
	 * @param   orgName：机构名称,orgInfoId：机构Id
	 * @return  OrgInfo
	 * @Exception   
	 */
	public OrgInfo saveAgentForBlockTrade(String orgName,String orgInfoId){
		OrgInfo orgInfo = new OrgInfo();
		orgInfo.setOrgName(orgName);
		orgInfo.setObjId(orgInfoId);
		this.saveAgentForFile(orgName, orgInfoId);
		return orgInfo;
	}
	
	private void saveAgentForFile(String orgName,String orgInfoId){
		String filePath = messageSource.getMessage("takeAgentUrl");
		String fileName = SeparationEnum.AGENT_BLOCKTRADE;
		String path=filePath.replaceAll("\\$\\{rootPath}", Constants.ROOTPATH);
		String content = orgInfoId+SeparationEnum.NUM+orgName+"\n";//将代理机构拼装好,格式:objId#name
		FileUtils.writerFileIsAppend(path+fileName, content);//往文件末尾追加
	}
	
	/** 
	 * Description :  删除设置的大宗代理机构
	 * Create Date: 2010-11-23下午02:27:25 by yangx  Modified Date: 2010-11-23下午02:27:25 by yangx
	 * @param   orgInfoIds：机构Ids
	 * @return  
	 * @Exception   
	 */
	public void removeAgentForBlockTrade(String orgInfoIds){
		String[] orgInfo = orgInfoIds.split(SeparationEnum.COMMA);
		if (orgInfo!=null&&orgInfo.length>0) {
			String filePath = messageSource.getMessage("takeAgentUrl");
			String fileName = SeparationEnum.AGENT_BLOCKTRADE;
			String path=filePath.replaceAll("\\$\\{rootPath}",Constants.ROOTPATH);
			String newContent = "";
			if((path+fileName)!=null&&!"".equals(path+fileName)){
				File file = new File(path+fileName);
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(file));
					String tempString = null;
					while ((tempString=reader.readLine())!= null) {//一次读入一行，直到读入null为文件结束
						boolean isDel = false;//是否删除标志
						String[] contents = tempString.split(SeparationEnum.NUM);//将格式:objId#name数据分解
						for (String orgInfoId:orgInfo) {//循环要删除的代理机构id
							if (orgInfoId.equals(contents[0])) {
								isDel = true;
							}
						}
						if (!isDel) {
								newContent +=tempString+"\n";//不删除的数据
						}
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
			FileUtils.writerFile(path+fileName,newContent);//往文件中写代理机构
		}
	}
	
	/** 
	 * Description :  根据机构Id过滤代理机构
	 * Create Date: 2010-11-23下午04:35:28 by yangx  Modified Date: 2010-11-23下午04:35:28 by yangx
	 * @param   orgInfoIds：要过滤的代理机构Id
	 * @return  List<OrgInfo>
	 * @Exception   
	 */
	public List<OrgInfo> getOrgInfoForAgent(String orgInfoIds){
		if (orgInfoIds!=null&&!"".equals(orgInfoIds)) {
			orgInfoIds = orgInfoIds.replaceAll(",","','");
		}
		return userApiDaoHibernate.getOrgInfoForAgent(orgInfoIds);
	}
	
	/** 
	 * Description :  从设置的大宗代理机构文件中查询代理机构
	 * Create Date: 2010-11-24上午11:47:30 by yangx  Modified Date: 2010-11-24上午11:47:30 by yangx
	 * @param   
	 * @return  List<OrgInfo>
	 * @Exception   
	 */
	public List<OrgInfo> getAgentForFile(){
		return userApiManager.getAgentForFile();
	}

	
	/** 
	 * Description : 根据代理机构ID查询代理机构信息
	 * Create Date: 2010-11-24上午11:46:14 by liuke  Modified Date: 2010-11-24上午11:46:14 by liuke
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Agency getAgentMessageByObjId(String objId) {
		return userApiDaoHibernate.getAgentMessageByObjId(objId);
	}
 

	public boolean findValidateUserByMd5Pssw(String userName, String md5Password) {
		return userApiDaoHibernate.findValidateUser(userName, md5Password);
	}

	public boolean findValidateUserByPssw(String userName, String password) {
		password = MD5Util.getMD5String(password);
		return userApiDaoHibernate.findValidateUser(userName, password);
	}
	
	public OrgInfo getOrgInfoByOrgCode(String orgCode) throws Exception {
		String hql = "from OrgInfo where orgCode = '" + orgCode+"'";
		 List  list = userApiDaoHibernate.findbyHql(hql);
		return (list.isEmpty())?null:(OrgInfo)list.get(0);
	}

	/**
	 * FuncName: getOrgInfoByOrginfoId
	 * Description :  根据orginfoID获得orginfo对象
	 * @param 
	 * @param orginfoId
	 * @return OrgInfo
	 * @author: liuke
	 * @Create Date:2011-4-13  下午05:15:34
	 * @Modifier: liuke
	 * @Modified Date:2011-4-13  下午05:15:34
	 */
	public OrgInfo getOrgInfoById(String orgId) {
		return (OrgInfo) this.getOrgInfoManagerImpl().get(orgId,OrgInfo.class);
	}

	/**
	 * 当前用户是否包括指定的权限
	 * @param url
	 * @return
	 * Created at 2011-6-13 10:50 by zhouzhanghe
	 */
	public boolean currUserAuthContainsURL(String url) {
		if(url == null || url.length() == 0)
			return false;
		
		for (String resurl:AuthenticationHelper.getCurrentUser().getAuth().split(",")) {
			if (url.equals(resurl)) 
				return true;
		}
		return false;
	}

	/**
	 * FuncName: isMemeberCurrOrg
	 * Description : 判断当前用户所属机构是否会员
	 * @return true:是会员,false:不是会员
	 * @author: zhouzhanghe
	 * @Create Date:2011-6-29  下午16:51:39
	 */
	public boolean getIsMemeberCurrOrg() {
		if(AuthenticationHelper.getCurrentUser().getOrgInfo() == null){
			List<OrgInfo> orgInfoList=findByHql("select ui.orgInfo from UserInfo ui where ui.objId=?", new Object[]{AuthenticationHelper.getCurrentUser().getObjId()});
			if(orgInfoList.size()!=0){
				GpcBaseObject orgInfo=orgInfoList.get(0);
				AuthenticationHelper.getCurrentUser().setOrgInfo(orgInfo);
			}
		}
		
		if(AuthenticationHelper.getCurrentUser().getOrgInfo() == null)
			return false;
		
		if("01".equals(((OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo()).getMemberClassId()))
				return true;
		
		return false;
	}
	
	/**
	 * FuncName: getEmployeeById
	 * Description :  根据ID得到Employee对象
	 * @param 
	 * @param empId
	 * @return Employee
	 * @author: liuke
	 * @Create Date:2011-4-14  下午01:36:56
	 * @Modifier: liuke
	 * @Modified Date:2011-4-14  下午01:36:56
	 */
	public Employee getEmployeeById(String empId) {
		return (Employee) employeeManager.get(empId, Employee.class);
	}
	
	/**
	 * FuncName: saveOrgInfo
	 * Description : 保存orginfo对象
	 * @param 
	 * @param orgInfo void
	 * @author: liuke
	 * @Create Date:2011-4-19  下午05:51:39
	 * @Modifier: liuke
	 * @Modified Date:2011-4-19  下午05:51:39
	 */
	public void updateOrgInfo(OrgInfo orgInfo) {
		userApiDaoHibernate.updateOrgInfo(orgInfo);
	}

	/**
	 * FuncName: getUserByUserName
	 * Description :  根据用户名获得用户对象
	 * @param 
	 * @param userName void
	 * @author: liuke
	 * @Create Date:2011-5-20  上午09:12:16
	 * @Modifier: liuke
	 * @Modified Date:2011-5-20  上午09:12:16
	 */
	public List<User> getUserByUserName(String userName,String password) {
		return userApiDaoHibernate.getUserByUserName(userName,password);
	}
	
	/** 
	 * Description :是否存在姓名的员工  
	 * Create Date: 2011-9-14上午09:43:42 by sunl  Modified Date: 2011-9-14上午09:43:42 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean  isExistEmployee(String name) {
		List<Employee> employeeList = userApiDaoHibernate.getEmployeeByName(name);
		if(employeeList != null && employeeList.size()>0) { 
			return true;
		} else {
			return false;
		}
	}
	
	/** 
	 * Description :  根据机构ID获取组织机构
	 * Create Date: 2011-9-14上午10:29:34 by sunl  Modified Date: 2011-9-14上午10:29:34 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Company getOrgnizationByOrgInfoId(String orgInfoId){
		return userApiDaoHibernate.getOrgnizationByOrgInfoId(orgInfoId);
	}
	
	/** 
	 * Description :  根据机构ID和员工名册获取员工
	 * Create Date: 2011-9-14上午10:29:34 by sunl  Modified Date: 2011-9-14上午10:29:34 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Employee getEmployeeByOrgAndName(String orgId,String name){
		return userApiDaoHibernate.getEmployeeByOrgAndName(orgId,name);
	}
	
	/**
	 * FuncName: getSuppliers2
	 * Description :  获取供应商
	 * @param queryObject
	 * @return List<OrgInfo>
	 * @author: shenjz
	 * @Create Date:2011-11-8  下午04:05:00
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-8  下午04:05:00
	 */
	public List<OrgInfo> getSuppliers2(String supplierIds,String orgName,String str) {
		return userApiDaoHibernate.getSuppliers2(supplierIds, orgName,str);
	}
}
