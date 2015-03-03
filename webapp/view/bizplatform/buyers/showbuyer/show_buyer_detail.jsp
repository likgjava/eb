<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>【${buyer.orgInfo.orgName}】- ${fn:split(buyer.orgInfo.bidForRange,'##||##')[1]}采购 - 采购人库 - 阳光易购电子采购与招标平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>
<div id="container">
	<input type="hidden" id="tabId" value="${tabId}"/>
	<input type="hidden" id="goodsId" value="${goods.objId}" />
	<input type="hidden" id="isMember" value="${isMember}" />

	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div id="contentSub"></div>
		<div id="contentMain" class="index2paL">
			<div id="conTitle">
				<div class="navCurrent">
					<a href="javascript:void(0);" onclick="BuyerDetailForm.searchByTitle(null,null,'1');return false;" >采购人展示</a>
					<a href="javascript:void(0);" onclick="BuyerDetailForm.searchByTitle('${buyer.orgInfo.entPrpt}',null,'1');return false;" >${buyer.orgInfo.entPrptCN }</a>
					<c:if test="${districtId!=null}">
					<a href="javascript:void(0);" onclick="BuyerDetailForm.searchByTitle('${buyer.orgInfo.entPrpt}','${districtId }','1');return false;" >${districtName }</a>
					</c:if>
					采购人详情
				</div>
			</div>
			<div id="conBody">
				<div class="imgAndInfo smallImg" style="height: 200px;">
					<div id="showImg" style="width: 200px;">
						<div class="short" style="width: 180px;height: 180px;">
							<img style="width: 180px;height: 180px;" src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${buyer.orgInfo.logo}" />
						</div>
					</div>
					<div id="showInfo" style="margin-left: 200px;">
						<ul class="meta">	
							<li class="detail-title"><span>组织机构代码：</span>${buyer.orgInfo.orgCode }</li>
							<li class="detail-title"><span>采购人名称：</span><strong>${buyer.orgInfo.orgName }</strong>
								<button type="button" onclick="BuyerDetailForm.addFavorites('${buyer.objId }','${buyer.orgInfo.orgName }','04')" class="favBtn" height="25px">加入收藏</button>
								<button type="button" onclick="show_list.addClient('02','${buyer.orgInfo.objId}','${buyer.orgInfo.orgName}')" class="favBtn" height="25px" id="addClient">关注客户</button>
							</li>
						</ul>
						<div class="key">
							<dl>
								<dt><em>评价总分：</em></dt>
								<dd class="totalScore">
									<ul class="rating-level">
										<li><a class="aa<fmt:formatNumber type="number" value="${buyer.evalSum }" maxFractionDigits="0"/>-stars current-rating"  href="#"></a></li>
									</ul>
									<span id="stars2-tips" class="result">
										<fmt:formatNumber type="number" value="${buyer.evalSum }" pattern="#0.0"/>分
									</span>
								</dd>
							</dl>
						</div>
						<ul class="other">
							<li><span>入库时间：</span><fmt:formatDate value="${buyer.orgInfo.validTime }" pattern="yyyy年MM月dd日"/></li>										
							<li><span>所属区域：</span>${buyer.orgInfo.distinctName }</li>
						</ul>
					</div>
				</div>
				
				<!-- Tab页 -->
				<div id="epsTabs">
					<ul>
						<li><a href="#baseParam" id="baseParamTab"><span>基本信息</span></a></li>
						<li><a href="#finance" id="financeTab"><span>财务信息</span></a></li>
						<li><a href="#legal" id="legalTab"><span>法务信息</span></a></li>
						<li><a href="#successCase" id="successCaseTab"><span>成功业绩</span></a></li>
						<li><a href="#evaluate" id="evaluateTab"><span>信用评价</span></a></li>
						<li><a href="#illegalRec" id="illegalRecTab"><span>违规信息</span></a></li>
					</ul>
					<div id="baseParam" class="formLayout form2Pa">
			    		<ul>
							<li><label>所属行业：</label> <span>${buyer.orgInfo.belongIndustry.name}</span></li>
							<li><label>企业类型：</label><span>${buyer.orgInfo.entPrptCN}</span></li>
							<li><label>人员规模：</label><span>${buyer.orgInfo.unitScapeCN}</span></li>
							<li><label>开业日期：</label><span>${buyer.orgInfo.begainDate}</span></li>
			    			<li><label>法定代表人：</label> <span>${buyer.orgInfo.company.croporate}</span></li>
			    			<li><label>公司网址：</label> <span><a href="${buyer.orgInfo.webUrl}" target="_blank">${buyer.orgInfo.webUrl}</a></span></li>
			    			<li class="fullLine"><label>公司地址：</label> <span>${buyer.orgInfo.company.address}</span></li>
			    			
			    			<!-- 商圈会员可以查看联系方式 -->
			    			<c:if test="${isShowContact==true}">
				    			<li><label>电子邮箱：</label><span>${buyer.orgInfo.company.email}</span></li>
				    			<li><label>联系电话：</label><span>${buyer.orgInfo.company.mobilePhone}</span></li>
			    			</c:if>
			    			
							<li class="fullLine"><label>企业产能：</label> <span>${buyer.orgInfo.entCapacity}</span></li>
			    			<li class="fullLine"><label>经营范围：</label> <span>${buyer.orgInfo.bidForRangeName}</span></li>
			    			<li class="fullLine"><label>主营产品：</label> <p>${buyer.orgInfo.mainProducts}</p></li>
			    			<li class="fullLine"><label>企业简介：</label> <p>${buyer.orgInfo.descCn}</p></li>
			    		</ul>
					</div>
					<div id="finance" class="formLayout form2Pa">
						<c:set var="numJ" value="0"></c:set>
				    	<c:forEach var="quality" items="${buyer.orgInfo.qualitys}">
							<c:if test="${quality.qualificationClass.qualityCode == 'C01'}">
									<c:set var="numJ" value="${numJ + 1}"></c:set>
									<h4><span>${quality.qualificationDefine.qualityName }</span></h4>
									<ul>
										<c:forEach var="detail" items="${quality.qualificationDetailSet}" >
											<c:if test="${detail.paramValue != null}">
												<li>${detail.qualityParam.qualityName }：${detail.paramValue} ${detail.qualityParam.unit }</li>
											</c:if>
										</c:forEach>
								    </ul>
							</c:if>
						</c:forEach>
						<c:if test="${numJ == 0}"><div class="sorry">暂无财务信息！</div></c:if>
					</div>
					<div id="legal" class="formLayout form2Pa">
						<c:set var="numF" value="0"></c:set>
						<c:forEach var="quality" items="${buyer.orgInfo.qualitys}">
							<c:if test="${quality.qualificationClass.qualityCode == 'C02'}">
									<c:set var="numF" value="${numF + 1}"></c:set>
									<h4><span>${quality.qualificationDefine.qualityName }</span></h4>
									<ul>
										<c:forEach var="detail" items="${quality.qualificationDetailSet}" >
											<c:if test="${detail.paramValue != null}">
												<li>${detail.qualityParam.qualityName }：${detail.paramValue} </li>
											</c:if>
										</c:forEach>
										<c:if test="${quality.qualificationFile != null}">
											<li><a href="javascript:void(0);" name="qualificationFile" id="${quality.qualificationFile }">文件下载</a></li>
										</c:if>
								    </ul>
							</c:if>
						</c:forEach>
						<c:if test="${numF == 0}"><div class="sorry">暂无法务信息！</div></c:if>
					</div>
					<div id="successCase" class="formLayout form2Pa">
						<c:if test="${fn:length(buyer.orgInfo.successCases) == 0}"><div class="sorry">暂无成功业绩！</div></c:if>
						<c:forEach var="scase" items="${buyer.orgInfo.successCases}">
							<div class="formLayout">
			      				<h5>${scase.projectName}</h5>
			     				<ul>
			     					<li><label>开始时间：</label><span><fmt:formatDate value="${scase.startTime}" pattern="yyyy年MM月dd"/></span></li>
			     					<li><label>结束时间：</label><span><fmt:formatDate value="${scase.endTime}" pattern="yyyy年MM月dd"/></span></li>
			     					<li class="fullLine"><label>采购品目：</label><span>${scase.categoryNames}</span></li>
			     					<li class="fullLine"><label>案例描述：</label><span>${scase.description}</span></li>
			     				</ul>
			    			</div>
						</c:forEach>
					</div>
					<div id="evaluate" class="formLayout">
						<c:forEach var="quotaDetai" items="${quotaDetailList}">
							<div class="evaluate">
								<span class="valueDic">${quotaDetai[0] }：</span> 
								<span class="valuePic">
									<div class="score">
										<span style="width:${(quotaDetai[2]+0.001)*10}%"></span>
									</div>
								</span>
								<span class="value">
									<c:choose>
										<c:when test="${quotaDetai[2]==null}"> &nbsp; 0分</c:when>
										<c:otherwise> &nbsp; <fmt:formatNumber type="number" value="${quotaDetai[2]}" maxFractionDigits="2"/>分</c:otherwise>
									</c:choose>
								</span>
							</div>
						</c:forEach>
						<table class="frontTableList" id="evaluateList">
						      <thead>
						        <tr>
						          <th class="operation">评论级别</th>
						          <th class="left" style="width:40%">评论</th>
						          <th class="left">评价人</th>
						          <th class="left">项目名称</th>
						        </tr>
						      </thead>
					     	  <tbody>
					     	  </tbody>
						</table>
					</div>
					<div id="illegalRec" class="formLayout">
						<c:if test="${fn:length(illegalRecList) == 0}"><div class="sorry">暂无违规记录！</div></c:if>
						<c:forEach var="illegalRec" items="${illegalRecList}">
							<div class="formLayout form2Pa">
			     				<ul>
			     					<li class="fullLine"><label>标题：</label><span>${illegalRec.title}</span></li>
			     					<li class="fullLine"><label>时间：</label><span><fmt:formatDate value="${illegalRec.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
			     					<li class="fullLine"><label>描述：</label>${illegalRec.reason}</li>
			     				</ul>
			    			</div>
						</c:forEach>
					</div>
				</div>
			</div>

		</div>
		<div id="contentSupp" class="index2paR">
			<jsp:include page="/BuyerShowController.do?method=getRecommendBuyer">
				<jsp:param value="10" name="rp"/>
				<jsp:param value="1" name="page"/>
			</jsp:include>
		</div>
	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
	<!--在线客服开始-->
    <%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
    <!--在线客服结束-->
