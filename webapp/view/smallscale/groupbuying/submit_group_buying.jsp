<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="bd">
	<div class="bd_top"></div>
	<!--主体部分start-->
	<div class="bd_context">
		<!--导航 position-->
		<div class="bd_title">
			<div class="bd_t_name" style="font-size: 17px;">发布团购信息</div>
			<div class="bd_t_guide">
				<div class="bd_t_pos pos_1">发布团购信息</div>
				<div class="bd_t_pos_spc"></div>
				<div class="bd_t_pos pos_2_on">发布成功</div>
			</div>
		</div>
		<!--内容 开始-->
		<div class="bd_main">
			<div class="bd_m_top"></div>
			<div class="bd_m_context">
				<div class="bd_offer_success_cls"></div>
				<div class="bd_offer_success">
					<c:if test="${param.useStatus=='00'}">
					<div class="bd_offer_s_status1">团购信息保存成功！
						<div id="submitDiv" class="bd_offer_s_btn">
							<a href="javascript:void(0);" id="submitProject" onclick="submitGroupBuying('${param.objId}');" class="bd_offer_s_goon">发布该团购信息</a>&nbsp;&nbsp;
						</div>
						<div id="submittingDiv" class="bd_offer_s_btn hidden">
							<img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/load.gif" />正在发布团购信息...
						</div>
					</div>
					<div class="bd_offer_s_others">您保存的尚未提交，点击上面的按钮进行发布。</div>
					</c:if>
					<c:if test="${param.useStatus=='01'}">
						<div class="bd_offer_s_status1">团购信息发布成功！</div>
					</c:if>
					<div class="bd_offer_s_btn">
						<a href="<%=request.getContextPath()%>/ModelIndexController.do?method=toDeskTopIndex" class="bd_offer_s_scan">进入您的商务室</a>
					</div>
				</div>
				<div class="bd_offer_success_cls_bottom"></div>
			</div>
			<div class="bd_m_bottom"></div>	
		</div>
		<!--内容 结束-->
	</div>
</div>

<script>
//发布团购信息
function submitGroupBuying(objId){
	if(window.confirm('确认发布团购信息吗?')) {
		$('#submitDiv').addClass('hidden');
		$('#submittingDiv').removeClass('hidden');
		$.getJSON($('#initPath').val() + "/GroupBuyingController.do?method=updateStatus&useStatus=01&objId="+objId, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			$('#sysContent').loadPage($('#initPath').val()+'/view/smallscale/groupbuying/submit_group_buying.jsp?useStatus=01');
		});
	}
}
</script>