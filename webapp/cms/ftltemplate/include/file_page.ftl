页次：${curPage!}/${totalPage!} 
每页${pageSize!}条 
<#if hasPrePage>
	<a href="${firstPage!}" title="首页">首页</a> 
	<a href="${prePage!}" title="上一页">上一页</a> 
<#else>
	<a href="javascript:void(0)" disabled title="当前已经是第一页">首页</a> 
	<a href="javascript:void(0)" disabled title="当前已经是第一页">上一页</a> 
</#if>

<#if hasNextPage>
	 <a href="${nextPage!}" title="下一页">下一页</a> 
	 <a href="${lastPage!}" title="尾页">尾页</a>
<#else>
	<a href="javascript:void(0)" disabled title="当前已经是最后一页">下一页</a> 
	<a href="javascript:void(0)" disabled title="当前已经是最后一页">尾页</a> 
</#if>
