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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/goods/css/publish_detail_ke.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/pubservice/css/goods_list.css"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>

</head>
<body>

<!--页面容器 开始-->
<div id="container"> 
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
			
			<div class="grid-c" id="Content">
				<div class="publish-title type-b" id="J_publishTitle">
					<h2><span>发布供应信息</span></h2>
					<div class="formTips smallLight hint"><strong>提醒：</strong>商品发布虚假信息，将不被审核通过，多次发布虚假商品，机构信息将进入黑名单。</div>
				</div>
				
				<div class="goods-box" id="item-publish">
					<span class="rc-tp"><span></span></span>
					<div class="hd">
						<h3>填写商品基本信息</h3>
					</div>
					<div class="bd">
						<div class="bd-sub">
							<div id="product-info">
								<h5>类目信息</h5>
								<div>
									<ul>
										<li id="changePurClassLi">
											${goods.purCategory.categoryName} &gt;&gt; ${goods.goodsClass.goodsClassName} &gt;&gt; ${goods.goodsBrand.brandName}
											<input type="submit" id="rebackBtn" data-detect="reEditCat" class="J_DetectTrigger" value="变更商品类目" />
										</li>
									</ul>
								</div>
							</div>
						</div>
						<!-- end bd-sub -->
						<div class="bd-main">														
							<div>
							<form id="GoodsBaseInfoForm" name="GoodsAddForm2" method="post">
								<input type="hidden" name="objId" id="objId" value="${goods.objId}" /> 
								<input type="hidden" name="purCategory.objId" id="purCategoryId" value="${goods.purCategory.objId}" />
								<input type="hidden" name="goodsClass.objId" id="goodsClassId" value="${goods.goodsClass.objId}" />
								<input type="hidden" name="goodsBrand.objId" id="goodsBrandId" value="${goods.goodsBrand.objId}" />
								<input type="hidden" name="goodsString" id="goodsString" value="" />
								
								<input type="hidden" id="auditStatus" value="${goods.auditStatus}" />
								<input type="hidden" id="picture" name="picture" value="${goods.picture}"/>
								<input type="hidden" id="submitRelaId" name="additionPicture" value="${goods.additionPicture}"/>
								
								
								<c:if test="${goods.opinion!=null && goods.auditStatus=='03' }">
									<div class="formTips attention">
									抱歉！您的商品不符合规范没有审核通过，管理员的意见是：【${goods.opinion}】，请修改后重新提交或者删除该记录！如有异议，请联系管理员。
									</div>
		   						</c:if>
								
								<span><h5>1. 商品基本信息</h5></span>
								<div class="form">
									<ul id="J_form">
										<li>
											<label>商品名称：</label>
											<span>
												<em>*</em>
												<input class="text text-long required" id="productName" value="${goods.productName}" maxlength="50" name="productName" type="text" size="40"/> 
												<span class="prop-tips">限定在30个汉字内（60个字符）</span>
											</span>
										</li>
										<li>
											<label>商品型号：</label>
											<span>
												<em>*</em>
												<input class="text text-short required" id="productCode" value="${goods.productCode}" maxlength="50" name="productCode" type="text" size="40"/> 
											</span>
										</li>
										<li id="fixpriceOption2">
											<label>参考价(元)：</label>
											<span>
												<em>*</em>
												<input class="text text-short money required" id="referPrice" value="${goods.referPrice}" name="referPrice" type="text" size="40"/>
											</span>
										</li>
										<li>
											<label>计量单位：</label>
											<span>
												<em>*</em>
												<input class="text text-short required" id="measureUnit" value="${goods.measureUnit}" name="measureUnit" maxlength="50" type="text" size="40"/> 
											</span>
										</li>																			
										<li>
											<label>制造商：</label>
											<span>
												<em>*</em>
												<input class="text text-short required" id="factory" value="${goods.factory}" name="factory" style="width:180px" maxlength="50" type="text" size="40"/>
											</span>
										</li>																			
										<li>
											<label>产地：</label>
											<span>
												<input class="text text-short" id="madeIn" value="${goods.madeIn}" name="madeIn" maxlength="50" style="width:180px" type="text" size="40"/>
											</span>
										</li>																			
										<li>
											<label>外部链接：</label>
											<span>
												<input class="text text-short" id="externalInforLink" value="${goods.externalInforLink}" name="externalInforLink" style="width:180px" maxlength="50" type="text" size="40"/>
												<span class="prop-tips">例如http://www...</span>
											</span>
										</li>			
										<li>
											<label>商品图片：</label>
											<span>
												<div class="upload-manager upload-pic">
													<ul class="tabs">
														<li id="oneLi" class="selected"><span><a id="uploadOne" href="javascript:void(0);">上传商品主图</a></span></li>
														<li id="moreLi"><span><a id="uploadMore" href="javascript:void(0);">上传更多图片</a></span></li>
													</ul>
													<div id="mainPic" class="panel panel-local" style="display: block;">
														<div class="side"></div>
														<div id="newPreview">
															<c:choose>
																<c:when test="${goods.picture==null}"><img src="<%=request.getContextPath()%>/view/resource/skin/goods/img/goods_add.gif" width="120px" height="120px" style="border:1px solid #D5D5D5;" /></c:when>
																<c:otherwise><img src="<c:url value="AttachmentController.do?method=showImg&objId=${goods.picture}" />" width="120px" height="120px" style="border:1px solid #D5D5D5;" /></c:otherwise>
															</c:choose>
														</div>
														<input type="button" id="toCropZoomImgBut" value="选择图片" />
													</div>
													<div id="morePic" class="panel panel-local" style="display: none;"></div>
												</div>
											</span>
										</li>								
										<li>
											<label>详细描述：</label><span><em>*</em></span>
											<div style="margin-left: 95px; width: 638px;">
												<div id="htmlEditor"></div>
												<textarea name="functionIntro" id="functionIntro" class="hidden required">${goods.functionIntro}</textarea>
												<span style="color: #808080; display: inline;">最大2000字符，包含页面标签。建议不要从word里面copy文字到编辑器里，容易出现丢失的现象。</span>
											</div>
										</li>
									</ul>
								</div>
								<h5>2. 商品属性</h5>
								<div class="form">
									<ul>
										<li>
											<label class="form_attribute">基本属性：</label>
											<span class="form_content">
												<input name="isCustom" <c:if test="${goods.isCustom == true}">checked="checked"</c:if> id="isCustom" type="checkbox" class="checkbox"/>
											  	自定义商品
											  
			                  					<input name="isAccessory" <c:if test="${goods.isAccessory == true}">checked="checked"</c:if> id="isAccessory" type="checkbox" class="checkbox"/>
			                  					零配件
			                  					  
			                  					<input name="soleToSell" <c:if test="${goods.soleToSell == true}">checked="checked"</c:if> id="soleToSell" type="checkbox" class="checkbox"/>
			                  					可单独出售
			                  					  
			                  					<input name="special" <c:if test="${goods.special == true}">checked="checked"</c:if> id="special" type="checkbox" class="checkbox"/>
			                  					特供商品
											</span>
										</li>
										<li>
											<label class="form_attribute">其他属性：</label>
											<span class="form_content">
												<div>
		   											<ul class="ul-select">
		                            				  	<li  style="height:30px;">
		                            					  <input name="isEnergy" <c:if test="${!empty goods.energySavingProductNo}">checked="checked"</c:if> id="isEnergy" type="checkbox" class="checkbox"/>
		                            					     节 能 产 品 编号 ：
														  <input type="text" value="${goods.energySavingProductNo}" size="30" name="energySavingProductNo" id="energySavingProductNo" maxlength="50"/>
		                            					  <span class="prop-tips">节能产品</span>
		                            					</li>
		                            				  	<li  style="height:30px;">
		                            					  <input name="isSelf" <c:if test="${!empty goods.creationCode}">checked="checked"</c:if> id="isSelf" type="checkbox"  class="checkbox"/>
		                            					     自主创新产品编号：
														  <input id="creationCode" value="${goods.creationCode}" name="creationCode" type="text" size="30" maxlength="50"/>
														  <span class="prop-tips">自主创新产品</span>
		                            					</li>
		                            				  	<li  style="height:30px;">
		                            					  <input name="isEnvirement" <c:if test="${!empty goods.environmentLabel}">checked="checked"</c:if> id="isEnvirement" type="checkbox"  class="checkbox"/>
		                            					     环境标志产品编号：
														  <input id="environmentLabel" value="${goods.environmentLabel}" name="environmentLabel" type="text" size="30" maxlength="50"/>
														  <span class="prop-tips">进入《环境标志产品政府采购清单》</span>
		                            					</li>
		                            				  	<li  style="height:30px;">
		                            					  <input name="isTechCode" <c:if test="${!empty goods.cryptographyTechCode}">checked="checked"</c:if> id="isTechCode" type="checkbox"  class="checkbox"/>
		                            					     密码技术产品编号：
														  <input id="cryptographyTechCode" value="${goods.cryptographyTechCode}" name="cryptographyTechCode" type="text" size="30" maxlength="50"/>
														  <span class="prop-tips">进入《含有密码技术的信息产品政府采购清单》</span>
		                            					</li>
		                            				</ul>
	                            				</div>
											</span>
										</li>
									</ul>
	    						</div>
							</form>
							
							<form id="GoodsParamForm" name="GoodsParamForm" method="post"> 	
								<h5>3. 商品参数</h5>
								<div class="form">
									<c:choose>
									<c:when test="${!empty goodsClassParamList && fn:length(goodsClassParamList) > 0}">
									<ul>
						   			<c:forEach var="gpObj" items="${goodsClassParamList}" varStatus="status1">
										<c:if test="${fn:contains(gpObj[4], '0')}">
										<li><label>${gpObj[2]}：</label>
												<input type="hidden" name="goodsParamSet[${status1.index}].objId" value="${gpObj[6]}" />
												<input type="hidden" name="goodsParamSet[${status1.index}].typeName" value="${gpObj[2]}" />
												<input type="hidden" name="goodsParamSet[${status1.index}].goodsClassParam.objId" value="${gpObj[0]}" />
												<input type="hidden" name="goodsParamSet[${status1.index}].sortNo" value="${gpObj[5]}" />
											<span>
			   									<div class="module-form">
			   										<div class="skin">
			   											<ul class="ul-radio ul-radio-vertical">
			   												<c:forEach var="paramLeaf" items="${goodsClassParamList}" varStatus="status2">
							   								<c:if test="${fn:contains(paramLeaf[4], '1') && paramLeaf[1]==gpObj[0]}">
				                            				  	<li style="width:48%">
				                            					  <label style="width:100px;float:left;" title="${paramLeaf[2]}">${paramLeaf[2]}<c:if test="${!empty paramLeaf[9]}">(${paramLeaf[9]})</c:if>：</label>
																  <input type="hidden" name="goodsParamSet[${status2.index}].objId" value="${paramLeaf[6]}" />
									   							  <input type="hidden" name="goodsParamSet[${status2.index}].paramName" value="${paramLeaf[2]}" />
									   							  <input type="hidden" name="goodsParamSet[${status2.index}].goodsClassParam.objId" value="${paramLeaf[0]}" />
									   							  <input type="hidden" name="goodsParamSet[${status2.index}].sortNo" value="${paramLeaf[5]}" />
								   								  <c:choose>
																	<c:when test="${fn:contains(paramLeaf[3], '1')}">
																		<input type="text" name="goodsParamSet[${status2.index}].paramValue" class="required" value="${paramLeaf[8]}" maxlength="200"/>
							   											<span class="eleRequired">*</span>
																	</c:when>		
																	<c:otherwise>
																		<input type="text" name="goodsParamSet[${status2.index}].paramValue" value="${paramLeaf[8]}" maxlength="200"/>
																	</c:otherwise>	
																  </c:choose>
				                            					</li>
			                            					</c:if>
						   									</c:forEach>
			                            				</ul>
			    									</div>
			   									</div>
											</span>
										</li>
										</c:if>
									</c:forEach>
									</ul>
						   				<input name="paramInputType" type="hidden" value="01" />
						   			</c:when>
						   			<c:otherwise>
							   			<input name="paramInputType" type="hidden" value="02" />
						   				<ul id="J_form">
							   				<li><label>规格说明：</label><span><em>*</em></span>
							   					<textarea class="required" name="spec" id="spec" maxLength="1000" style="height:100px;width:638px;">${goods.spec}</textarea>
							   				</li>
							   			</ul>
						   			</c:otherwise>
						   			</c:choose>
								</div>
								<div class="submit">
									<c:if test="${currRoleType == '4'}">
										<c:choose><c:when test="${goods.useStatus=='01'}">
											<input type="button" name="saveButton" value="保存" id="saveGoodsBtn_save" class="ic-btn ic-btn-blue"/>
										</c:when><c:otherwise>
											<input type="button" name="saveButton" value="提交" id="saveGoodsBtn_submit" class="ic-btn ic-btn-blue"/>
										</c:otherwise></c:choose>
									</c:if>
									<!-- manager -->
									<c:if test="${currRoleType == '3'}">
										<c:choose><c:when test="${goods.useStatus=='01'}">
											<input type="button" name="saveButton" value="保存" id="saveGoodsBtn_save" class="ic-btn ic-btn-blue"/>
										</c:when><c:otherwise>
											<input type="button" name="saveButton" value="保存为临时" id="saveGoodsBtn_save" class="ic-btn ic-btn-blue"/>
											<input type="button" name="saveButton" value="保存为正式" id="saveGoodsBtn_valid" class="ic-btn ic-btn-blue"/>
										</c:otherwise></c:choose>
									</c:if>
								</div>
							</form>
							</div>
						</div>
						<div class="ft">
							<p class="form-tip"><em>*</em> 表示该项必填</p>
						</div>
						<span class="rc-bt"><span></span></span>
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
var GoodsBaseInfoForm={};
var htmlEditor;

