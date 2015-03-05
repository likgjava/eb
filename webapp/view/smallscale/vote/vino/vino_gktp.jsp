<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		var templateId = $('#templateId').val();
		//加载投票结果列表
		$.getJSON($('#initPath').val()+"/VoteTemplateShowController.do?method=showVoteResult",{"templateId":templateId,"operation":"topNum"},function(json){
			//评选组
			var showFirstGroupName = "";
			if(json.voteUnitGroupList != null){
				$.each(json.voteUnitGroupList,function(index,obj){
					var strHtml = '<span><a href="javascript:void(0)" onclick="vino_gktp.voteUnitGroupSelect(\''+obj.objId+"\',\'"+obj.groupName+'\')">'+obj.groupName+'投票结果</a><br></span>';
					if(index == 0){showFirstGroupName = obj.groupName}
						
					$('#showVoteUnitGroupList').append(strHtml);			
				});
			}

			$('span[id=showFirstGroupName]').html(showFirstGroupName);

			if(json.showVoteResultList != null){
				$.each(json.showVoteResultList,function(index,obj){	
					var strHtml = "<div><a><span>"+obj[1]+"</span></a>&nbsp;&nbsp;<span>投票数："+obj[4]+"</span>&nbsp;&nbsp;<span>评论数："+obj[5]+"</span>&nbsp;&nbsp;<span>成绩："+obj[6]+"</span></div>";
						
					$('#showVoteResultListPager').append(strHtml);			
				});
			}
		});
	});
	var vino_gktp = {};
	//评选组点击事件
	vino_gktp.voteUnitGroupSelect = function(voteUnitGroupId,groupName){
		$('#showVoteResultListPager').empty().append('<span id="showFirstGroupName"></span><br>');
		var templateId = $('#templateId').val();
		$('span[id=showFirstGroupName]').html(groupName);
		//加载投票结果列表
		$.getJSON($('#initPath').val()+"/VoteTemplateShowController.do?method=showVoteResult",{"templateId":templateId,"operation":"topNum"},function(json){
			if(json.showVoteResultList != null){
				$.each(json.showVoteResultList,function(index,obj){	
					var strHtml = "<div><a><span>"+obj[1]+"</span></a>&nbsp;&nbsp;<span>投票数："+obj[4]+"</span>&nbsp;&nbsp;<span>评论数："+obj[5]+"</span>&nbsp;&nbsp;<span>成绩："+obj[6]+"</span></div>";
						
					$('#showVoteResultListPager').append(strHtml);			
				});
			}
		});
	}
</script>

<div class="hdjs_main">
	<div class="hdjs_main_left">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/hdjs_bg01.jpg" />
		<div id="showVoteResultTopNum">
			<%@ include file="/view/smallscale/vote/show_vote_result_topNum.jsp" %>
		</div>
	</div>
	<div class="hdjs_main_right">
		<div id="showVoteUnitGroupList" style='display: block; text-align: center;'>
		</div>
		<div id="showVoteResultListPager">
			<span id="showFirstGroupName"></span><br>
		</div>
	</div>
	<div style="clear: both;"></div>
</div>