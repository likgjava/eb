var obtainExpertList = {};

$(document).ready(function(){
	
	obtainExpertList.delMember=function(objId,i){//删除
		if(window.confirm("确定要删除?")){
			$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=removeWorkgMemberAndUser',{objId:objId},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$("#tName"+i).remove();
				alert('删除成功！');
				$('#obtain').attr("disabled",false);
			});
		}
	}

	obtainExpertList.isLeader=function(objId){//更新组长
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=updateWorkgMemberForIsLeader',{objId:objId},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
		});
	}
	
	obtainExpertList.isSubProject=function(workgMemberId,subProjectId) {//更新包组
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=updateWorkgMemberForSubProject',{workgMemberId:workgMemberId,subProjectId:subProjectId},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
		});
	}
	
	obtainExpertList.isAmoun=function(workgMemberId,isAmount){//更新正选专家
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=updateWorkgMemberForAmount',{'workgMemberId':workgMemberId,'isAmount':isAmount},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
		});
	}
	
	//获取
	$("#obtain").click(function(){
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=getExpertRule',{"subProjectId":$('#exper_subProjectId').val(),"workId":$('#workId').val()},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
//    		var subProjectS = json.subProjectList;
    		if("[]" != json.expertRule){
    			var html = '';
    			$.each(json.expertRule,function(i,n){
        			html += '<tr id=tName'+i+'>';
        				html += '<td style="text-align:center;">'+n.workgmName+'</td>';
        				html += '<td style="text-align:center;">'+n.workgmDuty+'</td>';
        				html += '<td style="text-align:center;">'+n.workgmSpeciality+'</td>';
        				html += '<td style="text-align:center;">'+n.workgmAccount+'</td>';
        				html += '<td style="text-align:center;">'+n.workgmPassWord+'</td>';
        				html += '<td style="text-align:center;">'+n.linkerPhone+'</td>';
        				html += '<td style="text-align:center;"><input type="radio" name="radioLeader"'; 
        				if(n.workgmIsLeader==01){//是否为组长
        					html +='checked="checked"';
        				}
        				html += 'class="expert_radioLeader" onClick="obtainExpertList.isLeader(\''+n.objId+'\')"/></td>';
//        				html += '<td style="text-align:center;"><input type="checkbox" name="radioAmoun'+i+'"'; 
//        				if(n.isAmount==00){//是否为正选
//        					html +='checked="checked"';
//        				}
//        				html += 'class="expert_radioLeader" onClick="obtainExpertList.isAmoun(\''+n.objId+'\',\'00\')"/></td>';
//        				html += '<td style="text-align:center;"><input type="checkbox" name="radioAmoun'+i+'"'; 
//        				if(n.isAmount==01){//是否为备选
//        					html +='checked="checked"';
//        				}
//        				html += 'class="expert_radioLeader" onClick="obtainExpertList.isAmoun(\''+n.objId+'\',\'01\')"/></td>';
        				html += '<td style="text-align:center;"><button id="'+i+'" objId="'+n.objId+'" onClick="obtainExpertList.delMember(\''+n.objId+'\',\''+i+'\')">删除</button></td>';
        			html += '</tr>';
        		});
    			$("#obtainExpertTable").empty().append(html);
    			$('#obtain').attr("disabled",true);
    		}
    	});
		
	});

});
