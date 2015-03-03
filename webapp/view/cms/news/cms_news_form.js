var cmsNewsForm={};
var channelId = $("#channelId").val();
var channelName = "";
var channelModelId = "";
var checkStr = "";
var htmlEditor;

//创建HTML编辑器
cmsNewsForm.createHtmlEditor = function(content){
	//加载ExtJs的HTML编辑器
	htmlEditor = new Ext.form.HtmlEditor({
		height: 300,
		width: 640,
		anchor: '100%'
	});
	new Ext.panel.Panel({
	    renderTo: 'htmlEditor',
	    layout: 'anchor',
	    border : false,
	    items: [htmlEditor]
	});
	htmlEditor.setValue(content+" ");
}

$(document).ready(function(){
	$('#cmsNewsForm').validate();
	
	$.ajax({
		url: $("#initPath").val()+"/ChannelController.do?method=getObjectQuery&includedProperties=&queryColumns=objId,name,isLeaf,channelModel.objId,channelModel.name,checkCount",
		type:"POST",
		data:{objId:channelId},
		async:false,
		dataType:'json',
		success:function(msg){
			if(parseInt(msg.result[0].checkCount)>0){
				checkStr="并提交到一级审核！";
			}
			channelName = msg.result[0].name;
			$("[name=channelName]").html("当前栏目："+channelName+"");
			channelModelId = msg.result[0]["channelModel.objId"];
		}
	})
	
	if(undefined == channelModelId || "" == channelModelId || "undefined" == channelModelId){
		alert(channelName +"栏目所对应的模型不存在，请先为该栏目选择模型！");
		$('#conBody').loadPage($('#initPath').val()+"/ChannelController.do");
		return false;
	}
	
	var queryColumns="";
	var ChannelModelItemJson=$.ajax({url:"ChannelModelItemController.do?method=getObject&queryColumns="+queryColumns,data:{"order":"sort","channelModel.objId":channelModelId,"display":true}, async: false}).responseText;
	var ChannelModelJsonObj = JSON.parse(ChannelModelItemJson).list;
	var ChannelModelItemList = "";
	$(ChannelModelJsonObj).each(function(i,n){
		var required_str = "";
		var eleRequired_str = " ";
		if(n.required=="true"){
			required_str = "required";
			eleRequired_str = "<span class='eleRequired'>*</span>";
		}
		var isCheckbox = "false";
		if("checkbox"==n.formType && ""!=n.keyVal){
			isCheckbox = "true";
		}
		if("img"==n.formType || "file"==n.formType){
			ChannelModelItemList += "<li class='fullLine'>";
		}else if("textarea"==n.formType){
			var textareaHeight = (""!=n.textareaRows && n.textareaRows>=50)?(parseInt(n.textareaRows))+15:65;
			ChannelModelItemList += "<li class='fullLine' isCheckbox='"+isCheckbox+"' style='height:"+textareaHeight+"px'>";
		}else{
			if(n.fullLine){
				ChannelModelItemList += "<li class='fullLine' isCheckbox='"+isCheckbox+"'>";
			}else{
				ChannelModelItemList += "<li isCheckbox='"+isCheckbox+"'>";
			}
		}
		ChannelModelItemList += "<label for='"+n.label+"'>"+n.label+"：</label>";
		
		if("content"==n.name){
			ChannelModelItemList += "<div id='htmlEditor' class='noCSS' style='float:left;'></div>";
			ChannelModelItemList += "<textarea  name='newsContent' id='FCKContent_content'  class='hidden "+required_str+"'></textarea>";
			ChannelModelItemList += "<input type='hidden' name='content' id='content'/>";
		}else if("" != n.dataType && undefined != n.dataType && "Date"== n.dataType){
			ChannelModelItemList += "<input type='text' name='"+n.name+"' id='"+n.name+"' class="+required_str+" />";
		}else if("" != n.dataType && undefined != n.dataType && "Boolean"== n.dataType){
			ChannelModelItemList += "<input type='checkbox' name='"+n.name+"' id='"+n.name+"' value='true' />";
		}else{
			var keyValArray="";
			if("textarea"==n.formType){
				var textareaHeight = (""!=n.textareaRows && n.textareaRows>=50)?n.textareaRows:50;
				var textareaWidth = (""!=n.textareaCols && n.textareaCols>75)?75:n.textareaCols;
				ChannelModelItemList += "<textarea name='"+n.name+"' id='"+n.name+"'   class='"+required_str+"' style='width:"+textareaWidth+"%;height:"+textareaHeight+"px'></textarea>";
			}else if("select"==n.formType && ""!=n.keyVal && undefined != n.keyVal){
				ChannelModelItemList += "<select name='"+n.name+"' id='"+n.name+"'>";
				keyValArray = n.keyVal.split(",");
				for(var i = 0; i < keyValArray.length; i++){
					ChannelModelItemList += "<option value='"+keyValArray[i].split("#")[1]+"'>"+keyValArray[i].split("#")[0]+"</option>";
				}
				ChannelModelItemList += "</select>";
				
			}else if("radio"==n.formType && ""!=n.keyVal && undefined != n.keyVal){
				keyValArray = n.keyVal.split(",");
				for(var i = 0; i < keyValArray.length; i++){
					ChannelModelItemList += "<input type='radio' name='"+n.name+"' value='"+keyValArray[i].split("#")[1]+"'/>"+keyValArray[i].split("#")[0]+"&nbsp;";
				}
			}else if("checkbox"==n.formType && ""!=n.keyVal && undefined != n.keyVal){
				keyValArray = n.keyVal.split(",");
				for(var i = 0; i < keyValArray.length; i++){
					ChannelModelItemList += "<input type='checkbox' name='"+n.name+"' value='"+keyValArray[i].split("#")[1]+"'/>"+keyValArray[i].split("#")[0]+"&nbsp;";
				}
			}else if ("img" == n.formType){
				ChannelModelItemList += "<div id='imageShow'></div>" ;
				ChannelModelItemList += "<input type='file' name='upload_"+n.name+"' size='50' ><input type='hidden' name='"+n.name+"' value='' ><button id='uploadImg' type='button' class='btn'>上传</button><button id='deleteImg' type='button' class='btn'>清空</button>";
			}else if ("file" == n.formType){
				ChannelModelItemList += "<div id='fileDownload_"+n.name+"' name='fileDownload'></div>" ;
				ChannelModelItemList += "<input type='file' name='upload_"+n.name+"' size='50' ><input type='hidden' name='"+n.name+"' value='' ><button id='uploadFile' type='button' class='btn'>上传</button><button id='deleteFile' type='button' class='btn'>清空</button>";
			}else{
				var textWidth = "";
				if(n.fullLine){
					textWidth = (("" != n.textLength && n.textLength>75)?75:n.textLength);
					ChannelModelItemList += "<input type='text' name='"+n.name+"' id='' value='' style='width:"+textWidth+"%' class='"+required_str+"'>";
				}else{
					textWidth = (("" != n.textLength && n.textLength<=40)?n.textLength:40);
					ChannelModelItemList += "<input type='text' name='"+n.name+"' id='' value='' style='width:"+textWidth+"%' class='"+required_str+"'>";
				}
			}
		}
		ChannelModelItemList += eleRequired_str;
		if(undefined != n.help)
		ChannelModelItemList += "<i>"+n.help+"</i>";
		ChannelModelItemList += "</li>";
	})
	
	$("#cmsNewsFormUl").empty().append(ChannelModelItemList).find("li").each(function(){
		if($(this).find("div[id=imageShow]").length>0){
			$("div[id=imageShow]").html("上传后可预览图片！");
			$("div[id=imageShow]").parent("li").css({"height":"70px"})
		}
		if($(this).find("input[name=content]").length>0){
			$(this).find("input[name=content]").parent("li").css({"height":"320px"})
		}
	}).end().find("li>button[id=uploadImg]").click(function(){
		$("#uploadFileName").val($(this).parent().find("input[type=file]").attr("name"));
		cmsNewsForm.uploadFile();
	}).end().find("li>button[id=uploadFile]").click(function(){
		$("#uploadFileName").val($(this).parent().find("input[type=file]").attr("name"));
		cmsNewsForm.uploadFile();
	}).end().find("li>button[id=deleteImg]").click(function(){
		$(this).parent().find("div[id=imageShow]").html("上传后可预览图片！");
		$(this).parent("li").css({"height":"70px"});
		$(this).parent().find("input").val("");
	}).end().find("li>button[id=deleteFile]").click(function(){
		$(this).parent().find("div[name=fileDownload]").html("");
		$(this).parent("li").css({"height":"auto"})
		$(this).parent().find("input").val("");
	})
	
	// 初始化所有的日期样式
	$("#date1").epsDatepicker()
	$("#date2").epsDatepicker()
	$("#date3").epsDatepicker()
	
	if($('#newsId').val()!=''){
    	
    	$.getJSON($('#initPath').val()+'/CmsNewsController.do?method=createOrUpdate',{objId:$('#newsId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('cmsNewsForm', json.cmsNews);
    		
    		$("#cmsNewsFormUl").find("div[id=imageShow]").each(function(){
    			var inputName = ($(this).parent().find("input[type=file]").attr("name")).replace("upload_","");
    			if(""!=json.cmsNews[inputName] && undefined != json.cmsNews[inputName]){
    				$(this).parent().find("div[id=imageShow]").empty().append("<img src='"+json.cmsNews[inputName]+"' width='200px' height='150px' /> ");
    				$(this).parent().css({"height":"200px"})
    			}
    		})
    		$("#cmsNewsFormUl").find("div[name=fileDownload]").each(function(){
    			var inputName = ($(this).parent().find("input[type=file]").attr("name")).replace("upload_","");
    			if(""!=json.cmsNews[inputName] && undefined != json.cmsNews[inputName]){
    				$(this).parent().find("div[name=fileDownload]").empty().append("<a href='"+json.cmsNews[inputName]+"' target='_blank' title='下载'>点击下载附件</a>");
    				$(this).parent().css({"height":"50px"})
    			}
    		})
    		if($("#cmsNewsFormUl").find("li>textarea[id=FCKContent_content]").length>0){
    			var newsContent = "";
	  			if(""!=json.cmsNews.content && undefined != json.cmsNews.content){
	  				$.ajax({
	  					url:$("#initPath").val()+"/CmsNewsController.do?method=getNewsContent",
	  					type:"POST",
	  					data:{"fileName":json.cmsNews.content},
	  					async: false,
	  					dataType:'json',
	  					success:function(content){
	  						cmsNewsForm.createHtmlEditor(content.content)
	  					}
	  				})
	  			}else{
	  				cmsNewsForm.createHtmlEditor("");
	  			}
    		}
    	});
    }else{
    	if($("#cmsNewsFormUl").find("li>input[name=content]").length>0){
    		cmsNewsForm.createHtmlEditor("");
    	}
    }
	
	// 返回
	$('#cmsNewsReturn').click(function(){
		if($('#returnSelf') && $('#returnSelf').val()=='true'){
			$('#conBody').loadPage($('#initPath').val()+"/CmsNewsController.do?channelId="+channelId);
		}else{
			$('#conBody').loadPage($('#returnUrl').val());
		}
	});
	
	// 提交
	$('#cmsNewsSave').click(function(){
		//让编辑器的内容回填到文本域
		$("#FCKContent_content").val(htmlEditor.getValue());
		var cmsNews = formToJsonObject('cmsNewsForm');
		$('#cmsNewsSave').attr("disabled",true);
		$('#cmsNewsSave>span').html("正在保存，并发布静态页面...");
		
		// 获得复选框选中的值
		$.each($("#cmsNewsFormUl").find("li[isCheckbox=true]"),function(){
			var name = $(this).find(":checkbox").attr("name");
			var value="";
			$(this).find("input[type=checkbox]").each(function(i,n){
				if($(n).attr("checked")){
					if("" == value){
						value = $(n).val();
					}else{
						value = value+","+$(n).val();
					}
				}
			})
			cmsNews[name] = value;
		})
		if(!$('#cmsNewsForm').valid()){
			alert('请正确填写表单!');
			$('#cmsNewsSave').attr("disabled",false);
			$('#cmsNewsSave>span').html("保存");
			return false;
		}
		$.getJSON($('#initPath').val()+'/CmsNewsController.do?method=saveCmsNews', cmsNews, function(json){
			if(json.result=="true"){
				alert("发布成功！"+checkStr);
			}else{
				alert("保存或生成静态页面发生错误，请重试！");
				$('#cmsNewsSave').attr("disabled",false);
				$('#cmsNewsSave>span').html("保存");
				return false;
			}
			$('#conBody').loadPage($('#returnUrl').val());
		});
	});
});

// 表单提交用于上传附件
cmsNewsForm.uploadFile = function(){
	$("#cmsNewsForm").ajaxSubmit({
	 	url:$('#initPath').val()+'/CmsNewsController.do?method=upLoadFile',
		dataType:'json',
		success:function(json){
			if(json.failure){alert(json.result);return;}
			if(json.result=="true"){
				var liEle = $("#cmsNewsFormUl").find("li>input[type=file][name="+$("#uploadFileName").val()+"]").parent();
				if("img"==json.fileType){
					$(liEle).find("div[id=imageShow]").empty().append("<img src='"+json.showUpload+"' width='200px' height='150px' /> ");
					$(liEle).css({"height":"200px"})
					$(liEle).find("input[type=hidden]").val(json.filePath);
				}else{
					$(liEle).find("div[name=fileDownload]").empty().append("<a href='"+json.showUpload+"' target='_blank' title='下载'>点击下载附件</a> ");
					$(liEle).css({"height":"50px"})
					$(liEle).find("input[type=hidden]").val(json.filePath);
					
				}
			}else{
				alert("上传失败！")
			}
			$("#uploadFileName").val("");
							},
		error:function(msg){
			alert(JSON.stringify(msg));
		}
	})
}
