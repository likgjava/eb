<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<form id="ruleSearchZone" >
	
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
		    		
			<li>
	  	        <label for="dictionary.dicName">积分来源</label>	  	        
               <html:select selectedValue="${sourceCode}" id="sourceCode" name="sourceCode" code="com.gpcsoft.smallscale.point.domain.Rule.sourceCode"></html:select>
                <input type="hidden" name="sourceCode_op" value="=">
            </li>
            <li>
	  	        <label for="currentStatus">有效状态</label>	 
	  	        <select name="currentStatus" id="currentStatus" >
                        <option value=''>--全部--</option>
				  		<option value='1'>有效</option>
						<option value='0'>无效</option>						
				</select>  	              
                <input type="hidden" name="currentStatus_op" value="=">
           </li>		
          	
		  <li class="operationBtnDiv right"><button type="button" id="query"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

 <div class="formTips attention">
	<ul>
		<li>		
		</li>
		<li>添加积分规则请点击		
			<span class="sysicon siAdd"><a href="javascript:void(0);" id="ruleAddBtn"><strong>添加规则</strong></a></span>			
		</li>
	</ul>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">

  <div id="ruleInfo">   
    <table id="ruleList" class="frontTableList">
      <thead>
      	<tr>      	  
      	  <th class="left">积分来源</th> 
          <th class="left">积分方式</th> 
          <th class="left">额度</th>
          <th class="left">百分比</th>
          <th class="left">累加方式</th>               
          <th class="date center">创建日期</th>  
          <th class="operation center">操作</th>       
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>

<script>
	var ruleUserList={};
	//生成列表中每条数据的操作：生成“修改”、“删除”
	ruleUserList.getOperatorStr=function(objId,currentStauts){
		var tdStr = '<td><a href="javascript:void(0);" onclick="ruleUserList.openOperatorPage(\''+objId+'\');return false;"><span>修改</span></a>';
		//tdStr += '<a href="javascript:void(0);" onclick="ruleUserList.openOperatorPage(\''+objId+'\')"><span>查看</span></a>';
		tdStr += '<a href="javascript:void(0);" onclick="ruleUserList.deleteOperatorPage(\''+objId+'\');return false;"><span>删除</span></a>';
        
		if ("1"==currentStauts) {
			tdStr += '<a href="javascript:void(0);" onclick="ruleUserList.updateOperatorPage(\''+objId+'\',\''+currentStauts+'\');return false;"><span>停用</span></a>';
		}
		else if("0"==currentStauts){
			tdStr += '<a href="javascript:void(0);" onclick="ruleUserList.updateOperatorPage(\''+objId+'\',\''+currentStauts+'\');return false;"><span>启用</span></a>';
		}
		

		tdStr += '</td>';		
		return tdStr;

	}

	//修改
	ruleUserList.openOperatorPage=function(objId){	
		$('#conBody').loadPage($('#initPath').val()+'/RuleController.do?method=toCreateOrUpdate&objId='+ objId );		
	}	

	//删除
	ruleUserList.deleteOperatorPage=function(objId){	
		if(confirm('确认删除？')){
			$.getJSON($("#initPath").val()+"/RuleController.do?method=remove",{"objId":objId},function(json){
				if(json.success){
					ruleUserList.oTable1.fnDraw();//刷新列表
				}
			})
		}
				
	}

	//启用，停用
	ruleUserList.updateOperatorPage=function(objId,currentStatus){	
		var mss = "";		
		if(currentStatus=='0'){
			mss = "确认启用！";
			currentStatus = '1';
		}
		else if(currentStatus=='1') {
			mss = "确认停用！";
			currentStatus = '0';
		}
		if(confirm(mss)){			
			$.getJSON($("#initPath").val()+"/RuleController.do?method=saveWithStatus",{"objId":objId,"currentStatus":currentStatus},function(json){
				if(json.success){
					ruleUserList.oTable1.fnDraw();//刷新列表
				}
			})
		}
		//$('#conBody').loadPage($('#initPath').val()+'/ruleController.do?method=saveWithStatus&objId='+ objId + '&currentStatus='+currentStatus);		
	}
	
	ruleUserList.oTable1;	
	
	$(document).ready(function(){
        
		$("#ruleAddBtn").click(function() {			
			$('#conBody').loadPage($('#initPath').val()+"/RuleController.do?method=toCreateOrUpdate");
		});
			
		//加载tabs
		var $tabs = $('#epsTabs').tabs({}); 		
		//
		
		ruleUserList.oTable1=$('#ruleList').dataTable( {
			'singleSelect':true,//(false表示可以多选)
			'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
			'queryColumns':'sourceCode,pointWay,pointNumber,pointPercent,pointSign,createTime',//指定要查询的列 createUser.emp.name,pubDate,
			//'alias':'sourceCode,pointWay,pointNumber,pointPercent,pointSign,currentStatusCN,createTime',
			'hiddenColumns':'objId,currentStatus',//隐藏查询不显示的列属性
			'fnInitComplete':function(oSettings) {
				 //表格初始化完毕、未开始查询之前的方法
			},
			'fnDrawCallback':function(oSettings) {	
				ruleUserList.oTable1.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件		
				$(nRow).append(ruleUserList.getOperatorStr(aData.objId,aData.currentStatus))			
				return nRow;
			},
			params:"",
			"sAjaxSource": $('#initPath').val()+"/RuleController.do?method=list",
			'searchZone':'ruleSearchZone'
		});		
		
		//查询
		$("#query").click(function(){	
			ruleUserList.oTable1.fnDraw();
		});	
	});	
</script>

