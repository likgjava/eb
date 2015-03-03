var SupplierGoodsBargainForm = {};

//挑选商品
function selectGood(ctrlIndex) {
	$.epsDialog({
	        title:'选择商品',
	        url:$('#initPath').val()+'/view/agreement/bargin/project/bargain/goods_select_list.jsp?ctrlIndex='+ctrlIndex,
	        width: 800,
			height: 400    
	});
	return true;
}
//提交报价
SupplierGoodsBargainForm.saveBargain=function(index){
	var bargain={};
	bargain.project={};
	bargain.project.objId=$('#projId').val();//项目
	bargain.requireItem={};
	bargain.requireItem.objId=$('input[id=requireItem'+index+']').val();//需求条目
	bargain.goods={};
	bargain.goods.objId=$('input[id=goodsId'+index+']').val();//商品
	bargain.goodsPrice=$('input[id=goodsPrice'+index+']').val();//报价
	bargain.goodsTotal=$('span[id=goodsTotal'+index+']').text().replace(/,/g,'');//总计
	bargain.serviceContent=$('textarea[id=productName'+index+']').val();//报价说明
	bargain.remark=$('textarea[id=remark'+index+']').val();//备注
	bargain.bargainFile=$('input[id=bargainFile'+index+']').val();//报价文件
	$.getJSON($('#initPath').val()+'/BiddingRecordDetailController.do?method=saveBargain',{"bagainStr":native2ascii(JSON.stringify(bargain)),"bargainTurnId":$('#bargainTurnId').val()},function(json){
		if(json.failure){alert(json.result);return;}
		window.location.href=$('#initPath').val()+"/BargainProjectController.do?method=toSupplierBargainPage&objId="+$('#projId').val();
	});
}
//重新报价
SupplierGoodsBargainForm.updateBargain=function(index){
	$.getJSON($('#initPath').val()+'/BiddingRecordDetailController.do?method=updateBargain',
			{"bargainDetailId":$('input[id=bargainDetailId'+index+']').val(),
			"turnId":$('#bargainTurnId').val(),
			"newPrice":$('input[id=myGoodsPrice'+index+']').val(),
			"newTotal":$('span[id=myGoodsTotal'+index+']').text().replace(/,/g,'')
		},function(json){
		if(json.failure){alert(json.result);return;}
		window.location.href=$('#initPath').val()+"/BargainProjectController.do?method=toSupplierBargainPage&objId="+$('#projId').val();
	});
}

//获取供应商的报价排名图表
SupplierGoodsBargainForm.loadBiddingRankChart = function(){
	$('div[id^=biddingRankChart_]').each(function(index, domE){
		var requireItemId = $(domE).attr('id').replace('biddingRankChart_','');
		var paramurl = "&projId="+$("#projId").val()+"&bargainTurnId="+$("#bargainTurnId").val()+"&requireItemId="+requireItemId;
		
		var url1 = $("#initPath").val()+"/BiddingRecordDetailController.do?method=getBiddingRankData";
		$.getJSON(url1+paramurl, function(json){
			if(json.failure){alert(json.result);return;}
			if(json.supplierIds==null || json.supplierIds==""){
				$(domE).html("暂无报价信息");
			}else{
				if($(domE).attr("supplierIds")!=json.supplierIds || $(domE).attr("minPrices")!=json.minPrices){
					var url2 = $("#initPath").val()+"/BiddingRecordDetailController.do?method=getBiddingRankChartXml&supplierId="+$("#myOrgInfoId").val();
					var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSLine.swf", "myChartId"+index, "280", "120");
					myChart.setDataURL(escape(url2+paramurl));
					myChart.render("biddingRankChart_"+requireItemId);
					myChart = null;
					
					$(domE).attr("supplierIds",json.supplierIds);
					$(domE).attr("minPrices",json.minPrices);
				}
			}
		});
	});
}

//检查降幅符合本轮降幅才能报价
function checkTotalCut(lastValue, nowValue) {
	var cutMoney = 0;
	var calcType = "01";
	if($('#totalCut').text().indexOf('元') > -1) {
		cutMoney = parseFloat($('#totalCut').text().replace('元',''));
		calcType = "02";
	}else if($('#totalCut').text().indexOf('%') > -1){
		cutMoney = parseFloat($('#totalCut').text().replace('%','')) / 100;
	}
	
	if(lastValue==null ||lastValue == 0 || cutMoney == 0) {//尚未报价，并且降幅为0
		return true;
	}
	if(calcType == "01") {//%计算方式
		if(nowValue > lastValue * (1-cutMoney)) {
			return false;
		}
	}else {
		if(nowValue > (lastValue-cutMoney)) {
			return false;
		}
	}
	return true;
}

