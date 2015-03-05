

$(document).ready(function(){
	
	
	$('#contractFile3').loadPage($('#initPath').val()+'/view/srplatform/upload/ftp_attachment.jsp',
		{
		defineSel:'contractFile3',//存放关联id的属性名
		maxSize:'1024',
		attachRelaId:'demo_ftp'
		}
	);
	
	
});
	
