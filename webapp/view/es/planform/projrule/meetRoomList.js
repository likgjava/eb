
var meetRoomList={};
meetRoomList.rows=null;//列表查询的结果集
	//新增
	meetRoomList.add=function(name,grid){
		$.epsDialog({
			width: '600',
		    height: '200',
	        title:'评标室',
	        url:$('#initPath').val()+"/MeetRoomController.do?method=toCreateOrUpdate",
	        onClose: function(){ 
	        	$(grid).reload();//刷新
	        }
		});
	}   
	//修改
	meetRoomList.update=function(name,grid){
		if(!meetRoomList.validation(name,grid))return;
		//跳转到修改页面
		var objId = $(grid).getSelect();
		$.epsDialog({
			width: '600',
		    height: '200',
	        title:'评标室',
	        url:$('#initPath').val()+"/MeetRoomController.do?method=toCreateOrUpdate&objId="+objId,
	        onClose: function(){ 
	        	$(grid).reload();//刷新
	        }
		});
	}   
	//删除
	meetRoomList.remove=function(name,grid){
		if(!meetRoomList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=removeMeetRoomByojbId',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	meetRoomList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	meetRoomList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#meetRoomGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	meetRoomList.success=function(){
		//alert('load complete!');
		//('#meetRoomGrid').selectedRows(['id1', 'id2']);//选中用户已拥有的行	参数ID数组
		//if(meetRoomList.rows==null) meetRoomList.rows=$('#meetRoomGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{id1:1, name1:2},{id2:11, name2:22}]
	}
$(document).ready(function(){
});

