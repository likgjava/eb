
var resProjectInfo = {};
//保存
resProjectInfo.save = function(){
	if(!$('#resProjectInfoForm').valid()){alert('请正确填写表单!');return;}
	if($("#resProjectInfoForm").find("input[type=radio][name=engProjType][checked]").length == 0){
		alert("请选择建设工程分类!");
		return ;
	}
	if($("#resProjectInfoForm").find("input[type=radio][name=isVoting][checked]").length == 0){
		alert("请选择是否比选代理机构!");
		return ;
	}
	var resProjectId = $('#resProjectId').val();
	$.getJSON($('#initPath').val()+'/ResProjectController.do?method=checkResProjectItem&resProjectId='+resProjectId,function(json){
		if(json.result=='YES'){
		var jsonObj ={};	
		jsonObj.landNo = $('#landNo').val();
		jsonObj.engProjType = $('input[name=engProjType]:checked').val();
		jsonObj.isVoting = $('input[name=isVoting]:checked').val();
		jsonObj.agencyId = $('#agencyId').val();
		jsonObj.agencyName = $('#agencyName').val();
		jsonObj.resProjectId = $('#resProjectId').val();
			if (jsonObj.isVoting=='01') {
				jsonObj.agencyId = '';
				jsonObj.agencyName = '';
			}
			var url = $('#initPath').val()+'/ResProjectController.do?method=saveResProject';
			$.getJSON(url,jsonObj,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/resproject/resProjectInfoList.jsp');
			});	 
		}else{
			alert('请填写招标项目明细！');
		}
		
	});	 
};
resProjectInfo.back = function(){
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/resproject/resProjectInfoList.jsp');
//	if($('#parentId').val()==''){
//		$('#conBody').loadPage($('#initPath').val()+'/ResProjectController.do?method=loadResProjectManagePage');
//	}else{
//		 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadDisassemblePage&resProjectId="+$('#parentId').val());
//	}
}
$(document).ready(function(){
	$("#resProjectInfoItem").empty().loadPage($('#initPath').val()+'/ResProjectController.do?method=toSetResProjectInfoItemPage&resProjectId='+$("#resProjectId").val());
	
	var isVotingId = $('#isVotingId').val();
	if (isVotingId!=null&&isVotingId!=''&&isVotingId!=undefined) {
		if (isVotingId=='00') {//显示
			$('#showAgency').removeClass('showAgency');
			$("#agencyName").addClass('required');
		}else if (isVotingId=='01') {
			$('#showAgency').addClass('showAgency');
		    $("#agencyName").removeClass();
		}
	}
	
	resProjectInfo.showAgency = function(status){
		if (status=='YES') { //显示
			$('#showAgency').removeClass('showAgency');
			$("#agencyName").addClass('required');
		}else{ //不显示
			$('#showAgency').addClass('showAgency');
		    $("#agencyName").removeClass();
		}
	}
	
	//扩展验证规则，对万元为单位的金额进行验证
	jQuery.validator.addMethod("bigMoney", function(value, element) { 
	    var money = /^([1-9]\d*|(0|[1-9]\d*)\.\d{0,6})$/; 
	    return money.test(value) || this.optional(element); 
	}, "请输入正确的金额(小数位数保留6为以内)");
	
	//扩展验证规则，对万元为单位的金额进行验证
	jQuery.validator.addMethod("linkPhone", function(value, element) { 
	    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	    var phone = /^(\d{3,4}-?)?\d{7,9}$/;
	    return (phone.test(value) || mobile.test(value)) ||this.optional(element); 
	}, "请输入正确的手机号码或电话号码");
	
	$("#agencyName").autocomplete(
	$('#initPath').val() + '/OrgInfoController.do?method=getObjectQuery&queryColumns=objId,orgName', 
	{
		matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
		extraParams:{"agencyId":null,"agencyId_op":"!="},
		mustMatch: true,
		max:50,
		formatItem: function(data, i, total)
		{
			return data.orgName;
		},
		formatMatch: function(data, i, total) {
			return data.orgName;
		},
		formatResult: function(data) {
			return data.orgName;
		}
	}
).result(function(event,data,formatted){
	if(data){
		$("#agencyName").val(data.orgName);//回填id
		$("#agencyId").val(data.objId);//回填id
	}
});
	
	$('#save').click(resProjectInfo.save);
	$('#back').click(resProjectInfo.back);
});