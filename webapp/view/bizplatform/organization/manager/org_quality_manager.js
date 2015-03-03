/*
 * 供应商资质列表(load用)
 * created by yucy
 */

var orgQulityManager={};

//重新定向页面
orgQulityManager.reload = function(){
	$('#conBody').loadPage($('#initPath').val()+"/OrgQualityController.do?method=toOrgQualityManageView");
}

//修改
orgQulityManager.update = function(id){
	$.epsDialog({
	    title:'资质信息',
	    url:$('#initPath').val()+'/OrgQualityController.do?method=toCreateOrUpdateOrgQuality&objId='+id,
	    width: '800',
	    height: '400',
	    onClose: function(){
			$('#selectDivList_tree').slideUp('fast',function(){$('#selectDivList_tree').remove();});//关闭树形下拉框
		}
	});
}

//删除
orgQulityManager.del = function(id){
	if(confirm('确认删除!')){
		$.getJSON($('#initPath').val()+'/OrgQualityController.do?method=removeQualificationInfo',{"quolityIds":id},function(json){
			if(json.success){
				orgQulityManager.reload();
			}else{
				alert("删除失败!");
			}
		})
	}
}

$(document).ready(function(){
	
	//添加附件事件
	$.each($("body").find("a[name=qualificationFile]"),function(index,obj){
		$(obj).click(function(){
			$.epsDialog({
				title:"附件下载",
				url:$("#initPath").val()+"/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId="+obj.id,
				width: 600,
				height: 300
				});
		})
	})
	
	//新增资质
	$("#addQuality").click(function(){
	    $.epsDialog({
	    	id:'updateQuality',
	        title:'资质信息',
	        url:$('#initPath').val()+'/OrgQualityController.do?method=toCreateOrUpdateOrgQuality',
	        width: '800',
	        height: '400',
	        onClose: function(){
	    		$('#selectDivList_tree').slideUp('fast',function(){$('#selectDivList_tree').remove();});//关闭树形下拉框
    		}
	    });
	})
	
});