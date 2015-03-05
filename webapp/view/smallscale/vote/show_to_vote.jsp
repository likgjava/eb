<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link href="view/resource/common/plug_in.css" type="text/css" rel="stylesheet"/>
<link href="view/resource/skin/skin07/css/main.css" type="text/css" rel="stylesheet"/>
<link href="view/smallscale/tempvote/hx.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
	var voteJsp = {};
	voteJsp.voteSubmit = function(){
		var ck = null;
		var result = "";
		var name = "";
		var pointerValidCount = $('#pointerValidCount').val();
		for(var index=1;index<(pointerValidCount+1);index++){
			name = "option_"+index;
		   ck = document.getElementsByName(name);
		   for ( var i = 0; i < ck.length; i++) {
			   if (ck[i].checked) {
				   result += $('#'+name).val()+"#" + ck[i].value + ";;";
			   }
		   }
		}
		$('#result').attr('value',result);

		var templateId = $('#templateId2').val();
		var voteAssessedThingId = $('#voteAssessedThingId2').val();
		var isSingleVote = $('#isSingleVote2').val();

		if(!$('#voteForm').valid()){alert('请正确填写表单!');return;}

		$.getJSON($('#initPath').val()+'/VoteTemplateShowController.do?method=voteGrade', formToJsonObject('voteForm'), function(json){		
			if(json.failure){alert(json.result); return;}
			if(json.result=="success"){
				alert("投票成功!谢谢你的参与!");
				window.location.href = $('#initPath').val()+"/VoteTemplateShowController.do?method=showVoteIndex&templateCode="+$('#templateObjCode').val();
			}
		});
	};
</script>

<div style="width:400px;margin:0 auto;text-align:center;margin-top:15px;">
	<span style="display: block; text-align: center;float:left;margin-right:20px;">
		<img src="AttachmentController.do?method=showImg&objId=${voteAssessedThing.picture }" height="146" width="187" /><br />
	</span>
	<span style="display: block; text-align: left;float:left;line-height:200%;font-size:14px;">
		品牌名称：${voteAssessedThing.attenderName }<br>
		所属评选组：${voteUnitGroup.groupName }<br>
		<c:if test="${orgInfo.objId != null}">所属机构：
			<a href="javascript:void(0);" onclick="template_unfurl.showOrgDetail('${orgInfo.objId }','${orgInfo.buyerId }','${orgInfo.supplierId }')">
				${orgInfo.orgName}
			</a>
		</c:if>
	</span>
	<div style="clear:both;"></div>
</div>
<div id="sysContainer" style="width:1000px;text-align:center;">
	<div id="sysContent" class="sysContent" >
		<div id="contentMain" class="contentMain">
			<div id="conBody" class="conBody hxConBody" >
				<table cellspacing="0"  cellpadding="0" width="100%;" align="center" border="1" class="parentTable">
					<tr>
						<th colspan="2" rowspan="2">评价指标</th>
						<th colspan="5" class="t1"><h1>满意度调查在线问卷</h1></th>
					</tr>
					<tr class="th2">
						<td>非常满意（100分）</td>
						<td>满意（85分）</td>
						<td>一般（75分）</td>
						<td>不满意（65分）</td>
						<td>很差（45分）</td>
					</tr>
					<c:set var="pointerValidCount" value="0"></c:set>
					<c:set var="count" value="1"></c:set>
					<c:set var="prePointer" value="prePointer"></c:set>
					<c:forEach var="votePointer" items="${votePointerList}" varStatus="status">
						<c:set var="pointerValidCount" value="${pointerValidCount + 1}"></c:set>
						<tr>
							<c:if test="${votePointer.pointerFactor == 0 }">
								<th id="CODE_${votePointer.pointerCode}" rowspan="">${votePointer.pointerName }
									<c:if test="${count != 1 && prePointer != ''}">
										<input type="hidden" id="HIDDEN_${prePointer}" value="${count}"/>
										<script>$('#CODE_${prePointer}').attr("rowspan",${count});</script>
									</c:if>
								</th>
								<th>
									<input name="option_${status.count }" value="0" type="hidden" checked="checked"/>
									<input type="hidden" id="option_${status.count }" value="${votePointer.objId }" />
								</th>
								
								<c:set var="count" value="1"></c:set>
								<c:set var="prePointer" value="${votePointer.pointerCode}"></c:set>
							</c:if>
							<c:if test="${votePointer.pointerFactor != 0 }">
								<c:if test="${fn:startsWith(votePointer.pointerCode, prePointer)}">
									<c:set var="count" value="${count + 1}"></c:set>
								</c:if>
								<th <c:if test="${count == 1}">colspan="2"</c:if>>${votePointer.pointerName }</th>
								<td><input name="option_${status.count }" value="100" type="radio" /><input type="hidden" id="option_${status.count }" value="${votePointer.objId }" /></td>
								<td><input name="option_${status.count }" value="85" type="radio" /></td>
								<td><input name="option_${status.count }" value="75"  checked="checked"  type="radio" /></td>
								<td><input name="option_${status.count }" value="65" type="radio" /></td>
								<td><input name="option_${status.count }" value="45" type="radio" /></td>
							</c:if>										
						</tr>
					</c:forEach>
				</table>
				<input type="hidden" id="HIDDEN_${prePointer}" value="${count}"/>
				<script>$('#CODE_${prePointer}').attr("rowspan",${count});</script>
				<input type="hidden" id="pointerValidCount" value="${pointerValidCount}"/>
	            <input type="hidden" id="templateObjCode" value="${voteTemplate.templateCode}"/>
	            <br>
	            <div class="formLayout form2Pa">
					<form id="voteForm" method="post">
						<input type="hidden" name="voteTemplate.objId" id="voteTemplate.objId" value="${voteTemplate.objId }" />
						<input type="hidden" name="voteAssessedThing.objId" id="voteAssessedThing.objId" value="${voteAssessedThingId}" />
						<input type="hidden" name="isSingleVote" id="isSingleVote" value="${isSingleVote}" />
						<input type="hidden" name="pointerFactor" id="pointerFactor" value="${expertVoteFactor}" />
						<input type="hidden" name="result" id="result" value="" />
						
				     	<ul>
					     	<li>
					     		<label>投票人名称：</label>
									<input type="text" name="userName" id="userName" class="required" maxlength="50" size="50" value="${user.emp.name}"/>
				    	   			<span class="eleRequired">*</span>
				    	   			<span><input type="checkbox" name="isAnonymity" id="isAnonymity" value="true"/>匿名投票</span>
				    	    </li>
				    	    <li></li>
				     		<li class="formTextarea">
				   				<label>评论内容：</label>
				     			<textarea name="memo" id="memo"  style="width:640px; height: 118px" maxlength="500"></textarea><span class="eleRequired">最大250个字</span>
				     		</li>
						</ul>
						<div class="conOperation">
							<button type="button" id="voteSubmit" onclick="voteJsp.voteSubmit()"><span>投票提交</span></button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
