package com.gpcsoft.epp.eval.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.eval.domain.FactypeMfactor;

public interface FactypeMfactorDao extends BaseGenericDao<FactypeMfactor> {

	/**
	 * @Description: 根据指标ID删除指标与包件中间表
	 * @param factorId
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-10-11 下午04:11:32 By wanghz
	 */
	public void removeFactypeMfactorByFactorId(String factorId);
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  获取指标分类集合(电子评标系统接口)
	 * @param projectCode:项目编号,packCode:包组编号
	 * @return List<CongruousFactorType>
	 * @author: shenjz
	 * @Create Date:2011-3-24  下午01:35:07
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  下午01:35:07
	 */
	public List<FactypeMfactor> getCongruousFactorTypList(String projectCode,String packCode);
}
