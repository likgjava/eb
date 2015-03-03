var channelTreeForm = {};
var userList = {};
var rootId = "";
var checkCount = 0;
var channelId = "";
var checkedChannelId = "";
var checkedRealChannelId = "";
var partiallyCheckedChannelId = "";
//点击节点事件
channelTreeForm.nodeClick=function(id){
	channelId = "";
	if(checkedChannelId!=""){
		for(var i = 0; i<checkedChannelId.split(",").length;i++){
			channelTreeForm.tree.setCheck(checkedChannelId.split(",")[i],0);//设置全部选中
		}
	}
	
	$('#channelRoleTabs').tabs('select',0);
	$('#channelForm').find("input[type=text]").val("");
	if("" == id || "-1" == id){$('a[id^=channelRoleType_]').parent().hide();return false;}
	$('#channelReturn').click();
	$('input').removeClass('eleWarning');
	$('span[class=eleWarning]').remove();
	channelId = id;
	$('a[id^=channelRoleType_]').parent().show();
	var queryColumns=["parent","channelModel"];
	$.getJSON($('#initPath').val()+'/ChannelController.do?method=createOrUpdate&includedProperties='+queryColumns.toString(),{objId:id},function(json){
		json2Object('channelInfoForm', json.channel,true);
		
		if(json.channel.checkCount && json.channel.checkCount > 0){
			checkCount = json.channel.checkCount;
			//$('#channelRoleType_02').parent().show();
		}else{
//			$('#channelRoleType_02').parent().hide();
		}
		
	});
	
}


// 初始化栏目树
channelTreeForm.initChannelTree=function(){
	channelTreeForm.tree=new dhtmlXTreeObject('channelTree',"100%","100%",0);
	channelTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	channelTreeForm.tree.enableCheckBoxes(1);
	channelTreeForm.tree.enableDragAndDrop(0);
	channelTreeForm.tree.enableThreeStateCheckboxes(true);
	channelTreeForm.tree.setOnClickHandler(channelTreeForm.nodeClick);
	channelTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/ChannelController.do?method=getTree&action=listById&order=sort');//点 + 号触发展开事件
	channelTreeForm.tree.loadXML($('#initPath').val()+'/ChannelController.do?method=getTree&action=listTop&id=0&order=sort&isOpen=1',function(){
	
		$('#channelTree').find('tbody').find("tr>td:eq(1)").hide();
		$('td.standartTreeRow').click(function(){
			if(channelId=="")return false;
			$(this).parent().find("td:eq(1)").find('img').click();
			checkedChannelId = (channelTreeForm.tree.getAllCheckedBranches()).replace('-1','')
			checkedRealChannelId = channelTreeForm.tree.getAllChecked().replace('-1','');//选中的，不包括第三部分
			partiallyCheckedChannelId = channelTreeForm.tree.getAllPartiallyChecked().replace('-1','');//部分选中的节点
		});
	});
}



$(document).ready(function(){
	
	$('#channelRoleTabs').tabs();
	
	// 点击tab页
	$('a[id^=channelRoleType_]').click(function(){
		$('input[id=channelRoleType]').val($(this).attr('id').replace('channelRoleType_',''));
		if($(this).attr('id').replace('channelRoleType_','')=='02'){
			//审核角色
			var checkRoleTab = "";
			checkRoleTab += '<div id="fileTabs">';
			checkRoleTab += '<ul id="checkRoleStep">';
			for(var i = 1; i <= 5; i++){
				checkRoleTab += '<li><a href="#checkRoleFormPage" id="checkStatus_0'+i+'"><span>第'+i+'级审核人</span></a></li>';
			}
			checkRoleTab += '</ul>';
			checkRoleTab += '<div id="checkRoleFormPage"></div>';
			checkRoleTab += '</div>';
			$('#channelRolePage').empty().append(checkRoleTab).find('[id=fileTabs]').tabs().end().find('a').click(function(){
				$('#checkRoleFormPage').loadPage($('#initPath').val()+'/view/cms/channel/assign_channel_role.jsp?checkStatus='+$(this).attr('id').replace('checkStatus_',''))
			}).end().find('a:first').click();
		}else{
			$('#channelRolePage').loadPage($('#initPath').val()+'/view/cms/channel/assign_channel_role.jsp?checkStatus=')
		}
	});
	
	
	channelTreeForm.initChannelTree();
	
	//列表操作验证
	userList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择用户'+name,'提示',{icon:'info'});return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name,'提示',{icon:'info'});return false;}//是否只选中一个
		return true;
	}
	userList.before=function(){//查询条件过滤
		var option={};
		$('#userGrid').flexOptions({params:option});
		return true;
	}
	
	userList.detail=function(id){
		$.epsDialog({
		        title:'账号信息',
		        url:$('#initPath').val()+'/view/srplatform/auth/float/userDetail.jsp?usName='+id,
		        width: '500',
		        height: '400',
		        onOpen: function(){ },
		        afterLoad: function(){ },
		        onClose: function(){ }
		    }); 
	}
	
})
