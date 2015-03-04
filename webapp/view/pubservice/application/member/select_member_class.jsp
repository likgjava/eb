<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_selectMemberClassDialogID"  value="<c:out value="${param.dialogId}"/>"/>
<div class="conSearch">
	<form id="selectMemberClassSearchForm" >
		<input type="hidden" id="Hname" value="${param.Hname}"/>
		<input type="hidden" id="Hid" value="${param.Hid}"/>
		<ul>
	 		<li>
	     		<label>会员级别名称：</label>
	     		<input type="text" name="memberClassName" id="memberClassName">
	     		<input type="hidden" name="memberClassName_op" value="like">
	 		</li>
			<li class="operationBtnDiv">
				<button type="button" id="selectMemberClassQuery"><span>查询</span></button>
 	 		</li>
   		</ul>
	</form>
</div>

<table class="frontTableList" id="memberClassList">
	<thead>
		<tr>
			<th class="center">会员级别图片</th>
			<th class="omission" omiLength="20">会员级别名称</th>
			<th class="center">会员级别</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<div class="conOperation">
	<button id="selectMemberClassClose" type="button"  class="largeBtn" ><span><spring:message code="globe.close"/></span></button>
	<button id="selectMemberClassClear" type="button"  class="largeBtn" ><span>清除</span></button>
</div>

<script>
var SelectMemberClassList={};

$(document).ready(function(){
	SelectMemberClassList.oTable = $('#memberClassList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'memberClassPic,memberClassName,memberClassNum',
		'alias' : 'memberClassPic,memberClassName,memberClassNumCN',
		'hiddenColumns':'',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			SelectMemberClassList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=memberClassPic]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["memberClassPic"]+'" CURSOR="hand" width="60px" height="60px"/>');
			
			$(nRow).append("<td class='operation'><a name='selectMemberClass' href='javascript:void(0);'>选择</a></td>").find('a[name=selectMemberClass]').click(function(){
				$("#"+$("#Hname").val()).val(aData.memberClassName);
				$("#"+$("#Hid").val()).val(aData.objId);
				$("#selectMemberClassClose").click();
			});
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/MemberClassController.do?method=list",
		"params":{},
		'searchZone':'selectMemberClassSearchForm'
	});
	
	//查询
	$("#selectMemberClassQuery").click(function(){
		SelectMemberClassList.oTable.fnDraw();
	})
	
	//关闭
	$("#selectMemberClassClose").click(function(){
		if($("#_selectMemberClassDialogID").val() != ""){
			$(document.getElementById($("#_selectMemberClassDialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	})
	
	//清空
	$("#selectMemberClassClear").click(function(){
		$("#"+$("#Hname").val()).val('');
		$("#"+$("#Hid").val()).val('');
		$("#selectMemberClassClose").click();
	})
});
</script>
