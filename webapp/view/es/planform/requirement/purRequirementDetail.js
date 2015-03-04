/*
 * 执行平台，需求详情
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var purRequirementDetail={};

//查询条件过滤
purRequirementDetail.before=function(){
	var option={"purRequirement.objId":$("#objId").val()}
	$('#preqEntryGrid').flexOptions({params:option});
	return true;
}

//查看详细
purRequirementDetail.showDetail=function(name,grid)
{
	if(!purRequirementDetail.validation(name,grid))return;
	$.epsDialog({
        title:'需求条目',
        url:$('#initPath').val()+'/view/es/planform/requirement/preqEntryDetail.jsp?objId='+$(grid).getSelect(),
        width: '800',
        height: '500'
	});
}

//列表操作验证
purRequirementDetail.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个需求条目');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择只一个需求条目');return false;}//是否只选中一个
	return true;
}

$(document).ready(function(){
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/PurRequirementController.do?method=createOrUpdate',{objId:$('#objId').val()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			json2ObjectSpan('purRequirementForm', json.purRequirement);
			
			//附件
			if(json.purRequirement.requireAtt)
				//$("#requireFile").empty().loadPage($('#initPath').val()+'/AttachmentController.do?isSelect=yes&defineSelf=requireAtt&attachRelaId='+json.purRequirement.requireAtt);
				//附件
				$('#requireFile').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
					defineSelf:'requireFile',//存放关联id的属性名
					attachRelaId:json.purRequirement.requireAtt
				});
    	});
    }
});
