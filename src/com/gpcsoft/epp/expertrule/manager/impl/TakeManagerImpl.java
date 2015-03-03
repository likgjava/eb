package com.gpcsoft.epp.expertrule.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.expertrule.manager.TakeManager;
import com.gpcsoft.eprocurement.taker.provider.CodePO;

@Repository
public class TakeManagerImpl extends BaseManagerImpl<TakeExpertRule> implements TakeManager{
	
	/** 
	 * Description :  获取信息[来源：本地]
	 * Create Date: 2010-8-30下午04:31:01 by yangx  Modified Date: 2010-8-30下午04:31:01 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<CodePO> getInfoForFile(String infoType){
		String filePath = messageSource.getMessage("takeUrl");
		String path=filePath.replaceAll("\\$\\{rootPath}", Constants.ROOTPATH);
		String fileName = SeparationEnum.CATEGORY;
		if (infoType!=null&&SeparationEnum.UNIT.equals(infoType)) {//判断获取的是否为单位
			fileName = SeparationEnum.UNIT;//单位
		}else if (infoType!=null&&SeparationEnum.CITYCODE.equals(infoType)) {//判断获取的是否为评审地域
			fileName = SeparationEnum.CITYCODE;//评审地域
		}else if (infoType!=null&&SeparationEnum.EXPERTGROUP.equals(infoType)) {//判断获取的是否为专家类型
			fileName = SeparationEnum.EXPERTGROUP;//专家类型
		}else if (infoType!=null&&SeparationEnum.EDU.equals(infoType)) {//判断获取的是否为专家学历
			fileName = SeparationEnum.EDU;//专家学历
		}else if (infoType!=null&&SeparationEnum.ROOM.equals(infoType)) {//判断获取的是否为评审室
			fileName = SeparationEnum.ROOM;//评审室
		}else {//判断获取的是否为品目
			fileName = SeparationEnum.CATEGORY;//品目
		}
		String content = FileUtils.readFileByLines(path+fileName, "UTF-8");
		List<CodePO> list = new ArrayList<CodePO>();
		if (content!=null&&!"".equals(content)) {//采购单位文件内容
			String[] contents = content.split(SeparationEnum.AT);
			for(int i=0;i<contents.length;i++){
				String content1 = contents[i];
				if (content1!=null&&!"".equals(content1)) {//对应的单位Id与单位名称
					String[] nameAndValue = content1.split(SeparationEnum.COMMA);
					CodePO codePO = new CodePO();
					codePO.setColumn_name(nameAndValue[1]);
					codePO.setColumn_value(nameAndValue[0]);
					list.add(codePO);
				}
			}
		}else {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TAKE_UNIT));
		}
		return list;
	}
	
	/** 
	 * Description :  获取品目信息[来源：本地]
	 * Create Date: 2010-8-30下午04:43:33 by yangx  Modified Date: 2010-8-30下午04:43:33 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getPurCategory(){
		String filePath = messageSource.getMessage("takeUrl");
		String path=filePath.replaceAll("\\$\\{rootPath}", Constants.ROOTPATH);
		String content = FileUtils.readFileByLines(path+SeparationEnum.CATEGORY, "UTF-8");
		List<PurCategory> list = new ArrayList<PurCategory>();
		if (content!=null&&!"".equals(content)) {//鍝佺洰鏂囦欢鍐呭
			String[] contents = content.split(SeparationEnum.AT);
			for(int i=0;i<contents.length;i++){
				String content1 = contents[i];
				if (content1!=null&&!"".equals(content1)) {//瀵瑰簲鐨勫搧鐩甀d涓庡崟浣嶅悕绉�
					String[] nameAndValue = content1.split(SeparationEnum.COMMA);
					PurCategory purCategory = new PurCategory();
					purCategory.setObjId(nameAndValue[0]);
					purCategory.setCategoryCode(nameAndValue[0]);
					purCategory.setCategoryName(nameAndValue[1]);
					list.add(purCategory);
				}
			}
		}else {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TAKE_CATEGORY));
		}
		return list;
	}
	
	/** 
	 * Description :  根据单位名称进行模糊查询单位信息
	 * Create Date: 2010-8-23下午03:38:47 by yangx  Modified Date: 2010-8-23下午03:38:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<CodePO> getUnitByName(String unitName){
		String filePath = messageSource.getMessage("takeUrl");
		String path=filePath.replaceAll("\\$\\{rootPath}", Constants.ROOTPATH);
		String content = FileUtils.readFileByLines(path+SeparationEnum.UNIT, "UTF-8");
		List<CodePO> list = new ArrayList<CodePO>();
		if (content!=null&&!"".equals(content)) {//采购单位文件内容
			String[] contents = content.split(SeparationEnum.AT);
			for(int i=0;i<contents.length;i++){
				String content1 = contents[i];
				if (content1!=null&&!"".equals(content1)) {//对应的单位Id与单位名称
					String[] nameAndValue = content1.split(SeparationEnum.COMMA);
					CodePO codePO = new CodePO();
					if (nameAndValue[1].contains(unitName)) {//判断是否包含此字符串
						codePO.setColumn_name(nameAndValue[1]);
						codePO.setColumn_value(nameAndValue[0]);
						list.add(codePO);
					}
				}
			}
		}else {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TAKE_UNIT));
		}
		return list;
	}
}
