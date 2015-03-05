<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--资质证书-->
<div class="introduction">
	<div class="titleright">
		<h2 class="marginright">财务信息</h2>
	</div>
	<div class="conrig">
		<c:if test="${fn:length(supplier.orgInfo.qualitys) == 0}">
		<div style="padding-left: 8px;" class="dataTables_paginate paging_full_numbers" id="QuotaList_paginate">
		<span class="l">没有检索到数据！</span>
		</div>
		</c:if>
		<c:forEach var="quality" items="${supplier.orgInfo.qualitys}">
			<div class="list">
			<c:if test="${quality.qualificationClass.qualityCode == 'C01'}">
				<h2>${quality.qualificationDefine.qualityName }</h2>
				<ul>
					<c:forEach var="detail" items="${quality.qualificationDetailSet}" >
						<c:if test="${detail.paramValue != null}">
							<li>${detail.qualityParam.qualityName }：${detail.paramValue} ${detail.qualityParam.unit }</li>
						</c:if>
					</c:forEach>
				</ul>
			</c:if>
			</div>
		</c:forEach>
	</div>
</div>

<div class="introduction">
<div class="titleright martop">
		<h2 class="marginright">法务信息</h2>
	</div>
	<div class="conrig">
			<c:set var="numL" value="0"></c:set>
			<c:forEach var="quality" items="${supplier.orgInfo.qualitys}">
				<div class="list">
				<c:if test="${quality.qualificationClass.qualityCode == 'C02'}">
				<c:set var="numL" value="${numL + 1}"></c:set>
						<h2><span>${quality.qualificationDefine.qualityName }</span></h2>
						<ul>
							<c:forEach var="detail" items="${quality.qualificationDetailSet}" >
								<c:if test="${detail.paramValue != null}">
									<li>${detail.qualityParam.qualityName}：${detail.paramValue} </li>
								</c:if>
							</c:forEach>
							<c:if test="${quality.qualificationFile != null}">
								<li><a href="javascript:void(0);" name="qualificationFile" id="${quality.qualificationFile }">文件下载</a></li>
							</c:if>
					    </ul>
				</c:if>
				</div>
		</c:forEach>
		<c:if test="${numL == 0}"><div style="padding-left: 8px;" class="dataTables_paginate paging_full_numbers" id="QuotaList_paginate">
		<span class="l">没有检索到数据！</span>
		</div></c:if>
	</div>
</div>

<div class="introduction">
<div class="titleright martop">
		<h2 class="marginright">其他资质</h2>
	</div>
	<div class="conrig">
			<c:set var="numL" value="0"></c:set>
			<c:forEach var="quality" items="${supplier.orgInfo.qualitys}">
				<div class="list">
				<c:if test="${quality.qualificationClass.qualityCode != 'C01' && quality.qualificationClass.qualityCode!='C02'  }">
				<c:set var="numL" value="${numL + 1}"></c:set>
						<h2><span>${quality.qualificationDefine.qualityName }</span></h2>
						<ul>
							<c:forEach var="detail" items="${quality.qualificationDetailSet}" >
								<c:if test="${detail.paramValue != null}">
									<li>${detail.qualityParam.qualityName}：${detail.paramValue} </li>
								</c:if>
							</c:forEach>
							<c:if test="${quality.qualificationFile != null}">
								<li><a href="javascript:void(0);" name="qualificationFile" id="${quality.qualificationFile }">文件下载</a></li>
							</c:if>
					    </ul>
				</c:if>
				</div>
		</c:forEach>
		<c:if test="${numL == 0}">
		<div style="padding-left: 8px;" class="dataTables_paginate paging_full_numbers" id="QuotaList_paginate">
		<span class="l">没有检索到数据！</span>
		</div></c:if>
	</div>
</div>

<script type="text/javascript">
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
</script>
