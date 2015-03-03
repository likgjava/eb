package com.gpcsoft.pubservice.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.Evaluate;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsEvaluate;
import com.gpcsoft.serve.hotel.domain.HotelEvaluate;
import com.gpcsoft.smallscale.pointmall.domain.GiftComment;

/** 
  *  Comments: <strong>ShowEvaluateController</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-12-24 下午04:58:29 by yucy    					                            
  *  <br/>Modified Date:  2010-12-24 下午04:58:29 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 

@Controller//标识为控制器
@Scope("request")
@RequestMapping("/ShowEvaluateController.do")//页面请求路径,可修改
public class ShowEvaluateController extends AnnotationMultiController<GpcBaseObject> {
	
    /** 
     * Description :  商品评价列表
     * Create Date: 2010-12-24下午05:03:34 by yucy  Modified Date: 2010-12-24下午05:03:34 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(params = "method=toGoodsEvaluateList")
    public ModelAndView toGoodsEvaluateList(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        String queryColumns = makeQueryColumns(request);
        QueryObject query = QueryWebUtils.getQuery(request, GoodsEvaluate.class);
        initQueryColums(request, query);
        Page page = prePage(request);
        query = onFind(query, request);
        Page pageData  = baseGenericService.findByQuery(query, true, page.getStart(), page.getPageSize());
        String alias = request.getParameter("alias");
        pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias != null ? alias.split(",") : null,GoodsEvaluate.class, new Map[] {
            getEnumColumns()
        }));
        endPage(model, pageData, request);
    	return new ModelAndView(Constants.JSON_VIEW,model);
    }
    
    /** 
     * Description :  供应商,代理机构评价列表
     * Create Date: 2010-12-24下午05:03:34 by yucy  Modified Date: 2010-12-24下午05:03:34 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(params = "method=toOrgEvaluateList")
    public ModelAndView toOrgEvaluateList(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        String queryColumns = makeQueryColumns(request);
        QueryObject query = QueryWebUtils.getQuery(request, Evaluate.class);
        initQueryColums(request, query);
        Page page = prePage(request);
        query = onFind(query, request);
        Page pageData  = baseGenericService.findByQuery(query, true, page.getStart(), page.getPageSize());
        String alias = request.getParameter("alias");
        pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias != null ? alias.split(",") : null, Evaluate.class, new Map[] {
            getEnumColumns()
        }));
        endPage(model, pageData, request);
    	return new ModelAndView(Constants.JSON_VIEW,model);
    }
    
    /** 
     * Description :  酒店评价列表
     * Create Date: 2010-12-24下午05:03:34 by yucy  Modified Date: 2010-12-24下午05:03:34 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(params = "method=toHotelEvaluateList")
    public ModelAndView toHotelEvaluateList(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        String queryColumns = makeQueryColumns(request);
        QueryObject query = QueryWebUtils.getQuery(request, HotelEvaluate.class);
        initQueryColums(request, query);
        Page page = prePage(request);
        query = onFind(query, request);
        Page pageData  = baseGenericService.findByQuery(query, true, page.getStart(), page.getPageSize());
        String alias = request.getParameter("alias");
        pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias != null ? alias.split(",") : null, Evaluate.class, new Map[] {
            getEnumColumns()
        }));
        endPage(model, pageData, request);
    	return new ModelAndView(Constants.JSON_VIEW,model);
    }
    
    
    /**
     * Description :  礼品评价列表
     * Create Date: 2011-1-17上午09:35:41 by zhaojf  Modified Date: 2011-1-17上午09:35:41 by zhaojf
     * @param   
     * @return  
     * @Exception
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(params = "method=toGiftCommentList")
    public ModelAndView toGiftCommentList(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        String queryColumns = makeQueryColumns(request);
        QueryObject query = QueryWebUtils.getQuery(request, GiftComment.class);
        initQueryColums(request, query);
        Page page = prePage(request);
        query = onFind(query, request);
        Page pageData  = baseGenericService.findByQuery(query, true, page.getStart(), page.getPageSize());
        String alias = request.getParameter("alias");
        pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias != null ? alias.split(",") : null,GiftComment.class, new Map[] {
            getEnumColumns()
        }));
        endPage(model, pageData, request);
    	return new ModelAndView(Constants.JSON_VIEW,model);
    }
}
