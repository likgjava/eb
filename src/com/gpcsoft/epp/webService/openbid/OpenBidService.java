package com.gpcsoft.epp.webService.openbid;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

@Controller//标识为控制器
@Scope("request")
@RequestMapping("/openBidHttpService.do")//页面请求路径,可修改
public class OpenBidService extends AnnotationMultiController<GpcBaseObject>{
	
	private com.gpcsoft.epp.webService.openbid.service.OpenBidService openBidServiceImpl;
	private com.gpcsoft.epp.webService.openbid.service.OpenBidService getOpenBidServiceImpl(){
		if(openBidServiceImpl == null)
			this.openBidServiceImpl = (com.gpcsoft.epp.webService.openbid.service.OpenBidService)FrameBeanFactory.getBean("wsOpenBidServiceImpl");
		return openBidServiceImpl;
	}

	/**
	 * 获取开标室信息
	 *<br>根据项目编号、包编号进入开标室，获取开标时间和状态信息以及其他信息（开标议程/视音频访问地址）。
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param orgCode 供应商机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String enterOpenBidRoom(String prjCode, String packCode, String orgCode, String username, String password){
		return getOpenBidServiceImpl().enterOpenBidRoom(prjCode, packCode, orgCode, username, password);
	}
	
	/**
	 * 获取开标室信息
	 *<br>根据项目编号、包编号进入开标室，获取开标时间和状态信息以及其他信息（开标议程/视音频访问地址）。
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param orgCode 供应商机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @author zhouzhanghe
	 * @throws IOException 
	 * @created date 2011-11-03 14:07
	 */
	@RequestMapping(params = "method=enterOpenBidRoom")
	public void enterOpenBidRoomViaHTTP(HttpServletResponse response,HttpServletRequest request, String prjCode, String packCode, String orgCode, String username, String password) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().enterOpenBidRoom(prjCode, packCode, orgCode, username, password));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	
	/**
	 * 签到
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param timeStamp 时间戳
	 * @param signData 签名值
	 * @param cert 签名CA人交换证书
	 * @param certNo 证书号（格式示例：证书号）
	 * @param orgName 供应商机构名称
	 * @param certType 证书类型
	 * @param signPerson 签到人
	 * @param signPersonTel 签到人联系电话
	 * @param signType 签到类型
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String signOpenBidRoom(String prjCode, String packCode,
			String timeStamp, String cert, String certNo, String certType, String signType, String signPerson,
			String signPersonTel, String orgCode, String username, String password){
		return getOpenBidServiceImpl().saveSignOpenBidRoom(prjCode, packCode, timeStamp, cert, certNo, certType, signType, signPerson, signPersonTel, orgCode, username, password);
	}
	
	
	/**
	 * 签到
	 * @param prjCode 项目编号
	 * @param packCode 项目编号
	 * @param signData 签名值
	 * @param cert 签名CA人交换证书
	 * @param certNo 证书号（格式示例：证书号）
	 * @param orgCode 供应商机构编号
	 * @param certType 证书类型
	 * @param signPerson 签到人
	 * @param signPersonTel 签到人联系电话
	 * @param signType 签到类型
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @author zhouzhanghe
	 * @throws IOException 
	 * @created date 2011-11-03 14:07
	 */
	@RequestMapping(params = "method=signOpenBidRoom")
	public void signOpenBidRoomViaHTTP(HttpServletResponse response,HttpServletRequest request,String prjCode, String packCode,
			String timeStamp, String cert, String certNo, String certType, String signType, String signPerson,
			String signPersonTel, String orgCode, String username, String password) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().saveSignOpenBidRoom(prjCode, packCode, timeStamp, cert, certNo, certType, signType, signPerson, signPersonTel, orgCode, username, password));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 获取签到到开标室的供应商列表
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param orgCode 供应商机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String getSignedSuppliers(String prjCode, String packCode, String orgCode, String username, String password){
		return getOpenBidServiceImpl().getSignedSuppliers(prjCode, packCode, orgCode, username, password);
	}
	
	/**
	 * 获取签到到开标室的供应商列表
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param orgCode 供应商机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @author zhouzhanghe
	 * @throws IOException 
	 * @created date 2011-11-03 14:07
	 */
	@RequestMapping(params = "method=getSignedSuppliers")
	public void getSignedSuppliersViaHTTP(HttpServletResponse response,HttpServletRequest request, String prjCode, String packCode, String orgCode, String username, String password) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().getSignedSuppliers(prjCode, packCode, orgCode, username, password));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 获取工作小组成员
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param workGroupType 工作组类型
	 * @param orgCode 供应商机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String getWorkGroup(String prjCode, String packCode, String workGroupType, String orgCode, String username, String password){
		return getOpenBidServiceImpl().getWorkGroup(prjCode, packCode, workGroupType, orgCode, username, password);
	}
	
	/**
	 * 获取工作小组成员
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param workGroupType 工作组类型
	 * @param orgCode 供应商机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @author zhouzhanghe
	 * @throws IOException 
	 * @created date 2011-11-03 14:07
	 */
	@RequestMapping(params = "method=getWorkGroup")
	public void getWorkGroupViaHTTP(HttpServletResponse response,HttpServletRequest request, String prjCode, String packCode, String workGroupType, String orgCode, String username, String password) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().getWorkGroup(prjCode, packCode, workGroupType, orgCode, username, password));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 经办人启动开标
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param orgCode 供应商机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String startOpenBid(String prjCode, String packCode, String caId, String orgCode, String username, String password){
		return getOpenBidServiceImpl().saveStartOpenBid(prjCode, packCode, caId, orgCode, username, password);
	}
	
	/**
	 * 经办人启动开标
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param orgCode 供应商机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @author zhouzhanghe
	 * @throws IOException 
	 * @created date 2011-11-03 14:07
	 */
	@RequestMapping(params = "method=startOpenBid")
	public void startOpenBidViaHTTP(HttpServletResponse response,HttpServletRequest request, String prjCode, String packCode, String caId, String orgCode, String username, String password) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().saveStartOpenBid(prjCode, packCode, caId, orgCode, username, password));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 在线获取开标信息
	 * @param prjCode 项目编号
	 * @param packCode 包件编号
	 * @param username 用户名
	 * @param password 密码
	 * @param caId ca证书id号
	 * @param tenderOrgCode 投标机构编号
	 * @param tenderOrgName 投标机构名称
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String getOpenBidInfo(String prjCode, String packCode, String username, String password, String caId, String tenderOrgCode, String tenderOrgName){
		return getOpenBidServiceImpl().getOpenBidInfo(prjCode, packCode, username, password, caId, tenderOrgCode, tenderOrgName);
	}
	
	/**
	 * 在线获取开标信息
	 * @param prjCode 项目编号
	 * @param packCode 包件编号
	 * @param username 用户名
	 * @param password 密码
	 * @param caId ca证书id号
	 * @param tenderOrgCode 投标机构编号
	 * @param tenderOrgName 投标机构名称
	 * @return
	 * @author zhouzhanghe
	 * @throws IOException 
	 * @created date 2011-11-03 14:07
	 */
	@RequestMapping(params = "method=getOpenBidInfo")
	public void getOpenBidInfoViaHTTP(HttpServletResponse response,HttpServletRequest request,String prjCode, String packCode, String username, String password, String caId, String tenderOrgCode, String tenderOrgName) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().getOpenBidInfo(prjCode, packCode, username, password, caId, tenderOrgCode, tenderOrgName));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 供应商远程确认开标信息
	 * @param prjCode 项目编号
	 * @param packCode 包件编号
	 * @param username 用户名
	 * @param password 密码
	 * @param caId ca证书id号
	 * @param timeStamp 时间戳
	 * @param confirmPerson 确认人
	 * @param orgCode 投标机构编号
	 * @param orgName 投标机构名称
	 * @param confirmResult 确认结果（Y/N）
	 * @param confirmMemo 确认意见
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String confirmOpenInfo(String prjCode, String packCode, String username, String password, String caId, String timeStamp, String confirmPerson, String orgCode, String orgName, String confirmResult, String confirmMemo){
		return getOpenBidServiceImpl().saveConfirmOpenInfo(prjCode, packCode, username, password, caId, timeStamp, confirmPerson, orgCode, orgName, confirmResult, confirmMemo);
	}
	
	/**
	 * 供应商远程确认开标信息
	 * @param prjCode 项目编号
	 * @param packCode 包件编号
	 * @param username 用户名
	 * @param password 密码
	 * @param caId ca证书id号
	 * @param timeStamp 时间戳
	 * @param confirmPerson 确认人
	 * @param orgCode 投标机构编号
	 * @param orgName 投标机构名称
	 * @param confirmResult 确认结果（Y/N）
	 * @param confirmMemo 确认意见
	 * @return
	 * @author zhouzhanghe
	 * @throws IOException 
	 * @created date 2011-11-03 14:07
	 */
	@RequestMapping(params = "method=confirmOpenInfo")
	public void confirmOpenInfoViaHTTP(HttpServletResponse response,HttpServletRequest request,String prjCode, String packCode, String username, String password, String caId, String timeStamp, String confirmPerson, String orgCode, String orgName, String confirmResult, String confirmMemo) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().saveConfirmOpenInfo(prjCode, packCode, username, password, caId, timeStamp, confirmPerson, orgCode, orgName, confirmResult, confirmMemo));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 从执行平台获取确认信息
	 * @param prjCode 项目编号
	 * @param packCode 包件编号
	 * @param username 用户名
	 * @param password 密码
	 * @param orgCode 投标机构编号
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String getOpenConfirmInfo(String prjCode, String packCode, String orgCode, String username, String password){
		return getOpenBidServiceImpl().getOpenConfirmInfo(prjCode, packCode, orgCode, username, password);
	}
	
	/**
	 * 从执行平台获取确认信息
	 * @param prjCode 项目编号
	 * @param packCode 包件编号
	 * @param orgCode 投标机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @author zhouzhanghe
	 * @throws IOException 
	 * @created date 2011-11-03 14:07
	 */
	@RequestMapping(params = "method=getOpenConfirmInfo")
	public void getOpenConfirmInfoViaHTTP(HttpServletResponse response,HttpServletRequest request,String prjCode, String packCode, String orgCode, String username, String password) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().getOpenConfirmInfo(prjCode, packCode, orgCode, username, password));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 电子评审客户端向执行平台上传开标结果
	 * @param prjCode 项目编号
	 * @param packCode 包件编号
	 * @param username 用户名
	 * @param password 密码
	 * @param caId ca证书id号
	 * @param orgCode 代理机构编号
	 * @param orgName 代理机构名称
	 * @param content 开标结果内容
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String uploadOpenBid(String prjCode, String packCode, String caId, String orgCode, String orgName,  String username, String password, String content){
		return getOpenBidServiceImpl().saveUploadOpenBidInfo(prjCode, packCode, caId, orgCode, orgName, username, password, content);
	}
	
	/**
	 * 电子评审客户端向执行平台上传开标结果
	 * @param prjCode 项目编号
	 * @param packCode 包件编号
	 * @param username 用户名
	 * @param password 密码
	 * @param caId ca证书id号
	 * @param orgCode 代理机构编号
	 * @param orgName 代理机构名称
	 * @param content 开标结果内容
	 * @return
	 * @author zhouzhanghe
	 * @throws IOException 
	 * @created date 2011-11-03 14:07
	 */
	@RequestMapping(params = "method=uploadOpenBid")
	public void uploadOpenBidViaHTTP(HttpServletResponse response,HttpServletRequest request,String prjCode, String packCode, String caId, String orgCode, String orgName,  String username, String password, String content) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().saveUploadOpenBidInfo(prjCode, packCode, caId, orgCode, orgName, username, password, content));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * FuncName: uploadSubKey
	 * Description :上传开标子密钥       
     * @param request
	 * @param response
	 * @throws Exception void
	 * @author: caojz
	 * @Create Date:2011-8-2  下午01:45:02
	 * @Modifier: caojz
	 * @Modified Date:2011-8-2  下午01:45:02
	 */
	public String uploadSubKey(String prjCode, String packCode, String subKey, String timeStamp, String orgCode, String username, String password){
		return getOpenBidServiceImpl().saveUploadSubKey(prjCode, packCode, subKey, timeStamp, orgCode, username, password);
	}
	
	/**
	 * FuncName: uploadSubKey
	 * Description :上传开标子密钥       
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: caojz
	 * @throws IOException 
	 * @Create Date:2011-8-2  下午01:45:02
	 * @Modifier: caojz
	 * @Modified Date:2011-8-2  下午01:45:02
	 */
	@RequestMapping(params = "method=uploadSubKey")
	public void uploadSubKeyViaHTTP(HttpServletResponse response,HttpServletRequest request,String prjCode, String packCode, String subKey, String timeStamp, String orgCode, String username, String password) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().saveUploadSubKey(prjCode, packCode, subKey, timeStamp, orgCode, username, password));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * FuncName: downSubKey
	 * Description :下载开标子密钥       
	 * @param prjCode   项目编号
	 * @param packCode   包组编号
	 * @param orgCode    机构编号
	 * @param caId     ca证书id号
	 * @param username   用户名
	 * @param password   密码
	 * @throws Exception void
	 * @author: caojz
	 * @Create Date:2011-8-2  下午01:45:02
	 * @Modifier: caojz
	 * @Modified Date:2011-8-2  下午01:45:02
	 */
	public String downSubKey(String prjCode,String packCode,String orgCode,String caId,String username,	String password){
		return getOpenBidServiceImpl().downSubKey(prjCode, packCode, orgCode, caId, username, password);
	}
	
	/**
	 * FuncName: downSubKey
	 * Description :下载开标子密钥       
	 * @param prjCode   项目编号
	 * @param packCode   包组编号
	 * @param orgCode    机构编号
	 * @param caId     ca证书id号
	 * @param username   用户名
	 * @param password   密码
	 * @throws Exception void
	 * @author: caojz
	 * @throws IOException 
	 * @Create Date:2011-8-2  下午01:45:02
	 * @Modifier: caojz
	 * @Modified Date:2011-8-2  下午01:45:02
	 */
	@RequestMapping(params = "method=downSubKey")
	public void downSubKeyViaHTTP(HttpServletResponse response,HttpServletRequest request,String prjCode,String packCode,String orgCode,String caId,String username, String password) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().downSubKey(prjCode, packCode, orgCode, caId, username, password));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * FuncName: getPrjOpenStatus
	 * Description : 从执行平台获取项目/包件的开标信息   
	 * @param prjCode
	 * @param packCode
	 * @param orgCode
	 * @param username
	 * @param password
	 * @return
	 * @author: zhouzhanghe
	 * @Create Date:2011-11-10 10:51
	 */
	public String getPrjOpenStatus(String prjCode, String packCode, String orgCode, String username, String password){
		return getOpenBidServiceImpl().getPrjOpenStatus(prjCode, packCode, orgCode, username, password);
	}
	
	/**
	 * FuncName: getPrjOpenStatus
	 * Description : 从执行平台获取项目/包件的开标信息   
	 * @param prjCode
	 * @param packCode
	 * @param orgCode
	 * @param username
	 * @param password
	 * @return
	 * @author: zhouzhanghe
	 * @throws IOException 
	 * @Create Date:2011-11-10 10:51
	 */
	@RequestMapping(params = "method=getPrjOpenStatus")
	public void getPrjOpenStatusViaHTTP(HttpServletResponse response,HttpServletRequest request, String prjCode, String packCode, String orgCode, String username, String password) throws IOException{
		response.getWriter().write(getOpenBidServiceImpl().getPrjOpenStatus(prjCode, packCode, orgCode, username, password));
		response.getWriter().flush();
		response.getWriter().close();
	}
}