//保存商品
GoodsBaseInfoForm.submit = function(saveType){
	//清空异步上传图片页内容
	$('#morePic').html('');
	
	var url = $('#initPath').val()+"/GoodsController.do?method=updateGoods";

	//每次先将goodsStr值置为空
	$("#goodsString").val("");
	var baseInfoForm = formToJsonObject('GoodsBaseInfoForm','json');

	//设定状态值
	if(saveType=="save"){
		//如果已经审核通过,则原状态值不变
		if($('#auditStatus').val() == '02') {
			baseInfoForm.auditStatus="02";//保存
		}
	}else if(saveType=="submit"){
		baseInfoForm.auditStatus="01";//提交
	}else if(saveType=="valid"){
		baseInfoForm.auditStatus="02";//保存为正式
	}

	//处理商品属性
	if(!$("[name=isCustom]").attr("checked")) {
		baseInfoForm.isCustom='1';
	}
	if(!$("[name=isAccessory]").attr("checked")) {
		baseInfoForm.isAccessory='1';
	}
	if(!$("[name=soleToSell]").attr("checked")) {
		baseInfoForm.soleToSell='1';
	}
	if(!$("[name=special]").attr("checked")) {
		baseInfoForm.special='1';
	}
	
	if($("[name=isEnergy]").attr("checked") == false){
		baseInfoForm.energySavingProductNo = "";
	}
	if($("[name=isSelf]").attr("checked") == false){
		baseInfoForm.creationCode = "";
	}
	if($("[name=isEnvirement]").attr("checked") == false){
		baseInfoForm.environmentLabel = "";
	}
	if($("[name=isTechCode]").attr("checked") == false){
		baseInfoForm.cryptographyTechCode = "";
	}
	var paramInfoForm = formToJsonObject('GoodsParamForm','jsonUtils');;
	
	$('#goodsString').val(JSON.stringify($.extend(baseInfoForm,paramInfoForm)));

	//屏蔽保存和提交按钮
	$('input[name=saveButton]').attr('disabled','disabled');
	
	$('#GoodsBaseInfoForm').ajaxSubmit({
		url:url,
		dataType:'json',
		success:function(json){
			window.location.href=$('#initPath').val()+'/GoodsController.do?method=toGoodsSubmitResultView&objId='+$('#objId').val()+'&goodsStatus='+baseInfoForm.auditStatus;
			$('input[name=saveButton]').removeAttr('disabled','disabled');
		},
		error:function(msg){
			$('input[name=saveButton]').removeAttr('disabled','disabled');
			alert(JSON.stringify(msg));
		}
	});
}

