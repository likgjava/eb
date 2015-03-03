<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>【${project.projName}】竞价项目-阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/projectDetail.css"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/agreement/bargin/project/project_view.js'></script>
</head>
<body>

<!--页面容器 开始-->
<div id="container"> 
	<!-- 项目Id -->
	<input name="projectId" id="projectId" type="hidden" value="${project.objId}"/>
	<input name="threeMust" id="threeMust" type="hidden" value="${threeMust}"/>
	<input name="timeBeforeOrder" id="timeBeforeOrder" type="hidden" value="${timeBeforeOrder}"/><!--发布结果公告与下订单的时间间隔-->

  <!--头部容器 开始-->
  <div class="header"> 
  		<input type="hidden" id="isOutCss" value="${param.isOutCss}"/>
  		<c:choose>
	  		<c:when test="${!isOutCss}"><jsp:include page="/view/srplatform/portal/include/backgroundmenu.jsp"></jsp:include></c:when>
  			<c:otherwise><%@ include file="/view/srplatform/portal/include/main_header_index.jsp"%></c:otherwise>
  		</c:choose>
  </div>
  <!--头部容器 结束--> 
	<!--主要内容容器 开始-->
	<div class="page" style="min-height:600px!important;">
		<div class="gridBox">
			<!-- 三级菜单 -->
			<div class="grid16_3 hidden" id="menuList">
				<div class="menuList"><ul></ul></div>
			</div>
			
			<!-- conBody开始 内容页面加载位置 -->
			<div class="grid16_16" id="conBody">
			
			<div id="base_bd" class="base_t3">
        
        
<h2 class="package_headline layoutfix ">[${project.projCode}]${project.projName}
<p class="package_fontcolor">报名时间：<fmt:formatDate value="${project.signUpSTime }" pattern="yyyy年MM月dd日 HH:mm:ss"/>&nbsp;至&nbsp;<fmt:formatDate value="${project.signUpETime }" pattern="yyyy年MM月dd日 HH:mm:ss"/>  </p>
<p class="package_fontcolor">报价时间：<fmt:formatDate value="${project.evalStartTime }" pattern="yyyy年MM月dd日 HH:mm:ss"/>&nbsp;至&nbsp;<fmt:formatDate value="${project.evalEndTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/> 

<!--权限判断-->
<authz:authorize ifAnyGranted="modifyTime"><c:set var="modifyTime" value="true"/></authz:authorize>
<authz:authorize ifAnyGranted="modifyProject"><c:set var="modifyProject" value="true"/></authz:authorize>
<authz:authorize ifAnyGranted="sendInvitation"><c:set var="sendInvitation" value="true"/></authz:authorize>
<authz:authorize ifAnyGranted="eliminateSupplier"><c:set var="eliminateSupplier" value="true"/></authz:authorize>
<authz:authorize ifAnyGranted="barginHall"><c:set var="barginHall" value="true"/></authz:authorize>
<authz:authorize ifAnyGranted="talkHall"><c:set var="talkHall" value="true"/></authz:authorize>
<authz:authorize ifAnyGranted="confirmResult"><c:set var="confirmResult" value="true"/></authz:authorize>
<authz:authorize ifAnyGranted="createOrder"><c:set var="createOrder" value="true"/></authz:authorize>
<authz:authorize ifAnyGranted="evaluate"><c:set var="evaluate" value="true"/></authz:authorize>
<authz:authorize ifAnyGranted="report"><c:set var="report" value="true"/></authz:authorize>
<!--权限判断完毕-->

<!-- 代理人可以操作 -->
<c:if test="${currentUserId==project.createUser.objId || modifyTime }">
<a href="javascript:void(0);" onclick="project_detail_view.modifyTime('${project.objId}');" class="sysicon report_edit">&nbsp;修改时间</a>
</c:if>
<!-- 代理人可以操作 -->
</p>
</h2>