</div>
</body>

<script type="text/javascript">

var BuyerDetailForm={};
var show_list={};
BuyerDetailForm.oTable;
//加载评价列表
BuyerDetailForm.oTable = $('#evaluateList').dataTable({   
	'singleSelect':true,	
	'checkbox':false,		
	'queryColumns':'leval,remark,rater.usName,projectName',
	'hiddenColumns':'rateOrg.supplierId,rateOrg.buyerId,rateOrg.agencyId,isAonymous',
	'alias':'',
	'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
		if(aData.leval=='0'){
			$(nRow).find("td:eq(0)").html('<span class="praise">好评</span>');
		}else if(aData.leval=='1'){
			$(nRow).find("td:eq(0)").html('<span class="mediumReview">中评</span>');
		}else{
			$(nRow).find("td:eq(0)").html('<span class="badReview">差评</span>');
		}
		//匿名
		if(aData.isAonymous=="1"){
			$(nRow).find("td:eq(2)").html('<span>匿名</span>');
		}
		return nRow;
	},
	"sAjaxSource": $('#initPath').val()+"/ShowEvaluateController.do?method=toOrgEvaluateList&org.objId="+$("#buyerOrgInfoId").val()
});

//点击导航
BuyerDetailForm.searchByTitle = function(unitType,districtId,districtLevel) {
	var param = "";
	if(unitType) {  //采购人类型
		param += "&unitType=" + unitType;
	}
	if(districtId) {  //区域id
		param += "&districtId=" + districtId;
	}
	if(districtLevel){
		param += "&districtLevel=" + districtLevel;
	}
	window.location.href = $('#initPath').val()+'/BuyerShowController.do?method=toBuyerList&rp=21&page=1'+ param;
	return false;
}

