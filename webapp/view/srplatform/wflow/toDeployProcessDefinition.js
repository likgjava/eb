var toDeployProcessDefinition = {};	

toDeployProcessDefinition.getLatestProcessDefinition=function(){
	//清空表单数据
	$.each($("#proDefTable tr"),function(i,n){
		//从数据所在行开始删除
		if(i > 0){
			$(n).remove();
		}
	})
	$.getJSON($("#initPath").val()+"/WorkFlowController.do?method=getLatestProcessDefinition",{page:"1",rp:"10"},function(json){
		if(json.rows != null){
			$("#proDefLi").show();
			$.each(json.rows,function(i,processDefinition){
				var $table=$("#proDefTable tr");
				var len=$table.length;
				$("#proDefTable").append(
						"<tr id="+(len+1)+">" +
						"	<td align=\'center\'>"+processDefinition.objId+
						"	</td>" +
						"	<td align=\'center\'>"+processDefinition.code+
						"	</td>" +
						"	<td align=\'center\'><a href=\'#\' onclick=\'toDeployProcessDefinition.removeProcessDefinition(\""+processDefinition.objId+"\");return false;\'>删除</a></td>" +
						"</tr>");
			});
		}
	});
}
//删除流程定义
toDeployProcessDefinition.removeProcessDefinition=function(id){
	$.getJSON($("#initPath").val()+"/WorkFlowController.do?method=removeProcessDefinitionById",{"id":id},function(json){
		if(json.result)alert(json.result);
		if(json.failure)return;
		$("#getLatestPro").click();
	});
}
$(document).ready(function() {
	toDeployProcessDefinition.getLatestProcessDefinition();
	$('#attachmentBtn').click(function(){	
		$('#attachmentForm').submit();
	});
	$("#getLatestPro").click(function(){
		toDeployProcessDefinition.getLatestProcessDefinition();
	});
	$('#attachmentForm').submit(function(){    
		//验证文件格式
	    var reg = /^.*(.zip)$/;
	    /*if($('#attachmentFile').val()!='' && !reg.test($('#attachmentFile').val())){
	        alert("请选择正确的流程发布文件!(.zip)");
	        //return false;
	    }*/
		$(this).ajaxSubmit({
			url:$('#initPath').val()+'/WorkFlowController.do?method=deployProcessDefinition',
			dataType:'json',
			success:function(json){	
				if(json.failure){alert(json.result);return;}
				alert('流程发布成功!');
				toDeployProcessDefinition.getLatestProcessDefinition();
			},error:function(msg){
				alert('流程发布失败!');
			}
		});
		return false;
	});
});