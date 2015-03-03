package com.gpcsoft.smallscale.pay.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.order.enumeration.OrderEnum;
import com.gpcsoft.agreement.order.manager.OrderManager;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.purchasedoc.service.DosBuyRecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.pubservice.utils.PayMD5;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyerService;
import com.gpcsoft.smallscale.pay.domain.PayRecord;
import com.gpcsoft.smallscale.pay.domain.PayRecordEnum;
import com.gpcsoft.smallscale.pay.service.PayRecordService;
import com.gpcsoft.smallscale.pay.service.PayService;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.service.EmployeeService;

/**
  *  @gpcsoft.view value="toOrderPayResultView"
  *  url="view/srplatform/chinabank/order_pay_result.jsp"
  *  
  *  @gpcsoft.view value="groupBuyingPayResult"
  *  url="view/smallscale/groupbuying/group_buying_pay_result.jsp"
  *  
  *  @gpcsoft.view value="toPrePayView"
  *  url="view/srplatform/chinabank/pre_pay.jsp"
  *  
  *  @gpcsoft.view value="toPayRecordDetail"
  *  url="view/srplatform/chinabank/pay_record_detail.jsp"
  *  
  *  @gpcsoft.view value="docPayResultView"
  *  url="view/srplatform/chinabank/doc_pay_result.jsp"
  *  
  *  @gpcsoft.view value="bailPayResultView"
  *  url="view/srplatform/chinabank/bail_pay_result.jsp"
  *  
  *  @gpcsoft.view value="servicePayResultView"
  *  url="view/srplatform/chinabank/service_pay_result.jsp"
  */
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/PayController.pay")//页面请求路径,可修改
public class PayController extends AnnotationMultiController {
	
	//商户用户名
	private static String V_MID = "v_mid";
	
	//数字签名
	private static String V_KEY = "v_key";
	
	//成功的逻辑
	private static String V_URL = "v_url";
	
	//金额类型
	private static String V_MONEYTYPE = "v_moneytype";

	@Autowired(required=true) @Qualifier("payServiceImpl")
	private PayService payService;
	
	@Autowired(required=true) @Qualifier("orderManagerImpl")
	private OrderManager orderManager;
	
	@Autowired(required=true) @Qualifier("groupBuyerServiceImpl")
	private GroupBuyerService groupBuyerService;
	
	@Autowired(required=true) @Qualifier("payRecordServiceImpl")
	private PayRecordService payRecordService;
	
	@Autowired(required=true) @Qualifier("bailRecordServiceImpl")
	private BailRecordService bailRecordService;
	
	@Autowired(required=true) @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	@Autowired(required=true) @Qualifier("employeeServiceImpl")
	private EmployeeService employeeService;
	
	@Autowired(required=true) @Qualifier("dosBuyRecordServiceImpl")
	private DosBuyRecordService dosBuyRecordService;

    /** 
     * Description :  订单支付完成后调用
     * Create Date: 2011-6-16上午01:17:31 by yucy  Modified Date: 2011-6-16上午01:17:31 by yucy
     * @param   
     * @return  
     * @Exception   
     */
	@RequestMapping(params = "method=orderPayResult")
    public ModelAndView orderPayResult(HttpServletRequest request) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		//如果支付成功,调用业务方法
		if(getPayStatus(request,PayRecordEnum.OD)) {
			
			model.put("payFlag", orderManager.updatePayStatus(request.getParameter("remark2"),OrderEnum.HAS_PAY)); 
			
		} else {
			
			model.put("payFlag", false);
			
		}
		
