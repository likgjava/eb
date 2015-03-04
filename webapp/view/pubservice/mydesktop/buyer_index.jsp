<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--用户登录详情、操作列表 开始-->
<div class="gridBox">
    <div class="grid16_12">
      <div class="userDetailsBox">
        <h3><em>${userName},</em>欢迎您！ <span class="lastLoginTime">上一次登录时间：${lastLoginTime}</span></h3>
        <h4>${orgInfo.orgName}<c:if test="${departmentName != null}">-${departmentName}</c:if><em>[<c:choose><c:when test="${member==null}">非会员</c:when><c:otherwise>${member.memberClass.memberClassNumCN}</c:otherwise></c:choose>]</em> <em>[<a href="javascript:void(0);" onclick="BuyerIndex.loadView('/ExOrgInfoController.do?method=toEntBaseInfo');return false;">修改企业信息</a>]</em></h4>
        <p class="userPic"><img style="width: 120px; height: 120px;" src="AttachmentController.do?method=showImg&objId=${orgInfo.logo}" /></p>
        <div class="userDetails">
          <p><label>所属地区：</label><span>${orgInfo.distinctName}</span></p>
          <p>
            <label>评价信息：</label>
            <span class="star"><fmt:formatNumber type="number" value="${buyer.evalSum }" pattern="#0.0" />分</span>
            <span>您有 <a href="javascript:void(0);" onclick="BuyerIndex.loadView('/IllegalRecController.do?method=toIllegalRecOrgList');return false;"><em>${statisticsInfo.I_N}</em></a> 次违规记录</span>
          </p>
        </div>
      </div>
    </div>
    <div class="grid16_4 omega">
      <div class="operationList">
       <ul>
         <li><a href="javascript:void(0);" onclick="BuyerIndex.loadView('/ProcurementtaskController.do?method=toCreateOrUpdateView');return false;" class="addPlan">新增采购计划</a></li>
         <li><a href="javascript:void(0);" onclick="BuyerIndex.openView('/RequirementController.do?method=toChooseCategory');" class="sent">发布采购需求</a></li>
         <li><a href="javascript:void(0);" onclick="BuyerIndex.openView('/BargainProjectController.do?method=toCreateBidProject_1');" class="create">创建竞价项目</a></li>
        </ul>
      </div>
    </div>
</div>
<!--用户登录详情、操作列表 结束-->
  
<!--待办任务、统计数据 开始-->
<div class="gridBox">
    <div class="grid16_12">
      <div class="pointInfo">
        <ul>
          <li>
            <label>待办任务：</label>
            <!--进行中的项目-->
            <authz:authorize ifAnyGranted="view/agreement/bargin/project/bargain/bargain_project_list.jsp">
              <a href="javascript:void(0);" onclick="BuyerIndex.loadView('/view/agreement/bargin/project/bargain/bargain_project_list.jsp');return false;">进行中的项目(<em>${task.BBI+task.BB}</em>)</a>&nbsp;&nbsp;
            </authz:authorize>
            
            <!--进行中的采购需求-->
            <authz:authorize ifAnyGranted="RequirementController.do?method=toRequirementPurchaserList">
              <a href="javascript:void(0);" onclick="BuyerIndex.loadView('/RequirementController.do?method=toRequirementPurchaserList');return false;">进行中的需求(<em>${task.RS}</em>)</a>&nbsp;&nbsp;
	 	    </authz:authorize>
	 	    
	 	    <!--待提交订单-->
            <authz:authorize ifAnyGranted="OrderController.do?method=toOrderBuyerList&appType=XEJY">
              <a href="javascript:void(0);" onclick="BuyerIndex.loadView('/OrderController.do?method=toOrderBuyerList&appType=XEJY',0);return false;">待提交订单(<em>${task.BO}</em>)</a>&nbsp;&nbsp;
	 	    </authz:authorize>
	 	    
	 	    <!--待确认合同-->
            <authz:authorize ifAnyGranted="AgContractController.do?method=toContractBuyerList">
			  <a href="javascript:void(0);" onclick="BuyerIndex.loadView('/AgContractController.do?method=toContractBuyerList');$('#currentTab').val(1);return false;">待确认合同(<em>${task.BC}</em>)</a>
	 	    </authz:authorize>
          </li>
          <li class="last">
            <label>我的消息：</label>
            <a href="javascript:void(0);" onclick="BuyerIndex.loadView('/MessageController.do?method=toMessageListView');return false;">站内消息(<em>${statisticsInfo.M_N}</em>)</a>&nbsp;&nbsp;
            <a href="javascript:void(0);" onclick="BuyerIndex.loadView('/NoteController.do?method=toNoteList');return false;">咨询回复(<em>${statisticsInfo.N_N}</em>)</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="grid16_4 omega">
      <div class="statDataBox">
        <h3><em>${statisticsInfo.C_N}</em> 家企业关注</h3>
        <ul>
          <li><label>已发布项目：</label><em>${statisticsInfo.P_N}</em></li>
          <li><label>已发布需求：</label><em>${statisticsInfo.R_N }</em></li>
          <li><label>采购总金额：</label><em><fmt:formatNumber value="${buyer.dealTotal * 0.0001}" pattern="#,##0.00#" /></em> 万元</li>
        </ul>
      </div>
    </div>
