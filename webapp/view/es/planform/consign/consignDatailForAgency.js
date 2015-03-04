/*
 * 执行平台，委托书表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignForm={};
$(document).ready(function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/consign/consignListForAgencyConfirm.jsp");
	//日历控件
    $("#consTime").epsDatepicker();
    $("#consFinishTime").epsDatepicker();
    $("#effectiveStartTime").epsDatepicker();
    $("#effectiveEndTime").epsDatepicker();
	if($("#consignType").val()=="00"){
		$("#startTime").hide();
		$('#effectiveStartTime').removeClass('required').val('');
		$('input[name=effectiveStartTime]').val('');
		$("#endTime").hide();
		$('#effectiveEndTime').removeClass('required').val('');
		$('input[name=effectiveEndTime]').val('');
		$("#effectiveStartTime").val(null);
		$("#effectiveEndTime").val(null);
	}
	//判断输入字符是否超过多少字符
	function wordsNumMoreThan(num,id)
	{
		var otext = $("#"+id).val();
	    var app = $("label[for='"+id+"']").html();
		if(otext.length>num)
		{
			alert(app+"的输入字数不能超过"+num+"字符！")
			return true;
		}
		return false;
	};
    $("#consAgentName").autocomplete(
    		$('#initPath').val() + '/OrgInfoController.do?method=getObjectQuery&queryColumns=objId,orgName', 
    		{
    			matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
    			extraParams:{"agencyId":null,"agencyId_op":"!="},
    			mustMatch: true,
    			formatItem: function(data, i, total) {
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
    			$("#consAgentName").val(data.orgName);//回填id
    			$("input[id=consAgent.objId]").val(data.objId);//回填id
    		}
    	});
    $("[name=consType]").click( function() {
    	//当前委托为委托
    	 if(this.value=='00'){
    		$("#startTime").hide();
    		$('#effectiveStartTime').removeClass('required').val('');
    		$('input[name=effectiveStartTime]').val('');
    		$("#endTime").hide();
    		$('#effectiveEndTime').removeClass('required').val('');
    		$('input[name=effectiveEndTime]').val('');
    		$("#effectiveStartTime").val(null);
    	    $("#effectiveEndTime").val(null);
    	 }
    	 else{
    		$('#effectiveStartTime').val("");
    		$('#effectiveEndTime').val("");
    		$("#startTime").show();
    		$("#endTime").show();
    		$('#effectiveStartTime').addClass('required');
    		$('#effectiveEndTime').addClass('required');
         }
    });  
	//保存
	$('#consignSave').click(function(){
		$("#useStatus").val('00');
		$("#confirmStatus").val('00');
		if(!$('#consignForm').valid()){
			alert('请正确填写表单!');
			return;
		}else if($("#consFinishTime").val()<=$("#consTime").val())
		{
			alert("拟完成时间应在委托时间之后！");
		}else if($("#effectiveStartTime").val()>=$("#effectiveEndTime").val()&&$("[name=consType]").value=='01')
		{
			alert("有效结束时间应在有效开始时间之后！");
		}
		else if(window.confirm('确定要保存吗？'))
		{
			$.getJSON($('#initPath').val()+'/ConsignController.do?method=updateSaveConsignForBuyer', formToJsonObject('consignForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				//跳转到上一个页面
				$('button[name=historyBackBtn]').click();
			});
		}
	});
	
	//提交
	$('#consignSubmit').click(function(){
		$("#useStatus").val('01');
		$("#confirmStatus").val('00');
		if(!$('#consignForm').valid()){
			alert('请正确填写表单!');
			return;
		}else if($("#consFinishTime").val()<=$("#consTime").val())
		{
			alert("拟完成时间应在委托时间之后！");
		}else if(window.confirm('确定要提交招标中心确认吗？'))
		{
			$.getJSON($('#initPath').val()+'/ConsignController.do?method=updateSubmitConsignForBuyer', formToJsonObject('consignForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				//跳转到上一个页面
				$('button[name=historyBackBtn]').click();
			});
		}
	});

	var isShowOpinion = $("#isShowOpinion").val();
	if(isShowOpinion != "true"){
		$("#show_audit_view").remove();
	}
});

$('#show_audit_view').click(function(){
	$("#consign_audit_list").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#consignForm").find('input[id=objId]').val()+'&taskType=05');
});
