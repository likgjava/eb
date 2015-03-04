
var InqpDocDetail={};
$(document).ready(function(){
	$("#downLoad").click(
			function(){
				window.open(''+$("#downAddr").text()+'');
			}
	)
				$("#downLoad2").click(
					function(){
						window.location.href=''+$("#downAddr2").text()+'';
					}
			)
			
			$('#congrousFactor').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toFactorTabShow&projectId="+$("input[name='projectId']").val());
});