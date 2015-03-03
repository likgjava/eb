package com.gpcsoft.epp.purchasedoc.service.impl;


import java.util.List;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.purchasedoc.dao.PurchaseDocAttDao;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;
import com.gpcsoft.epp.purchasedoc.manager.PurchaseDocAttManager;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocAttService;

@Service 
public class PurchaseDocAttServiceImpl extends BaseGenericServiceImpl<PurchaseDocAtt> implements PurchaseDocAttService {

	@Autowired(required=true) @Qualifier("purchaseDocAttManagerImpl")
	private PurchaseDocAttManager purchaseDocAttManager;
	
	@Autowired(required=true)  @Qualifier("purchaseDocAttDaoHibernate")
	private PurchaseDocAttDao purchaseDocAttDaoHibernate;
	
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
		return purchaseDocAttDaoHibernate.getPurchaseDocAtt(projectId, packId, fileType);
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
	public List<PurchaseDocAtt> getPurchaseDocAttList(String projectId){
		return purchaseDocAttDaoHibernate.getPurchaseDocAttList(projectId);
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
		List list = purchaseDocAttDaoHibernate.getPurchaseDocAttsByPrjId(projectId);
		return list;
	}

}
