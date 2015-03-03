<div class="dataTables_paginate paging_full_numbers">
<#if hasPrePage>
	<span class="next paginate_button cmsHref_self" id="${firstPage!}" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="${prePage!}" title="上一页">上一页</span> 
</#if>
<#if hasNextPage>
	<span class="next paginate_button cmsHref_self" id="${nextPage!}" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="${lastPage!}" title="尾页">尾页</span>
</#if>
<span class="totalNum">页次：${curPage!}/${totalPage!} 每页${pageSize!}条 </span>
</div>