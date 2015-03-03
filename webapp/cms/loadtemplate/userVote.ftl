
<#--
投票
-->
<#if vote??>
<script language="javascript" type="text/javascript">
	$(function(){
		$("#voteTopicDoff").click(function(){
		
		if($("input[name=${vote.objId}][checked]").length == 0){
		   alert("请选择投票的主题！");
		   return false;
		}
		var voteItemIds = "";
		$("input[name=${vote.objId}][checked]").each(function(i,n){
		   if("" == voteItemIds){
		   	  voteItemIds = $(n).val();
		   }else{
		      voteItemIds = voteItemIds+","+$(n).val();
		   }
		});
		
		$.ajax({
			url:"${base}/VoteTopicController.do?method=saveVote",
			type:"POST",
			data:{"voteItemIds":voteItemIds, "voteTopicId" : "${vote.objId}"},
			dataType:'json',
			async:false,
			success:function(msg){
			  if(msg.success='true'){
			  	alert("投票成功！");
			  }else{
			  	alert("投票失败！");
			  }
			}
		})
	  	$("input[name=${vote.objId}]").attr("checked",false);		
	})
	$('#showVoteResult').click(function(){
	
	$.epsDialog({
		title:"${vote.title!}投票结果",
		height:260, 
		width:550, 
		url:"${base}/VoteTopicController.do?method=toShowView&objId=${vote.objId}"
	})
	return false;
	});
	
})
</script>
<div class="formLayout formPa" id="voteAndResult">
<h5>${vote.title!}</h5>
<ul>
  <#list vote.voteItems as item>
  <li>
    <label for="radio">&nbsp;</label>
      <input name="${vote.objId}" <#if vote.multSelect>type="checkbox"<#else>type="radio"</#if>  value="${item.objId}"  />
      ${item.title}
  </li>
  </#list>
</ul>
  <div class="conOperation">
    <button class="narrow" id="voteTopicDoff" ><span>投票</span></button>
    <button class="narrow" id="showVoteResult"><span>结果</span></button>
  </div>
</div>
</#if>