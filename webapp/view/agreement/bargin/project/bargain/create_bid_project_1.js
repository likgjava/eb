var CreateProjectForm1 = {};

//克隆需求条目
CreateProjectForm1.cloneRequireItem = function(cloneType) {
	var trSize;
	var totalLen = 0;
	if(cloneType == 'desc') {
		totalLen = $('#CreateProjectForm1').find('div[id^=requireItemDiv]').length;
		if($('#CreateProjectForm1').find('div[id^=requireItemDiv]').length < 1) {
			trSize = 1;
		} else {
			trSize = $('#CreateProjectForm1').find('div[id^=requireItemDiv]:last').attr('id').replace('requireItemDiv','');
			trSize = parseInt(trSize) + 1;
		}
	} else {
		totalLen = $('#CreateProjectForm1').find('div[id^=cartItemDiv]').length;
		if($('#CreateProjectForm1').find('div[id^=cartItemDiv]').length < 1) {
			trSize = 1;
		} else {
			trSize = $('#CreateProjectForm1').find('div[id^=cartItemDiv]:last').attr('id').replace('cartItemDiv','');
			trSize = parseInt(trSize) + 1;
		}
	}
	if(totalLen >= 5) {
		alert("已到达最大需求条目数");
		return;
	}
	//无商品模板
	if(cloneType == 'desc') {
		var newDiv = $('#cloneDescDiv1').clone(true);
		$(newDiv).attr('id','requireItemDiv' + trSize);
		//修改原始id和name
		$(newDiv).find('textarea[id=descr1]').attr('id','descr'+trSize).attr('name','descr'+trSize);
		$(newDiv).find('input[id=purCategoryId1]').attr('id','purCategoryId'+trSize);
		$(newDiv).find('input[id=purCategoryName1]').attr('id','purCategoryName'+trSize);
		$(newDiv).find('input[id=goodsPrice1]').attr('id','goodsPrice'+trSize).attr('name','goodsPrice'+trSize);
		$(newDiv).find('input[id=goodsQty1]').attr('id','goodsQty'+trSize).attr('name','goodsQty'+trSize);
		$(newDiv).find('input[id=goodsUnit1]').attr('id','goodsUnit'+trSize).attr('name','goodsUnit'+trSize);
		$(newDiv).find('input[id=goodsTotal1]').attr('id','goodsTotal'+trSize).attr('name','goodsTotal'+trSize);
		$(newDiv).find('span[id=goodsTotalSpan_1]').attr('id','goodsTotalSpan_'+trSize);
		$(newDiv).find('a[id=delRequireItem_1]').attr('id','delRequireItem_'+trSize);
		$("#CreateProjectForm1").append(newDiv);
		//缓慢显示
		$('div[id=requireItemDiv'+trSize+']').show('slow');
	}
	//有商品模板
	else if(cloneType == 'goods') {
		var newDiv = $('#cloneGoodsDiv1').clone(true);
		$(newDiv).attr('id','cartItemDiv' + trSize);
		//修改原始id和name
		$(newDiv).find('input[id=goodsId1]').attr('id','goodsId'+trSize);
		$(newDiv).find('input[id=purCategoryId1]').attr('id','purCategoryId'+trSize);
		$(newDiv).find('input[id=purCategoryName1]').attr('id','purCategoryName'+trSize);
		$(newDiv).find('input[id=marketPrice1]').attr('id','marketPrice'+trSize).attr('name','marketPrice'+trSize);
		$(newDiv).find('input[id=agreePrice1]').attr('id','agreePrice'+trSize).attr('name','agreePrice'+trSize);
		$(newDiv).find('input[id=goodsPrice1]').attr('id','goodsPrice'+trSize).attr('name','goodsPrice'+trSize);
		$(newDiv).find('input[id=goodsQty1]').attr('id','goodsQty'+trSize).attr('name','goodsQty'+trSize);
		$(newDiv).find('input[id=goodsUnit1]').attr('id','goodsUnit'+trSize).attr('name','goodsUnit'+trSize);
		$(newDiv).find('input[id=productName1]').attr('id','productName'+trSize);
		$(newDiv).find('input[id=goodsTotal1]').attr('id','goodsTotal'+trSize).attr('name','goodsTotal'+trSize);
		$(newDiv).find('span[id=goodsTotalSpan_1]').text('');
		$(newDiv).find('span[id=goodsTotalSpan_1]').attr('id','goodsTotalSpan_'+trSize);
		$(newDiv).find('a[id=delRequireItem_1]').attr('id','delRequireItem_'+trSize);
		$(newDiv).find('span[id=marketPrice1]').attr('id','marketPrice'+trSize).attr('name','marketPrice'+trSize);
		$(newDiv).find('span[id=agreePrice1]').attr('id','agreePrice'+trSize).attr('name','agreePrice'+trSize);
		$("#CreateProjectForm1").append(newDiv);
		//缓慢显示
		$('div[id=cartItemDiv'+trSize+']').show('slow');
	}
}
//删除需求条目
function removeRequireItem(i,divType){
	if(window.confirm('确定删除?')) {
		$('#CreateProjectForm1').find('div[id='+divType+']').remove();
		changeTotal();
	}
}

