<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<div class="conSearch">
	<form id="goodsBrandForm" >
		<input type="hidden" id="Hname" value="${param.Hname}"/>
		<input type="hidden" id="Hid" value="${param.Hid}"/>
		<ul>
			<li>
				<label for="brandName">品牌名称：</label>
				<input type="text" name="brandName" id="brandName" maxlength="50" size="60" />
				<input type="hidden" name="brandName_op" value="like" />
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span>查询</span></button>
			</li>
		</ul>
	</form>
</div>

<table class="frontTableList" id="goodsBrandList">
		<thead>
			<tr>
				<th class="left">品牌编码</th>
				<th class="left omission">品牌名称</th>
				<th class="left omission">所属机构</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
</table>

<div class="conOperation">
	<button id="close" type="button"  class="largeBtn" ><span><spring:message code="globe.close"/></span></button>
	<button id="clear" type="button"  class="largeBtn" ><span>清除</span></button>
</div>

<script>
var goodsBrandList={};

$(document).ready(function(){
	var params = {};
	params = {"useStatus":"01","sellStatus":"01"};
	
	goodsBrandList.oTable = $('#goodsBrandList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'brandCode,brandName,belongsName',
		'hiddenColumns':'useStatus,sellStatus,mainLogo,brandDesc',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			goodsBrandList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).append("<td align='center'><a name='selectOrg' href='javascript:void(0);'>选择</a></td>").find('a[name=selectOrg]').click(function(){
				$("#"+$("#Hname").val()).val(aData.brandName);
				$("#"+$("#Hid").val()).val(aData.objId);

				//加载图片
				var pictureUrl = $("#initPath").val()+"/AttachmentController.do?method=showImg&objId="+aData.mainLogo;
				$("#newPreview").html('<img src="'+pictureUrl+'" width="200px" height="175px">');//替换图片
				$("input[id=picture]").val(aData.mainLogo);//回填id 

				$("#thingComment").val(aData.brandDesc);
				
				$('.epsDialogClose').trigger('click');
			});
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsBrandController.do?method=list",
		"params":params,
		'searchZone':'goodsBrandForm'
	});
	
	//查询
	$("#query").click(function(){
		goodsBrandList.oTable.fnDraw();
	})
	
	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
	
	//清空
	$("#clear").click(function(){
		$("#"+$("#Hname").val()).val(null);
		$("#"+$("#Hid").val()).val(null);

		$("#newPreview").html('<img src="'+$("#initPath").val()+'/view/resource/skin/goods/img/brand_add.gif" width="200px" height="175px">');//替换图片
		$("input[id=picture]").val(null);//回填id

		$("#thingComment").val(null);
		
		$('.epsDialogClose').trigger('click');
	})
})
</script>
