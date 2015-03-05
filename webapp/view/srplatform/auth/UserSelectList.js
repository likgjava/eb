//定义文件全局变量 处理方法名重复问题
var UserSelectList={};
UserSelectList.pageSelectContent;
UserSelectList.pageCheckContent;

UserSelectList.query=function(){
	var queryJson=formToJsonObject($("#UserqueryForm")[0]);
	if(UserSelectList.pageSelectContent)
		UserSelectList.pageSelectContent.query(queryJson);
	else
		UserSelectList.pageCheckContent.query(queryJson);
}

$(document).ready(function(){
	var org = PlatForm.user.org;
	var url = $("#initPath").val()+"/UserController.do?method=listOld&_queryCols=objId,usrCnName,usName";
	if($("#_param").val() == "" && PlatForm.user.usrIsManager != "1"){
		url += "&org.objId="+org.objId+"&org.parent.objId="+org.objId+"&org.objId_relative=[and:or]&org.parent.objId_relative=[and:or]";
	}
	else if($("#_param").val() != ""){
		url += "&"+$("#_param").val();
	}

	if($.trim($("#_isCheckBox").val())==""){
		$("#UserSelectTable").removeClass("hidden");
		UserSelectList.pageSelectContent=new PageList("UserSelectTbody","UserSelectTemplate","UserSelectList.pageSelectContent","UserSelectPage");
		UserSelectList.pageSelectContent.sorttable=function(){
			sorttable.init("UserSelectTable");
		}
	
		UserSelectList.pageSelectContent.initData({url:url,data:{}},function(tr,trIndex,td,obj){
			$(tr).click(function(){
				returnValue(obj.objId,obj.usrCnName);
			})
			return true;
		})
		UserSelectList.pageSelectContent.searchForm();
	}
	else{
		$("#UserCheckTable").removeClass("hidden");
		$("#_OK").removeClass("hidden");
		UserSelectList.pageCheckContent=new PageList("UserCheckTbody","UserCheckTemplate","UserSelectList.pageCheckContent","UserCheckPage");
		UserSelectList.pageCheckContent.sorttable=function(){
			sorttable.init("UserCheckTable");
		}
	
		UserSelectList.pageCheckContent.initData({url:url,data:{}},function(tr,trIndex,td,obj){
			var objId = $("#_checkValues").val().split(",");
			if(objId && objId.length > 0){
				for(var i = 0 ; i < objId.length ; i ++){
					if(obj.objId == objId[i])
						$(tr).find("input[type=checkbox]").attr("checked","true");
				}
			}
			return true;
		})
		UserSelectList.pageCheckContent.searchForm();
	}
	
	//查询
	$("#UserqueryBuuton").click(UserSelectList.query);
	
	//清空
	$("#_clear").click(function(){
		returnValue("","");
	})
	
	//确定
	$("#_OK").click(function(){
		if(UserSelectList.pageCheckContent.getCheckedValues()<=0){
			alert("请选择相关数据!如需清空数据,请点击 清空 按钮");
			return;
		}
		var objIds=UserSelectList.pageCheckContent.getCheckedValues().toString();
		var names="";
		$("#UserCheckTable").find("input[type=checkbox]").each(function(i,n){
			if($(n).attr("checked")){
				names += $(n).parent("div").parent("td").parent("tr").find("#usrCnName").text() + ",";
			}
		})
		names=names.substring(0,names.length-1);

		returnValue(objIds+"",names);
	})
	
	
	
})

function returnValue(id,name){
	//回填id
	if("" == $("#_ID").val())
		document.getElementById($("#_property").val()).value=id;
	else
		document.getElementById($("#_ID").val()).value=id;
	
	//回填name
	if("" == $("#_NAME").val()){
		document.getElementById($("#_property").val()+"CnName").value=name;
		$(document.getElementById($("#_property").val()+"CnName")).keyup();
	}
	else{
		document.getElementById($("#_NAME").val()).value=name;
		$(document.getElementById($("#_NAME").val())).keyup();
	}
	
	//关闭弹出层
	$("#floaterDiv").dialog("close");
	$('#epsDialogClose').click();
}
