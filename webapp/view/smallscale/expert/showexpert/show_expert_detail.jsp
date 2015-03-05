<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>${expert.name}- 专家库 - 阳光易购电子采购与招标平台</title>
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
<input type="hidden" id="expertId" value="${expert.objId}" />
<input type="hidden" id="tabId" value="${tabId}"/>

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
				<a href="javascript:void(0);" onclick="ExpertDetailForm.searchByTitle(null,null,'1');return false;" >专家展示</a>
				<c:if test="${categoryId!=null}">
					<a href="javascript:void(0);" onclick="ExpertDetailForm.searchByTitle('${categoryId}',null,'1');return false;" >${categoryName }</a>
				</c:if>
				<c:if test="${districtId!=null}">
					<a href="javascript:void(0);" onclick="ExpertDetailForm.searchByTitle('${categoryId}','${districtId }','1');return false;" >${districtName }</a>
				</c:if>
				专家详情</div>
			</div>
			<div id="conBody"><!--功能页内容-->
				<div class="imgAndInfo" style="height: 220px;">
					<div id="showImg" style="width: 200px;">
						<div class="short" style="width:160px;height:208px;">
							<img style="width:160px;height:208px;" src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${expert.photo}&fileNameSuffix=_180*240"/>
						</div>
					</div>
					<div id="showInfo" style="margin-left: 200px;">
						<ul class="meta">	
							<li class="detail-title"><span>专家姓名：</span><strong>${expert.name }</strong> 
								<c:if test="${expert.isConsultant=='1'}">
									<a href="javascript:void(0);" title="咨询专家"><img src="<%=request.getContextPath()%>/view/resource/skin/smallscale/img/expert-zx.png"/></a>
								</c:if>
								<c:if test="${expert.isReviewers=='1'}">
									<a href="javascript:void(0);" title="评标专家"><img src="<%=request.getContextPath()%>/view/resource/skin/smallscale/img/expert-ps.png"/></a>
								</c:if>
							</li>
							<li class="detail-title"><span>职业资格等级：</span>${expert.professionQualificationLevelCN } 
								<button type="button" onclick="ExpertDetailForm.addFavorites('${expert.objId }','${expert.name}','05')" class="favBtn" height="25px">加入收藏</button>
							</li>
						</ul>
						<div class="key">
						    <dl>
					   			<dt><em>评价总分：</em></dt>
					   			<dd class="totalScore">
					   						<ul class="rating-level">
					   								<li><a class="aa<fmt:formatNumber type="number" value="0" maxFractionDigits="0"/>-stars current-rating"  href="#"></a></li>
					   						</ul>
					   						<span id="stars2-tips" class="result"><fmt:formatNumber type="number" value="0" pattern="#0.0"/>分</span>
					   			</dd>
					   		</dl>
					    </div>
						<ul class="other">
							<li><span>入库时间：</span><fmt:formatDate value="${expert.validTime }" pattern="yyyy年MM月dd日"/></li>										
							<li><span>所属区域：</span>${expert.district.name }</li>
						</ul>
					</div>
					</div>
					<!-- Tab页 -->
					<div id="epsTabs">
					<ul>
						<li><a href="#baseParam" id="baseParamTab"><span>基本信息</span></a></li>
						<li><a href="#finance" id="financeTab"><span>职称信息</span></a></li>
						<li><a href="#legal" id="legalTab"><span>从业经历</span></a></li>
						<li><a href="#quality" id="qualityTab"><span>教育背景</span></a></li>
						<li><a href="#successCase" id="successCaseTab"><span>培训经历</span></a></li>
					</ul>
					<div id="baseParam" class="formLayout form2Pa">
			    		<ul>
			    			<li><label>政治面貌：</label><span>${expert.politicalLandscapeCN}</span></li>
							<li><label>出生年月：</label><span><fmt:formatDate value="${expert.birthday }" pattern="yyyy年MM月dd日"/></span></li>
							<li class="fullLine"><label>所属行业：</label> <span>${expert.belongIndustry.name}</span></li>
							<li class="fullLine"><label>评审品目：</label><span>${expert.appCategoryName}</span></li>
							<li class="fullLine"><label>评审区域：</label><span>${expert.appDistinctName}</span></li>
			    			<li class="fullLine"><label>从事特长年限：</label> <span>${expert.specifyYear} 年</span></li>
			    			<li class="fullLine"><label>特长描述：</label> <p>${expert.technicalExcellence}</p></li>
			    			<li class="fullLine"><label>经验描述：</label> <p>${expert.tenderExperience}</p></li>
			    			<li class="fullLine"><label>荣誉证书：</label> <div id="honorFile" class="uploadFile">${expert.honorFile}</div></li>
			    		</ul>
			    	</div>
					<div id="finance">
						<c:choose>
							<c:when test="${fn:length(expert.certificates) == 0}"><div class="sorry">暂无职称信息！</div></c:when>
							<c:otherwise>
								<c:forEach var="certificate" items="${expert.certificates}">
									<div class="formLayout form2Pa myTask">
										<h4><span>${certificate.titleName }</span></h4>
										<ul>
											<li>证书编号：${certificate.certificateNo} </li>
											<li>颁发机构：${certificate.issueUnit} </li>
											<li>获得证书时间：<fmt:formatDate value="${certificate.acquireTime }" pattern="yyyy年MM月dd日"/></li>
											<li>证书有效时间：<fmt:formatDate value="${certificate.valDate }" pattern="yyyy年MM月dd日"/></li>
											<c:if test="${certificate.file != null}">
												<li class="fullLine"><a href="javascript:void(0);" name="qualificationFile" id="${certificate.file }">证书附件下载</a></li>
											</c:if>
									    </ul>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="legal">
						<c:choose>
							<c:when test="${fn:length(expert.experiences) == 0}"><div class="sorry">暂无从业经历！</div></c:when>
							<c:otherwise>
								<c:forEach var="experience" items="${expert.experiences}">
									<div class="formLayout form2Pa myTask">
										<h4><span>${experience.orgName }</span></h4>
										<ul>
											<li>职业：${experience.specialty} </li>
											<li>职务：${experience.duty} </li>
											<li>开始时间：<fmt:formatDate value="${experience.startTime }" pattern="yyyy年MM月dd日"/></li>
											<li>结束时间：<fmt:formatDate value="${experience.endTime }" pattern="yyyy年MM月dd日"/></li>
											<li class="fullLine">成就描述：${experience.achievement}</li>
									    </ul>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="quality">
						<c:choose>
							<c:when test="${fn:length(expert.educations) == 0}"><div class="sorry">暂无教育背景！</div></c:when>
							<c:otherwise>
								<c:forEach var="education" items="${expert.educations}">
									<div class="formLayout form2Pa myTask">
										<h4><span>${education.graduateSchool }</span></h4>
										<ul>
											<li>所学专业：${education.speciality.dicName} </li>
											<li>学历：${education.degree.dicName} </li>
											<li>入学时间：<fmt:formatDate value="${education.enrollDate }" pattern="yyyy年MM月dd日"/></li>
											<li>毕业时间：<fmt:formatDate value="${education.graduateDate }" pattern="yyyy年MM月dd日"/></li>
											<c:if test="${education.file != null}">
												<li class="fullLine"><a href="javascript:void(0);" name="qualificationFile" id="${education.file }">证明文件下载</a></li>
											</c:if>
									    </ul>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="successCase">
						<c:choose>
							<c:when test="${fn:length(expert.trainings) == 0}"><div class="sorry">暂无培训经历！</div></c:when>
							<c:otherwise>
								<c:forEach var="training" items="${expert.trainings}">
									<div class="formLayout form2Pa myTask">
										<h4><span>${training.trainingCourse }</span></h4>
										<ul>
											<li class="fullLine">培训机构：${training.trainingOrg} </li>
											<li>开始时间：<fmt:formatDate value="${training.beginDate }" pattern="yyyy年MM月dd日"/></li>
											<li>结束时间：<fmt:formatDate value="${training.endDate }" pattern="yyyy年MM月dd日"/></li>
											<li class="fullLine">课程介绍：${training.courseMemo}</li>
											<c:if test="${training.file != null}">
												<li class="fullLine"><a href="javascript:void(0);" name="qualificationFile" id="${training.file }">证书附件下载</a></li>
											</c:if>
									    </ul>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<div id="contentSupp" class="index2paR">
			<jsp:include page="/ExpertShowController.do?method=getRecommendExpert&rp=5&page=1"></jsp:include>
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
var ExpertDetailForm={};

//加入收藏
ExpertDetailForm.addFavorites = function(favoriteId,favoriteName,favoriteType){
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

//点击导航
ExpertDetailForm.searchByTitle = function(categoryId,districtId,districtLevel) {
	var param = "";
	if(categoryId) {  //品目
		param += "&categoryId=" + categoryCode;
	}
	if(districtId) {  //区域id
		param += "&districtId=" + districtId;
	}
	if(districtLevel){
		param += "&districtLevel=" + districtLevel;
	}
	window.location.href = $('#initPath').val()+'/ExpertShowController.do?method=toExpertList&rp=21&page=1'+ param;
	return false;
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
	//加载tabs
	$tabs=$('#epsTabs').tabs();

	//选中指定的tab页
	if($("#tabId").val() != "") {
		$("#"+$("#tabId").val()).click();
	}

	//荣誉证书
	$('#honorFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=honorFile&isSelect=yes&attachRelaId='+$("#honorFile").text());
	
});
</script>
</html>