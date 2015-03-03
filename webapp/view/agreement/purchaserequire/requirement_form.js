requirement_form = {}
var htmlEditor;

//返回重填类目
requirement_form.backTochooseCategory = function(){
	document.location.href = $("#initPath").val()+"/RequirementController.do?method=toChooseCategory&categoryvalues="+strIgnore( $("#categoryvalues").val() ) 
	+"&categoryNames="+ native2ascii(strIgnore( $("#categoryNames").html().replace(/&gt;/g,'>') ));
}

//选择使用区域
requirement_form.selectDistrict = function(){
	
    $.epsDialog({
    	id:'districtDiv',
        title:'选择使用区域',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=district&className=District&action=listTop&isOpen=1&childNodeOnly=true&andAllParent=true&dialogId=districtDiv'
        ,onClose:function(){
        	if( $("input[name=districtId]").val() ){
        		var ids = $("input[name=districtId]").val().split(",");
        		$("input[name=districtId]").val( ids[ids.length-1] );
        		//如果是搜索出来的区域，则补充省和市
        		if(ids.length == 1){
        			$.getJSON($("#initPath").val()+"/DistrictController.do?method=getObjectQuery&queryColumns=objId,name,parent.name,parent.parent.name",{"objId":ids},function(json){
        				var districtObj = json.result[0];
        				var districtName = json.result[0]['parent.parent.name']+','+json.result[0]['parent.name']+','+json.result[0]['name'];
        				$("input[name=districtNames]").val(districtName);
        			});
        		}
        	}
        }
    }); 
}

//同意和发布
requirement_form.agreedAndPublish = function(){
	$("#discription").val(htmlEditor.getValue());
	if(!$('form[id=requirementForm]').valid()){alert('请正确填写表单！');return;}
	var requirement = formToJsonObject("requirementForm");
	var endTime = new Date();
	endTime.setDate( gpcsoftDate.getDate()+ Number( requirement.endTime ) ); 
	requirement.endTime = 	endTime.getFullYear()+"-"+ (endTime.getMonth()+1) +"-" + endTime.getDate() 

	if($("input[name=picture]").val()!="picture" && $("input[name=picture]").val()!=""){
		requirement.picture = $("input[name=picture]").val();
	}
	
	var categoryvaluesArray =  $("#categoryvalues").val().split(",");
	requirement["category.objId"] = categoryvaluesArray[categoryvaluesArray.length-1];
	$.getJSON($("#initPath").val()+"/RequirementController.do?method=saveRequirement",requirement,function(json){
		if(json.success){
			document.location.href = $("#initPath").val()+"/RequirementController.do?method=toSaveSuccess"; 
		}
	})
}

//我要反馈
requirement_form.addSelfAdvise = function(){
	var url = $('#initPath').val()+"/AdviseController.do?method=toCreateAdvise&isDialog=true";
	$.epsDialog({
		title:'我要反馈',
		url:url
	});
}

$(document).ready(function(){
	
	//验证
	$("#requirementForm").validate();

	//加载ExtJs的HTML编辑器
	htmlEditor = new Ext.form.HtmlEditor({
		height: 340,
		width: 687,
		renderTo: 'htmlEditor'
	});
	htmlEditor.setValue(" ");
	
	//图片上传控件
	$('#additionPicture').loadPage($('#initPath').val()+'/AttachmentController.do?method=toUploadAjaxImg',{
			defineSelf:'picture',//存放关联id的属性名
			maxSize:'1024',
			fileType: 'gif|jpeg|jpg|bmp|png',
			quantity:'3',//附件最大数
			attachRelaId:'',
			isView:'0',//是否只是显示图片不删除和上传
			reducedWidth:400,	
			reducedHeight:400,
			maxWidth:80,//建议长
			maxHeight:80,//建议宽
			nopicPath:'AttachmentController.do?method=showImg'//提示图片  可以是AttachmentController.do?method=showImg
	});
	
})