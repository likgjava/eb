<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>   
   <script language="javascript">
   var evalBidRecordListView = {};

   $(document).ready(function(){
		var isKinescopeOpenBid = $('#is_kinescope_openBid').val();
		if(isKinescopeOpenBid == "true"){
			$('#kinescope').attr("disabled",false);
		}else{
			$('#kinescope').attr("disabled",true);
		}
	})
  
   evalBidRecordListView.readyPutin = function() {
         alert("不能重复录入！");
   }
   evalBidRecordListView.beginevalBidRecord =function(count,supplierId,objId,bidId,quoteSum,subProjId) {
	   var flag = $('#openBidRecord').val();//判断是否投标结束
	   if (flag=='yes') {
	    	$.epsDialog({
	        	title:'录入',
	        	url:$('#initPath').val()+'/view/es/planform/projreview/newOpenBidRecordForm.jsp?count='+count+'&supplierId='+supplierId+'&objId='+objId+'&bidId='+bidId+'&quoteSum='+quoteSum+'&projectId='+$("#projectId").val()+'&subProjId='+subProjId,
	        	width: '900',
	        	height: '150',
		    	isReload:false,
		    	onClose: function(){
		   			planTemplateTask.refresh($("#projectTaskId").val()+"");
	      		}
			});	
		}else{
			alert('投标还未结束,请等待！');
		}
   }
   
   // 录入开标记录事件
   $("#kinescope").unbind("click");
   $("#kinescope").click(function(){
	   var flag = $('#openBidRecord').val();//判断是否投标结束
	   if (flag=='yes') {
		   showImg();
		   evalBidRecordListView.kinescopeOpenBidRecord($('#projectId').val());
		}else{
			alert('投标还未结束,请等待！');
		}
   })
   
   // 录入开标记录
   evalBidRecordListView.kinescopeOpenBidRecord = function(projectId){
	   var subProjectId = $("#pack_id").val();
	   $.getJSON($('#initPath').val()+'/OpenBidRecordController.do?method=saveOpenBidRecord', {"projectId":projectId,"subProjectId":subProjectId}, function(json){
		   if(json.result){
			   alert(json.result);
			}
		   if(json.failure){
			   closeImg();
			}
		   setTimeout("closeImg();",1000);
		   planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		    }
	   })
   }

	function showImg() {
    	$.epsDialog({
		    id :"showImg",
	        title:"录入开标记录",
	        url:$("#initPath").val()+"/view/es/planform/projreview/kinescopeBidRecord.jsp",
	        width: 400,
	        height: 150,
	        isReload: true
		});
	}	

	function closeImg(){
		$('#showImg').find(".epsDialogClose").click();
		$('#tab'+$('#pack_id').val()+' a ').click();
	}
	   
   </script>
   <input type="hidden" id="pack_id" value="${param.subProjId}"></input>
   <input type="hidden" id="is_kinescope_openBid" value="${isKinescopeOpenBid}"></input>
   	<table class="tableList" id="SubProjectList">
   	<h5><span>投标单位列表</span></h5>
		<div class="functionBtnDiv"></div>
  		<thead>
      		<tr>
          		<th class="center">投标单位名称</th>
          		<th>报价总金额(元)</th><!--
          		<th>状态</th>
          		<th class="operation">操作</th>
     		--></tr>
		</thead>
	<tbody>
	<input type="hidden" id="openBidRecord" value="${openBidRecord}">
	<c:set value="0" var="subProjectCount"/>
	<c:forEach items="${openBidRecordList}" var="openBidRecord" >
		<tr>
			<td id="supplierName_${subProjectCount}">${openBidRecord.sellerName}</td>
			<td id="quoteSum_${subProjectCount}" style="text-align: right;">
			<c:if test="${openBidRecord.openBRStatus == '00'}"></c:if>
			<c:if test="${openBidRecord.openBRStatus == '01'}"><fmt:formatNumber type="currency" value="${openBidRecord.quoteSum}"/></c:if>
			</td><!--
			<td>
			     <c:choose>
		      		<c:when test="${openBidRecord.openBRStatus == '01'}">已录入开标记录</c:when>
		      		<c:when test="${openBidRecord.openBRStatus == '00'}">未录入开标记录</c:when>
		      		<c:otherwise>未进行操作</c:otherwise>
		      	</c:choose>
			</td>
			<td>
			       <c:choose>
		      		<c:when test="${openBidRecord.openBRStatus == '01'}">
		      		<button class="sysicon siModify" onclick=" evalBidRecordListView.readyPutin();" title="已录入"><span>已经录入</span></button>
		      		</c:when>
		      		<c:when test="${openBidRecord.openBRStatus == '00'}">
		      		<button class="sysicon siModify" onclick="evalBidRecordListView.beginevalBidRecord('${subProjectCount }','${openBidRecord.supplier.objId}','${openBidRecord.objId}','${openBidRecord.bidId}','${openBidRecord.quoteSum}','${openBidRecord.subProjId}');" title="录入"><span>录入</span></button>
		      		</c:when>
		      	</c:choose>
			</td>
		--></tr>
		<c:set value="${subProjectCount+1}" var="subProjectCount"/>
	</c:forEach>
	</tbody>
    </table>
    <div class="conOperation">
		<button id="kinescope" tabindex="20"><span><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out></span></button>
	</div>