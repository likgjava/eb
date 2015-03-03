package com.gpcsoft.epp.webService.openbid.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.domain.BidSubKey;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.project.domain.Project;

public interface OpenBidService extends BaseGenericService<GpcBaseObject>{

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
	public String enterOpenBidRoom(String prjCode, String packCode, String orgCode, String username, String password);
	
	/**
	 * 签到
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param timeStamp 时间戳
	 * @param cert 签名CA人交换证书
	 * @param certNo 证书号（格式示例：证书号）
	 * @param orgCode 供应商机构编码
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
	public String saveSignOpenBidRoom(String prjCode, String packCode,
			String timeStamp, String cert, String certNo, String certType, String signType, String signPerson,
			String signPersonTel, String orgCode, String username, String password);
	
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
	public String getSignedSuppliers(String prjCode, String packCode, String orgCode, String username, String password);
	
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
	public String getWorkGroup(String prjCode, String packCode, String workGroupType, String orgCode, String username, String password);
	
	/**
	 * 经办人启动开标
	 * @param prjCode 项目编号
	 * @param packCode 包组编号
	 * @param caId ca证书id号
	 * @param orgCode 供应商机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String saveStartOpenBid(String prjCode, String packCode, String caId, String orgCode, String username, String password);
	
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
	public String getOpenBidInfo(String prjCode, String packCode, String username, String password, String caId, String tenderOrgCode, String tenderOrgName);
	
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
	public String saveConfirmOpenInfo(String prjCode, String packCode, String username, String password, String caId, String timeStamp, String confirmPerson, String orgCode, String orgName, String confirmResult, String confirmMemo);
	
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
	public String getOpenConfirmInfo(String prjCode, String packCode, String orgCode, String username, String password);
	
	/**
	 * 电子评审客户端向执行平台上传开标结果
	 * @param prjCode 项目编号
	 * @param packCode 包件编号
	 * @param username 用户名
	 * @param password 密码
	 * @param caId ca证书id号
	 * @param orgCode 代理机构编号
	 * @param orgName 代理机构名称
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String saveUploadOpenBidInfo(String prjCode, String packCode, String caId, String orgCode, String orgName,  String username, String password, String content);
	
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
	public String saveUploadSubKey(String prjCode, String packCode, String subKey, String timeStamp, String orgCode, String username, String password);
	
 
	
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
	public String downSubKey(String prjCode,String packCode,String orgCode,String caId,String username,	String password);
	
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
	public String getPrjOpenStatus(String prjCode, String packCode, String orgCode, String username, String password);
}
