
var bailRecordTreeForm={};
bailRecordTreeForm.tree;
bailRecordTreeForm.isSort=0;
bailRecordTreeForm.currentObj;

//点击目录树操作
bailRecordTreeForm.edit=function(objId){
	if(objId==''){
		return;
	}	
	if(objId=='-1'){
		$('#bailRecordForm')[0].reset();
	}else{
		$.getJSON($('#initPath').val()+'/BailRecordController.do?method=createOrUpdate',{"objId":objId},function(json){
    		jsonObjectToForm('bailRecordForm',json.bailRecord);
    	});	
	}
}

function treeTab(){
	treeHe();
	$(window).wresize(treeHe);
	var h =$('#contentThis').height();
	$('.treeOutside').jqResize('.treeResize',function(w){if(parseInt(w)<240){$('.treeOutside').width(240);}	$('.treeOutside').height(h)});
	function treeHe(){$('.treeOutside,.treeRight,.treeResize').height($('#contentThis').height());}
}

//点击节点事件
bailRecordTreeForm.nodeClick=function(id){
	var queryColumns=[];
	$.getJSON($('#initPath').val()+'/BailRecordController.do?method=createOrUpdate&includedProperties='+queryColumns.toString(),{objId:id},function(json){
		bailRecordTreeForm.currentObj=json.bailRecord;
		json2Object('bailRecordForm', json.bailRecord);
		jsonObjectToForm('bailRecordForm', json.bailRecord);
	});
}

//初始化树
bailRecordTreeForm.initBailRecordTree=function(){
	//dhtmlXTreeObject(htmlObject, width, height, rootId)//rootId 虚拟根节点，在界面上不会显示，一般取值0
	bailRecordTreeForm.tree=new dhtmlXTreeObject('bailRecordTree',"100%","100%",0);
	bailRecordTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	//bailRecordTreeForm.tree.enableCheckBoxes(1);
	bailRecordTreeForm.tree.enableDragAndDrop(1);
	bailRecordTreeForm.tree.enableThreeStateCheckboxes(true);
	bailRecordTreeForm.tree.setOnClickHandler(bailRecordTreeForm.nodeClick);
	bailRecordTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/BailRecordController.do?method=getTree&action=listById');//点 + 号触发展开事件
	bailRecordTreeForm.tree.loadXML($('#initPath').val()+'/BailRecordController.do?method=getTree&action=listTop&id=0',function(){
		//初始化树之后的回调函数
	});
}

