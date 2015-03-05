var organizationGridList={};//公共方法变量名

//查看
	organizationGridList.detail=function(id){
		var json = $("#organizationGrid").flexGetRowJsonById(id);
		var type = "1";
		var companyId = json['company.objId'];
		if(type=="1"){
			$.epsDialog({
		        title:'公司详细信息',
		        url:$('#initPath').val()+'/view/srplatform/auth/float/company_detail.jsp?objId='+companyId,
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
		         url:$('#initPath').val()+'/view/srplatform/auth/float/department_detail.jsp?objId='+companyId,
		        width: '500',
		        height: '220',
		        onOpen: function(){ },
		        afterLoad: function(){ },
		        onClose: function(){ }
		    }); 
		}
	}   

	organizationGridList.before=function(){//查询条件过滤
		var option={};
		$('#organizationGrid').flexOptions({params:option});
		return true;
	}
	organizationGridList.auth = function(orgId,name){
		 $.epsDialog({
	        title:'给'+name+'授权',
	        url:$('#initPath').val()+'/OrganizationController.do?method=toAuth&orgId='+orgId,
	        width: '900',
	        height: '500',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
	    }); 
		
	}
	//加载数据成功之后调用的函数
	organizationGridList.success=function(){
		$("#organizationGrid").flexGetColByName({
			'company.name':function(id,t){
				$(t).html('<a href="#" onclick="organizationGridList.detail(\''+id+'\')" style="padding-left: 0px;">'+$(t).html()+'</a>');
			},'company.objId':function(id,t){
				var json = $("#organizationGrid").flexGetRowJsonById(id);
				var showStr = "";
				if (undefined != json.buyerId && null != json.buyerId && "" != json.buyerId) {
					showStr += "招标单位、";
				}
				if (undefined != json.supplierId && null != json.supplierId && "" != json.supplierId) {
					showStr += "投标单位、";
				}
				if (undefined != json.agencyId && null != json.agencyId && "" != json.agencyId) {
					showStr += "招标中心、";
				}
				if (undefined != json.supervisionId && null != json.supervisionId && "" != json.supervisionId) {
					showStr += "监管、";
				}
				if ("" != showStr && showStr.length>0) {
					showStr = showStr.substr(0,showStr.length-1)
				}
				$(t).html(showStr);
			}
		});
		$("#organizationGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn" title="授权" style="color:#005aa0">授权</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			 btn.click(function(){
			 	var json = $("#organizationGrid").flexGetRowJsonById(rowId);
				organizationGridList.auth(json['company.objId'],json.name);
			 }).appendTo(obj);
		}
	});
	}
$(document).ready(function(){
	
	$('input[name=name]').autocomplete($('#initPath').val() + '/OrganizationController.do?method=getObjectQuery&queryColumns=name,shortSpellName', {
		extraParams:{
			'auditStatus':'2',
			'status':'1',
			'type':'1'
		},//查询条件  例如    {objId:111}
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
	}).result(function(event,data,formatted){
	});   
});



