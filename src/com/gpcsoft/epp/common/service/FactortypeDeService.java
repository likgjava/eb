package com.gpcsoft.epp.common.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.common.domain.FactortypeDe;
import com.gpcsoft.epp.common.exception.EsException;

public interface FactortypeDeService extends BaseGenericService<FactortypeDe> {

	
	/**
	 * @Description: 根据指标分类ID,获取下级指标分类记录条数
	 * @param 
	 * @return Integer
	 * @throws Exception
	 * @Create Date 2010-8-10 下午03:44:05 By wanghz
	 */
	public Integer getsubFactortypeDes(String factortypeId)throws Exception;
	
	/**
	 * @Description: 删除指标分类
	 * @param objId
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-8-10 下午04:31:12 By wanghz
	 */
	public void removeFactortypeDe(String objId)throws Exception;
	
	
	/**
	 * @Description: 创建指标分类
	 * @param factortypeDe
	 * @return FactortypeDe
	 * @throws Exception
	 * @Create Date 2010-9-7 下午03:14:08 By wanghz
	 */
	public FactortypeDe saveCreatefactortypeDe(FactortypeDe factortypeDe)throws EsException;
	
	/**
	 * @Description: 更新指标分类
	 * @param factortypeDe
	 * @return FactortypeDe
	 * @throws Exception
	 * @Create Date 2010-9-7 下午03:14:08 By wanghz
	 */
	public FactortypeDe updateFactortypeDe(FactortypeDe factortypeDe)throws EsException;
}
