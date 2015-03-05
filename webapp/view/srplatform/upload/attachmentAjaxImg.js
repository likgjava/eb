
var attachmentAjaxImg={};
attachmentAjaxImg.del= function(id,i){
	if(window.confirm("确认要删除该附件吗？")){
		$.getJSON($('#initPath').val()+'/AttachmentController.do?method=delectAjax',{objId:id},function(json){
			if(json.failtrue){
				alert(json.result);
				return;
			}
			//释放上传按钮
			$('#form_'+i).find('input').removeAttr('disabled');
			
			//销毁提示
			$("#newPreview"+i+"").find("img").qtip('destroy');			
			
			//显示初始图片
			setTimeout("attachmentAjaxImg.showInitImg('"+i+"')", 100);//设定一点延迟保证程序稳定
			
			//清空文件框
			$('#form_'+i).find('input').val('');
			//消除附件ID值
			$('#form_'+i).find('input').attr('attachid','');
		})
	}
}


//显示初始的图片
attachmentAjaxImg.showInitImg = function(i){
	//清空图片预览
	$("#newPreview"+i+"").find("img").attr("src",$("input[name=nopicPath]").val() );	
}

var maxWidth = $("input[name=maxWidthM]").val();
var maxHeight = $("input[name=maxHeightM]").val();

//初始
initChange = function(inputId, imgId){
	$("#attachFile_"+inputId).change(function(){
		new ImagePreview( 
				$$( "attachFile_"+inputId ), 
				$$( "tempImgM_"+imgId ), 
				{
					maxWidth: maxWidth, 
					maxHeight: maxHeight, 
					action: "viewImg.jsp",
					onShow:function(){
						setTimeout("attachmentAjaxImg.checkSize('"+inputId+"','"+imgId+"')", 100);
					}
				}
		).preview();
	})
}

//检测尺寸
attachmentAjaxImg.checkSize = function(inputId,imgId){
    var img = $("#tempImgM_"+imgId);
    var img_width = img.width();
    var img_height = img.height();

    //限制比例
	if( img_width/img_height != maxWidth/maxHeight ){
    	alert("图片不符合标准,请上传系统默认尺寸("+maxWidth+":"+maxHeight+")或同比例的图片");
	}else{
		
		$('#form_'+inputId).ajaxSubmit({
			url:$('#initPath').val()+'/AttachmentController.do?method=upLoadAjax&maxSize='+$('input[name=maxSize]').val()+'&attachRelaID='+$('input[name=attachRelaID]').val()+'&reducedWidth='+$('input[name=reducedWidth]').val()+'&reducedHeight='+$('input[name=reducedHeight]').val()/*+'&pic_WH_rule_str='+$('input[name=pic_WH_rule_str]').val()*/,
			dataType:'json',
			success:function(json){
			
				if(json.failtrue){
					alert(json.result);
					return;
				}
				$('input[name=attachRelaID]').val(json.relationId).prev('input').val(json.relationId);
				$('#submitRelaId').val(json.relationId)
				
				//屏蔽上传(删除后释放)
				$('#form_'+inputId).find('input').attr('disabled','disabled');
				
				//图片预览
				//$("#newPreview"+inputId+"").find("img").attr("src",img.attr("src") );
				//$("#newPreview"+inputId+"").find("img").attr("style",img.attr("style") );
				
				$("#newPreview"+inputId+"").find("img").replaceWith( img.clone().attr("id", $("#newPreview"+inputId+"").find("img").attr("id") ).attr("class","show") );
				
				//提示
				$("#newPreview"+inputId+"").find("img").qtip({
				    content: '<a href="javascript:void(0);" onclick="attachmentAjaxImg.del(\''+json.attachmentId+'\',\''+inputId+'\');" style="color:#68BADA;font-weight:blod;">删除</a>', // Give it some content
				    position: 'topRight', // Set its position
				    hide: { fixed: true /* Make it fixed so it can be hovered over*/},
				    style: {
				       padding: '1px 5px', /*Give it some extra padding*/
				       name: 'dark' /*And style it with the preset dark theme*/
				    }
				});
			},
			error:function(msg){
				alert(JSON.stringify(msg));
			}
		});
    }
}

$(document).ready(function(){
	
	//每个都初始化
	$.each($("#imgUploadUL").find("input"),function(index,obj){
		initChange(index, index );
	})
});