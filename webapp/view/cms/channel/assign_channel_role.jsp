<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>  
<script>
var alreadySelectIds = "";
var totalPageNum = 1;
var curPage = 1;

// 加载要选择的用户列表
function getSourceUser(){
	var url = $("#initPath").val()+"/UserController.do?method=list&queryColumns=objId,usName,emp.objId,emp.name,emp.email,&rp=15&page="+curPage;
	if($('#shortSpellName').val() != ""){
		url = url+"&emp.objId="+$('#queryUserID').val();
	}
	//读取数据
	$.getJSON(url,{},function(json){
		totalPageNum = parseInt(json.totalPageNum);
		if(totalPageNum == 0)totalPageNum = 1;
		curPage = parseInt(json.page);
		$("#toSelect").empty();
		$(json.rows).each(function(i,n){
			if(alreadySelectIds.indexOf(n.objId)  < 0){
				$("#toSelect").append('<option value='+n.objId+'>'+n['emp.name']+'       email:'+n['emp.email']+'</option>');
			}
		});
		$('#curPage').html(curPage +"/"+totalPageNum);
	})
}

// 加载已经分配角色的用户
function getRoleUser(){
	alreadySelectIds = "";
	var channelRoleList = $.ajax({ url: $('#initPath').val()+"/ChannelRoleController.do?method=getChannelRoleUser",data:{"channelId":channelId,"roleType":$('#channelRoleType').val(),"checkStatus":$('#checkStatus').val()}, async: false }).responseText
	var channelRoleJosn =JSON.parse(channelRoleList).result;
	if(channelRoleJosn.length > 0)$("#alreadySelect").empty();
	$.each(channelRoleJosn,function(i,n){
		alreadySelectIds += (n.user.objId + ",");
		$("#alreadySelect").append('<option value="'+n.user.objId+'">'+n['userName']+'       email:'+n['userEmail']+'</option>');
	})
	
	
}
$(document).ready(function(){

	
	$("#queryUserForm").validate();
	getRoleUser();
	//高级查询
	$("#addUser").click(function(){
		getSourceUser()
		$("[name=addChannelRole]").toggle("slow");
		return false;
	});
	$("#closeUser").click(function(){
		$("[name=addChannelRole]").toggle("slow");
		return false;
	});

	//添加(添加的时候获取所有被选中节点id，包括第三中状态的节点（全部选中的节点）)
	$("#addRoleUser").click(function(){
		var selectUser = $('#toSelect option:selected').map(function(){ return $(this).val();}).get().join(","); 
		var selectUserName = $('#toSelect option:selected').map(function(){ return $.trim($(this).text());}).get().join(","); 
		if(!selectUser){
			alert("请至少选择一项！");
			return;
		}
		$('#toSelect option:selected').remove();
		for(var i=0; i<selectUser.split(",").length; i++){
			var channelRole = {};
			channelRole['user.objId'] = selectUser.split(",")[i];
			channelRole.userName = (selectUserName.split(",")[i]).split('email:')[0];
			channelRole.userEmail = (selectUserName.split(",")[i]).split('email:')[1];
			channelRole.roleType = $('#channelRoleType').val();
			channelRole.checkStatus = $('#checkStatus').val();
			channelRole.channelIds = checkedChannelId;
			channelRole.checkedRealChannelId = checkedRealChannelId;
			channelRole.partiallyCheckedChannelId = partiallyCheckedChannelId;
			$.getJSON($('#initPath').val()+'/ChannelRoleController.do?method=saveChannelRole',channelRole,function(json){
				getRoleUser()
			});
		}
	})
	
	//删除(删除的时候获取所有被选中节点id，不包括第三中状态的节点（部分选中的节点）)
	$("button[id^=deleteRoleUser]").click(function(){
		var selectUser = $('#alreadySelect option:selected').map(function(){ return $(this).val();}).get().join(","); 
		var selectUserName = $('#alreadySelect option:selected').map(function(){ return $.trim($(this).text());}).get().join(","); 
		if(!selectUser){
			alert("请至少选择一项！");
			return;
		}
		$('#alreadySelect option:selected').remove();
		for(var i=0; i<selectUser.split(",").length; i++){
			var userId = (selectUser.split(",")[i]);
			var userName = selectUserName.split(",")[i];
			$.getJSON($('#initPath').val()+'/ChannelRoleController.do?method=removeChannelRole',{'userId':userId,'channelIds':checkedRealChannelId,'roleType':$('#channelRoleType').val(),'checkStatus':$('#checkStatus').val()},function(json){
				if(json.failure){alert(json.result);return;}
				$("#toSelect").append('<option value='+userId+'>'+userName+'</option>')
			});
		}
		alreadySelectIds = $('#alreadySelect option').map(function(){ return $(this).val();}).get().join(",");
	})

	// 分页
	$('[id^=trunPage_]').click(function(){
		var turnPage = $(this).attr('id').replace('trunPage_','');
		if(turnPage == 'pre'){
			if(curPage <= 1){alert("已经是第一页了！");return false}
			curPage = curPage - 1 ;
		}else{
			if(curPage >= totalPageNum){alert("已经是最后一页了！");return false;}
			curPage = curPage + 1 ;
		}
		getSourceUser();
	return false;
	});

	// 查询用户
	$('#querySourceUser').click(function(){
		curPage = 1;
		getSourceUser();
		return false;
	});
	$('#queryUserName').autocomplete($('#initPath').val() + '/EmployeeController.do?method=getObjectQuery&queryColumns=objId,name', {
		extraParams:{},//查询条件  例如    {objId:111}
		matchColumn:'name,shortSpellName',//作为查询显示, 被选中之后匹配的列
		formatItem: function(data, i, total) {
			return data.name;
		},
		formatMatch: function(data, i, total) {
			return data.name;
		},
		formatResult: function(data) {
			return data.name;
		}
	}).result(function(event,data,formatted){
		if(data){
			$("#queryUserName").val(data.name);//回填名称
			$("#queryUserID").val(data.objId);//回填id
		}
	});   
})

