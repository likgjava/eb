<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!-- 隐藏数据 -->
<!-- 项目 全局使用 start-->
<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
<input type="hidden" value="" id="subProjectId" name="subProjectId" />
<input type="hidden" value="" id="projectTaskId" name="projectTaskId" />
<!-- 项目 全局使用 end-->
<div id="projectTop" class="accountInfo" style="margin-bottom:5px;">
	<div class="right">
		<p>待办工${gpc}作 23条</p>
		<p>07月12日，星期二</p>
	</div>
	<p><span>[${project.projCode }]${project.projName }</span></p>
	<p>
	采购方式：${project.ebuyMethodCN } 
	监管：${project.monitor.name } 
	负责人：${project.manager.name }<c:if test="${project.manager  == null ||project.manager.objId == null }">[<a href="#"    onClick="javascript:checkProjectMenuForDialog('xmjbr_fp',false);">指定经办人</a>]</c:if>
	项目状态：${project.useStatusCN}
	</p>
</div>
<div class="accordion" style="width:199px;float:left;margin-right:5px;">
	<div class="accordionHeader">
		<h2><span>Folder</span>统计分析</h2>
	</div>
	<div class="accordionContent">
		<div id="menuTree"></div>
	</div>
</div>
<div id="projectDoDiv"></div>

<script language="javascript">
var projectId = $("#projectId").val();
var projectagent = {};
var menuTree;
//定义树鼠标左键点击事件调用的方法名
projectagent.treeLeftClick = function(id){
	$("#projectTaskId").val(id);//将当前的选择ID存入到隐藏数据中，方便数据回显
	$.getJSON($('#initPath').val()+'/ProjectPlanController.do?method=createOrUpdate', {objId:id}, function(json){
		if(json.result)alert(json.result);
		if(json.failure)return;
		if(json.projectPlan.isLeaf && json.projectPlan.isLeaf == 'true'){//若为叶子节点，则跳转到具体 的页面，否则不跳转。
			var url = json.projectPlan.url;
			if(url.indexOf('?')==-1){//若请求里没有"?",表示请求地址为jsp,则需要手动添加?projectId=
				url+= "?projectId="+$("#projectId").val();
			}else if(url.indexOf('projectId=')>-1){//若请求里有"projectId=",表示请求需要项目ID，则将项目ID赋给它
				url+=$("#projectId").val();
			}
			//alert(url);
			$("#projectDoDiv").empty().loadPage($('#initPath').val()+'/'+url);
		}else{
			return false;
		}
	});
	
}

//初始化系统配置类型树
projectagent.loadMenuTree = function(){
	menuTree = new dhtmlXTreeObject("menuTree","100%","100%",0);
	menuTree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	menuTree.enableThreeStateCheckboxes(false);

	//定义树鼠标左键点击事件调用的方法名
	menuTree.setOnClickHandler(projectagent.treeLeftClick);
	 
	//定义从xml文件加载树
	menuTree.loadXML($("#initPath").val()+"/ProjectController.do?method=getMenuTree&projectId="+$("#projectId").val()+"&objId="+$("#objid").val(), function(){
		
	});
	//定义设置允许动态加载xml文件（异步加载）
	menuTree.setXMLAutoLoading("ProjectController.do?method=getMenuTree&projectId="+$("#projectId").val()+"&objId="+$("#objid").val());
}
$(document).ready(function(){
	//初始化系统配置类型树
	projectagent.loadMenuTree();
	
})
		
		


//弹出显示
function checkProjectMenuForDialog(id,isReLoad){
	if(id == 'xmjbr_fp'){//分配项目负责人
		dialogPublicMethod("/ProjectController.do?method=toProjectLinkGovMan&projectId="+$("#projectId").val(),isReLoad,"分配项目负责人",800,300);
	}
	
}
//弹出层共有方法
function dialogPublicMethod(url,isReLoad,title,width,height){
	$.epsDialog({
        title:title,
        url:$("#initPath").val()+url,
        width: width,
        height: height,
        isReload:isReLoad,
        onClose: function(){ 
       		//跳转到招标中心项目首页
        	$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForAgent&objId='+projectId+'&userType=agent');
        }
	});
}
</script>