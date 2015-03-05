

$(document).ready(function(){
	
	$('#contractForm').validate()
	var contractFile_value ='demo';
	$('#contractFile').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxImg.jsp',
		{
		defineSel:'contractFile',//存放关联id的属性名
		maxSize:'1024',
		fileType: 'gif|jpeg|jpg|bmp|png',
		quantity:'4',//附件最大数
		attachRelaId:contractFile_value
		}
	);
	
	$('#contractFile2').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',
		{
		defineSel:'contractFile2',//存放关联id的属性名
		maxSize:'1024',
		attachRelaId:'demo2',
		readOnly:'no'
		}
	);
	
	$('#save').click(function(){
		if(!$('#contractForm').valid()){alert('请正确填写表单!');return;}
	})
	
});
	
