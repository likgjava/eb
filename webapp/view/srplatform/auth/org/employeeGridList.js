var employeeGridList={};
employeeGridList.del = function(id){
	if(window.confirm("确定删除?")){
			$.ajax({
					url:"EmployeeController.do?method=delEmpByIds",
					type:"POST",
					data:{objId:id},
					success:function(json){
						if(json.result)alert(json.result,{inco:'info'});;
						if(json.failure)return;
						$("#employeeGrid").reload();
					},error:function(json){
						alert(json)
					}
			})
		}
	
}

//右键菜单,e为兼容FireFox
employeeGridList.keyRight =function(rowDate,e){

	$('.epsContentMenu').epsContentMenu(rowDate,{
		menuEv: {
	    'detail': function(t,rowDate) {
			$('.treeRight').loadPage($('#initPath').val()+"/EmployeeController.do?method=toShowView&model=grid&objId="+rowDate.objId);
	    },'edit': function(t,rowDate) {
	    	$('.treeRight').loadPage($('#initPath').val()+"/EmployeeController.do?method=toCreateOrUpdate&model=grid&objId="+rowDate.objId);
	    },'del': function(t,rowDate){
	    	employeeGridList.del(rowDate.objId);
	    }
	  }
	},e);
}



employeeGridList.rows=null;

	//导出excel
	employeeGridList.excel=function(name,grid){
		var title = "用户列表";//excel文件里面顶上的标题
		var columnNames = "姓名,所属机构,帐号,状态,有效期";//每一列的表态
		var queryColumns="emp.name,emp.company.name,usName,usrIsLocked,usrPeriodOfValidity";//要导出的列的属性
		var columnWidth = "150,150,150,150,150";//每一列的宽带
		var clazzName= "com.gpcsoft.srplatform.auth.domain.User";//要导出的实体类名
		var order="emp.name";//排序属性
		exportFile.excel('userGrid',title,columnNames,queryColumns,columnWidth,clazzName,order,'userGrid',false);
	}   


	
	

	//新增
	employeeGridList.add=function(name,grid){
		//跳转到修改页面
		$('.treeRight').loadPage($('#initPath').val()+"/EmployeeController.do?method=toCreateOrUpdate&model=grid");
	}   
	//修改
	employeeGridList.modify=function(name,grid){
		if(!employeeGridList.validation(name,grid))return;
		//跳转到修改页面
		$('.treeRight').loadPage($('#initPath').val()+'/EmployeeController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//删除
	employeeGridList.remove=function(name,grid){
		//if(!employeeGridList.validation(name,grid))return;
		if($(grid).isSelectEmpty()){alert('请选择用户'+name,'提示',{icon:'info'});return false;}//是否选中
		employeeGridList.del($(grid).getSelects().split(','));
	}
	//列表操作验证
	employeeGridList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择员工'+name,'提示',{icon:'info'});return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个员工'+name,'提示',{icon:'info'});return false;}//是否只选中一个
		return true;
	}
	employeeGridList.before=function(){//查询条件过滤
		var option={};
		if(employeeTreeGrid.tree){
			var id = employeeTreeGrid.tree.getSelectedItemId();
			if(id!=''&&id!='-1'){
				option['orgId']=id;
			}
		}
		$('#employeeGrid').flexOptions({params:option});
		return true;
	}
	employeeGridList.detail=function(id){
		$.epsDialog({
		        title:'部门详细信息',
		         url:$('#initPath').val()+'/view/srplatform/auth/float/employee_detail.jsp?objId='+id,
		        width: '500',
		        height: '380',
		        onOpen: function(){ },
		        afterLoad: function(){ },
		        onClose: function(){ }
		    }); 
	}
	//加载数据成功之后调用的函数
	employeeGridList.success=function(){
		$("#employeeGrid").flexGetColByName({
			 'name' : function(id,t){
				$(t).html('<a href="#" onclick="employeeGridList.detail(\''+id+'\');return false;">'+$(t).html()+'</a>')
			}
		});
	}
$(document).ready(function(){
	$('input[name=name]').autocomplete($('#initPath').val() + '/EmployeeController.do?method=getObjectQuery&queryColumns=name,shortSpellName', {
		extraParams:{},//查询条件  例如    {objId:111}
		matchColumn:'name,shortSpellName',//作为查询显示, 被选中之后匹配的列
		mustMatch: false,
		formatItem: function(data, i, total) {
			return data.name;
		},
		formatMatch: function(data, i, total) {
			return data.name;
		},
		formatResult: function(data) {
			return data.name;
		}
	}).result(function(event,data,formatted){
	});   
});