//计算行金额和总金额、总数量
function changeItem(i){
	var totalRow = parseFloat($('#goodsPrice'+i).val()) * parseInt($('#goodsQty'+i).val());
	if(totalRow+'' != "NaN" && totalRow+'' != "NaN") {
		$('span[id=goodsTotalSpan_'+i+']').text(formatAmount(totalRow,2));
		$('#goodsTotal'+i).val(totalRow);
	}
	changeTotal();
}
//计算需求总预算
function changeTotal(){
	var total = 0;
	$('#CreateProjectForm1').find('.bd_post_form').each(function(i,n){
		total += parseFloat($(n).find('span[id=goodsTotalSpan_'+i+']').text()==""?0:$(n).find('span[id=goodsTotalSpan_'+i+']').text().replace(/,/g,''));
	})
	$('#total').text(formatAmount(total,2));
	$('#budgetTotalMoney').val(total);
}
//挑选需求商品
function selectGoods(index){
	$.epsDialog({
        title:'挑选商品',
        url:$('#initPath').val()+'/view/agreement/bargin/project/bargain/goods_select_list.jsp?ctrlIndex='+index,
        width: 800,
		height: 400,
		onClose: function(){
			changeItem(index);
		}
	})
}
//挑选品目
function selectCategory(index){
	$.epsDialog({
        title:'选择品目',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=purCategoryId'+index+'&NAMES=purCategoryName'+index+'&className=PurCategory&action=listTop&childNodeOnly=true'
    }); 
}
//判断是否选择商品,以及商品数量是否为0
function checkGoods() {
	var tempV = {};
	$('#CreateProjectForm1').find('div[id^=requireItemDiv]').each(function(i,n){
		var index = $(n).attr('id').replace('requireItemDiv','');
		if($('input[id=goodsQty'+index+']').val() == 0) {
			tempV.msg = '请输入商品数量';
		}
		tempV.divType = 'desc';
	})
	$('#CreateProjectForm1').find('div[id^=cartItemDiv]').each(function(i,n){
		var index = $(n).attr('id').replace('cartItemDiv','');
		if($('input[id=goodsId'+index+']').val() == null || $('input[id=goodsId'+index+']').val() == "") {
			tempV.msg = '请选择商品';
		}else {
			if($('input[id=goodsQty'+index+']').val() == 0) {
				tempV.msg = '请输入商品数量';
			}
		}
		tempV.divType = 'goods';
	})
	return tempV;
}
$(document).ready(function(){
	//项目名称唯一性校验
	$.validator.addMethod("projNameUnique",function(value,element,param){return uniqueHandler("Project",param,value,"");},'该项目名称已被使用');
	$("#CreateProjectForm1").validate({
		rules:{
			projName:{projNameUnique:"projName"}
		}
	});	
	
	$("#signUpSTime").epsDatepicker({ timeShow:true }).val(new Date().Format('yyyy-MM-dd hh:mm:00'));
	$("#signUpETime").epsDatepicker({ timeShow:true });

	//计算总预算
	changeTotal();
	
	//选择品目
	$("input[name=purCategory.name]").click(function(){
	    selectCategory($(this).attr('id').replace('purCategoryName',''));
	});

	//删除需求条目
	$('a[id^=delRequireItem_]').click(function(){
		if($(this).attr('type')) {
			removeRequireItem($(this).attr('id').replace('delRequireItem_',''),'cartItemDiv'+$(this).attr('id').replace('delRequireItem_',''));
		} else {
			removeRequireItem($(this).attr('id').replace('delRequireItem_',''),'requireItemDiv'+$(this).attr('id').replace('delRequireItem_',''));
		}
	})

	//挑选需求商品
	$('input[id^=productName]').click(function(){
		selectGoods($(this).attr('id').replace('productName',''));
	})
	
	//单个预算失去焦点后计算
	$('input[id^=goodsPrice]').blur(function(){
		changeItem($(this).attr('id').replace('goodsPrice',''));
	})
	
	//数量失去焦点后计算
	$('input[id^=goodsQty]').blur(function(){
		changeItem($(this).attr('id').replace('goodsQty',''));
	})
	
	//保存-去设置轮次和规则
	$('#bd_post_submit_btn').click(function(){
		if(!$('#CreateProjectForm1').valid()){alert('请正确填写表单!');return;}
		var res = checkGoods();
		if(res.msg) {
			alert(res.msg);
		}else{
			//res.divType为goods或desc，有商品或无商品循环组装条目不一样
			if(window.confirm('确定保存?')) {
				CreateProjectForm1.save(res.divType);
			}
		}
	});
})

