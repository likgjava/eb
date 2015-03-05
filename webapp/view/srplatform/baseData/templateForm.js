var templateForm = {};

$(document).ready(function(){
	var oFCKeditor;
	
	templateForm.vform=$("#TemplateForm").validate();	
	
	 
 
	//获得分类的下拉框数据
	templateForm.typeList=$.ajax({ url: "DictionaryController.do?method=getBaseObjectListByProperty&_queryCols=objId,dicName",data:{"dicType":"templateType"}, async: false }).responseText
	templateForm.typeJosn =JSON.parse(templateForm.typeList);
	if(templateForm.typeJosn.length == 0)
		$("#type").append("<option value=''>暂无模版类型，请先在字典表中创建模版类型</option>");
	else{
		$.each(templateForm.typeJosn,function(i,n){
			$("#type").append("<option value='"+n.objId+"'>"+n.dicName+"</option>")
		})
	}
	
    if($("#objId").val()!=""&&$("#objId").val()!="null"){
    	$.getJSON("TemplateController.do?method=createOrUpdate",{objId:$("#objId").val()},function(json){
    		jsonObjectToForm($("#TemplateForm")[0],json.list[0]);

    		//创建飞鱼编辑器
    		oFCKeditor = new FCKeditor('FCKContent','100%','300','Template',json.list[0].des) ;
    	    oFCKeditor.ReplaceTextarea();
    	})
    }
    else{
    	//创建飞鱼编辑器
		oFCKeditor = new FCKeditor('FCKContent','100%','300','Template','') ;
	    oFCKeditor.ReplaceTextarea();
    }
	
	//返回
	$("#return").click(function(){
		loadPage_toContent("/TemplateController.do");
	})
	
	//提交
	$("#submit").click(function(){
		if(templateForm.vform.checkForm()){
			FCKeditor_BackValue();  //将FCK的值放入到隐藏域中
			if($("#FCKContent").val() == ""){
				alert("请填写模版内容！");return;
			}
			
			var josnObj=formToJsonObject($("#TemplateForm")[0]);
			josnObj.json=JSON.stringify(josnObj);
			
			$.ajax({
				url:"TemplateController.do?method=save",
				type:"POST",
				data:josnObj,
				success:function(msg){
					loadPage_toContent("/TemplateController.do?method=toSuccess");
				}
			})
		}else{
			templateForm.vform.showErrors();
		}
	})

})
