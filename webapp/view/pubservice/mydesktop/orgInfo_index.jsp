<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--用户登录详情、操作列表 开始-->
<div class="gridBox">
    <div class="grid16_12">
      <div class="agencyBox">
        <input type="hidden" id="orgInfoId" value="${orgInfo.objId}" />
        <input type="hidden" id="isRoleBuyer" value="${isRoleBuyer}" />
        <h4>${orgInfo.orgName}<c:if test="${departmentName != null}">-${departmentName}</c:if><em>[<c:choose><c:when test="${member==null}">非会员</c:when><c:otherwise>${member.memberClass.memberClassNumCN}</c:otherwise></c:choose>]</em></h4>
        <p class="userPic"><img width="120px;" height="120px;" src="AttachmentController.do?method=showImg&objId=${orgInfo.logo}" /></p>
        <div  class="userDetails">
          <p>
            <label>认证状态：</label><span>${orgInfo.useStatusCN }</span>　
            <label>信息状态：</label>
            <span>基本信息[<c:choose><c:when test="${currSuppStatus=='00' || currBuyStatus=='00'}"><a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/ExOrgInfoController.do?method=toEntBaseInfo');return false;">不完整</a></c:when><c:otherwise>完整</c:otherwise></c:choose>]</span>&nbsp;
            <span>财务信息[<c:choose><c:when test="${fn:length(qC01)<14}"><a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/ExOrgInfoController.do?method=toOrgFinanceInfoView&qualificationCode=C01&urlType=self');return false;">不完整</a></c:when><c:otherwise>完整</c:otherwise></c:choose>]</span>&nbsp;
            <span>法务信息[<c:choose><c:when test="${fn:length(qC02)<3}"><a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/ExOrgInfoController.do?method=toOrgLegalInfo&qualityClassCode=C02&urlType=self');return false;">不完整</a></c:when><c:otherwise>完整</c:otherwise></c:choose>]</span>
          </p>
          <div class="hotLayer">
            <ul>
              <li>
                <a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/OrgQualityController.do?method=toOrgQualityManageView');return false;">认证资质(<em>${orgStatisticsInfo.Q_N}</em>)</a>
                <a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/SuccessCaseController.do');return false;">成功案例(<em>${orgStatisticsInfo.S_N}</em>)</a>
                <a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/OrgInfoController.do?method=listContact');return false;">已有联系人(<em>${orgStatisticsInfo.E_N}</em>)</a>
              </li>
              <li>
                <a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/EvaluateController.do?method=toMyEvaluate');return false;">客户评价次数(<em>${orgStatisticsInfo.EV_N}</em>)</a>
                <a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/EvaluateController.do?method=toMyEvaluate');return false;">客户评价总分(<em><c:if var="hasEval" test="${orgStatisticsInfo.EV_N > 0}">${orgStatisticsInfo.EVS_N / orgStatisticsInfo.EV_N}</c:if><c:if test="${!hasEval}">--</c:if></em>)</a>
                <a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/EvaluateController.do?method=toMyEvaluate');return false;">客户满意率(<em><c:if test="${hasEval}">${orgStatisticsInfo.GEV_N / orgStatisticsInfo.EV_N}</c:if><c:if test="${!hasEval}">--</c:if></em>)</a>
              </li>
              <li>
                <a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/view/pubservice/application/message/complain_user_list.jsp');return false;">被投诉次数(<em>${orgStatisticsInfo.TS_N}</em>)</a>
                <a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/view/pubservice/application/message/complain_user_list.jsp');return false;">被举报次数(<em>${orgStatisticsInfo.JB_N}</em>)</a>
                <a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/IllegalRecController.do?method=toIllegalRecOrgList');return false;">违规记录数(<em>${orgStatisticsInfo.I_N}</em>)</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="grid16_4 omega">
      <div class="operationList">
        <ul>
          <li><a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/ExOrgInfoController.do?method=toEntBaseInfo');return false;" class="create">修改基本信息</a></li>
          <li><a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/ExOrgInfoController.do?method=toOrgFinanceInfoView&qualificationCode=C01&urlType=self');return false;" class="create">修改财务信息</a></li>
          <li><a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/ExOrgInfoController.do?method=toOrgLegalInfo&qualityClassCode=C02&urlType=self');return false;" class="create">修改法务信息</a></li>
        </ul>
      </div>
    </div>
</div>
<!--用户登录详情、操作列表 结束-->
<!--企业统计概况、短信通 开始-->
<div class="gridBox">
    <div class="grid16_12">
      <div id="company" class="cols">
	    <h2>企业统计概况 截止：<fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd"/></h2>
	    <ul class="companyList">
		  <li><a class="ask">收藏次数</a> <strong id="favoriteNum"><fmt:formatNumber value="${orgStatisticsInfoByTime.BF_N + orgStatisticsInfoByTime.SF_N}" pattern="#,###" /></strong></li>
		  <li><a class="ask">关注数量</a> <strong id="concernNum"><fmt:formatNumber value="${orgStatisticsInfoByTime.C_N}" pattern="#,###" /></strong></li>
		  <li><a class="ask">销售金额</a> <strong id="saleAmount">￥<fmt:formatNumber value="${orgStatisticsInfoByTime.S_N}" pattern="#,##0.00#" /></strong></li>
		  <li><a class="ask">采购金额</a> <strong id="buyAmount">￥<fmt:formatNumber value="${orgStatisticsInfoByTime.B_N}" pattern="#,##0.00#" /></strong></li>
	    </ul>
	    <div class="pageBox">
	      <a href="javascript:void(0);" onclick="OrgInfoIndex.changeTime(this,36500);" class="selected" title="查看全部统计情况">全部</a>
	      <a href="javascript:void(0);" onclick="OrgInfoIndex.changeTime(this,7);" title="查看上周统计情况">上周</a>
	      <a href="javascript:void(0);" onclick="OrgInfoIndex.changeTime(this,30);" title="查看上月统计情况">上月</a>
	      <a href="javascript:void(0);" onclick="OrgInfoIndex.changeTime(this,90);" title="查看上季统计情况">上季</a>
	      <a href="javascript:void(0);" onclick="OrgInfoIndex.changeTime(this,365);" title="查看上年统计情况">上年</a>
	    </div>
      </div>
    </div>
    <div class="grid16_4 omega">
    	<c:if var="hasServiceBase" test="${serviceBase != null}">
		<div class="smsBox">
			<h3 title="${serviceBase.serviceName}"><c:choose><c:when test="${fn:length(serviceBase.serviceName)> 4}">${fn:substring(serviceBase.serviceName,0,3)}…</c:when><c:otherwise>${serviceBase.serviceName}</c:otherwise></c:choose></h3>
			<span class="monthly">￥<em><fmt:formatNumber value="${serviceBase.servicePrice}" pattern="#,##0.00#" /></em>/${serviceBase.serviceUnitCN}</span>
			<p><c:choose><c:when test="${fn:length(serviceBase.serviceDesc)> 51}">${fn:substring(serviceBase.serviceDesc,0,50)}…</c:when><c:otherwise>${serviceBase.serviceDesc}</c:otherwise></c:choose></p>
			<span class="subscribe"><a href="javascript:void(0);" onclick="OrgInfoIndex.loadView('/ServiceBaseController.do?method=toServiceSubscribeForm&objId=${serviceBase.objId}');return false;">订阅</a></span>
		</div>
		</c:if>
		<c:if test="${!hasServiceBase}">
		<div class="smsBox"><h3>暂无可订阅服务</h3></div>
		</c:if>
    </div>
</div>
<!--企业统计概况、短信通 结束-->
<!--我的推荐人开始-->
<div class="gridBox hidden" id="myPromoterDiv">
    <div class="cols">
		<h2>我的推广人</h2>
		<div id="promoterInfo"></div>
	</div>
</div>
<!--我的推荐人结束-->
<!--子公司销售统计 开始-->
<c:if test="${fn:length(subCompnyStatInfoList)> 0}">
<div class="gridBox">
    <div class="cols">
      <h2>子公司机构信息</h2>
      <div id="table">
        <table class="tableList">
          <thead>
            <tr>
              <th>机构名称</th><th>认证资质数</th><th>成功案例数</th><th>客户评价次数</th><th>客户评价总分</th><th>被投诉次数</th><th>被举报次数</th><th>违规记录数</th>
            </tr>
            </thead>
            <tbody>
              <c:forEach var="objs" items="${subCompnyStatInfoList}">
              <tr>
                <td>${objs[1] }</td>
                <td class="center">${objs[2] }</td>
                <td class="center">${objs[3] }</td>
                <td class="center">${objs[4] }</td>
                <td class="center">${objs[5] }</td>
                <td class="center">${objs[6] }</td>
                <td class="center">${objs[7] }</td>
                <td class="center">${objs[8] }</td>
              </tr>
              </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
</div>
</c:if>
<!--子公司销售统计 结束-->
  
<script>
var OrgInfoIndex = {};

//查看项目
OrgInfoIndex.showProject = function(projectId, ebuyMethod){
	if(ebuyMethod == '05'){
		window.open($("#initPath").val()+'/TalkProjectController.do?method=toTalkProjectDetailView&userType=buyer&objId='+projectId);
	}else{
		window.open($("#initPath").val()+'/BargainProjectController.do?method=toProjectView&userType=buyer&projectId='+projectId);
	}
}

//更换时间段
OrgInfoIndex.changeTime = function(domE,days){
	$(domE).siblings().removeClass('selected');
	$(domE).addClass('selected');
	$.getJSON($('#initPath').val()+'/ModelIndexController.do?method=getOrgStatInfoByTime',{'orgInfoId':$('#orgInfoId').val(), 'days':days}, function(json){
		$('#favoriteNum').text(Number(json.BF_N)+Number(json.SF_N));
		$('#concernNum').text(json.C_N);
		$('#saleAmount').text(formatAmount(json.S_N,2));
		$('#buyAmount').text(formatAmount(json.B_N,2));
	});
}

//在新的页面打开
OrgInfoIndex.openView = function(url){
	window.open($("#initPath").val()+url);
}

//在div[id=grid]中加载页面
OrgInfoIndex.loadView = function(url){
	$('#conBody').loadPage($('#initPath').val()+url);
}

$(document).ready(function(){	
	//如果当前机构是采购人，则显示推广信息
	if("true" == $('#isRoleBuyer').val()){
		$('#myPromoterDiv').show();
		$("#promoterInfo").loadPage("PromoterController.do?method=toPromoterBuyerRecord");
	}
});
</script>
