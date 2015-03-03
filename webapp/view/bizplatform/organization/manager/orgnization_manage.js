/**
 * 组织机构管理页面
 */
var OrgnizationManage = {};
OrgnizationManage.tree; //组织机构树

//点击节点事件
OrgnizationManage.nodeClick = function(id){
	var nodeLevel = OrgnizationManage.tree.getLevel(id); //获取节点层级

	if(nodeLevel == 1){ //显示机构信息
		$("#orgnizationDetail").hide();
		$("#orgnizationForm").hide();
		$("#companyDetail").show();
	}else if(nodeLevel == 2){ //显示部门信息
		$("#companyDetail").hide();
		$("#orgnizationForm").hide();
		$("#orgnizationDetail").empty().show().loadPage($('#initPath').val()+"/DepartmentController.do?method=toDepartmentDetailView&objId="+id);
	}else if(nodeLevel == 3){ //显示岗位信息
		$("#companyDetail").hide();
		$("#orgnizationForm").hide();
		$("#orgnizationDetail").empty().show().loadPage($('#initPath').val()+"/PostController.do?method=toPostDetailView&objId="+id);
	}
}

//新增部门
OrgnizationManage.addDepartment = function(){
	$("#companyDetail").hide();
	$("#orgnizationForm").empty().show().loadPage($('#initPath').val()+"/DepartmentController.do?method=toDepartmentFormView&companyId="+$("#companyId").val());
}
//新增岗位
OrgnizationManage.addPost = function(departmentId){
	$("#orgnizationDetail").hide();
	$("#orgnizationForm").empty().show().loadPage($('#initPath').val()+"/PostController.do?method=toPostFormView&departmentId="+departmentId);
}

//修改部门信息
OrgnizationManage.modifyDepartment = function(objId){
	$("#orgnizationDetail").hide();
	$("#orgnizationForm").empty().show().loadPage($('#initPath').val()+"/DepartmentController.do?method=toDepartmentFormView&objId="+objId);
}
//修改岗位信息
OrgnizationManage.modifyPost = function(objId){
	$("#orgnizationDetail").hide();
	$("#orgnizationForm").empty().show().loadPage($('#initPath').val()+"/PostController.do?method=toPostFormView&objId="+objId);
}

//删除部门信息
OrgnizationManage.deleteDepartment = function(objId){
	var msg = "确认删除该部门吗？";
	if(OrgnizationManage.tree.hasChildren(objId)){
		msg = "确认删除该部门以及该部门下的所有岗位吗？";
	}
	if(confirm(msg)){
		$.getJSON($('#initPath').val()+"/DepartmentController.do?method=removeDepartment",{objId:objId},function(json){
			if(json.success){
				if(OrgnizationManage.tree.hasChildren(objId)){
					OrgnizationManage.tree.deleteChildItems(objId);
				}
				OrgnizationManage.tree.deleteItem(objId, true);
				OrgnizationManage.nodeClick("-1");
			}
		});
	}
}
//删除岗位信息
OrgnizationManage.deletePost = function(objId){
	//判断是否存在兄弟节点
	var hasSibling = true;
	if(OrgnizationManage.tree.hasChildren(OrgnizationManage.tree.getParentId(objId)) == 1){
		hasSibling = false;
	}
	if(confirm("确认删除该岗位吗？")){
		$.getJSON($('#initPath').val()+"/PostController.do?method=removePost",{objId:objId,hasSibling:hasSibling},function(json){
			if(json.success){
				var pid = OrgnizationManage.tree.getParentId(objId);
				OrgnizationManage.tree.deleteItem(objId, true);
				OrgnizationManage.nodeClick(pid);
			}
		});
	}
}

