<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page import="com.gpcsoft.srplatform.common.utils.SysInfo"%>

<form:form id="ExBaseOrgInfoDetailForm" method="post" modelAttribute="orgInfo">
	<input type="hidden" id="bidForRange" name="bidForRange" value="${orgInfo.bidForRange}"/>
	<c:set var="roleType" value=""></c:set>
	<c:forEach var="role" items="${roles}" varStatus="status">
		<c:set var="roleType" value="${roleType},${role.type}"></c:set>
	</c:forEach>
		
	<div id="infoDiv">
		<ul>
			<li><a href="#baseParam" id="baseParamTab"><span>基本信息</span></a></li>
			<li><a href="#finance" id="financeTab"><span>财务信息</span></a></li>
			<li><a href="#legal" id="legalTab"><span>法务信息</span></a></li>
			<li><a href="#quality" id="qualityTab"><span>企业资质</span></a></li>
			<li><a href="#successCase" id="successCaseTab"><span>成功案例</span></a></li>
		</ul>
		<div id="baseParam" class="formLayout form2Pa">
			<div class="k1" style="float: left;">
				<div class="img_250_2" id="newPreview" style="width: 175px;height: 175px;">
					<img src="<c:url value="AttachmentController.do?method=showImg&objId=${orgInfo.logo}" />" width="175px" height="175px">
				</div>
			</div>
			<ul>
				<li class="fullLine"><label>企业名称：</label> <span>${orgInfo.orgName}</span></li>
				<li><label>机构代码：</label> <span>${orgInfo.orgCode}</span></li>
				<li><label>机构子域名：</label> <span>http://<%=SysInfo.getInstance().getSitename()%>/${orgInfo.company.orgSite}</span></li>
				<li class="fullLine"><label>所属行业：</label> <span>${orgInfo.belongIndustry.name}</span></li>
				<li><label>企业类型：</label><span>${orgInfo.entPrptCN}</span></li>
				<li><label>人员规模：</label><span>${orgInfo.unitScapeCN}</span></li>
				<li><label>开业日期：</label><span>${orgInfo.begainDate}</span></li>
    			<li><label>法定代表人：</label> <span>${orgInfo.company.croporate}</span></li>
    			<li><label>联系手机：</label> <span>${orgInfo.company.mobilePhone}</span></li>
    			<li><label>公司网址：</label> <span><a href="${orgInfo.webUrl}" target="_blank">${orgInfo.webUrl}</a></span></li>
    			<li><label>办公电话：</label> <span>${orgInfo.company.tel}</span></li>
    			<li><label>办公传真：</label> <span>${orgInfo.company.fax}</span></li>
    		</ul>
    		<ul>
    			<!-- 采购人不用显示如下信息 -->
    			<c:if test="${!empty orgInfo.supplierId}">
	    			<li><label>是否厂商：</label>
	    			<span><c:choose>
	    				<c:when test="${fn:contains(roleType,'m')}">是</c:when>
	    				<c:otherwise>否</c:otherwise></c:choose></span>
	    			</li>
	    			<li><label>是否服务商：</label>
	    			<span><c:choose>
	    				<c:when test="${fn:contains(roleType,'r')}">是</c:when>
	    				<c:otherwise>否</c:otherwise></c:choose></span>
	    			</li>
    			</c:if>
    			<li class="fullLine"><label>联系人：</label> <span>${orgInfo.user.emp.name}</span></li>
    			<li class="fullLine"><label>联系人电话：</label> <span>${orgInfo.user.emp.mobile}</span></li>
    			<li class="fullLine"><label>办公邮箱：</label> <span>${orgInfo.company.email}</span></li>
    			<li class="fullLine"><label>公司地址：</label> <span>${orgInfo.company.address}</span></li>
				<li class="fullLine"><label>企业产能：</label> <span>${orgInfo.entCapacity}</span></li>
    			<li class="fullLine"><label>经营范围：</label> <span>${orgInfo.bidForRangeName}</span></li>
    			<li class="fullLine"><label>主营产品：</label> <span>${orgInfo.mainProducts}</span></li>
    			<li class="fullLine"><label>企业简介：</label> <p>${orgInfo.descCn}</p></li>
    		</ul>
		</div>
		
		<!-- 扩展信息部分开始 -->
		<div id="finance" class="formLayout form2Pa">
			<c:set var="numF" value="0"></c:set>
	    	<c:forEach var="quality" items="${orgInfoWithEx.qualitys}">
				<c:if test="${quality.qualificationClass.qualityCode == 'C01'}">
				<c:set var="numF" value="${numF + 1}"></c:set>
						<h4><span>${quality.qualificationDefine.qualityName }</span></h4>
						<ul>
							<c:forEach var="detail" items="${quality.qualificationDetailSet}" >
								<c:choose>
									<c:when test="${detail.qualityParam.paramType=='5'}">
										<c:if test="${detail.paramValue != null}">
										<li>${detail.qualityParam.qualityName }：
											<c:forEach var = "level" items = "${fn:split(detail.qualityParam.level,',')}">
													<c:if test="${(fn:split(level,'#'))[1] == detail.paramValue}">
														${(fn:split(level,'#'))[0] }
													</c:if>
											</c:forEach>
										</li>
										</c:if>
									</c:when>
									<c:otherwise>
										<c:if test="${detail.paramValue != null}">
										<li>${detail.qualityParam.qualityName }：${detail.paramValue} ${detail.qualityParam.unit }</li>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					    </ul>
				</c:if>
			</c:forEach>
			<c:if test="${numF == 0}"><div>暂无财务信息！</div></c:if>
		</div>
		<div id="legal" class="formLayout form2Pa">
			<c:set var="numL" value="0"></c:set>
			<c:forEach var="quality" items="${orgInfoWithEx.qualitys}">
				<c:if test="${quality.qualificationClass.qualityCode == 'C02'}">
				<c:set var="numL" value="${numL + 1}"></c:set>
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
			<c:if test="${numL == 0}"><div>暂无法务信息！</div></c:if>
		</div>
		<div id="quality" class="formLayout form2Pa">
			<c:set var="num" value="0"></c:set>
			<c:forEach var="quality" items="${orgInfoWithEx.qualitys}">
				<c:if test="${quality.qualificationClass.isDisplay && quality.qualificationClass.qualityCode != 'C01' && quality.qualificationClass.qualityCode != 'C02'}">
					<c:set var="num" value="${num + 1}"></c:set>
					<div class="importantNote myTask 
						<c:if test="${num % 2 == 1}">myTaskL</c:if>
						<c:if test="${num % 2 == 0}">myTaskR</c:if>
					">
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
					</div>
				</c:if>
			</c:forEach>
			<c:if test="${num == 0}"><div>暂无资质信息！</div></c:if>
		</div>
		<div id="successCase" class="formLayout form2Pa">
			<c:if test="${empty orgInfoWithEx.successCases || fn:length(orgInfoWithEx.successCases)<=0}">
				<div>暂无成功案例信息！</div>
			</c:if>
			<c:forEach var="scase" items="${orgInfoWithEx.successCases}">
				<div class="formLayout">
      				<h5>${scase.projectName}</h5>
     				<ul>
     					<li><label>开始时间：</label><span><fmt:formatDate value="${scase.startTime}" pattern="yyyy年MM月dd日"/></span></li>
     					<li><label>结束时间：</label><span><fmt:formatDate value="${scase.endTime}" pattern="yyyy年MM月dd日"/></span></li>
     					<li class="fullLine"><label>采购品目：</label><span>${scase.categoryNames}</span></li>
     					<li class="fullLine"><label>案例描述：</label><span>${scase.description}</span></li>
     				</ul>
    			</div>
			</c:forEach>
		</div>
	</div>
	</form:form>
	
	<div class="conOperation">
		<button class="operationBtnDiv" type="button" id="close"><span>关闭</span></button>
	</div>
	<!-- 扩展信息部分结束 -->

<script>
var ExBaseOrgInfoDetailForm={};

$(document).ready(function(){
  	//加载扩展信息
  	var $tabs=$("#infoDiv").tabs();

	//回填投标范围及类别
	if($('#bidForRange').val()!=null && $('#bidForRange').val()!= ""){
		var bidForRange=$('#bidForRange').val().split("##||##");
		$('#bidForRangeSpan').text(bidForRange[1]);
	}

	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})

	//下载附件
	$.each($("a[name=qualificationFile]"),function(index,obj){
		$(obj).click(function(){
			$.epsDialog({
				title:"附件下载",
				url:$("#initPath").val()+"/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId="+obj.id,
				width: 600,
				height: 300
				});
		})
	})	
	
});
</script>