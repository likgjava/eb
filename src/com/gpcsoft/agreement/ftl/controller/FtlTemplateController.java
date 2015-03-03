package com.gpcsoft.agreement.ftl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.ftl.domain.FtlTemplate;
import com.gpcsoft.agreement.ftl.service.FtlTemplateService;
import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.cms.util.FileUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="ftlTemplateFormView"
  *  url="view/agreement/ftl/ftl_template_form.jsp"
  * @gpcsoft.view value="ftlTemplateListView"
  *  url="view/agreement/ftl/ftl_template_list.jsp"
  * @gpcsoft.view value="ftlTemplateDetailView"
  *  url="view/agreement/ftl/ftl_template_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={FtlTemplate.class})
@RequestMapping("/FtlTemplateController.do")//页面请求路径,可修改
public class FtlTemplateController extends AnnotationMultiController<FtlTemplate> {

	@Autowired(required=true) @Qualifier("ftlTemplateServiceImpl")
	private FtlTemplateService ftlTemplateService;

	/** 
	 * Description : 保存模板 
	 * Create Date: 2010-12-1下午08:49:28 by guoyr  Modified Date: 2010-12-1下午08:49:28 by guoyr
	 * @param   
	 * @return  
	 * @throws IOException 
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveFtlTemplate")
	public ModelAndView saveFtlTemplate(HttpServletRequest request, FtlTemplate  ftlTemplate) throws IOException{
		Map<String, String> model = new HashMap<String, String>();
		
		if(!StringUtils.hasLength(ftlTemplate.getFileName())){
			
			Calendar cld = Calendar.getInstance();
			// 设置拼音简写
			WordToSpell spell = new WordToSpell();
			ftlTemplate.setFileName(spell.String2Alpha(ftlTemplate.getFtlName()) + cld.get(Calendar.MILLISECOND)); 
		}
		
		// 模板的存储路径
		Properties properties = new Properties();
        InputStream in =FtlTemplateController.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
        properties.load(in);
        String ftlTemplatePath = properties.getProperty("ftlTemplatePath") ;
		String path = ftlTemplatePath + File.separator + ftlTemplate.getFileName()+".ftl";
		String content = ftlTemplate.getContent();
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs(); 
        }
		FileUtil.writerFile(file.getPath(),content,"UTF-8");
		
		ftlTemplate.setFtlPath(file.getPath());
		ftlTemplateService.save(ftlTemplate);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/** 
	 * Description : 修改模板时将模板的内容读取出来 
	 * Create Date: 2010-12-1下午08:49:28 by guoyr  Modified Date: 2010-12-1下午08:49:28 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView createOrUpdate(String objId, String includedProperties,
			HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		FtlTemplate  ftlTemplate = ftlTemplateService.get(objId);
		String path = ftlTemplate.getFtlPath();
		String content = "";
		if(new File(path).exists()){
			content = FileUtil.read(new FileInputStream(path));
		}
		ftlTemplate.setContent(content);
		model.put("ftlTemplate", ftlTemplate);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 删除模板 
	 * Create Date: 2010-12-2上午09:45:29 by guoyr  Modified Date: 2010-12-2上午09:45:29 by guoyr
	 * @param path 模板的物理路径  
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=deleteFtlTemplate")
	public ModelAndView deleteFtlTemplate(HttpServletRequest request,String objId,String path){
		Map<String, String> model = new HashMap<String, String>();
		ftlTemplateService.remove(objId, FtlTemplate.class);
		
		// 如果文件存在，则从物理路径上删除
		if(new File(path).exists()){
			FileUtils.delete(path);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 验证当前用户下的模板是不是唯一的
	 * Create Date: 2010-12-2下午03:49:37 by guoyr  Modified Date: 2010-12-2下午03:49:37 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=isUnique")
	public ModelAndView isUnique(HttpServletRequest request, String ftlName, String objId ){
		Map<String, Boolean> model = new HashMap<String, Boolean>();
		
		// 转换ascii码
		ftlName  = com.gpcsoft.bizplatform.base.common.util.StringUtils.ascii2Native(ftlName);
		User user = AuthenticationHelper.getCurrentUser(true);
		model.put("isUnique", ftlTemplateService.isUnique(ftlName, objId, user.getObjId()));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
}
