<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/projectDetail.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />
<style>
<!--
.hi_60{height:60px;}
.hi_auto{height:auto;}
-->
</style>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>
<!--页面容器 开始-->
<div id="container"> 

<!-- 项目Id -->
<input name="projectId" id="projectId" type="hidden" value="${project.objId}"/>

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
	<div class="page" style="min-height:800px!important;">
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
<p class="package_fontcolor">报价时间：<fmt:formatDate value="${project.evalStartTime }" pattern="yyyy年MM月dd日 HH:mm:ss"/>&nbsp;至&nbsp;<fmt:formatDate value="${project.evalEndTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/> </p>
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
	<c:if test="${projStatus[2]==1}">
		<c:if test="${project.ebuyMethod=='06'}"><button type="button" class="base_btns7" onclick="project_detail_view.intoBiddingRoom('${project.objId}','${project.ebuyMethod}')"><span>竞价厅</span></button></c:if>
	</c:if>
	<span <c:if test="${projStatus[4]!=1 && projStatus[3]!=1}">class="hidden"</c:if> >
		<button type="button" class="base_btns7" onclick="project_detail_view.evaluate('${project.objId}','${project.projName}')"><span>评价</span></button>
		<button type="button" class="base_btns7" onclick="project_detail_view.report('${project.objId}','tell','${project.ebuyMethod}')"><span>投诉</span></button>
		<button type="button" class="base_btns7" onclick="project_detail_view.report('${project.objId}','complain','${project.ebuyMethod}')"><span>举报</span></button>
	</span>
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
<div class="boxx" style="width:100%">
		<div class="hd">
			<h3>项目信息</h3>
		</div>
		<div class="bd">
			<dl>
				<dt>项目轮次</dt>
				<c:forEach var ="bargainTurn" items= "${bargainTurnList}" varStatus="status">
					<dd><span class="base_black">第${bargainTurn.turnNo}轮：</span><fmt:formatDate value="${bargainTurn.startTime }" pattern="yyyy年MM月dd日 HH:mm:ss"/> 至 <fmt:formatDate value="${bargainTurn.endTime }" pattern="yyyy年MM月dd日 HH:mm:ss"/></dd>
				</c:forEach>
				
				<dt>支付方式</dt>
				<dd class="base_cols_2"><span class="base_black">交货时间：</span>${projectPayInfo.deliveryDate}</dd>
				<dd class="base_cols_2"><span class="base_black">交货地点：</span>${projectPayInfo.deliveryAddress}</dd>
				<dd class="base_cols_2"><span class="base_black">交货方式：</span>${projectPayInfo.deliveryTypeCN}</dd>
				<dd class="base_cols_2"><span class="base_black">支付方式：</span>${projectPayInfo.payTypeCN}</dd>
				<dd><span class="base_black">其他说明：</span>${projectPayInfo.supplement}</dd>
				
				<dt>联系方式</dt>
				<dd class="base_cols_4"><span class="base_black">联系人：</span>${projectContactInfo.linker}</dd>
				<dd class="base_cols_4"><span class="base_black">移动电话：</span>${projectContactInfo.mobilePhone}</dd>
				<dd class="base_cols_4"><span class="base_black">固定电话：</span>${projectContactInfo.fixedTelephone}</dd>
				<dd class="base_cols_4"><span class="base_black">传真：</span>${projectContactInfo.fax}</dd>
				<dd class="base_cols_2"><span class="base_black">地址：</span>${projectContactInfo.address} </dd>
				<dd class="base_cols_2"><span class="base_black">邮编：</span>${projectContactInfo.postCode}</dd>
			</dl>
		</div>
	</div>
</div>
        