$(document).ready(function(){
	//计算总计
	$('input[name=goodsPrice]').blur(function(){
		var index = $(this).attr('id').replace('goodsPrice','');
		if($(this).val()) {
			$('span[id=goodsTotal'+index+']').text(formatAmount(parseFloat($(this).val()) * parseInt($('span[id=qty'+index+']').text()),2));
		}
	})
	//计算总计
	$('input[name=myGoodsPrice]').blur(function(){
		var index = $(this).attr('id').replace('myGoodsPrice','');
		var requireindex = $(this).attr('requireindex');//为了获取需求条目的数量
		if($(this).val()) {
			$('span[id=myGoodsTotal'+index+']').text(formatAmount(parseFloat($(this).val()) * parseInt($('span[id=qty'+requireindex+']').text()),2));
		}
	})
	
	//提交报价
	$('a[id^=submitBargain]').click(function(){
		//被剔除，不能报价
		if($('#isEliminated').val() == 'true'){ alert('您不能参与该轮报价！'); return ; }
		
		var index = $(this).attr('id').replace('submitBargain','');
		//价格校验
		if(!$('input[id=goodsPrice'+index+']').valid()) {
			return;
		}
		if(window.confirm('确定提交报价')) {
			if($('#bargainNumber').val() == 'false') {
				if($('div[id=bargainSupplierListDiv'+index+']').find('table').eq(1).find('tbody').find('tr').length > 0) {
					alert('该项目规则为单次报价,您已报价');
					return;
				}
			}
			var lastValue = $('div[id=bargainSupplierListDiv'+index+']').find('table').eq(1).find('tbody').find('tr:first').find("input[id*=oldPrice]").val();
			var nowValue = $('input[id=goodsPrice'+index+']').val();//报价
			if(!checkTotalCut(lastValue,nowValue)) {
				alert("您的本次报价不符合降幅规则");
				return;
			}
			$(this).attr('disabled','disabled');
			SupplierGoodsBargainForm.saveBargain(index);
		}
	})
	
	//上传报价文件
	$('a[id^=uploadFile]').click(function(){
		var index = $(this).attr('id').replace('uploadFile','');
		//由于有多个form所以每次上传文件之前先把_attachRelaID清除
		$('#_attachRelaID').val('');
		$('#attachments_show_area').text('');
		var strURL = $('#initPath').val()+"/AttachmentController.do?method=toUpload&attachRelaID="+$('input[id=bargainFile'+index+']').val()+"&isSingle=yes";
		$.epsDialog({
			id:"uploadFile",
			title:"上传报价文件",
			url:strURL+"&divId=uploadFile",
			onClose: function(){
				if($('#_attachRelaID').val()) {
					$('input[id=bargainFile'+index+']').val($('#_attachRelaID').val());
				}
				$('input[id=bargainFile'+index+']').next('div').text('已上传文件：'+$('#_count').val()+'个');
			}
		})
	})
	
	//重新报价
	$('a[id^=resetBargain]').click(function(){
		//被剔除，不能报价
		if($('#isEliminated').val() == 'true'){ alert('您不能参与该轮报价！'); return ; }
		
		var index = $(this).attr('id').replace('resetBargain','');
		if($('input[id=myGoodsPrice'+index+']').attr('disabled')) {
			$('input[id=myGoodsPrice'+index+']').removeAttr('disabled')
		} else {
			$('input[id=myGoodsPrice'+index+']').attr('disabled','disabled');
		}
		$(this).addClass('hidden');
		$('a[id=resetSubmitBargain'+index+']').removeClass('hidden');
		$('a[id=resetCancelBargain'+index+']').removeClass('hidden');
	})
	
	//更新报价
	$('a[id^=resetSubmitBargain]').click(function(){
		var index = $(this).attr('id').replace('resetSubmitBargain','');
		//价格校验
		if(!$('input[id=myGoodsPrice'+index+']').valid()) {
			return;
		}
		//只能比上次报价低
		if(parseFloat($('input[id=myGoodsPrice'+index+']').val()) >= parseFloat($('input[id=oldPrice'+index+']').val())) {
			alert("新报价不能比大于等于上次报价,请重新填写");
			return;
		}
		if(window.confirm('确定提交?')) {
			$(this).attr('disabled','disabled');
			SupplierGoodsBargainForm.updateBargain(index);
		}
	})
	
	//取消更新
	$('a[id^=resetCancelBargain]').click(function(){
		var index = $(this).attr('id').replace('resetCancelBargain','');
		if($('input[id=myGoodsPrice'+index+']').attr('disabled')) {
			$('input[id=myGoodsPrice'+index+']').removeAttr('disabled')
		} else {
			$('input[id=myGoodsPrice'+index+']').attr('disabled','disabled');
		}
		$(this).addClass('hidden');
		$('a[id=resetSubmitBargain'+index+']').addClass('hidden');
		$('a[id=resetBargain'+index+']').removeClass('hidden');
	})
	
	//折叠/展开
	$('a[id^=viewOrhide]').click(function(){
		var index = $(this).attr('id').replace('viewOrhide','');
		if($('div[id=bargainSupplierListDiv'+index+']').css('display')=='block') {
			$('div[id=bargainSupplierListDiv'+index+']').hide();
		} else {
			$('div[id=bargainSupplierListDiv'+index+']').show();
		}
	})
	
	//默认第一个展开
	$('a[id=viewOrhide1]').click();
	
	SupplierGoodsBargainForm.loadBiddingRankChart();
	//定时器-请求轮次、供应商报价条目信息等
	var biddingRankTimer=setInterval(function(){
		//获取供应商的报价排名图表
		SupplierGoodsBargainForm.loadBiddingRankChart();
	}, 8000);
});
