package com.gpcsoft.epp.purchasedoc.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.purchasedoc.dao.PurchaseDocAttDao;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;

@Repository
public class PurchaseDocAttDaoHibernate extends
		BaseGenericDaoHibernate<PurchaseDocAtt> implements PurchaseDocAttDao {

	/**
	 * FuncName: getPurchaseDocAtt Description :
	 * 根据项目ID和包组Id和fileType类型获取招标文件附件表对象
	 * @param projectId
	 * @param packId 
	 * @param fileType[CommonEnum.FILE_TYPE_PURCHASE_BUSINESS_STATUS,FILE_TYPE_PURCHASE_TECHNICAL_STATUS] 
	 * @return PurchaseDocAtt
	 * @author: shenjz
	 * @Create Date:2011-8-26 上午10:16:51
	 * @Modifier: shenjz
	 * @Modified Date:2011-8-26 上午10:16:51
	 */
	public PurchaseDocAtt getPurchaseDocAtt(String projectId,
			String packId, String fileType) {
		List<PurchaseDocAtt> list = this
				.findbyHql("from PurchaseDocAtt purdocAtt where purdocAtt.tenderId='"
						+ projectId
						+ "' and purdocAtt.packId='"
						+ packId
						+ "' and purdocAtt.fileType = '" + fileType + "'");
		return (list.size() > 0) ? list.get(0) : null;
	}
	
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
	public List<PurchaseDocAtt> getPurchaseDocAttList(String projectId) {
		List<PurchaseDocAtt> list = this.findbyHql("from PurchaseDocAtt purdocAtt " +
				"where purdocAtt.tenderId='"+ projectId+"'");
		return list;
	}
	
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
	public List<PurchaseDocAtt> getPurchaseDocAttsByPrjId(String projectId) {
		List<PurchaseDocAtt> list = super.findbyHql("from PurchaseDocAtt purdocAtt where purdocAtt.purchaseDoc.project.objId='"+ projectId+"'");
		return list;
	}
	/** 
	 * FuncName : getPurchaseDocAttByPurchaseDocId
	 * Description :  根据招标文件Id获取附件对象
	 * Create Date: 2011-9-21上午11:41:44 by yangx  Modified Date: 2011-9-21上午11:41:44 by yangx
	 * @param   purchaseDocId：招标文件Id 
	 * @return  List<PurchaseDocAtt>
	 * @Exception   
	 */
	public List<PurchaseDocAtt> getPurchaseDocAttByPurchaseDocId(String purchaseDocId){
		List<PurchaseDocAtt> list = super.findbyHql("from PurchaseDocAtt purdocAtt where purdocAtt.purchaseDoc.objId='"+ purchaseDocId+"'");
		return list;
	}
}
