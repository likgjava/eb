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
	
	//拼装区域id串
	catalogJson.districtString =  $("input[id=district.objId]").val();
	
	$.getJSON($('#initPath').val()+'/PurCatalogController.do?method=savePurCatalog',
			catalogJson,
			function(json){
				if(json.success&&saveType=="save"){
					//回设objId
					$("#objId").val(json.purCatalog.objId);
					//隐藏按钮
					$("#catalogSave").hide();$("#saveSecond").show()
					//load 目录明细页面
					alert('保存成功！');
					if($("#catalogDetailTree").html()==null||$("#catalogDetailTree").html()==""){
						$("#catalogDetailTree").loadPage($("#initPath").val()+"/view/bizplatform/base/purcatalog/catalog_detail_form.jsp",function(){
							$("#catalogDetailTree").toggle("slow");
						});
					}
				}else if(json.success&&saveType=="submit"){
					alert('提交成功！');
					$('#conBody').loadPage($('#initPath').val()+"/PurCatalogController.do");
				}else{
					alert('操作失败！');
				}
			}
		);
}

$(document).ready(function(){
	
	//初始化发布日期
	$("#relDate").epsDatepicker();
	
	//初始化年度
	var currentYear = $("#year").val();
	var year = (new Date()).getFullYear(); 
	var checkedIndex = 1;
	for(var i = -2; i <= 5; i++){
		if(currentYear != null && currentYear != ""){
			year = parseInt(currentYear);
			checkedIndex = 0;
		}
		var checkedYear = "";
		if(i == checkedIndex){
			checkedYear = "selected='selected'";
		}
		$("select[name=year]").append("<option value='" + (year + i) + "'" +checkedYear+ ">" + (year + i) + "</option>");
	}
	
	
	
	//选择区域
    $('input[id=district.name]').click(function(e){
	    $.epsDialog({
	        title:'请选择区域',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=district&className=District&action=listTop&isOpen=1&isCheckBox=true'
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
	
	//第n次保存
	$("#saveSecond").click(function(){				
		if(!$('#catalogForm').valid()){alert('请正确填写表单!');return;}
		catalogForm.saveOrSubmit("save");
	})
	
	//提交
	$("#catalogSubmit").click(function(){				
		if(!$('#catalogForm').valid()){alert('请正确填写表单!');return;}
		if(null==$("#objId").val()||""==$("#objId").val()){
			alert("目录下将无任何详细内容,请保存后添加");
			return;
		}
		//确认提交
		$.getJSON($('#initPath').val()+'/PurCatalogController.do?method=getSubmitConfrim',{"objId":$("#objId").val()},function(json){
			if(json.flag=="true"){
				if(confirm('确认提交？')){
					catalogForm.saveOrSubmit("submit");
				}
			}else{
				alert("目录下将无任何详细内容,请保存后添加");
			}
		});
	})
	
	//返回
	$("#catalogReturn").click(function(){				
		$('#conBody').loadPage($('#initPath').val()+"/PurCatalogController.do");
	})
});