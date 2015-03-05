<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src='<c:url value="/view/resource/plug-in/jquery/ajaxpager/jquery.ajaxpager.js"/>'></script>

<!-- 评价信息tab页 -->
<div class="orderManagement" id="hotelEvaluateListDiv">
	<table class="condiscusstable" id="hotelEvaluateTable" width="100%">
	</table>
</div>
<div id="hotelEvaluateListPager" class="fronAjaxPager"></div>     

<script>
$(document).ready(function(){
	//加载评价列表div分页
	$('#hotelEvaluateListPager').ajaxPager({   
			url:$('#initPath').val()+'/ShowEvaluateController.do?method=toHotelEvaluateList&hotel.objId='+$("#hotelId").val(),
			rp:15,
			data:{"queryColumns":"title,level,remark,rateName,createTime"},
			startPage: 1, 
			fnCallbackDraw: function(json,pageIndex){
				$("#hotelEvaluateTable").empty()
				if(json.rows.length>0){
					$.each(json.rows,function(index,obj){
						var levelHtml = '';
						for(var i= 0;i<obj.level;i++){
							levelHtml += '<img src="view/resource/skin/skin07/img/az-detail_star-fen.gif">';
						}
						$("#hotelEvaluateTable").append(
						        '<tr><th><em class="tittlediscussem">'+obj.title+'</em> '+ obj.createTime+' </th></tr>'+
						        '<tr><td> <em>'+obj["rateName"].substring(0,2)+'****</em> 为此酒店评分：'+levelHtml+'</tr>'+
						        '<tr><td>'+obj.remark+'</td></tr>'
						);
					})
				}else{
					$("#hotelEvaluateListDiv").append('<div class="sorry">暂无评价！</div>');
					
				}
			}
	});
});
</script>