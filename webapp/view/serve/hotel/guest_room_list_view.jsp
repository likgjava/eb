<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<table class="frontTableList" id="guestRoomList">
	<thead>
		<tr>
			<th class="center">图片</th>
			<th class="center">房型</th>
			<th class="money">门市价(元)</th>
			<th class="center">早餐</th>
			<th class="center">床型</th>
			<th class="center">宽带</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<script>
var GuestRoomList = {};
$(document).ready(function(){
	//加载客房列表
	if(null==GuestRoomList.oTable){
		GuestRoomList.oTable = $('#guestRoomList').dataTable({
			'queryColumns' : 'picture,guestRoomType,retailPrice,breakfastType,bedType,broadband',
			'alias' : 'picture,guestRoomTypeCN,retailPrice,breakfastTypeCN,bedTypeCN,broadband',
			'hiddenColumns': '',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" CURSOR="hand" width="60px" height="60px"/>');
				$(nRow).append('<td class="operation"><a name="view" title="查看" href="javascript:void(0);"><span>查看</span></a></td>').find('a[name=view]').click(function(){
					$.epsDialog({
						title:"客房详情",
						url:$('#initPath').val()+'/GuestRoomController.do?method=toGuestRoomDetailView&guestRoomId='+aData.objId,
						width: '700',
						height:'450'
					});
				});
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GuestRoomController.do?method=list&hotel.objId="+$('#hotelId').val(),
			"params":{}
		});
	}else{
		GuestRoomList.oTable.fnDraw();
	}
})
</script>
