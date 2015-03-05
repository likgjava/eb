//包组区分JS方法、属性的唯一性
var MenuList={};
var menuId="";
MenuList.isSort=0;

//treeTab
function treeTab(){
	treeHe();
	$(window).wresize(treeHe);
	var h =$('#contentThis').height();
	$('.treeOutside').jqResize('.treeResize',function(w){if(parseInt(w)<240){$('.treeOutside').width(240);}	$('.treeOutside').height(h)});
	function treeHe(){$('.treeOutside,.treeRight,.treeResize').height($('#contentThis').height());}
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
	})
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


//加载tabs
var $tabs = $('#epsTabs').tabs({
	   add: function(event, ui) {
			$tabs.tabs('select', '#' + ui.panel.id);
			}
	   }); 

//表单验证
function formValiation(){
	//  用户名验证    
	jQuery.validator.addMethod("userName", function(value, element) {    
	  return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);    
	}, "只能包括中文、英文、数字和下划线");    
	
	
	//密码验证
	jQuery.validator.addMethod("password", function( value, element ) {
		var result = this.optional(element) || value.length >= 6 && /\d/.test(value) && /[a-z]/i.test(value);
		return result;
	}, "至少6位并有数字和字母.");
	
	$('#commentForm').validate({
		//onkeyup: false,
		rules: {
				radio1: "required",
				check1:"required",
				approved:{
							required: true,
							minlength: 2
							//,equalTo: "#password"
						}
				//,type:"required"
		},
		messages:{
			radio1:"这是必选项",
			check1:"这是必选项",
			approved:"至少要选择2个",
			area1:"选择省份",
			area2:"选择市区"
		}
	});
	
	loadPage_includeJs('../../resource/plug-in/jquery/jquery.maskedinput.js',function(){
		$("input.cnMobile").mask("99999999999");
		$("input.cnZipCode").mask("999999");
	})

}

//初始化菜单表单
function initMenuType(){
	//select下拉框的第一个元素为当前选中值
	$('#showFlag')[0].selectedIndex = 0;
	$('#isDefault')[0].selectedIndex = 0;
	//将上级菜单选择树设置为启用
	//$("*[name=parent.name]").attr("disabled","");
	//将表单中的值清空
	$.each($("input:text"),function(i,n){
		$(n).attr("value","");
	})
	$("#memo").attr("value","");
	$("#objId").attr("value","");
	$("#parent.objId").attr("value","");
}

//加载菜单树
function menuTree(id){
	//dhtmlXTreeObject(htmlObject, width, height, rootId)//rootId 虚拟根节点，在界面上不会显示，一般取值0
	menutree=new dhtmlXTreeObject("menuTree","100%","100%",0);
	menutree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	//menutree.enableCheckBoxes(1);
	//menutree.enableDragAndDrop(1);
	menutree.enableThreeStateCheckboxes(true);
	menutree.setOnClickHandler(menuclick);
	menutree.setXMLAutoLoading("MenuController.do?method=getTree&order=sort&action=listById",function(){
	});
	menutree.loadXML($("#initPath").val()+"/MenuController.do?method=getTree&order=sort&action=listTop&id=0",function(){
	});
}

//加载菜单详细信息
function getMenuInfo(id){
	$.getJSON('MenuController.do?method=getMenuInfo',{'menuId':id},function(json){
		if(json.failure) return;
		if(json.menu){
			//调用json2Object会改掉roleTabName的名字
			json2ObjectSpan('MenuList',json.menu);
			if(json.menu.menuIcon) {
				$("div[name=newPreview]").empty().append("<img src='"+$("#initPath").val()+json.menu.menuIcon+"' width='50px' height='50px'/>")
			}else
				$("div[name=newPreview]").empty().append("<img width='50px' height='50px'/>");
			
			//是否默认菜单
			if(json.menu.menuIsDefault_value == 0)
				$("#isDefault").text("否");
			else
				$("#isDefault").text("是");
			
			//是否显示
			if(json.menu.menuShowFlag_value == 0)
				$("#showFlag").text("否");
			else
				$("#showFlag").text("是");
		}
		
		//恢复roleTabName的名字
		if($("#menuTabName").text()=="修改菜单"){
			MenuList.modifyMenu();
		}
	});
}


//菜单节点点击事件
function menuclick(id){
	//doLog(id);
	if(id != -1){//去除资源树顶级节点
		menuId = id;//将所点击的资源id设置为全局变量
		getMenuInfo(id);//加载菜单详细信息
		$('*[name=parent.name]').attr('value',menutree.getItemText(menuId));
	}else{//点击树形root
		initMenuType();//初始化菜单表单
		menuId = "";//初始化选中菜单
		$("#menuTabName").text("新建菜单");
		$('*[name=parent.name]').attr('value','');
		$('*[name=parent.objId]').attr('value','');
		$('*[name=resource.name]').attr('value','');
		$('*[name=resource.objId]').attr('value','');
		$("#menuTable:td").find("div span[id]").each(function(i,n){
			$(n).html("");
		})
	}
	$('#menuDetail').show();
	$('#menuForm').hide();
}

//取得节点名称:
function doLog(id){
	tree.getItemText(id)
}
//菜单修改界面
MenuList.modifyMenu = function(){
	if(menuId == ""){
		//$('#menuForm').hide();
		alert("请选择一个菜单");
		return;
	}
	//$("*[name=parent.name]").attr("disabled","disabled");
	//将点击的角色id的值赋予objId
	$("#objId").attr('value',menuId);
	//修改tab标题
	$("#menuTabName").text("修改菜单");
	//查询所选角色的信息
	$.getJSON("MenuController.do?method=createOrUpdate",{'objId':menuId,includedProperties:"resource,parent"},function(json){
		//前台页面显示
		$("#MenuList")[0].reset();
		jsonObjectToForm('MenuList',json.menu,false);		
	})
}

