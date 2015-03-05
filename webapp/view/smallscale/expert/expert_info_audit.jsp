<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="expertInfoAuditForm" method="post" modelAttribute="expertInfo">
	<input type="hidden"" id="expertId" name="objId" value="${expertInfo.objId}"/>
	<input type="hidden" id="honorFileId" value="${expertInfo.honorFile}"/>
	
	<div id="infoDiv" class="epsTabs">
		<!-- Tab页 -->
		<ul>
			<li><a href="#baseParam" id="baseParamTab"><span>基本信息</span></a></li>
			<li><a href="#education" id="educationTab"><span>教育经历信息</span></a></li>
			<li><a href="#training" id="trainingTab"><span>培训经历信息</span></a></li>
			<li><a href="#experience" id="experienceTab"><span>任职经历信息</span></a></li>
			<li><a href="#certificate" id="certificateTab"><span>职称信息</span></a></li>
		</ul>
		
		<!-- 基本信息开始 -->
		<div id="baseParam" class="formLayout form2Pa">
			<div class="k1">
				<div class="img_250_2" id="newPreview">
					<c:choose>
						<c:when test="${expertInfo.photo==null}">
							<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
						</c:when>
						<c:otherwise>
							<img src="<c:url value="AttachmentController.do?method=showImg&objId=${expertInfo.photo}" />" width="200px" height="200px">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<ul>
				<li><label>姓名：</label> <span>${expertInfo.user.emp.name}</span></li>
				<li><label>性别：</label> <span>${expertInfo.user.emp.sex_value}</span></li>
				<li><label>所属行业：</label> <span>${expertInfo.belongIndustry.name}</span></li>
				<li><label>国家职业资格等级：</label> <span>${expertInfo.professionQualificationLevelCN}</span></li>
				<li><label>政治面貌：</label> <span>${expertInfo.politicalLandscapeCN}</span></li>
    			<li><label>是否退休：</label>
    				<span><c:choose><c:when test="${expertInfo.isRetired}">已退休</c:when><c:otherwise>未退休</c:otherwise></c:choose></span>
    			</li>
				<li><label>出生年月：</label> <span><fmt:formatDate value="${expertInfo.birthday}" pattern="yyyy-MM"/></span></li>
				<li><label>联系手机：</label> <span>${expertInfo.user.emp.mobile}</span></li>
				<li class="fullLine"><label>Email：</label> <span>${expertInfo.user.emp.email}</span></li>
    			<li><label>MSN：</label> <span>${expertInfo.user.emp.msn}</span></li>
				<li><label>QQ：</label> <span>${expertInfo.user.emp.qq}</span></li>
				<li><label>办公电话：</label> <span>${expertInfo.user.emp.telOffice}</span></li>
				<li><label>传真：</label> <span>${expertInfo.user.emp.fax}</span></li>
				<li class="fullLine"><label>所属地区：</label> <span>${expertInfo.district.parent.parent.name} ${expertInfo.district.parent.name} ${expertInfo.district.name}</span></li>
				<li class="fullLine"><label>详细通信地址：</label> <span>${expertInfo.user.emp.address}</span></li>
				<li class="fullLine"><label>评审区域名称：</label> <span>${expertInfo.appDistinctName}</span></li>
				<li class="fullLine"><label>评审品目名称：</label> <span>${expertInfo.appCategoryName}</span></li>
				<li class="fullLine"><label>从事特长年限：</label> <span>${expertInfo.specifyYear}</span></li>
				<li class="fullLine"><label>特长描述：</label> <span>${expertInfo.technicalExcellence}</span></li>
				<li class="fullLine"><label>经验描述：</label> <span>${expertInfo.tenderExperience}</span></li>
				<li class="fullLine">
	     			<label>荣誉证书：</label>
	     			<div id="honorFile" class="uploadFile"></div>
    	    	</li>
    		</ul>
		</div>
		<!-- 基本信息结束 -->
		
		<!-- 教育经历信息开始 -->
		<div id="education" class="formLayout form2Pa">
			<c:set var="numEd" value="0"></c:set>
	    	<c:forEach var="education" items="${expertInfo.educations}">
				<c:set var="numEd" value="${numEd + 1}"></c:set>
				<ul>
					<li><label>毕业院校：</label><span>${education.graduateSchool}</span></li>
					<li><label>入学时间：</label><span><fmt:formatDate value="${education.enrollDate}" pattern="yyyy-MM-dd"/></span></li>
					<li><label>毕业时间：</label><span><fmt:formatDate value="${education.graduateDate}" pattern="yyyy-MM-dd"/></span></li>
					<li><label>所学专业：</label><span>${education.speciality.dicName}</span></li>
					<li><label>学历：</label><span>${education.degree.dicName}</span></li>
			    </ul>
			</c:forEach>
			<c:if test="${numEd == 0}"><div>暂无教育经历信息！</div></c:if>
		</div>
		<!-- 教育经历信息结束 -->
		
		<!-- 培训经历信息开始 -->
		<div id="training" class="formLayout form2Pa">
			<c:set var="numTr" value="0"></c:set>
	    	<c:forEach var="training" items="${expertInfo.trainings}">
				<c:set var="numTr" value="${numTr + 1}"></c:set>
				<ul>
					<li><label>培训课程：</label><span>${training.trainingCourse}</span></li>
					<li><label>培训机构：</label><span>${training.trainingOrg}</span></li>
					<li><label>开始时间：</label><span><fmt:formatDate value="${training.beginDate}" pattern="yyyy-MM-dd"/></span></li>
					<li><label>结束时间：</label><span><fmt:formatDate value="${training.endDate}" pattern="yyyy-MM-dd"/></span></li>
					<li><label>课程介绍：</label><span>${training.courseMemo}</span></li>
			    </ul>
			</c:forEach>
			<c:if test="${numTr == 0}"><div>暂无培训经历信息！</div></c:if>
		</div>
		<!-- 培训经历信息结束 -->
		
		<!-- 任职经历信息开始 -->
		<div id="experience" class="formLayout form2Pa">
			<c:set var="numEx" value="0"></c:set>
	    	<c:forEach var="experience" items="${expertInfo.experiences}">
				<c:set var="numEx" value="${numEx + 1}"></c:set>
				<ul>
					<li><label>工作单位：</label><span>${experience.orgName}</span></li>
					<li><label>职业：</label><span>${experience.specialty}</span></li>
					<li><label>职务：</label><span>${experience.duty}</span></li>
					<li><label>开始时间：</label><span><fmt:formatDate value="${experience.startTime}" pattern="yyyy-MM-dd"/></span></li>
					<li><label>结束时间：</label><span><fmt:formatDate value="${experience.endTime}" pattern="yyyy-MM-dd"/></span></li>
					<li><label>成就描述：</label><span>${experience.achievement}</span></li>
			    </ul>
			</c:forEach>
			<c:if test="${numEx == 0}"><div>暂无任职经历信息！</div></c:if>
		</div>
		<!-- 任职经历信息结束 -->
		
		<!-- 职称信息开始 -->
		<div id="certificate" class="formLayout form2Pa">
			<c:set var="numCe" value="0"></c:set>
	    	<c:forEach var="certificate" items="${expertInfo.certificates}">
				<c:set var="numCe" value="${numCe + 1}"></c:set>
				<ul>
					<li><label>职称名称：</label><span>${certificate.titleName}</span></li>
					<li><label>证书编号：</label><span>${certificate.certificateNo}</span></li>
					<li><label>颁发机构：</label><span>${certificate.issueUnit}</span></li>
					<li><label>获得证书时间：</label><span><fmt:formatDate value="${certificate.acquireTime}" pattern="yyyy-MM-dd"/></span></li>
					<li><label>证书有效时间：</label><span><fmt:formatDate value="${certificate.valDate}" pattern="yyyy-MM-dd"/></span></li>
			    </ul>
			</c:forEach>
			<c:if test="${numCe == 0}"><div>暂无职称信息！</div></c:if>
		</div>
		<!-- 职称信息结束 -->
	</div>
