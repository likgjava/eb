package com.gpcsoft.bizplatform.suppliers.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class RecommendSupplierEnum 
{
	/**
     * 审核状态[00:待审核, 01:审核通过, 02:审核不通过]
     */
    public final static String AUDIT_STATUS = "recommendSupplier.auditStatus";
    public final static String AWAIT_AUDIT = "00";
    public final static String PASS_AUDIT = "01";
    public final static String NO_PASS_AUDIT = "02";
	public static String getAuditStatusCN(String str)
	{
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(RecommendSupplierEnum.AUDIT_STATUS, str);
	}
}
