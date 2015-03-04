<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
var MemberClassList = {};

//操作
MemberClassList.view = function(memberClassId,viewName){
	if("showView" == viewName){
		$.epsDialog({
			title:"会员级别详细信息",
			width: 550,
			height: 300,
			url:$('#initPath').val()+'/MemberClassController.do?method=toShowView&objId='+memberClassId
		});
	}else if("updateView" == viewName){
		$('#conBody').loadPage($('#initPath').val()+'/MemberClassController.do?method=toCreateOrUpdate&objId='+memberClassId);
	}else if("deleteView" == viewName){
		if(confirm("确定删除该会员级别吗？")){
			$.getJSON($("#initPath").val()+"/MemberClassController.do?method=remove",{"objId":memberClassId},function(json){
				if(json.success){
					alert("删除成功！");
					MemberClassList.reload();//刷新列表
				}else{
					alert("操作失败！");
				}
			});
		}
	}
}

//重新加载列表数据
MemberClassList.reload = function(){
	MemberClassList.oTable.fnDraw();
}

$(document).ready(function(){
	//加载服务列表
	MemberClassList.oTable = $('#memberClassList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'memberClassPic,memberClassName,memberClassNum,minAge,minFee',
		'alias': 'memberClassPic,memberClassName,memberClassNumCN,minAge,minFee',
		'hiddenColumns' : 'maxAge,maxFee',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			MemberClassList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=memberClassPic]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["memberClassPic"]+'" CURSOR="hand" width="60px" height="60px"/>');
			$(nRow).find("td[name=minAge]").empty().append(aData.minAge+"~"+aData.maxAge+"");
			$(nRow).find("td[name=minFee]").empty().append("￥"+formatAmount(aData.minFee,2)+"~￥"+formatAmount(aData.maxFee,2)+"");
			//添加操作按钮
			var operStr = '<td class="operation">';
			operStr += '<a href="javascript:void(0);" title="查看详情" onclick="MemberClassList.view(\''+aData.objId + '\',\'showView\');return false;">查看</a>';
			operStr += '<a href="javascript:void(0);" title="修改级别" onclick="MemberClassList.view(\''+aData.objId + '\',\'updateView\');return false;">修改</a>';
			operStr += '<a href="javascript:void(0);" title="删除级别" onclick="MemberClassList.view(\''+aData.objId + '\',\'deleteView\');return false;">删除</a>';
			operStr += '</td>';
			$(nRow).append(operStr);
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/MemberClassController.do?method=list",
		'searchZone':'memberClassSearchForm'
	});
	
	//查询
	$("#query").click(function() {
		MemberClassList.reload();
	})
	
	//新增会员级别
	$('#addMemberClassBtn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/MemberClassController.do?method=toCreateOrUpdate');
	})
});
</script>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="memberClassSearchForm">
	<ul>
		<li>
			<label>会员级别名称：</label>
			<input type="text" name="memberClassName" id="memberClassName" value="">
			<input type="hidden" name="memberClassName_op" value="like">
		</li>
		<li class="operationBtnDiv">
			<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>

<div class="formTips attention">
	<ul>
		<li>
			新增会员级别请点击
			<span class="sysicon siAdd"><a id="addMemberClassBtn" href="javascript:void(0);"><strong>新增会员级别</strong></a></span>.
		</li>
	</ul>
</div>

<table class="frontTableList" id="memberClassList">
	<thead>
		<tr>
			<th class="center">会员级别图片</th>
			<th class="omission" omiLength="15">级别名称</th>
			<th class="center">级别</th>
			<th class="center">入会时长（月）</th>
			<th class="center">缴费金额（元）</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>