<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="dailogId" name="dailogId" value="${param.dailogId}">
<form:form id="GoodsDetailForm" method="post" modelAttribute="goods">
	<form:hidden path="objId"/>
	<input type="hidden" name="currentId" id="currentId" value="${goods.currentId}"/>
	<input type="hidden" name="useStatus" id="useStatus" value="${goods.useStatus}"/>
	<input type="hidden" name="additionPicture" value="${goods.additionPicture}" />
	<input type="hidden" name="role_type" id="role_type" value="${roleType}" />
		
	<div class="formLayout form2Pa">
		<h4 class="title"><span>商品信息</span></h4>
		<div class="k1">
			<img width="200px" height="175px" src="<c:url value="AttachmentController.do?method=showImg&objId=${goods.picture}" />">
		</div>
		<ul>
			<li class="fullLine"><label>商品名称：</label>${goods.productName}</li>
		 	<li><label>规格型号：</label>${goods.productCode}</li>
		 	<li><label>品牌名称：</label>${goods.goodsBrand.brandName}</li>
		 	<li><label>采购品目：</label>${goods.purCategory.categoryName}</li>
		 	<li><label>商品分类：</label>${goods.goodsClass.goodsClassName}</li>
		 	<li><label>计量单位：</label>${goods.measureUnit}</li>
		 	<c:if test="${goods.useStatus=='01'}">
			<li><label>发布日期：</label><fmt:formatDate value="${goods.productDateIssued}" pattern="yyyy-MM-dd"/></li>
		 	</c:if>
		 	<li><label>参考价(元)：</label><span>￥<fmt:formatNumber value="${goods.referPrice}" pattern="#,##0.00#" /></span></li>
		 	<li><label>制造商：</label>${goods.factory}</li>
		 	<li class="fullLine"><label>产地：</label>${goods.madeIn}</li>
		 	<li class="fullLine"><label>外部链接：</label><a><span id="externalInforLink">${goods.externalInforLink}</span></a></li>
		 	<li><label>是否特供商品：</label><c:choose><c:when test="${goods.special == true}">是</c:when><c:otherwise>否</c:otherwise></c:choose></li>
		 	<li><label>是否自定义商品：</label><c:choose><c:when test="${goods.isCustom == true}">是</c:when><c:otherwise>否</c:otherwise></c:choose></li>
		 	<li><label>是否零配件：</label><c:choose><c:when test="${goods.isAccessory == true}">是</c:when><c:otherwise>否</c:otherwise></c:choose></li>
		 	<li><label>是否可单独出售：</label><c:choose><c:when test="${goods.soleToSell == true}">是</c:when><c:otherwise>否</c:otherwise></c:choose></li>
		 	<li><label>自主创新认定编号：</label>${goods.creationCode}<c:if test="${goods.creationCode == null}">无</c:if></li>
			<li><label>环境标志产品编号：</label>${goods.environmentLabel}<c:if test="${goods.environmentLabel == null}">无</c:if></li>
			<li><label>节能产品编号编号：</label>${goods.energySavingProductNo}<c:if test="${goods.energySavingProductNo == null}">无</c:if></li>
			<li><label>密码技术产品编号：</label>${goods.cryptographyTechCode}<c:if test="${goods.cryptographyTechCode == null}">无</c:if></li>
		 	<c:if test="${goods.paramInputType==02}">
			<li id="spec_1" class="fullLine"><label>详细配置：</label>${goods.spec}</li>
		 	</c:if>
		</ul>
	</div>
    		
	<c:if test="${goods.functionIntro!=null && goods.functionIntro != ''}">
	<div class="formLayout form2Pa">
		<h4 class="title" style="clear:both;"><span>商品描述</span></h4>
		<div>${goods.functionIntro}</div>
	</div>
	</c:if>
	
	<c:if test="${goods.goodsParamSet !=null && fn:length(goods.goodsParamSet) > 0}">
		<div class="formLayout form2Pa">
		<h4><span>商品参数</span></h4>
		<table class="tableList">
			<c:forEach var="goodsParam" items="${goods.goodsParamSet}">
				<c:if test="${goodsParam.goodsClassParam.isLeaf == false}">
				<tr>
					<td colspan="2"><em>${goodsParam.goodsClassParam.paramName}</em></td>
					<c:forEach var="paramLeaf" items="${goods.goodsParamSet}">
						<c:if test="${paramLeaf.goodsClassParam.isLeaf == true && paramLeaf.goodsClassParam.parent.objId==goodsParam.goodsClassParam.objId}">
							<tr>
								<td width="30%"><label>${paramLeaf.paramName}<c:if test="${!empty paramLeaf.goodsClassParam.paramUnit}">(${paramLeaf.goodsClassParam.paramUnit})</c:if></label></td>
								<td>
									<c:choose>
										<c:when test="${!empty paramLeaf.goodsOptionalFittingSet}">
											${paramLeaf.paramValue}
											[选配:
											<c:forEach var="optinalFitting" items="${paramLeaf.goodsOptionalFittingSet}" varStatus="status2">
												<c:if test="${optinalFitting.isUse != '02'}">
													${optinalFitting.optionContent}
												</c:if>
											</c:forEach>
											]
										</c:when>
										<c:otherwise>
											${paramLeaf.paramValue}
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
	</c:if>
	
	<div id="additionPicture"></div>
	
	<div class="conOperation ">
		<c:choose>
		<c:when test="${!empty viewFrom && viewFrom == 'bargain'}">
			<button type="button" class="largeBtn" id="close_gview"><span>关闭</span></button>
		</c:when>
		<c:otherwise>
	    	<button class="largeBtn" id="returnBtn" type="button" ><span><spring:message code="globe.return"/></span></button>
	    	<button type="button" class="largeBtn" id="viewHistory"><span>查看历史</span></button>
	    </c:otherwise>
	    </c:choose>
	</div> 
