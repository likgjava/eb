<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var SupplierDetailForm={};
var show_list={};
SupplierDetailForm.oTable;
//加载评价列表
SupplierDetailForm.oTable = $('#evaluateList').dataTable({   
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
	"sAjaxSource": $('#initPath').val()+"/ShowEvaluateController.do?method=toOrgEvaluateList&org.objId="+$("#supplierOrgInfoId").val()
});

//加入收藏
SupplierDetailForm.addFavorites = function(favoriteId,favoriteName,favoriteType){
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
		title:"关注供应商",
		width:500,
		height:300,
		url:$('#initPath').val()+"/ConcernController.do?method=toAddConcernForm&groupType="+groupType+"&orgInfoId="+orgInfoId,
		afterLoad: function(){$('#orgInfoName').html(orgInfoName) }
	});
}

//点击导航
SupplierDetailForm.searchByTitle = function(categoryCode,districtId,districtLevel) {
	var param = "";
	if(categoryCode) {  //代理机构类型
		param += "&categoryCode=" + categoryCode;
	}
	if(districtId) {  //区域id
		param += "&districtId=" + districtId;
	}
	if(districtLevel){
		param += "&districtLevel=" + districtLevel;
	}
	$('#sysContent').loadPage($('#initPath').val()+'/SupplierShowController.do?method=toSupplierList&rp=21&page=1'+ param);
}

//添加附件事件
SupplierDetailForm.downLoadFile = function(id){
	$.epsDialog({
		title:"附件下载",
		url:$("#initPath").val()+"/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId="+id,
		width: 600,
		height: 300
	});
}

$(document).ready(function(){
	//布局页面
	fnRemoveOtherMain(); 
	changeTabsCss("goToSupplier");//选中顶部菜单
	
	//选中查询下拉框
	keyWordTypeChange('4');
	
	//屏掉供应商关注
	if(common.isHasRole('b')){$("#addClient").show()};
	
	$tabs=$('#epsTabs').tabs();//加载tabs
	
	//选中指定的tab页
	if($("#tabId").val() != "") {
		$("#"+$("#tabId").val()).click();
	}
});
</script>

<input type="hidden" id="supplierId" value="${supplier.objId}" />
<input type="hidden" id="supplierOrgInfoId" value="${supplier.orgInfo.objId}" />
<input type="hidden" id="tabId" value="${tabId}">

<div id="conTitle">
	<div class="navCurrent">
	<a id="a" href="javascript:void(0);" onclick="SupplierDetailForm.searchByTitle(null,null,'1');return false;" >供应商展示</a>
	<c:if test="${categoryCode!=null}">
		<a id="a" href="javascript:void(0);" onclick="SupplierDetailForm.searchByTitle('${categoryCode}',null,'1');return false;" >${categoryName }</a>
	</c:if>
	<c:if test="${districtId!=null}">
		<a id="b" href="javascript:void(0);" onclick="SupplierDetailForm.searchByTitle('${categoryCode}','${districtId }','1');return false;" >${districtName }</a>
	</c:if>
	供应商详情</div>