<div class="base_box package_details_intro">
<div class="b_bd layoutfix">
<div class="pripackage_date_main">
	<h5>报价详情</h5>
	<div class="describ">
		1、双击单元格，可以查看对指定需求的报价详情。 <br/>
		2、字体为红色的为对应需求的最低报价。
		
		<c:if test="${!empty bulletin.objId}">
			<span style="float:right;margin-right:15px;"><a class="sysicon report" onclick="common.goToBulletinDetail('${project.objId}','${bulletin.bullType}');" href="javascript:void(0);">查看公告</a>&nbsp;<a class="sysicon report_user" onclick="project_detail_view.showSignUpRecord('${supplierId}');" href="javascript:void(0);">报名信息</a> </span>
		</c:if>
	</div>
	<table class="pripackage_date_list">
	<thead>
			<tr>
				<td>需求描述</td>
				<td>报价描述</td>
				<td><em class="base_txtred">我的报价</em></td>
			</tr>
	</thead>
	<tbody id="minPriceRecordTable">
	
		<c:set var ="requireId" value=""/>
		<c:set var ="requireCount" value="0"/>
		<c:set var ="minTotal" value="0"/>
		
		<c:forEach var="biddingRecordObject" items="${biddingRecordObjectList}" varStatus="status">
		
		<tr>	
			<!-- 第一列表头 -->
			<c:if test="${biddingRecordObject[5]!=null}">
				<td class="col_big desc hi_60" rowspan="${biddingRecordObject[5]}">
				<div>
			  		<c:if test="${biddingRecordObject[10]!=null}">
			  			<a onclick="common.geToGoodsDetail('${biddingRecordObject[10]}');" href="javascript:void(0);">${biddingRecordObject[2]}</a>
			  		</c:if>
					<c:if test="${biddingRecordObject[10]==null}">${biddingRecordObject[2]}</c:if>
				</div>
				</td>
			</c:if>

			<c:choose>
				<c:when test="${biddingRecordObject[3]==null}">
					<td colspan="2">暂未报价</td>
				</c:when>
				<c:otherwise>
					<td ondblclick="project_detail_view.showRequireRecordDetail('${biddingRecordObject[0]}','${biddingRecordObject[1]}');" class="price_td col_big desc">
						<div>
						<c:choose>
							<c:when test="${biddingRecordObject[7]!=null}"><a onclick="common.geToGoodsDetail('${biddingRecordObject[7]}');" href="javascript:void(0);">${biddingRecordObject[6]}</a><br />${biddingRecordObject[8]}</c:when>
							<c:otherwise>${biddingRecordObject[6]}</c:otherwise>
						</c:choose>
						</div>
					</td>
					<td class="col_sum <c:if test="${requireId!=biddingRecordObject[1]}">base_txtred<c:set var="minTotal" value="${minTotal+biddingRecordObject[3]}"/></c:if>">
						<div>￥<span id="${biddingRecordObject[1]}" name="cellPrice" ><fmt:formatNumber value="${biddingRecordObject[3]}" pattern="#,##0.00#"/></span>元</div>
						<div><a href="javascript:void(0);" onclick="project_detail_view.downLoadFile('${biddingRecordObject[9]}');">报价文件</a></div>
					</td>
				</c:otherwise>
			</c:choose>
		</tr>
		
		<c:set var ="requireId" value="${biddingRecordObject[1]}"/>
		</c:forEach>
		
		<tr>
			<td>
				<c:if var="isShowBudget" test="${isShowBudget}">总预算：<fmt:formatNumber value="${project.budgetTotalMoney }" pattern="#,##0.00#"/>（元）</c:if>
			</td>
			<td></td>
			<td class="col_sum" style="font-weight: blod;">最低报价合计：<fmt:formatNumber value="${minTotal}" pattern="#,##0.00#"/>（元）</td>
		</tr>
	
	</tbody></table>
</div>	

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

<script type="text/javascript">
var project_detail_view = {};

