
var recordFormForm={};
var craeteProjForTaskPlanSub={};
craeteProjForTaskPlanSub.add = function(){
	var taskTypes = $("#tenderType").val();
	//弹出对应的申报书明细信息
	$.epsDialog({
        title:"申报书明细",
        url:$("#initPath").val()+"/TaskPlanController.do?method=toTaskPlanSubListPageForSuperaddition&taskType="+taskTypes+"&ebuyMethod="+$("#ebuyMethod").val()+"&rp=6&taskPlanSubIds_not="+$("#taskPlanSubIds").val(),
        width: 600,
        height: 330,
        isReload:false,
        onClose: function(){
        }
	});
}
//移除
craeteProjForTaskPlanSub.remove = function(){
	if($("#consignGrid").isSelectEmpty()){alert('请选择申报书明细');return false;}//是否选中
	if(confirm("确认移除申报书明细吗？")){
		var ids_remove = $("#consignGrid").getSelects().split(',');
		var taskPlanSubIds = $("#taskPlanSubIds").val();
		$.each(ids_remove,function(k,o){//循环要删除申报书条目
			$.each($("#taskPlanSubIds").val().split(','),function(i,n){//循环已经存在的申报书条目
				if (n=o) {//判断是否是要删除的申报书条目
					taskPlanSubIds = taskPlanSubIds.replace(n,'');
				}
			});
		});
		var new_taskPlanSubIds = new Array();
		$.each(taskPlanSubIds.split(','),function(j,f){
			if (f!=null&&f!=''&&f!=undefined) {
				new_taskPlanSubIds.push(f);
			}
		});
	}
	if(new_taskPlanSubIds.toString() == null || new_taskPlanSubIds.toString() == ""){
		alert("委托至少要有一条申报书明细!");
		return false;
	}
	//回选最终的数据
	$("#taskPlanSubIds").val(new_taskPlanSubIds.toString());
	//刷新表单
	$("#consignGrid").reload();
}


craeteProjForTaskPlanSub.saveOrSumbit=function(){
	$('#craeteProjForTaskPlanSubForm').ajaxSubmit({
		url:$('#initPath').val()+"/RecordFormController.do?method=saveRecordFormJz",
		dataType:'json',
		success:function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toJZGCTaskPlanListForCreateProj');
		},
		error:function(msg){
			alert(msg);
		}
	});
}
craeteProjForTaskPlanSub.viewTaskPlanSub=function(taskPlanSubId){
	//弹出对应的申报书明细信息
	$.epsDialog({
        title:"申报书明细",
        url:$("#initPath").val()+"/view/es/planform/taskplan/taskPlanSubDetailForm.jsp?taskPlanSubId="+taskPlanSubId,
        width: 750,
        height: 450,
        isReload:false,
        onClose: function(){
        }
	});
}
//招标编号重构
craeteProjForTaskPlanSub.changePrefixCode = function(code,ebuyMethod){
	var pre ;
	switch(ebuyMethod){
	 case "00": pre="open"; break;
	 case "01": pre="invite"; break;
	 case "02": pre="negotiate"; break;
	 case "03":	 pre="inquiry";	 break;
	 case "04":	 pre="single";	 break;
	 default:pre=""; 
	};
	var v= pre+code.substring( code.indexOf('-'),code.length);
	$('#projCode').val(v);
}
$(document).ready(function(){
	$('#recordFormForm').validate();
	$('#recordFormReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toTaskPlanListForCreateProj')
	});
	//提交
	$('#recordFormSave').click(function(){
		if(!$('#craeteProjForTaskPlanSubForm').valid()){alert('请正确填写表单!');return;}
		if ($('#managerId').val()==PlatForm.user.emp.objId) {
			if (window.confirm('确认要分配给自己？')){
				if($("#taskPlanSubIds").val() == "" || $("#taskPlanSubIds").val() == "-1"){
					alert("请选择申报书明细！");
					return;
				}
				craeteProjForTaskPlanSub.saveOrSumbit();
			}
		}else{
			if($("#taskPlanSubIds").val() == "" || $("#taskPlanSubIds").val() == "-1"){
				alert("请选择申报书明细！");
				return;
			}
			if(window.confirm('是否确认？')){
				craeteProjForTaskPlanSub.saveOrSumbit();
			}
		}
	});
	
	$('#ebuyMethod').change(function(){
		craeteProjForTaskPlanSub.changePrefixCode($('#projCode').val(),$('#ebuyMethod').val());
	})
	
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
    
    //加载数据，判断id是否为空
    if($('#objId').val()!= null && $('#objId').val()!=''){
    	//编号置为不可用
    	$("#projCode").attr("disabled","disabled");
    };
    
    //查询条件过滤
    recordFormForm.before=function(){
    	var option={"objId":$("#taskPlanSubIds").val(),"objId_op":"in"}
    	$('#recordFormGrid').flexOptions({params:option});
    	return true;
    }
    
  //加载数据成功之后调用的函数
    recordFormForm.success=function(){
    	$("#recordFormGrid").flexAddOptionStr({
    		'<span><a href="#" class="abtn">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'   : function(btn,rowId,obj){
    		btn.click(function(){
    			$.epsDialog({
    		    	title:'备案书',
    		    	url:$('#initPath').val()+"/RecordFormController.do?method=toShowView&objId="+rowId,
    		    	width: '800',
    		    	height: '500'
    			});
    		}).appendTo(obj);
    	}});
	}
});
