var voteTopicList={};
voteTopicList.rows=null;//列表查询的结果集


function openwindow(url,name,iWidth,iHeight)
{
	var url; //转向网页的地址;
	var name; //网页名称，可为空;
	var iWidth; //弹出窗口的宽度;
	var iHeight; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}

	// 新增
	voteTopicList.add=function(name,grid){
		
		// 跳转到新增页面
		$('#conBody').loadPage($('#initPath').val()+"/VoteTopicController.do?method=toCreateOrUpdate");
	} 
	
	// 修改
	voteTopicList.update=function(name,grid){
		if(!voteTopicList.validation(name,grid))return;
		
		// 跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/VoteTopicController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	
	// 删除
	voteTopicList.remove=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择投票主题'+name);return false;}//是否选中
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/VoteTopicController.do?method=remove',{objId:$(grid).getSelectArray()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	
	// 列表操作验证
	voteTopicList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择投票主题'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个投票主题'+name);return false;}//是否只选中一个
		return true;
	}
	// 查询条件过滤
	
	voteTopicList.before=function(){
		var option={
		}
		$('#voteTopicGrid').flexOptions({params:option});
		return true;
	}
	
	// 加载数据成功之后调用的函数
	voteTopicList.success=function(){
		$("#voteTopicGrid").flexGetColByName({
			'title' : function(id,t){
			var url = "VoteTopicController.do?method=toShowView&objId="+id
				$(t).html("<a href='javascript:void(0)'>"+$(t).html()+"</a>").find("a").click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/VoteTopicController.do?method=toShowView&objId='+id);
				});
			}
		})
	}
$(document).ready(function(){
});
$("span[id=voteTopicItemH5]").click(function(){
	if($(this).parent().next("ul").hasClass("eleHide")){
		$(this).parent().next("ul").removeClass("eleHide");
	}else{
		$(this).parent().next("ul").addClass("eleHide");
	}
})

 


