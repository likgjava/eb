
var project_detail_view = {};

var show_list = {}
//显示商品详情
show_list.showDetail = function(goodsId,tabId,classcode) {
	var params = "";
	if(tabId) {
		params += "&tabId=" + tabId;
	}
	common.geToGoodsDetail( goodsId );
}

//计算
project_detail_view.caculator  = function(divId){
	var tr = null;
	if(divId){
		tr = $("#"+divId).find("tr");
	}else{
		tr = $("#minPriceRecordTable").find("tr");
	}
	$.each( tr ,function(index , obj){

		var tdObj =  $(obj).find("span[name=cellPrice]");
		var cellTotal = 0;

		var saveTotal = 0;
		
		$.each(tdObj ,function(i , o){
			var cellPrice = Number( $(o).html().replace(/,/g,'') );
			cellTotal += cellPrice;

			//算节省的钱
			var requirePrice = Number( $("span[id=require"+ $(o).attr("id").split(",")[1] +"]").attr("price") ) ;
			
			saveTotal += ( requirePrice-cellPrice );//节省总价
			$(o).parent().parent().find("span[name=saveCell]").html( formatAmount( requirePrice-cellPrice ,2) );
		})
		
		$(obj).find("span[name=cellTotal]").html( formatAmount(cellTotal,2) );
		$(obj).find("span[name=saveTotal]").html( formatAmount(saveTotal,2) );
	})
}

//供应商详情
project_detail_view.goSupplierPage = function(supplierId){
	$.epsDialog({
		id:"showSupplierDetail",
		title:"供应商详情",
		url:$("#initPath").val()+"/ExOrgInfoController.do?method=getExAllBaseInfo&orgId="+supplierId
	})
}

//双击-供应商对需求的所有报价
project_detail_view.showRecordDetail = function( supplierId , requireId  ){
	var title = "["+$("a[name=supplier"+supplierId+"]").html()+"]对["+$("span[name=require"+requireId+"]").parent().attr("title")+"]的所有报价";
	var href = "["+$("a[name=supplier"+supplierId+"]").html()+"]对["+$("span[name=require"+requireId+"]").html()+"]的所有报价";
	$.epsDialog({
		title:"<span title='"+title+"'>"+href+"</span>",
		width:'900',
		url:$("#initPath").val()+"/BiddingRecordDetailController.do?method=toRecordDetailBySupplierAndRequire&supplierId="+supplierId+"&requireId="+requireId,
		onOpen: function(){$(".epsDialogMax").click();}
	})
}

//显示报名记录
project_detail_view.showSignUpRecord = function(supplierId){
	$.epsDialog({
		title:"供应商报名信息",
		url:$("#initPath").val()+"/SupplierSignupController.do?method=toSupplierSignUpDetail&objId="+supplierId+"&projectId="+$("#projectId").val()
	})
}

//显示报名的附件
project_detail_view.showSignUpRecordFile = function(supplierId){
	$.getJSON($("#initPath").val()+"/SupplierSignupController.do?method=getSupplierSignUp",{"objId":supplierId,"projectId":$("#projectId").val() },function(json){
		if(json.success){
			$.epsDialog({
				title:"附件下载",
				url:$("#initPath").val()+"/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId="+json.signUprecord.attachRelaId,
				width: 600,
				height: 300
			});
		}
	})
}

//标记最低价
project_detail_view.tagLowPrice = function(){
	//先去掉
	$("span[name=cellPrice]").parent().removeClass("base_txtred");
	$("span[name=cellTotal]").parent().removeClass("base_txtred");
	
	$.each( $("#minPriceRecordTable").find("tr:eq(0)").find("td") , function(index,obj){
		var cellPrice = $(obj).find("span[name=cellPrice]");
		var cellTotal = $(obj).find("span[name=cellTotal]");
		if( cellPrice.html()!=null ){
			var tempPrice = Number.MAX_VALUE;
			var supplier = cellPrice.attr("id").split(",")[0];
			$.each($("span[name=cellPrice][id$="+cellPrice.attr("id").split(",")[1]+"]"),function(ind,o){
				 if( Number( $(o).html().replace(/,/g,'') ) < tempPrice && Number( $(o).html().replace(/,/g,'') )>0 ){
					 tempPrice =  Number( $(o).html().replace(/,/g,'') );
					 supplier = $(o).attr("id").split(",")[0];
				 }
			} )
			if(tempPrice == 0){return;}
			$("span[id="+supplier+","+cellPrice.attr("id").split(",")[1]+"]").parent().addClass("base_txtred");
		} else if( cellTotal.html()!=null ){
			var supplier = cellTotal.attr("id");
			var tempPrice = Number.MAX_VALUE;
			$.each($("span[name=cellTotal]"),function(ind,o){
				 if( Number( $(o).html().replace(/,/g,'') ) < tempPrice && Number( $(o).html().replace(/,/g,'') )>0 ){
					 tempPrice =  Number( $(o).html().replace(/,/g,'') );
					 supplier = $(o).attr("id");
				 }
			} )
			if(tempPrice == 0){return;}
			$("span[id="+supplier+"]").parent().addClass("base_txtred");
		}
	})
}

//切换
project_detail_view.exchange = function(){
	if( $("#extInfoDiv").attr("style")!=null && $("#extInfoDiv").attr("style").indexOf("block")>=0 ){
		$("#extInfoDiv").hide();
	}else{
		$("#extInfoDiv").show();
	}
}

//按需求的比较页面
project_detail_view.openRecordDetail = function(requireId){
	  $.epsDialog({
		title:'报价详情',
		width:'900',
		url:$("#initPath").val()+"/BiddingRecordDetailController.do?method=loadRecordByRequire&requireId="+requireId,
		onOpen: function(){$(".epsDialogMax").click();}
	})
}

