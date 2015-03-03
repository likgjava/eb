<script language="javascript" type="text/javascript">
$(function(){

	$("#searchNewsBtn").click(function(){
		window.open("${base}/CmsNewsController.do?method=search&chnlCodes="+$("#chnlCodes").val()+"&searchKey="+$("#searchKey").val()+"&pageSize=20")
	})
})
</script>

<form id="searchNewsForm" action="${base}/CmsNewsController.do?method=search">
      <input id="searchKey" name="searchKey" type="text"  value="请输入关键字"/>
      <input id="chnlCodes" name="chnlCodes" type="hidden"  value=""/>
      <button id="searchNewsBtn"><span>搜索</span></button>  
</form>