</div>
<div id="conBody"><!--功能页内容-->
	<div class="imgAndInfo smallImg">
		<div id="showImg" >
			<div style="height:120px;" class="short">
				<img style="width:160px;height:120px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${supplier.orgInfo.logo}" />">
			</div>
		</div>
		<div id="showInfo">
			<ul class="meta">	
				<li class="detail-title"><span>组织机构代码：</span>${supplier.orgInfo.orgCode }</li>
				<li class="detail-title"><span>供应商名称：</span><strong>${supplier.orgInfo.orgName }</strong>
					<button type="button" onclick="common.addFavorites('${supplier.orgInfo.objId }','${supplier.orgInfo.orgName}','02')" class="favBtn" height="25px">加入收藏</button>
					<button type="button" onclick="show_list.addClient('01','${supplier.orgInfo.objId}','${supplier.orgInfo.orgName}')" class="favBtn hidden" height="25px" id="addClient">关注客户</button>
				</li>
			</ul>
			<div class="key">
			    <dl>
		   			<dt><em>评价总分：</em></dt>
		   			<dd class="totalScore">
		   						<ul class="rating-level">
		   								<li><a class="aa<fmt:formatNumber type="number" value="${supplier.evalSum }" maxFractionDigits="0"/>-stars current-rating"  href="#"></a></li>
		   						</ul>
		   						<span id="stars2-tips" class="result"><fmt:formatNumber type="number" value="${supplier.evalSum }" pattern="#0.0"/>分</span>
		   			</dd>
		   		</dl>
		    </div>
			<ul class="other">
				<li><span>入库时间：</span><fmt:formatDate value="${supplier.orgInfo.validTime }" pattern="yyyy年MM月dd日"/></li>										
				<li><span>所属区域：</span>${supplier.orgInfo.distinctName }</li>
			</ul>
		</div>
		</div>
		<!---商品图 结束--> <!-- Tab页 -->
		<div id="epsTabs" class="epsTabs">
		<ul>
			<li><a href="#baseParam" id="baseParamTab"><span>基本信息</span></a></li>
			<li><a href="#finance" id="financeTab"><span>财务信息</span></a></li>
			<li><a href="#legal" id="legalTab"><span>法务信息</span></a></li>
			<li><a href="#quality" id="qualityTab"><span>企业资质</span></a></li>
			<li><a href="#successCase" id="successCaseTab"><span>成功业绩</span></a></li>
			<li><a href="#evaluate" id="evaluateTab"><span>信用评价</span></a></li>
		</ul>
		<div id="baseParam" class="formLayout form2Pa">
    		<ul>
				<li><label>所属行业：</label> <span>${supplier.orgInfo.belongIndustry.name}</span></li>
				<li><label>企业类型：</label><span>${supplier.orgInfo.entPrptCN}</span></li>
				<li><label>人员规模：</label><span>${supplier.orgInfo.unitScapeCN}</span></li>
				<li><label>开业日期：</label><span>${supplier.orgInfo.begainDate}</span></li>
    			<li><label>法定代表人：</label> <span>${supplier.orgInfo.company.croporate}</span></li>
    			<li><label>公司网址：</label> <span><a href="${supplier.orgInfo.webUrl}" target="_blank">${supplier.orgInfo.webUrl}</a></span></li>
    			<li class="fullLine"><label>公司地址：</label> <span>${supplier.orgInfo.company.address}</span></li>
    			
    			<!-- 商圈会员可以查看联系方式 -->
    			<c:if test="${isShowContact==true}">
	    			<li><label>传真：</label><span>${supplier.orgInfo.company.fax}</span></li>
	    			<li><label>联系电话：</label><span>${supplier.orgInfo.company.tel}</span></li>
	    			<li class="fullLine"><label>邮政编码：</label><span>${supplier.orgInfo.company.postCode}</span></li>
    			</c:if>
    			
				<li class="fullLine"><label>企业产能：</label> <span>${supplier.orgInfo.entCapacity}</span></li>
    			<li class="fullLine"><label>经营范围：</label> <span>${supplier.orgInfo.bidForRangeName}</span></li>
    			<li class="fullLine"><label>主营产品：</label> <span>${supplier.orgInfo.mainProducts}</span></li>
    			<li class="fullLine"><label>企业简介：</label> <span>${supplier.orgInfo.descCn}</span></li>
    		</ul>
		</div>
		<div id="finance" class="formLayout form2Pa">
			<c:set var="numJ" value="0"></c:set>
	    	<c:forEach var="quality" items="${supplier.orgInfo.qualitys}">
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
			<c:forEach var="quality" items="${supplier.orgInfo.qualitys}">
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
								<li><a href="javascript:SupplierDetailForm.downLoadFile('${quality.qualificationFile}');">文件下载</a></li>
							</c:if>
					    </ul>
				</c:if>
			</c:forEach>
			<c:if test="${numF == 0}"><div class="sorry">暂无法务信息！</div></c:if>
		</div>
		<div id="quality" class="formLayout form2Pa">
			<c:set var="num" value="0"></c:set>
			<c:forEach var="quality" items="${supplier.orgInfo.qualitys}">
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
								<li><a href="javascript:SupplierDetailForm.downLoadFile('${quality.qualificationFile}');">文件下载</a></li>
							</c:if>
					    </ul>
					</div>
				</c:if>
			</c:forEach>
			<c:if test="${num == 0}"><div class="sorry">暂无企业资质！</div></c:if>
		</div>
		<div id="successCase" class="formLayout form2Pa">
			<c:if test="${fn:length(supplier.orgInfo.successCases) == 0}"><div class="sorry">暂无成功业绩！</div></c:if>
			<c:forEach var="scase" items="${supplier.orgInfo.successCases}">
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
	</div>
</div>