/**************************************************************项目操作开始************************************/
//修改时间
project_detail_view.modifyTime = function(projectId){
	$.epsDialog({
		title:"修改项目时间",
		url:$('#initPath').val() + "/BargainProjectController.do?method=toDelayProjectTime&objId="+projectId
		,width:900,
		onClose:function(){
			location.reload();//此处简单处理刷新一下页面
		}
	})
}

//修改项目
project_detail_view.update = function(){
	$('#conBody').loadPage($('#initPath').val()+"/BargainProjectController.do?method=toUpdateBargainProject&objId="+$("#projectId").val()+"&type=s");
}
//进入竞价厅
project_detail_view.intoHall = function(){
	var url = $('#initPath').val()+"/BargainProjectController.do?method=toBuyerBargainPage&objId="+$("#projectId").val();
	window.open(url);
}
//进入议价厅
project_detail_view.intoTalkHall = function(){
	url = $('#initPath').val()+"/TalkProjectController.do?method=toBuyerTalkHall&inType=buyer&objId="+objId;
	loadPage_openModelWindow(url,"960");
}

//确认结果
project_detail_view.confirm = function(){
	if($("#operationDiv").html()==null||$("#operationDiv").html()==''){		
		$("#minPriceRecordTable").ScrollTo(800);
		$("#operationDiv").loadPage("view/agreement/bargin/buyresult/confrim_result_view.jsp" ,function(){
			$("#operationDiv").show("slow");
		});
	}
}

//发送邀请函
project_detail_view.sendInvitation = function(){
	$.epsDialog({
		id:"invitationDiv",
		title:"发送邀请函",
		url:$("#initPath").val()+"/InvitationController.do?method=toSendInvitationForm&projectId="+$("#projectId").val() 
	})
}

//生成订单
project_detail_view.order = function(){
	//规则中设置了发布结果公告与下订单的时间间隔
	var timeBeforeOrder = $('#timeBeforeOrder').val();
	if(timeBeforeOrder!=null && timeBeforeOrder!='' && Number(timeBeforeOrder)>0){
		//判断当前时间是否可以下订单(检查项目规则中设置的发布结果公告与下订单的时间间隔)
		$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=checkWhetherCanOrder',{'objId':$("#projectId").val()},function(json){
			if(json.failure){alert(json.result);return;}
			if(json.canToOrder == 'true'){
				var chckOrder = $("input[name=chckOrder]:checked");
				if(chckOrder.html() == null ){alert("请选择一个中标供应商");return ;}
				$.epsDialog({
					id:"resultCreateOrderDiv",
					title:"生成订单",
					url:$("#initPath").val()+"/BuyResultXyghController.do?method=toChooseGoodsToOrderView&appType="+$("#appType").val()+"&supplierId="+chckOrder.val()+"&projectId="+$("#projectId").val()+"&singlePrice=1"
				})
			}else{
				alert('对不起，您在'+json.canOrderTime+'之前还不能生成订单。');
			}
		});
	}else{
		var chckOrder = $("input[name=chckOrder]:checked");
		if(chckOrder.html() == null ){alert("请选择一个中标供应商");return ;}
		$.epsDialog({
			id:"resultCreateOrderDiv",
			title:"生成订单",
			url:$("#initPath").val()+"/BuyResultXyghController.do?method=toChooseGoodsToOrderView&appType="+$("#appType").val()+"&supplierId="+chckOrder.val()+"&projectId="+$("#projectId").val()+"&singlePrice=1"
		})
	}
}

//评价
project_detail_view.evaluate = function(projectId,projectName){
	$.epsDialog({
		id:"evaluateDailog", 
		title:"对参与项目的机构评价",
		url:$("#initPath").val()+"/view/agreement/bargin/project/project_evaluate_div.jsp?userType=buyer&projectId="+projectId+"&projectName="+native2ascii(projectName)
	});
}

//投诉举报
project_detail_view.report = function(projectId,type,ebuyMethod){
	$.epsDialog({
		id:"complainDiv", 
		title:"选择参与项目的机构",
		url:$("#initPath").val()+"/view/agreement/bargin/project/project_complain_div.jsp?userType=buyer&projectId="+projectId+"&type="+type+"&ebuyMethod="+ebuyMethod
	});
}

//确定剔除供应商
project_detail_view.confirmEliminate = function(){
	
	//定位
	$("#minPriceRecordTable").ScrollTo(800);
	
	//显示
	$("input[name*=eliminateSupplier]").show("slow");
	
	$("#operationDiv").loadPage($("#initPath").val()+"/view/agreement/bargin/project/bargain/confrim_eliminate_view.jsp");
	
	$("#operationDiv").show("fast");
}

//取消剔除供应商
project_detail_view.cancleEliminate = function(eliminateSupplierId){
	if(window.confirm('确定取消剔除吗?')) {
		$.getJSON($('#initPath').val()+'/BiddingEliminateSupplierController.do?method=remove',{'objId':eliminateSupplierId},function(json){
			if(json.failure){alert(json.result);return;}
			alert("取消剔除成功");
			window.location.reload();
		});
	}
}

//终止项目
project_detail_view.stopProject = function(){
	$.epsDialog({
		title: "终止项目",
		url: $("#initPath").val()+"/BargainProjectController.do?method=toStopProjectView&objId="+$("#projectId").val()
	});
}

/**************************************************************项目操作结束************************************/

$(document).ready(function(){
	//一次计算
	project_detail_view.caculator();

	//标记最低
	project_detail_view.tagLowPrice();

	$(".price_td").hover(function(){$(this).addClass("price_td_hilight");},function(){$(this).removeClass("price_td_hilight");})
})