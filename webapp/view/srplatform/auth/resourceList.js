//包组区分JS方法、属性的唯一性
var resourceList={};
var resId="";
resourceList.isSort=0;

//treeTab
function treeTab(){
	treeHe();
	$(window).wresize(treeHe);
	var h =$('#contentThis').height();
	$('.treeOutside').jqResize('.treeResize',function(w){if(parseInt(w)<240){$('.treeOutside').width(240);}	$('.treeOutside').height(h)});
	function treeHe(){$('.treeOutside,.treeRight,.treeResize').height($('#contentThis').height()-50);}
}

//打开全部节点
function openTreeItem(){
	$('#openAllItem').click(function(){tree.openAllItems(0);})
}
	
//关闭全部节点
function closeTreeItem(){
	$('#closeAllItem').click(function(){tree.closeAllItems(0);})
}
	
//删除节点
function delTreeItem(){
$('#delTreeItem').click(function(){
	if(confirm('确定删除？')){tree.deleteItem(tree.getSelectedItemId(),true);del}
	return false;
	})
}

//空值检测
function checkEditNull(cid,mid,name){
	var ccid = $('#'+cid);
	var mmid = $('#'+mid);
	if((ccid.val().length) <1)
	{
		ccid.addClass('input_null');
		ccid.focus();
		mmid.html(name + '不能为空');
		return false;
	}else{
		ccid.removeClass('input_null');
		mmid.html('');
		return true;
	}
}	

//修改节点
function modTreeItem(){
	$('#modifyTreeItem').click(function(){
		if(checkEditNull('ed1','modErrorMsg','名称')==false) return false;
		tree.setItemText(tree.getSelectedItemId(),$('#ed1').val());
	});
}

//同级新增节点
function addTreeItemSame(){
	var d=new Date();
	$('#addTreeItemSame').click(function(){
		if(checkEditNull('ed2','addErrorMsg','名称')==false) return false;
		tree.insertNewNext(tree.getSelectedItemId(),d.valueOf(),$('#ed2').val(),0,0,0,0,'SELECT');
		tree.setItemImage2(tree.getSelectedItemId(),'book_titel.gif','book.gif','book_titel.gif');
		$('#ed2').val('');
	})
}

//下级新增节点
function addTreeItemNext(){
	var d=new Date();
	$('#addTreeItemNext').click(function(){
		if(checkEditNull('ed2','addErrorMsg','名称')==false) return false;
		tree.insertNewItem(tree.getSelectedItemId(),d.valueOf(),$('#ed2').val(),0,0,0,0,'SELECT');
		tree.setItemImage2(tree.getSelectedItemId(),'book_titel.gif','book.gif','book_titel.gif');
		$('#ed2').val('');
	})
}

//初始化资源表单
function initResType(){
	//新建资源默认为可用状态，将资源状态选择框设置禁用
	$("#status").attr("disabled","disabled");
	//默认产生菜单启用
//	$('*[name=defaultMenu]').attr("disabled","");
	//将上级资源选择树设置为启用
	//$("*[name=parent.name]").attr("disabled","");
	//将表单中的值清空
	$.each($("input:text"),function(i,n){
		$(n).attr("value","");
	})
	$("#objId").attr("value","");
	$("#parent.objId").attr("value","");
}

//加载资源树
function resourceTree(id){
	//dhtmlXTreeObject(htmlObject, width, height, rootId)//rootId 虚拟根节点，在界面上不会显示，一般取值0
	resourcetree=new dhtmlXTreeObject("resourceTree","100%","100%",0);
	resourcetree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	//resourcetree.enableCheckBoxes(1);
	//resourcetree.enableDragAndDrop(1);
	resourcetree.enableThreeStateCheckboxes(true);
	resourcetree.setOnClickHandler(resclick);
	resourcetree.setXMLAutoLoading("ResourceController.do?method=getTree&order=sort&action=listById",function(){
	});
	resourcetree.loadXML($("#initPath").val()+"/ResourceController.do?method=getTree&order=sort&action=listTop&id=0",function(){
	});
}

