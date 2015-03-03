<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.srplatform.common.utils.SysInfo"%>

<form:form id="OrgInfoAuditForm" method="post" modelAttribute="orgInfo">
	<input type="hidden" id="objId" name="objId" value="${orgInfo.objId}"/>
	<input type="hidden" id="currentId" name="currentId" value="<c:out value="${orgInfo.currentId}"/>"/>
	<input type="hidden" id="useStatus" name="useStatus" value="<c:out value="${orgInfo.useStatus}"/>"/>
	<input type="hidden" id="auditRole" name="auditRole" value="${auditRole}"/>
	<input type="hidden" id="bidForRange" name="bidForRange" value="${orgInfo.bidForRange}"/>
	
	<c:set var="roleType" value=""></c:set>
	<c:forEach var="role" items="${roles}" varStatus="status">
		<c:set var="roleType" value="${roleType},${role.type}"></c:set>
	</c:forEach>
			
	<div id="baseParam">
		<div class="formLayout imgAndForm">
			<div class="k1">
				<div class="img_250_2" id="newPreview">
					<c:choose>
						<c:when test="${orgInfo.logo==null}">
							<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
						</c:when>
						<c:otherwise>
							<img src="<c:url value="AttachmentController.do?method=showImg&objId=${orgInfo.logo}" />" width="200px" height="200px">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<ul>
				<li class="fullLine"><label>企业名称：</label> <span>${orgInfo.orgName}</span></li>
				<li class="fullLine"><label>机构代码：</label> <span>${orgInfo.orgCode}</span></li>
				<li class="fullLine"><label>机构子域名：</label><span>http://<%=SysInfo.getInstance().getSitename()%>/${orgInfo.company.orgSite}</span></li>
				<li class="fullLine"><label>所属行业：</label> <span>${orgInfo.belongIndustry.name}</span></li>
				<li class="fullLine"><label>行政区域：</label> <span>${orgInfo.distinctName}</span></li>
				<li class="fullLine"><label>企业类型：</label><span>${orgInfo.entPrptCN}</span></li>
				<li class="fullLine"><label>人员规模：</label><span>${orgInfo.unitScapeCN}</span></li>
				<li class="fullLine"><label>开业日期：</label><span>${orgInfo.begainDate}</span></li>
		   	</ul>
		</div>
		<div class="formLayout form2Pa">
			<ul>	
				<li class="fullLine"><label>法定代表人：</label> <span>${orgInfo.company.croporate}</span></li>
		   		<li class="fullLine"><label>公司网址：</label> <span><a href="${orgInfo.webUrl}" target="_blank">${orgInfo.webUrl}</a></span></li>
		   		<li><label>办公电话：</label> <span>${orgInfo.company.tel}</span></li>
		   		<li><label>办公传真：</label> <span>${orgInfo.company.fax}</span></li>
		   		<li class="fullLine"><label>联系人：</label> <span>${orgInfo.user.emp.name}</span></li>
    			<li class="fullLine"><label>联系人电话：</label> <span>${orgInfo.user.emp.mobile}</span></li>
		   		<li class="fullLine"><label>办公邮箱：</label> <span>${orgInfo.company.email}</span></li>
		   		<li class="fullLine"><label>公司地址：</label> <span>${orgInfo.company.address}</span></li>
				<li class="fullLine"><label>企业产能：</label> <span>${orgInfo.entCapacity}</span></li>
		   		<li class="fullLine"><label>经营范围：</label> <span>${orgInfo.bidForRangeName}</span></li>
		   		<li class="fullLine"><label>主营产品：</label> <span>${orgInfo.mainProducts}</span></li>
		   		<li class="fullLine"><label>企业简介：</label> <span>${orgInfo.descCn}</span></li>
		   	</ul>
		</div>
	</div>
	<div id="infoDiv" class="epsTabs">
		<ul>
			<li><a href="#finance" id="financeTab"><span>财务信息</span></a></li>
			<li><a href="#legal" id="legalTab"><span>法务信息</span></a></li>
		</ul>
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
	</div>
	<div class="formLayout form2Pa">
		<ul>
			<li class="formTextarea"><label>审核意见：</label><textarea id="opinion" name="opinion" maxLength="100"></textarea></li>
		</ul>
	</div>
</form:form>
	
<div class="conOperation">
	<button class="operationBtnDiv" type="button" id="agreeBtn"><span>审核通过</span></button>
	<button class="operationBtnDiv" type="button" id="refuseBtn"><span>审核不通过</span></button>
	<button type="button" name="historyBackBtn" ><span>返回</span></button>
	<button type="button" class="largeBtn" id="viewHistory"><span>查看历史</span></button>
</div>

<script>
var OrgInfoAuditForm={};

//审核
OrgInfoAuditForm.auditOrgInfo=function(orgInfoId,auditStatus){
	  var jsonObj = formToJsonObject('OrgInfoAuditForm');
	  jsonObj.auditStatus=auditStatus;
	  var url = $('#initPath').val()+'/OrgInfoController.do?method=auditOrgInfo';

	  $.getJSON(url,jsonObj,function(json){
	      if(json.failure){return;}
	      alert('审核成功!');
	      var auditRole = $('#auditRole').val();
	      if(auditRole=="SUPPLIER") {
	    	  $('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/suppliers/suppliers/supplier_audit_list.jsp");
	      }else if(auditRole=="BUYER") {
	    	  $('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/buyers/buyers/buyer_audit_list.jsp");
	      }else if(auditRole=="AGENCY") {
	    	  $('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/agency/agency/agency_audit_list.jsp");
	      }else {
	    	  $('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/organization/manager/organization_audit_list.jsp");
	      }
	  });
	}

$(document).ready(function(){
	$("#OrgInfoAuditForm").validate();
	
	//加载扩展信息
  	var $tabs=$("#infoDiv").tabs();
  	
	//回填投标范围及类别
	if($('#bidForRange').val()!=null && $('#bidForRange').val()!= ""){
		var bidForRange=$('#bidForRange').val().split("##||##");
		$('#bidForRangeSpan').text(bidForRange[1]);
	}
	
	$('#agreeBtn').click(function(){//通过
		 if(window.confirm("确认通过吗")){
				OrgInfoAuditForm.auditOrgInfo($("#objId").val(),'02');
		 }
	});
	
	$('#refuseBtn').click(function(){//不通过
		if($('#opinion').val()==null || $('#opinion').val()==''){alert('请输入审核不通过的意见！'); return ;}
		if(window.confirm("确认不通过吗")){
			OrgInfoAuditForm.auditOrgInfo($("#objId").val(),'03');
		}
	});
	
	//查看历史
	$("#viewHistory").click(function(){
		var url = $('#initPath').val()+"/OrgInfoController.do?method=getOrgHistory&id="+$("#objId").val();
		$.epsDialog({
	        title:'机构历史',
	        url:url
	    }); 
	});

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
})
</script>