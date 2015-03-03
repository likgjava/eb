<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<form id="BargainHistoryForm" name="BargainHistoryForm" method="post">
    <input type="hidden" name="bargainDetailId" id="bargainDetailId" value="${param.bargainDetailId}"/>
    
    <table class="tableList" id="BiddingHistoryList">
		<tr>
			<th>报价商品/说明</th>
			<th>报价时间</th>
			<th>报价(元)</th>
		</tr>
	</table>

    <div class="conOperation">
		<button id="close" type="button"><span>关闭</span></button>
	</div>
</form>

<script>
var BargainHistoryForm={};

$(document).ready(function(){ 
	//查询历史报价
	$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=getHistoryByDetail',
			{"bargainDetailId":$('#bargainDetailId').val()},function(json){
		if(json.failure){alert(json.result);return;}
		var html = '';
		$.each(json.historyList,function(i,n){	
			html += '<tr>';
			if(n[0].length > 8) {
				html += '<td title='+n[0]+'>'+n[0].substring(0,12)+'...</td>';
			} else {
				html += '<td title='+n[0]+'>'+n[0]+'</td>';
			}
			html += '<td>'+n[1]+'</td>';
			html += '<td>'+formatAmount(n[2],2)+'</td>';
			html += '</tr>';
		})
		$('#BiddingHistoryList').append(html);
	});
	
	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
});
</script>