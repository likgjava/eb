<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa">
<input type="hidden" id="subProjectId_" value="${param.subProjId}">
	<h5><span>开标一览表查看</span></h5>
	<table width="100%" border="0" class="tableList">
		<thead>
			<tr>
                <th width="50%">名称</th>
				<th width="50%">排序</th>
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
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<script>
var setGenviewDefine ={}; 
//新增指标

$(document).ready(function(){

  $("input[name=factorName]").each(function(i,n){
		if('总报价'==$(n).val()){
			var no = $(n).parent().parent().find('input[name=showNo]').val();
			$(n).parent().parent().empty().append("<td width='30%' align='center'><span>总报价</span></td><td width='10%' align='center'><span>"+no+"</span></td>");
		}else{
			var no = $(n).parent().parent().find('input[name=showNo]').val();
			$(n).parent().parent().find('span[id=save]').text('修改').attr("id",'update');
			$(n).parent().next().attr("align","center").attr("id","showNo").empty().append("<span>"+no+"</span>");
			$(n).parent().attr("align","center").attr("id","factorName").empty().append("<span>"+$(n).val()+"</span>");
			
		}
		

	})

	
})
</script>				