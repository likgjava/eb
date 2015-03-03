package com.gpcsoft.epp.projreview.service;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.projreview.domain.OpenBidSign;
import com.gpcsoft.srplatform.auth.domain.User;

public interface OpenBidSignService extends BaseGenericService<OpenBidSign> {

	 /**
	 * FuncName: anonymousSignUp
	 * Description :  匿名投标
	 * @param 
	 * @param bidNo
	 * @param openBidSign
	 * @param orginfo
	 * @param currentUser
	 * @param csSn void
	 * @author: shenjz
	 * @Create Date:2011-12-25  下午02:25:39
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-25  下午02:25:39
	*/		 
	public void saveAnonymousSignUp(String bidNo,String projectId,OpenBidSign openBidSign,
			OrgInfo orginfo, User currentUser, String csSn);
	/**
	 * FuncName: getListBySupplierIdAndTenderId
	 * Description :  创建或修改对象
	 * @param 
	 * @param orginfo
	 * @param tenderId
	 * @return List<OpenBidSign>
	 * @author: shenjz
	 * @Create Date:2011-12-25  下午04:47:02
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-25  下午04:47:02
	 */
	public List<OpenBidSign> getListBySupplierIdAndTenderId(OrgInfo orginfo,String tenderId);
}
