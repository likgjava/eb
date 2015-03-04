/*
 * 执行平台,录入招标中心
 * author: 杨兴
 * mail: yangx@gpcsoft.com
 */
var agentList={};
//
////新增
//agentList.add=function(name,grid){
////	alert(name+" aaa "+grid);
//	if(!agentList.validation(name,grid))return;
////	alert($(grid).getSelect()+" abc");
//	$('#conBody').loadPage($('#initPath').val()+'/BlockTradeController.do?method=saveAgent&type=1&org_id='+$(grid).getSelect());
//}  
//
////列表操作验证
//agentList.validation=function(name,grid){
//	if($(grid).isSelectEmpty()){alert('请选择一个招标中心');return false;}//是否选中
//	if(!$(grid).isSelectOne()){alert('请只选择一个招标中心');return false;}//是否只选中一个
//	return true;
//}

	//关闭
	$("#subProjectReturn").click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	//保存
	$("#subAgentSaveButton").click(function(){
		var item = $(":radio[name=radioOrgId]:checked").val();
		if(item!=undefined&&""!=item){
			if(window.confirm("确认保存！")){
				$.getJSON($('#initPath').val()+'/BlockTradeController.do?method=saveAgent&type=1&org_id='+item,{}, function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					alert("保存成功！");
					$("#root").empty().loadPage($('#initPath').val()+'/view/es/planform/taskplan/blockTradeExtraction.jsp');
					$('#epsDialogCloseNoReload').click();
				});
			}
		}else{
			alert(" 请选择要录入的招标中心！ ");
		}
	});

$(document).ready(function(){

});