//加载资源详细信息
function getResInfo(id){
	$.getJSON('ResourceController.do?method=getResInfo',{'resId':id},function(json){
		if(json.failure) return;
		if(json.result) {
			var resTabName = $("#resTabName").text();
			json2ObjectSpan('ResourceList',json.result);
		}
		$("#resTabName").text(resTabName);
		
		if($("#resTabName").text()=="修改资源"){
			$.getJSON("ResourceController.do?method=createOrUpdate",{'objId':resId,includedProperties:"parent"},function(json){
//				alert(obj2str(json))
				//清空上级资源
				$("#ResourceList")[0].reset();
				//前台页面显示
				//alert($("input[name=parent.objId]").val())
				jsonObjectToForm('ResourceList',json.resource);		
	    	})
		}
	});
	
}


//资源节点点击事件
function resclick(id){
	//doLog(id);
	//$tabs.tabs('select', 0);//打开资源详细信息tab页
	getResInfo(id);//加载资源详细信息
	initResType();//初始化资源表单
	if(id != -1){//去除资源树顶级节点
		resId = id;//将所点击的资源id设置为全局变量
		$('*[name=parent.name]').attr('value',resourcetree.getItemText(resId));
		$('*[name=parent.objId]').attr('value',resId);
	}
	$('#resourceDetail').show();//隐藏detail
	$('#resourceForm').hide();//隐藏form
}

//取得节点名称:
function doLog(id){
	tree.getItemText(id)
}

