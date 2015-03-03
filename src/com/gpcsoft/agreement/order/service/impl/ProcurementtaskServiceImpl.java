package com.gpcsoft.agreement.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.order.dao.ProcurementtaskDao;
import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.Procurementtask;
import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.agreement.order.manager.ProcurementtaskManager;
import com.gpcsoft.agreement.order.service.ProcurementtaskService;
import com.gpcsoft.bizplatform.base.exception.CustomerException;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.manager.PurCategoryManager;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.common.utils.BeanUtil;
import com.gpcsoft.epp.common.utils.UploadFileResult;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.common.enumeration.CommonEnum;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;


@Service 
public class ProcurementtaskServiceImpl extends BaseGenericServiceImpl<Procurementtask> implements ProcurementtaskService {

	@Autowired(required=true) @Qualifier("procurementtaskManagerImpl")
	private ProcurementtaskManager procurementtaskManager;
	
	@Autowired(required=true) @Qualifier("procurementtaskDaoHibernate")
	private ProcurementtaskDao procurementtaskDao;
	
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	
	@Autowired(required=true) @Qualifier("goodsClassManagerImpl")
	private GoodsClassManager goodsClassManager;
	
	@Autowired(required=true) @Qualifier("purCategoryManagerImpl")
	private PurCategoryManager purCategoryManager;
	
	@SuppressWarnings("unchecked")
	public Page<Order> listOrderBySql(Page page ,HttpServletRequest request) {
		return procurementtaskManager.listOrderBySql(page, request);
	}