		return new ModelAndView("toOrderPayResultView",model);
    }
	
	/** 
	 * Description :  团购支付完成后调用
	 * Create Date: 2011-6-24上午10:04:46 by likg  Modified Date: 2011-6-24上午10:04:46 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGroupBuyingPayResult")
    public ModelAndView toGroupBuyingPayResult(HttpServletRequest request) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		//如果支付成功,调用业务方法
		if(getPayStatus(request,PayRecordEnum.TG)) {
			
			model.put("payFlag", groupBuyerService.updatePayStatus(request.getParameter("remark2"), OrderEnum.HAS_PAY)); 
			
		} else {
			
			model.put("payFlag", false);
			
		}
		
		return new ModelAndView("groupBuyingPayResult", model);
    }
	
	/** 
	 * Description :  采购文件支付完成后调用
	 * Create Date: 2011-7-21上午11:33:52 by sunl  Modified Date: 2011-7-21上午11:33:52 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=docPayResult")
    public ModelAndView docPayResult(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//如果支付成功,调用业务方法
		PayRecord payRecord = new PayRecord();
		if(getPayStatus(request,PayRecordEnum.DF,payRecord)) {
			//获取支付人的id
			String empId = payRecord.getPayEmpId();
			
			//更新招标文件的支付状态
			String projId = request.getParameter("remark2");
			
			model.put("payFlag", dosBuyRecordService.updatePayStatus(projId,empId)); 
		} else {
			model.put("payFlag", false);
		}
		
		return new ModelAndView("docPayResultView",model);
    }
	
	/** 
	 * Description :  保证金支付完成后调用
	 * Create Date: 2011-7-21上午11:33:52 by sunl  Modified Date: 2011-7-21上午11:33:52 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=bailPayResult")
    public ModelAndView bailPayResult(HttpServletRequest request) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		//如果支付成功,调用业务方法
		PayRecord payRecord = new PayRecord();
		if(getPayStatus(request,PayRecordEnum.BF,payRecord)) {
			
			//获取支付人的id
			//String empId = request.getParameter("remark1");
			String empId = payRecord.getPayEmpId();
			
			//获取项目ID
			String projId = request.getParameter("remark2");
			
			//保存保证金记录
			bailRecordService.saveBailRecordAfterPay(projId,empId,OrderEnum.HAS_PAY);
			
			model.put("payFlag", true); 
			
		} else {
			
			model.put("payFlag", false);
			
		}
		
		return new ModelAndView("bailPayResultView",model);
    }
	
	/** 
	 * Description :  服务订阅支付完成后调用
	 * Create Date: 2011-7-21上午11:33:52 by sunl  Modified Date: 2011-7-21上午11:33:52 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=servicePayResult")
    public ModelAndView servicePayResult(HttpServletRequest request) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		//如果支付成功,调用业务方法
		if(getPayStatus(request,PayRecordEnum.SF)) {
			
			//支付业务ID
			String serviceOrderId = request.getParameter("remark2");
			
			//支付金额
			String payAmount= request.getParameter("v_amount");
			
			//服务订阅成功，更新支付金额和支付状态
			model.put("payFlag", serviceSubscribeService.updateServicePayStatus(serviceOrderId, BigDecimal.valueOf(Double.parseDouble(payAmount)), OrderEnum.HAS_PAY)); 
			
		} else {
			
			model.put("payFlag", false);
			
		}
		
		return new ModelAndView("servicePayResultView",model);
    }
	
	/** 
	 * Description :  跳转到预支付页面（含支付跳转链接、支付参数）
	 * Create Date: 2011-7-20下午07:55:26 by sunl  Modified Date: 2011-7-20下午07:55:26 by sunl
	 * @param   remark1:支付人empId_发票抬头_发票名目_邮寄地址(可继续添加，以下划线分割)
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPrePayView")
    public ModelAndView toPrePayView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		PayMD5 md5 = new PayMD5();
		String v_back_req_method = request.getParameter("v_back_req_method");//支付返回调用的方法
		
		//必填参数
		String v_oid = payService.savePayNo();//支付号
		String v_mid = payService.getPayProperties(V_MID);//商户用户名称
		String remark1 = request.getParameter("remark1");//支付人empId_发票抬头_发票名目_邮寄地址(可继续添加，以下划线分割)
		String remark2 = request.getParameter("v_business_id");//支付业务ID
		String v_moneytype = payService.getPayProperties(V_MONEYTYPE);//商户密钥
		String v_url = payService.getPayProperties(V_URL);
		v_url = v_url + v_back_req_method;//商户返回逻辑
		
		String key = payService.getPayProperties(V_KEY);//商户金额类型
		String v_amount = request.getParameter("v_amount");//支付金额
		model.put("v_oid", v_oid);//支付号-生成的序列号
		model.put("v_mid", v_mid);
		model.put("v_moneytype", v_moneytype);
		model.put("v_key", key);
		model.put("v_url", v_url);
		model.put("remark1", com.gpcsoft.bizplatform.base.common.util.StringUtils.ascii2Native(remark1));//支付人empId_发票抬头_发票名目_邮寄地址(可继续添加，以下划线分割)
		model.put("remark2", remark2);//业务id
		model.put("v_amount", v_amount);

		//生成MD5码,拼凑加密串  网银支付平台对MD5值只认大写字符串，所以小写的MD5值得转换为大写7CA9E7671423154049DD10EE181CA250
		model.put("v_md5info", md5.getMD5ofStr(v_amount+v_moneytype+v_oid+v_mid+v_url+key));
		
		return new ModelAndView("toPrePayView", model);
    }
	
	/** 
	 * Description :  返回支付状态，同时保持支付记录
	 * Create Date: 2011-7-20下午04:09:46 by sunl  Modified Date: 2011-7-20下午04:09:46 by sunl
	 * @param   业务类型
	 * @return  
	 * @Exception   
	 */
	public boolean getPayStatus(HttpServletRequest request,String businessType,PayRecord payRecord) throws Exception {
		
		//request.setCharacterEncoding("gbk");//转码关键！！！！！！！！！！！！！！！！！！
		
		boolean payFlag = false;
		
		/*支付成败验证逻辑*/
		PayMD5 md5 = new PayMD5();
		String key = payService.getPayProperties(V_KEY); //商户金额类型
		String v_oid = request.getParameter("v_oid"); //支付号
		//String v_remark_1 = !StringUtils.hasLength(request.getParameter("remark1") )?"匿名":((Employee)(employeeService.get(request.getParameter("remark1").toString()))).getName(); //支付人姓名
		String v_remark_2 = request.getParameter("remark2"); //业务ID
		//String v_emp_id =!StringUtils.hasLength(request.getParameter("remark1") )?"":request.getParameter("remark1").toString();
		String v_pstatus = request.getParameter("v_pstatus"); //支付结果，20支付完成；30支付失败；
		String v_amount = request.getParameter("v_amount"); //订单实际支付金额
		String v_moneytype = request.getParameter("v_moneytype"); //币种
		String v_md5str = request.getParameter("v_md5str"); //MD5校验码
		String v_pmode = new String(request.getParameter("v_pmode")); //支付银行
		
		//支付人empId_发票抬头_发票名目_邮寄地址(可继续添加，以下划线分割)
		String remark1 = request.getParameter("remark1");
		String payEmpId = null;
		String payPersonName = "匿名";
		String invoiceTitle = null; //发票抬头
		String invoiceItems = null; //发票名目
		String mailingAddress = null; //邮寄地址
		if(StringUtils.hasLength(remark1)) {
			int split_index_p = 0; //前一分割线下标
			int split_index_n = remark1.indexOf("_"); //后一分割线下标
			payEmpId = remark1.substring(split_index_p, split_index_n);
			if(!StringUtils.hasLength(payEmpId) && AuthenticationHelper.getCurrentUser()!=null) {
				payEmpId = AuthenticationHelper.getCurrentUser(true).getEmp().getObjId();
			}
			payPersonName = ((Employee)(employeeService.get(payEmpId))).getName();
			
			split_index_p = split_index_n;
			split_index_n = remark1.indexOf("_", split_index_n+1);
			invoiceTitle = remark1.substring(split_index_p+1, split_index_n);
			
			split_index_p = split_index_n;
			split_index_n = remark1.indexOf("_", split_index_n+1);
			invoiceItems = remark1.substring(split_index_p+1, split_index_n);
			
			split_index_p = split_index_n;
			split_index_n = remark1.indexOf("_", split_index_n+1);
			mailingAddress = remark1.substring(split_index_p+1);
			
//			String[] remarks = remark1.split("_");
//			for(int i=0; i<remarks.length; i++) {
//				if(i == 0) {
//					payEmpId = remarks[i];
//					if(!StringUtils.hasLength(payEmpId) && AuthenticationHelper.getCurrentUser()!=null) {
//						payEmpId = AuthenticationHelper.getCurrentUser(true).getEmp().getObjId();
//					}
//					payPersonName = ((Employee)(employeeService.get(payEmpId))).getName();
//				} else if(i == 1) {
//					invoiceTitle = remarks[i];
//				} else if(i == 2) {
//					invoiceItems = remarks[i];
//				} else if(i == 3) {
//					mailingAddress = remarks[i];
//				}
//			}
		}
		
		String text = v_oid+v_pstatus+v_amount+v_moneytype+key;
		
		String v_md5 = md5.getMD5ofStr(text).toUpperCase();
		if(v_md5str.equals(v_md5)) {
			if ("20".equals(v_pstatus)) {
				payFlag = true;
			}
		}
		
		/*保持支付记录*/
		if(payRecord == null) {
			payRecord = new PayRecord();
		}
		//PayRecord payRecord = new PayRecord();
		payRecord.setPayNo(v_oid);//支付号-序列号
		payRecord.setPayAmount(BigDecimal.valueOf(Double.parseDouble(v_amount)));//支付金额
		payRecord.setPayPersonName(payPersonName);//支付人姓名
		payRecord.setPayEmpId(payEmpId);//支付人id
		payRecord.setPayBusinessId(v_remark_2);//支付业务ID
		payRecord.setPayTime(new Date());//支付时间
		payRecord.setPayStatus(v_pstatus);//支付状态
		payRecord.setPayBusinessType(businessType);//支付业务类型
		payRecord.setPayMode(v_pmode);//支付方式如工商银行
		payRecord.setInvoiceTitle(invoiceTitle);//发票抬头
		payRecord.setInvoiceItems(invoiceItems);//发票名目
		payRecord.setMailingAddress(mailingAddress);//邮寄地址
		
		
		payRecordService.save(payRecord);
		
		return payFlag;
	}
	
	/** 
	 * Description :  重载getPayStatus方法，该方法不需要从PayRecord中取数据
	 * Create Date: 2011-11-9下午03:50:34 by likg  Modified Date: 2011-11-9下午03:50:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private boolean getPayStatus(HttpServletRequest request,String businessType) throws Exception {
		return this.getPayStatus(request, businessType, null);
	}
	
	/** 
	 * Description :  跳转到查看采购文件支付记录页面，显示支付记录，接收支付业务ID参数
	 * Create Date: 2011-7-22上午11:54:19 by sunl  Modified Date: 2011-7-22上午11:54:19 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toDocPayRecordDetail")
    public ModelAndView toDocPayRecordDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		//支付业务ID
		String pay_business_id = request.getParameter("pay_business_id");
		String userType = request.getParameter("userType"); //用户类型
		
		//获得支付记录
		QueryObject<PayRecord> queryObject = new QueryObjectBase<PayRecord>();
		queryObject.setEntityClass(PayRecord.class);
		queryObject.getQueryParam().and(new QueryParam("payBusinessId",QueryParam.OPERATOR_EQ,pay_business_id));
		if(!StringUtils.hasLength(userType)) {
			queryObject.getQueryParam().and(new QueryParam("payEmpId",QueryParam.OPERATOR_EQ,AuthenticationHelper.getCurrentUser(true).getEmp().getObjId()));
		}
		
		//采购文件支付业务
		queryObject.getQueryParam().and(new QueryParam("payBusinessType",QueryParam.OPERATOR_EQ,PayRecordEnum.DF));
		
		List<PayRecord> payRecordList = payRecordService.findByQuery(queryObject);
		
		model.put("payRecordList", payRecordList);
		
		return new ModelAndView("toPayRecordDetail", model);
    }
	
	/** 
	 * Description :  跳转到查看保证金文件支付记录页面，显示支付记录，接收支付业务ID参数
	 * Create Date: 2011-7-22上午11:54:19 by sunl  Modified Date: 2011-7-22上午11:54:19 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBailPayRecordDetail")
    public ModelAndView toBailPayRecordDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		//支付业务ID
		String pay_business_id = request.getParameter("pay_business_id");
		
		//获得支付记录
		QueryObject<PayRecord> queryObject = new QueryObjectBase<PayRecord>();
		queryObject.setEntityClass(PayRecord.class);
		queryObject.getQueryParam().and(new QueryParam("payBusinessId",QueryParam.OPERATOR_EQ,pay_business_id));
		
		
		//保证金支付业务
		queryObject.getQueryParam().and(new QueryParam("payBusinessType",QueryParam.OPERATOR_EQ,PayRecordEnum.BF));
		
		List<PayRecord> payRecordList = payRecordService.findByQuery(queryObject);
		
		model.put("payRecordList", payRecordList);
		
		return new ModelAndView("toPayRecordDetail", model);
    }
	
	/** 
	 * Description :  跳转到查看服务费支付记录页面，显示支付记录，接收支付业务ID参数
	 * Create Date: 2011-7-22上午11:54:19 by sunl  Modified Date: 2011-7-22上午11:54:19 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toServicePayRecordDetail")
    public ModelAndView toServicePayRecordDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		//支付业务ID
		String pay_business_id = request.getParameter("pay_business_id");
		
		//获得支付记录
		QueryObject<PayRecord> queryObject = new QueryObjectBase<PayRecord>();
		queryObject.setEntityClass(PayRecord.class);
		queryObject.getQueryParam().and(new QueryParam("payBusinessId",QueryParam.OPERATOR_EQ,pay_business_id));
		
		//服务订阅支付业务
		queryObject.getQueryParam().and(new QueryParam("payBusinessType",QueryParam.OPERATOR_EQ,PayRecordEnum.SF));
		
		List<PayRecord> payRecordList = payRecordService.findByQuery(queryObject);
		
		model.put("payRecordList", payRecordList);
		
		return new ModelAndView("toPayRecordDetail", model);
    }
	
	/** 
	 * Description :  跳转到查看订单支付记录页面，显示支付记录，接收支付业务ID参数
	 * Create Date: 2011-7-22上午11:54:19 by sunl  Modified Date: 2011-7-22上午11:54:19 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrderPayRecordDetail")
    public ModelAndView toOrderPayRecordDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		//支付业务ID
		String pay_business_id = request.getParameter("pay_business_id");
		
		//获得支付记录
		QueryObject<PayRecord> queryObject = new QueryObjectBase<PayRecord>();
		queryObject.setEntityClass(PayRecord.class);
		queryObject.getQueryParam().and(new QueryParam("payBusinessId",QueryParam.OPERATOR_EQ,pay_business_id));
		
		//订单支付业务
		queryObject.getQueryParam().and(new QueryParam("payBusinessType",QueryParam.OPERATOR_EQ,PayRecordEnum.OD));
		
		List<PayRecord> payRecordList = payRecordService.findByQuery(queryObject);
		
		model.put("payRecordList", payRecordList);
		
		return new ModelAndView("toPayRecordDetail", model);
    }
	
	/** 
	 * Description :  跳转到查看团购支付记录页面，显示支付记录，接收支付业务ID参数
	 * Create Date: 2011-7-22上午11:54:19 by sunl  Modified Date: 2011-7-22上午11:54:19 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGroupPayRecordDetail")
    public ModelAndView toGroupPayRecordDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		//支付业务ID
		String pay_business_id = request.getParameter("pay_business_id");
		
		//获得支付记录
		QueryObject<PayRecord> queryObject = new QueryObjectBase<PayRecord>();
		queryObject.setEntityClass(PayRecord.class);
		queryObject.getQueryParam().and(new QueryParam("payBusinessId",QueryParam.OPERATOR_EQ,pay_business_id));
		
		//团购支付业务
		queryObject.getQueryParam().and(new QueryParam("payBusinessType",QueryParam.OPERATOR_EQ,PayRecordEnum.TG));
		
		List<PayRecord> payRecordList = payRecordService.findByQuery(queryObject);
		
		model.put("payRecordList", payRecordList);
		
		return new ModelAndView("toPayRecordDetail", model);
    }
}
