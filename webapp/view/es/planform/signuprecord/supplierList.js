
var signUprecordList={};
signUprecordList.rows=null;//列表查询的结果集

	
	
	//列表操作验证
	signUprecordList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	signUprecordList.before=function(){
		var option={
			//'confirmStatus':'00'//查询条件，过滤待确认的投标单位
		}
		$('#signUprecordGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	signUprecordList.success=function(){
		//加入操作字符,回调id
		$("#signUprecordGrid").flexAddOptionStr({
//			 '<button  type="button"><span>确认</span></button>' : function(btn,rowId,obj){
//				var rowObj = $('#signUprecordGrid').getRowById(rowId);
//				if("00" == rowObj.auditStatus){//若为待确认状态，则显示灰化确认按钮
//				   	btn.click(function(){
//				    	//alert('修改'+id);
//				    	$.epsDialog({
//				            title:"确认投标单位",
//				            url:$("#initPath").val()+"/SignUprecordController.do?method=toConfirmPage&objId="+rowId,
//				            width: 1000,
//				            height: 330,
//				            isReload: false,
//				            onClose: function(){
//				    			$("#signUprecordGrid").flexReload();//刷新;
//				           	}
//				    	});
//					 }).appendTo(obj);
//				}else{
//					btn.attr("disabled","disabled");
//					btn.appendTo(obj);
//				}
//		},
		'<button type="button"><span>查看</span></button>' : function(btn,rowId,obj){
		     btn.click(function(){
		    	//alert('修改'+id);
		    	$.epsDialog({
		            title:"查看投标单位",
		            url:$("#initPath").val()+"/SignUprecordController.do?method=toViewSignupPage&objId="+rowId,
		            width: 800,
		            height: 250,
		            isReload: false
		    	});
			 }).appendTo(obj);
		}
		});
	}
$(document).ready(function(){
});

