package com.gpcsoft.epp.purchasedoc.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;

public interface PurchaseDocAttDao extends BaseGenericDao<PurchaseDocAtt> {


	/**
	 * FuncName: getPurchaseDocAtt Description :
	 * 根据项目Id和包组Id和fileType类型获取招标文件附件表对象
	 * 
	 * @param
	 * @param purchaseDocId
	 * @return PurchaseDocAtt
	 * @author: shenjz
	 * @Create Date:2011-8-26 上午10:16:51
	 * @Modifier: shenjz
	 * @Modified Date:2011-8-26 上午10:16:51
	 */
	public PurchaseDocAtt getPurchaseDocAtt(String projectId,
			String packId, String fileType);
	/**
	 * FuncName: getPurchaseDocAttList
	 * Description :  根据项目Id获取到招标文件从表集合
	 * @param projectId
	 * @return List<PurchaseDocAtt>
	 * @author: shenjz
	 * @Create Date:2011-8-29  下午02:20:06
	 * @Modifier: shenjz
	 * @Modified Date:2011-8-29  下午02:20:06
	 */
	public List<PurchaseDocAtt> getPurchaseDocAttList(String projectId);

	/**
	 * FuncName: getPurchaseDocAttsByPrjId Description :
	 * 根据项目ID获取招标文件相关的投标附件
	 * @param projectId  招标项目ID
	 * @return list<PurchaseDocAtt>
	 * @author: liuy
	 * @Create Date:2011-8-26 上午10:16:51
	 * @Modifier: shenjz
	 * @Modified Date:2011-8-26 上午10:16:51
	 */
	public List<PurchaseDocAtt> getPurchaseDocAttsByPrjId(String projectId);
	
	/** 
	 * FuncName : getPurchaseDocAttByPurchaseDocId
	 * Description :  根据招标文件Id获取附件对象
	 * Create Date: 2011-9-21上午11:41:44 by yangx  Modified Date: 2011-9-21上午11:41:44 by yangx
	 * @param   purchaseDocId：招标文件Id 
	 * @return  List<PurchaseDocAtt>
	 * @Exception   
	 */
	public List<PurchaseDocAtt> getPurchaseDocAttByPurchaseDocId(String purchaseDocId);
	
}
