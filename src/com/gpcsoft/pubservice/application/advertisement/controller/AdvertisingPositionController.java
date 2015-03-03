package com.gpcsoft.pubservice.application.advertisement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.pubservice.application.advertisement.service.AdvertisingPositionService;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingPosition;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingSubscribe;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.baseData.domain.Dictionary;
import com.gpcsoft.srplatform.baseData.service.DictionaryService;

/**
  * @gpcsoft.view value="adverPositionAddOrModifyView"
  *  url="view/pubservice/application/advertisement/adver_position_add_modify.jsp"
  * @gpcsoft.view value="advertisingPositionListView"
  *  url="view/pubservice/application/advertisement/adver_position_list.jsp"
  * @gpcsoft.view value="adverPositionDetailView"
  *  url="view/pubservice/application/advertisement/adver_position_detail.jsp"
  * @gpcsoft.view value="showPaomadengView"
  *  url="view/pubservice/application/advertisement/adver_preview_div.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={AdvertisingPosition.class})
@RequestMapping("/AdvertisingPositionController.do")//页面请求路径,可修改
public class AdvertisingPositionController extends AnnotationMultiController<AdvertisingPosition> {

	@Autowired(required=true) @Qualifier("advertisingPositionServiceImpl")
	private AdvertisingPositionService advertisingPositionService;
	
	@Autowired(required=true) @Qualifier("dictionaryServiceImpl")
	private DictionaryService dictionaryService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	/**
	 * Description :  新增或修改
	 * Create Date: 2011-3-23上午10:21:52 by zhaojf  Modified Date: 2011-3-23上午10:21:52 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toAddOrModifiy")
	public ModelAndView toAddOrModifiy(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		if(objId != null && objId != ""){//修改
			AdvertisingPosition advertisingPosition = advertisingPositionService.get(objId);
			model.put("advertisingPosition", advertisingPosition);
		}else{//新增
			AdvertisingPosition advertisingPosition = new AdvertisingPosition();
			Dictionary dictionary  = new Dictionary();
			advertisingPosition.setPositionDictionary(dictionary);
			model.put("advertisingPosition", advertisingPosition);
		}
		
		//广告位置字典项
		List<Dictionary> positionDictionaryList = dictionaryService.getDictionaryByDictType("adPosition");
		model.put("positionDictionaryList", positionDictionaryList);
		
		return new ModelAndView("adverPositionAddOrModifyView",model);
	}
	
	/**
	 * Description :  删除(可批量删除)
	 * Create Date: 2011-3-23上午10:35:13 by zhaojf  Modified Date: 2011-3-23上午10:35:13 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=removeAdverPositon")
	public ModelAndView removeAdverPositon(String adverPositionIds) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		model = advertisingPositionService.removeAdverPositon(adverPositionIds);//删除
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  判断广告位下是否还有广告订阅记录
	 * Create Date: 2011-3-31上午11:24:03 by zhaojf  Modified Date: 2011-3-31上午11:24:03 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=isHasAdverSubcribe")
	public ModelAndView isHasAdverSubcribe(String objId,String operType) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("operType", "");
		if("auditValide".equals(operType)){//判断此广告位是否还有有效的广告订阅信息
			//广告订阅
			paramsMap.put("adverPositionId", objId);
			List<AdvertisingSubscribe> adverSubscribeList = advertisingPositionService.getAdverSubscribeByPositionId(paramsMap);
			model.put(Constants.JSON_RESULT, adverSubscribeList.size()>0?true:false);
		}else{//判断广告位下是否还有广告订阅记录(有效和无效的)
			String[] adverPositonArray = new String[1];
			adverPositonArray[0] = objId;
			model.put(Constants.JSON_RESULT, advertisingPositionService.judgeCondition(adverPositonArray));
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * Description :  广告位详情
	 * Create Date: 2011-3-23上午11:48:56 by zhaojf  Modified Date: 2011-3-23上午11:48:56 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toAdverPositionDetail")
	public ModelAndView toAdverPositionDetail(String objId,String operType) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		String returnView = "adverPositionDetailView";
		paramsMap.put("operType", "");
		//selectLoad选择广告位时
		if("selectLoad".equals(operType)){
			paramsMap.put("operType", "selectLoad");
			returnView = Constants.JSON_VIEW;
		}
		
		//跑马灯预览显示
		if("preview".equals(operType)){
			paramsMap.put("operType", "preview");
			returnView = "showPaomadengView";
		}
		
		//广告位
		AdvertisingPosition advertisingPosition = advertisingPositionService.get(objId);
		model.put("advertisingPosition", advertisingPosition);
		
		//广告订阅
		paramsMap.put("adverPositionId", objId);
		List<AdvertisingSubscribe> adverSubscribeList = advertisingPositionService.getAdverSubscribeByPositionId(paramsMap);
		model.put("adverSubscribeList", adverSubscribeList);
		
		//文件的大小
		Integer fileValue = 0;
		for(int i=0;i<adverSubscribeList.size();i++){
			Attachment attachment = attachmentService.get(adverSubscribeList.get(i).getAdverFile());
			fileValue += Integer.valueOf(attachment.getFileSize().toString());
		}
		model.put("fileValueTotal", fileValue);
		
		//广告高度、宽度
		model.put("adverHeight", advertisingPosition.getAdverLength());
		model.put("adverWidth", advertisingPosition.getAdverWidth());
		
		//查看操作时
		if(adverSubscribeList != null && adverSubscribeList.size()>0){
			model.put("advertisingSubscribeDiv", adverSubscribeList.get(0));
		}
		
		return new ModelAndView(returnView,model);
	}

	/**
	 * Description : 获取广告位下的随机图片  
	 * Create Date: 2011-9-28下午03:56:18 by zhaojf  Modified Date: 2011-9-28下午03:56:18 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=getAdverOfPositionByRandom")
	public ModelAndView getAdverOfPositionByRandom(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		String adver_position_name = request.getParameter("adver_position_name");//广告位名称
		if(adver_position_name!="" && adver_position_name != null){
			//根据广告位名称获取广告位信息
			AdvertisingPosition adverPosition = advertisingPositionService.getPositionByName(adver_position_name);
			if(adverPosition != null){
				paramsMap.put("adverPositionId", adverPosition.getObjId());
				List<AdvertisingSubscribe> adverSubscribeList = advertisingPositionService.getAdverSubscribeByPositionId(paramsMap);
				Random rand = new Random();
				AdvertisingSubscribe adverSubscribe = adverSubscribeList.size()>0?adverSubscribeList.get(rand.nextInt(adverSubscribeList.size())):null;
				model.put("adverSubscribe", adverSubscribe);
			}
		}
		model.put("adver_position_name", adver_position_name);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
