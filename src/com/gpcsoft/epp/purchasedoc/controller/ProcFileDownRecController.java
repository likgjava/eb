package com.gpcsoft.epp.purchasedoc.controller;

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
import com.gpcsoft.epp.purchasedoc.service.ProcFileDownRecService;
import com.gpcsoft.epp.purchasedoc.domain.ProcFileDownRec;

/**
  * @gpcsoft.view value="procFileDownRecFormView"
  *  url="view/es/planform/purchasedoc/procFileDownRecForm.jsp"
  * @gpcsoft.view value="procFileDownRecTreeFormView"
  *  url="view/es/planform/purchasedoc/procFileDownRecTreeForm.jsp"
  * @gpcsoft.view value="procFileDownRecListView"
  *  url="view/es/planform/purchasedoc/procFileDownRecList.jsp"
  * @gpcsoft.view value="procFileDownRecDetailView"
  *  url="view/es/planform/purchasedoc/procFileDownRecDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProcFileDownRec.class})
@RequestMapping("/ProcFileDownRecController.do")//页面请求路径,可修改
public class ProcFileDownRecController extends AnnotationMultiController<ProcFileDownRec> {

	@Autowired(required=true) @Qualifier("procFileDownRecServiceImpl")
	private ProcFileDownRecService procFileDownRecService;

}
