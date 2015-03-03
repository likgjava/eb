package com.gpcsoft.epp.expertrule.manager.impl;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.expertrule.dao.ExpertRuleDao;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRuleItem;
import com.gpcsoft.epp.expertrule.manager.ExpertRuleSendManager;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.eprocurement.taker.provider.TakeExpertRuleCondition;
import com.gpcsoft.eprocurement.taker.provider.TakerExpert;
import com.gpcsoft.eprocurement.taker.provider.TakerExpertServiceLocator;

/** 
  *  Comments: <strong>鍙戦�佷笓瀹惰鍒欑敵璇峰埌闈掑矝涓撳搴�</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   鐝犳捣鏀块噰杞欢鎶�鏈湁闄愬叕鍙�    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 鏉冮檺骞冲彴     		
  *  <br/>Create Date锛�2010-9-8 涓婂崍09:54:46 by liuy    					                            
  *  <br/>Modified Date:  2010-9-8 涓婂崍09:54:46 by liuy  
  */ 
public class ExpQdExpertRuleSendImpl extends BaseGenericServiceImpl implements ExpertRuleSendManager {
	
	@Autowired(required=true)  @Qualifier("expertRuleDaoHibernate")
	private ExpertRuleDao expertRuleDaoHibernate;
	
	public String[] sendExpertRule(String takeExpertRuleId) {
		List<TakeExpertRuleItem> list = expertRuleDaoHibernate.getTakeExpertRuleItemBytakeExpertRuleId(takeExpertRuleId);//瑙勫垯鏄庣粏
		TakeExpertRule takeExpertRule = list.get(0).getTakeExpertRule();//瑙勫垯
		Project project = (Project)this.get(list.get(0).getTakeExpertRule().getSubProjectId().getObjId(),Project.class);//瑙勫垯涓叧鑱旂殑椤圭洰
		Calendar calendar1 = Calendar.getInstance();//鍒涘缓鏃堕棿
		calendar1.setTime(new Date());
		Calendar calendar2 = Calendar.getInstance();//璇勫缁撴潫鏃堕棿
		calendar2.setTime(takeExpertRule.getBidEndtime());
		Calendar calendar3 = Calendar.getInstance();//璇勫寮�濮嬫椂闂�
		calendar3.setTime(takeExpertRule.getBidStarttime());
		String ebuyMethod = getEbuyMethodByOutDataBase(messageSource.getMessage("ebuyMethod"),project.getEbuyMethod());//鑾峰彇澶栧簱瀵瑰簲鐨勯噰璐柟寮�
		String takeExpertURL = messageSource.getMessage("takeExpertURL");//webService鍦板潃
		try {
			URL url = new URL(takeExpertURL);
			TakerExpertServiceLocator locator = new TakerExpertServiceLocator();
			TakerExpert service = locator.getTakerExpert(url);		
			com.gpcsoft.eprocurement.taker.provider.TakeExpertRule takeRule = new com.gpcsoft.eprocurement.taker.provider.TakeExpertRule();
			takeRule.setProject_name(project.getProjName());//椤圭洰鍚嶇О
			takeRule.setBid_project_id(project.getProjCode());//椤圭洰缂栧彿
			takeRule.setProcure_mode_id(ebuyMethod);//閲囪喘鏂瑰紡
			takeRule.setRoom_name(takeExpertRule.getBidRoom());//璇勫瀹�
			takeRule.setBuyer_name_ext(takeExpertRule.getBuyerNames());//閲囪喘鍗曚綅
			takeRule.setOut_buyer_ids(takeExpertRule.getOutBuyerIds());//鍥為伩鍗曚綅
			if (takeExpertRule.getAssembleTime()!=null) {
				Calendar calendar = Calendar.getInstance();// 闆嗗悎鏃堕棿
				calendar.setTime(takeExpertRule.getAssembleTime());
				takeRule.setAssemble_time(calendar);// 闆嗗悎鏃堕棿
			}
			if (takeExpertRule.getAssembleAddr()!=null) {
				takeRule.setAssemble_addr(takeExpertRule.getAssembleAddr());//闆嗗悎鍦扮偣
			}
			takeRule.setBid_starttime(calendar3);//璇勫寮�濮嬫椂闂�
			takeRule.setBid_endtime(calendar2);//璇勫缁撴潫鏃堕棿
			takeRule.setProject_principal(messageSource.getMessage("project_principal"));//椤圭洰璐熻矗浜�  
			takeRule.setCreate_staff(messageSource.getMessage("create_staff"));//鍒涘缓浜� 
			takeRule.setCreate_date(calendar1);//鍒涘缓鏃堕棿
			takeRule.setTake_staff_id(messageSource.getMessage("take_staff"));//鎶藉彇浜�
			takeRule.setTake_date(calendar1);//鎶藉彇鏃堕棿
			TakeExpertRuleCondition[] expertConditionArr = new TakeExpertRuleCondition[list.size()];//澧炲姞澶氫釜涓撳鎶藉彇鏉′欢
			for (int i=0;i<list.size();i++) {//鎶藉彇鏉′欢
				TakeExpertRuleItem takeExpertRuleItem = list.get(i);//涓撳瑙勫垯鏉＄洰;鏈湴
				com.gpcsoft.eprocurement.taker.provider.TakeExpertRuleCondition expertCondition = new com.gpcsoft.eprocurement.taker.provider.TakeExpertRuleCondition();
				expertCondition.setAge(takeExpertRuleItem.getAge());//骞撮緞
				expertCondition.setAmount(takeExpertRuleItem.getAmount());//姝ｉ�変汉鏁�
				expertCondition.setSub_amount(takeExpertRuleItem.getSubAmount());//澶囬�変汉鏁�
				expertCondition.setCity_code(takeExpertRuleItem.getCityCode());//璇勫鍦板煙
				expertCondition.setExpert_category_id(takeExpertRuleItem.getPurCategoryId());//鍝佺洰
				expertCondition.setExpert_group(takeExpertRuleItem.getExpertGroup());//涓撳绫诲瀷
				expertCondition.setExpert_level(takeExpertRuleItem.getExpertLevel());//鎶藉彇绫诲瀷
				expertCondition.setSpecialty_year(takeExpertRuleItem.getSpecialtyYear());//宸ラ緞
				expertCondition.setTop_educ(takeExpertRuleItem.getTopEduc());//瀛﹀巻
				expertConditionArr[i]=expertCondition;
			}
			takeRule.setExpertCondition(expertConditionArr);//涓撳鎶藉彇鏉′欢
			String res = service.createTakeProject(takeRule);//閫氳繃WebService鍒涘缓椤圭洰
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TRANS_EXPERTRULE_FAIL));
		}
		return null;
	}
	
	/** 
	 * Description :  
	 * Create Date: 2010-8-22涓嬪崍08:01:55 by yangx  Modified Date: 2010-8-22涓嬪崍08:01:55 by yangx
	 * @param   ebuyMethods:閰嶇疆鏂囦欢涓殑閲囪喘鏂瑰紡銆佹湰鍦颁笌澶栧簱鐨勫姣�,localEbuyMethod:鏈湴閲囪喘鏂瑰紡
	 * @return  
	 * @Exception   
	 */
	public String getEbuyMethodByOutDataBase(String ebuyMethods,String localEbuyMethod){
		if (ebuyMethods==null||"".equals(ebuyMethods)||ebuyMethods.split(SeparationEnum.COMMA)==null||ebuyMethods.split(SeparationEnum.COMMA).length<1) {//鍒ゆ柇鏄惁閰嶇疆浜嗙浉搴斾俊鎭�
			throw new EsException(messageSource.getMessage(EsExceptionEnum.IS_NOT_EBUYMETHOD));
		}
		String[] ebuyMethod = ebuyMethods.split(SeparationEnum.COMMA);
		for(int i=0;i<ebuyMethod.length;i++){
			String outEbuyMethod = ebuyMethod[i];
			if (outEbuyMethod==null||"".equals(outEbuyMethod)||outEbuyMethod.split(SeparationEnum.COLON)==null||outEbuyMethod.split(SeparationEnum.COLON).length<1) { //鍒ゆ柇姣忎釜瀵瑰簲鐨�(鏈湴涓庡搴�)鏄惁鏈夊��
				throw new EsException(messageSource.getMessage(EsExceptionEnum.IS_NOT_EBUYMETHOD));
			}
			String[] outEbuyMethods = outEbuyMethod.split(SeparationEnum.COLON);
			String localEbuy = outEbuyMethods[0];
			String OutEbuy = outEbuyMethods[1];
			if (localEbuy.equals(localEbuyMethod)) {//鍒ゆ柇閰嶇疆涓殑鏈湴閲囪喘鏂瑰紡涓庝紶杩涙潵鐨勬槸鍚︾浉绛�
				return OutEbuy;
			}
		}
		return null;
	}

}
