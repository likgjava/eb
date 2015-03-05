<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		var templateId = $('#templateId').val();
		//加载投票结果列表
		$.getJSON($('#initPath').val()+"/VoteTemplateShowController.do?method=showVoteResult",{"templateId":templateId,"operation":"topNum",'rp':'10','page':'1'},function(json){
			if(json.voteResultTopNumLilst != null){
				$.each(json.voteResultTopNumLilst,function(index,obj){	
					var strHtml = "<div class='tpjg'><dl><dt><img src='AttachmentController.do?method=showImg&objId="+obj.picture+"' height='146' width='187 ' />";
					strHtml += "</dt><dd>"+obj.attenderName+"<br>投票数："+obj.voteCountNum+"<br>评论数："+obj.voteCommentNum+"</dd></dl></div>";
						
					$('#showVoteResultList').append(strHtml);	
				});	
			}
			
		});
	});
</script>

<div id="showVoteResultList">
</div>