<div class="proce_status">
	<span class="<c:choose><c:when test="${projStatus[0]==1}">currentFirst</c:when>
	<c:otherwise>finishedFirst</c:otherwise></c:choose>">发布公告</span>
	
	<span class="<c:choose><c:when test="${projStatus[1]==0}">unfinished</c:when>
	<c:when test="${projStatus[1]==1}">current</c:when>
	<c:otherwise>finished</c:otherwise></c:choose>">供应商报名</span>
	
	<span class="<c:choose><c:when test="${projStatus[2]==0}">unfinished</c:when>
	<c:when test="${projStatus[2]==1}">current</c:when>
	<c:otherwise>finished</c:otherwise></c:choose>">供应商报价</span>
	
	<span class="<c:choose><c:when test="${projStatus[3]==0}">unfinished</c:when>
	<c:when test="${projStatus[3]==1}">current</c:when>
	<c:otherwise>finished</c:otherwise></c:choose>">确定结果</span>
	
	<span class="<c:choose><c:when test="${projStatus[4]==0}">unfinishedLast</c:when>
	<c:otherwise>finishedLast</c:otherwise></c:choose>">项目结束</span>
</div>
<div class="operationBtn"> 
<c:choose>
<%--项目正常--%>
<c:when test="${project.projImplStatus=='00'}">
	<!-- 代理人可操作 -->
	<c:if test="${( currentUserId==project.createUser.objId || modifyProject ) && projStatus[0]==1}">
		<button type="button" class="base_btns7" onclick="project_detail_view.update()"><span>修改项目</span></button>
	</c:if>
	
	<c:if test="${( currentUserId==project.createUser.objId || sendInvitation )&& projStatus[1]==1 }">
		<button type="button" class="base_btns7" onclick="project_detail_view.sendInvitation()" ><span>发邀请函</span></button>
	</c:if>
	
	<c:if test="${( projStatus[1]==1 || projStatus[2]==1 ) && ( currentUserId==project.createUser.objId || eliminateSupplier ) }">
			<button type="button" class="base_btns7" onclick="project_detail_view.confirmEliminate()" ><span>剔供应商</span></button>
	</c:if>
	
	<c:if test="${projStatus[2]==1}">
		<c:if test="${( currentUserId==project.createUser.objId || barginHall ) && project.ebuyMethod=='06'}"><button type="button" class="base_btns7" onclick="project_detail_view.intoHall()"><span>竞价厅</span></button></c:if>
		<c:if test="${( currentUserId==project.createUser.objId || talkHall ) && project.ebuyMethod=='05'}"><button type="button" class="base_btns7" onclick="project_detail_view.intoTalkHall()"><span>议价厅</span></button></c:if>
	</c:if>
	<c:if test="${( currentUserId==project.createUser.objId || confirmResult ) && projStatus[3]==1}">
		<button type="button" name="toConfirmResultBtn" class="base_btns7" onclick="project_detail_view.confirm()"><span>确定结果</span></button>
	</c:if>
	
	<c:if test="${currentUserId==project.createUser.objId && project.projProcessStatus!='200'}">
		<button type="button" class="base_btns7" onclick="project_detail_view.stopProject();return false;"><span>终止项目</span></button>
	</c:if>
	<!-- 代理人也可操作 -->
	
	<!-- 只有采购人才可以操作 -->
	<c:if test="${currentUserId==project.createUser.objId || createOrder }">
		<input type="hidden" name="canCreateOrder" value="true"/><!-- 做标记用 -->
		<c:if test="${projStatus[4]==1}">
				<button type="button" name="toCraeteOrderBtn" class="base_btns7" onclick="project_detail_view.order()"><span>生成订单</span></button>
		</c:if>
	</c:if>
	<!-- 只有采购人才可以操作 -->
	
	<!-- 代理人可操作 -->
	<span <c:if test="${projStatus[4]!=1 && projStatus[3]!=1}">class="hidden"</c:if> >
		<c:if test="${ currentUserId==project.createUser.objId || evaluate }">
			<button type="button" class="base_btns7" onclick="project_detail_view.evaluate('${project.objId}','${project.projName}')"><span>评价</span></button>
		</c:if>
		<c:if test="${ currentUserId==project.createUser.objId || report }">
			<button type="button" class="base_btns7" onclick="project_detail_view.report('${project.objId}','tell','${project.ebuyMethod}')"><span>投诉</span></button>
			<button type="button" class="base_btns7" onclick="project_detail_view.report('${project.objId}','complain','${project.ebuyMethod}')"><span>举报</span></button>
		</c:if>
	</span>
	<!-- 代理人可操作 -->
