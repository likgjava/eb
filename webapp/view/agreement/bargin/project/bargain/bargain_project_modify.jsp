<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<input type="hidden" id="projectId" name="projectId" value="${projectId}" />
<div id="epsTabs">
	<ul>
		<li><a href="#project_info" id = "tabs_requireItem" class="refreshData"><span>项目和需求条目</span></a></li>
		<li><a href="#project_info" id = "tabs_turnAndRule" class="refreshData"><span>轮次和规则</span></a></li>
		<li><a href="#project_info" id = "tabs_otherInfo" class="refreshData"><span>其他信息</span></a></li>
	</ul>
	<div id="project_info"></div>
</div>

<div class="formTips attention">
	<ul>
		<li>
			<em>完成修改，发布竞价项目请点击
				<a id="pubProject" href="javascript:void(0);"><strong>发布该项目</strong></a>
				或者<a id="return" href="javascript:void(0);"><strong>返回</strong></a>
			</em>
		</li>
	</ul>
</div>

<script>
var ReverseProjectCreateForm = {};

$(document).ready(function(){
	
	var $tabs = $('#epsTabs').tabs({}); 
	var projectId = $('#projectId').val();
	var url = $('#initPath').val()+"/BargainProjectController.do?method=toUpdateRequireInfo&objId="+projectId;
	$('#project_info').loadPage(url);
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		var id = $(this).attr('id');
		if ("tabs_requireItem"==id) {//项目和需求条目
			url = $('#initPath').val()+"/BargainProjectController.do?method=toUpdateRequireInfo&objId="+projectId;
		} else if ("tabs_turnAndRule"==id) {//轮次和规则
			url = $('#initPath').val()+"/BargainProjectController.do?method=toUpdateTurnAndRule&objId="+projectId;
		} else if ("tabs_otherInfo"==id) {//其他信息
			url = $('#initPath').val()+"/BargainProjectController.do?method=toUpdateSignPayLinkerInfo&objId="+projectId;
		} 
		$('#project_info').loadPage(url);
	})
	
	//发布项目
	$("#pubProject").click(function(){
		if(window.confirm("确定发布该项目吗")){
			//提示扩展信息的填写
			$.getJSON($('#initPath').val() + "/BargainProjectController.do?method=getProjectExInfo&projectId="+projectId,{},function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
				
				var res = '';
				if(json.ruleCount==0) {
					res += '规则和轮次信息,';
				}
				if(json.signCount==0) {
					res += '供应商资质,';
				}
				if(json.payCount==0) {
					res += '支付信息,';
				}
				if(json.contactCount==0) {
					res += '联系方式,';
				}
				if(res.length > 0) {
					res = res.substring(0,res.length-1);
					alert('您的' + res + '未填写,请填写完整');
				}else{
					if(json.requireCount<1) {
						alert('您未填写需求条目!');
					}else {
						$.getJSON($('#initPath').val() + "/BargainProjectController.do?method=toSubmitProject&projectId="+projectId,{},function(json){
							if(json.failure){if(json.result)alert(json.result);return;}
							window.location.reload();
						});
					}
				}
			});
		}
	})
	
	//返回
	$("#return").click(function(){
		window.location.reload();
	})
});
</script>
