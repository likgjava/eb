<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/index.css"/>

<script type="text/javascript">
	$(document).ready(function(){
		var templateId = $('#templateId').val();
		var userName = $('#userName').val();//用户名称
		$('#voteTemplateObjId').val(templateId);
		
		//加载主题联系人信息
		$.getJSON($('#initPath').val()+"/VoteTemplateShowController.do?method=pageFirstInitOnLoad",{"templateId":templateId,"operation":"showBrand"},function(json){
			if("manager" != userName){
				//评选组
				if(json.voteUnitGroupList != null && json.voteUnitGroupList.length>0){
					$.each(json.voteUnitGroupList,function(index,obj){
						var strHtml1 = '<option value="'+obj.objId+'">'+obj.groupName+'</option>';
						$('#voteUnitGroupObjId').append(strHtml1);			
					});	
				}

				//当前供应商的品牌列表
				if(json.showGoodsBrandList != null && json.showGoodsBrandList.length>0){
					$.each(json.showGoodsBrandList,function(index,obj){
						var strHtml1 = '<option value="'+obj.objId+'">'+obj.brandName+'</option>';
						$('#attender').append(strHtml1);			
					});	
				}else{
					$('#attenderDiv').empty().append("<label>品牌列表：</label>你还没有可维护的有效品牌！请添加你的品牌并提交等待审核！");
					$("#submitBtn").attr("disabled","disabled");
				}
				$('#signOnlinDiv').removeClass("hidden");	
			}else{
				$('#signOnlinDiv2').removeClass("hidden");				
			}
		});
	});

	//提交
	$("#submitBtn").click(function(){
		$("#submitBtn").attr("disabled","disabled");
		if(window.confirm('是否确定在线报名？')){
			var url = $('#initPath').val()+"/VoteAssessedThingController.do?method=signOnLineAssessedThing";
			$("#voteAssessedThingForm").ajaxSubmit({
				url:url,
				dataType:"json",
				success:function(json){
						if(json.result == 'true'){
							alert("在线报名成功！请等待审核！");
							vinoIndex.toVinoIndex();
						}
						if(json.result == 'false'){
							alert("此品牌已经报名参加！");
							$("#submitBtn").attr("disabled",false);
						}
					},
				error:function(msg){
						alert("在线报名失败,请确认你的品牌是有效的！");
						$("#submitBtn").attr("disabled",false);
						return;
					}
			});
	    }
	    else{
  			$("#submitBtn").attr("disabled",false);
  			return;
       	}
     });
</script>

<div class="hdjs_main">
	<div class="hdjs_main_left">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/hdjs_bg01.jpg" />
		<div id="showVoteResultTopNum">
			<%@ include file="/view/smallscale/vote/show_vote_result_topNum.jsp" %>
		</div>
	</div>
	<div class="hdjs_main_right">
		<h4>在线报名</h4>
		<h4>一、参选评选组</h4>
		<div id="signOnlinDiv" class="hidden formLayout form2Pa">
			<form name="voteAssessedThingForm" id="voteAssessedThingForm" method="post">
				<input type="hidden" name="voteTemplate.objId" id="voteTemplateObjId" />
				<input type="hidden" name="isRecommended" id="isRecommended" value="false"/>
				<input type="hidden" name="useStatus" id="useStatus" value="00"/>
				<ul>
					<li  class="fullLine">
						<label>参选评选组：</label>
						<select name="voteUnitGroup.objId" id="voteUnitGroupObjId">
						</select>
					
					</li>
					<li id="attenderDiv"  class="fullLine">
						<label>品牌列表：</label>
						<select name="attender" id="attender">
						</select>
					</li>
				</ul>
			</form>
			<div class="conOperation" style="text-align: center;">
				<button type="button" id="submitBtn"><span><spring:message code="globe.submit"/></span></button>
			</div>
		</div>
		<div id="signOnlinDiv2" class="hidden">
			<h4>manager管理员,你好！</h4>
		</div>
	</div>
</div>
