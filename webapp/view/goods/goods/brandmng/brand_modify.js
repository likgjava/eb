brand_modify = {}

//我要反馈
brand_modify.addSelfAdvise = function(){
	var url = $('#initPath').val()+"/AdviseController.do?method=toCreateAdvise&isDialog=true";
	$.epsDialog({
		title:'我要反馈',
		url:url
	});
}

//选择分类
brand_modify.chooseClassByCategory = function(){
	$.epsDialog({
		id:'chooseClassDiv',
		title:'选择分类',
		url:$("#initPath").val()+'/GoodsBrandController.do?method=toChooseClassByCategory&checkStatus=1&property=goodsClass&isCheckBox=true&childNodeOnly=true&isManager='+$("#isManager").val()
		,onClose:function(){
			if($("#brandName").val()){
				brand_modify.brandNameCheck($("#brandName"));
			}
			if($("#englishName").val()){
				brand_modify.brandNameCheck($("#englishName"));
			}
		}
	})
}

//检查品牌名称合法性
brand_modify.brandNameCheck = function(e){
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
				$(e).parent().find("span").remove();
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
brand_modify.brandNameFocus = function(){
	if(!$("input[id=goodsClass.objId]").val()){
		alert("请先选择分类！");
		$("textarea[id=goodsClass.name]").focus();
	}
}


//选择机构
brand_modify.chooseOrg = function(){
	
	var url =$('#initPath').val()+'/view/bizplatform/organization/manager/select_orginfo.jsp?Hname=belongsName&Hid=belongsId&orgType=s&isManufacturer=true';
    $.epsDialog({
        title:'请选择所属机构',
        url:url
    });
	
}

//保存或者提交
brand_modify.createOrUpdateBrand = function(saveType){
	
	if(!$('#brandForm').valid()){alert('请正确填写表单!');return;}
	
	var brandJson = formToJsonObject("brandForm");
	brandJson.classBrandString = $("input[id=goodsClass.objId]").val();
	brandJson.saveType = saveType;
	$('#brandForm').ajaxSubmit({
		url:$("#initPath").val()+"/GoodsBrandController.do?method=saveGoodsBrand&classBrandString="+brandJson.classBrandString+"&saveType="+brandJson.saveType,
		dataType:'json',
		success:function(json){
			if(json.result=='success') {
				window.location.href=$("#initPath").val()+"/GoodsBrandController.do?method=toGoodsBrandSuccess&objId="+json.goodsBrand.objId;
			}else {
				alert(ascii2native(json.result));
			}
		},
		error:function(msg){
			alert(JSON.stringify(msg));
		}
	});
}

$(document).ready(function(){
	
	//验证
	$("#brandForm").validate();
	
	//选择品牌图片
	$("#toCropZoomImgBut").click(function(){
		var url = $('#initPath').val()+"/view/pubservice/application/cropzoomimg/crop_zoom_img.jsp?picWidth=160&picHeight=120&pic_WH_rule_str=brands_pic_width_height_rule&propertyName=mainLogo&oldAttachmentId="+$("#mainLogo").val();
		$.epsDialog({
			title: '选择品牌图片',
			url: url
		});
	});
})