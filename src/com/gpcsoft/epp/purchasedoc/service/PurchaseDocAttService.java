package com.gpcsoft.epp.purchasedoc.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;

public interface PurchaseDocAttService extends BaseGenericService<PurchaseDocAtt> {

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

}