//保存
MenuList.submit=function(){
	var jsonObj=formToJsonObject('MenuList','json');
	if($("input[id=parent.objId]").val() == ''){
		jsonObj.parent = {};
	}
	
	$("#jsonStr").val(JSON.stringify(jsonObj));
	$('#MenuList').ajaxSubmit({
		url:$('#initPath').val()+"/MenuController.do?method=saveMenu",
		dataType:'json',
		success:function(json){
			alert("保存成功！");
			$('#MenuList input[name=objId]').val(json.menu.objId);
			 $('#MenuList input[name=parent.level]').val(json.parentMenu.level);
				if(menutree._globalIdStorageFind(json.menu.objId)){
					menutree.setItemText(json.menu.objId,json.menu.name);
				}else{
					var temp = menutree.getSelectedItemId();
					if(!temp)temp=-1;
					menutree.insertNewItem(temp,json.menu.objId,json.menu.name,0,0,0,0,'SELECT');
					menutree.setItemImage2(temp,"node.gif","folderOpen.gif","folderClosed.gif");
				}
		}
	})
}

$(document).ready(function(){

	//预览图片
	$("#icon").change(function(){
		var filePath = $("#icon").val();
		var fileName = filePath.replace(/.+\\([^\\]+)/,'$1');
		var i = fileName.lastIndexOf('.');       	 //从右边开始找第一个'.'
		var len = fileName.length;                	 //取得总长度
		var str = fileName.substring(len,i+1);    	 //取得后缀名
		var exName = "PNG,BMP,JPG,GIF";       		 //允许的后缀名
		var k = exName.indexOf(str.toUpperCase());	 //转成大写后判断
		if(k==-1){                                	 //没有符合的
		    alert("上传文件错误！只能上传"+exName);
			this.value="";
		}else{
			$("#newPreview").find('img').attr("src",preViewPic(this));
		}
	})
	
	//改变浏览器大小时改变高度
	 $('#menuForm').hide();
	 
	
	//返回
	 $('#returnMenu').click(function() {
		 $('#menuDetail').show();//隐藏form
		 $('#menuForm').hide();//隐藏form
	 })
	menuTree();	//初始化菜单树	
	initMenuType();//初始化菜单表单
	//打开新建菜单页面
	$("#newMenu").click(function(){
		initMenuType();
		if(menuId != -1 && menuId != ""){//资源树顶级节点
			$('*[name=parent.name]').attr('value',menutree.getItemText(menuId));
		//alert("$( newMenu ).click id="+menuId);
			$('*[name=parent.objId]').attr('value',menuId);
		}else{
			$('*[name=parent.name]').attr('value','');
			$('*[name=parent.objId]').attr('value','');
		}
		//$("#menuTabName").text("新建菜单");
		$('#menuDetail').hide();
		$('#menuForm').show();
		//$tabs.tabs('select', 1);
	})

	//保存和修改菜单
	$("#saveMenu").click(function(){
		//判断当前菜单下是否存在默认菜单
		if(!$('#MenuList').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		if($("#isDefault").val()==1){
			var menuParentId =  $("input[id=parent.objId]").val();
			$.getJSON("MenuController.do?method=checkIsDefault",{'menuId':menuParentId},function(json){
				if(json.failure) return;
				if(json.result){
					if(json.result == 'true'){
						alert("此菜单下已存在默认菜单");
					}else{
						MenuList.submit();
					}
				}
			});
		}else{
			MenuList.submit();
		}
	})
	
	//进入修改角色页面
	$("#modifyMenu").click(function(){
		MenuList.modifyMenu();
		$('#menuForm').hide();
		//打开修改页
		if(menuId != ""){
			$('#menuDetail').hide();
			$('#menuForm').show();
			//$tabs.tabs('select', 1);
		}
	})
	
	
	
	//查询上级菜单
	$("input[id=parent.name]").click(function(e){
		$.epsDialog({
	        title:'选择上级菜单',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=parent&className=Menu',
	        width: '500',
	        height: '400',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
	    }); 
 	})	
 	
 	//查询授权资源
	$("input[id=resource.name]").click(function(e){
		$.epsDialog({
	        title:'选择关联资源',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=resource&className=Resource',
	        width: '500',
	        height: '400',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
	    }); 
 	})
 	
 	//删除菜单
 	$("#deleteMenu").click(function(){
 		if(window.confirm("是否确定删除此菜单?")){
			$.getJSON("MenuController.do?method=deleteMenu",{'menuId':menuId},function(json){
				if(json.failure){ 
					alert("操作失败") 
					return;
				}
				if(json.success){
					if(json.result=='true'){
						menutree.deleteItem(menuId,true);
						$('#menuDetail ul>li>span').empty();
					}else{
						alert("请选择菜单再进行删除操作");
					}
				}
	    	})
 		}
	})
	
	//节点上移
   	$("#up").click(function(){
   		if(MenuList.isSort==0){
   			MenuList.isSort=1;
   	   		if(treeUtil.sortUp(menutree,"up","com.gpcsoft.srplatform.auth.domain.Menu"))
   	   			MenuList.isSort=0;
   		}
   		
	})
	//节点下移
	$("#down").click(function(){
		if(MenuList.isSort==0){
			MenuList.isSort=1;
			if(treeUtil.sortDown(menutree,"down","com.gpcsoft.srplatform.auth.domain.Menu"))
				MenuList.isSort=0;
   		}
		
	})

})
