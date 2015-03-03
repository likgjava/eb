package com.gpcsoft.epp.projreview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidItems;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.service.OpenBidService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="openBidFormView"
  *  url="view/es/planform/projreview/openBidForm.jsp"
  * @gpcsoft.view value="openBidTreeFormView"
  *  url="view/es/planform/projreview/openBidTreeForm.jsp"
  * @gpcsoft.view value="openBidListView"
  *  url="view/es/planform/projreview/openBidList.jsp"
  * @gpcsoft.view value="openBidDetailView"
  *  url="view/es/planform/projreview/openBidDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OpenBid.class})
@RequestMapping("/OpenBidController.do")//页面请求路径,可修改
public class OpenBidController extends AnnotationMultiController<OpenBid> {
	@Autowired(required=true) @Qualifier("openBidServiceImpl")
	private OpenBidService openBidService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	
	/**
	 * FuncName: underLineUpdateBid
	 * Description : 修改开标结果
	 * @param 
	 * @param request
	 * @param bid
	 * @param status
	 * @param bidItem
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-6-22  下午02:10:44
	 * @Modifier: liuke
	 * @Modified Date:2011-6-22  下午02:10:44
	 */
	@RequestMapping(params = "method=underLineUpdateOpenBid")
	public ModelAndView underLineUpdateOpenBid(HttpServletRequest request,Bid bid, SessionStatus status,String bidItem)throws Exception {
			logger.debug("\nes BidController||underLineUpdateBid\n");
			bid.setAttachRelaId(request.getParameter("attrId"));
			bid.setBidRemark(request.getParameter("bidRemark"));
			String packIds = request.getParameter("PACK_IDS");
			Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 附件上传控制
			if(null == packIds || "".equals(packIds)){
				throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_FACTOR_INFO_IS_ERROR));
			}
			List<BidItems> bidItemsList = new ArrayList<BidItems>();
			if (null != bidItem && !"".equals(bidItem) && !"UNDEFINED".equals(bidItem.toUpperCase())) {// 组装投标品目数据
				JSONArray jsonArray = JSONArray.fromObject(bidItem);
				if (null != jsonArray && jsonArray.isArray()) {
					for(int i=0;i<jsonArray.size();i++){
						JSONObject jSONObject = (JSONObject)jsonArray.get(i);
						BidItems bidItems = (BidItems)JSONObject.toBean(jSONObject,BidItems.class);
						bidItemsList.add(bidItems);
					}
				}
			}
			String strNum = request.getParameter("listNum");// 1.组装指标响应数据
			if(null != strNum && !"".equals(strNum)){
				Integer listNum = Integer.parseInt(strNum);
				List<CongFactorResponse> congFactorResponseList = new ArrayList<CongFactorResponse>();
				for(int i=0;i<listNum;i++){
					CongFactorResponse congFactorResponse = new CongFactorResponse();
					congFactorResponse.setObjId(request.getParameter("objId"+i));
					congFactorResponse.setFactorId(request.getParameter("factorId"+i));
					congFactorResponse.setFactorName(request.getParameter("factorName"+i));
					congFactorResponse.setRespValue(request.getParameter("respValue"+i));
					congFactorResponse.setPackIds(request.getParameter("packId"+i));
					if(null == congFactorResponse.getObjId() || "".equals(congFactorResponse.getObjId())){
						congFactorResponse.setCreateTime(new java.util.Date());
						congFactorResponse.setObjId(null);
					}
					isUploadFile = new Boolean(request.getParameter("isUploadFile"+i));// 1.1附件上传控制
					if(isUploadFile){
						Object o=AttachmentUtil.uploadFile(request,("attrFile"+i));
						if(o instanceof GpcBaseObject){
							Attachment attachment = (Attachment)o;
							attachmentService.saveAttachment((Attachment)o);
							congFactorResponse.setAttr(attachment);
						}
					}else{
						String attId = request.getParameter("attrId"+i);
						if(null != attId && !"".equals(attId)){
							Attachment attachment = new Attachment();
							attachment.setObjId(attId);
							congFactorResponse.setAttr(attachment);
						}else{
							congFactorResponse.setAttr(null);
						}
					}
					congFactorResponseList.add(congFactorResponse);
				}
				openBidService.updateUnderLineOpenBid(bid,congFactorResponseList,packIds.split(","),bidItemsList);
			}else{
				openBidService.updateUnderLineOpenBid(bid,new ArrayList<CongFactorResponse>(),packIds.split(","),bidItemsList);
			}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
    
	
	
	
	/**
	 * FuncName: underLineSaveBid
	 * Description :录入开标结果
	 * @param 
	 * @param request
	 * @param bid
	 * @param status
	 * @param bidItem
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-6-22  下午02:11:31
	 * @Modifier: liuke
	 * @Modified Date:2011-6-22  下午02:11:31
	 */
	@RequestMapping(params = "method=underLineSaveOpenBid")
	public ModelAndView underLineSaveOpenBid(HttpServletRequest request,Bid bid, SessionStatus status,String bidItem)throws Exception {
			logger.debug("\nes BidController||saveBid\n");
			bid.setAttachRelaId(request.getParameter("attrId"));
			bid.setBidRemark(request.getParameter("bidRemark"));
			String packIds = request.getParameter("PACK_IDS");
			Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 附件上传控制
			
			if(null == packIds || "".equals(packIds)){
				throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_FACTOR_INFO_IS_ERROR));
			}
			List<BidItems> bidItemsList = new ArrayList<BidItems>();
			if (null != bidItem && !"".equals(bidItem) && !"UNDEFINED".equals(bidItem.toUpperCase())) {// 组装投标品目数据
				JSONArray jsonArray = JSONArray.fromObject(bidItem);
				if (null != jsonArray && jsonArray.isArray()) {
					for(int i=0;i<jsonArray.size();i++){
						JSONObject jSONObject = (JSONObject)jsonArray.get(i);
						BidItems bidItems = (BidItems)JSONObject.toBean(jSONObject,BidItems.class);
						bidItemsList.add(bidItems);
					}
				}
			}
			String strNum = request.getParameter("listNum");// 1.组装指标响应数据
			if(null != strNum && !"".equals(strNum)){
				Integer listNum = Integer.parseInt(strNum);
				List<CongFactorResponse> congFactorResponseList = new ArrayList<CongFactorResponse>();
				for(int i=0;i<listNum;i++){
					CongFactorResponse congFactorResponse = new CongFactorResponse();
					congFactorResponse.setObjId(request.getParameter("objId"+i));
					congFactorResponse.setFactorId(request.getParameter("factorId"+i));
					congFactorResponse.setFactorName(request.getParameter("factorName"+i));
					congFactorResponse.setRespValue(request.getParameter("respValue"+i));
					congFactorResponse.setPackIds(request.getParameter("packId"+i));
					if(null == congFactorResponse.getObjId() || "".equals(congFactorResponse.getObjId())){
						congFactorResponse.setCreateTime(new java.util.Date());
						congFactorResponse.setObjId(null);
					}
					isUploadFile = new Boolean(request.getParameter("isUploadFile"+i));// 1.1附件上传控制
					if(isUploadFile){
						Object o=AttachmentUtil.uploadFile(request,("attrFile"+i));
						if(o instanceof GpcBaseObject){
							Attachment attachment = (Attachment)o;
							attachmentService.saveAttachment((Attachment)o);
							congFactorResponse.setAttr(attachment);
						}
					}else{
						String attId = request.getParameter("attrId"+i);
						if(null != attId && !"".equals(attId)){
							Attachment attachment = new Attachment();
							attachment.setObjId(attId);
							congFactorResponse.setAttr(attachment);
						}else{
							congFactorResponse.setAttr(null);
						}
					}
					congFactorResponseList.add(congFactorResponse);
				}
				openBidService.saveUnderLineOpenBid(bid,congFactorResponseList,packIds.split(","),bidItemsList);
			}else{
				openBidService.saveUnderLineOpenBid(bid,new ArrayList<CongFactorResponse>(),packIds.split(","),bidItemsList);
			}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
}