//保存
CreateProjectForm1.save = function(divType){
	if($('#signUpSTime').val().length==16) {
		$('#signUpSTime').val($('#signUpSTime').val()+":00");
	}
	
	if($('#signUpSTime').val() >= $('#signUpETime').val()) {
		alert("报名开始时间不能大于报名结束时间");
		return;
	}
	
	if(!divType) {
		alert("请添加需求条目");
		return;
	} 
	
	//所有条件验证完毕更换按钮样式
	$('#submitDiv').addClass('hidden')
	$('#submittingDiv').removeClass('hidden');
	
	var jsonObj = formToJsonObject('CreateProjectForm1');
	
	if(divType == 'desc') {
		//需求条目对象
		var requireItemJson = [];
		$('div[id^=requireItemDiv]').each(function(i,n){
			var index = $(n).attr('id').replace('requireItemDiv','');
			var descr = $(n).find('textarea[id=descr'+index+']').val();//商品描述
			var purCategory = $(n).find('input[name=purCategory.objId]').val();//品目
			var categoryName = $(n).find('input[name=purCategory.name]').val();//品目名称
			var goodsPrice = $(n).find('input[id=goodsPrice'+index+']').val();//价格
			var goodsQty = $(n).find('input[id=goodsQty'+index+']').val();//数量
			var goodsUnit = $(n).find('input[id=goodsUnit'+index+']').val();//单位
			var goodsTotal = $(n).find('input[id=goodsTotal'+index+']').val();//金额
			
			//拼装json数组对象
			requireItemJson[i] = {};
			requireItemJson[i].objId = $(n).attr("tiid")+"_tiid";
			requireItemJson[i].descr=descr;
			requireItemJson[i].purCategory = {};
			requireItemJson[i].purCategory.objId = purCategory;
			requireItemJson[i].purCategory.categoryName = categoryName;
			requireItemJson[i].goodsPrice=goodsPrice;
			requireItemJson[i].goodsQty=goodsQty;
			requireItemJson[i].goodsUnit=goodsUnit;
			requireItemJson[i].goodsTotal=goodsTotal;
		})
		jsonObj.require = JSON.stringify(requireItemJson);
		
		//任务书条目
		if($('#taskItemIds').val()) {
			var taskJson = [];
			var taskArray = $('#taskItemIds').val().split(',');
			$.each(taskArray,function(i,n){
				taskJson[i] = {};
				taskJson[i].protaskItem = {};
				taskJson[i].protaskItem.objId = n;
			})
			jsonObj.task = JSON.stringify(taskJson);
		}
		
		//采购需求
		if($('#requirementIds').val()) {
			jsonObj.requirementIds = $('#requirementIds').val();
		}
		
		//保存
		$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=createProjectNoGoods',jsonObj, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			
			//跳转至创建设置竞价规则页面
			window.location.href=$('#initPath').val()+'/BargainProjectController.do?method=toCreateBidProject_2&projId='+json.projId;
		})
	} 
	//创建带商品的项目和需求
	else {
		var cartItemIds = "";
		//需求条目对象
		var requireItemJson = [];
		$('div[id^=cartItemDiv]').each(function(i,n){
			var index = $(n).attr('id').replace('cartItemDiv','');
			
			var goodsPrice = $(n).find('input[id=goodsPrice'+index+']').val();//单价
			var goodsQty = $(n).find('input[id=goodsQty'+index+']').val();//数量
			var goodsUnit = $(n).find('input[id=goodsUnit'+index+']').val();//计量单位
			
			requireItemJson[i] = {};
			
			if($(n).attr('cartitemid')) {//拼装购物车条目id等参数
				cartItemIds += $(n).attr('cartitemid') + "_" + goodsPrice + "_" + goodsQty + "_" + goodsUnit+ ",";//购物车条目ID
			} else {
				//拼装json数组对象
				requireItemJson[i].productName=$(n).find('span[id=productName'+index+']').text();//商品名称
				requireItemJson[i].marketPrice=$(n).find('input[id=marketPrice'+index+']').val();//市场价
				requireItemJson[i].agreePrice=$(n).find('input[id=agreePrice'+index+']').val();//协议价
				requireItemJson[i].goodsPrice=goodsPrice;//单价
				requireItemJson[i].goodsQty=goodsQty;//数量
				requireItemJson[i].goodsUnit=goodsUnit;//计量单位
				requireItemJson[i].goodsTotal=$(n).find('input[id=goodsTotal'+index+']').val();//金额
				requireItemJson[i].goods = {};
				requireItemJson[i].goods.objId = $(n).find('input[id=goodsId'+index+']').val();
				requireItemJson[i].purCategory = {};
				requireItemJson[i].purCategory.objId = $(n).find('input[id=purCategoryId'+index+']').val();//品目ID
				requireItemJson[i].purCategory.categoryName = $(n).find('input[id=purCategoryName'+index+']').val();//品目名称
			}
		})
		jsonObj.require = JSON.stringify(requireItemJson);
		//保存
		$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=createProjectGoods&cartItemIds='+native2ascii(cartItemIds.substring(0,cartItemIds.length-1)),jsonObj, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			
			//跳转至创建设置竞价规则页面
			window.location.href=$('#initPath').val()+'/BargainProjectController.do?method=toCreateBidProject_2&projId='+json.projId;
		})
	}
}