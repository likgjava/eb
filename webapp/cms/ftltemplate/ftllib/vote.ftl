
<#--
投票
-->
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
})
</script>
<ul>
          <li>
            <strong>${vote.title}</strong>
          </li>
          <#list vote.voteItems as item>
          <li>
            <label for="radio">
              <input name="${vote.objId}" <#if vote.multSelect>type="checkbox"<#else>type="radio"</#if>  value="${item.objId}"  />
              ${item.title}</label>
          </li>
          </#list>
        </ul>
          <div class="center">
            <button class="narrow" id="voteTopicDoff" ><span>投票</span></button>
            <button class="narrow" onclick="window.open('${base}/VoteTopicController.do?method=toShowView&objId=${vote.objId}','','width:40px')"><span>结果</span></button>
          </div>