<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<div class="formLayout">
<div class="partContainers operationLog"><h5 id="performancebond"><span class="switch  left11">项目归档</span></h5></div>
     <div id="fileDetail">
	<flex:flexgrid checkbox="false" id="resultPublishedListGrid" url="view/esdemo/data/projectmanager/project_end.txt" queryColumns=""  
			searchZone="resultPublishedListSearchZone" rp="10" title="" width="1200" height="150" onSubmit="resultPublishedList.before" onKeyRight="resultPublishedList.keyRight" onSuccess="resultPublishedList.success">
		<flex:flexCol name="no" display="序号" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="code" display="招标编号" sortable="true" width="150" align="left"></flex:flexCol>
		<flex:flexCol name="name" display="招标名称" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="rname" display="采购方式" sortable="true" width="200" align="left"></flex:flexCol>	
		<flex:flexCol name="phone" display="项目负责人" sortable="true" width="200" align="left"></flex:flexCol>	
	</flex:flexgrid>
	</div>
</div>
<script>
var resultPublishedList = {};
//新增开标人
resultPublishedList.add = function(){
	$.epsDialog({
      title:'修改招标单位',
      url:$('#initPath').val()+'/view/esdemo/projectmanager/opening_tenders_group.jsp',
      width: '800',
      height: '400',
      onOpen: function(){ },
      afterLoad: function(){ },
      onClose: function(){
      	
      }
});
}
//加载成功后调用方法
resultPublishedList.success = function(){
	$("#resultPublishedListGrid").flexAddOptionStr({
		  '<button class="btn" type="button"><span><span>存档</span></span></button>' : function(btn,rowId,obj){
				 btn.click(function(){
					 resultPublishedList.modify(rowId);
				 })
				 .appendTo(obj)
			 }
	})
}
//修改开标人
resultPublishedList.modify = function(id){
	alert("操作成功");
}
//删除开标人
resultPublishedList.del = function(id){
	if(window.confirm("确定要删处改用户?")){
		alert("删除成功!");
	}
}



</script>
