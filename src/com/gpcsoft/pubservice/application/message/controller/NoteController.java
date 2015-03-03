package com.gpcsoft.pubservice.application.message.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.message.domain.Note;
import com.gpcsoft.pubservice.application.message.enumeration.EnumMessage;
import com.gpcsoft.pubservice.application.message.service.NoteService;
import com.gpcsoft.pubservice.application.message.service.impl.NoteServiceImpl;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="noteFormView"
  *  url="view/pubservice/application/message/noteForm.jsp"
  * @gpcsoft.view value="noteListView"
  *  url="view/pubservice/application/message/noteList.jsp"
  * @gpcsoft.view value="replyNoteView"
  *  url="view/pubservice/application/message/reply_note.jsp"
  * @gpcsoft.view value="noteInfoView"
  *  url="view/pubservice/application/message/note_view.jsp"
  * @gpcsoft.view value="consulationListView"
  *  url="view/pubservice/application/message/consulationList.jsp"
  * @gpcsoft.view value="consulationDealListView"
  *  url="view/pubservice/application/message/consulation_deal_List.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Note.class})
@RequestMapping("/NoteController.do")//页面请求路径,可修改
public class NoteController extends AnnotationMultiController<Note> {

	@Autowired(required=true) @Qualifier("noteServiceImpl")
	private NoteService noteService;
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;

	/**
	 * Description :  跳转到 留言/咨询 新增
	 * Create Date: 2010-10-21上午07:02:47 by zhaojf  Modified Date: 2010-10-21上午07:02:47 by zhaojf
	 * @param   type 类型 00：留言, 01：咨询
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toNoteForm")
	public ModelAndView toNoteForm(String type,HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取表情图片
		Properties properties = new Properties();
        InputStream in =NoteServiceImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
        properties.load(in);
		String realPath = request.getSession().getServletContext().getRealPath(properties.getProperty("defaultFaceTag"));
		List<String> faceList = noteService.initAvatar(new ArrayList<String>(), realPath);
		
		model.put("faceTags", faceList);
		model.put("faceTrNumCount", faceList.size());//计数
		model.put("defaultFaceTag",properties.get("defaultFaceTag"));

		return new ModelAndView("noteFormView",model);
	}
		
	/**
	 * Description :  留言/咨询的 新增/回复保存
	 * Create Date: 2010-10-21上午06:59:27 by zhaojf  Modified Date: 2010-10-21上午06:59:27 by zhaojf
	 * @param   saveType 保存类型(新增保存或回复保存,当为'replySave'时为回复保存)
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=noteSave")
	public ModelAndView noteSave(Note command,String isAnnoy,SessionStatus status,String saveType) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//新增留言/咨询时
		if(!"replySave".equals(saveType)){			
			if(EnumMessage.TYPE_NOTE.equals(command.getType())){	
				//新增留言时,设置接收人
				OrgInfo supplier = orgInfoService.get(command.getReceiver());
				if(supplier!=null){
					command.setReceiverName(supplier.getUser().getUsername());
					command.setReceiver(supplier.getUser().getObjId());
				}
			}				
			
			//设置  留言人/咨询人
			command.setLeaver(AuthenticationHelper.getCurrentUser(true).getObjId());
			if("true".equals(isAnnoy)){
				command.setLeaverName("匿名");	
			}else{
				command.setIsAnnoy(false);
				command.setLeaverName(AuthenticationHelper.getCurrentUser(true).getUsername());
			}
		}else{//回复留言/咨询时
			
			command.setReplyTime(new Date());//设置回复时间
			
			//若是咨询   则要设置咨询接受人为当前用户
			if(EnumMessage.TYPE_CONSULATION.equals(command.getType())){
				command.setReceiver(AuthenticationHelper.getCurrentUser(true).getObjId());
				command.setReceiverName(AuthenticationHelper.getCurrentUser(true).getUsername());
			}
		}
		
		noteService.save(command);//保存
		model.put(Constants.JSON_RESULT, "操作成功");
		
		status.setComplete();		
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
		
	/**
	 * Description :  跳转到 留言/咨询 管理列表  咨询处理列表
	 * Create Date: 2010-10-21上午07:16:31 by zhaojf  Modified Date: 2010-10-21上午07:16:31 by zhaojf
	 * @param   typeParam 类型参数(留言/咨询 typeParam='consult'时为咨询管理,typeParam='dealConsult'时为咨询处理)
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toNoteList")
	public ModelAndView toNoteList(String typeParam) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//选择跳转页
		String returnStr = "noteListView";//留言管理列表
		if("consult".equals(typeParam)){
			returnStr = "consulationListView";//咨询管理列表user
		}
		if("dealConsult".equals(typeParam)){
			returnStr = "consulationDealListView";//咨询处理列表manager
		}
		
		User noteUser = AuthenticationHelper.getCurrentUser(true);//当前用户
		
		String noteLeaver = noteUser.getObjId();//向页面抛出当前用户Id,用于页面列表查询		
		model.put("noteUser",noteLeaver );
		
		return new ModelAndView(returnStr,model);
	}
			
	/**
	 * Description :  跳转到 留言查看/咨询查看   留言/咨询回复
	 * Create Date: 2010-10-21上午07:21:05 by zhaojf  Modified Date: 2010-10-21上午07:21:05 by zhaojf
	 * @param   objId 主键, viewOrReplyParam 查看或回复参数  默认为查看
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toNoteView")
	public ModelAndView toNoteView(String objId,String viewOrReplyParam) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("viewNote", noteService.get(objId));
		
		//获取表情图片存储位置
		Properties properties = new Properties();
        InputStream in = NoteServiceImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
        properties.load(in);
        model.put("faceFolder", properties.get("defaultFaceTag"));

		//选择是查看或回复页面
		String returnStr = "noteInfoView" ;
		if("reply".equals(viewOrReplyParam)){
			returnStr = "replyNoteView" ;
		}
		
		return new ModelAndView(returnStr,model);
	}

	/**
	 * Description :  查看时修改阅读状态为"已读"
	 * Create Date: 2010-10-21上午07:22:10 by zhaojf  Modified Date: 2010-10-21上午07:22:10 by zhaojf
	 * @param   objId 主键
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=viewSave")
	public ModelAndView viewSave(String objId) throws Exception{
		
		noteService.setReadStatusIsRead(objId, EnumMessage.IS_READ_YES);
		
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
