<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa">
<input type="hidden" id="subProjectId_" value="${param.subProjId}">
	<h5><span>开标一览表设置</span></h5>
	<ul>
		<li><a onClick="insertRow()" class="linkButton"><span>新增开标表头</span></a></li>
	</ul>
	<table width="100%" border="0" class="tableList">
		<thead>
			<tr>
                <th width="30%">名称</th>
				<th width="10%">排序</th>
				<th width="10%">操作</th>
	  		</tr>
		</thead>
		<tbody id="genviewDefines">
		<c:forEach items="${genviewDefineList}" var="genviewDefine">
	   			<tr>
	   				<input type="hidden" name="objId_" value="${genviewDefine.objId}" />
	                <input type="hidden" name="factorId" value="${genviewDefine.factorId }" />
	                <input type="hidden" name="project.objId" value="${genviewDefine.project.objId }" />
                <td id='factorName'>
	                <input type="text" name='factorName'  maxlength='35' size='25' value="${genviewDefine.factorName }" />
	                <span style='color: red;' >*</span>
	            </td>
                <td id='showNo'>
	                 <input type="text" name='showNo'  maxlength='35' size='25' value="${genviewDefine.showNo }" />
                </td>
                <td class="center"><a class='abtn' id='remove' onClick='setGenviewDefine.liveButtonClick(this);'><span id='delete'>删除</span></a>
                    &nbsp;&nbsp;&nbsp;&nbsp;<a id='save' onClick='setGenviewDefine.liveButtonClick(this);' name='saveGenviewDefine'><span id='save'>保存</span></a>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<div class="conOperation">
   		<button id="finshSaveButton" type="button" tabindex="18"><span>完成</span></button>
   		<span><font color="red">(点击完成，方可进入下一步操作！)</font></span>
 	</div>
<script>
var setGenviewDefine ={}; 
//新增指标
function insertRow(){
	var html = "";
	html+="<tr><input type='hidden'  name='objId_' value='' /><input type='hidden' name='project.objId' value='' />";		
	html+="<input type='hidden'  name='factorId' value='' /><td id='factorName'><input name='factorName'  type='text' maxlength='35' size='35' /><span style='color: red;' size='1'>*</span></td>";		
	html += "<td id='showNo'><input type='text' name='showNo'  maxlength='35' size='25' value='' /></td>";
	html+="<td><a  id='remove' onClick='setGenviewDefine.liveButtonClick(this);'><span id='delete'>删除</span></a>";
	html+="&nbsp;&nbsp;&nbsp;&nbsp;<a id='save' onClick='setGenviewDefine.liveButtonClick(this);' name='saveGenviewDefine'><span id='save'>保存</span></a>";
	html += " </td></tr>";
	$("#genviewDefines").append(html);
}



setGenviewDefine.liveButtonClick = function(thisObj){
	var id = thisObj.id;
	var jsonObj = {};
	jsonObj.objId = $(thisObj).parent().parent().find('input[name=objId_]').val();
	jsonObj.projectId = $(thisObj).parent().parent().find('input[name=project.objId]').val();
	jsonObj.factorId = $(thisObj).parent().parent().find('input[name=factorId]').val();
	
	// 删除
	
	jsonObj.projectId = $("#subProjectId_").val();

	if("remove" == id){
		if(window.confirm('确定要删除吗？')){
		$(thisObj).parent().parent().remove();
	    	$.getJSON($('#initPath').val()+'/CongruousFactorController.do?method=deleteGenviewDefine',{objId:jsonObj.objId},function(json){
				alert('删除成功！');
			});
		}
	}else if("save" == id){
		var spanFlag = $(thisObj).find('span').attr('id');
		var factorNameVal = $(thisObj).parent().parent().find("td[id='factorName']").find("span").text();
		var showNoVal = $(thisObj).parent().parent().find("td[id='showNo']").find("span").text();
		if('update'==spanFlag){
			$(thisObj).parent().parent().find("td[id='factorName']").empty().append("<input name='factorName'  type='text' maxlength='35' size='35' value='"+factorNameVal+"' /><span style='color: red;' size='1'>*</span>");
			$(thisObj).parent().parent().find("td[id='showNo']").empty().append("<input type='text' name='showNo'  maxlength='35' size='25' value='"+showNoVal+"' />");
			$(thisObj).find('span').attr('id','save');	
			$(thisObj).find('span').text('保存');	
		}else if('save'==spanFlag){
			jsonObj.factorName = $(thisObj).parent().parent().find('input[name=factorName]').val();
			if(jsonObj.factorName==''){
				alert("名称不能为空！");
				return;
			}
			jsonObj.showNo = $(thisObj).parent().parent().find('input[name=showNo]').val();
			var re =  /^((?!0)\d{1,2}|100)$/;  
		 	if(!re.test(jsonObj.showNo)){
				alert("排序数据请输入1-100整数！");
				return;
			}
			    	$.getJSON($('#initPath').val()+'/CongruousFactorController.do?method=saveGenviewDefine',{objId:jsonObj.objId,projectId:jsonObj.projectId,factorId:jsonObj.factorId,factorName:jsonObj.factorName,showNo:jsonObj.showNo},function(json){
						alert('保存成功！');
						//回填数据
						$(thisObj).parent().parent().find('input[name=objId_]').val(json.genviewDefine.objId);
						$(thisObj).parent().parent().find('input[name=factorId]').val(json.genviewDefine.factorId);
						
						var no = $(thisObj).parent().parent().find('input[name=showNo]').val();
						$(thisObj).find('span[id=save]').text('修改').attr("id",'update');
						$(thisObj).parent().attr("align","center");
						$(thisObj).parent().prev().attr("align","center").attr("id","showNo").empty().append("<span>"+no+"</span>");
						$(thisObj).parent().prev().prev().attr("align","center").attr("id","factorName").empty().append("<span>"+jsonObj.factorName+"</span>");
						
					});
		}

	}
}

$(document).ready(function(){

  $("#finshSaveButton").click(function(){
		$.getJSON($('#initPath').val()+'/CongruousFactorController.do?method=saveFinishGenviewDefine',{projectId :$("#projectId").val()},function(json){
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			  }
			});
  })
	
  $("input[name=factorName]").each(function(i,n){
		if('总报价'==$(n).val()){
			var no = $(n).parent().parent().find('input[name=showNo]').val();
			$(n).parent().parent().empty().append("<td width='30%' align='center'><span>总报价</span></td><td width='10%' align='center'><span>"+no+"</span></td><td width='10%'><span/></td>");
		}else{
			var no = $(n).parent().parent().find('input[name=showNo]').val();
			$(n).parent().parent().find('span[id=save]').text('修改').attr("id",'update');
			$(n).parent().next().attr("align","center").attr("id","showNo").empty().append("<span>"+no+"</span>");
			$(n).parent().attr("align","center").attr("id","factorName").empty().append("<span>"+$(n).val()+"</span>");
			
		}
		

	})

	
})
</script>				