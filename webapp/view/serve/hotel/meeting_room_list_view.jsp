<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
var meetingRoomList = {};
$(document).ready(function(){
	//加载会议室列表
	if(null==meetingRoomList.oTable){
		meetingRoomList.oTable = $('#meetingRoomList').dataTable({
			'queryColumns' : 'picture,marketPrice,unit,meetingRoomType,containNum',
			'alias' : 'picture,marketPrice,unitCN,meetingRoomTypeCN,containNum',
			'hiddenColumns': '',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=containNum]").attr("align","right");
			$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" CURSOR="hand" width="60px" height="60px"/>');
				$(nRow).append('<td class="operation"><a name="view" title="查看" href="javascript:void(0);"><span>查看</span></a></td>').find('a[name=view]').click(function(){
					$.epsDialog({
						title:"会议室详情",
						url:$('#initPath').val()+'/MeetingRoomController.do?method=toShowView&objId='+aData.objId,
						width: '600',
						height:'450'
					});
				});
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/MeetingRoomController.do?method=list&hotel.objId="+$('#hotelId').val(),
			"params":{},
			'searchZone':'MeetingRoomSearchForm'
		});
	}else{
		meetingRoomList.oTable.fnDraw();
	}
})

</script>
<table class="frontTableList" id="meetingRoomList">
	<thead>
		<tr>
			<th class="center">图片</th>
			<th class="money">市场价(元)</th>
			<th class="center">价格单位</th>
			<th class="center">会议类型</th>
			<th class="number">坐席人数</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
