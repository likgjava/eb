/*
 * 小额交易平台.商品展示列表
 * author: liangxj
 * mail: liangxj@gpcsoft.com
 */
//定义文件全局变量 处理方法名重复问题
var show_list={};

//点击商品分类标题
show_list.searchByCategory = function(goodsClassId,goodsClassCode) {
	window.location.href = $('#initPath').val()+'/GoodsShowController.do?method=toGoodsList&rp=21&page=1&goodsClassId='+goodsClassId+'&goodsClassCode='+goodsClassCode;
	return false;
}

//直接加入购物车
show_list.addToCartDirect = function(goodsId,referPrice,goodsUnit){
	//加入购物车
	if(common.isLogin(true,"请先登录再添加商品到购物车！")){
		var shoppingCartItem={
				goods:{objId:goodsId},
				goodsUnit:goodsUnit,
				goodsQty:"1",
				goodsTotal:referPrice,
				goodsPrice:referPrice,
				agreePrice:referPrice,
				marketPrice:referPrice
		}; 
		shoppingCartItem.cartGoodsOptions=[];
		$.getJSON($('#initPath').val()+'/ShoppingCartItemController.do?method=saveAppendGoodsToShoppingCart',{shoppingCartItemStr:JSON.stringify(shoppingCartItem)},function(json){
			if(json.failure){alert(json.result);return;}
			alert('添加到购物车成功！');
			getCartInfo();
		});
	}
}

//构造搜索条件
show_list.makeSearchData=function(firstPage){
	show_list.search = "";
	
	//分页信息
	if(($('#rp') && $('#rp').val()==null) || firstPage!=null){
		show_list.search += "&rp=20";
		show_list.search += "&page=1";
	}else{
		show_list.search += "&rp="+$('#rp').val();
		show_list.search += "&page="+$('#page').val();
	}
	
	//展示方式
	show_list.search += "&style=" + $("#style").val();
	
	//排序
	var sort = ""; 
	$.each($("span.goodsSort"),function(i,n){
		//获得升序属性
		if($(n).hasClass("siUp")) {
			sort += $(n).attr("name") + "_asc,"; 
		}
		else if($(n).hasClass("siDown")) {
			sort += $(n).attr("name") + "_desc,"; 
		}
	})
	if(sort.length > 0) {
		show_list.search += "&order=" + sort.substring(0,sort.length-1);
	}
	
	//商品分类
	show_list.goodsClassId = "";
	if($('#propDisp1 a.strong').attr("name") != "-1") {
		$.each($('#propDisp1 a.strong'),function(i,n){
			show_list.goodsClassId += $(n).attr("name") + ",";
		})
	}
	if(show_list.goodsClassId.length > 0) {
		show_list.search += "&goodsClassId=" + show_list.goodsClassId.substring(0,show_list.goodsClassId.length-1);
	}
	
	//商品分类code
	show_list.search += "&goodsClassCode=" + $("#goodsClassCode").val();
	
	//品牌
	show_list.brandId = "";
	if($('#propDisp2 a.strong').attr("name") != "-1") {
		$.each($('#propDisp2 a.strong'),function(i,n){
			show_list.brandId += $(n).attr("name") + ",";
		})
	}
	if(show_list.brandId.length > 0) {
		show_list.search += "&brandId=" + show_list.brandId.substring(0,show_list.brandId.length-1);
	}
	
	//关键字
	if($("#keyWord").val() != "") {
		show_list.search += "&keyWord=" + native2ascii(strIgnore($("#keyWord").val()));
	}
	
	//价格区间
	show_list.search += "&price=" + $("#sprice").val() + "," + $("#eprice").val();
	
	//其他过滤条件
	show_list.search += "&moreFilter=" + show_list.getMoreFilter();

	$("#showGoodsListAndPic").empty().loadPage($('#initPath').val()+"/GoodsShowController.do?method=getGoodsForListAndPic"+show_list.search,function(){
		$("#totalAmount").html($("#totalRowNum").val());
		show_list.search = null;//内存泄露问题 by yucy
	});
}

