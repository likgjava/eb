
var signUprecordForm={};

$(document).ready(function(){
	
	signUprecordForm.getSignUprecordForm = function(projectId,orgInfoId){
		$.epsDialog({
			title:'我要报名',
			url:$('#initPath').val()+'/SignUprecordController.do?method=toPage&projectId='+projectId+'&orgInfoId='+orgInfoId,
			width: '850',
			height: '350',
			isReload:false,
			onClose: function(){
		}
		});	
	};
	signUprecordForm.supplierDetail = function(objId){
		$.epsDialog({
			title:'报名信息',
			url:$('#initPath').val()+'/SignUprecordController.do?method=toViewSignupPage&objId='+objId,
			width: '900',
			height: '350',
			isReload:false,
			onClose: function(){
		}
		});	
	};
});