brand_change = {}

//变更相关属性
brand_change.changeProperties = function(properties,objId,e){
	switch (properties){   
	    case 'goodsClass':{
	    	brand_change.changeGoodsClass(objId,e);
	    	break;
	    }
	    case 'brandName':{
	    	brand_change.changeBrandName(objId,e);
	    	break;
	    }
	}   
}

//改变分类
brand_change.changeGoodsClass = function(objId,e){
	if($(e).parent().find("input[id=changeClass.objId]").html()==null){
		$(e).parent().append('<input type="hidden" id="changeClass.objId" name="changeClass.objId" value="'+$("input[id=goodsClass.objId]").val()+'"/>');
	}
	if($(e).parent().find("input[id=changeClass.name]").html()==null){
		$(e).parent().append('<input type="hidden" id="changeClass.name" name="changeClass.name" value="'+$("#goodsClassNames").html()+'"/>');
	}
	
	$.epsDialog({
		id:'chooseClassDiv',
		title:'选择分类',
		url:$("#initPath").val()+'/GoodsBrandController.do?method=toChooseClassByCategory&checkStatus=1&property=changeClass&isCheckBox=true&childNodeOnly=true&isManager='+$("#isManager").val()
		,onClose:function(){
			if($("input[id=changeClass.name]").val()){
				$(e).parent().prev("span").html($("input[id=changeClass.name]").val());
			}
			//中文名称
			var brandName = $("#brandNameSpan").html()!=null?$("#brandNameSpan").html():"";
			brandName = $("input[name=changeName]").val()!=''?$("input[name=changeName]").val():brandName;
			if(brandName!=null&&brandName!=''){
				brand_change.changeClassCheck(brandName,'brandNameHref');
			}
			//英文名称
			var englishName = $("#englishNameSpan").html()!=null?$("#englishNameSpan").html():"";
			englishName = $("input[name=changeEnglish]").val()!=''?$("input[name=changeEnglish]").val():englishName;
			if(englishName!=null&&englishName!=''){
				brand_change.changeClassCheck(englishName,'englishNameHref');
			}
		}
	})
}

//检测改变class之后的表单
brand_change.changeClassCheck = function(brandName,columns){
	
	var param = {};
	param.brandName = brandName;
	if($("input[id=goodsClass.objId]").val()){
		param.goodsClassId = $("input[id=goodsClass.objId]").val();
	}
	if($("input[id=changeClass.objId]").val()){
		param.goodsClassId = $("input[id=changeClass.objId]").val();
	}
	if($("#objId").val()){
		param.objId = $("#objId").val();
	}
	$.getJSON($("#initPath").val()+"/GoodsBrandController.do?method=checkBrandName",param,function(json){
		//成功
		if(json.checkType=='true'){
			$("#"+columns).parent().find("span").remove();
		}else{
			$("#"+columns).parent().find("span").remove()
			$("#"+columns).parent().append('<span style="color:#CC0000;">&nbsp;已有相同名称.</span>');
		}
	})
}

//改变名称
brand_change.changeBrandName = function(objId,e){
	
	//已经变更的数据
	var changeName = $(e).prev("font").html()!=null?$(e).prev("font").html():'';
	
	if($("input[id=changeClass.objId]").val()||$("input[id=goodsClass.objId]").val()){
		$.epsDialog({
			id:'changeNameDiv',
			title:'变更名称',
			content:'<div class="formLayout formPa">'
				+'<ul><li><label for="changeName">变更名称：</label>'
				+'<input style="width:300px;" id="changeName" value="'+changeName+'" onchange="brand_change.brandNameCheck(this);"/></li></ul>'
				+'<div class="conOperation"><button name="confirmChange"><span>确定</span></button><button onclick="$(\'.epsDialogClose\').trigger(\'click\')"><span>关闭</span></button></div></div>'
				,width:600
				,height:200
		})
		
		//确定变更
		$("button[name=confirmChange]").click(function(){
			if($("input[id=changeName]").next("span").html()){
				alert("请正确填写！");
			}else{
				$(e).parent().prev("span").html($("input[id=changeName]").val() );
				$(e).parent().prev("span").prev("input").val($("input[id=changeName]").val() );
				//如果已经验证，则更改正确时去掉
				if($(e).parent().find("span").html()){
					$(e).parent().find("span").remove();
				}
				$('.epsDialogClose').trigger('click');
			}
		})
	}else{
		alert("请先选择分类！");
	}
}

//检查品牌名称合法性
brand_change.brandNameCheck = function(e){
	var brandName = $(e).val();
	if(brandName){
		var param = {};
		param.brandName = brandName;
		
		if($("input[id=goodsClass.objId]").val()){
			param.goodsClassId = $("input[id=goodsClass.objId]").val();
		}
		if($("input[id=changeClass.objId]").val()){
			param.goodsClassId = $("input[id=changeClass.objId]").val();
		}
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

//保存或者提交
brand_change.createOrUpdateBrand = function(saveType){
	
	if(!$('#brandForm').valid()){alert('请正确填写表单!');return;}
	
	var brandJson = formToJsonObject("brandForm");
	brandJson.classBrandString = $("input[id=goodsClass.objId]").val();
	brandJson.saveType = saveType;
	$('#brandForm').ajaxSubmit({
		url:$("#initPath").val()+"/GoodsBrandController.do?method=saveGoodsBrand&classBrandString="+brandJson.classBrandString+"&saveType="+brandJson.saveType,
		dataType:'json',
		success:function(json){
			if(json.result=='success') {
				alert("操作成功！");
				window.location.href=$("#initPath").val()+"/GoodsBrandController.do?method=toGoodsBrandSuccess&objId="+json.goodsBrand.objId;
			}else {
				alert(ascii2native(json.result));
			}
		},
		error:function(msg){
			alert("操作成功！");
		}
	});
}





