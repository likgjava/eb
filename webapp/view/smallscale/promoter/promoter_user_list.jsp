<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="promoterUserSearchZone">
	<input type="hidden" name="fromWeb" id="fromWeb" value="like">
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>	
			<li>
	  	        <label for="promoterUnitName">公司名称：</label>	  	        
                <input type="text" name="promoterUnitName" id="promoterUnitName" />
                <input type="hidden" name="promoterUnitName_op" value="like" />
			</li>	
			<li class="operationBtnDiv right"><button type="button" id="query"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>
<c:if test="${fromWeb != 'yes'}">
<div id="sendMailDiv" class="formTips attention">
	<ul>
		<li><em>提示：</em>若要邀请采购企业请点击<span class="sysicon siAdd"><a href="javascript:void(0);" id="sendMailBtn"><strong>我要推广</strong></a></span></li>			
	</ul>
</div>
</c:if>
<div id="epsTabs">
	<div id="promoterInfo">   
		<table id="promoterList" class="frontTableList">
		<thead>
			<tr>
				<th class="left omission" omiLength="25">公司名称</th>
          		<th class="left">姓名</th>                              
          		<th class="center">来源</th>            
          		<th class="center">确认状态</th> 
          		<th class="center">验证码</th>     
          		<th class="center date">创建时间</th>  
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
promoterUserList.getOperatorStr=function(objId,dealStatus,recordType){			
	var appendStr = '<td class="operation">';		
	if(dealStatus=="已确认" || recordType=="采购大使记录")	{
    	appendStr +=  '<a href="javascript:void(0);" onclick="promoterUserList.dealOperatorPage(\''+objId+'\');return false;"><span>查看</span></a>';
    }else{
    	appendStr +=  '<a href="javascript:void(0);" onclick="promoterUserList.dealOperatorPage(\''+objId+'\');return false;"><span>确认</span></a>';
    }			
	appendStr += '<a href="javascript:void(0);" onclick="promoterUserList.deleteOperatorPage(\''+objId+'\');return false;"><span>删除</span></a>';	
	appendStr += "</td>";

	return appendStr;	
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

//确认
promoterUserList.dealOperatorPage=function(objId){
	$.epsDialog({
        title:'确认已推广企业',
        url:$('#initPath').val()+'/PromoterController.do?method=toDealView&objId='+ objId,
        width: '450',
        height: '400'
    });					
}		

$(document).ready(function(){	
	//加载tabs
	var $tabs = $('#epsTabs').tabs({}); 		
	//推广
	promoterUserList.oTable1=$('#promoterList').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'promoterUnitName,promotedLinkName,recordType,dealStatus,validationCode,createTime',//指定要查询的列 content,
		'hiddenColumns':'objId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append(promoterUserList.getOperatorStr(aData.objId,aData.dealStatus,aData.recordType));//添加操作按钮	

			if(aData.dealStatus!="已确认" && aData.recordType!="采购大使记录"){//对方录入的数据，隐藏验证码
				$(nRow).find('td[name=validationCode]').html('<span ></span>');	
			}
				
			return nRow;
		},
		params:{},
		"sAjaxSource": $('#initPath').val()+"/PromoterController.do?method=list&userType=user",
		'searchZone':'promoterUserSearchZone'
	});	

	//发送邮件
	$("#sendMailBtn").click(function() {	
		$.epsDialog({
	        title:'推荐采购人',
	        url:$('#initPath').val()+'/PromoterController.do?method=toPromoterDoingPage',
	        width: '500',
	        height: '350'
	    });
	});	
	
	//查询
	$("#query").click(function(){	
		promoterUserList.oTable1.fnDraw();
	});

});
</script>