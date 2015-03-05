<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/index.css"/>
<div class="hdjs_main">
	<div class="hdjs_main_left">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/hdjs_bg01.jpg" />
		<div id="showVoteResultTopNum">
			<%@ include file="/view/smallscale/vote/show_vote_result_topNum.jsp" %>
		</div>
	</div>
	<div class="hdjs_main_right">
		<h4>在线报名</h4>
		<h4>二、发布品牌（带*号的为必填项）</h4>
		<div class="formLayout form2Pa">
		<form id="brandForm" method="post" enctype="multipart/form-data">	
			<input type="hidden" name="objId" id="objId" value= ""/>
			<ul>
				<li class="formTextarea">
					<label>商品分类：</label>
					<input type="hidden" name="goodsClassIds" id="goodsClass.objId"/>
					<textarea style="width:360px;height:110px;" readonly="readonly" name="goodsClassNames"  class="required" id="goodsClass.name" onclick="brand_add.chooseClassByCategory();"></textarea>
					<a href="javascript:void(0);" onclick="brand_add.chooseClassByCategory();"><span class="sysicon siSearch">&nbsp;</span></a>
					<span class="eleRequired">*</span> 
				</li> 
				<li class="fullLine">
					<label>品牌名称：</label>
					<input type="text" name="brandName" id="brandName" class="required" size="50" onchange="brand_add.brandNameCheck(this);" onfocus="brand_add.brandNameFocus();"/>
					<span class="eleRequired">*</span><br>
					<div style="margin-left: 120px;">品牌名称应当是真实可信的，相关部门存档备案的真实数据</div>
				</li>
				<li class="fullLine">
					<label>英文名称：</label>
					<input  type="text" id="englishName" name="englishName" class="required" size="50" onchange="brand_add.brandNameCheck(this);" onfocus="brand_add.brandNameFocus();" />
					<span class="eleRequired">*</span>
				</li>
				<li class="fullLine">
					<label>外地品牌：</label>
					<select id="ecdemic" name="ecdemic">
			        	<option value="false">否</option>
			        	<option value="true">是</option>
		        	</select>
				</li>
				<li class="fullLine">
					<label>国外品牌：</label>
					<select id="foreigner" name="foreigner">
			        	<option value="false">否</option>
			        	<option value="true">是</option>
		        	</select>
				</li>
				<li class="fullLine">
					<label>所属机构：</label>
					<input type="hidden" name="belongsId" value="${blongOrg.objId}"/>
					<input type="hidden" name="belongsName" value="${blongOrg.orgName}"/>
					${blongOrg.orgName }
				</li>
				<li class="fullLine">
					<label>品牌描述：</label>
					<textarea id="brandDesc" name="brandDesc" class="required" style="width:360px;height:110px;" maxlength="250"></textarea><span class="eleRequired">*</span>
					<div style="margin-left: 100px;">注意：请勿在详细说明中出现您的公司名、电话、电子邮箱、传真等联系信息，出现上述内容的品牌信息将无法通过审核;</div>
				</li>
				<li class="fullLine">
					<label>品牌图片：</label>
					<div class="k1">
						<div class="img_250_1" id="newPreview">
							<img width="200" height="175" src="<c:url value="/view/resource/skin/goods/img/brand_add.gif"/>"></img>
						</div>
						<input name="mainLogoFile" type="file" class="required" id="mainLogoFile" size="22" style="margin-left: 130px;"/><span class="eleRequired">*</span>
					</div>
				</li>
			</ul>	
		</form>
		</div>
		<div class="formLayout form2Pa">
			<form id="voteAssessedThingFrom" name="voteAssessedThingFrom" method="post">
				<input type="hidden" name="voteTemplate.objId" id="voteTemplate.objId" value="${templateId}"/>
				<input type="hidden" name="attenderOrgInfoId" id="attenderOrgInfoId" value="${blongOrg.objId}"/>
				<input type="hidden" name="attenderName" id="attenderName" value=""/>
				<input type="hidden" name="isRecommended" id="isRecommended" value="0"/>
				<input type="hidden" name="useStatus" id="useStatus" value="00"/>
				<ul>
					<li class="fullLine">
						<label>参选评选组：</label>
						<select name="voteUnitGroup.objId" id="voteUnitGroupObjId">
							<c:forEach items="${voteUnitGroupList}" var="voteUnitGroup">
								<option value="${voteUnitGroup.objId }">${voteUnitGroup.groupName }</option>
							</c:forEach>
						</select>
					</li>
				</ul>
			</form>
		</div>
	  	<div class="conOperation" style="text-align: center;">
			<button type="button" onclick="brand_add.createOrUpdateBrand('submit');"><span><spring:message code="globe.submit"/></span></button>
		</div>
	</div>
