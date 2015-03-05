/*
 * 平台开发demo
 * author: wangsw
 * mail: sinlff@163.com
 */
$(document).ready(function(){
    $('#DepartmentTable').flexigrid(   {   
	    //url:'/view/resource/plug-in/flexigrid/post2.txt',    模拟的文本数据
	    url: '/DepartmentController.do?method=Department',
	    queryColumns : 'objId,usrCnName',//要查询的列
	    dataType: 'json',   
	    colModel : [   
	        {display: 'ID', name : 'objId', width : 180, sortable : true, align: 'left'},
	        {display: '职位名称', name : 'usrCnName', width : 100, sortable : true, align: 'left'}  
	        ],   
	    buttons : [   
	        {name: 'Add', bclass: 'add', onpress : test},   
	        {name: 'Delete', bclass: 'delete', onpress : test},   
	        {separator: true}   
	        ],   
	    usepager: true,   
	    title: '最新职位',   
	    useRp: true,   
	    rp: 2,   
	    showTableToggleBtn: true,   
	    width: 700,   
	    height: 200   
	    }   
    );   
    function test(com,grid) {   
    	if (com=='Delete')   {   
    		$.ajax({
    			url:$('#initPath').val()+"/UserControllerTests.do?method=remove",
    			type:'post',
    			dataType:'json',
    			data:{
    				'objId':$('#myDepartmentTable').getSelectId()		//myListTable是当前表格的id
    			},
    			success:function(msg){	
    				if(msg.result!=undefined)
    					alert(msg.result);
    			},
    			error:function(msg){
    				alert('操作失败!');
    			}
    		});
        } else if (com=='Add') {   
            alert('Add New Item');   
        }              
    }   
});