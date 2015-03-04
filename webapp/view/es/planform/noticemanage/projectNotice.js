
var noticeList={};
noticeList.rows=null;//列表查询的结果集
	//新增
	noticeList.add=function(name,grid){
		var projectId=$("#projectId").val();
		$("#projectContent").empty().loadPage($('#initPath').val()+"/NoticeController.do?method=toNoticeForm&projectId="+projectId);
	}   
	
	//发布
	noticeList.publish=function(name,grid){
		if(!noticeList.validation(name,grid))return;
		alert("发布成功");
	}   

	//列表操作验证
	noticeList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	noticeList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#noticeGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	noticeList.success=function(){
		$("#noticeGrid").flexGetColByName({
	 		'noticeType':function(id,t){
		 		if($(t).html()=="00"){
		 			$(t).html("成交通知书");
		 		}else if($(t).html()=="01"){
		 			$(t).html("结果通知书");
		 		}
			}
		});
	}
$(document).ready(function(){
});

