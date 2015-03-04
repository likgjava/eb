/*
 * 执行平台，采购申报书明细表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanSubForm={};
var num = 0;
$(document).ready(function(){
    if($('#task_sub_objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/TaskPlanSubController.do?method=createOrUpdate',{"objId":$("#task_sub_objId").val()},function(json){
    		json2Object('lookTaskPlanForm', json.taskPlanSub);
    		// 回填申报书条目信息
    		$.getJSON($("#initPath").val()+"/PreqEntryController.do?method=getObjectQuery&queryColumns=objId,attachRelaId,spec,warrentyLen,paymentClause,deliveryRequire,servicePromise,qualityAssurance,acceptStandard,technical",{"taskPlanSub.objId":$('#task_sub_objId').val()},function(json){
    			var list = json.result;
    	    	if(null != list && '' != list && "null" != list && "[]" != list){
    	    		// 回填采购需求条目信息
    	    		json2Object('lookTaskPlanForm', list[0]);
    	    		$.each(list,function(i,n){
    	    			if(null != n.attachRelaId && "" != n.attachRelaId){
    	    				// 获取附件
    	    				$.getJSON($("#initPath").val()+"/AttachmentController.do?method=getObjectQuery&queryColumns=objId,viewName",{"relationId":n.attachRelaId},function(json){
    	    					var list = json.result;
    			    	    	if(null != list && '' != list && "null" != list && "[]" != list){
    			    	    		var fileContent = "";
    			    	    		$.each(list,function(k,o){
    			    	    			num ++;
    			    	    			fileContent += '<li class="fullLine">';
    			    	    			fileContent += '<tr>';
    			    	    			fileContent += '<th style="width: 10%"><label class="short">附件'+num+'：</label></th>';
    			    	    			fileContent += '<td colspan="3">';
    			    	    			fileContent += '&nbsp;';
    			    	    			fileContent += '&nbsp;';
    			    	    			fileContent += '<a href="#" onclick="taskPlanSubForm.downLoadFole(\''+o.objId+'\');" id="_downFile" num="'+num+'" objId="'+o.objId+'"><span>'+o.viewName+'</span></a>';
    			    	    			fileContent += '</td>';
    			    	    			fileContent += '</tr>';
    			    	    			fileContent += '</li>';
    			    	    		})
    			    	    		$("#TaskPlanUl").append(fileContent);
    			    	    	}
			    	    	})
    	    			}
    	    			return true;
    	    		})
    	    		var money = $('#totalPrice').html();
    	    		$('#totalPrice').html(yToWany(money));
    	    	}
    		})
    		
    	});
    }
	
    taskPlanSubForm.downLoadFole = function(objId){
    	window.open($('#initPath').val()+"/AttachmentController.do?method=downLoadFile&objId="+objId);
    }
    
	//关闭
	$('#task_plan_sub_close').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	
	
});
