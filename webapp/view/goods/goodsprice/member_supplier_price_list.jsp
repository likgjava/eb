<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" name="goodsPriceSupplierId" id="goodsPriceSupplierId" value="${param.goodsPriceSupplierId}"/>

<div class="formTips attention">
	<ul>
		<li>
			<em>提示：</em>在下列文本框中输入会员价后,点击"保存会员价"按钮,将会员价保存到行情中,也可点击"删除会员价"进行删除。
		</li>
	</ul>
</div>

<table class="frontTableList" id="supplierGoodsPriceList">
		<thead>
			<tr>
				<th class="left">所属区域</th>
				<th class="money">市场价</th>
				<th class="money">折扣率</th>
				<th class="money">协议价</th>
				<th class="center">生效日期</th>
				<th class="center">失效日期</th>
				<th class="operation">录入会员价</th>
			</tr>
		</thead>
		<tbody id="supplierGoodsPriceListTbody">
		</tbody>
</table>

<script>
var MemberSupplierGoodsPriceList = {};
MemberSupplierGoodsPriceList.getOperatorStr=function(objId,memberPrice){
	var operatorHtml = "";
	operatorHtml += '<td align="center">';
	operatorHtml += '<span id="'+objId+'" >';
	
	if(memberPrice) {
		operatorHtml += '<input type="text" class="money" maxlength="10" style="width: 60px;" value="'+memberPrice+'" disabled="disabled"/>';
		operatorHtml += '</span>';
		operatorHtml += '<a  title="删除会员价" id="'+objId+'" href="javascript:void(0);" onclick="MemberSupplierGoodsPriceList.removeMemberPrice(\''+objId+'\');return false;">删除会员价</a>';
	} else {
		operatorHtml += '<input type="text" class="money" maxlength="10" style="width: 60px;"/>';
		operatorHtml += '</span>';
		operatorHtml += '<a  title="保存会员价" id="'+objId+'" href="javascript:void(0);" onclick="MemberSupplierGoodsPriceList.saveMemberPrice(\''+objId+'\');return false;">保存会员价</a>';
	}
	
	operatorHtml += '</td>';
	return operatorHtml;
}
//保存
MemberSupplierGoodsPriceList.saveMemberPrice=function(pid){
	var mprice = $('span[id='+pid+']').find('input').val();
	if(!mprice) {
		alert("请填写会员价格!");
		return;
	} else {
		if(!/^-?\d+(\.\d{0,2})?$/.test(mprice)) {
			alert('请输入正确的价格!');
			return;
		}
	}
	//保存会员价
	$.getJSON($("#initPath").val()+"/GoodsPriceController.do?method=saveMemberPrice",{"goodsPriceId":pid,"memberPrice":mprice},function(json){
		if(json.success){
			//MemberSupplierGoodsPriceList.oTable.fnDraw();
		}else{
			alert("操作失败！");
		}
	})
}

//删除会员价-即更新会员价格为空
MemberSupplierGoodsPriceList.removeMemberPrice=function(pid){
	var mprice = 0;
	$.getJSON($("#initPath").val()+"/GoodsPriceController.do?method=saveMemberPrice",{"goodsPriceId":pid,"memberPrice":mprice},function(json){
		if(json.success){
			MemberSupplierGoodsPriceList.oTable.fnDraw();
		}else{
			alert("操作失败！");
		}
	})
}

$(document).ready(function(){
	//加载供应商报价列表
	MemberSupplierGoodsPriceList.oTable = $('#supplierGoodsPriceList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'district.name,unitPrice,dscuRate,prtcPrice,efctDate,endDate',
		'hiddenColumns':'memberPrice',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			MemberSupplierGoodsPriceList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮
			$(nRow).append(MemberSupplierGoodsPriceList.getOperatorStr(aData.objId,aData.memberPrice))
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsPriceController.do?method=list",
		"params":{"goodsPriceSupplier.objId":$('#goodsPriceSupplierId').val()}
	});
})
</script>