$(document).ready(function(){
	$('#defaultMenu').click(function(){
		if($('#defaultMenu').val()=='0')	$('#defaultMenu').val('1');
		else if($('#defaultMenu').val()=='1')	$('#defaultMenu').val('0'); 	
	});
	
	$('#resourceForm').hide();//隐藏form
	
	resourceTree();	//初始化资源树	
	initResType();//初始化资源表单
	//打开新建资源页面
	$("#newRes").click(function(){
		initResType();
		if(resId != -1 && resId != ""){//资源树顶级节点
			$('*[name=parent.name]').attr('value',resourcetree.getItemText(resId));
			$('*[name=parent.objId]').attr('value',resId);
		}else{
			$('*[name=parent.name]').attr('value','');
			$('*[name=parent.objId]').attr('value','');
		}
		$('#resourceDetail').hide();//隐藏form
		$('#resourceForm').show();//隐藏form
		//默认产生菜单启用
		$('*[name=defaultMenu]').removeAttr("disabled");
	});
	
	//点击返回
	$("#returnResource").click(function(){
		$('#resourceDetail').show();//隐藏form
		$('#resourceForm').hide();//隐藏form
	});

	//保存和修改资源
	$("#saveResource").click(function(){
		if(!$('#ResourceList').valid()){alert('请正确填写资源信息!','提示',{icon:'info'});return;}
		var josnObj=formToJsonObject('ResourceList');
		if($("input[name =objId]").val()!=""){
			josnObj.menuDefault = '0';
		}else{
			josnObj.menuDefault = $('#defaultMenu').val();//是否默认产生菜单
		}
		
		if($("#objId").val()=="")
			josnObj.isLeaf = 1;//新增资源为叶子节点
		var resParentId = $("*[name=parent.objId]").val();
		//如果是新增 还有上级资源
		if($("input[name =objId]").val()==""&&resParentId!=""){
			//查询上级资源的level和path
			$.getJSON("ResourceController.do?method=getParentResInfo",{'resParentId':resParentId},function(json){
				 if(json.failure) return;
				 if(json.result){
					 josnObj.path=json.result.parentPath;//资源path
					 josnObj.level=json.result.parentLevel;//资源level
					 //alert(obj2str(josnObj))
					 $.getJSON("ResourceController.do?method=saveResource",josnObj,function(json){
							if(json.failure) return;
							if(json.success){
								if(json.result=='true'){
									alert("保存资源成功"); 
									var temp = resourcetree.getSelectedItemId();
									if(!temp)temp=-1;
									resourcetree.insertNewItem(temp,json.resource.objId,json.resource.name,0,0,0,0,'SELECT');
									resourcetree.setItemImage2(temp,"node.gif","folderOpen.gif","folderClosed.gif");
									//resourcetree.setItemText(json.resource.objId,json.resource.name);
									//$('#conBody').empty().loadPage("ResourceController.do");
								}else{
									alert("保存资源失败");
								}
							}
				    	})
				 }
	    	})
		}else{//不存在上级资源	
			josnObj.level=1;
			$.getJSON("ResourceController.do?method=saveResource",josnObj,function(json){
				if(json.failure) return;
				if(json.success){
					if(json.result=='true'){
						alert("保存资源成功");
						if(resourcetree._globalIdStorageFind(json.resource.objId)){
							resourcetree.setItemText(json.resource.objId,json.resource.name);
						}else{
							var temp = resourcetree.getSelectedItemId();
							if(!temp)temp=-1;
							resourcetree.insertNewItem(temp,json.resource.objId,json.resource.name,0,0,0,0,'SELECT');
							resourcetree.setItemImage2(temp,"node.gif","folderOpen.gif","folderClosed.gif");
						}
					}else{
						alert("保存资源失败");
					}
				}
	    	})
		}
	})
	
	//进入修改角色页面
	$("#modifyRes").click(function(){
		//将点击的角色id的值赋予objId
		$("#objId").attr('value',resId);
		if(resId == ""||resId == null) {
			alert('请选择一个资源');
			$('#resourceForm').hide();
			return;
		}
		//角色状态选择框启用
		$("#status").attr("disabled","");
		//上级选择框禁用
		//$("*[name=parent.name]").attr("disabled","disabled");
		//默认产生菜单禁用
		$('*[name=defaultMenu]').attr("disabled","disabled");
		//修改tab标题
		$("#resTabName").text("修改资源");
		//查询所选角色的信息
		$.getJSON("ResourceController.do?method=createOrUpdate",{'objId':resId,includedProperties:"parent"},function(json){
			//清空上级资源
			$("#ResourceList")[0].reset();
			//前台页面显示
				$("input[name=parent.objId]").val('');
				$("input[name=parent.name]").val('');
			jsonObjectToForm('ResourceList',json.resource);		
    	});
		
		$('#resourceDetail').hide();//隐藏form
		$('#resourceForm').show();//隐藏form
	});
	
	//查询资源
	$("input[id=parent.name]").click(function(e){
		$.epsDialog({
	        title:'选择'+$('#name').val()+'的资源',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=parent&className=Resource',
	        width: '500',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
	    }); 
 	})	
 	
 	//删除资源
 	$("#deleteRes").click(function(){
 		if(window.confirm("是否确定删除此资源?")){
			$.getJSON("ResourceController.do?method=deleteResource",{'resId':resId},function(json){
				if(json.failure){alert(json.result); return;}
				if(json.success){
					if(json.result=='true'){
//						alert("操作成功"); 
						resourcetree.deleteItem(resId,true);
					}else{
						alert("请选择资源再进行删除操作");
					}
				}
	    	})
 		}
	})
	
	//节点上移
   	$("#up").click(function(){
   		if(resourcetree.getSelectedItemId()==""){
   			return;
   		}
   		if(resourceList.isSort==0){
   			resourceList.isSort=1;
   	   		if(treeUtil.sortUp(resourcetree,"up","com.gpcsoft.srplatform.auth.domain.Resource"))
   	   		resourceList.isSort=0;
   		}
   		
	})
	//节点下移
	$("#down").click(function(){
		if(resourcetree.getSelectedItemId()==""){
   			return;
   		}
		if(resourceList.isSort==0){
			resourceList.isSort=1;
			if(treeUtil.sortDown(resourcetree,"down","com.gpcsoft.srplatform.auth.domain.Resource"))
				resourceList.isSort=0;
		}
	})

})
