<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
<!--
.layoutfix {display: inline-block;overflow: hidden;}
.itinerary_content{border-bottom:1px solid #D8D8D8;}
.line_content td {color: #333333;padding: 3px 2px;vertical-align: top;}
.line_content th {color: #333333;padding: 3px 2px;vertical-align: top;}
.hi_60{height:60px;}
.hi_auto{height:auto;}
-->
</style>
<!-- 代理人可操作 -->
<c:if test="${currentOrgId==requireItem.project.buyersId || confirmResult }">

<div class="formTips attention"><em>注意：</em>1、您可以选择合适的报价作为标记。<br />
		<span style="padding-left:40px;">2、同一供应商只能选择一条报价。</span><br />
		<span style="padding-left:40px;">3、点击排序按钮，进行排序，排序后如想保存排序状态，请点击“保存排序”</span>
</div>

</c:if>


<!-- 需求id -->
<input id="requireId" name="requireId" type="hidden" value="${param.requireId}"/>
	<div class="conrank">
      <ul>
	    <li class="shortBy" style="left:20px;"><em>排序：</em>
			<span id="supplierSort" name="suplier_id" onclick="project_detail_require.clickSort(this);" class="goodsSort sysicon"><a href="javascript:void(0);">供应商</a></span>
			<span id="priceSort" name="goods_total" onclick="project_detail_require.clickSort(this);" class="goodsSort sysicon"><a href="javascript:void(0);">价格</a></span>
		</li>
		<li style="float: right;">
			<span class="conOperation">
			
			
				<!-- 代理人可操作 -->
				<c:if test="${currentOrgId==requireItem.project.buyersId || confirmResult }">
			
				<button id="orderButton" disabled="disabled" onclick="project_detail_require.confirmOrder();"><span>保存排序</span></button>
				<button onclick="project_detail_require.confirmChoose();"><span>确定</span></button>
				
				</c:if>
				
				
				<button onclick="$('.epsDialogClose').trigger('click');"><span>关闭</span></button>
				</span>
		</li>
      </ul>
	</div>

	<div class="itinerary_content layoutfix hi_60 showMoreInfo" id="package_detail_line0-page1_day2"  style="width:100%; background:none repeat scroll 0 0 #DDDDDD;" ondblclick="project_detail_require.showMoreInfo(this)">
		<table class="line_content" style="width:100%;">
			<tr>
				<th style="width: 250px;text-align:center;"><b>供应商</b></th>
				<!-- 需求名称 -->
				<c:set var="requrieDesc" value="${requireItem.goods!=null ? requireItem.goods.productName : requireItem.descr}"/>
				<th title="${requrieDesc}">
					<b>需求描述:</b>
					<i><strong>双击查看完整描述</strong></i><br/>
					
					<c:if test="${requireItem.goods!=null}"><a onclick="common.geToGoodsDetail('${requireItem.goods.objId}');" href="javascript:void(0);">${requrieDesc}</a></c:if>
					<c:if test="${requireItem.goods==null}">${requrieDesc}</c:if>
					</th>
				<th style="width: 250px;padding-left:50px;"><b>需求预算:</b> <br />￥<fmt:formatNumber value="${requireItem.goodsPrice*requireItem.goodsQty}" pattern="#,##0.00#" />（元）</th>
			</tr>
		</table>
	</div>
	
	<c:set var ="supplierId"  value=""/>
	
	<div id="dataDiv">
	
	<%@ include file="/view/agreement/bargin/project/project_record_by_require_div.jsp"%>
	
	</div>
	
<script type="text/javascript">

var project_detail_require = {};

//排序
project_detail_require.clickSort = function(e){
	$(".shortBy span").not($(e)).removeClass("siUp").removeClass("siDown");
	
	common.order(e);
	$("#orderButton").attr("disabled",false);
	project_detail_require.reload();
}

//排序重载
project_detail_require.reload = function(){
	//排序
	var sort = ""; 
	$.each($("span.goodsSort"),function(i,n){
		//获得升序属性
		if($(n).hasClass("siUp")) {
			sort += $(n).attr("name") + "__asc,"; 
		}
		else if($(n).hasClass("siDown")) {
			sort += $(n).attr("name") + "__desc,"; 
		}
	})

	$("#dataDiv").loadPage($("#initPath").val()+"/BiddingRecordDetailController.do?method=loadRecordByRequireForLoad&requireId="+$("#requireId").val()+"&order="+ sort );
}

//标记最低价
project_detail_require.tagLowPrice = function(){

	var tempPrice = Number( $("span[name=detailPrice]:eq(0)").html().replace("￥",'').replace(/,/g,'') ) ;

	var detailId = $("span[name=detailPrice]:eq(0)").attr("id") ;
	
	$.each( $("span[name=detailPrice]") ,function( index , obj ){
		if( tempPrice > Number( $(obj).html().replace("￥",'').replace(/,/g,'') ) ){
			tempPrice = Number( $(obj).html().replace("￥",'').replace(/,/g,'') );
			detailId = $(obj).attr("id");
		}
	})
	$("span[id="+detailId+"]").addClass("base_txtred");
}

//check事件
project_detail_require.checkDetail = function(detailId,supplierId){
	//目标是选中
	if( $("input[id="+detailId+"]:checked") ){
		var checkedObj = $("input[name="+supplierId+"]:checked");
		$("input[name="+supplierId+"]").not("input[id="+detailId+"]").attr("checked",false);
	}
}

//添加附件事件
project_detail_require.downLoadFile = function(id){
	$.epsDialog({
		title:"附件下载",
		url:$("#initPath").val()+"/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId="+id,
		width: 600,
		height: 300
	});
}

//表格高亮显示
project_detail_require.hilightTd = function(td) {
	$(".pdetail").css("background-color","#ffffff");
	$(td).css("background-color","#f7ffc4");
}

//双击显示更多信息 
project_detail_require.showMoreInfo = function(div) {
	if($(div).hasClass("hi_60"))
		$(div).removeClass("hi_60").addClass("hi_auto");
	else if($(div).hasClass("hi_auto"))
		$(div).removeClass("hi_auto").addClass("hi_60");
}

//上移
project_detail_require.moveUp = function(divId){
	$("#orderButton").attr("disabled",false);
	var ids = [];
	$.each($("div[id*=div]") ,function(index,obj){
		ids.push( $(obj).attr("id") );
	})
	var sourceObj = $("div[id=div"+divId+"]");
	var sh2 = sourceObj.next("h2");
	
	var targetObj = $("div[id=div"+divId+"]").prev("div");

	project_detail_require.hilightTd(sourceObj);
	
	if(ids.toString().indexOf( "div"+divId ) <= 0){
	 	alert("已经是最前");
	 	return;
	}
	//目标是第一位
	if( 0 >= ids.toString().indexOf( targetObj.attr("id") ) ){
		targetObj.find("a[name=moveUp]").find("span").removeClass("siUpGray").addClass("siUp");
		sourceObj.find("a[name=moveUp]").find("span").removeClass("siUp").addClass("siUpGray");
	}
	//源是第最后位
	if( 60 > ids.toString().length - ids.toString().indexOf( sourceObj.attr("id") ) ){
		sourceObj.find("a[name=moveDown]").find("span").removeClass("siDownGray").addClass("siDown");
		targetObj.find("a[name=moveDown]").find("span").removeClass("siDown").addClass("siDownGray");
	}
	targetObj.before( sourceObj );
}

//下移
project_detail_require.moveDown = function(divId){
	$("#orderButton").attr("disabled",false);
	var ids = [];
	$.each($("div[id*=div]") ,function(index,obj){
		ids.push( $(obj).attr("id") );
	})
	var sourceObj = $("div[id=div"+divId+"]");
	var targetObj = $("div[id=div"+divId+"]").next("div");

	project_detail_require.hilightTd(sourceObj);
	
	if(ids.toString().length - ids.toString().indexOf("div"+divId) < 60){
	 	alert("已经是最后");
	 	return;
	}
	//目标是第最后位
	if( 60 > ids.toString().length - ids.toString().indexOf( targetObj.attr("id") ) ){
		targetObj.find("a[name=moveDown]").find("span").removeClass("siDownGray").addClass("siDown");
		sourceObj.find("a[name=moveDown]").find("span").removeClass("siDown").addClass("siDownGray");
	}
	//源是第一位
	if( 0 >= ids.toString().indexOf( sourceObj.attr("id") ) ){
		sourceObj.find("a[name=moveUp]").find("span").removeClass("siUpGray").addClass("siUp");
		targetObj.find("a[name=moveUp]").find("span").removeClass("siUp").addClass("siUpGray");
	}
	targetObj.after( sourceObj );
}

//确认排序
project_detail_require.confirmOrder = function(){
	var orderArray = [];
	$.each($("#dataDiv").find("div[id*=div]"), function(index,obj){
		$(obj).attr("id").replace("div","")
		orderArray.push($(obj).attr("id").replace("div","")+"__"+index  );
	})
	$.getJSON($("#initPath").val()+"/BiddingRecordDetailController.do?method=saveSort",{"orderArray":orderArray},function(json){
		if(json.success){
			$("#orderButton").attr("disabled",true);
			alert('保存成功！');
		}
	})
}

//确认更改
project_detail_require.confirmChoose = function(){
	var changedIdandValue = [];
	var supplierIdandValue = [];
	//取所有的check的id
	$.each( $("input[type=checkbox]") , function( index , obj ){
		if( ($(obj).attr("isChoose")==1) != $(obj).attr("checked")){
			changedIdandValue.push( $(obj).attr("id")+":"+ ($(obj).attr("checked")?"1":"0") +":"+ $(obj).attr("name")+":"+ $(obj).attr("price") ) 
		}
	})
	//确认请求
	$.getJSON($("#initPath").val()+"/BiddingRecordDetailController.do?method=updateRecordChooseStatus",{ "changedIdandValue":changedIdandValue.toString(),"requireId":$("#requireId").val() },function(json){
		if(json.success){
			//定位回显 (逐行)
			$.each(json.resultList , function( index , obj ){
				var spanObj =  $("span[name=cellPrice][id="+obj[1]+","+obj[2]+"]");
				spanObj.html( formatAmount(obj[4],2) );
				obj[3]=='1'?spanObj.parent().parent().addClass("flag"):spanObj.parent().parent().removeClass("flag");
			})
			
			//重新计算
			project_detail_view.caculator();

			//重新标记最低
			project_detail_view.tagLowPrice();
			$('.epsDialogClose').trigger('click');
		}
	})
}

$(document).ready(function(){
	project_detail_require.tagLowPrice();
	
})
</script>
