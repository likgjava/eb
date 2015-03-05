//定义文件全局变量 处理方法名重复问题

var companyForm={};
companyForm.uniqueHandler = function(param,value){
	var jsonObj={};
	jsonObj["className"]="Company";
	jsonObj["property"]=param;
	jsonObj["value"]=value;
	jsonObj["objId"]=$("#objId").val();
	var isUnique = false;
	$.ajax({
		url:"UniqueController.do?method=isUnique",
		type:"POST",
		async:false,
		data:jsonObj,
		success:function(result){
			isUnique=eval(result);
		}
	})
	return isUnique;
}


$(document).ready(function(){
	var option = {parameter:"objId"};  //默认值:{datatype:"json",textfield:"name",valuefiled:"objId"};
	var provinceDatas=$('select[name=province.objId]').FillOptions($('#initPath').val()+'/DistrictController.do?method=findTopDistrict',option);
    $('select[name=province.objId]').CascadingSelect(
		$('select[name=city.objId]'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',
		option,
		function(datas){$('select[name=town.objId]').html('');}
	);
    $('select[name=city.objId]').CascadingSelect(
    		$('select[name=town.objId]'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',
    		option,
		function(datas){}
	);
    if(organizationParentShortName!='')
		$("input[name=parent.shortName]").val(organizationParentShortName);//初始化父级机构中文名称
	$.validator.addMethod(
			"unique",
			function(value,element,param){
				return companyForm.uniqueHandler(param,value);
			},
			'数据重复'
	);
	$("#companyForm").validate(
			{
				rules:{
					code:{unique:"code"}
				}
			}
	);	
	
    if( $('#objId').val() != undefined && $('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/CompanyController.do?method=createOrUpdate',{objId:$('#objId').val(),'includedProperties':'parent,town,town.parent,town.parent.parent'},function(json){
    		jsonObjectToForm('companyForm', json.company);
    		if(!json.company.parent){
    			$("input[name=parent.shortName]").val('');
    			$("input[name=parent.objId]").val('');
    		}
    		if(json.company.town){
    			var town = json.company.town;
    			$("select[name=province.objId]").val(town.parent.parent.objId);
    			var citydatas=$('select[name=city.objId]').FillOptions(
	    					$('#initPath').val()+'/DistrictController.do?method=getSubDistricts&objId='+town.parent.parent.objId,
	    					{parameter:"objId"},
	    					function(){$("select[name=city.objId]").val(town.parent.objId);}
    					);
    				
    		    var towndatas=$('select[name=town.objId]').FillOptions(
    					$('#initPath').val()+'/DistrictController.do?method=getSubDistricts&objId='+town.parent.objId,
    					{parameter:"objId"},
    					function(){ $("select[name=town.objId]").val(town.objId);}
					);
    		}
    	});
    }
	//返回
	$('#companyReturn').click(function(){
		if("grid"==$("#model").val()){
			$('#conBody').loadPage($('#initPath').val()+'/OrganizationController.do') ;
		}
		else{
			$('#conBody').loadPage($('#initPath').val()+'/OrganizationController.do?method=toTreeList') ;
		}
		
	});
	//提交
	$('#companySave').click(function(){
		if(!$('#companyForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		//alert(obj2str(formToJsonObject('companyForm')));
		$.getJSON($('#initPath').val()+'/CompanyController.do?method=saveCompany', formToJsonObject('companyForm'), function(json){
			if(json.result)alert(json.result,{inco:'info'});;
			if(json.failure)return;
			if("grid"==$("#model").val()){
				$('#conBody').loadPage($('#initPath').val()+'/OrganizationController.do') ;
			}
			else{
				organizationList.reloadTree(json.comId,$("input[id=name]").val(),1);
				$('.treeRight').loadPage($('#initPath').val()+'/CompanyController.do?method=toShowView&objId='+json.comId);
			}
		});
	});

});
