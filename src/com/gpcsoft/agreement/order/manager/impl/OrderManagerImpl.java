package com.gpcsoft.agreement.order.manager.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordDetailDao;
import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordItemDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordItem;
import com.gpcsoft.agreement.bargin.project.dao.RequireTaskItemDao;
import com.gpcsoft.agreement.bargin.require.domain.RequireGoodsAccessories;
import com.gpcsoft.agreement.bargin.require.domain.RequireGoodsGift;
import com.gpcsoft.agreement.bargin.require.domain.RequireGoodsOpt;
import com.gpcsoft.agreement.cart.dao.ShoppingCartDao;
import com.gpcsoft.agreement.cart.dao.ShoppingCartItemDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsAccessories;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsGift;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsOption;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.agreement.order.dao.OrderDao;
import com.gpcsoft.agreement.order.dao.OrderProtaskDao;
import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.OrderGoodsAccessories;
import com.gpcsoft.agreement.order.domain.OrderGoodsGift;
import com.gpcsoft.agreement.order.domain.OrderGoodsOption;
import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.agreement.order.domain.OrderProtask;
import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.agreement.order.enumeration.OrderEnum;
import com.gpcsoft.agreement.order.manager.OrderManager;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.exception.CustomerException;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.pubservice.utils.MobileMessageUtil;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.UserManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Repository
public class OrderManagerImpl extends BaseManagerImpl<Order> implements OrderManager {

	@Autowired(required=true)  @Qualifier("orderDaoHibernate")
	private OrderDao orderDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("orderProtaskDaoHibernate")
	private OrderProtaskDao orderProtaskDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("shoppingCartItemDaoHibernate")
	private ShoppingCartItemDao shoppingCartItemDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("shoppingCartDaoHibernate")
	private ShoppingCartDao shoppingCartDaoHibernate;
	
	@Autowired(required=false) @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDaoHibernate;
	
	@Autowired(required=false) @Qualifier("requireTaskItemDaoHibernate")
	private RequireTaskItemDao requireTaskItemDao;
	
	@Autowired(required=false) @Qualifier("biddingRecordDetailDaoHibernate")
	private BiddingRecordDetailDao biddingRecordDetailDao;
	
	@Autowired(required=false) @Qualifier("biddingRecordItemDaoHibernate")
	private BiddingRecordItemDao biddingRecordItemDaoHibernate;
	
	@Autowired(required=false) @Qualifier("userManagerImpl")
	private UserManager userManagerImpl;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.order.service.OrderService#listOrderByTask(com.gpcsoft.core.utils.Page, javax.servlet.http.HttpServletRequest)
	 */
	public Page<Order> listOrderByTask(Page<Order> page,HttpServletRequest request){
		String protaskId = "";
		if(null!=request.getParameter("protaskId")){
			protaskId = request.getParameter("protaskId").toString();
		}
		return orderDaoHibernate.listOrderByTask(page,protaskId);
	}


	public Page<Order> listOrderByTaskItem(Page<Order> page, HttpServletRequest request) {
		String protaskItemId = "";
		if(null!=request.getParameter("protaskItemId")){
			protaskItemId = request.getParameter("protaskItemId").toString();
		}
		return  orderDaoHibernate.listOrderByTaskItem(page,protaskItemId);
	}
	
	/**
	 * 获得订单商品配件信息
	 */
	public List<OrderItem> getOrderGoodsOption(Map<String, Object> paramsMap) {
		return orderDaoHibernate.getOrderGoodsOption(paramsMap);
	}

	/**
	 * 将合同的id更新到订单里面 
	 */
	public void saveOrderContract(Map<String, Object> paramsMap) {
		Object orderIds = paramsMap.get("orderIds");
		Object contractId = paramsMap.get("contractId");
		if(orderIds != null && contractId != null){
			orderDaoHibernate.saveOrderContract(orderIds.toString(), contractId.toString());
		}
	}

