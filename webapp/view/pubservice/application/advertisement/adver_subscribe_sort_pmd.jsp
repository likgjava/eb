<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" name="positionId" value="${param.positionId}"/>
<div id="epsTabs" class="epsTabs">
	<div id="adverSubscribeTableDiv">
		<table class="frontTableList" id="adverSubscribeSortDiv">
			<thead>
				<tr>
					<th>广告图片</th>
					<th class="left omission">广告位</th>
					<th class="left omission">投放单位</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
 <div class="conOperation">
<button id="adver_pmd_sort_publish" type="button" onclick="adverSubscribeSortPMD.rePublish()"><span>重新发布</span></button>
</div>
<script type="text/javascript">
var adverSubscribeSortPMD = {};
adverSubscribeSortPMD.oTable;

$(document).ready(function(){
	//广告为Id
	var positionId = $("input[name=positionId]").val();
	
	//填充table列表
	adverSubscribeSortPMD.oTable = $('#adverSubscribeSortDiv').dataTable({
		'singleSelect':false,
		'checkbox':false,
		'searchZone':'adverSubscribeSearchForm',
		'sAjaxSource':$('#initPath').val()+"/AdvertisingSubscribeController.do?method=list",
		 params:{'auditStatus':'02','useStatus':'01','advertisingPosition.objId':positionId,"order":"sort"},
		'queryColumns':'adverFile,advertisingPosition.positionDictionary.dicName,orgName',
		'hiddenColumns':'advertisingPosition.adverType',
		'fnInitComplete':function(oSettings){},
		'fnDrawCallback':function(oSettings){
			adverSubscribeSortPMD.oTable.oSettings = oSettings;

			var totalRecords = oSettings._iRecordsTotal;
			adverSubscribeSortPMD.currentPage = oSettings._iDisplayStart;
			var pageSize = oSettings._iDisplayLength;
			var totalPage = totalRecords % pageSize == 0 ? totalRecords/pageSize : totalRecords/pageSize+1;
			adverSubscribeSortPMD.totalPage = parseInt(totalPage);
		   
			//第一页
			if(adverSubscribeSortPMD.currentPage==1){
				$("#adverSubscribeSortDiv tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
			}
			//最后一页
			if(adverSubscribeSortPMD.currentPage==adverSubscribeSortPMD.totalPage){
				$("#adverSubscribeSortDiv tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
			}
		},
		'fnRowCallback':function(nRow,aData,iDisplayIndex){
			$(nRow).find('td[name=adverFile]').empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData.adverFile+'" width="80" height="80"/>');

			$(nRow).append('<td class="operation"><a name="up" href="javascript:void(0);" onclick="adverSubscribeSortPMD.upClick(this);return false;"><span class="sysicon siUp">&nbsp;</span></a><a name="down" href="javascript:void(0);" onclick="adverSubscribeSortPMD.downClick(this)"><span class="sysicon siDown">&nbsp;</span></a></td>');
			return nRow;
		}
	});
});

//刷新
adverSubscribeSortPMD.rePublish = function(){
	if(confirm("确定重新发布广告吗？")){
		$.getJSON($("#initPath").val()+"/AdvertisingSubscribeController.do?method=publishAdverSubscribe",{"adverSubscribeIds":$("#adverSubscribeSortDiv tbody tr:first").attr("objid")},function(json){
			if(json.result=='true'){alert("发布成功!");$('.epsDialogClose').trigger('click');}
		});
	}
}

//设置上下移动的样式
adverSubscribeSortPMD.drawUpAndDownCss=function(){
	$("#adverSubscribeSortDiv tr a[name=up]>span").removeClass("siUpGray").addClass("siUp");
	$("#adverSubscribeSortDiv tr a[name=down]>span").removeClass("siDownGray").addClass("siDown");
	
    //顶级节点 
	if(adverSubscribeSortPMD.currentPage==1){
		$("#adverSubscribeSortDiv tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
	}
	if(adverSubscribeSortPMD.currentPage==adverSubscribeSortPMD.totalPage){
		$("#adverSubscribeSortDiv tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
	}
}

//向上
adverSubscribeSortPMD.upClick=function(obj){
	if($(obj).find("span").hasClass("siUpGray")) { return false; }
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).prev();
	var id = $(curRow).attr("objid");
	
	//更新到数据库
	$.getJSON($('#initPath').val()+"/AdvertisingSubscribeController.do?method=updateSort",{"objId":id,"isToUp":'true'},function(json){
		if(json.success){
			var firstRow = $("#adverSubscribeSortDiv tr").eq(1);
			var firstObjId = $(firstRow).attr("objid");
			if(firstObjId == id){ //如果移动的是第一行，则重画列表。
				adverSubscribeSortPMD.oTable2.fnDraw();
			}else{
				$(targetRow).before(curRow);	//把当前行放到目标行之前
				adverSubscribeSortPMD.drawUpAndDownCss();	//重画向上向下的样式
			}
		}
	});
};

//向下
adverSubscribeSortPMD.downClick=function(obj){
	if($(obj).find("span").hasClass("siDownGray")) { return false; }
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).next();
	var id = $(curRow).attr("objid");
	
	//更新到数据库
	$.getJSON($('#initPath').val()+"/AdvertisingSubscribeController.do?method=updateSort",{"objId":id,"isToUp":"false"},function(json){
		if(json.success){
			var lastRow = $("#adverSubscribeSortDiv tr:last");
			var lastObjId = $(lastRow).attr("objid");
			if(lastObjId == id){ //如果移动的是最后一行，则重画列表。
				adverSubscribeSortPMD.oTable2.fnDraw();
			}else{
				$(targetRow).after(curRow);	//把当前行放到目标行之后
				adverSubscribeSortPMD.drawUpAndDownCss();	//重画向上向下的样式
			}
		}
	});
};
</script>