</div>

<script type="text/javascript">
var brand_add = {};

//选择分类
brand_add.chooseClassByCategory = function(){
	$.epsDialog({
		id:'chooseClassDiv',
		title:'选择分类',
		url:$("#initPath").val()+'/GoodsBrandController.do?method=toChooseClassByCategory&checkStatus=1&property=goodsClass&isCheckBox=true&childNodeOnly=true&isManager='+$("#isManager").val()
	})
}

//检查品牌名称合法性
brand_add.brandNameCheck = function(e){
	var brandName = $(e).val();
	if(brandName){
		var param = {};
		param.brandName = brandName;
		param.goodsClassId = $("input[id=goodsClass.objId]").val();
		
		if($("#objId").val()){
			param.objId = $("#objId").val();
		}
		$.getJSON($("#initPath").val()+"/GoodsBrandController.do?method=checkBrandName",param,function(json){
			//成功
			if(json.checkType=='true'){
				$(e).parent().find("span").remove().append('asdfadsf');
			}else{
				$(e).parent().find("span").remove()
				$(e).parent().append('<span style="color:#CC0000;">已有相同名称.</span>');
			}
		})
	}else{
		$(e).parent().find("span").remove()
		$(e).parent().append('<span style="color:#CC0000;">这是必填项.</span>');
	}
}

//品牌名称聚焦事件
brand_add.brandNameFocus = function(){
	if(!$("input[id=goodsClass.objId]").val()){
		alert("请先选择分类！");
		$("textarea[id=goodsClass.name]").focus();
	}
}

//保存或者提交
brand_add.createOrUpdateBrand = function(saveType){
	//设置参选者名称
	var brandName = $('#brandName').val();
	$('#attenderName').val(brandName);
	
	if(!$('#brandForm').valid()){alert('请正确填写表单!');return;}
	var userName = $('#userName').val();
	var brandJson = formToJsonObject("brandForm");
	brandJson.classBrandString = $("input[id=goodsClass.objId]").val();
	brandJson.saveType = saveType;
	$('#brandForm').ajaxSubmit({
		url:$("#initPath").val()+"/GoodsBrandController.do?method=saveGoodsBrand&classBrandString="+brandJson.classBrandString+"&saveType="+brandJson.saveType,
		dataType:'json',
		success:function(json){
			if(json.result=='success') {
				alert("发布品牌成功！");
				$('#voteAssessedThingFrom').ajaxSubmit({
					url:$("#initPath").val()+"/VoteAssessedThingController.do?method=signOnLineAssessedThing",
					dataType:'json',
					success:function(json){
						alert('报名成功！请等待审核通过！');
						//$("#conBody").loadPage($("#initPath").val()+"/view/smallscale/vote/sign_online_has_user.jsp?userName="+userName);
						window.location.href = $('#initPath').val() + '/VoteTemplateShowController.do?method=showVoteIndex&templateCode=VT00000002';
					},
					error:function(msg){
						alert('报名失败！请于品牌管理处添加品牌！审核通过后重新报名！');
					}
				});
			}else {
				alert(ascii2native(json.result));
			}
		},
		error:function(msg){
			alert("发布品牌失败！");
		}
	});
}

$(document).ready(function(){
	//验证
	$("#brandForm").validate();
	
	//预览文件
	$("#mainLogoFile").change(function(){
		var filePath = $("#mainLogoFile").val();
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
			$("#newPreview").find('img').attr("src",preViewPic(this));
		}
	})
});
</script>