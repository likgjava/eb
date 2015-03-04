
var congruousFactorForm={};

$(document).ready(function(){
	//返回
	$('#back').click(function(){
		$('#epsDialogCloseNoReload').click();
		$("#congruousFactorGrid").reload();
	});
	//提交
	$('#congruousFactorSave').click(function(){
		if(!$('#congruousFactorForm').valid()){return false;}
		var checkBoxs = new Array();
		var projNames = new Array();
		if($('[id=projId]').length > 0){
			$.each($('[id=projId]'),function(i,n){
				if(n.checked){
					checkBoxs.push($(n).val());
					projNames.push($(n).attr("projName"));
				}
			})
			if(checkBoxs.length<1){
				alert("请选择适用包件.");
				return false;
			}
		}else{
			checkBoxs.push($('#PROJ_ID').val());
			projNames.push($('#PROJ_NAME').val());
		}
		if(checkBoxs.length>0){
			var jsonObj = formToJsonObject('congruousFactorForm');
			jsonObj.projName = projNames.toString();
			jsonObj.projId = checkBoxs.toString();
			jsonObj.projIds = checkBoxs.toString();
			$.getJSON($('#initPath').val()+'/CongruousFactorController.do?method=updateCongruousFactor',jsonObj, function(json){
				if(json.failure){alert(json.result);return;}
				$('#epsDialogCloseNoReload').click();
				$("#congruousFactorGrid").reload();
			});
		}
	});
});
