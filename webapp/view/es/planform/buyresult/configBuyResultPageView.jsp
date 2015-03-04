<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
       <div class="formLayout form2Pa" style="width: 97%;"> 
       <ul>
        <li>
           <label class="short" for="projName"><spring:message code="projectForm.projName"/>：</label>
							<span id="projName">${project.projName}</span>
        </li>
        <li >
           <label class="short" for="projCode"><spring:message code="projectForm.projCode"/>：</label>
							<span id="projCode">${project.projCode}</span>
        </li>
        <li >
           <label class="short" for="projCode"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称：</label>
                          <c:if test="${projectRule.isDividePack==false}">
                         	 <span id="projCode">项目未拆分</span>
                          </c:if>
                          <c:if test="${projectRule.isDividePack==true}">
                         	 <span id="projCode">${subProject.projName}</span>
                          </c:if>
							
        </li>
        <li>
           <label class="short" for="projCode">评审报告：</label>
						<span id="projCode">${bulletin.bullTitle}</span>
        </li>
      </ul>     
       </div>
   
     <div class="formLayout" style="width: 97%;">
        <table class="tableList" id="SubProjectList" >
		<caption>投标单位列表</caption>
  		<thead>
      		<tr>
          		<th class="center">投标单位名称</th>
          		<th style="width: 18%">报价金额(元)</th>
          		<th style="width: 18%">排序</th>
          		<th class="operation" style="width: 10%">选择投标单位</th>
     		</tr>
		</thead>
	<tbody>
	<c:set value="0" var="subProjectCount"/>
	<c:forEach items="${openBidRecordList}" var="openBidRecord" >
		<tr>
			<td id="supplierName_${subProjectCount}">${openBidRecord.supplier.orgName}
			</td>
			<td id="quoteSum_${subProjectCount}" class="amount"><fmt:formatNumber value="${openBidRecord.quoteSum}"  pattern="￥#,#00.00#"></fmt:formatNumber></td>
			<td></td>
			<td class="choose">
			  <input type="checkbox" id="${openBidRecord.supplier.orgName}"  <c:if test="${isConfig== 'YES'}"> disabled="disabled" </c:if> <c:if test="${openBidRecord.isSelect==00}">checked="checked"</c:if>  name="selectSupplier" value="${openBidRecord.supplier.objId}">    
			</td>
		</tr>
		<c:set value="${subProjectCount+1}" var="subProjectCount"/>
	</c:forEach>
	</tbody>
    </table>   	
    </div>
<script language="javascript">
  function showInputWordCount(){
	  var count = $('#buyrOpinion').val().length;
	  $('.eleRequired').text("已输入"+count+"个文字。");
  }
  $(document).ready(function(){	
	 
    //返回
    $('#returnButton').click(function(){
   	  	$('#epsDialogClose').click();
    });
   //提交
   $('#submitButton').click(function(){
     var supplierIds = '';
     var allSupplierIds = '';
     $(":checkbox[name='selectSupplier']").each(function(){
	   	var allSupplierId = $(this).val();	
	   	allSupplierIds += allSupplierId+',';
	 })
	$(":checkbox[name='selectSupplier']:checked").each(function(){
		var supplierId = $(this).val();	
    	supplierIds += supplierId+',';
	})
   	if(!$('#buyResultForm').valid()){alert('请正确填写表单!');return;}
    if(supplierIds==''){alert('请至少选择一家投标单位!');return;}
	if(window.confirm("确认提交?")){
		$.getJSON($('#initPath').val()+'/BuyResultController.do?method=saveOrUpdateBuyResult&supplierIds='+supplierIds+'&allSupplierIds='+allSupplierIds, formToJsonObject('buyResultForm'), function(json){
	 		if(json.result)alert(json.result);if(json.failure)return;
	 		$('#epsDialogCloseReload').click();
				  
	 	});
   	}
	});
});
</script>