$(document).ready(function(){	
	$("#GoodsBaseInfoForm").validate({
		rules: {
			energySavingProductNo: {
				required: "#isEnergy:checked"
			},
			creationCode: {
				required: "#isSelf:checked"
			},
			environmentLabel: {
				required: "#isEnvirement:checked"
			},
			cryptographyTechCode: {
				required: "#isTechCode:checked"
			}
		},
		messages: {
			energySavingProductNo: {required: "请填写节能产品编号"},
			creationCode: {required: "请填写自主创新产品编号"},
			environmentLabel: {required: "请填写环境标志产品编号"},
			cryptographyTechCode: {required: "请填写密码技术产品编号"}
		}
	});

	
	//商品名称唯一性验证
	$('#productName').blur(function(){
		if(""==$(this).val()) return;//为空不检测
		$.getJSON($("#initPath").val()+"/GoodsController.do?method=checkGoodsUnique",{
			"objId":$('#objId').val(),
			"productName":native2ascii($(this).val()),
			"classId":$('#classId').val(),
			"brandId":$('#brandId').val()
			},function(json){
			if(json.success){
				$("#tips").empty().append(json.result);
				$("#tips").show();
			} else {
				$("#tips").hide();
			}
		});
	});
	//商品型号唯一性验证
	$('#productCode').blur(function(){
		if(""==$(this).val()) return;//为空不检测
		$.getJSON($("#initPath").val()+"/GoodsController.do?method=checkGoodsUnique",{
			"objId":$('#objId').val(),
			"productCode":native2ascii($(this).val()),
			"classId":$('#classId').val(),
			"brandId":$('#brandId').val()
			},function(json){
			if(json.success){
				$("#tips").empty().append(json.result);
				$("#tips").show();
			} else {
				$("#tips").hide();
			}
		});
	});
	
	//保存或提交
	$('input[id^=saveGoodsBtn_]').click(function(){     
		//编辑器填值
		$('#functionIntro').val(htmlEditor.getValue());
		
		if(!$('#GoodsBaseInfoForm').valid()){alert('请正确填写表单!');return;}   
		if(!$('#GoodsParamForm').valid()) {alert('请正确填写表单!');return;}
		var disP = $(this).attr("id").replace("saveGoodsBtn_","")=="save"?"保存":"提交";
		if(window.confirm("确认"+disP+"商品吗?")){
			GoodsBaseInfoForm.submit($(this).attr("id").replace("saveGoodsBtn_",""));
		}
	})
	
	//变更商品类目
	$("#rebackBtn").click(function(){         
		var url = $('#initPath').val()+"/GoodsChangeController.do?method=toGoodsChange&objId="+$('#objId').val();
		$.epsDialog({
			id:'goodsChangeDiv',
	        title:'商品类目变更',
	        url:url
	    })
	})
	
	//上传主图
	$('#uploadOne').click(function(){
		$('#oneLi').addClass('selected');
		$('#moreLi').removeClass('selected');
		
		$('#mainPic').show();
		$('#morePic').hide();
	})
	
	//上传多图
	$('#uploadMore').click(function(){
		$('#moreLi').addClass('selected');
		$('#oneLi').removeClass('selected');
		
		$('#morePic').show();
		$('#mainPic').hide();

		if(!$('#morePic').html()) {
			//更多图片
			$('#morePic').loadPage($('#initPath').val()+'/AttachmentController.do?method=toUploadAjaxImg',{
					defineSelf:'additionPicture',//存放关联id的属性名
					maxSize:'1024',
					fileType: 'gif|jpeg|jpg|bmp|png',
					quantity:'4',//附件最大数
					attachRelaId:$("input[name=additionPicture]").val(),
					isView:'0'//是否只是显示图片不删除和上传

					,maxWidth:80,//建议长
					maxHeight:80,//建议宽
					pic_WH_rule_str:'goods_pic_width_height_rule',//上传商品图片宽高规则字符参数
					nopicPath:'AttachmentController.do?method=showImg'//提示图片  可以是AttachmentController.do?method=showImg
			});
		}
	})

	//加载ExtJs的HTML编辑器
	htmlEditor = new Ext.form.HtmlEditor({
		height: 240,
		width: 635,
	    renderTo: 'htmlEditor'
	});
	htmlEditor.setValue($('#functionIntro').val()+" ");

	//选择商品主图片
	$("#toCropZoomImgBut").click(function(){
		var url = $('#initPath').val()+"/view/pubservice/application/cropzoomimg/crop_zoom_img.jsp?picWidth=200&picHeight=200&pic_WH_rule_str=goods_pic_width_height_rule&propertyName=picture&oldAttachmentId="+$("#picture").val();
		$.epsDialog({
			title: '选择商品图片',
			url: url
		});
	});
})
</script>

</body>
</html>
