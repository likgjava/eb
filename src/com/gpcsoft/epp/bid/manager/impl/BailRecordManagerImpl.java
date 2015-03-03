package com.gpcsoft.epp.bid.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.bid.dao.BailRecordDao;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.manager.BailRecordManager;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;

@Repository
public class BailRecordManagerImpl extends BaseManagerImpl<BailRecord> implements BailRecordManager {

	@Autowired(required=true)  @Qualifier("bailRecordDaoHibernate")
	private BailRecordDao bailRecordDaoHibernate;
	
	 /**
	   * Funcname:checkBailStatusForSupllier
	   * Description : 保证金记录对供应商参与招标项目的影响(保证金记录状态00：未收；01：已收；02：已退)
	   * @Create Date: 2010-8-11下午02:08:50 
	   * @author shenjianzhuang  
	   * @Modified Date: 2010-8-11下午02:08:50 
	   * @author  shenjianzhuang
	   * @return  boolean
	   * @Exception 
	   */
	  public boolean checkBailStatusForSupllier(String  bailRecordId)
	  {
		  StringBuffer exception = new StringBuffer();
		  Boolean checkValue = true;//判断是否有不满足条件的数据
		  BailRecord bailRecord = bailRecordDaoHibernate.get(bailRecordId);//获取保证金记录表对象
		  if(null==bailRecordId||"".equals(bailRecordId)){
			  exception.append(messageSource.getMessage(EsExceptionEnum.BAILRECORDID_ISNOTEXISTED));//保证金记录表不存在
		  }else {
			  if(bailRecord.getBailStatus().equals("00")) {
				  exception.append(messageSource.getMessage(EsExceptionEnum.BAILRECORDID_BAILSTATUS_UNERCEIVED));//保证金未交付
			  }
			  if(bailRecord.getBailStatus().equals("01")){
				  exception.append(messageSource.getMessage(EsExceptionEnum.BAILRECORDID_BAILSTATUS_ACCEPTED));//保证金已交付
			  }
			  if(bailRecord.getBailStatus().equals("02")) {
				  exception.append(messageSource.getMessage(EsExceptionEnum.BAILRECORDID_BAILSTATUS_REFUNDED));//保证金已退回
			  }
		  }
		  if(exception.length()>0)checkValue=false;//若有异常信息，表示有异常，需要把判断置为false;
		  if (!checkValue) {
			 throw new EsException(exception.toString());//若有影响项目操作的数据，则抛出异常
			}
		  return checkValue;
	  }

}
