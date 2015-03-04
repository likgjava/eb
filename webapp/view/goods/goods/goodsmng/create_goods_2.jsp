<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>创建商品-阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/goods/css/publish_detail_ke.css"/>

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
										<li>
											${category.categoryName} &gt;&gt; ${class.goodsClassName} &gt;&gt; ${brand.brandName}
											<input type="submit" id="rebackBtn" data-detect="reEditCat" class="J_DetectTrigger" value="编辑类目" />
										</li>
									</ul>
								</div>
							</div>
						</div>
						<!-- end bd-sub -->
						<div class="bd-main">														
							<div>
							<form id="GoodsBaseInfoForm" name="GoodsAddForm2" method="post"> 
								<input type="hidden" name="purCategory.objId" id="categoryId" value="${category.objId}" />
								<input type="hidden" name="goodsClass.objId" id="classId" value="${class.objId}" />
								<input type="hidden" name="goodsBrand.objId" id="brandId" value="${brand.objId}" />
								<input type="hidden" name="goodsString" id="goodsString" value="" />
								<input type="hidden" id="submitRelaId" name="additionPicture" />
								<input type="hidden" id="picture" name="picture" />
								
								<span><h5>1. 商品基本信息</h5></span>
								<div class="form">
									<ul id="J_form">
										<li>
											<div class="formTips warm hidden" id="tips"></div>
											<label>商品名称：</label>
											<span class="form_span">
												<em>*</em>
												<input class="text text-long required" id="productName" maxlength="50" name="productName" type="text" size="40"/> 
												<div><span class="prop-tips">注意：商品名称请尽量使用名称+型号命名</span></div>
											</span>
										</li>
										<li>
											<label>商品型号：</label>
											<span>
												<em>*</em>
												<input class="text text-short required" id="productCode" maxlength="50" name="productCode" type="text" size="40"/> 
											</span>
										</li>
										<li id="fixpriceOption2">
											<label>参考价(元)：</label>
											<span>
												<em>*</em>
												<input class="text text-short money required" id="referPrice" name="referPrice" type="text" size="40"/>
											</span>
										</li>
										<li>
											<label>计量单位：</label>
											<span>
												<em>*</em>
												<input class="text text-short required" id="measureUnit" name="measureUnit" maxlength="50" type="text" size="40"/> 
											</span>
										</li>																			
										<li>
											<label>制造商：</label>
											<span>
												<em>*</em>
												<input class="text text-short required" id="factory" name="factory" style="width:180px" maxlength="50" type="text" size="40"/>
											</span>
										</li>																			
										<li>
											<label>产地：</label>
											<span>
												<input class="text text-short" id="madeIn" name="madeIn" maxlength="50" style="width:180px" type="text" size="40"/>
											</span>
										</li>																			
										<li>
											<label>外部链接：</label>
											<span>
												<input class="text text-short" id="externalInforLink" name="externalInforLink" style="width:180px" maxlength="50" type="text" size="40"/>
											</span>
											
											<span class="prop-tips">例如http://www...</span>
										</li>			
																
										<li>
											<label>详细描述：</label><span><em>*</em></span>
											<div style="margin-left: 95px; width: 638px;">
												<div id="htmlEditor"></div>
												<textarea name="functionIntro" id="functionIntro" class="hidden required"></textarea>
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
												<input name="isCustom" id="isCustom" type="checkbox" class="checkbox"/>
											  	自定义商品
											  
			                  					<input name="isAccessory" id="isAccessory" type="checkbox" class="checkbox"/>
			                  					零配件
			                  					  
			                  					<input name="soleToSell" id="soleToSell" type="checkbox" class="checkbox"/>
			                  					可单独出售
			                  					  
			                  					<input name="special" id="special" type="checkbox" class="checkbox"/>
			                  					特供商品
											</span>
										</li>
										<li>
											<label class="form_attribute">其他属性：</label>
											<span class="form_content">
												<div>
		   											<ul class="ul-select">
		                            				  	<li  style="height:30px;">
		                            					  <input name="isEnergy" id="isEnergy" type="checkbox" class="checkbox"/>
		                            					  <label for="isApplyPostage_false">节 能 产 品 编号 ：</label>
														  <input type="text" size="30" name="energySavingProductNo" id="energySavingProductNo" maxlength="50"/>
		                            					  <span class="prop-tips">节能产品</span>
		                            					</li>
		                            				  	<li  style="height:30px;">
		                            					  <input name="isSelf" id="isSelf" type="checkbox"  class="checkbox"/>
		                            					  <label for="isApplyPostage_false">自主创新产品编号：</label>
														  <input id="creationCode" name="creationCode" type="text" size="30" maxlength="50"/>
														  <span class="prop-tips">自主创新产品</span>
		                            					</li>
		                            				  	<li  style="height:30px;">
		                            					  <input name="isEnvirement" id="isEnvirement" type="checkbox"  class="checkbox"/>
		                            					  <label for="isApplyPostage_false">环境标志产品编号：</label>
														  <input id="environmentLabel" name="environmentLabel" type="text" size="30" maxlength="50"/>
														  <span class="prop-tips">进入《环境标志产品政府采购清单》</span>
		                            					</li>
		                            				  	<li  style="height:30px;">
		                            					  <input name="isTechCode" id="isTechCode" type="checkbox"  class="checkbox"/>
		                            					  <label for="isApplyPostage_false">密码技术产品编号：</label>
														  <input id="cryptographyTechCode" name="cryptographyTechCode" type="text" size="30" maxlength="50"/>
														  <span class="prop-tips">进入《含有密码技术的信息产品政府采购清单》</span>
		                            					</li>
		                            				</ul>
	                            				</div>
											</span>
										</li>
									</ul>
								</div>
							
							
							
							<h5>3.商品图片：</h5>
							<div class="upload-manager upload-pic">
								<ul class="tabs">
									<li id="oneLi" class="selected"><span><a id="uploadOne" href="javascript:void(0);">上传商品主图</a></span></li>
									<li id="moreLi"><span><a id="uploadMore" href="javascript:void(0);">上传更多图片</a></span></li>
								</ul>
								
								<div id="mainPic" class="panel panel-local" style="display: block;">
									<div class="side"></div>
									<div id="newPreview">
										<img src="<%=request.getContextPath()%>/view/resource/skin/goods/img/goods_add.gif" width="120px" height="120px" style="border:1px solid #D5D5D5;" />
									</div>
									<input type="button" id="toCropZoomImgBut" value="选择图片" />
								</div>
								<div id="morePic" class="panel panel-local" style="display: none;"></div>
							</div>
							</form>
							
							<form id="GoodsParamForm" name="GoodsParamForm" method="post"> 	
								<h5>4. 商品参数</h5>
								<div class="form">
									<c:choose>
									<c:when test="${!empty goodsClassParamList && fn:length(goodsClassParamList) > 0}">
									<ul>
						   			<c:forEach var="classParam" items="${goodsClassParamList}" varStatus="status1">
										<c:if test="${classParam.isLeaf == false}">
										<li><label>${classParam.paramName}：</label>
											<input type="hidden" name="goodsParamSet[${status1.index}].typeName" value="${classParam.paramName}" />
											<input type="hidden" name="goodsParamSet[${status1.index}].goodsClassParam.objId" value="${classParam.objId}" />
											<input type="hidden" name="goodsParamSet[${status1.index}].sortNo" value="${classParam.sort}" />
											<span>
			   									<div class="module-form">
			   										<div class="skin">
			   											<ul class="ul-radio ul-radio-vertical">
			   												<c:forEach var="paramLeaf" items="${goodsClassParamList}" varStatus="status2">
							   								<c:if test="${paramLeaf.isLeaf == true && paramLeaf.parent.objId==classParam.objId}">
				                            				  	<li style="width:48%">
				                            					  <label style="width:125px;float:left;" title="${paramLeaf.paramName}">${paramLeaf.paramName}<c:if test="${!empty paramLeaf.paramUnit}">(${paramLeaf.paramUnit})</c:if>：</label>
																  <input type="hidden" name="goodsParamSet[${status2.index}].paramName" value="${paramLeaf.paramName}" />
								   								  <input type="hidden" name="goodsParamSet[${status2.index}].goodsClassParam.objId" value="${paramLeaf.objId}" />
								   								  <input type="hidden" name="goodsParamSet[${status2.index}].sortNo" value="${paramLeaf.sort}" />
								   								  <c:choose>
																	<c:when test="${paramLeaf.needInput == true}">
																		<input type="text" name="goodsParamSet[${status2.index}].paramValue" class="required" maxlength="200"/>
									   									<span class="eleRequired"><em>*</em></span>
																	</c:when>		
																	<c:otherwise>
																		<input type="text" name="goodsParamSet[${status2.index}].paramValue" maxlength="200"/>
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
							   					<textarea name="spec" id="spec" maxLength="1000" class="required" style="height:100px;width:638px;"></textarea>
							   				</li>
							   			</ul>
						   			</c:otherwise>
						   			</c:choose>
								</div>
								
								<div class="submit">
									<c:if test="${currRoleType == '4'}">
										<input type="button" name="saveButton" value="提交" id="saveGoodsBtn_submit" class="ic-btn ic-btn-blue"/>
										<input type="button" name="saveButton" value="保存为临时" id="saveGoodsBtn_save" class="ic-btn ic-btn-blue"/>
									</c:if>
									<!-- manager -->
									<c:if test="${currRoleType == '3'}">
										<input type="button" name="saveButton" value="保存为临时" id="saveGoodsBtn_save" class="ic-btn ic-btn-blue"/>
										<input type="button" name="saveButton" value="保存为正式" id="saveGoodsBtn_valid" class="ic-btn ic-btn-blue"/>
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


