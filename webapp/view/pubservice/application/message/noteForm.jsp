<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formZone">
	<form id="noteForm" method="post">
		<input type="hidden" name="goodsId" id="goodsId" value="<c:out value="${param.goodsId}"/>"/>
		<input type="hidden" name="receiver" id="receiver" value="${param.receive}"/>
		<input type="hidden" name="isRead" id="isRead" value="false"/>
		<input type="hidden" name="isReply" id="isReply" value="false" />
		<input type="hidden" name="type" id="type" value="${param.type}"/>		
     	<div class="formLayout">
     		<ul>
     			<li class="formTextarea" >
     				<label for="content"><c:if test="${param.type == '00'}">我要留言</c:if><c:if test="${param.type == '01'}">我要咨询</c:if>:</label>
     				<textarea name="content" id="content"  style="width:350px; height: 80px" class="required" maxlength="100"></textarea><span class="eleRequired">*最大100个字</span>
     			</li>     			
     			<li class="fullLine">
     				<table>
     					<c:set var="faceStartCount" value="1"/>
     					<c:forEach begin="1" end="${faceTrNumCount/10+1}" >
     						<c:set var="faceEndCount" value="${faceStartCount+10-1}"/>
	     						<tr>
	     							<c:forEach items="${faceTags}" var="faceTag" begin="${faceStartCount-1}" end="${faceEndCount-1}">
	     								<td>
	     									<input type="radio" name="attFile" value="${faceTag}"/><img alt="" src="<%=request.getContextPath()%>/${defaultFaceTag }/${faceTag}" width="20px" height="20px"></img>
	     								</td>
	     								<c:set var="faceStartCount" value="${faceStartCount+1}"/>
	     							</c:forEach>
	     						</tr>
     					</c:forEach>
     				</table>
     			</li>
     			<li id="liAnnoy">
     				<label></label>
     				<span><input type="checkbox" name="isAnnoy" id="isAnnoy" value="true"/> 匿名留言</span>
     			</li>
     		</ul>
		</div>
	    <div class="conOperation">
	        <button id="noteSave" type="button" tabindex="18"><span>发表</span></button>
	    </div>
	</form>
</div>

<script>
var noteForm={};

$(document).ready(function(){
	if($('#type').val() == '01'){
		$('#isAnnoy').checked = true;
		$('#isAnnoy').value = false;
		$('#liAnnoy').hide();
	}	
});

//发表
$('#noteSave').click(function(){
	if(!$('#noteForm').valid()){alert('请正确填写表单!');return;}
	$.getJSON($('#initPath').val()+'/NoteController.do?method=noteSave', formToJsonObject('noteForm'), function(json){
		if(json.result){
			alert(json.result);
			//自动关闭		
			$('.epsDialogClose').trigger('click');			
		}
		if(json.failure)return;			
	});
});

//验证是否匿名
function validCheckbox(){	
	var annoys = document.getElementsByTagName("input");	
	for(i=0;i<annoys.length;i++){
		var annoy = annoys[i];		
		if(annoy.type == 'checkbox'){			
			if(!annoy.checked){		
				annoy.checked = true;				
				annoy.value = false;				
			}
		}
	}
}
</script>