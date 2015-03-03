/**
 * 
 * create by yucy
 * 
 * 
 */
var catalogForm={};

//保存或者提交
catalogForm.saveOrSubmit = function(saveType){
	var catalogJson = formToJsonObject("catalogForm");
	catalogJson.saveType = saveType;
	catalogJson.type= 'new' ;
	
	//拼装区域id串
	catalogJson.districtString =  $("input[id=district.objId]").val();
	
	$.getJSON($('#initPath').val()+'/PurCatalogController.do?method=savePurCatalog',
			catalogJson,
			function(json){
				if(json.success){
					$("#newCatalogDiv").html("");
					catalogCategoryForm.oTable.fnDraw();
				}else{
					alert('操作失败！');
				}
			}
		);
}

$(document).ready(function(){
	
	//初始化发布日期
	$("input[name=relDate]").epsDatepicker();
	
	//初始化年度
	var year = (new Date()).getFullYear(); 
	for(var i = -2; i <= 5; i++){
		$("#catalogForm").find("select[name=year]").append("<option value='" + (year + i) + "'>" + (year + i) + "</option>");
	}
	
	//选择区域
    $('input[id=district.name]').click(function(e){
	    $.epsDialog({
	    	id:'district',
	        title:'请选择区域',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=district&className=District&action=listTop&isOpen=1&isCheckBox=true&dialogId=district'
	    }); 
	});
    
	//判断修改则加载目录详细
	if($("#objId").val()!=null&&$("#objId").val()!=""){
		//隐藏按钮
		$("#catalogSave").hide();$("#saveSecond").show()
		$("#catalogDetailTree").show().loadPage($("#initPath").val()+"/view/bizplatform/base/purcatalog/catalog_detail_form.jsp",function(){});
	}
	
	//保存
	$("#catalogSave").click(function(){				
		if(!$('#catalogForm').valid()){alert('请正确填写表单!');return;}
		catalogForm.saveOrSubmit("save");
	})
	
	//关闭
	$("#catalogclose").click(function(){				
		$("#newCatalogDiv").html("");
	})
});