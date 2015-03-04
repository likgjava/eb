<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--用户登录详情、操作列表 开始-->
<div class="gridBox">
    <div class="grid16_12">
      <div class="userDetailsBox">
        <h3><em>${userName}</em>,欢迎您！ <span class="lastLoginTime">上一次登录时间：${lastLoginTime}</span></h3>
        <h4>${orgInfo.orgName}<c:if test="${departmentName != null}">-${departmentName}</c:if><em>[<c:choose><c:when test="${member==null}">非会员</c:when><c:otherwise>${member.memberClass.memberClassNumCN}</c:otherwise></c:choose>]</em> <em>[<a href="javascript:void(0);" onclick="SupplierIndex.loadView('/ExOrgInfoController.do?method=toEntBaseInfo');return false;">修改企业信息</a>]</em></h4>
        <p class="userPic"><img style="width: 120px; height: 120px;" src="AttachmentController.do?method=showImg&objId=${orgInfo.logo}" /></p>
        <div class="userDetails">
          <p><label>所属地区：</label><span>${orgInfo.distinctName}</span></p>
          <p>
            <label>评价信息：</label>
            <span class="star"><fmt:formatNumber type="number" value="${supplier.evalSum }" pattern="#0.0" />分</span>
            <span>您有 <a href="javascript:void(0);" onclick="SupplierIndex.loadView('/IllegalRecController.do?method=toIllegalRecOrgList');return false;"><em>${statisticsInfo.I_N}</em></a> 次违规记录</span>
          </p>
        </div>
      </div>
    </div>
    <div class="grid16_4 omega">
      <div  class="operationList">
        <ul>
          <li><a href="javascript:void(0);" onclick="SupplierIndex.openView('/GoodsController.do?method=toCreateGoods');" class="release">发布商品信息</a></li>
          <li><a href="javascript:void(0);" onclick="SupplierIndex.loadView('/view/smallscale/chart/sale_data_analysis.jsp');return false;" class="check">查看销售统计</a></li>
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
            <authz:authorize ifAnyGranted="SupplierSignupController.do?method=toMyProjectSupplier">
              <a href="javascript:void(0);" onclick="SupplierIndex.loadView('/SupplierSignupController.do?method=toMyProjectSupplier');return false;">进行中的项目(<em>${task.SP+task.SB}</em>)</a>&nbsp;
            </authz:authorize>
            
            <!--进行中的采购需求-->
            <authz:authorize ifAnyGranted="RequirementRegController.do?method=toMyRequirementReg">
              <a href="javascript:void(0);" onclick="SupplierIndex.loadView('/RequirementRegController.do?method=toMyRequirementReg');return false;">进行中的需求(<em>${task.SR}</em>)</a>&nbsp;
            </authz:authorize>
            
            <!-- 待确认订单 -->
	        <authz:authorize ifAnyGranted="OrderController.do?method=toOrderSupplierList&appType=XEJY">
		      <a href="javascript:void(0);" onclick="SupplierIndex.loadView('/OrderController.do?method=toOrderSupplierList&appType=XEJY');return false;">待确认的订单(<em>${task.SO}</em>)</a>&nbsp;
	        </authz:authorize>
	
	        <!-- 待确认合同 -->
	        <authz:authorize ifAnyGranted="AgContractController.do?method=toContractSupplierList">
		      <a href="javascript:void(0);" onclick="SupplierIndex.loadView('/AgContractController.do?method=toContractSupplierList');$('#currentTab').val(1);return false;">待确认的合同(<em>${task.SC}</em>)</a>
	        </authz:authorize>
          </li>
          <li class="last">
            <label>我的消息：</label>
            <a href="javascript:void(0);" onclick="SupplierIndex.loadView('/MessageController.do?method=toMessageListView');return false;">站内消息(<em>${statisticsInfo.M_N}</em>)</a>&nbsp; 
            <a href="javascript:void(0);" onclick="SupplierIndex.loadView('/NoteController.do?method=toNoteList');return false;">咨询回复(<em>${statisticsInfo.N_N}</em>)</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="grid16_4 omega">
      <div class="statDataBox">
        <h3><em>${statisticsInfo.C_N}</em> 家企业关注</h3>
        <ul>
          <li><label>参与项目数：</label><em>${statisticsInfo.RP_N}</em></li>
          <li><label>参与需求数：</label><em>${statisticsInfo.RG_N }</em></li>
          <li><label>销售总金额：</label><em><fmt:formatNumber value="${statisticsInfo.S_A * 0.0001}" pattern="#,##0.00#" /></em> 万元</li>
        </ul>
      </div>
    </div>
