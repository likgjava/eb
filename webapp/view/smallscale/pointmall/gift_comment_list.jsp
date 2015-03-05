<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src='<c:url value="/view/resource/plug-in/jquery/ajaxpager/jquery.ajaxpager.js"/>'></script>

<!-- 评价信息tab页 -->
<div class="orderManagement" id="giftCommentListDiv">
	<table class="condiscusstable" id="giftCommentTable" width="100%">
	</table>
</div>
<div id="giftCommentListPager" class="fronAjaxPager"></div>     

<script>
$(document).ready(function(){
	//加载评价列表div分页
	$('#giftCommentListPager').ajaxPager({   
			url:$('#initPath').val()+'/ShowEvaluateController.do?method=toGiftCommentList&gift.objId='+$("#giftId").val(),
			rp:15,
			data:{"queryColumns":"title,level,comment,rateName,createTime"},
			startPage: 1, 
			fnCallbackDraw: function(json,pageIndex){
				$("#giftCommentTable").empty()
				if(json.rows.length>0){
					$.each(json.rows,function(index,obj){
						var levelHtml = '';
						for(var i= 0;i<obj.level;i++){
							levelHtml += '<img src="view/resource/skin/skin07/img/az-detail_star-fen.gif">';
						}
						$("#giftCommentTable").append(
						        '<tr><th><em class="tittlediscussem">'+obj.title+'</em> '+ obj.createTime+' </th></tr>'+
						        '<tr><td> <em>'+obj["rateName"].substring(0,2)+'****</em> 为此礼品评分：'+levelHtml+'</tr>'+
						        '<tr><td>'+obj.comment+'</td></tr>'
						);
					})
				}else{
					$("#giftCommentListDiv").append('<div class="sorry">暂无评价！</div>');
					
				}
			}
	});
});
</script>