</div>
<!--待办任务、统计数据 结束-->
  
<!--发布的项目、推荐供应商 开始-->
<div class="gridBox">
    <div class="grid16_12">
      <div id="sendProject" class="cols">
        <h2>发布的项目</h2>
        <ul>
          <c:forEach var="project" items="${bidList}" varStatus="status">
          <li>
            <div class="announcementBox">
              <h3>[${project.tenderno}]<c:choose><c:when test="${fn:length(project.tendername)> 28}">${fn:substring(project.tendername,0,27)}…</c:when><c:otherwise>${project.tendername}</c:otherwise></c:choose></h3>
              <ul>
                <li><label>采购方式：</label><em><c:choose><c:when test="${project.TenderMethod == '06'}">竞价</c:when><c:otherwise>议价</c:otherwise></c:choose></em></li>
                <c:if test="${project.TenderMethod == '06'}">
                <li><label>报名截止时间：</label><em><fmt:formatDate value="${project.SIGN_UP_END_DATE}" pattern="yyyy-MM-dd HH:mm"/></em></li>
                </c:if>
                <li class="spread">
                  <label>项目进度：</label>
                  <div class="projectProgress">
                    <c:set var="projStatus" value="0" />
					<c:choose>
						<c:when test="${project.ProcessStatus >= 200}">
							<c:set var="projStatus" value="4" />
						</c:when>
						<c:when test="${project.ProcessStatus == 200 || project.EVAL_END_DATE < nowDateStr}">
							<c:set var="projStatus" value="3" />
						</c:when>
						<c:when test="${project.EVAL_START_DATE < nowDateStr}">
							<c:set var="projStatus" value="2" />
						</c:when>
						<c:when test="${project.SIGN_UP_END_DATE > nowDateStr}">
							<c:set var="projStatus" value="1" />
						</c:when>
					</c:choose>
                    <ul>
                      <li <c:choose><c:when test="${projStatus > 1}">class="fist"</c:when></c:choose>>供应商报名</li>
					  <li <c:choose><c:when test="${projStatus == 2}">class="selected"</c:when><c:when test="${projStatus > 2}">class="center"</c:when></c:choose>>供应商报价</li>
			          <li <c:choose><c:when test="${projStatus == 3}">class="selected"</c:when><c:when test="${projStatus > 3}">class="center"</c:when></c:choose>>确定供应商</li>
			          <li <c:choose><c:when test="${projStatus == 4}">class="selected"</c:when></c:choose>>结束</li>
                    </ul>
                  </div>
                </li>
              </ul>
              <div class="announcementBtn"><a href="javascript:void(0);" onclick="BuyerIndex.showProject('${project.tenderid}','${project.TenderMethod}');" class="bigBtn"><span>进入项目</span></a></div>
            </div>
          </li>
          </c:forEach>
        </ul>
        <div class="more"><a href="javascript:void(0);" onclick="BuyerIndex.loadView('/view/agreement/bargin/project/bargain/bargain_project_list.jsp');return false;">更多</a></div>
      </div>
    </div>
    <div class="grid16_4 omega">
      <div id="supplierJoin" class="cols">
        <h2>最新加盟供应商</h2>
        <ul>
          <c:forEach var="supplier" items="${newSupplierList}">
          <li class="hotAreaNews">
            <h3>
              <a href="javascript:void(0);" title="${supplier.orgInfo.orgName}" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');">
              <c:choose><c:when test="${fn:length(supplier.orgInfo.orgName)> 10}">${fn:substring(supplier.orgInfo.orgName,0,9)}…</c:when><c:otherwise>${supplier.orgInfo.orgName}</c:otherwise></c:choose>
              </a>
            </h3>
            <p class="hotImg"><img style="width: 60px; height: 60px;" src="AttachmentController.do?method=showImg&objId=${supplier.orgInfo.logo}" /></p>
            <p class="hotDetails">经营范围：<c:choose><c:when test="${fn:length(supplier.bidForRangeName)> 18}">${fn:substring(supplier.bidForRangeName,0,17)}…</c:when><c:otherwise>${supplier.bidForRangeName}</c:otherwise></c:choose></p>
          </li>
          </c:forEach>
        </ul>
        <div class="more"><a href="javascript:void(0);" onclick="BuyerIndex.openView('/SupplierShowController.do?method=toShowSupplierIndexView');">更多</a></div>
      </div>
    </div>
