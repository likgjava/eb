package com.gpcsoft.epp.signuprecord.service;
import java.util.List;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
public interface SignUpCondFactorService extends BaseGenericService<SignUpCondFactor> {
    /**
     * 
     * Description : 根据项目主键得到符合性要求对象 
     * Create Date: 2010-8-10下午01:46:47 by liuke  Modified Date: 2010-8-10下午01:46:47 by liuke
     * @param   
     * @return  
     * @Exception
     */
	public List<SignUpCondFactor> getSignUpCondFactorListByProjectId(String projectId);
	
	/**
	 * 
	 * Description :根据主键获得符合性要求对象   
	 * Create Date: 2010-9-7上午09:23:28 by liuke  Modified Date: 2010-9-7上午09:23:28 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUpCondFactor getSignUpCondFactorByObjId(String objId);
	
	/**
	 * 
	 * Description :根据主键删除报名指标 
	 * Create Date: 2010-9-7上午10:07:00 by liuke  Modified Date: 2010-9-7上午10:07:00 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeSignUpCondFactorByObjId(String objId);
	
	/**
	 * 
	 * Description :保存报名指标   
	 * Create Date: 2010-9-7上午10:11:50 by liuke  Modified Date: 2010-9-7上午10:11:50 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUpCondFactor saveSignUpCondFactor(SignUpCondFactor signUpCondFactor);
}