$(document).ready(function(){	
	$('#addBailRecord').click(function(){//新增
		$('#bailRecordDetail').hide();
		$('#bailRecordFormDiv').show();
		$('#bailRecordForm div input').val('');
		$('#bailRecordForm input[name=objId]').val('');
		$('#bailRecordForm input[name=parent.objId]').val(bailRecordTreeForm.tree.getSelectedItemId());
	});
	$('#updateBailRecord').click(function(){//修改
		$('#bailRecordDetail').hide();
		$('#bailRecordFormDiv').show();
		jsonObjectToForm('bailRecordForm', bailRecordTreeForm.currentObj);
	});
	$('#deleteBailRecord').click(function(){//删除
		$('#bailRecordForm')[0].reset();
		bailRecordTreeForm.currentObj=null;
	});
	$('#bailRecordReturn').click(function(){
		$('#bailRecordDetail').show();
		$('#bailRecordFormDiv').hide();
	});

	$('#bailRecordForm').validate();//表单验证
	bailRecordTreeForm.initBailRecordTree();//加载树
	//提交
	$('#bailRecordSave').click(function(){
		if(!$('#bailRecordForm').valid()){alert('请正确填写表单!');return;}
		var bailRecord=formToJsonObject('bailRecordForm');
		bailRecord['parent.isLeaf']='0';
		$.getJSON($('#initPath').val()+'/BailRecordController.do?method=save',bailRecord, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			var pid=$("input[id=parent.objId]").val();
			var id=$('#objId').val();
			if(bailRecordTreeForm.tree._globalIdStorageFind(json.bailRecord.objId)){
							bailRecordTreeForm.setItemText(json.resource.objId,json.bailRecord.);
			}else{
				var selectedItemId = bailRecordTreeForm.tree.getSelectedItemId();
				if(!selectedItemId)selectedItemId=-1;
				bailRecordTreeForm.tree.insertNewItem(selectedItemId,json.bailRecord.objId,json.bailRecord.,0,0,0,0,'SELECT');
				bailRecordTreeForm.tree.setItemImage2(selectedItemId,"node.gif","folderOpen.gif","folderClosed.gif");
			}
			alert('保存成功');
			$('#bailRecordForm div input').val('');
			$("input[name=parent.objId]").val(pid);
			$('#objId').val(id);
		});
	});
	//删除节点
   	$('#bailRecordDelete').click(function(){
   		if($.trim($('#objId').val())!=""){
   			if(window.confirm("确定删除?")){
	   			$.getJSON($('#initPath').val()+'/BailRecordController.do?method=remove',{objId:$('#objId').val()},function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					treeUtil.refreshTree(bailRecordTreeForm.tree,'delete','');
					$('#bailRecordForm')[0].reset();
				});
			}
   		}
   	});
	//设置拖拽事件
	bailRecordTreeForm.tree.setDragHandler(function(id,newParentId){
		if(bailRecordTreeForm.isSort==1)return true;
   		var jsonObj={};
   		jsonObj.sort=bailRecordTreeForm.tree._globalIdStorageFind(newParentId).childsCount;
   		if(newParentId == "-1")newParentId = null;
   		jsonObj.objId=id;jsonObj.parent={};jsonObj.parent.objId=newParentId;
   		jsonObj.json=JSON.stringify(jsonObj);
		$.getJSON($('#initPath').val()+'/bailRecordController.do?method=save', jsonObj, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
		});
		return true;
   	});
   	//展开合并所有节点
   	var isOpen = false;  //是否是展开状态
	var isFirst = true;  //是否是第一次展开
	$('#openAll').click(function(){
		if(!isOpen){
			if(isFirst){
				bailRecordTreeForm.tree.deleteChildItems(0);//删掉根节点的所有子节点
				bailRecordTreeForm.tree.loadXML($('#initPath').val()+'/BailRecordController.do?method=getTree&action=listTop&isOpen=1');
				isFirst = false;
			}
			else bailRecordTreeForm.tree.openAllItems('0');
			$('#openAll').html('关闭');
			isOpen=true;
		}else{
			bailRecordTreeForm.tree.closeAllItems('0');
			$('#openAll').html('展开');
			isOpen=false;
		}
	});
   	//增加下级节点
   	$('#newChild').click(function(){ 
   		if(bailRecordTreeForm.tree.getSelectedItemId() == null || bailRecordTreeForm.tree.getSelectedItemId() == '')
  			alert("请先选中一个节点，再进行该操作！");
  		else if(bailRecordTreeForm.tree.getSelectedItemId() != "-1"){
  			var pid=$('#objId').val();
  			$('#bailRecordForm')[0].reset();
  			$('input[id=parent.objId]').val(pid);
  		}
   	});
   	//节点上移
   	$('#bailRecordUp').click(function(){
   		bailRecordTreeForm.isSort=1;
   		if(myTreeSort('up_strict',bailRecordTreeForm.tree,'up','down',$('#initPath').val()+'/SortController.do?method=updateSort&className=com.gpcsoft.es.planform.bid.domain.BailRecord','sort'))
   	    	bailRecordTreeForm.isSort=0;
	});
	//节点下移
	$('#bailRecordDown').click(function(){
		bailRecordTreeForm.isSort=1;
		if(myTreeSort('down_strict',bailRecordTreeForm.tree,'up','down',$('#initPath').val()+'/SortController.do?method=updateSort&className=com.gpcsoft.es.planform.bid.domain.BailRecord','sort'))
	    	bailRecordTreeForm.isSort=0;
	});
});