</div>
<!--待办任务、统计数据 结束-->
<!--参与的项目、最新加盟采购人开始-->
<div class="gridBox">
    <div class="grid16_12">
      <div id="sendProject" class="cols">
        <h2>我参与的项目</h2>
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
						<c:when test="${project.SIGN_UP_END_DATE < nowDateStr}">
							<c:set var="projStatus" value="1" />
						</c:when>
					</c:choose>
                    <ul>
                      <li <c:choose><c:when test="${projStatus == 1}">class="selected"</c:when><c:when test="${projStatus > 1}">class="fist"</c:when></c:choose>>供应商报名</li>
					  <li <c:choose><c:when test="${projStatus == 2}">class="selected"</c:when><c:when test="${projStatus > 2}">class="center"</c:when></c:choose>>供应商报价</li>
			          <li <c:choose><c:when test="${projStatus == 3}">class="selected"</c:when><c:when test="${projStatus > 3}">class="center"</c:when></c:choose>>确定供应商</li>
			          <li <c:choose><c:when test="${projStatus == 4}">class="selected"</c:when></c:choose>>结束</li>
                    </ul>
                  </div>
                </li>
              </ul>
              <div class="announcementBtn"><a href="javascript:void(0);" onclick="SupplierIndex.showProject('${project.tenderid}','${project.TenderMethod}');" class="bigBtn"><span>进入项目</span></a></div>
            </div>
          </li>
          </c:forEach>
        </ul>
        <div class="more"><a href="javascript:void(0);" onclick="SupplierIndex.loadView('/SupplierSignupController.do?method=toMyProjectSupplier');return false;">更多</a></div>
      </div>
    </div>
    <div class="grid16_4 omega">
      <div id="supplierJoin" class="cols">
        <h2>最新加盟采购人</h2>
        <ul>
          <c:forEach var="buyer" items="${newBuyerList}">
          <li class="hotAreaNews">
            <h3>
              <a title="${buyer.orgInfo.orgName}" onclick="common.geToBuyerDetail('${buyer.objId}');" href="javascript:void(0);">
              <c:choose><c:when test="${fn:length(buyer.orgInfo.orgName)> 10}">${fn:substring(buyer.orgInfo.orgName,0,9)}…</c:when><c:otherwise>${buyer.orgInfo.orgName}</c:otherwise></c:choose>
              </a>
            </h3>
            <p class="hotImg"><img style="width: 60px; height: 60px;" src="AttachmentController.do?method=showImg&objId=${buyer.orgInfo.logo}" /></p>
            <p class="hotDetails">经营范围：<c:choose><c:when test="${fn:length(buyer.orgInfo.bidForRangeName)> 16}">${fn:substring(buyer.orgInfo.bidForRangeName,0,15)}…</c:when><c:otherwise>${buyer.orgInfo.bidForRangeName}</c:otherwise></c:choose></p>
          </li>
          </c:forEach>
        </ul>
        <div class="more"><a target="_blank" href="<%=request.getContextPath()%>/BuyerShowController.do?method=toBuyerList&rp=21&page=1&districtLevel=1">更多</a></div>
      </div>
    </div>
</div>
<!--参与的项目、最新加盟采购人 结束-->
<!--子公司销售统计 开始-->
<c:if test="${fn:length(subCompnySaleInfoList)> 0}">
<div class="gridBox">
    <div class="cols">
      <h2>子公司销售统计</h2>
      <div id="table">
        <table class="tableList">
          <thead>
            <tr>
              <th>机构名称</th><th>参与项目数</th><th>中标项目数</th><th>中标比例</th><th>成交总金额</th>
            </tr>
            </thead>
            <tbody>
              <c:forEach var="objs" items="${subCompnySaleInfoList}">
              <tr>
                <td>${objs[1] }</td>
                <td class="center"><fmt:formatNumber value="${objs[2]}" pattern="#,###" /></td>
                <td class="center"><fmt:formatNumber value="${objs[3]}" pattern="#,###" /></td>
                <td class="center"><c:if var="hasWined" test="${objs[2] != 0}"><fmt:formatNumber value="${objs[3]/objs[2]*100}" pattern="#0.0" />%</c:if><c:if test="${!hasWined}">--</c:if></td>
                <td class="center"><fmt:formatNumber value="${objs[4]}" pattern="#,##0.00#" />元</td>
              </tr>
              </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
</div>
</c:if>
<!--子公司销售统计 结束-->
  
<!--销售统计 开始-->
<div class="gridBox">
    <%@ include file="/view/pubservice/mydesktop/supplier_index_chart.jsp" %>
</div>
<!--销售统计 结束-->

<script>
var SupplierIndex = {};

//在新的页面打开
SupplierIndex.openView = function(url){
	window.open($("#initPath").val()+url);
}

//在div[id=grid]中加载页面
SupplierIndex.loadView = function(url){
	$('#conBody').loadPage($('#initPath').val()+url);
}

//查看项目
SupplierIndex.showProject = function(projectId, ebuyMethod){
	if(ebuyMethod == '05'){
		window.open($("#initPath").val()+'/TalkProjectController.do?method=toTalkProjectDetailView&userType=supplier&objId='+projectId);
	}else{
		window.open($("#initPath").val()+'/BargainProjectController.do?method=toProjectView&userType=supplier&projectId='+projectId);
	}
}
</script>