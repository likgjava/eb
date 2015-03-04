<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
#serviceBaseInfo .serviceImg {border: 1px solid #D5D5D5; float: left; margin-right: 5px;}
#serviceBaseInfo ul {margin-bottom: 10px;}
</style>
<input type="hidden" id="serviceBaseId" value="${serviceBase.objId}" />
<input type="hidden" id="viewType" value="${viewType}" />
<form:form id="serviceBaseDetailForm" method="post" modelAttribute="serviceBase">
	<div id="serviceBaseInfo" class="formLayout form2Pa">
		<div class="serviceImg">
			<img src="<c:url value="AttachmentController.do?method=showImg&objId=${serviceBase.servicePic}" />" width="160px" height="160px">
		</div>
		<ul>
			<li class="fullLine"><label>服务名称：</label> <span>${serviceBase.serviceName}</span></li>
			<li class="fullLine"><label>服务费用：</label> <span>￥<fmt:formatNumber value="${serviceBase.servicePrice}" pattern="#,##0.00#" />/${serviceBase.serviceUnitCN}</span></li>
			<li class="fullLine"><label>前置服务：</label><span>${serviceBase.servicePreposition.serviceName}</span></li>
			<li class="fullLine"><label>外部链接：</label> <span><a href="${serviceBase.serviceLink}" target="_blank">${serviceBase.serviceLink}</a></span></li>
			<li class="fullLine"><label>协议外部链接：</label> <span><a href="${serviceBase.serviceAgreementLink}" target="_blank">${serviceBase.serviceAgreementLink}</a></span></li>
			<li class="fullLine">
				<label>备注信息：</label>
				<span><c:choose><c:when test="${serviceBase.isSinglePurchase=='1'}">可以单独购买</c:when><c:otherwise>不可单独购买</c:otherwise></c:choose></span>&nbsp;&nbsp;
				<span><c:choose><c:when test="${serviceBase.isRecommendation=='1'}">是推荐服务</c:when><c:otherwise>不是推荐服务</c:otherwise></c:choose></span>
			</li>
	  	</ul>
	  	<ul>
			<li class="fullLine"><label>服务描述：</label><p>${serviceBase.serviceDesc}</p></li>
	  	</ul>
	</div>
</form:form>

<!-- Tab页 -->
<c:if test="${viewType=='allInfo'}">
<div id="epsTabs">
	<ul>
		<li>
			<a href="#serviceChargingInfoList" id = "tabs_toServiceCharging" class="refreshData"><span>服务计费</span></a>
		</li>
		<li>
			<a href="#serviceGroupInfoList" id = "tabs_toServiceGroup" class="refreshData"><span>服务组合</span></a>
		</li>
	</ul>
	<!-- 服务计费列表 -->
	<div id="serviceChargingInfoList">
		<table class="frontTableList" id="serviceChargingList">
		<thead>
			<tr>
				<th>会员级别</th>
				<th class="r">时长（${serviceBase.serviceUnitCN}）</th>
				<th class="money">金额（元）</th>
				<th class="r">折扣率（%）</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		</table>
	</div>
	<!-- 服务组合列表 -->
	<div id="serviceGroupInfoList">
		<table class="frontTableList" id="serviceGroupList">
		<thead>
			<tr>
				<th>搭配服务名称</th>
				<th class="r">时长</th>
				<th class="hidden">金额单位</th>
				<th class="money">金额（元）</th>
				<th class="r">折扣率（%）</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		</table>
	</div>
</div>
</c:if>

<div class="conOperation">
	<button class="operationBtnDiv" type="button" id="close"><span>关闭</span></button>
</div>

<script>
/**
 * 服务详细信息页面
 * create by likg
 */
ServiceDetail = {};
ServiceDetail.oTableCharging;
ServiceDetail.oTableGroup;

//取得服务计费列表
ServiceDetail.getServiceChargingList = function() {
	if(null==ServiceDetail.oTableCharging){
		ServiceDetail.oTableCharging = $('#serviceChargingList').dataTable({
			'singleSelect' : true,
			'checkbox' : false,
			'queryColumns' : 'memberClass.memberClassName,duration,amount,discount',
			'hiddenColumns':'memberClass.objId,useStatus',
			'fnInitComplete' : function(oSettings) {
			},
			'fnDrawCallback' : function(oSettings) {
				ServiceDetail.oTableCharging.oSettings = oSettings;
			},
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				var memberClassName = aData["memberClass.memberClassName"];
				if(memberClassName.length > 25){
					memberClassName = memberClassName.substring(0,25)+"...";
				}
				$(nRow).find("td[name='memberClass.memberClassName']").empty().append('<a title="'+aData["memberClass.memberClassName"]+'" href="javascript:void(0);" onclick="ServiceDetail.showMemberClassDetail(\''+aData["memberClass.objId"]+'\')">'+memberClassName+'</a>');
				
				return nRow;
			},
			"params":{'useStatus':'02','useStatus_op':'!='},
			"sAjaxSource" : $('#initPath').val()+ "/ServiceChargingController.do?method=list&serviceBase.objId="+$("#serviceBaseId").val(),
			'searchZone':'serviceChargingSearchForm'
		});
	}else{
		ServiceDetail.oTableCharging.fnDraw();
	}
}

//取得服务组合列表
ServiceDetail.getServiceGroupList = function() {
	if(null==ServiceDetail.oTableGroup){
		ServiceDetail.oTableGroup = $('#serviceGroupList').dataTable({
			'singleSelect' : true,
			'checkbox' : false,
			'queryColumns' : 'appendService.serviceName,duration,appendService.serviceUnit,amount,discount',
			'alias' : 'appendService.serviceName,duration,appendService.serviceUnitCN,amount,discount',
			'hiddenColumns':'useStatus,appendService.objId',
			'fnInitComplete' : function(oSettings) {
			},
			'fnDrawCallback' : function(oSettings) {
				ServiceDetail.oTableGroup.oSettings = oSettings;
			},
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				return nRow;
			},
			"params":{'useStatus':'02','useStatus_op':'!='},
			"sAjaxSource" : $('#initPath').val()+ "/ServiceGroupController.do?method=list&mainService.objId="+$("#serviceBaseId").val(),
			'searchZone':'serviceGroupSearchForm'
		});
	}else{
		ServiceDetail.oTableGroup.fnDraw();
	}
}

//查看会员级别详情
ServiceDetail.showMemberClassDetail = function(memberClassId){
	$.epsDialog({
		id: 'memberClassDetailDialogId',
		title:"会员级别详细信息",
		width: 800,
		height: 500,
		url:$('#initPath').val()+'/MemberClassController.do?method=toShowView&dialogId=memberClassDetailDialogId&objId='+memberClassId
	});
}

$(document).ready(function(){
	var viewType = $('#viewType').val();
	if(viewType == 'allInfo'){ //详细详情视图
		var $tabs = $('#epsTabs').tabs({}); 
		
		//加载服务计费信息列表
		ServiceDetail.getServiceChargingList();
		
		//切换列表
		$('#tabs_toServiceCharging').click(function() {
			ServiceDetail.getServiceChargingList();
		});
		$('#tabs_toServiceGroup').click(function() {
			ServiceDetail.getServiceGroupList();
		});
	}
	
	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
});
</script>