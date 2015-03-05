
var userList={};
userList.currentCompanyId;

	//授权
	userList.authorize=function(id,name){
	    $.epsDialog({
            title:'请给账号 ' + name + ' 授权',
            url:$('#initPath').val()+'/UserController.do?method=toUserAuthPage&userId='+id+"&currentCompanyId="+userList.currentCompanyId,
            width: '600',
            height: '360',
            hasBg:true,//背景
            fadeTo:80,//透明度
            onOpen: function(){ },
            afterLoad: function(){ },
            onClose: function(){ }
	    });
	}

	//重置密码
	userList.resetPassword=function(id){
		if(window.confirm("是否确定重置密码?")){
			$.getJSON($('#initPath').val()+'/UserController.do?method=resetPassword',{objId:id},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
			});
		}	
	}
	
	//用户锁定解锁
	userList.lockOrUnlock=function(id,status){
		var name = "锁定";
		if(status=='1') {
			name = "解锁";
		}
		if(window.confirm("确定要"+name+"?")){
			$.getJSON($('#initPath').val()+'/UserController.do?method=lockOrUnlock',{objId:id},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				userList.oTable.fnDraw();//刷新
			});
		}			
	}

	//新增
	userList.add=function(){
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/UserController.do?method=toCreateOrUpdate");
	}   
	//修改
	userList.update=function(id){
		if(id=="") {
			if(userList.oTable.dtSelects().length==0){alert('请选择用户');return false;}//是否选中
			if(userList.oTable.dtSelects().split(',').length>1){alert('请选择一个用户');return false;}//是否选中一个
			id=userList.oTable.dtSelects();
		}
		//跳转到修改页面
		$('#conBody').loadPage('UserController.do?method=toCreateOrUpdate&objId='+id);
	}   
	//删除
	userList.remove=function(){
		if(userList.oTable.dtSelects().length==0){alert('请至少选择一个用户');return false;}//是否选中
		if(window.confirm('确定删除所选用户?')){
			$.getJSON($('#initPath').val()+'/UserController.do?method=delete',{objId:userList.oTable.dtSelects().split(',')},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				userList.oTable.fnDraw();//刷新
			});
		}
	}
	
	
	//初始化组织机构树
	userList.tree;
	userList.initOrgnizationTree = function(companyId){
		userList.tree = new dhtmlXTreeObject("orgnizationTreeGrid","100%","100%",0);
		userList.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
		userList.tree.setOnClickHandler(userList.nodeClick);
		userList.tree.setXMLAutoLoading("OrganizationController.do?method=getOwnerOrgTree&order=sort&action=listById&isOpen=0");
		userList.tree.loadXML($("#initPath").val()+"/OrganizationController.do?method=getOwnerOrgTree&action=listTop&order=sort",function(){
			userList.nodeClick("-1");
		});
	}
	
	//节点点击事件
	userList.nodeClick = function(companyId){
		userList.currentCompanyId = ( companyId=="-1"?$("#currentCompanyId").val():companyId );
		$("#selectedCompany").html(userList.tree.getItemText(companyId));
		userList.tree.selectItem(companyId)
		userList.getUserList(userList.currentCompanyId);
	}
	
	//获取列表
	userList.getUserList = function(companyId){
		if(!userList.oTable){
			userList.oTable = $('#userList').dataTable({
				'singleSelect' : false,
				'checkbox' : true,
				'queryColumns' : 'usName,emp.name,usrIsAdmin,createTime',
				'alias' : 'usName,emp.name,usrIsAdmin,createTime',
				'hiddenColumns':'usrSort,usrIsLocked',
				'fnInitComplete' : function(oSettings) {
				},
				'fnDrawCallback' : function(oSettings) {
					userList.oTable.oSettings = oSettings;
				},
				'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
					var operatorHtml = '<td>';
					operatorHtml += '<a title="修改" href="javascript:void(0);" onclick="userList.update(\''+aData.objId+'\');return false;">修改</a>';
					operatorHtml += '<a title="授权" href="javascript:void(0);" onclick="userList.authorize(\''+aData.objId+'\',\''+aData.usName+'\');return false;">授权</a>';
					operatorHtml += '<a title="重置密码" href="javascript:void(0);" onclick="userList.resetPassword(\''+aData.objId+'\');return false;">重置密码</a>';
					if(aData.usrIsLocked=='有效') {
						operatorHtml += '<a title="锁定" href="javascript:void(0);" onclick="userList.lockOrUnlock(\''+aData.objId+'\',\'0\');return false;">锁定</a>';
					}else {
						operatorHtml += '<a title="解锁" href="javascript:void(0);" onclick="userList.lockOrUnlock(\''+aData.objId+'\',\'1\');return false;">解锁</a>';
					}
					operatorHtml += '</td>';
					$(nRow).append(operatorHtml);
					return nRow;
				},
				"sAjaxSource" : $('#initPath').val()+ "/UserController.do?method=list",
				"params":{"emp.company.objId":companyId},
				'searchZone':'userSearchZone'
			});
		}else {
			$(userList.oTable.dataTableSettings).attr("params",{"emp.company.objId":companyId});
			userList.oTable.fnDraw();
		}
	}

$(document).ready(function(){
	
	//设置返回路径
	$("#returnUrl").val($("#initPath").val()+"/UserController.do?method=listUser");
	
	//初始化树
	userList.initOrgnizationTree($("#currentCompanyId").val());
	
	$('input[name=usName]').autocomplete($('#initPath').val() + '/UserController.do?method=getObjectQuery&queryColumns=usName', {
		extraParams:{},//查询条件  例如    {objId:111}
		matchColumn:'usName',//作为查询显示, 被选中之后匹配的列
		minChars: 0,
		max: 8,
		autoFill: false,
		mustMatch: false,
		scrollHeight: 220,
		formatItem: function(data, i, total) {
			return data.usName;
		},
		formatMatch: function(data, i, total) {
			return data.usName;
		},
		formatResult: function(data) {
			return data.usName;
		}
	});   
	$('input[name=emp.company.name]').autocomplete($('#initPath').val() + '/OrganizationController.do?method=getObjectQuery&queryColumns=name,shortSpellName', {
		extraParams:{},//查询条件  例如    {objId:111}
		matchColumn:'name,shortSpellName',//作为查询显示, 被选中之后匹配的列
		minChars: 0,
		max: 8,
		autoFill: false,
		mustMatch: false,
		scrollHeight: 220,
		formatItem: function(data, i, total) {
			return data.name;
		},
		formatMatch: function(data, i, total) {
			return data.name;
		},
		formatResult: function(data) {
			return data.name;
		}
	});   
});



