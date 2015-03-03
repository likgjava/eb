<#--
该文件不要删除,用于参考
-->
<script language="javascript" type="text/javascript">
$(function(){
	$("[name^=searchPage]").click(function(){
	this.href="${base}/CmsNewsController.do?method=search&chnlCodes="+$("#_chnlCodes").val()+"&searchKey="+$("#_searchKey").val()+"&pageSize="+$("#pageSize").val()+"&curPage="+this.id;
	this.target="_top";
	})
});
</script>

页次：${curPage!}/${totalPage!} 
每页${pageSize!}条 
<#if hasPrePage>
 <a href="javascript:void(0)" name="searchPage_first" id="${firstPage!}" title="首页">首页</a> 
 <a href="javascript:void(0)" name="searchPage_pre" id="${prePage}" title="上一页">上一页</a> 
<#else>
 <a href="javascript:void(0)" disabled title="当前已经是第一页">首页</a> 
 <a href="javascript:void(0)" disabled title="当前已经是第一页">上一页</a> 
</#if>

<#if hasNextPage>
 <a href="javascript:void(0)" name="searchPage_next" id="${nextPage!}" title="下一页">下一页</a> 
 <a href="javascript:void(0)" name="searchPage_last" id="${lastPage!}" title="尾页">尾页</a>
<#else>
 <a href="javascript:void(0)" disabled title="当前已经是最后一页">下一页</a> 
 <a href="javascript:void(0)" disabled title="当前已经是最后一页">尾页</a>
</#if>
<input id="pageSize" name="pageSize" type="hidden"  value="${pageSize!}"/>
<input id="_searchKey" name="searchKey" type="hidden"  value="${searchKey!}"/>
<input id="_chnlCodes" name="chnlCodes" type="hidden"  value="${chnlCodes!}"/>