</form:form>

<script>
var GoodsDetailForm = {};
$(document).ready(function(){
	$('#externalInforLink').click(function(){
		var htm = $('#externalInforLink').text();
		if(htm.indexOf('http')==-1) {
			htm = 'http://' + htm;
		}
		window.open(htm);
	})
	
	//更多图片
	$('#additionPicture').loadPage($('#initPath').val()+'/AttachmentController.do?method=toUploadAjaxImg',
			{
			defineSelf:'additionPicture',//存放关联id的属性名
			maxSize:'1024',
			fileType: 'gif|jpeg|jpg|bmp|png',
			quantity:'4',//附件最大数
			attachRelaId:$("input[name=additionPicture]").val(),
			isView:'1'//是否只是显示图片不删除和上传
			,maxWidth:80,//建议长
			maxHeight:80,//建议宽
			nopicPath:'AttachmentController.do?method=showImg'//提示图片  可以是AttachmentController.do?method=showImg
			}
		);
	//返回
	$("#returnBtn").click(function(){
		if($('#role_type').val()=="4") {
			$('#conBody').loadPage($('#initPath').val()+'/GoodsController.do?method=getGoodsClassByOrg');
		}else if($('#role_type').val()=="3") {
			$('#conBody').loadPage($("#initPath").val()+"/view/goods/goods/goodsmng/goods_manage_list.jsp");
		}
	})
	//关闭
	$("#close_gview").click(function(){
		$('#close_gview').closest('.epsDialog').find('.epsDialogClose').trigger('click');
	})
	//查看历史
	$("#viewHistory").click(function(){
		var url = $('#initPath').val()+"/GoodsController.do?method=getGoodsHistory";
		if($("#currentId").val()!="" && $("#useStatus").val()=="00"){//变更
			url += "&id="+$("#currentId").val();
		}else{
			url += "&id="+$("#objId").val();
		}
		$.epsDialog({
			id:'goodsChangeHistoryDiv',
	        title:'商品变更历史',
	        url:url,
	        width:600,
	        height:300
	    }); 
	})
})
</script>