</div>
<!--发布的项目、推荐供应商 结束-->
  
<!--子公司采购统计 开始-->
<c:if test="${fn:length(subCompnyBuyList)> 0}">
  <div class="gridBox">
    <div class="cols">
      <h2>子公司采购统计</h2>
      <div id="table">
        <table class="tableList">
          <thead>
            <tr>
              <th rowspan="2">机构名称</th><th colspan="2">采购计划</th><th colspan="2">实际采购</th><th colspan="2">采购项目</th>
            </tr>
            <tr>
              <th>数量</th><th>金额</th><th>数量</th><th>金额</th><th>议价</th><th>竞价</th>
            </tr>
            </thead>
            <tbody>
              <c:forEach var="objs" items="${subCompnyBuyList}">
              <tr>
                <td>${objs[1] }</td>
                <td class="center">${objs[2] }</td>
                <td class="center">${objs[3] }</td>
                <td class="center">${objs[4] }</td>
                <td class="center">${objs[5] }</td>
                <td class="center">${objs[6] }</td>
                <td class="center">${objs[7] }</td>
              </tr>
              </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</c:if>
<!--子公司采购统计 结束-->
    
<!--采购统计 开始-->
<div class="gridBox">
    <%@ include file="/view/pubservice/mydesktop/buyer_index_chart.jsp" %>
</div>
<!--采购统计 结束-->

<script>
var BuyerIndex = {};

//查看项目
BuyerIndex.showProject = function(projectId, ebuyMethod){
	if(ebuyMethod == '05'){
		window.open($("#initPath").val()+'/TalkProjectController.do?method=toTalkProjectDetailView&userType=buyer&objId='+projectId);
	}else{
		window.open($("#initPath").val()+'/BargainProjectController.do?method=toProjectView&userType=buyer&projectId='+projectId);
	}
}

//在新的页面打开
BuyerIndex.openView = function(url){
	window.open($("#initPath").val()+url);
}

//在div[id=grid]中加载页面
BuyerIndex.loadView = function(url,currentTab){

	//设置返回桌面
	$("#returnUrl").val($("#initPath").val()+"/ModelIndexController.do?method=getBuyerIndex");
	
	if(currentTab!=null){
		$("#currentTab").val(currentTab);
	}else{
		$("#currentTab").val(0);
	}
	$('#conBody').loadPage($('#initPath').val()+url);
}
</script>
