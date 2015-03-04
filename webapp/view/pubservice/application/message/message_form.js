/*
 * 发送站内信页面
 * created by likg
 */

var messageAdd = {};

//发送
messageAdd.saveMessage = function(messageJson)
{
	if($("#messageType").val()=="00"){
		$('#messageForm').ajaxSubmit({
			url:$("#initPath").val()+"/MessageController.do?method=saveSystemMessage",
			dataType:'json',
			success:function(json){
				if(json.success) {
					alert("操作成功！");
					$('#conBody').loadPage($('#initPath').val()+'/MessageController.do?method=toMessageListView');
				}
			},
			error:function(msg){
				alert("操作失败！");
			}
		});
	}else{
		$('#messageForm').ajaxSubmit({
			url:$("#initPath").val()+"/MessageController.do?method=saveMessage&objIds="+$("#receiver\\.objId").val(),
			dataType:'json',
			success:function(json){
				if(json.success) {
					alert("操作成功！");
					$('#conBody').loadPage($('#initPath').val()+'/MessageController.do?method=toMessageListView');
				}
			},
			error:function(msg){
				alert("操作失败！");
			}
		});
	}
	
}

$(document).ready(function(){
	//自动补全用户名【收信人】
	$("#receiver\\.name").autocomplete($('#initPath').val() + '/UserController.do?method=getObjectQuery&queryColumns=usName,emp.company.name', {
		extraParams:{},//查询条件  例如    {objId:111}
		matchColumn:'usName,emp.company.name',//作为查询显示, 被选中之后匹配的列
		minChars: 0,
		max: 8,
		autoFill: false,
		mustMatch: false,
		scrollHeight: 220,
		formatItem: function(data, i, total) {
			return data.usName+"("+data["emp.company.name"]+")";
		},
		formatMatch: function(data, i, total) {
			alert(data.objId);
			return data.usName;
		},
		formatResult: function(data) {
			return data.usName;
		}
	});
	
	//发送到多人
	$("#sendMorePerson").click(function(){
		$.epsDialog({
			id:'userSelect',
	        title:'选择收信人',
	        width:930,
	        url:$('#initPath').val()+'/view/srplatform/empSelect/selectBox.jsp?property=receiver&dialogId=userSelect&domainName=User&queryColumns=objId,usName,emp.company.name'
	    }); 
		
	});
	
	//发送到所有人
	$("#to_all").click(function(){
		$("#receiverType\\.name").val("所有人");
	});
	
	//发送到指定角色
	$("#to_role").click(function(){
		$.epsDialog({
			id:'roleSelect',
	        title:'选择收信人的角色',
	        url:$('#initPath').val()+'/view/srplatform/empSelect/selectBox.jsp?property=receiverType&dialogId=roleSelect&domainName=Role&queryColumns=objId,chName'
	    }); 
	});
	
	//发送到指定人
	$("#to_user").click(function(){
		$.epsDialog({
			id:'userSelect2',
	        title:'选择收信人',
	        width:930,
	        url:$('#initPath').val()+'/view/srplatform/empSelect/selectBox.jsp?property=receiverType&dialogId=userSelect2&domainName=User&queryColumns=objId,emp.name'
	    }); 
	});
	
    //发送
    $('#submitBtn').click(function(){
		if(!$('#messageForm').valid()){alert('请正确填写表单!');return;}
		if(confirm('确定发送？')) {
			var messageJson = formToJsonObject("messageForm");
			messageAdd.saveMessage(messageJson);
		}
    });
    
    //返回
    $("#cancelBtn").click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/MessageController.do?method=toMessageListView");
    });
    
    //改变选择类型
    $("input[name=receivers]").change(function(){
    	$("input[id=receiverType.objId]").val("");
    	$("input[id=receiverType.name]").val("");
    });
    
})