<script>
var GoodsBaseInfoForm={};
var htmlEditor;

//保存商品
GoodsBaseInfoForm.submit = function(saveType){
	//清空异步上传图片页内容
	$('#morePic').html('');
	
	var url = $('#initPath').val()+"/GoodsController.do?method=createGoods";

	//每次先将goodsStr值置为空
	$("#goodsString").val("");
	var baseInfoForm = formToJsonObject('GoodsBaseInfoForm','json');

	//状态
	if(saveType=="save"){
		baseInfoForm.auditStatus="00";//保存
	}else if(saveType=="submit"){
		baseInfoForm.auditStatus="01";//提交
	}else if(saveType=="valid"){
		baseInfoForm.auditStatus="02";//保存为正式
	}
	
	//处理特殊属性
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
			window.location.href=$('#initPath').val()+'/GoodsController.do?method=toGoodsSubmitResultView&goodsStatus='+baseInfoForm.auditStatus+"&goodsId="+json.goodsid;
			$('input[name=saveButton]').removeAttr('disabled','disabled');
		},
		error:function(msg){
			$('input[name=saveButton]').removeAttr('disabled','disabled');
			alert(JSON.stringify(msg));
		}
	});
}

$(document).ready(function(){	
	//表单验证
	$("#GoodsParamForm").validate();
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
	$('input[name=saveButton]').click(function(){     
		//编辑器填值
		var editorValue = (  htmlEditor.getValue()=='<br> ' )?'':htmlEditor.getValue();//编辑器问题
		$('#functionIntro').val( editorValue );
		if(!$('#GoodsBaseInfoForm').valid()){alert('请正确填写表单!');return;}   
		if(!$('#GoodsParamForm').valid()) {alert('请正确填写表单!');return;}
		var disP = ( $(this).attr("id").replace("saveGoodsBtn_","")=="save" )?"保存":"提交";
		if(window.confirm("确认"+disP+"商品吗?")){
			GoodsBaseInfoForm.submit($(this).attr("id").replace("saveGoodsBtn_",""));
		}
	})
	
	//重新编辑类目
	$("#rebackBtn").click(function(){         
		window.location.href = $('#initPath').val()+"/GoodsController.do?method=toCreateGoods";
	})
	
	/*
	//预览图片
	$("#pictureFile").change(function(){
		var filePath = $("#pictureFile").val();
		var fileName = filePath.replace(/.+\\([^\\]+)/,'$1');
		var i = fileName.lastIndexOf('.');       	 //从右边开始找第一个'.'
		var len = fileName.length;                	 //取得总长度
		var str = fileName.substring(len,i+1);    	 //取得后缀名
		var exName = "PNG,BMP,JPG,GIF";       		 //允许的后缀名
		var k = exName.indexOf(str.toUpperCase());	 //转成大写后判断
		if(k==-1){                                	 //没有符合的
		    alert("上传文件错误！只能上传"+exName);
			this.value="";
		}else{
			$("#newPreview").html('<img src="'+preViewPic(this)+'"></img>');
		}
	})
	
	//鼠标在图片上悬停
	$("#firstLi").hover(
		function(){
			if($("#pictureFile").val()) {
				$("#act").css("display","block");
			}
		}
		,
		function(){$("#act").css("display","none");}
	)
	
	//删除图片
	$('#delSpan').click(function(){
		$("#pictureFile").val("");
		$("#newPreview").html('<img src="AttachmentController.do?method=showImg"></img>');
	})
	
	*/

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
					attachRelaId:'',
					isView:'0',//是否只是显示图片不删除和上传
					maxWidth:80,//建议长
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
	htmlEditor.setValue(" ");

	//选择商品主图片
	$("#toCropZoomImgBut").click(function(){
		var url = $('#initPath').val()+"/view/pubservice/application/cropzoomimg/crop_zoom_img.jsp?picWidth=200&picHeight=200&pic_WH_rule_str=goods_pic_width_height_rule&propertyName=picture";
		$.epsDialog({
			title: '选择商品图片',
			url: url
		});
	});
})
</script>
</body>
</html>
