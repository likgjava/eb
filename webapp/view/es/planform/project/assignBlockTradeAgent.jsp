<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
<!--
.styleA{
cursor: hand;
text-decoration: underline;
color: blue;
}
.styleC{
text-align: center;
}
.styleL{
text-align: left;
}
.styleR{
text-align: right;
}
.formLayout h5{ position:relative;}
.formLayout h5 .BtnDiv{ position:absolute; right:10px; top:2px;}
-->
</style>
	<form id="pageSearchZone" name="pageSearchZone" >
		<!-- 查询隐藏条件 -->
		<!-- 当前用户类型 -->
		<!--总数据 -->
		<input type="hidden" id="totalRowNum" name="totalRowNum" value="${PAGERESULT.totalRowNum }"/>
		<!--总页数 -->
		<input type="hidden" id="totalPageNum" name="totalPageNum" value="${PAGERESULT.totalPageNum }"/>
		<!--当前页 -->
		<input type="hidden" id="page" name="page" value="${PAGERESULT.pageNum }"/>
		<!--当前页 -->
		<input type="hidden" id="newPage" name="newPage" value="${PAGERESULT.pageNum }"/>
		<!--每页显示条数 -->
		<input type="hidden" id="rp" name="rp" value="${PAGERESULT.pageSize }"/>
		<input type="hidden" id="previousPage" name="previousPage" value="${PAGERESULT.previousPage }"/>
		<input type="hidden" id="nextPage" name="nextPage" value="${PAGERESULT.nextPage }"/>
		<!--查询访问的请求地址 -->
		<input type="hidden" id="pageSearchUrl" name="pageSearchUrl" value="${pageSearchUrl }"/>
	</form>
	<div class="formLayout">
	<h5>招标中心列表
	<div class="BtnDiv styleR">
		<a href="#" id="newAgentId" class="sysicon siAdd">新增</a>
		<a href="#" id="delMoreAgentId" class="sysicon siCancel">批量删除</a>
	</div>
	</h5>
		<table class="tableList">
			<%--<caption>招标中心列表
				
				<div class="functionBtnDiv">
				<button id="newAgentId">
						<span>新增</span>
				</button>
				<button id="delMoreAgentId">
						<span class="">批量删除</span>
				</button>
				</div>
			</caption>--%>
		<tr>
			<td style="width: 25px;text-align: center"><input type="checkbox" id="checkAll"></td>
			<td class="styleC">名称</td>
			<td class="styleC">操作</td>
		</tr>
		<c:forEach items="${PAGERESULT.data}" var="orgInfo" varStatus="i">
			<tr>
			<td style="width: 25px;text-align: center"><input type="checkbox" name="orgCheck" value="${orgInfo.objId}"></td>
			<td class="styleL">${orgInfo.orgName}</td>
			<td class="functionBtnDiv styleC">
			<a href="#" onclick="agentList.delAgent('${orgInfo.objId}')" class="styleA">删除</a></td>	   		 
	       </tr>
	       <input type="hidden" name="orgInfoObjId" value="${orgInfo.objId}">
		</c:forEach>
		</table>
		<%@ include file="/view/es/common/pageDirection.jsp" %>
	  </div>
	  <div id="testPage">
	  </div>
<script language="javascript">
	var agentList={};

$(document).ready(function(){

	$('#delMoreAgentId').click(function(){
		var orgInfo = new Array();
		$('input[name="orgCheck"]:checked').each(function(){
		    orgInfo.push($(this).val());
		 });
		 var orgInfoIds = orgInfo.toString();
		 if (orgInfoIds==null||orgInfoIds==""||orgInfoIds==undefined) {
			alert('至少选择一个招标中心!');
		 }else{
			 agentList.delAgent(orgInfoIds);
		 }
	});
	
	agentList.delAgent = function(agentId){
		if(window.confirm('确定要删除招标中心吗？')){
			$.getJSON($('#initPath').val()+'/UserApiController.do?method=removeAgentForBlockTrade',{'orgInfoIds':agentId},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/UserApiController.do?method=toBlockTradeAgent');
			});
		}
	}

	$('#newAgentId').click(function(){//新增操作
		$.epsDialog({
	        title:"新增招标中心",
	        url:$("#initPath").val()+"/view/es/planform/project/agentsForm.jsp",
	        width: 500,
	        height: 100,
	        isReload: false,
	        onClose: function(){
				planTemplateTask.refresh($('#projectTaskId').val())
	       	}
		});
	});

	$('#checkAll').toggle(function(){
        $("input[name=orgCheck]").each(function(){
            $(this).attr('checked',true);
        });
    },function(){
        $("input[name=orgCheck]").each(function(){
            $(this).attr('checked',false);
        });
    });
	
});
	
</script>