</script>
<style>
.form3Pa .framePaCon{float:left; min-height:380px;width:44%; border:0px;margin-left:3px}
.form3Pa .framePaCon select {width:98%; height:345px;align:center;}
.form3Pa .framePaCon2 {width:9%; float:left;min-height:350px; text-align:center; background:#999; position:relative;}
.form3Pa .framePaCon2 div {margin-top:150px;} 
.form3Pa .framePaConAdd {width:45%; float:left;min-height:350px; text-align:left;  position:relative;}
</style>
<div class="conSearch">
    	<ul class="highclassSearch" name="addChannelRole">
    	<li class="conOperation">
    		管理该角色下的用户请点击：<button id="addUser"><span>管理用户</span></button>
    	</li>
    	</ul>
    	<form  id="queryUserForm">
    	<ul class="hidden" name="addChannelRole">
			<li class="conOperation">
				<label>姓名或拼音缩写：</label>
				<input id="queryUserName" style="width:100px" class="sysicon " value=""/>
				<input id="queryUserID" type="hidden"/>
    			<button id="querySourceUser"><span><spring:message code="globe.query"/></span></button>
    			<button id="closeUser"><span>关闭</span></button>
    		</li>
		</ul>
    	</form>
</div>

<div class="partContainers">
<input type="hidden" id="checkStatus" value="${param.checkStatus}"/>
	<div class="formLayout form3Pa">
		<ul>
			<li class="framePaCon hidden" name="addChannelRole">
				<select size="18" id="toSelect" multiple="multiple"><option>数据正在读取中...</option></select>
				<br/>
				<div class=" paging_full_numbers" id="messagePaginate">
				<span class="previous paginate_button" id="trunPage_pre">上一页</span>
				<span class="previous" id="curPage">1/1</span>
				<span class="next paginate_button" id="trunPage_next">下一页</span>
				</div>
			</li>
			<li class="framePaCon2 hidden" name="addChannelRole">
				<div>
					<button id="addRoleUser"><span>添加&nbsp;&gt;</span></button>
					<br/>
					<button id="deleteRoleUser"><span>&lt;&nbsp;删除</span></button>
				</div>
			</li>
			<li class="framePaCon">
				<select size="20" id="alreadySelect" multiple="multiple"></select>
			</li>
			<li class="framePaConAdd" name="addChannelRole">
			<span>按Ctrl键可以选中多个</span><br/>
				<button id="" type="button" tabindex="19"><span>通知</span></button>
				<br/>
				<button id="deleteRoleUser" type="button" tabindex="19"><span>删除</span></button>
			</li>
		</ul>
  	</div>
</div>
