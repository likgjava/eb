<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
.item-list-bd .shop {padding-left: 5px;padding-top: 10px;vertical-align: top;}
.item-list-bd .shop .pic {float: left;border:1px solid #ddd;}
.item-list-bd .shop .info {float: left;height: 65px;margin-left: 10px;position: relative;text-align: left;width: 140px;word-wrap: break-word;}
.item-list-bd .auction {text-align: left;}
.item-list-bd .price {color:#FF5500;}
.item-list-bd .item-history {display: none;float: left;margin-top: -1px;width: 378px;z-index: 1;}
.item-list-bd .auction li.list-trigger {float: left;}
.item-list-bd .show {display: block;}
.item-list-bd .auction a.item-list-trigger {background:url("http://img01.taobaocdn.com/tps/i1/T1y6lRXlttXXXXXXXX-180-50.jpg") no-repeat scroll 113px 9px #F8F9FB;border: 1px solid #E2EFF3;display: block;height: 25px;line-height: 25px;overflow: hidden;text-indent: 10px;width: 125px;z-index: 2;}
.item-list-bd .item-history .list {background-color: #F8F9FB;border: 1px solid #BBDBE4;float: left;margin-left: 0;overflow: hidden;padding: 10px 0;text-align: center;width: 380px;}
.item-list-bd .selected {border-color: #BBDBE4;border-style: solid;border-width: 1px 1px 0;position: relative;}
.item-list-bd .selected a.item-list-trigger {background-position: 113px -10px;border-bottom-width: 0;}
.item-list-bd .item-history .item {display: block;float: left;margin-left: 12px;margin-right: 10px;overflow: hidden;width: 72px;}
.item-list-bd .item-history .item.no-pic {border: 1px solid #E4E4E4;margin:2px 10px;}
.item-list-bd .item-history .item .pic {border: 1px solid #E4E4E4;}
.item-list-bd .item-history .item .pic a {height: 72px;width: 72px;padding: 0;}
.item-list-bd .item-history .item .pic img {border: 1px solid #FFFFFF;display: block;height: 70px;max-height: 70px;max-width: 70px;width: 68px;}
.item-list-bd .item-history .item .title {line-height: 21px;overflow: hidden;}
.item-list-bd .item-history .item .price {color: #666666;font-family: tahoma, sans-serif;font-size: 12px;height: 18px;overflow: hidden;padding-top: 3px;text-align: center;}
.item-list-bd .item-history .item .price span {color: #FF5500;}
.item-list-bd .view-all {display: block;float: right;padding: 10px 20px 10px 0;text-align: right;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/service/service_list.js"></script>
<input type="hidden" id="addFeeSuccess" value="0"><!-- 标记新增服务标准和组合计费是否成功 -->

<!-- 查询条件 -->
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="ServiceListForm">
		<ul>
			<li>
				<label>服务名称：</label>
				<input type="text" name="serviceName" id="serviceName" value="${serviceName}">
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<!-- div class="formTips attention">
	<ul>
		<li>新增服务请点击 <span class="sysicon siAdd"><a id="addServiceBtn" href="javascript:void(0);"><strong>新增服务</strong></a></span></li>
	</ul>
</div -->

<table class="frontTableList" id="ServiceList">
	<thead>
		<tr>
			<th class="center">服务信息</th>
			<th>报价信息</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody class="item-list-bd">
		<c:forEach var="service" items="${serviceList}" varStatus="status">
		<tr>
			<td class="shop">
				<div class="pic s70">
					<img width="70" src="<c:url value="AttachmentController.do?method=showImg&objId=${service.servicePic}" />">
				</div>
				<div class="info">
					<a title="${service.serviceName}" href="javascript:void(0);" onclick="ServiceListForm.view('${service.objId}','showView','ServiceBase');"><strong>${service.serviceName}</strong></a><c:if test="${service.isRecommendation=='1'}"><span class="sysicon award_star_add"><a href="javascript:void(0);" title="推荐服务">&nbsp;</a></span></c:if><br>
					￥<strong class="price"><fmt:formatNumber value="${service.servicePrice}" pattern="#,##0.00#" /></strong> 元 / ${service.serviceUnitCN}
					<div><c:if test="${service.isSinglePurchase=='0'}">不可单独购买</c:if></div>
				</div>
			</td>
			<td class="auction">
				<ul>
					<li class="J_tab_li${service.objId} list-trigger">
						<a href="javascript:void();" rel="#stardFee${service.objId}" class="J_tab J_tab_a${service.objId} item-list-trigger" name="${service.objId}" type="charging">标准计费(<span id="stardFeeNum${service.objId}">${serviceChargingNumList[status.index]}</span>)</a>
					</li>
					<li class="J_tab_li${service.objId} list-trigger">
						<a href="javascript:void();" rel="#groupFee${service.objId}" class="J_tab J_tab_a${service.objId} item-list-trigger" name="${service.objId}" type="group">组合计费(<span id="groupFeeNum${service.objId}">${serviceGroupNumList[status.index]}</span>)</a>
					</li>
				</ul>

				<div class="J_tab_div${service.objId} item-history" id="stardFee${service.objId}"></div>

				<div class="J_tab_div${service.objId} item-history" id="groupFee${service.objId}"></div>
			</td>
			<td class="operation">
				<a title="查看服务详情" href="javascript:void(0);" onclick="ServiceListForm.view('${service.objId}','showView','ServiceBase');">查看</a>
			 	<a title="修改服务信息" href="javascript:void(0);" onclick="ServiceListForm.view('${service.objId}','updateView','ServiceBase');">修改</a>
				<a title="删除服务" href="javascript:void(0);" onclick="ServiceListForm.deleteService('${service.objId}');">删除</a><br>
				<c:if var="isRecommend" test="${service.isRecommendation=='1'}">
					<a title="取消推荐" href="javascript:void(0);" onclick="ServiceListForm.updateStatus('${service.objId}','isRecommendation','0');return false;">取消推荐</a>
				</c:if>
				<c:if test="${!isRecommend}">
					<a title="推荐" href="javascript:void(0);" onclick="ServiceListForm.updateStatus('${service.objId}','isRecommendation','1');return false;">推荐</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<script>
$(".J_tab").click(function(){
	var divId = $(this).attr("rel");
	var isSelect = $(this).parent().hasClass("selected");

	//当前行所有的tab还原
	var id = $(this).attr("name").replace("J_tab_a","");
	$(".J_tab_li"+id).removeClass("selected");
	$(".J_tab_div"+id).removeClass("show");
	
	if(isSelect){
		$(divId).removeClass("show");
		$(this).parent().removeClass("selected");
	}
	else{
		$(divId).addClass("show");
		$(this).parent().addClass("selected");

		var type = $(this).attr("type");
		if(type == 'charging'){
			$(divId).loadPage($('#initPath').val()+"/ServiceChargingController.do?method=toServiceChargingListView&serviceBaseId="+id);
		}else if(type == 'group'){
			$(divId).loadPage($('#initPath').val()+"/ServiceGroupController.do?method=toServiceGroupListView&serviceBaseId="+id);
		}
	}
	return false;
})
</script>