//加入收藏
BuyerDetailForm.addFavorites = function(favoriteId,favoriteName,favoriteType){
	//判断是否登录
	if(common.isLogin(true,"请先登录再进行收藏！")){
		$.epsDialog({
	        title:'加入收藏',
	        width:400,
	        height:150,
	        url:$('#initPath').val()+'/FavoritesController.do?method=toFavoritesForm&favoriteId='+favoriteId+'&favoriteName='+encodeURIComponent(favoriteName)+'&favoriteType='+favoriteType
		});
	}
}

//加入我的客户
show_list.addClient = function(groupType,orgInfoId,orgInfoName){
	$.epsDialog({
		title:"关注客户",
		width:500,
		height:300,
		url:$('#initPath').val()+"/ConcernController.do?method=toAddConcernForm&groupType="+groupType+"&orgInfoId="+orgInfoId,
		afterLoad: function(){$('#orgInfoName').html(orgInfoName) }
	});
}

//添加附件事件
$.each($("body").find("a[name=qualificationFile]"),function(index,obj){
	$(obj).click(function(){
		$.epsDialog({
			title:"附件下载",
			url:$("#initPath").val()+"/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId="+obj.id,
			width: 600,
			height: 300
			});
	})
})

$(document).ready(function(){	
	$tabs=$('#epsTabs').tabs();//加载tabs

	//选中指定的tab页
	if($("#tabId").val() != "") {
		$("#"+$("#tabId").val()).click();
	}
});
</script>
</html>