//计算
project_detail_view.caculator  = function(){
	$.each($("#minPriceRecordTable").find("tr"),function(index , obj){
		var tdObj =  $(obj).find("span[name=cellPrice]");
		var cellTotal = 0;
		var saveTotal = 0;
		$.each(tdObj ,function(i , o){
			var cellPrice = Number( $(o).html().replace(/,/g,'') );
			cellTotal += cellPrice;
			//算节省的钱
			var requirePrice = Number( $("span[id=require"+ tdObj.attr("id").split(",")[1] +"]").attr("price") ) ;
			saveTotal += ( requirePrice-cellPrice );//节省总价
			$(o).parent().parent().find("span[name=saveCell]").html( formatAmount( requirePrice-cellPrice ,2) );
		})
		$(obj).find("span[name=cellTotal]").html( formatAmount(cellTotal,2) );
		$(obj).find("span[name=saveTotal]").html( formatAmount(saveTotal,2) );
	})
}

//供应商详情
project_detail_view.goSupplierPage = function(supplierId){
	$.epsDialog({
		id:"showSupplierDetail",
		title:"供应商详情",
		url:$("#initPath").val()+"/ExOrgInfoController.do?method=getExAllBaseInfo&orgId="+supplierId
	})
}

//双击-供应商对需求的所有报价
project_detail_view.showRequireRecordDetail = function( detailId ,requireId){
	$.epsDialog({
		title:"所有历史报价",
		url:$("#initPath").val()+"/BiddingRecordHistoryController.do?method=toHistoryByDetail&detailId="+detailId+"&requireId="+requireId
	})
}

//显示报名记录
project_detail_view.showSignUpRecord = function(supplierId){
	$.epsDialog({
		id:"",
		title:"供应商报名信息",
		url:$("#initPath").val()+"/SupplierSignupController.do?method=toSupplierSignUpDetail&objId="+supplierId+"&projectId="+$("#projectId").val()
	})
}

//显示报名的附件
project_detail_view.showSignUpRecordFile = function(supplierId){
	$.getJSON($("#initPath").val()+"/SupplierSignupController.do?method=getSupplierSignUp",{"objId":supplierId,"projectId":$("#projectId").val() },function(json){
		if(json.success){
			$.epsDialog({
				title:"附件下载",
				url:$("#initPath").val()+"/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId="+json.signUprecord.attachRelaId,
				width: 600,
				height: 300
			});
		}
	})
}

//添加报价附件事件
project_detail_view.downLoadFile = function(id){
	$.epsDialog({
		title:"附件下载",
		url:$("#initPath").val()+"/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId="+id,
		width: 600,
		height: 300
	});
}

//切换
project_detail_view.exchange = function(){
	if( $("#extInfoDiv").attr("style")!=null && $("#extInfoDiv").attr("style").indexOf("block")>=0 ){
		$("#extInfoDiv").hide();
	}else{
		$("#extInfoDiv").show();
	}
}

//进入竞价议价厅
project_detail_view.intoBiddingRoom = function( projectId ,ebuyMethod){
	var url = $('#initPath').val()+"/BargainProjectController.do?method=toSupplierBargainPage&objId="+projectId;
	if(ebuyMethod == '05'){
		url = $('#initPath').val()+"/TalkProjectController.do?method=toBuyerTalkHall&objId="+projectId;
		loadPage_openModelWindow(url,"960");
		mySignupProject.oTable.fnDraw();
	} else {
		window.open(url);
	}
}

//评价(显示被评价的人)
project_detail_view.evaluate = function(projectId,projectName){
	$.epsDialog({
		id:"evaluateDailog", 
		title:"对参与项目的机构评价",
		url:$("#initPath").val()+"/view/agreement/bargin/project/project_evaluate_div.jsp?userType=supplier&projectId="+projectId+"&projectName="+native2ascii(projectName)
	});
}


//显示被投诉举报的人
project_detail_view.report = function(projectId,type,ebuyMethod){
	$.epsDialog({
		id:"complainDiv", 
		title:"选择参与项目的机构",
		url:$("#initPath").val()+"/view/agreement/bargin/project/project_complain_div.jsp?userType=supplier&projectId="+projectId+"&type="+type+"&ebuyMethod="+ebuyMethod
	});
}

$(document).ready(function(){
	//一次计算
	project_detail_view.caculator();

	$(".price_td").hover(function(){$(this).addClass("price_td_hilight");},function(){$(this).removeClass("price_td_hilight");})
})
</script>
</body>
</html>