</c:when>
<%--项目暂停--%>
<c:when test="${project.projImplStatus=='01'}">
	<span style="font-size:18px; font-weight:bold; color:#F00;">项目已暂停</span>
</c:when>
<%--项目终止--%>
<c:when test="${project.projImplStatus=='02'}">
	<span style="font-size:18px; font-weight:bold; color:#F00;">项目已终止</span>
</c:when>
</c:choose>
</div>

<div class="more_info_btn"><a href="javascript:void(0);" onclick="project_detail_view.exchange();return false;">更多项目信息...</a></div>
<div class="more_info hidden" id="extInfoDiv">
	<div class="boxx">
		<div class="hd">
			<h3>项目轮次和规则</h3>
		</div>
		<div class="bd">
			<dl>
				<dt>项目轮次</dt>
				
				<c:forEach var ="bargainTurn" items= "${bargainTurnList}" varStatus="status">
					<dd><span class="base_black">第${bargainTurn.turnNo}轮：</span><fmt:formatDate value="${bargainTurn.startTime }" pattern="yyyy年MM月dd日 HH:mm:ss"/> 至 <fmt:formatDate value="${bargainTurn.endTime }" pattern="yyyy年MM月dd日 HH:mm:ss"/></dd>
				</c:forEach>
				
				<dt>项目规则</dt>
				<c:forEach var="projrule" items="${projruleList}" varStatus="status">
		    		<c:if test="${projrule.resValue !=null&& projrule.resValue !=''}" >
							<c:if test="${projrule.code!='bargainNumber'}">
							<dd class="col2"><span class="base_black">${projrule.sysItemName}：</span>${projrule.resValue}</dd>
							</c:if>
							<c:if test="${projrule.code=='bargainNumber'}">
								<dd class="col2"><span class="base_black">是否多次报价：</span>${projrule.resValue}</dd>
							</c:if>
		    		</c:if>
				</c:forEach>
				<dt>要求资质</dt>
				<dd>${projectSignInfo.companyQualification}</dd>
			</dl>
		</div>
	</div>
	<div class="boxx">
		<div class="hd">
			<h3>其他信息</h3>
		</div>
		<div class="bd">
			<dl>
				<dt>支付方式</dt>
				<dd><span class="base_black">交货时间：</span>${projectPayInfo.deliveryDate}</dd>
				<dd><span class="base_black">交货地点：</span>${projectPayInfo.deliveryAddress}</dd>
				<dd class="col2"><span class="base_black">交货方式：</span>${projectPayInfo.deliveryTypeCN}</dd>
				<dd class="col2"><span class="base_black">支付方式：</span>${projectPayInfo.payTypeCN}</dd>
				<dd><span class="base_black">其他说明：</span>${projectPayInfo.supplement}</dd>
				
				<dt>联系方式</dt>
				<dd class="col2"><span class="base_black">联系人：</span>${projectContactInfo.linker}</dd>
				<dd class="col2"><span class="base_black">移动电话：</span>${projectContactInfo.mobilePhone}</dd>
				<dd class="col2"><span class="base_black">固定电话：</span>${projectContactInfo.fixedTelephone}</dd>
				<dd class="col2"><span class="base_black">传真：</span>${projectContactInfo.fax}</dd>
				<dd><span class="base_black">地址：</span>${projectContactInfo.address} </dd>
				<dd><span class="base_black">邮编：</span>${projectContactInfo.postCode}</dd>
			</dl>
		</div>
	</div>
	
</div>
        
