//用以恢复项目实施的
$('#projectSave0').click(function(){
				var objId=$('#objId').val();
				var projImplStatus='00';
				if($('#projImplStatus').val()=='02')
				{
					alert('项目已处于终止状态，无法进行任何操作！');
				}
				else if($('#projImplStatus').val()==projImplStatus)
				{
					alert('项目已处于正常状态，无需此操作！');
				}
				else if(window.confirm("确认要恢复项目吗?"))
				{
						$("#agentTabDiv").empty().loadPage($('#initPath').val()+'/ProjectController.do?method=updateProjImplStatus&projImplStatus='+projImplStatus+'&projectId='+objId);
						alert("项目恢复成功！");
				}
			});
//用以暂停项目实施的
$('#projectSave1').click(function(){
		var objId=$('#objId').val();
		var projImplStatus='01';
		if($('#projImplStatus').val()=='02')
		{
			alert('项目已处于终止状态，无法进行任何操作！');
		}
		else if($('#projImplStatus').val()=='01')
		{
			alert('项目已处于暂停状态，无需此操作！');
		}
		else if(window.confirm("确认要暂停项目吗?"))
		{
				$("#agentTabDiv").empty().loadPage($('#initPath').val()+'/ProjectController.do?method=updateProjImplStatus&projImplStatus='+projImplStatus+'&projectId='+objId);
				alert("项目暂停成功！");
		}
	});	
//用以终止项目实施的
$('#projectSave2').click(function(){
		var objId=$('#objId').val();
		var projImplStatus='02';
		if($('#projImplStatus').val()==projImplStatus)
		{
			alert('项目已处于终止状态，无法进行任何操作！');
		}
		else if(window.confirm("确认要终止项目吗?"))
		{
			$("#agentTabDiv").empty().loadPage($('#initPath').val()+'/ProjectController.do?method=updateProjImplStatus&projImplStatus='+projImplStatus+'&projectId='+objId);
			alert("项目终止成功！");
		}
	});	

    $(document).ready(function(){
    	var objId=$('#objId').val();
    	//alert(objId);
    	//加载修改项目时间页面
    	$("#updateProjectTime").empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toUpdateProjectTime&projectId='+objId);
    	
    	//加载修改评审时间页面
    	$("#updateProjectEvalTime").empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toUpdateProjectEvalTime&projectId='+objId);
    })


