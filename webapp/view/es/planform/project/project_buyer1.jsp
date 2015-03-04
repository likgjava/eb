<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/project_buyer1.js"></script>
<br class="spacer" />
<div class="wrap">
	<br class="spacer" />
	<div id="left">
		<h2>[${project.projCode }]${project.projName }</h2>
		<!-- 隐藏数据 -->
		<!-- 项目 全局使用 -->
		<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
		<p class="rightTxt1">采购方式：<span>${project.ebuyMethodCN }</span> [<a href="#"  onClick="javascript:checkProjectMenuForDialog('menu_bidtype');">变更</a>]<span style="padding-left:80px;padding-right:60px;"></span><span class="rightTxt1">项目经办人：${project.projManager }[<a href="#"  id="xmjbr_fp">变更</a>]</span></p>
		<p class="rightTxt1">联 系 人：[<a href="#">乔红花（13910003872 010-62567890-830 liuy@gpcsoft.com）</a> </p>
		<p class="rightTxt1">报名时间：2010-06-04 至 2010-06-15 <span style="padding-left:50px;"></span><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>时间：${"2010-06-15 8:00"} 至 ${"2010-06-18 8:00"} <span id="tb">我要<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out></span></p>
		<div id="tj">
			<p class="rightTxt2">待办任务（5）</p>
		</div>
	
	</div>
	<div id="right">
		<h2>快速通道</h2>
		<p class="rightTxt1"><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out> [<a href="#">查看</a>][<a href="#" onclick="javascript:checkProjectMenu('menu_purBulletin');">新增</a>]</p>
		<p class="rightTxt1"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out> [<a href="#">申请下载</a>]</p>
		<p class="rightTxt1">变更公告 [<a href="#">查看</a>]</p>
		<p class="rightTxt1">质疑 [<a href="#">查看</a>]</p>
	</div>
	<br class="spacer" />
</div>
<div class="wrap background" >
	<ul id="menu" >
		
	</ul>
	<div>
		<h5>
			<span onClick="javascript:checkProjectMenu('menu_PurBulletin');"><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span onClick="javascript:checkProjectMenu('menu_PurchaseDoc');"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span onClick="javascript:checkProjectMenu('menu_project_rule');">项目规则</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span onClick="javascript:checkProjectMenu('menu_project_SignUprecord');"><dm:out value="local__signup" tenderType="${project.tenderType}">报名信息</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span onClick="javascript:checkProjectMenu('menu_project_Bid');"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>信息</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span onClick="javascript:checkProjectMenu('menu_RestBulletin');"><dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span onClick="javascript:checkProjectMenu('menu_Notice');">通   知  书</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span onClick="javascript:checkProjectMenu('menu_OppugnRequisition');">质          疑</span>&nbsp;&nbsp;&nbsp;&nbsp;
		</h5>
	</div>
</div>
<div id="projDoDiv">
</div>
<script>
$(document).ready(function(){	
	//分配项目经办人
	$("#xmjbr_fp").click(function(){
		$.epsDialog({
	        title:'任务书条目',
	        url:$("#initPath").val()+"/ProjectController.do?method=toProjectLinkGovMan&projectId="+$("#projectId").val(),
	        width: '800',
	        height: '300',
	        onClose: function(){ 
	        	//跳转到招标中心项目首页
	        	$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForAgent&objId='+$("#projectId").val());
	        }
		});	
	})
})
//弹出显示
function checkProjectMenuForDialog(id){
	var projectId = $('#projectId').val();
	if(id == 'menu_bidtype'){
		$.epsDialog({
	        title:'变更采购方式',
	        url:$("#initPath").val()+"/ProjectController.do?method=toProjectComWork&projectId="+$("#projectId").val(),
	        width: '800',
	        height: '300',
	        onClose: function(){ 
	        	//跳转到招标中心项目首页
	        	$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForAgent&objId='+projectId);
	        }
		});
	}
	if(id == 'menu_project_number'){
		//设置招标编号
		$("#projDoDiv").empty().loadPage($("#initPath").val()+"/view/es/planform/project/numberSet.jsp?projectId="+$("#projectId").val());
	}
	
}
//公共区域显示
function checkProjectMenu(id){
	var projectId = $('#projectId').val();
	//招标公告
	if(id == 'menu_PurBulletin'){
		$("#projDoDiv").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurBulletinRead&projectId='+$("#projectId").val());
			     
	}
	//招标文件
	if(id == 'menu_PurchaseDoc'){
		$("#projDoDiv").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocbuyer&projectId='+$("#projectId").val());
	}
	if(id=='menu_project_rule'){
     //项目规则
        $("#projDoDiv").empty().loadPage($('#initPath').val()+'/ProjProcessRuleController.do?method=toProjProcessRuleBuyer&projectId='+$("#projectId").val());
	}
	if(id=='menu_project_SignUprecord'){
	 //报名信息
	 $("#projDoDiv").empty().loadPage($('#initPath').val()+'/SignUprecordController.do?method=toPage&projectId='+$("#projectId").val());
		}
	if(id=='menu_project_Bid'){
	 //投标信息
	 $("#projDoDiv").empty().loadPage($('#initPath').val()+'/BidController.do?method=toBidSupplier&projectId='+$("#projectId").val());
			}
	if(id=='menu_RestBulletin'){
	 //中标公告
	 $("#projDoDiv").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=getRestBulletin&projectId='+$("#projectId").val());
				}
	if(id=='menu_Notice'){
	  //通知书
	  $("#projDoDiv").empty().loadPage($('#initPath').val()+'/NoticeController.do?method=getNotice&projectId='+$("#projectId").val());
				}
	if(id=='menu_OppugnRequisition'){
	   //质疑
	   $("#projDoDiv").empty().loadPage($('#initPath').val()+'/OppugnRequisitionController.do?method=getOppugnRequisition&projectId='+$("#projectId").val());
					}




	
}
</script>