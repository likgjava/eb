<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
var ProcessDefineList={};
ProcessDefineList.pageContent;

ProcessDefineList.success = function(){
	$("#processDefineGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').empty().loadPage($('#initPath').val()+'/ProcessDefineTemplateController.do?method=toCreateOrUpdate&objId='+rowId);
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">编缉流程节点</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').empty().loadPage($('#initPath').val()+'/ProcessDefNodeTemplateController.do?processDefId='+rowId);
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#processDefineGrid").flexGetRowJsonById(rowId); 
				if(window.confirm('确定删除['+json.processDefName+']吗?')){
					$.getJSON($('#initPath').val()+'/ProcessDefineTemplateController.do?method=removeProcessDefineTempCascadeDelNodeTempCascadeDelNodeFormTemp',{'objId':rowId},function(json){
						$("#processDefineGrid").reload();
					});
				}
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">复制流程</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#processDefineGrid").flexGetRowJsonById(rowId); 
				$.epsDialog({
					id:'copy_process_define',
			        title:'复制流程定义模版['+json.processDefName+']',
			        url:$("#initPath").val()+"/ProcessDefineTemplateController.do?method=toCopyProcessDefineTemplateFormView&objId="+rowId,
			        width: '800',
			        height: '500',
			        maxWin:false,
			        onClose: function(){
			        	
			        }
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">生效验证</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#processDefineGrid").flexGetRowJsonById(rowId); 
				$.getJSON($('#initPath').val()+'/ProcessDefineTemplateController.do?method=validateProcessDefineTemplate',{'objId':rowId},function(json){
					if (json.failure == 'true') {
						var html = json.result;
						html += '<div class="conOperation">';
						html += '<button type="button" tabindex="18"><span>关闭</span></button>';
						html += '</div>';
						$.epsDialog({
							content:html,
				    		id:'message_content',
				        	title:'您的流程配置有误,验证失败!以下是错误信息..',
				        	url:'',
				        	width: '650',
				        	height: '380',
				        	isReload:false
				    	});
						$('#message_content').find('button').click(function(){
							$('#message_content .epsDialogCloseReload').click();
						})
					} else {
						$("#processDefineGrid").reload();
					}
				});
			}).appendTo(obj);
		}
	});
	$("#processDefineGrid").flexGetColByName({
		 'effective' : function(id,t){
		 	var json = $("#processDefineGrid").flexGetRowJsonById(id); 
		 	var content = '';
		 	if ('' == json.effective || false == json.effective || 'false' == json.effective) {
		 		content = '否';
			} else {
				content = '是';
			}
			$(t).html(content);
		}
	});
}
ProcessDefineList.add = function(){
	$('#conBody').empty().loadPage($('#initPath').val()+'/ProcessDefineTemplateController.do?method=toCreateOrUpdate');
}
$(document).ready(function(){
	$("#returnUrl").val($('#initPath').val()+'/ProcessDefineTemplateController.do');
})
</script>
<div class="partContainers">	  
	<flex:flexgrid checkbox="false" id="processDefineGrid" url="ProcessDefineTemplateController.do?method=list&order=createTime&order_flag=true" 
	onSuccess="ProcessDefineList.success" rp="200" title="流程定义模板" queryColumns="" usepager="false">
		<flex:flexCol name="processDefName" display="processDefineForm.processDefName" sortable="true" width="300" align="left"></flex:flexCol>
		<flex:flexCol name="bizType" display="processDefineForm.bizType" sortable="true" width="120" align="left"></flex:flexCol>
		<flex:flexCol name="status" display="processDefineForm.status" alias="statusCN" sortable="true" width="40" align="center"></flex:flexCol>
		<flex:flexCol name="effective" display="processDefineTemplateForm.effective" sortable="true" width="50" align="center"></flex:flexCol>
		<flex:flexCol name="createTime" display="processDefineForm.createTime" sortable="true" width="120" align="center"></flex:flexCol>
		<flex:flexCol name="objId" display="操作" sortable="true" width="350" align="center"></flex:flexCol>
		<flex:flexBtn name="新增流程定义" bclass="add" onpress="ProcessDefineList.add"></flex:flexBtn>
	</flex:flexgrid>
</div>