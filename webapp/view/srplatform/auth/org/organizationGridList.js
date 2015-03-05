var organizationList={};//公共方法变量名

var organizationGridList={};
organizationGridList.currentOrgId='';
if(PlatForm.user.usrIsAdmin!='2'){
	organizationGridList.currentOrgId = PlatForm.user.emp.company.objId;
}

organizationGridList.addChildCom = function(name,grid){
//	if(organizationGridList.validation('',$('#organizationGrid'))){
//		var id = $('#organizationGrid').getSelect();
//		var json = $("#organizationGrid").flexGetRowJsonById(id);
//		organizationParentShortName = json.shortName;//初始化父级机构中文名称
//		if(json['type_value']=='1'){
//			$('#conBody').loadPage($('#initPath').val()+'/CompanyController.do?method=toCreateOrUpdate&model=grid&parentShortName='+json['shortName']+'&parentId='+id);
//		}else{
//			alert('只有公司才可以新增下级公司！');
//		}
//		
//	}

	if(organizationGridList.validation('',$('#organizationGrid'))){
		//创建采购人/供应商
		$('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/organization/manager/create_orginfo.jsp?parentCompanyId="+$('#organizationGrid').getSelect());
	}

}
organizationGridList.addChildDep = function(){
	if(organizationGridList.validation('',$('#organizationGrid'))){
		var id = $('#organizationGrid').getSelect();
		var json = $("#organizationGrid").flexGetRowJsonById(id);
		organizationParentShortName = json.shortName;//初始化父级机构中文名称
		$('#conBody').loadPage($('#initPath').val()+'/DepartmentController.do?method=toCreateOrUpdate&model=grid&parentShortName='+json['shortName']+'&parentId='+id);
	}
}
organizationGridList.modify = function(){
	if(organizationGridList.validation('',$('#organizationGrid'))){
		var id = $('#organizationGrid').getSelect();
		var json = $("#organizationGrid").flexGetRowJsonById(id);
		if(json['type_value']=='1'){
			$('#conBody').loadPage($('#initPath').val()+'/CompanyController.do?method=toCreateOrUpdate&model=grid&objId='+id);
		}else if(json['type_value']=='2'){
			$('#conBody').loadPage($('#initPath').val()+'/DepartmentController.do?method=toCreateOrUpdate&model=grid&objId='+id);
		}
	}
}
organizationGridList.changeParent = function(){
	if(organizationGridList.validation('',$('#organizationGrid'))){
		var id = $('#organizationGrid').getSelect();
		var json = $("#organizationGrid").flexGetRowJsonById(id);
		 $.epsDialog({
		            title:'请选择上级机构',
		            url:$('#initPath').val()+'/view/srplatform/auth/org/change_parent_org.jsp?orgId='+id+'&oldPid='+json['parent.objId']+'&roleOrgId='+organizationGridList.currentOrgId,
		            width: '600',
		            height: '360',
		            hasBg:true,//背景
		            fadeTo:80 //透明度
		 });
	}
}

organizationList.delOrg = function(id){
	if(id==$("#currentCompany").val()){alert("不能删除本机构！");return;}
	if(window.confirm("确定删除?")){
			$.ajax({
					url:"OrganizationController.do?method=deleteOrg",
					type:"POST",
					data:{ids:id},
					success:function(json){
						if(json.result)alert(json.result,{inco:'info'});;
						if(json.failure)return;
						$("#organizationGrid").reload();
					},error:function(json){
						alert(json)
					}
			})
	}
	
}
//获取右键 新增下级菜单,type 机构类型   btnsArea动态按钮的区域div
organizationList.getOnRightMenu = function(type,menuArea,id,pname){
	if(type=='1'){
		//menuArea.append("<li ><a href='CompanyController.do?method=toShowView&model=grid&objId="+id+"' target='#conBody' class='look'  ><span></span>查看详细信息</a></li>");
		menuArea.append("<li ><a href='CompanyController.do?method=toCreateOrUpdate&model=grid&objId="+id+"' target='#conBody' class='modify'  ><span></span>修改机构信息</a></li>");
		menuArea.append(organizationList.onRightMenuHtml('1',id,pname));
		menuArea.append(organizationList.onRightMenuHtml('2',id,pname));
		
	}
	else if(type=='2'){
		//menuArea.append("<li ><a href='DepartmentController.do?method=toShowView&model=grid&objId="+id+"' target='#conBody' class='look'  ><span></span>查看详细信息</a></li>");
		menuArea.append("<li ><a href='DepartmentController.do?method=toCreateOrUpdate&model=grid&objId="+id+"' target='#conBody' class='modify'  ><span></span>修改机构信息</a></li>");
		menuArea.append(organizationList.onRightMenuHtml('2',id,pname));
	}
	menuArea.append("<li ><a href='#' target='#' onclick='organizationList.delOrg(\""+id+"\");return false;' class='del'  ><span></span>删除本机构</a></li>");
	
//	$.getJSON($("#initPath").val()+"/OrganizationController.do?method=getOrgRules",{type:type},function(json){
//		var rule = json.result;
//		if(rule.length>0){
//			for(var i=0;i<rule.length;i++){
//				menuArea.prepend(organizationList.onRightMenuHtml(rule[i],id,pname));
//			}
//		}
//		if(type=="1"){
//			menuArea.append("<li ><a href='CompanyController.do?method=toShowView&model=grid&objId="+id+"' target='#conBody' class='look'  ><span></span>查看详细信息</a></li>");
//		}
//		else if(type=="2"){
//			menuArea.append("<li ><a href='DepartmentController.do?method=toShowView&model=grid&objId="+id+"' target='#conBody' class='look'  ><span></span>查看详细信息</a></li>");
//		}
//		else{
//			menuArea.append("<li ><a href='PostController.do?method=toShowView&model=grid&objId="+id+"' target='#conBody' class='look'  ><span></span>查看详细信息</a></li>");
//		}
//			
//		menuArea.append("<li ><a href='#' target='#' onclick='organizationList.delOrg(\""+id+"\");return false;;' class='del'  ><span></span>删除本机构</a></li>");
//		
//	})
}
organizationList.onRightMenuHtml = function(type,id,pname){
	var btn;
	switch(type){
		case '1':
			btn = "<li id=\"createCom\"><a href='CompanyController.do?method=toCreateOrUpdate&model=grid&parentShortName="+pname+"&parentId="+id+"' target='#conBody' class='addOrg'  ><span></span>新增下级公司</a></li>";
			break;
		case '2':
			btn = "<li id=\"createDep\"><a href='DepartmentController.do?method=toCreateOrUpdate&model=grid&parentShortName="+pname+"&parentId="+id+"' target='#conBody' class='addDep'  ><span></span>新增下级部门</a></li>";
			break;
		case '3':
			btn = "<li id=\"createPost\"><a href='PostController.do?method=toCreateOrUpdate&model=grid&parentShortName="+pname+"&parentId="+id+"' target='#conBody' class='addJob'  ><span></span>新增下级岗位</a></li>";
			break;
		default:
			btn="";
		break;
	}
	return btn;
} 

//获取 新增下级菜单,type 机构类型   btnsArea动态按钮的区域div
organizationList.getOrgRules = function(type,btnsArea,shortName,objId){
	if(type=='1'){
		btnsArea.prepend(organizationList.createBtnHtml('2',shortName,objId));
		btnsArea.prepend(organizationList.createBtnHtml('1',shortName,objId));
	}
	else if(type=='2'){
		btnsArea.prepend(organizationList.createBtnHtml('2',shortName,objId));
	}
	
//	$.getJSON($("#initPath").val()+"/OrganizationController.do?method=getOrgRules",{type:type},function(json){
//		var rule = json.result;
//		if(rule.length>0){
//			for(var i=0;i<rule.length;i++){
//				btnsArea.prepend(organizationList.createBtnHtml(rule[i],shortName,objId));
//			}
//		}
//		
//	})
}
organizationList.createBtnHtml = function(type,objId,shortName){
	var btn;
	switch(type){
	case '1':
		btn = "<li class='add'><a href='CompanyController.do?method=toCreateOrUpdate&model=grid&parentShortName="+shortName+"&parentId="+objId+"' target='#conBody' ><span>添加公司</a></span></li>";
		break;
	case '2':
		btn = "<li class='addDep'><a href='DepartmentController.do?method=toCreateOrUpdate&model=grid&parentShortName="+shortName+"&parentId="+objId+"' target='#conBody' ><span>添加部门</span></a></li>";
		break;
	case '3':
		btn = "<li class='addJob'><a href='PostController.do?method=toCreateOrUpdate&model=grid&parentShortName="+shortName+"&parentId="+objId+"' target='#conBody' ><span>添加岗位</span></a></li>";
		break;
	default:
		btn="";
	break;
	}
	return btn;
} 


//右键菜单,e为兼容FireFox
organizationGridList.keyRight =function(rowDate,e){
	
	var id = rowDate.objId;
	var pname = rowDate.shortName;
	var type = rowDate.type_value;
	organizationParentShortName = rowDate.shortName;//初始化父级机构中文名称
	//清空菜单
	$('.epsContentMenu>ul').html("");
	organizationList.getOnRightMenu(type,$('.epsContentMenu>ul'),id,pname);
	$('.epsContentMenu').epsContentMenu(id,{},e);
}



organizationGridList.rows=null;

	//导出excel
	organizationGridList.excel=function(name,grid){
		var title = "用户列表";//excel文件里面顶上的标题
		var columnNames = "姓名,所属机构,帐号,状态,有效期";//每一列的表态
		var queryColumns="emp.name,emp.company.name,usName,usrIsLocked,usrPeriodOfValidity";//要导出的列的属性
		var columnWidth = "150,150,150,150,150";//每一列的宽带
		var clazzName= "com.gpcsoft.srplatform.auth.domain.User";//要导出的实体类名
		var order="emp.name";//排序属性
		exportFile.excel('userGrid',title,columnNames,queryColumns,columnWidth,clazzName,order,'userGrid',false);
	}   

	//新增
	organizationGridList.add=function(name,grid){
		organizationParentShortName='';//清空父机构中文值
		if(PlatForm.user.usrIsAdmin!='2'){
			alert("您不能新增新公司!","提示",{icon:'info'})
			return;
		}
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/CompanyController.do?method=toCreateOrUpdate&model=grid");
	}   
	
	
	//查看
	organizationGridList.detail=function(id){
		var type = $("#organizationGrid").flexGetRowJsonById(id)['type_value'];
		//跳转到修改页面
		if(type=="1"){
			$.epsDialog({
		        title:'公司详细信息',
		        url:$('#initPath').val()+'/view/srplatform/auth/float/company_detail.jsp?objId='+id,
		        width: '500',
		        height: '400',
		        onOpen: function(){ },
		        afterLoad: function(){ },
		        onClose: function(){ }
		    }); 
		}
		else if(type=="2"){
			$.epsDialog({
		        title:'部门详细信息',
		         url:$('#initPath').val()+'/view/srplatform/auth/float/department_detail.jsp?objId='+id,
		        width: '500',
		        height: '220',
		        onOpen: function(){ },
		        afterLoad: function(){ },
		        onClose: function(){ }
		    }); 
		}
	}   
	//删除
	organizationGridList.remove=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择机构'+name,'提示',{icon:'info'});return false;}//是否选中
		organizationList.delOrg($(grid).getSelects().split(','));
	}
	//列表操作验证
	organizationGridList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择机构'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个机构'+name);return false;}//是否只选中一个
		return true;
	}
	organizationGridList.before=function(){//查询条件过滤
		var option={};
		$('#organizationGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	organizationGridList.success=function(){
		$("#organizationGrid").flexGetColByName({
			 'createTime' : function(id,t){
				var shortTime = $(t).html().substring(0,10);
				$(t).html(shortTime);
			},
			'name':function(id,t){
				//var json = $("#organizationGrid").flexGetRowJsonById(id);
				$(t).html('<a href="#" onclick="organizationGridList.detail(\''+id+'\');return false;">'+$(t).html()+'</a>');
			}
		});
	}
$(document).ready(function(){
	//返回
	$("#returnUrl").val($("#initPath").val()+"/OrganizationController.do?method=toOrganizationList");
	
	//chooseModel
	$("#chooseModel").click(function(){
			$("#conBody").empty().loadPage("OrganizationController.do?method=toTreeList");
	})
	$('input[name=name]').autocomplete($('#initPath').val() + '/OrganizationController.do?method=getObjectQuery&queryColumns=name,shortSpellName', {
		extraParams:{
			'status':'1',
			'status_relative':'[and]'
		},//查询条件  例如    {objId:111}
		matchColumn:'name,shortSpellName',//作为查询显示, 被选中之后匹配的列
		minChars: 0,
		max: 8,
		autoFill: false,
		mustMatch: false,
		scrollHeight: 220,
		formatItem: function(data, i, total) {
//			return '<I>'+data.shortSpellName+'</I>';
			return data.name;
		},
		formatMatch: function(data, i, total) {
			return data.name;
		},
		formatResult: function(data) {
			return data.name;
		}
	}).result(function(event,data,formatted){
//		if(data)
//			alert('这里是选中之后回调函数显示的: 选中的是'+data.shortSpellName);
	});   
});



