var UserRoles={};

UserRoles.sumbitClick=function(){
		var rolesArr=UserRoles.tree.getAllCheckedBranches().replace("-1","");
		var dataObj={"objId":$("#objId").val()};
		dataObj.roles=[];
		//没有选中用户的时候，不执行SET值操作
		if(rolesArr != ""){
			var rolesCount=0;
			for(var i = 0 ; i<rolesArr.split(",").length ; i++){
				//处理修改时,默认选中时带有空id
				if(rolesArr.split(",")[i]!=""&&rolesArr.split(",")[i]!=null){
					//初始话dataObj.roles[i]为空对象
					dataObj.roles[rolesCount]={};
					dataObj.roles[rolesCount].objId=rolesArr.split(",")[i];
					rolesCount++;
				}
			}
		}
		
		dataObj.json=JSON.stringify(dataObj);
		$.ajax({
			url:"UserController.do?method=save",
			type:"POST",
			data:dataObj,
			success:function(msg){	
				loadPage_toContent("/UserController.do?method=toSuccess")
			}
		})
}

$(document).ready(function(){
	index.treeHeight();
	$(window).resize(function(){index.treeHeight()});
	
	UserRoles.tree=new dhtmlXTreeObject('treeLeft',"100%","85%",0); 
	UserRoles.tree.enableDragAndDrop(true);
	UserRoles.tree.enableCheckBoxes(true)
	//初始化按钮不级联选中 0为表示不选中,1为选中状态
	UserRoles.tree.enableThreeStateCheckboxes(0);
	UserRoles.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/csh_vista/");
	UserRoles.tree.loadXML("RoleTreeController.do?method=getRoleByUser&type=2",function(){	
		
	    if($("#objId").val()!=""&&$("#objId").val()!="null"){
	    	$.getJSON("UserController.do?method=createOrUpdate",{objId:$("#objId").val()},function(json){
	    		json2ObjectDiv("userInfo",json.list[0])
	    		$.getJSON("UserController.do?method=getRolesByUser",{objId:$("#objId").val()},function(json){
	    			$.each(json,function(i,n){
	    				UserRoles.tree.setCheck(n.objId,1);
	    			})
	    			UserRoles.tree.setCheck("-1",1);
	    			UserRoles.tree.enableThreeStateCheckboxes(1);
	    		})
	    		
	    	})
	    }else{//处理JSON数据延迟加载的问题
	    	UserRoles.tree.enableThreeStateCheckboxes(1);
	    }
		
	});
	
	//返回
	$("#return").click(function(){
		loadPage_toContent("/UserController.do");
	});
	
	//提交
	$("#submit").click(UserRoles.sumbitClick)
})