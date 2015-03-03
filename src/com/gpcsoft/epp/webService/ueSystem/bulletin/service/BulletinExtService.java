package com.gpcsoft.epp.webService.ueSystem.bulletin.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.BulletinDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.BulletinInfoDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.PackDTO;

@SuppressWarnings("unchecked")
public interface BulletinExtService extends BaseGenericService {
	
	/** 
	 * Description :  getBulletinBybulNo
	 * Create Date: 2011-2-21下午01:19:41 by yangx  Modified Date: 2011-2-21下午01:19:41 by yangx
	 * @param   bulNo：公告编号
	 * @return  Bulletin
	 * @Exception   
	 */
	//public BulletinDTO getBulletinBybulNo(String bulNo);

	/** 
	 * Description :  获取公告列表
	 * Create Date: 2011-3-3下午06:07:54 by yangx  Modified Date: 2011-3-3下午06:07:54 by yangx
	 * @param   bulletintype:公告类型,startTime:开始时间,endTime:结束时间,purareacode:采购区域,purcategorycode:采购品目编号,bulletinnum,获取记录条数
	 * @return  String：拼装好的XML字符串
	 * @Exception   
	 */
	public List<BulletinDTO> getBulletinList(String bulletintype,String startTime,String endTime,String purareacode,String purcategorycode,String bulletinnum,String keyword)  throws Exception;
	
 
	/**
	 * Description :  获取公告详情
	 * @param bulletinNo 公告编号
	 * @param userId 用户Id
	 * @return
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 * @since 2011-3-14 14:38
	 */
	public BulletinInfoDTO getBulletinInfoDTO(String bulletinNo,String userId) throws Exception;
	
	/**
	 * Description :  获取公告关联的包组信息
	 * @param bulletinNo 公告编号
	 * @param userId 用户Id
	 * @param contextPath 应用上下文
	 * @return
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 * @since 2011-3-14 14:38
	 */
	public List<PackDTO> getPackDTO(String bulletinNo,String userId) throws Exception;
	
	/**
	 * Description :  提交用户查看信息
	 * @param bulletinCode 公告编号
	 * @param bulletinTitle 公告标题
	 * @param itemCode 采购品目编号
	 * @param areaCode 采购区域编号
	 * @param orgCode 查看机构编号
	 * @return
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 * @since 2011-3-14 14:38
	 */
	public String uploadViewInfo(String bulletinCode, String bulletinTitle, String itemCode, String areaCode, String orgCode);
	
	/**
	 * Description : 提交收藏公告信息
	 * @param bulletinCode 公告编号
	 * @param bulletinTitle 公告标题
	 * @param itemCode 采购品目编号
	 * @param areaCode 采购区域编号
	 * @param orgCode 收藏机构编号
	 * @return
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 * @since 2011-3-14 14:38
	 */
	public String uploadHeatInfo(String bulletinCode, String bulletinTitle, String itemCode, String areaCode, String orgCode);
	
	/**
	 * Description :提交报名信息
	 * @param bulletinCode 公告编号
	 * @param bulletinTitle 公告标题
	 * @param itemCode 采购品目编号
	 * @param areaCode 采购区域编号
	 * @param orgCode 报名机构编号
	 * @return
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 * @since 2011-3-14 14:38
	 */
	public String uploadApplyInfo(String bulletinCode, String bulletinTitle, String itemCode, String areaCode, String orgCode);
	
	/**
	 * Description :获取公告热度信息
	 * @param bulletinCode 公告编号
	 * @return 
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 * @since 2011-3-14 14:38
	 */
	public String getBulletinHeatInfo(String bulletinCode);
	
	/**
	 * Description :获取公告数量统计信息
	 * @param fromDate 开始时间
	 * @param toDate 截止时间
	 * @return
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 * @since 2011-3-14 14:38
	 */
	public String getBulletinNumInfo(String fromDate, String toDate)throws Exception;
}