</form:form>
	
<div class="conOperation">
	<button class="operationBtnDiv" type="button" id="agreeBtn"><span>审核通过</span></button>
	<button class="operationBtnDiv" type="button" id="refuseBtn"><span>审核不通过</span></button>
	<button type="button" name="historyBackBtn" id="returnBut"><span>返回</span></button>
</div>

<script>
/**
 * 专家审核页面
 * create by likg
 */
var ExpertInfoAuditForm = {};

//审核
ExpertInfoAuditForm.auditExpertInfo=function(expertId,auditStatus){
	var jsonObj = formToJsonObject('expertInfoAuditForm');
	jsonObj.auditStatus=auditStatus;
	var url = $('#initPath').val()+'/ExpertInfoController.do?method=auditExpertInfo';

	$.getJSON(url,jsonObj,function(json){
		if(json.failure){return;}
		$('#returnBut').click();
	});
}

$(document).ready(function(){
	//加载专家扩展信息
  	var $tabs=$("#infoDiv").tabs();

	//加载专家的荣誉证书附件
  	var honorFileId = $('#honorFileId').val();
  	if(null!=honorFileId && ""!=honorFileId){
  		$('#honorFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=honorFile&isSelect=false&attachRelaId='+honorFileId);
  	}

	//审核通过
	$('#agreeBtn').click(function(){
		if(window.confirm("确认通过吗")){
			ExpertInfoAuditForm.auditExpertInfo($("#expertId").val(), '02');
		}
	});
	
	//审核不通过	
	$('#refuseBtn').click(function(){
		if(window.confirm("确认不通过吗")){
			ExpertInfoAuditForm.auditExpertInfo($("#expertId").val(), '03');
		}
	});
  
});
</script>