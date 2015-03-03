/*
 * 供应商资质表单(弹出用)
 * created by yucy
 */
var supplierQualityForm={};


//保存或提交
supplierQualityForm.createOrUpdate = function(status){
	if($("#param").find("input").length==0){alert("无任何参数，无法完成操作！");return}
	var param = $("#param").find('ul').find('li').not('li[id=opinion]');
	var paramJson = '';
	$.each(param,function(index,obj){
		var json = '';
		if(($(obj).find("select")).length==1){
			json = $(obj).find("input:eq(0)").val()+':'+$(obj).find("select:eq(0)").val()+':'+$(obj).find("input:eq(1)").val()
		}else{
			json = $(obj).find("input:eq(0)").val()+':'+$(obj).find("input:eq(1)").val()+':'+$(obj).find("input:eq(2)").val()
		}
		if(index==0){
			paramJson += json;
		}else{
			paramJson += ','+json;
		}
	})
	
	var jsonObj=formToJsonObject($("#paramForm")[0]);
	//保存为草稿或直接提交
	jsonObj.auditStatus = status;
	
	if('00'==status||'01'==status){
		jsonObj.useStatus = '00';
	}else if('02'==status){
		jsonObj.useStatus = '01';
	}else{
		jsonObj.useStatus = '02';
	}
	jsonObj.paramJson = paramJson;
	$.getJSON($('#initPath').val()+"/OrgQualityController.do?method=saveQualificationInfo",jsonObj,function(json){
		if(json.success){
			alert(json.result);
			$('.epsDialogClose').trigger('click');
			//刷新列表
			$('#conBody').loadPage($('#initPath').val()+"/OrgQualityController.do?method=toOrgQualityManageView");
		}else{
			alert("操作失败!");
		}
	})
}

$(document).ready(function(){
	var resId = $("#qualificationFile").attr("value")
	
	//回填附件
	if(null!=resId&&""!=resId){
		$('#qualificationFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=qualificationFile&attachRelaId='+resId);
	}
	else{
		$('#qualificationFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=qualificationFile');
	}
	
	//修改则初始化日期控件
	if(null!=$("#objId").val()&&""!=$("#objId").val()){
		var input = $("#param").find('ul').find('input');
		$.each(input,function(index,obj){
			if($(obj).hasClass('siDate')){
				$(obj).epsDatepicker();
			}
		});
	}
	//选择资质类型
	$("input[name=qualificationClass.qualityName]").click(function(){
		$(this).listTree(
				$('#initPath').val()+'/QualificationClassController.do?method=getTree',
				'radio',
				function(selectedId,text){
					$("input[name=qualificationClass.objId]").val(selectedId);
					//根据所选类型,联动定义
					$.getJSON($('#initPath').val()+'/QualificationDefineController.do?method=getDefineByClass',{"qualificationClassId":selectedId},function(json){
						if(null!=json.defineList&&json.defineList.length>0){
							$("select[name=qualificationDefine.objId]").empty().append('<option>请选择资质定义!</option>');
							$.each(json.defineList,function(index,obj){
								$("select[name=qualificationDefine.objId]").append('<option value="'+obj.objId+'">'+obj.qualityName+'</option>');
							})
						}else{
							$("select[name=qualificationDefine.objId]").empty().append('<option>无可选资质定义!</option>');
						}
						$("#paramForm").find('ul:eq(1)').empty().append('<li class="fullLine"><label>暂无参数！</label></li>');
					})
				}
		);
	})
	
	//选择资质定义
	$("select[name=qualificationDefine.objId]").change(function(){
		if($(this).val()!=null&&$(this).val()!=""){
			$.getJSON($('#initPath').val()+'/QualificationParamController.do?method=getParamByDefine',{"qualificationDefineId":$(this).val()},function(json){
				if(null!=json.paramList&&json.paramList.length>0){
					$("#param").find('ul').empty();
					$.each(json.paramList,function(index,obj){
						var isRequired = 'class="';
						var requiredTips = '';
						if(obj.isRequired=='true'){
							isRequired += 'required';
							requiredTips += '<span class="eleRequired">*</span>';
						}
						//1.文本 2.整数 3.含小数 4.日期
						if(obj.paramType=='1'){
							isRequired += ' text';
							requiredTips += '<span>文本格式</span>';
						}else if(obj.paramType=='2'){
							isRequired += ' digits';
							requiredTips += '<span>整数格式</span>';
						}else if(obj.paramType=='3'){
							isRequired += ' floats';
							requiredTips += '<span>含小数格式</span>';
						}else if(obj.paramType=='4'){
							isRequired += ' sysicon siDate bbit-dp-input';
							requiredTips += '<span>日期格式</span>';
						}else if(obj.paramType=='5'){
							requiredTips += '<span>级别格式</span>';
						}
						
						//级别
						var levelHtml = '';
						if(obj.paramType=='5'){
							levelHtml += '<select id="'+index+'.paramValue" name="'+index+'.paramValue" '+isRequired+'">';
							var levelString = obj.level.split(",");
							levelHtml += '<option value="">---请选择---</option>';
							$.each(levelString,function(index,obj){
								var KeyAnnValue = obj.split("#");
								levelHtml += '<option value='+KeyAnnValue[1]+'>'+KeyAnnValue[0]+'</option>';
							});
							levelHtml += '</select>'+requiredTips;
						}else{
							levelHtml +='<input id="'+index+'.paramValue" name="'+index+'.paramValue" size="70" '+isRequired+'"/>'+ requiredTips
						}
						
						//根据所选定义,联动参数
						$("#param").find('ul').append(
								'<li class="fullLine">'+
								'<label><span>'+obj.qualityName+'</span>：</label>'+
								'<input name="'+index+'.ParamId" value="'+obj.objId+'" class="hidden"/>'+
								levelHtml +
								'</li>'
						);
					})
					
					//添加日期事件
					$.each($("#param").find('ul').find("input"),function(index,obj){
						if($(obj).hasClass("siDate")){
							$(obj).epsDatepicker();
						}
					})
					
				}else{
					$("#param").find('ul').empty().append('<li class="fullLine"><label>暂无参数！</label></li>');
				}
			})
		}
	})
	
	//保存
	$("#saveQuality").click(function(){
		if(!$('#paramForm').valid()){alert('请正确填写表单!');return;}
		if(!$('#param').valid()){alert('请正确填写表单!');return;}
		supplierQualityForm.createOrUpdate('00');
	})
	
	//提交
	$("#submitQuality").click(function(){
		if(!$('#paramForm').valid()){alert('请正确填写表单!');return;}
		if(!$('#param').valid()){alert('请正确填写表单!');return;}
		if(confirm('确认提交?')){
			supplierQualityForm.createOrUpdate('01');
		}
	})
	
	//点击关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
});