	/** 
	 * Description :  保存对象
	 * Create Date: 2010-11-9下午03:14:37 by yucy  Modified Date: 2010-11-9下午03:14:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Procurementtask createOrUpdatePlan(Map<String, Object> param) throws Exception {
		
		Procurementtask procurementtask = (Procurementtask)param.get("plan");
		
		procurementtask.setTaskNo(SequenceNumberUtil.getProcurementtaskSN());
		
		procurementtask.setBuyer((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		
		//使用状态
		procurementtask.setUseStatus("save".equals(param.get("saveType"))?CommonEnum.USER_STATUS_DRAEF:CommonEnum.USER_STATUS_CONFIRM);
		
		String itemStr = (String)param.get("itemStr");
		
		String categoryNames = "";
		
		Set<ProtaskItem> protaskItemSet = new HashSet<ProtaskItem>();
		
		if(procurementtask.getProtaskItems()!=null){
			protaskItemSet = procurementtask.getProtaskItems();
		}
		
		if(StringUtils.hasLength(itemStr)){
			for(String item : itemStr.split(",")){
				ProtaskItem protaskItem = new ProtaskItem();
				
				if(item.split(":").length==8){
					for(ProtaskItem Item: protaskItemSet){
						if(item.split(":")[7].equals(Item.getObjId())){
							protaskItem = Item;
						}
					}
				}
				
				protaskItem.setMemo(item.split(":")[0]);//备注
				
				PurCategory purCategory = new PurCategory();
				purCategory.setObjId(item.split(":")[1]);
				purCategory.setCategoryName(item.split(":")[2]);
				protaskItem.setPurCategory(purCategory);//品目
				if("".equals(categoryNames)){
					categoryNames += item.split(":")[2];
				}else{
					categoryNames += ","+item.split(":")[2];
				}
				
				protaskItem.setGoodsQty(new BigDecimal(item.split(":")[3]));//数量
				protaskItem.setGoodsPrice(new BigDecimal(item.split(":")[4]));//金额
				protaskItem.setGoodsTotal(new BigDecimal(item.split(":")[5]));//总金额
				
				GoodsClass goodsClass = new GoodsClass();//分类
				goodsClass.setObjId(item.split(":")[6]);
				protaskItem.setGoodsClass(goodsClass);
				
				protaskItem.setProcurementtask(procurementtask);//任务主单
				
				protaskItemSet.add(protaskItem);
			}
		}
		procurementtask.setCategoryNames(categoryNames);
		
		procurementtask.setProtaskItems(protaskItemSet);
		
		return procurementtaskDao.save(procurementtask);
	}

	/** 
	 * Description :  导入采购计划
	 * Create Date: 2011-12-9上午10:41:14 by yucy  Modified Date: 2011-12-9上午10:41:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean saveImportPlanXML(UploadFileResult result) throws Exception {
		try {
			if (result.isResult()) {
				Document document = (Document)result.getObject();
				Element element = document.getRootElement();
					List<Element> purchasePlanElements = element.elements("purchasePlan");
					if( purchasePlanElements==null|| purchasePlanElements.size()<=0 ) throw new CustomerException("请确认导入数据！");
					for (Element purchasePlanElement : purchasePlanElements) {
						//取出采购计划Procurementtask
						Procurementtask procurementtask = (Procurementtask)BeanUtil.getObjectByElement(purchasePlanElement);
						
						//编码
						procurementtask.setTaskNo(SequenceNumberUtil.getProcurementtaskSN());
						
						//如果机构是导入的（由管理员完成导入,但在模板中一定要注明orgCode）
						if(null!= procurementtask.getBuyer()&& StringUtils.hasLength( procurementtask.getBuyer().getOrgCode() )){
							OrgInfo buyer = orgInfoManager.getOrgInfoByOrgCode( procurementtask.getBuyer().getOrgCode() );
							if(buyer!=null){
								procurementtask.setBuyer(buyer);
							}else{
								throw new CustomerException("请确定系统中存在机构编码为"+procurementtask.getBuyer().getOrgCode()+"的相关机构");
							}
						}
						//无指定机构则指定当前导入人的机构
						else{
							procurementtask.setBuyer((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo());
						}
						//品目名称冗余
						String categoryNames = "";
						//取出采购计划明细planItem
						List<Element> planItemElements = purchasePlanElement.element("protaskItems").elements("planItem");
						for (Element planItemElement : planItemElements) {
							ProtaskItem protaskItem =  (ProtaskItem)BeanUtil.getObjectByElement(planItemElement);
							if(null!= protaskItem.getGoodsClass()&& StringUtils.hasLength( protaskItem.getGoodsClass().getGoodsClassCode() )){
								GoodsClass goodsClass = goodsClassManager.getGoodsClassByClassCode(protaskItem.getGoodsClass().getGoodsClassCode());
								if(null!=goodsClass){
									protaskItem.setGoodsClass(goodsClass);
								}else{
									throw new CustomerException("请确定系统中存在编码为"+protaskItem.getGoodsClass().getGoodsClassCode()+"的相关商品分类！");
								}
							}
							if(null!= protaskItem.getPurCategory()&& StringUtils.hasLength( protaskItem.getPurCategory().getCategoryCode() )){
								PurCategory purCategory = purCategoryManager.getPurCategoryByCategoryCode(protaskItem.getPurCategory().getCategoryCode());
								if(null!=purCategory){
									protaskItem.setPurCategory(purCategory);
									categoryNames += StringUtils.hasLength(categoryNames)?","+purCategory.getCategoryName():purCategory.getCategoryName();//品目名称冗余
								}else{
									throw new CustomerException("请确定系统中存在编码为"+protaskItem.getPurCategory().getCategoryCode()+"的相关品目！");
								}
							}
							protaskItem.setProcurementtask(procurementtask);
							procurementtask.getProtaskItems().add(protaskItem);
						}
						procurementtask.setCategoryNames(categoryNames);
						procurementtask.setCreateTime(new Date());
						procurementtask.setCreateUser(AuthenticationHelper.getCurrentUser(true));
						procurementtask.setUseStatus(CommonEnum.USER_STATUS_CONFIRM);
						save( procurementtask );
					}
			}else{
				throw new CustomerException("xml格式有误！");
			}
		}catch (Exception e){
			if(e instanceof CustomerException) {
				throw e;
			}else{
				throw new CustomerException("xml导入异常！");
			}
		}
		return true;
	}

	/** 
	 * Description : 导入采购计划Excel
	 * Create Date: 2011-12-9上午10:51:43 by yucy  Modified Date: 2011-12-9上午10:51:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("deprecation")
	public Boolean saveImportPlanExcel( UploadFileResult result ) throws Exception {
		if (result.isResult()) {
			HSSFWorkbook hssfWorkBook = ( HSSFWorkbook )result.getObject();//取得excel导入对象
			List<Procurementtask> procurementtaskList = new ArrayList<Procurementtask>();
			for(int sheetNumTemp = 0;sheetNumTemp <= hssfWorkBook.getSelectedTab() ;sheetNumTemp++){				//循环所有导入的Excel Sheet工作表。
				HSSFSheet sheet = hssfWorkBook.getSheetAt(sheetNumTemp);	
				Procurementtask procurementtask = null;
				for(int i=4;i<=sheet.getLastRowNum();i++){			//循环导入的Excel每一行
					HSSFRow row = sheet.getRow(i);					//获取当前行
					//是否有机构编码这一项
					boolean hasOrgCode =  row.getLastCellNum()>=12 ;
					if( StringUtils.hasLength( row.getCell( 0 ).toString() ) && StringUtils.hasLength( row.getCell( 1 ).toString() ) &&  StringUtils.hasLength( row.getCell( 2 ).toString() )){
						procurementtask = new Procurementtask();
						//编码
						procurementtask.setTaskNo(SequenceNumberUtil.getProcurementtaskSN());
						procurementtaskList.add(procurementtask);//存于list中待批量保存
						//计划信息
						if( row.getCell(0).toString().indexOf("年")>=0 ){
							procurementtask.setPeriodType("year");
						}else if ( row.getCell(0).toString().indexOf("月")>=0 ) {
							procurementtask.setPeriodType("month");
						}else{
							procurementtask.setPeriodType("quarter");
						}
						procurementtask.setPeriodValue( row.getCell(1).toString().indexOf(".")>=0?row.getCell(1).toString().substring(0,row.getCell(1).toString().indexOf(".")):row.getCell(1).toString()   );
						procurementtask.setSumQty(new BigDecimal( row.getCell(2).toString() ) );
						procurementtask.setSumTotal( new BigDecimal( row.getCell(3).toString() )  ) ;
						if( hasOrgCode ){
							OrgInfo buyer = orgInfoManager.getOrgInfoByOrgCode( row.getCell(4).toString() );
							if(buyer==null) throw new CustomerException("请确定系统中存在机构编码为"+row.getCell(4).toString()+"的相关机构，错误出现在第"+(i+1)+"行第5列");
							procurementtask.setBuyer(buyer);
							procurementtask.setMemo( row.getCell(5).toString() );
						}else{
							procurementtask.setBuyer( (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo() );
							procurementtask.setMemo( row.getCell(4).toString() );
						}
						procurementtask.setCreateTime(new Date());
						procurementtask.setCreateUser(AuthenticationHelper.getCurrentUser(true));
					}
					//计划明细信息
					ProtaskItem protaskItem = new ProtaskItem();
					
					protaskItem.setPurCategory( purCategoryManager.getPurCategoryByCategoryCode(  row.getCell(hasOrgCode?6:5).toString()  ));
					if(null==protaskItem.getPurCategory()) throw new CustomerException("请确定系统中存在编码为"+  row.getCell(hasOrgCode?6:5).toString()+"的相关品目！，错误出现在第"+(i+1)+"行第"+((hasOrgCode?6:5)+1)+"列");
					
					protaskItem.setGoodsClass( goodsClassManager.getGoodsClassByClassCode( row.getCell(hasOrgCode?7:6).toString() ));					
					if(null==protaskItem.getGoodsClass())  throw new CustomerException("请确定系统中存在编码为"+row.getCell(hasOrgCode?7:6).toString()+"的相关商品分类！，错误出现在第"+(i+1)+"行第"+((hasOrgCode?7:6)+1)+"列");
					
					protaskItem.setGoodsQty(new BigDecimal( row.getCell(hasOrgCode?8:7).toString() ));
					protaskItem.setGoodsTotal(new BigDecimal( row.getCell(hasOrgCode?9:8).toString() ));
					protaskItem.setGoodsPrice(new BigDecimal( row.getCell(hasOrgCode?10:9).toString() ));
					protaskItem.setMemo( row.getCell(hasOrgCode?11:10).toString() );
					protaskItem.setCreateTime(new Date());
					protaskItem.setProcurementtask(procurementtask);
					
					procurementtask.getProtaskItems().add(protaskItem);
					procurementtask.setCategoryNames( StringUtils.hasLength(procurementtask.getCategoryNames())?( procurementtask.getCategoryNames()) +","+protaskItem.getPurCategory().getCategoryName() : protaskItem.getPurCategory().getCategoryName()   );
					procurementtask.setUseStatus(CommonEnum.USER_STATUS_CONFIRM);//确认
				}
			}
			save(procurementtaskList);//保存
		}else{
			throw new CustomerException("EXCEL导入异常！");
		}
		return true;
	}
}