	/**
	 * 保存/提交订单
	 */
	public Order saveOrder(Order baseobject,String methodName) throws Exception {
		String sure = getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.order.domain.Order.confirmStatus", "SURE");//同意
		String back = getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.order.domain.Order.confirmStatus", "BACK");//退回
		String wait = getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.order.domain.Order.confirmStatus","WAIT");//待提交
		String confirm = getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.order.domain.Order.useStatus","CONFIRM");//已确认
		
		baseobject.setOrderItems(null);
		
		Order order = (Order)this.get(baseobject.getObjId(),Order.class);
		
		//供应商机构管理员
		User supplierUser = userManagerImpl.getManagerUser(order.getSupplier().getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		
		//采购人机构管理员
		User buyerUser = userManagerImpl.getManagerUser(order.getBuyer().getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());

		//采购人提交
		if("submit".equals(methodName)){
			baseobject.setBuyerConfirmDate(new Date());
			baseobject.setBuyerConfirmUser(AuthenticationHelper.getCurrentUser(true));
			baseobject.setBuyerConfirmStatus(sure);
			baseobject.setSupplierConfirmStatus(wait);
			
			//获取采购人提交订单时，短信、邮件、站内信提醒的标题
			String title = messageSource.getMessage(CustomerMessage.BUYER_SUBMINT_ORDER_REMIND_TITLE);
			
			//获取采购人提交订单时，短信、邮件、站内信提醒的内容
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("buyerOrgName", order.getBuyer().getOrgName());
			templateMap.put("siteName", SysInfo.getInstance().getSitename());
			String content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.BUYER_SUBMINT_ORDER_REMIND_CONTENT), templateMap);
			
			//发送邮件，站内信，短信
			sendMessage(buyerUser, supplierUser, title, content);
		}
		//采购人撤销(撤销提交)
		else if("revocate".equals(methodName)){
			if(sure.equals(orderDaoHibernate.get(baseobject.getObjId()).getSupplierConfirmStatus())){
				throw new CustomerException("供应商已经确认！此操作已失效");
			}
			baseobject.setBuyerConfirmDate(null);
			baseobject.setBuyerConfirmUser(null);
			baseobject.setBuyerConfirmStatus(wait);
		}
		//供应商确认
		else if("sure".equals(methodName)){
			baseobject.setSupplierConfirmDate(new Date());
			baseobject.setSupplierConfirmUser(AuthenticationHelper.getCurrentUser(true));
			baseobject.setSupplierConfirmStatus(sure);
			baseobject.setUseStatus(confirm);
			
			//供应商确认订单时，短信、邮件、站内信提醒的标题
			String title = messageSource.getMessage(CustomerMessage.SUPPLIER_CONFIRM_ORDER_REMIND_TITLE);
			
			//供应商确认订单时，短信、邮件、站内信提醒的内容
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("supplierOrgName", order.getSupplier().getOrgName());
			templateMap.put("siteName", SysInfo.getInstance().getSitename());
			String content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.SUPPLIER_CONFIRM_ORDER_REMIND_CONTENT), templateMap);
			
			//发送邮件，站内信，短信
			sendMessage(supplierUser, buyerUser, title, content);
		}
		//供应商退回
		else if("back".equals(methodName)){
			baseobject.setSupplierConfirmDate(new Date());
			baseobject.setSupplierConfirmUser(AuthenticationHelper.getCurrentUser(true));
			baseobject.setSupplierConfirmStatus(back);
			baseobject.setBuyerConfirmStatus(wait);
			
			//供应商申请调整订单订单时，短信、邮件、站内信提醒的标题
			String title = messageSource.getMessage(CustomerMessage.SUPPLIER_APPLY_ADJUST_ORDER_REMIND_TITLE);
			
			//供应商申请调整订单订单时，短信、邮件、站内信提醒的内容
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("supplierOrgName", order.getSupplier().getOrgName());
			templateMap.put("siteName", SysInfo.getInstance().getSitename());
			String content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.SUPPLIER_APPLY_ADJUST_ORDER_REMIND_CONTENT), templateMap);
			
			//发送邮件，站内信，短信
			sendMessage(supplierUser, buyerUser, title, content);
		}
		
		try{
			BeanUtils.copyPropertiesFilterEmptyNew(order,baseobject);
			return order;
		}catch(Exception ce){
			ce.printStackTrace();return null;
		}
	}
	
	/** 
	 * Description :  获取模板内容
	 * Create Date: 2011-7-27下午03:32:31 by likg  Modified Date: 2011-7-27下午03:32:31 by likg
	 * @param   ftlFileName:模板名称	templateMap:模板所需数据
	 * @return  
	 * @Exception   
	 */
	private String getFreeMarkerContent(String ftlFileName, Map<String, Object> templateMap) throws Exception {
		String content = "";
		try {
			content = freeMarkerService.getFreeMarkerContent(ftlFileName, templateMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/** 
	 * Description :  发送信息
	 * Create Date: 2011-7-22下午04:08:42 by yucy  Modified Date: 2011-7-22下午04:08:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private void sendMessage(User sendUser ,User receiveUser , String title , String content ) throws Exception {
		
		//发送邮件
		MailUtil.sendEmailNotAlways(new String[]{receiveUser.getEmp().getEmail()}, title , content ,null, null);
		//发站内信
		MessageUtil.sendMessagePrivete(new String[]{receiveUser.getObjId()},title, content,null, null, true);
		//发短信
		MobileMessageUtil.sendMobileMessage(receiveUser.getEmp().getMobile(),sendUser.getObjId(), sendUser.getUsername(), receiveUser.getObjId(), receiveUser.getUsername(), content, null, null);
		
	}

	/**
	 * 作废订单，同步删除订单与任务书的关系
	 */
	public void invalidOrder(String contractId) throws Exception{
		//作废订单
		orderDaoHibernate.invalidOrder(contractId);
		//删除订单与任务书的关系
		orderProtaskDaoHibernate.deleteProtaskByContractForInvalid(contractId);
	}

	/**
	 * Description :  创建订单
	 * Create Date: 2010-4-23上午11:15:22 by liangxj  Modified Date: 2010-4-23上午11:15:22 by yucy
	 * @param   carItemObjIds 购物车商品id ，有效的品目id
	 * @return  
	 * @Exception 不是同一供应商，选购品目不存在任务书中
	 */
	public Order createOrderByShoppingCart(String carItemObjIds,String purCategoryIds) throws Exception {
		/** 新建订单*/
		Order order = newOrder();
		
		/** 获得购物车商品信息 判断所选商品的品目是否存在任务书*/
		String paramIds="";
		for(String id:carItemObjIds.split(",")){
			if(paramIds.equals("")){
				paramIds += "'"+id+"'";
			}else{
				paramIds += ",'"+id+"'";
			}
		}
		List<ShoppingCartItem> cartItemList = shoppingCartItemDaoHibernate.findbyHql("from ShoppingCartItem where objId in ("+paramIds+")");
		OrgInfo supplier = null; int i=0;
		StringBuffer purCategoryNames = new StringBuffer("");
		
		//总数量和总金额
		BigDecimal qty = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		//购物车id
		String cartId = "";
		
		for (ShoppingCartItem shoppingCartItem : cartItemList) {
			if(i == 0){
				supplier = shoppingCartItem.getSupplier();
				cartId = shoppingCartItem.getShoppingCart().getObjId();
			}
			//采购品目
			if(purCategoryNames.indexOf(shoppingCartItem.getGoods().getPurCategory().getCategoryName()) == -1){
				purCategoryNames.append(shoppingCartItem.getGoods().getPurCategory().getCategoryName() + ",");
			}
			i++;
			
			/** 创建订单明细 */
			OrderItem orderItem = new OrderItem();
			BeanUtils.copyPropertiesFilterEmptyNew(orderItem,shoppingCartItem);
			orderItem.setObjId(null);
			orderItem.setOrderGoodsOptions(new HashSet<OrderGoodsOption>());
			
			//处理商品选配
			Set<ShoppingCartGoodsOption> option = shoppingCartItem.getCartGoodsOptions();
			for (ShoppingCartGoodsOption shoppingCartGoodsOption : option) {
				OrderGoodsOption goodsOption = new OrderGoodsOption();
				BeanUtils.copyPropertiesFilterEmptyNew(goodsOption,shoppingCartGoodsOption);
				goodsOption.setOptionalFitting(shoppingCartGoodsOption.getOption());//名称不同没有拷贝过来
				goodsOption.setObjId(null);
				goodsOption.setOrderItem(orderItem);
				orderItem.getOrderGoodsOptions().add(goodsOption);
			}
			//处理商品礼包
			Set<ShoppingCartGoodsGift> gifts = shoppingCartItem.getCartGoodsGifts();
			for (ShoppingCartGoodsGift shoppingCartGoodsGift : gifts) {
				OrderGoodsGift orderGoodsGift = new OrderGoodsGift();
				BeanUtils.copyPropertiesFilterEmptyNew(orderGoodsGift, shoppingCartGoodsGift);
				orderGoodsGift.setObjId(null);
				orderGoodsGift.setOrderItem(orderItem);
				orderItem.getOrderGoodsGifts().add(orderGoodsGift);
			}
			//处理商品零配件
			Set<ShoppingCartGoodsAccessories> accesses = shoppingCartItem.getCartGoodsAccessories();
			for (ShoppingCartGoodsAccessories shoppingCartGoodsAccess : accesses) {
				OrderGoodsAccessories orderGoodsAccess = new OrderGoodsAccessories();
				BeanUtils.copyPropertiesFilterEmptyNew(orderGoodsAccess, shoppingCartGoodsAccess);
				orderGoodsAccess.setObjId(null);
				orderGoodsAccess.setOrderItem(orderItem);
				orderItem.getOrderGoodsAccessories().add(orderGoodsAccess);
			}
			
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
			orderItem.setGoodsUnit(shoppingCartItem.getGoodsUnit());//计量单位
			qty = qty.add(orderItem.getGoodsQty());
			total = total.add(orderItem.getGoodsTotal());
			
		}
		order.setSupplier(supplier);
		order.setCategoryNames(purCategoryNames.substring(0,purCategoryNames.length()-1));
		order.setGoodsQty(qty);
		order.setGoodsTotal(total);
		
		//保存订单，级联保存订单子项，订单选配，以及任务书与订单的关系
		orderDaoHibernate.save(order);
		
		//删除购物车内的相关商品
		shoppingCartItemDaoHibernate.remove(carItemObjIds.split(","), ShoppingCartItem.class);
		shoppingCartDaoHibernate.updateSortChatAndItemQytAndMoney(cartId);
		
		return order;
	}

	/**
	 * 创建订单（议价项目），异常情况：不是同一供应商，选购品目不存在任务书中
	 */
	public Order createOrderByBidding(Map<String, Object> paramsMap) throws Exception {
		
		/** 新建订单*/
		Order order = this.newOrder();
			
		/** 获得购物车商品信息 判断所选商品的品目是否存在任务书*/
		Object[] ids = ((String)paramsMap.get("biddingRecordItemObjIds")).split(",");
		String items = "";
		for (int i = 0;i<ids.length ;i++) {
			if(i==0){
				items+="'"+ids[i]+"'";
			}else {
				items+=",'"+ids[i]+"'";
			}
		}
		//List<BiddingRecordItem> recordItemList = biddingRecordItemDaoHibernate.findbyHql("from BiddingRecordItem bri where bri.objId in ("+ items +") ");
		
		
		paramsMap.put("objIds", (String)paramsMap.get("biddingRecordItemObjIds"));
		List<BiddingRecordDetail> recordDetailList = biddingRecordDetailDao.getDealRecordDetail(paramsMap);
		
		if(recordDetailList.size()==0){
			return createOrderByBiddingTalkProject(paramsMap);//此处判断是老的报价记录方法 走老的方法 议价修改后删掉
		}
		
		StringBuffer purCategoryNames = new StringBuffer("");
		
		//总数量和总金额
		BigDecimal qty = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		for(int i =0;i<recordDetailList.size();i++ ) {
			BiddingRecordDetail biddingRecordDetail = recordDetailList.get(i);
			
			//采购品目//确定需求条目里一定会有品目
			purCategoryNames.append((purCategoryNames.length()!=0?",":"")+ biddingRecordDetail.getRequireItem().getPurCategory().getCategoryName());
			
			/** 创建订单明细 */
			OrderItem orderItem = new OrderItem();
			
			orderItem.setSupplier(biddingRecordDetail.getSupplier());
			orderItem.setGoodsUnit(biddingRecordDetail.getRequireItem().getGoodsUnit());
			orderItem.setGoodsQty(biddingRecordDetail.getRequireItem().getGoodsQty());
			orderItem.setMarketPrice(
					biddingRecordDetail.getGoods()!=null?biddingRecordDetail.getGoods().getReferPrice():biddingRecordDetail.getGoodsPrice()
					);//没有商品就把报价当成市场价//这样在后面算节约金额的时候就等于没有节约:没有市场价=没有节约
			orderItem.setAgreePrice(biddingRecordDetail.getGoodsPrice());
			BeanUtils.copyPropertiesFilterEmptyNew(orderItem,biddingRecordDetail);
			
			orderItem.setDesr(biddingRecordDetail.getServiceContent());
			
			orderItem.setObjId(null);
			
			//orderItem.setOrderGoodsOptions(new HashSet<OrderGoodsOption>());
			//处理商品选配
			Set<RequireGoodsOpt> opts = biddingRecordDetail.getRequireItem().getRequireGoodsOpt();
			for (RequireGoodsOpt requireGoodsOpt : opts) {
				OrderGoodsOption orderGoodsOption = new OrderGoodsOption();
				BeanUtils.copyPropertiesFilterEmptyNew(orderGoodsOption, requireGoodsOpt);
				orderGoodsOption.setObjId(null);
				orderGoodsOption.setOrderItem(orderItem);
				orderItem.getOrderGoodsOptions().add(orderGoodsOption);
			}
			//处理商品礼包
			Set<RequireGoodsGift> gifts = biddingRecordDetail.getRequireItem().getRequireGoodsGifts();
			for (RequireGoodsGift requireGoodsGift : gifts) {
				OrderGoodsGift orderGoodsGift = new OrderGoodsGift();
				BeanUtils.copyPropertiesFilterEmptyNew(orderGoodsGift, requireGoodsGift);
				orderGoodsGift.setObjId(null);
				orderGoodsGift.setOrderItem(orderItem);
				orderItem.getOrderGoodsGifts().add(orderGoodsGift);
			}
			
			//处理商品零配件
			Set<RequireGoodsAccessories> accesses = biddingRecordDetail.getRequireItem().getRequireGoodsAccess();
			for (RequireGoodsAccessories requireGoodsAccessories : accesses) {
				OrderGoodsAccessories orderGoodsAccess = new OrderGoodsAccessories();
				BeanUtils.copyPropertiesFilterEmptyNew(orderGoodsAccess, requireGoodsAccessories);
				orderGoodsAccess.setObjId(null);
				orderGoodsAccess.setOrderItem(orderItem);
				orderItem.getOrderGoodsAccessories().add(orderGoodsAccess);
			}
			
			orderItem.setRequireItem(biddingRecordDetail.getRequireItem());//需求条目
			
			//插入OrderProtask数据(订单和采购计划关联对象)
			if("XEJY".equals((String)paramsMap.get("appType"))&&biddingRecordDetail.getRequireItem()!=null&&StringUtils.hasLength(biddingRecordDetail.getRequireItem().getObjId())){
				ProtaskItem proTaskItem = requireTaskItemDao.getTaskItemByRequireItem(biddingRecordDetail.getRequireItem().getObjId());
				if(proTaskItem!=null){
					OrderProtask orderProtask = new OrderProtask();
					orderProtask.setOrderItem(orderItem);
					orderProtask.setProtaskItem(proTaskItem);
					orderProtask.setCreateTime(new Date());
					orderProtask.setOrderTaskQty(orderItem.getGoodsQty());
					orderProtask.setOrderTaskTotal(orderItem.getGoodsTotal());
					orderProtaskDaoHibernate.save(orderProtask);
				}
			}

			order.getOrderItems().add(orderItem);//条目加入订单
			
			qty = qty.add(orderItem.getGoodsQty());//数量累加
			total = total.add(orderItem.getGoodsTotal());//总额累加

		}
		order.setSupplier(recordDetailList.get(0).getSupplier());//供应商
		order.setCategoryNames(purCategoryNames.toString());//品目冗余串
		order.setGoodsQty(qty);//总数量
		order.setGoodsTotal(total);//总金额
		order.setProject(recordDetailList.get(0).getProject());
		
		//保存订单，级联保存订单子项，订单选配，以及任务书与订单的关系
		orderDaoHibernate.save(order);
		return order;
	}
	
	
	/**创建订单（议价项目老的报价记录用的方法议价改了之后就要删掉）*/
	public Order createOrderByBiddingTalkProject(Map<String, Object> paramsMap) throws Exception {
		
		/** 新建订单*/
		Order order = this.newOrder();
			
		/** 获得购物车商品信息 判断所选商品的品目是否存在任务书*/
		Object[] ids = ((String)paramsMap.get("biddingRecordItemObjIds")).split(",");
		String items = "";
		for (int i = 0;i<ids.length ;i++) {
			if(i==0){
				items+="'"+ids[i]+"'";
			}else {
				items+=",'"+ids[i]+"'";
			}
		}
		List<BiddingRecordItem> recordItemList = biddingRecordItemDaoHibernate.findbyHql("from BiddingRecordItem bri where bri.objId in ("+ items +") ");
		OrgInfo supplier = null; int i=0;
		StringBuffer purCategoryNames = new StringBuffer("");
		//总数量和总金额
		BigDecimal qty = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		for (BiddingRecordItem biddingRecordItem : recordItemList) {
			if(i == 0){
				supplier = biddingRecordItem.getBiddingRecord().getSupplier();
			}
			if(biddingRecordItem.getRequireItem().getGoods()!=null&&purCategoryNames.indexOf(biddingRecordItem.getRequireItem().getGoods().getPurCategory().getCategoryName()) == -1){
				purCategoryNames.append(biddingRecordItem.getRequireItem().getGoods().getPurCategory().getCategoryName() + ",");
			}else if(StringUtils.hasLength(biddingRecordItem.getRequireItem().getDescr())){
				purCategoryNames.append(biddingRecordItem.getRequireItem().getDescr() + ",");
			}
			i++;
			OrderItem orderItem = new OrderItem();
			orderItem.setSupplier(supplier);
			orderItem.setGoodsUnit(biddingRecordItem.getRequireItem().getGoodsUnit());
			orderItem.setGoodsQty(biddingRecordItem.getRequireItem().getGoodsQty());
			orderItem.setMarketPrice(biddingRecordItem.getGoods().getReferPrice());
			orderItem.setAgreePrice(biddingRecordItem.getGoodsPrice());
			BeanUtils.copyPropertiesFilterEmptyNew(orderItem,biddingRecordItem);
			orderItem.setObjId(null);
			Set<RequireGoodsOpt> opts = biddingRecordItem.getRequireItem().getRequireGoodsOpt();
			for (RequireGoodsOpt requireGoodsOpt : opts) {
				OrderGoodsOption orderGoodsOption = new OrderGoodsOption();
				BeanUtils.copyPropertiesFilterEmptyNew(orderGoodsOption, requireGoodsOpt);
				orderGoodsOption.setObjId(null);
				orderGoodsOption.setOrderItem(orderItem);
				orderItem.getOrderGoodsOptions().add(orderGoodsOption);
			}
			Set<RequireGoodsGift> gifts = biddingRecordItem.getRequireItem().getRequireGoodsGifts();
			for (RequireGoodsGift requireGoodsGift : gifts) {
				OrderGoodsGift orderGoodsGift = new OrderGoodsGift();
				BeanUtils.copyPropertiesFilterEmptyNew(orderGoodsGift, requireGoodsGift);
				orderGoodsGift.setObjId(null);
				orderGoodsGift.setOrderItem(orderItem);
				orderItem.getOrderGoodsGifts().add(orderGoodsGift);
			}
			Set<RequireGoodsAccessories> accesses = biddingRecordItem.getRequireItem().getRequireGoodsAccess();
			for (RequireGoodsAccessories requireGoodsAccessories : accesses) {
				OrderGoodsAccessories orderGoodsAccess = new OrderGoodsAccessories();
				BeanUtils.copyPropertiesFilterEmptyNew(orderGoodsAccess, requireGoodsAccessories);
				orderGoodsAccess.setObjId(null);
				orderGoodsAccess.setOrderItem(orderItem);
				orderItem.getOrderGoodsAccessories().add(orderGoodsAccess);
			}
			orderItem.setGoodsUnit(biddingRecordItem.getGoods().getMeasureUnit());
			orderItem.setRequireItem(biddingRecordItem.getRequireItem());
			if("XEJY".equals((String)paramsMap.get("appType"))&&biddingRecordItem.getRequireItem()!=null&&StringUtils.hasLength(biddingRecordItem.getRequireItem().getObjId())){
				ProtaskItem proTaskItem = requireTaskItemDao.getTaskItemByRequireItem(biddingRecordItem.getRequireItem().getObjId());
				if(proTaskItem!=null){
					OrderProtask orderProtask = new OrderProtask();
					orderProtask.setOrderItem(orderItem);
					orderProtask.setProtaskItem(proTaskItem);
					orderProtask.setCreateTime(new Date());
					orderProtask.setOrderTaskQty(orderItem.getGoodsQty());
					orderProtask.setOrderTaskTotal(orderItem.getGoodsTotal());
					orderProtaskDaoHibernate.save(orderProtask);
				}
			}
			order.getOrderItems().add(orderItem);
			qty = qty.add(orderItem.getGoodsQty());
			
			if(orderItem.getGoodsTotal()!=null){
				total = biddingRecordItem.getBiddingRecord().getGoodsTotal();
			}else{
				total = total.add(orderItem.getGoodsTotal());
			}
		}
		order.setSupplier(supplier);
		order.setCategoryNames(purCategoryNames.substring(0,purCategoryNames.length()-1));
		order.setGoodsQty(qty);
		order.setGoodsTotal(total);
		order.setProject(recordItemList.get(0).getBiddingRecord().getProject());
		orderDaoHibernate.save(order);
		return order;
	}
	
	//创建订单
	private Order newOrder() throws Exception{
		/** 创建订单信息 */
		User user = AuthenticationHelper.getCurrentUser(true);
		Order order = new Order();
		order.setPayStatus(OrderEnum.NO_PAY);//没有支付
		order.setOrderNo(SequenceNumberUtil.getOrderSN());
		order.setBuyer(orgInfoDaoHibernate.getLastedOrgInfo(user.getEmp().getCompany().getObjId(), true));
		order.setOrderItems(new HashSet<OrderItem>());
		//设置状态
		order.setUseStatus(getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.order.domain.Order.useStatus","DRAFT"));
		order.setBuyerConfirmStatus(getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.order.domain.Order.confirmStatus","WAIT"));
		order.setSupplierConfirmStatus(getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.order.domain.Order.confirmStatus","WAIT"));
		return order;
	}
	
	/** 
	 * Description :  更改订单支付状态
	 * Create Date: 2011-6-20下午02:34:39 by yucy  Modified Date: 2011-6-20下午02:34:39 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updatePayStatus(String id, String status) throws Exception {
		return orderDaoHibernate.updatePayStatus(id,status);
	}
}
