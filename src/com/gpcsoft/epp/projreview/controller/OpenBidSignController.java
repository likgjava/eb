package com.gpcsoft.epp.projreview.controller;

import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import com.gpcsoft.core.Constants;
import com.gpcsoft.epp.projreview.service.OpenBidSignService;
import com.gpcsoft.epp.projreview.domain.OpenBidSign;

/**
  * @gpcsoft.view value="openBidSignFormView"
  *  url="view/es/planform/projreview/openBidSignForm.jsp"
  * @gpcsoft.view value="openBidSignTreeFormView"
  *  url="view/es/planform/projreview/openBidSignTreeForm.jsp"
  * @gpcsoft.view value="openBidSignListView"
  *  url="view/es/planform/projreview/openBidSignList.jsp"
  * @gpcsoft.view value="openBidSignDetailView"
  *  url="view/es/planform/projreview/openBidSignDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OpenBidSign.class})
@RequestMapping("/OpenBidSignController.do")//页面请求路径,可修改
public class OpenBidSignController extends AnnotationMultiController<OpenBidSign> {

	@Autowired(required=true) @Qualifier("openBidSignServiceImpl")
	private OpenBidSignService openBidSignService;

}
