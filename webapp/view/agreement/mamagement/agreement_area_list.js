/*
 * 协议管理，协议区间页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */

//包组区分JS方法、属性的唯一性
var AreaTree = {};
AreaTree.isSort=0;
//全局树对象
AreaTree.tree;
	
//加载菜单树
AreaTree.menuTree = function(id){
	AreaTree.tree=new dhtmlXTreeObject("agreementAreaTree","100%","100%",0);
	AreaTree.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	AreaTree.tree.enableThreeStateCheckboxes(true);
	AreaTree.tree.setOnClickHandler(AreaTree.agreementAreaclick);
	AreaTree.tree.setXMLAutoLoading($('#initPath').val()+"/AreaController.do?method=getOwnerAreaTree&order=sort&action=listById&isOpen=0");
	AreaTree.tree.loadXML($("#initPath").val()+"/AreaController.do?method=getOwnerAreaTree&action=listTop&order=sort&isOpen=0",function(){
	});
}

//重置表单
AreaTree.resetForm = function(){
	$("input[name=objId],input[name=parent.objId]").val("");
	$("#reset").click();
}

//菜单节点点击事件
AreaTree.agreementAreaclick = function(id){
	if(id != -1){//去除资源树顶级节点
		$("#isRoot").val("");
		$('#agreementAreaDetail').show();
		$('#agreementAreaForm').hide();
		
		$.getJSON($('#initPath').val()+'/AreaController.do?method=getAreaInfo',{objId:id},function(json){
			json2Object("areaDetail",json.area);
			//是否可以删除
			"true"==json.isDelete?$("#isDelete").val(true):$("#isDelete").val(false);
			//是否可以修改
			"true"==json.isDelete?$("#isModify").val(true):$("#isModify").val(false);
			//objId
			$("input[name=areaId]").val(json.area.objId);
		});
	}else{//点击树形root
		//alert("这是根节点");
		$("#isRoot").val(true);
		$("input[name=areaId],input[name=areaId],input[name=parentId],input[name=isDelete],input[name=isModify],input[name=objId],input[name=parent.objId]").val("");
		$('#agreementAreaDetail').show();
		$('#agreementAreaForm').hide();
	}
	
	//重置表单
	AreaTree.resetForm();
}

//刷新节点
AreaTree.reloadTree = function (itemId,text){
	//判断是否增加还是修改
	if(AreaTree.tree._globalIdStorageFind(itemId)){
		AreaTree.tree.setItemText(itemId,text);
		AreaTree.agreementAreaclick(itemId);
	}else{
		var temp = AreaTree.tree.getSelectedItemId();
		if(!temp)temp=-1;
		//获取节点状态：0 - 没有子节点或者  节点合拢, 1  节点展开
		if(AreaTree.tree.hasChildren(temp)&&AreaTree.tree.getOpenState(temp)==0){
			return false;
		}
		else{
			AreaTree.tree.insertNewItem(temp,itemId,text,0,0,0,0,'SELECT');
			AreaTree.tree.setItemImage2(itemId,"leaf.gif","folderOpen.gif","folderClosed.gif");
			AreaTree.agreementAreaclick(itemId);
		}
	}
}


$(document).ready(function(){
	//初始化菜单树	
	AreaTree.menuTree();
	
	//选择行政区域
	$("#name").click(function(){
			$.epsDialog({
				id:"district",
		        title:'选择关联资源',
		        url:$('#initPath').val()+'/view/mamagement/DistrictTree.jsp?property=district&className=District&isCheckBox=true&id=district&Column=code,name'
		    }); 
	});
	
	//修改
	$("#modifyAgreementArea").click(function(){
		//重置表单
		AreaTree.resetForm();
		
		if("false"==$("#isModify").val()){
			alert("已被引用，不允许修改!");
			return;
		}
		if(null!=$("#areaId").val()&&""!=$("#areaId").val()){
			$("#agreementAreaDetail").hide();
			$("#agreementAreaForm").show();
			//form表单回填
			$.getJSON($('#initPath').val()+'/AreaController.do?method=toCreate',
					{
						objId:$("#areaId").val()
					},
					function(json){
						jsonObjectToForm('areaForm', json.area);
					}
			);
		}else if('true'==$("#isRoot").val()){
			alert("根节点不能修改");
		}else{
			alert("请选择某个节点修改");
		}
	});
	
	//删除
	$("#deleteAgreementArea").click(function(){
		if("false"==$("#isDelete").val()){
			alert("已被引用，不允许删除!");
			return;
		}
		if(null!=$("#areaId").val()&&""!=$("#areaId").val()){
			if(confirm("确定删除此区间？")){
				$.getJSON($('#initPath').val()+'/AreaController.do?method=delArea',{"areaId":$("#areaId").val()},function(json){
					if(json.failure){
						alert("删除失败,所选区期间使用中!");
						return;
					}else{
						alert(json.result);
						AreaTree.tree.deleteItem($("#areaId").val(),true);
						
						$("input[name=areaId],input[name=areaId],input[name=parentId],input[name=isDelete],input[name=isModify],input[name=objId],input[name=parent.objId]").val("");
						$("span[id=areaName],span[id=areaCodes],span[id=areaNames],span[id=memo]").html("");
						
						//重置表单
						AreaTree.resetForm();
						
						$('#agreementAreaDetail').show();
						$('#agreementAreaForm').hide();
						
					}
				});
				
			}
		}else if('true'==$("#isRoot").val()){
			alert("根节点不能删除!");
		}
		else{
			alert("请选择某个节点删除!");
		}
	});
	
	//新增
	$("#newAgreementArea").click(function(){
		//重置表单
		AreaTree.resetForm();
		
		//form表单回填
		$.getJSON($('#initPath').val()+'/AreaController.do?method=toCreate',{},function(json){
			
		});
		$("input[id=parent.objId]").val($("#areaId").val());
		$("#agreementAreaDetail").hide();
		$("#agreementAreaForm").show();
		//}
//		else if(){
//			alert("请选择某个区域新增!");
//		}
	});
	
	//保存
	$("#saveAgreementArea").click(function(){
		if(!$('#areaForm').valid()){alert('请正确填写表单!');return;}
		
		//保存或新增
		$.getJSON($('#initPath').val()+'/AreaController.do?method=saveArea',
			formToJsonObject('areaForm'),
			function(json){
				if(json.failure){
					alert(json.result);
					return;
				}else{
					alert(json.result);
					//刷新树
					AreaTree.reloadTree(json.objId,$("#areaForm").find("input[name=areaName]").val());
					//重置表单
					AreaTree.resetForm();
				}
		});
	});
	
	//返回
	$("#returnAgreementArea").click(function(){
		//重置表单
		AreaTree.resetForm();
		$("#agreementAreaDetail").show();
		$("#agreementAreaForm").hide();
	});
	
	//节点上移
   	$("#up").click(function(){
   		if(AreaTree.isSort==0){
   			AreaTree.isSort=1;
   	   		if(treeUtil.sortUp(menutree,"up","com.gpcsoft.eps.agreement.management.domain.Area"))
   	   			AreaTree.isSort=0;
   		}
	})
	
	//节点下移
	$("#down").click(function(){
		if(AreaTree.isSort==0){
			AreaTree.isSort=1;
			if(treeUtil.sortDown(menutree,"down","com.gpcsoft.eps.agreement.management.domain.Area"))
				AreaTree.isSort=0;
   		}
	})

})
