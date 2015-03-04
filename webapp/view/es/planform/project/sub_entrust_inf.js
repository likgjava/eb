var subEntrustInf = {};

function checkTime(){
	var constime=$("#constime").val();
	var constimeArray=constime.split("-");
	var constimeyear=constimeArray[0];
	var constimemonth=constimeArray[1];
	var constimeday=constimeArray[2];
	
	var consfinishtime=$("#consfinishtime").val();
	var consfinishtimeArray=consfinishtime.split("-");
	var consfinishtimeyear=consfinishtimeArray[0];
	var consfinishtimemonth=consfinishtimeArray[1];
	var consfinishtimeday=consfinishtimeArray[2];
	
	if(consfinishtimeyear<constimeyear){
		alert("拟完成时间不能早于委托时间!");
		return false;
	}else{
		if(consfinishtimemonth<constimemonth){
			alert("拟完成时间不能早于委托时间!");
			return false;
		}else {
			if(consfinishtimeday<constimeday){
				alert("拟完成时间不能早于委托时间!");
				return false;
			}
		}
	}
	return true;
}

$(document).ready(function(){
	$("#infoDiv").tabs();
	$("#constime").epsDatepicker();
	$("#consfinishtime").epsDatepicker();
	$("#subEntrustForm").validate();
	
	//导入委托
	$("#upload").click(function(){
		if($("#entrustFile").val()=="" || $("#entrustFile").val()==null){
			alert("导入委托不能为空!");
		}else{
			alert('导入成功!');
		}	
	})
	
	//一般情况第4个参数不需要改.注意content是保留字.如果是修改页面需等查询的回调函数里再执行
	subEntrustInf.oFCKeditor = new FCKeditor('consContents','70%','300','Template', 'fck数据信息,fck例程') ;
	subEntrustInf.oFCKeditor.ReplaceTextarea();
	
	//提交委托
	$("#sure").click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if($("#subEntrustForm").valid()){
			if(checkTime()){
				$.ajax({
					url:$('#initPath').val()+'/ProjectController.do?method=addConsignprotocol',
					type:'post',
					data:formToJsonObject('subEntrustForm'),
					dataType:'json',
					success:function(json){ 	 
					if(json.result)alert(json.result);if(json.failure)return;
					},error:function(msg){alert('操作失败.');}
				});
			}
		}
	})
	
	//上一步
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/agent_accept.jsp");
	})
	
	//下一步
	$("#next").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/sure_entrust.jsp");
	})
	
})