//保存部门信息
OrgnizationManage.saveDepartment=function(objId){
	if(!$('#departmentForm').valid()){alert('请正确填写表单!'); return;}
	
	$('#departmentFormSave').attr('disabled', true);
	var url = $('#initPath').val()+'/DepartmentController.do?method=saveDepartment';
	$('#departmentForm').ajaxSubmit({
 		url: url,
 		data: {'company.objId':$("#companyId").val()},
		dataType: 'json',
		success:function(json){
			alert("操作成功！");
			if(objId==null || objId==''){ //新增
				OrgnizationManage.tree.insertNewItem('-1',json.departmentId,json.departmentName,0,0,0,0,'CLICK');
				OrgnizationManage.nodeClick("-1");
			}else{ //修改
				OrgnizationManage.tree.setItemText(json.departmentId,json.departmentName,'部门名称');
				OrgnizationManage.nodeClick(json.departmentId);
			}
		},
		error:function(msg){
			alert("操作失败！");
			$('#departmentFormSave').attr('disabled', false);
		}
	});
}
//保存岗位信息
OrgnizationManage.savePost=function(objId){
	if(!$('#postForm').valid()){alert('请正确填写表单!'); return;}
	
	$('#postFormSave').attr('disabled', true);
	var url = $('#initPath').val()+'/PostController.do?method=savePost';
	$('#postForm').ajaxSubmit({
		url: url,
		data: {'company.objId':$("#companyId").val()},
		dataType: 'json',
		success:function(json){
			alert("操作成功！");
			if(objId==null || objId==''){ //新增
				OrgnizationManage.tree.insertNewItem(json.departmentId,json.postId,json.postName,0,0,0,0,'CLICK');
				OrgnizationManage.nodeClick(json.departmentId);
			}else{ //修改
				OrgnizationManage.tree.setItemText(json.postId,json.postName,'岗位名称');
				OrgnizationManage.nodeClick(json.postId);
			}
		},
		error:function(msg){
			alert("操作失败！");
			$('#postFormSave').attr('disabled', false);
		}
	});
}

//新增或修改部门信息页面的返回
OrgnizationManage.departmentFormReturn = function(){
	OrgnizationManage.nodeClick("-1");
}
//新增或修改岗位信息页面的返回
OrgnizationManage.postFormReturn = function(){
	OrgnizationManage.nodeClick(OrgnizationManage.tree.getSelectedItemId());
}

//选择主管
OrgnizationManage.selectSupervisor = function(departmentId){
	var url =$('#initPath').val()+'/view/srplatform/auth/select_employee.jsp?dialogId=selectEmployeeDialog&Hname=supervisorName&Hid=supervisorId&companyId='+$("#companyId").val();
	if(departmentId!=null && departmentId!=''){
		url += '&departmentId='+departmentId;
	}
    $.epsDialog({
	    id: 'selectEmployeeDialog',
        title:'请选择主管',
        url:url
    });
}
//选择联系人
OrgnizationManage.selectContactor = function(departmentId){
	var url =$('#initPath').val()+'/view/srplatform/auth/select_employee.jsp?dialogId=selectEmployeeDialog&Hname=contactorName&Hid=contactorId&companyId='+$("#companyId").val();
	if(departmentId!=null && departmentId!=''){
		url += '&departmentId='+departmentId;
	}
    $.epsDialog({
	    id: 'selectEmployeeDialog',
        title:'请选择联系人',
        url:url
    });
}

//为部门和岗位授权
OrgnizationManage.authorizeOrgnization = function(orgnizationId){
	var url =$('#initPath').val()+'/BaseOrgnizationController.do?method=toOrgnizationAuthorizeRoleView&objId='+orgnizationId+'&companyId='+$("#companyId").val();
	$.epsDialog({
		title:'请选择角色',
		url:url,
		onClose: function(){
			OrgnizationManage.nodeClick(orgnizationId);
		}
	});
}

//初始化组织机构树
OrgnizationManage.initOrgnizationTree = function(companyId, companyName){
	OrgnizationManage.tree = new dhtmlXTreeObject("orgnizationTreeGrid","100%","100%",0);
	OrgnizationManage.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	OrgnizationManage.tree.setOnClickHandler(OrgnizationManage.nodeClick);
	OrgnizationManage.tree.setXMLAutoLoading("BaseOrgnizationController.do?method=getTree&order=sort&action=listById&isOpen=0&company.objId="+companyId);
	OrgnizationManage.tree.loadXML($("#initPath").val()+"/BaseOrgnizationController.do?method=getTree&action=listTop&order=sort&company.objId="+companyId,function(){
		OrgnizationManage.tree.setItemText("-1",companyName,companyName);
	});
}

$(document).ready(function(){	   
	OrgnizationManage.initOrgnizationTree($("#companyId").val(), $("#companyName").val());
	
	//返回到机构列表页面
	$('#orgnizationManageReturnBtn').click(function(){
		$('#conBody').loadPage($("#initPath").val()+'/BaseCompanyController.do?method=toCompanyAllList');
	});
});
