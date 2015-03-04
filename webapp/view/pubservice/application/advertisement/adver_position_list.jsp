<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="adverPositionSearchForm">
		<ul>
			<li>
				<label>位置：</label>
				<input type="text" id="positionDictionary.dicName" name="positionDictionary.dicName" value=""/>
				<input type="hidden" id="positionDictionary.dicName_op" name="positionDictionary.dicName_op" value="like"/>
			</li>
			<li>
				<label>文件类型：</label>
				<select name="adverType" id="adverType">
					<option value=''>所有</option>
					<option value='00'>图片文件</option>
					<option value='01'>flash文件</option>
					<option value='02'>跑马灯</option>
				</select>
				<input type="hidden" name="adverType_op" id="adverType_op" value="="/>
			</li>
			<li class="operationBtnDiv">
		        <button id = "adverPositionSearchButton" type="button"><span>查询</span></button>
		    </li>
		</ul>
	</form>
</div>

<div class="operationBtnDiv l">
	<button id="addAdverPosition"><span>新增广告位</span></button>
	<button id="delAdverPosition"><span>删除广告位</span></button>
</div>
	
<!--列表 -->
<div id="adverPositionTableDiv">
	<table class="frontTableList" id="adverPositionTableList">
		<thead>
			<tr>
				<th class="left omission">位置</th>
				<th>文件类型</th>
				<th class="money">资费(元/月)</th>
				<th>高度(px)</th>
				<th>宽度(px)</th>
				<th>容量(KB)</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
<script type="text/javascript">
var adverPositionList = {};
adverPositionList.oTable;

$(document).ready(function(){
	//填充table列表
	adverPositionList.oTable = $('#adverPositionTableList').dataTable({
		'iDisplayLength':'20',
		'singleSelect':false,
		'checkbox':true,
		'searchZone':'adverPositionSearchForm',
		'sAjaxSource':$('#initPath').val()+"/AdvertisingPositionController.do?method=list",
		 params:{},
		'queryColumns':'positionDictionary.dicName,adverType,adverOutlay,adverLength,adverWidth,adverFileMaxValue',
		'hiddenColumns':'',
		'alias':'positionDictionary.dicName,adverTypeCN,adverOutlay,adverLength,adverWidth,adverFileMaxValue',
		'fnInitComplete':function(oSettings){},
		'fnDrawCallback':function(oSettings){
			adverPositionList.oTable.oSettings = oSettings;
		},
		'fnRowCallback':function(nRow,aData,iDisplayIndex){
			//修改
			$(nRow).append('<td><a href="javascript:void(0);" name="modify"><span>修改</span></a></td>');
			$(nRow).find("a[name=modify]").click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/AdvertisingPositionController.do?method=toAddOrModifiy&objId="+aData.objId);
			});

			//查看
			$(nRow).find("td:last").append('<a href="javascript:void(0);" name="detail"><span>查看</span></a>');
			$(nRow).find("a[name=detail]").click(function(){
				$.epsDialog({
					title:"查看广告位信息",
					url:$("#initPath").val()+"/AdvertisingPositionController.do?method=toAdverPositionDetail&objId="+aData.objId,
					onClose:function(){
						adverPositionList.oTable.fnDraw();
					}
				})
			});
			return nRow;
		}
	});
});

//广告位新增
$('#addAdverPosition').click(function(){
	$('#conBody').loadPage($('#initPath').val()+"/AdvertisingPositionController.do?method=toAddOrModifiy");
});

//广告位删除
$('#delAdverPosition').click(function(){
	var adverPositonIds = adverPositionList.oTable.dtSelects();
	if(adverPositonIds.length<=0){alert("至少选择一条记录");return;}
	if(confirm("确认删除广告位码？")){
		$.getJSON($('#initPath').val()+"/AdvertisingPositionController.do?method=removeAdverPositon",{"adverPositionIds":adverPositonIds},function(json){
			if(json.result=='true'){alert("操作成功！");adverPositionList.oTable.fnDraw();}
			if(json.result=='false'){alert("有广告位还拥有广告信息，不能删除！");}
			if(json.failure){alert("操作没有成功,请重新操作");return;}
			});
	}
});

//查询过滤
$('#adverPositionSearchButton').click(function(){
	adverPositionList.oTable.fnDraw();
});
</script>
