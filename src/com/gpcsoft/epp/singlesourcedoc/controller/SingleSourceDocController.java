package com.gpcsoft.epp.singlesourcedoc.controller;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;

/**
 * @gpcsoft.view value="singleSourceDocFormView"
 *  url="view/es/singlesource/singlesourcedoc/singleSourceDocForm.jsp"
 * @gpcsoft.view value="singleSourceDocDetailView"
 *  url="view/es/singlesource/singlesourcedoc/singleSourceDocDetail.jsp"
 * @gpcsoft.view value="updateSingleSourceDocFormView"
 *  url="view/es/singlesource/singlesourcedoc/updateSingleSourceDocForm.jsp"  
 * @gpcsoft.view value="singleSourceDocConfigView"
 *  url="view/es/singlesource/singlesourcedoc/singleSourceDocConfig.jsp" 
 *  @gpcsoft.view value="singleSourceDocProcurementView"
 *  url="view/es/singlesource/singlesourcedoc/singleSourceDocProcurement.jsp"   
 *  @gpcsoft.view value="InqpDocAuditingView"
 *  url="view/es/inquiryprice/inqpdoc/InqpDocAuditing.jsp"  
 *  @gpcsoft.view value="moreInqpDocRecordListView"
 *  url="view/es/inquiryprice/inqpdoc/moreInqpDocRecordList.jsp"    
 *  @gpcsoft.view value="configInqpDocPageView"
 *  url="view/es/inquiryprice/inqpdoc/configInqpDocPage.jsp"    
 *  @gpcsoft.view value="auditingInqpDocPageView"
 *  url="view/es/inquiryprice/inqpdoc/InqpDocProcurement.jsp"    
 *  @gpcsoft.view value="InqpDocDetailForSupplierView"
 *  url="view/es/inquiryprice/inqpdoc/InqpDocDetailForSupplier.jsp"    
 *  
 *  
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={DosBuyRecord.class})
@RequestMapping("/SingleSourceDocController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class SingleSourceDocController extends AnnotationMultiController<DosBuyRecord>{
	
	
}
