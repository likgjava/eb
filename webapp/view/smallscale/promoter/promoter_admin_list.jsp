<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="promoterAdminSearchZone">
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>	
			<li>
	  	        <label for="promoterUnitName">采购人公司名称：</label>	  	        
                <input type="text" name="promoterUnitName" id="promoterUnitName" />
                <input type="hidden" name="promoterUnitName_op" value="like" />
			</li>	          	
			<li class="operationBtnDiv right"><button type="button" id="query"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>
<div id="btnDiv" class="formTips attention">
	<ul>
		<li><em>提示：</em>添加已推广企业请点击<span class="sysicon siAdd"><a href="javascript:void(0);" id="addBtn"><strong>添加已推广企业</strong></a></span></li>			
	</ul>
</div>
<div id="epsTabs">
	<div id="promoterInfo">   
		<table id="promoterList" class="frontTableList">
		<thead>
			<tr>
				<th class="left">采购大使姓名 </th>            
				<th class="left omission" omiLength="12">采购人公司名称</th>
				<th class="left">采购人名称</th>
				<th class="center">来源</th>
				<th class="center">验证码</th>
				<th class="date center">创建日期</th>      
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody></tbody>
		</table>
	</div>
</div>

<script>
var promoterUserList={};
promoterUserList.oTable1;	

//生成“删除”，“确认” 超链接
promoterUserList.getOperatorStr=function(objId,dealStatus){
	var appendStr = '<td class="operation">';	
	if(dealStatus=="已确认")	{
		appendStr +=  '<a href="javascript:void(0);" onclick="promoterUserList.dealOperatorPage(\''+objId+'\');return false;"><span>查看</span></a>';
	}else{
		appendStr +=  '<a href="javascript:void(0);" onclick="promoterUserList.dealOperatorPage(\''+objId+'\');return false;"><span>确认</span></a>';
	}
	appendStr += '<a href="javascript:void(0);" onclick="promoterUserList.deleteOperatorPage(\''+objId+'\');return false;"><span>删除</span></a>';	
	appendStr += "</td>";

	return appendStr;	
}

//确认
promoterUserList.dealOperatorPage=function(objId){		
	$.epsDialog({
		title:'确认已推广企业',
		url:$('#initPath').val()+'/PromoterController.do?method=toAdminDealView&objId='+ objId,
		width: '600',
		height: '350'
	});			
}

//删除
promoterUserList.deleteOperatorPage=function(objId){	
	if(confirm('确认删除？')){
		$.getJSON($("#initPath").val()+"/PromoterController.do?method=remove",{"objId":objId},function(json){
			if(json.success){
				promoterUserList.oTable1.fnDraw();					
			}
		});
	}		
}
	
$(document).ready(function(){		
	//加载tabs
	var $tabs = $('#epsTabs').tabs({}); 		
		
	promoterUserList.oTable1=$('#promoterList').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'promoterName,promoterUnitName,promotedLinkName,recordType,validationCode,createTime',//指定要查询的列 content,
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件		
			$(nRow).append(promoterUserList.getOperatorStr(aData.objId,aData.dealStatus))//添加操作按钮			
			return nRow;
		},
		params:{},
		"sAjaxSource": $('#initPath').val()+"/PromoterController.do?method=list&userType=admin",
		'searchZone':'promoterAdminSearchZone'
	});
      
	//录入推广企业
	$("#addBtn").click(function() {
		$.epsDialog({
	        title:'录入已推广企业',
	        url:$('#initPath').val()+'/PromoterController.do?method=toAdminRecord',
	        width: '600',
	        height: '350'
	    });
	});
		
	//查询
	$("#query").click(function(){	
		promoterUserList.oTable1.fnDraw();
	});
	
});
</script>