package com.gpcsoft.pubservice.application.message.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.message.domain.Advise;

public interface AdviseService extends BaseGenericService<Advise> {
	
	void saveReply(Advise advise)  throws Exception;

}