<div class="package_details_intro layoutfix">
<div class="b_bd layoutfix">
<div class="pripackage_date_main">
	<h5>已报名供应商</h5>
	<div class="describ">
		1、双击单元格，可以查看该供应商对指定需求的报价详情。 <br />
		2、字体为红色的为对应需求的最低报价。<br />
		3、点击 “对比报价信息” 可以对某一需求的所有供应商报价详情进行查看。
		
		<c:if test="${!empty bulletin.objId}">
			<span style="float:right;margin-right:15px;"><a class="sysicon report" onclick="common.goToBulletinDetail('${bulletin.project.objId}','${bulletin.bullType}');" href="javascript:void(0);">${bulletin.bullType=='12'?'采购公告':'结果公告'}</a></span>
		</c:if>
	</div>
	<table class="pripackage_date_list">
		<thead>
			<tr>
			
				<c:if test="${fn:length(biddingRecordObjectList)>0 }">
				<td class="col_title"></td>
				</c:if>
				
			  	<c:forEach var="requireItem" items="${requireItemList}">
					<c:set var="reqDesc" value="${requireItem.goods!=null ? requireItem.goods.productName : requireItem.descr }"/>
			  		<td title="${reqDesc}">
			  		<span name="require${requireItem.objId}">
			  		<c:if test="${requireItem.goods!=null}"><a href="javascript:void(0);" onclick="common.geToGoodsDetail('${requireItem.goods.objId}');"></c:if>
			  		<c:choose>
			  			<c:when test="${fn:length(reqDesc)>20}">${fn:substring(reqDesc,0,20)}...</c:when>
			  			<c:otherwise>${reqDesc}</c:otherwise>
			  		</c:choose>
			  		<c:if test="${requireItem.goods!=null}"></a></c:if>
			  		</span>
			  		<span  class="package_fontcolor">(${requireItem.goodsQty}${requireItem.goodsUnit})</span>
			  		<div class="base_txtgreen">￥<span id="require${requireItem.objId}" price ="${requireItem.goodsTotal}"><fmt:formatNumber value="${requireItem.goodsTotal }" pattern="#,##0.00#"/></span>元</div>
	  				<c:if test="${fn:length(biddingRecordObjectList)>0 }">
			  		<div class="base_txtnormal"><a href="javascript:void(0);" onclick="project_detail_view.openRecordDetail('${requireItem.objId}');" class="sysicon report_magnify">对比报价信息</a></div>
			  		</c:if>
			  		</td>
			  	</c:forEach>

				<td class="col_sum"><em class="base_txtred">总预算</em><div class="base_txtgreen">￥<fmt:formatNumber value="${project.budgetTotalMoney }" pattern="#,##0.00#"/>元</div></td>
			</tr>
		</thead>
		
	<tbody id="minPriceRecordTable">
	
			
		<!-- 标志位 -->
		<c:set var="supplierId" value=""/>
		
		<!-- 行小计 -->
		<c:set var="cellTotal" value="0"/>
		
		<c:forEach var="biddingRecordObject" items="${biddingRecordObjectList}" varStatus="status">
			
			<!-- 供应商名称列 -->
			<c:if test="${supplierId != biddingRecordObject[0] }">
				<c:set var="supplierId" value="${biddingRecordObject[0]}"/>
				<tr>
			
				<!-- 是成交供应商则有stamp样式 -->
				<td class="col_title<c:if test="${dealSupplierId!=null && fn:contains( dealSupplierId , biddingRecordObject[0])}"> stamp</c:if>">
				
				
					<div style="padding-left: 20px; text-align: left; float:left;">
					<!-- 检查是否为剔除供应商 开始 -->
					<c:set var="hasEliminate" value="0" />
					<c:set var="eliminateSupplierId" value="" />
					<c:forEach var="eliminateSupplier" items="${eliminateSupplierList}">
						<c:if test="${biddingRecordObject[0] == eliminateSupplier.supplier.objId}">
							<c:set var="hasEliminate" value="1" />
							<c:set var="eliminateSupplierId" value="${eliminateSupplier.objId}" />
						</c:if>
					</c:forEach>
					<!-- 检查是否为剔除供应商 结束 -->
					
					<!-- 检查是否黑名单客户 开始 -->
					<c:set var="isInBlack" value="0" />
					<c:forEach var="blackOrgId" items="${blackOrgIdList}">
						<c:if test="${biddingRecordObject[0] == blackOrgId}"><c:set var="isInBlack" value="1" /></c:if>
					</c:forEach>
					<!-- 检查是否黑名单客户 结束 -->
					
					<!-- 剔除供应商或黑名单客户特殊操作  开始 -->
					<c:choose>
					<c:when test="${hasEliminate == 0}">
						<input class="hidden" type="checkbox" name="eliminateSupplier" value="${biddingRecordObject[0]}" <c:if test="${isInBlack == 1}">checked="checked"</c:if> />
					</c:when>
					<c:otherwise>
						<input class="hidden" type="checkbox" name="eliminateSuppliered" checked="checked" disabled="disabled"/></c:otherwise>
					</c:choose>
					<!-- 剔除供应商或黑名单客户特殊操作  结束 -->
					</div>
				
					<div>
						<!-- 生成订单 -->
						<c:if test="${dealSupplierId!=null && fn:contains( dealSupplierId , biddingRecordObject[0])}">
							<input name="chckOrder" value="${biddingRecordObject[0]}" type="radio" checked="checked"/>
						</c:if>
						<!-- 供应商名称 -->
						<a name="supplier${biddingRecordObject[0]}" href="javascript:void(0);" onclick="project_detail_view.goSupplierPage('${biddingRecordObject[0]}');" <c:if test="${hasEliminate == 1}">class="delete-line"</c:if>>${biddingRecordObject[4]}</a>
					</div>
					<div>
						<c:if test="${isInBlack == 1}">[<strong>黑名单</strong>]</c:if>
						<c:if test="${hasEliminate == 1}">
							[<a href="javascript:void(0);" onclick="project_detail_view.cancleEliminate('${eliminateSupplierId}');">取消剔除</a>]
						</c:if>
					</div>
					<a href="javascript:void(0);" onclick="project_detail_view.showSignUpRecord('${biddingRecordObject[0]}');" class="sysicon report_user">报名信息</a> 
					<a href="javascript:void(0);" onclick="project_detail_view.showSignUpRecordFile('${biddingRecordObject[0]}');" class="sysicon report_word">报名文件</a>

				</td>
			</c:if>
			
			<!-- 供应商对需求 报价列 -->
			<c:choose>
				<c:when test="${biddingRecordObject[2]!=null}">
					<td class="price_td col_${fn:length(requireItemList)}<c:if test="${ biddingRecordObject[3]==49 }"> flag</c:if>" ondblclick="project_detail_view.showRecordDetail('${biddingRecordObject[0]}','${biddingRecordObject[1]}');">
					<c:set var="cellTotal" value="${cellTotal+ biddingRecordObject[2] }"/><!-- 累加统计行小计 -->
					<div>￥
					<span name="cellPrice" id="${biddingRecordObject[0]},${biddingRecordObject[1]}">
						<fmt:formatNumber value="${biddingRecordObject[2]}" pattern="#,##0.00#"/>
					</span>
					元</div>
					<div class="save_total">节省：<span name ="saveCell"></span>元</div>
					</td>
				</c:when>
				<c:otherwise>
					<td class="price_td col_${fn:length(requireItemList)}" >
					<c:set var="cellTotal" value="${cellTotal+ biddingRecordObject[2] }"/><!-- 累加统计行小计 -->
					<div>
					<span >暂无报价
					</span>
					<span name="cellPrice" class="hidden" id="${biddingRecordObject[0]},${biddingRecordObject[1]}">0</span>
					</div>
					</td>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${(status.index+1) % fn:length(requireItemList) == 0 }">
				<td class="col_sum">
					
					<c:choose>
						<c:when test="${cellTotal!=0}">
						
						<div>￥<span id="${biddingRecordObject[0]}" name="cellTotal" style="font-weight: bold;">
						<fmt:formatNumber value="${cellTotal}" pattern="#,##0.00#"/>
						</span>
						元</div>
						<div class="save_total">共节省：<span name="saveTotal"></span>元</div>
						
						</c:when>
						<c:otherwise>
							<div>
							<span>暂无报价</span>
							<span id="${biddingRecordObject[0]}" name="cellTotal" class="hidden">0</span>
							</div>
						</c:otherwise>
					</c:choose>
				</td>
				</tr>
				<c:set var="cellTotal" value="0"/><!-- 行小计归零 -->
			</c:if>
		</c:forEach>
		
		<c:if test="${fn:length(biddingRecordObjectList)==0 }">
			<tr><td colspan="${fn:length(requireItemList)+1}">暂无供应商报名</td></tr>
		</c:if>
	</tbody></table>
</div>	


<div id="operationDiv" class="hidden"></div>

            </div>
        </div>
    </div>
			
			</div>
			<!-- conBody结束 内容页面加载位置-->
    	</div>
	</div>
	<!--主要内容容器 结束--> 
  
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
  </div>
  <!--底部容器 结束--> 
</div>
<!--页面容器 结束--> 
</body>
</html>