//获得更多过滤条件,将json格式的数据转换成url传参
show_list.getMoreFilter=function(){
	var filter = JSON.stringify(formToJsonObject("goodsFilter"));
	var temp = filter.replace(/\",\"/g,",");
	temp = temp.replace(/\":\"on/g,"");
	temp = temp.replace(/{\"/g,"");
	temp = temp.replace(/\"}/g,"");
	temp = temp.replace(/{}/g,"");
	return temp;
}

//比较复选框点击事件
$("input[name=compareCheckBox]").live('click',function(){
	var goodsId = $(this).attr("gid");
	var goodsName = $(this).attr("gname");
	var goodsCode = $(this).attr("gcode");

	//选中
	if($(this).attr("checked")){
		$("#goodsCompare").removeClass("hidden").show();  //显示比较层
		if($("#C_"+goodsId).length == 0) {
			if($('#goodsCompare ul').find('li').length == 4) {  //最多可以比较四个
				alert("最多可以比较四个！")
				$(this).removeAttr("checked");
				return;
			}else {
				$('#goodsCompare ul').append('<li class=center id=C_'+goodsId+'>'+goodsName+' ('+goodsCode+')&nbsp;<a href="javascript:void(0);" onclick="show_list.removeCompare(\''+goodsId+'\');return false;"><span class="sysicon siExit" >&nbsp;</span></a></li>'); //追加比较数据
			}
		}
	}else { 
		show_list.removeCompare(goodsId);
	}
})

//删除比较数据
show_list.removeCompare=function(goodsId){
	if(goodsId) { //单条删除
		$("#C_"+goodsId).remove();
	
		//将对应的复选框选中状态去掉
		$(":checkbox[gid="+goodsId+"]").removeAttr("checked");
		
		if($('#goodsCompare ul').text() == "") {    //隐藏比较层
			$("#goodsCompare").hide();
		}
	}else {  //全部清除
		$('#goodsCompare ul').empty();
		$(":checkbox[name=compareCheckBox]").removeAttr("checked");
		$("#goodsCompare").hide();  ////隐藏比较层
	}
}

//点击品牌过滤
show_list.changeBrand=function(brandId){
	$("#propDisp2 a").removeClass("strong");
	$("#propDisp2 a[name="+brandId+"]").addClass("strong");
	
	$('#page').val('1');//点击不同的商品分类后重置分页值
	
	show_list.makeSearchData();  //重新加载商品
}

//加入关注
show_list.addAttention = function(goodsId){
	if(common.isLogin(true,true )){
		$.getJSON($("#initPath").val()+"/AttentionPriceController.do?method=saveAttention",{"goodsId":goodsId},function(json){
			if(json.result){
				alert("您已经关注过该商品！");
			} else if(json.success){
				alert("关注成功！");
			}
		})
	}
}

$(document).ready(function(){
	//调整页面布局
	fnRemoveOtherMain(); 
	changeTabsCss("goToGoods"); //选中顶部菜单
	
	//选中查询下拉框
	keyWordTypeChange('3');
	
	show_list.removeCompare();  //清除对比框

	//价格，控制不能输入非数字
	$("#sprice").inputFillter({type:'float'});
	$("#eprice").inputFillter({type:'float'});
	
	//点击分类过滤
	$('#propDisp1 a').click(function(){
		show_list.removeCompare();  //清除对比框
		
		$("#propDisp1 a").removeClass("strong");
		$("#propDisp2 a").removeClass("strong");
		$(this).addClass("strong");
		
		$('#page').val('1');//点击不同的商品分类后重置分页值
		
		show_list.makeSearchData();  //重新加载商品
		
		//获取品牌信息
		$.getJSON($('#initPath').val()+'/GoodsShowController.do?method=getBrandsListShowByClass',{"classId":show_list.goodsClassId,"goodsClassCode":$("#goodsClassCode").val(),"keyWord":native2ascii(strIgnore($("#keyWord").val()))},function(json){
			if(json.result.length < 17) {
				$("#expandProp2").parent().remove();
			}else {
				$("#moreProp2").next("div").empty().append('<a href="javascript:void(0);" onclick="common.showOrHiddenMore(\'expandProp2\',\'moreProp2\',\'品牌\');return false;"><span class="sysicon siDownGray" id="expandProp2">显示全部品牌</span></a>');
			}
			
			$("#propDisp2").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeBrand(\'-1\');return false;">全部品牌</a></li>');
			$.each(json.result,function(i,n){
				$("#propDisp2").append('<li><a href="javascript:void(0);" onclick="show_list.changeBrand(\'' + n[0] + '\');return false;" name="' + n[0] + '">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
			})
			
		});
		
		//刷新推荐商品
		$("#contentSupp").loadPage($('#initPath').val()+'/GoodsShowController.do?method=getRecommendGoods&rp=5&page=1&goodsClassId='+show_list.goodsClassId.substring(0,show_list.goodsClassId.length-1));
	});

	//列表显示
	$('#showGoodsList').click(function(){
		$("#style").val("list");
		show_list.makeSearchData();
	});

	//大图显示
	$('#showGoodsPic').click(function(){
		$("#style").val("pic");
		show_list.makeSearchData();
	});

	//评价、价格、信用排序
	$('.goodsSort').click(function(){
		common.order(this);
		show_list.makeSearchData();
	});
	
	//商品比较
	$("#startGoodsCompare").click(function(){
		var objIds = "";
		$.each($('#goodsCompare ul li'),function(i,n){
			objIds += $(n).attr("id").split("_")[1] + ",";
		})
	
		if(objIds.length == 0) {
			return;
		}else {
			objIds = objIds.substring(0,objIds.length-1);
		}

		$.epsDialog({
		 	id:"compareDialog",
		    title:'商品对比',
		    width:920,
		    height:500,
			url:$('#initPath').val()+'/GoodsShowController.do?method=getCompareGoodsInfo&objIds='+objIds
		}); 
	})
	
});