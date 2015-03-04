<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var AttentionList={};  //定义文件全局变量 处理方法名重复问题
AttentionList.oTable;	

//报价列表
AttentionList.priceList = function(goodsId,districtId){
	$.epsDialog({
		id:"priceDiv",
		title:"供应商报价",
		url:$("#initPath").val()+"/view/goods/showgoods/goods_price_list_div.jsp?districtId="+districtId+"&goodsId="+goodsId
	})
}

//趋势图
AttentionList.priceChartList = function(goodsId,districtId){
	if(districtId==""||null==districtId){
		districtId = $("input[name=district.objId]").val();
	}
	
	$.epsDialog({
		id:"priceChartDiv",
		title:"报价趋势",
		url:$("#initPath").val()+"/GoodsPriceShowController.do?method=toPriceChartByDistrictView&goodsId="+goodsId+"&districtId="+districtId
	})
}

//改变关注城市
AttentionList.changeAttentionDiv = function(){
	var districtName = $("input[name=district.name]").val();
	$.epsDialog({
		id:"changeAttentionDivCity",
		title:"设置当前关注的城市",
        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=district&className=District&action=listTop&isOpen=1&checkValues='+$("input[id=district.objId]").val(),
        onClose:function(){
            if($("input[name=district.name]").val()!=districtName){
	            $("#districtName").html($("input[name=district.name]").val());
				$.getJSON($("#initPath").val()+"/AttentionPriceController.do?method=changerAttentionCity",{"districtId":$("input[name=district.objId]").val()},function(json){
					if(json.success){
						AttentionList.oTable.fnDraw();
					}
				})
            }
        }
	});
}

$(document).ready(function(){
	
	//加载列表
	AttentionList.oTable=$('#AttentionList').dataTable( {   
		'singleSelect':false,	
		//'checkbox':true,		
		'queryColumns':'goods.productCode,goods.productName,goods.referPrice,avgPrice',
		'hiddenColumns':'goods.objId,district.objId',
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			$(nRow).append('<td class="operation"></td>');
			$(nRow).find('td:last').append('<a href="javascript:void(0);" name="price">报价</a>');
			$(nRow).find('a[name=price]').click(function(){AttentionList.priceList(aData['goods.objId'],aData['district.objId']) });
			
			$(nRow).find('td:last').append('<a href="javascript:void(0);" name="pricechart">行情趋势图</a>');
			$(nRow).find('a[name=pricechart]').click(function(){AttentionList.priceChartList(aData['goods.objId'],aData['district.objId']) });

			$(nRow).find('td:last').append('<a href="javascript:void(0);" name="delete">删除</a>');
			$(nRow).find('a[name=delete]').click(function(){
				$.getJSON($("#initPath").val()+"/AttentionPriceController.do?method=remove",{objId:aData.objId},function(json){
					AttentionList.oTable.fnDraw();
				})
			});
			return nRow;
		},
		"params":{"createUser.objId":$("input[name=currentUserId]").val()},
		"sAjaxSource": $('#initPath').val()+"/AttentionPriceController.do?method=list"
	});
	
});
</script>

<input type="hidden" id="orgId" name="orgId" value="${orgId }">
<input type="hidden" id="currentUserId" name="currentUserId" value="${currentUserId}">

<div class="formTips attention">
	<ul>
		<li>
			<c:if test="${fn:length(cuttentCity)> 0}">
				<em>您当前关注的城市为：<strong id="districtName">${cuttentCity[1] }</strong></em>
				<input name="district.objId" id="district.objId" type="hidden" value="${cuttentCity[0]}">
				<input name="district.name" id="district.name" type="hidden" value="${cuttentCity[1]}">
			</c:if>
			<c:if test="${fn:length(cuttentCity)==0}">
				<em>您当前关注的城市为：<strong id="districtName">北京</strong></em>
				<input name="district.objId" id="district.objId" type="hidden" value="110000">
				<input name="district.name" id="district.name" type="hidden" value="北京">
			</c:if>
			<span id="changeAttention"><a class="sysicon siAdd" href="javascript:void(0);" onclick="AttentionList.changeAttentionDiv();return false;"><strong>修改当前关注的城市</strong></a></span>
		</li>
	</ul>
</div>


<div>

<table class="frontTableList" id="AttentionList">
      <thead>
        <tr>
          <th class="left">规格型号</th>
          <th class="left">商品名称</th>
          <th class="amount">参考价格</th>
          <th class="amount">平均价格</th>
          <th class="operation">操作</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
</table>
</div>