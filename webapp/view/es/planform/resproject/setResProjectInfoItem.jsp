<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="paper" style="overflow-x:auto" >
<p class="headInfo">&nbsp;</p>
<h5 align="center"><span>项目招标事项申请</span></h5>
	<table width="400px" border="0" class="tableList">
		<thead>
			<tr>
                <th align="center" >招标内容</th>
				<th >合同估算价(万元)</th>
				<th >是否招标</th>
				<th >招标方式</th>
				<th >招标组织形式</th>
				<th >备注</th>
				<th >操作</th>
	  		</tr>
		</thead>
		<tbody id="resProjectInfoItem">
		<c:forEach items="${resProjectItemList}" var="resProjectItem">
	   			<tr>
	   				<input type="hidden" name="objId_" value="${resProjectItem.objId}" />
	                <input type="hidden" name="itemCode" value="${resProjectItem.itemCode }" />
	                <input type="hidden" name="resProject.objId" value="${param.resProjectId }" />
                <td id='itemName' nowrap="nowrap" align="center">
                <input type="hidden" name='itemName'   value="${resProjectItem.itemName }" />
	                <span>${resProjectItem.itemName }</span>
	            </td>
                <td id='contractPrice' align="center">
                	 <span><c:if test="${resProjectItem.contractPrice==null}"></c:if><c:if test="${resProjectItem.contractPrice!=null}">${resProjectItem.contractPrice }</c:if></span>
                </td>
                <td id='isEbuy' nowrap="nowrap" align="center">
                   <input type="hidden" name='isEbuy'   value="${resProjectItem.isEbuy }" />
                   <span><c:choose><c:when test="${resProjectItem.isEbuy==00}">不招标</c:when><c:when test="${resProjectItem.isEbuy==01}">招标</c:when><c:otherwise></c:otherwise></c:choose></span>
                </td>
                <td id='ebuyMethod' nowrap="nowrap" align="center">
                	<input type="hidden" name='ebuyMethod'   value="${resProjectItem.ebuyMethod }" />
	                <span><c:choose><c:when test="${resProjectItem.ebuyMethod==00}">公开招标</c:when><c:when test="${resProjectItem.ebuyMethod==01}">邀请招标</c:when><c:otherwise></c:otherwise></c:choose></span>	
                </td>
                <td id='ebuyStyle' nowrap="nowrap" align="center">
                	<input type="hidden" name='ebuyStyle'   value="${resProjectItem.ebuyStyle }" />
	               	<span><c:choose><c:when test="${resProjectItem.ebuyStyle==00}">自行招标</c:when><c:when test="${resProjectItem.ebuyStyle==01}">委托招标</c:when><c:otherwise></c:otherwise></c:choose></span>
                </td>
                <td id='remark' nowrap="nowrap">
	                <span><c:if test="${resProjectItem.remark==null}"></c:if><c:if test="${resProjectItem.remark!=null}">${resProjectItem.remark }</c:if> </span>
                </td>
                <td class="center" nowrap="nowrap">
                <a id='save' onClick='setGenviewDefine.liveButtonClick(this);' name='saveGenviewDefine'><span id='update'>修改</span></a>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<script>
var setGenviewDefine ={}; 



setGenviewDefine.liveButtonClick = function(thisObj){
	var id = thisObj.id;
	var jsonObj = {};
	jsonObj.objId = $(thisObj).parent().parent().find('input[name=objId_]').val();
	jsonObj.itemCode = $(thisObj).parent().parent().find('input[name=itemCode]').val();
	jsonObj.resProjectId = $(thisObj).parent().parent().find('input[name=resProject.objId]').val();

	 
	if("remove" == id){
		if(window.confirm('确定要删除吗？')){
		$(thisObj).parent().parent().remove();
	    //	$.getJSON($('#initPath').val()+'/CongruousFactorController.do?method=deleteGenviewDefine',{objId:jsonObj.objId},function(json){
		//		alert('删除成功！');
		//	});
		}
	}else if("save" == id){
		var spanFlag = $(thisObj).find('span').attr('id');
		var itemNameVal = $(thisObj).parent().parent().find("td[id='itemName']").find("span").text();
		var contractPriceVal = $(thisObj).parent().parent().find("td[id='contractPrice']").find("span").text();
		var isEbuyVal = $(thisObj).parent().parent().find("td[id='isEbuy']").find("input").val();
		var ebuyMethodVal = $(thisObj).parent().parent().find("td[id='ebuyMethod']").find("input").val();
		var ebuyStyleVal = $(thisObj).parent().parent().find("td[id='ebuyStyle']").find("input").val();
		var remarkVal = $(thisObj).parent().parent().find("td[id='remark']").find("span").text();
		if('update'==spanFlag){
			
			$(thisObj).parent().parent().find("td[id='contractPrice']").empty().append("<input type='text' name='contractPrice'  value='"+contractPriceVal+"' />");
		
			var isEbuyStr = "";
			if(isEbuyVal=='00'){
				isEbuyStr = "<input type='radio' name='isEbuy' value='00' checked='checked'>不招标&nbsp;&nbsp;<input type='radio' name='isEbuy' value='01'>招标";
			}else{
				isEbuyStr = "<input type='radio' name='isEbuy' value='00'>不招标&nbsp;&nbsp;<input type='radio' name='isEbuy' value='01' checked='checked'>招标";
			}
			$(thisObj).parent().parent().find("td[id='isEbuy']").empty().append(isEbuyStr);
			var ebuyMethodStr = "";
			if(ebuyMethodVal=='00'){
				ebuyMethodStr = "<input type='radio' name='ebuyMethod' value='00' checked='checked'>公开招标&nbsp;&nbsp;<input type='radio' name='ebuyMethod' value='01'>邀请招标";
			}else{
				ebuyMethodStr = "<input type='radio' name='ebuyMethod' value='00'>公开招标&nbsp;&nbsp;<input type='radio' name='ebuyMethod' value='01' checked='checked'>邀请招标";
			}
			$(thisObj).parent().parent().find("td[id='ebuyMethod']").empty().append(ebuyMethodStr);
			var ebuyStyleStr="";
			if(ebuyStyleVal=='00'){
				ebuyStyleStr = "<input type='radio' name='ebuyStyle' value='00' checked='checked'>自行招标&nbsp;&nbsp;<input type='radio' name='ebuyStyle' value='01'>委托招标";
			}else{
				ebuyStyleStr = "<input type='radio' name='ebuyStyle' value='00'>自行招标&nbsp;&nbsp;<input type='radio' name='ebuyStyle' value='01' checked='checked'>委托招标";
			}
			$(thisObj).parent().parent().find("td[id='ebuyStyle']").empty().append(ebuyStyleStr);

			$(thisObj).parent().parent().find("td[id='remark']").empty().append("<input type='text' name='remark'  value='"+remarkVal+"' />");
			

			$(thisObj).find('span').attr('id','save');	
			$(thisObj).find('span').text('保存');	
		}else if('save'==spanFlag){
			jsonObj.itemName = itemNameVal;
			jsonObj.contractPrice = $(thisObj).parent().parent().find("td[id='contractPrice'] input[name=contractPrice]").val();
			if(jsonObj.contractPrice==''){
				alert("金额不能为空！");
				return;
			}
			var re =  /^(0|[1-9]\d*|(0|[1-9]\d*)\.\d{0,6})$/;  
		 	if(!re.test(jsonObj.contractPrice)){
				alert("请输入正确金额！");
				return;
			}
		 			
			jsonObj.remark = $(thisObj).parent().parent().find("td[id='remark'] input[name=remark]").val();
			if(jsonObj.remark==''){
				alert("备注不能为空！");
				return;
			}
			jsonObj.isEbuy = $(thisObj).parent().parent().find('input[name=isEbuy]:checked').val();
			jsonObj.ebuyMethod = $(thisObj).parent().parent().find('input[name=ebuyMethod]:checked').val();
			jsonObj.ebuyStyle = $(thisObj).parent().parent().find('input[name=ebuyStyle]:checked').val();

	    	$.getJSON($('#initPath').val()+'/ResProjectController.do?method=saveResProjectItem',jsonObj,function(json){
				alert('保存成功！');
				//回填数据
				$(thisObj).parent().parent().find('input[name=objId_]').val(json.resProjectItem.objId);

				$(thisObj).parent().parent().find("td[id='contractPrice']").empty().append("<span>"+json.resProjectItem.contractPrice+"</span>");
				 var isEbuyStr1 = ''
				 if(jsonObj.isEbuy=='00'){
					 isEbuyStr1 = "不招标"
				 }else{
					 isEbuyStr1 = "招标"
					}	 
				$(thisObj).parent().parent().find("td[id='isEbuy']").empty().append("<input type='hidden' name='isEbuy'   value='"+jsonObj.isEbuy+"' /><span>"+isEbuyStr1+"</span>");
				var ebuyMethodStr1 = '';
				 if(jsonObj.ebuyMethod=='00'){
					 ebuyMethodStr1 = "公开招标"
				 }else{
					 ebuyMethodStr1 = "邀请招标"
					}
				$(thisObj).parent().parent().find("td[id='ebuyMethod']").empty().append("<input type='hidden' name='ebuyMethod'   value='"+jsonObj.ebuyMethod+"' /><span>"+ebuyMethodStr1+"</span>");


				var ebuyStyleStr1 = '';
				 if(jsonObj.ebuyStyle=='00'){
					 ebuyStyleStr1 = "自行招标"
				 }else{
					 ebuyStyleStr1 = "委托招标"
					}
				$(thisObj).parent().parent().find("td[id='ebuyStyle']").empty().append("<input type='hidden' name='ebuyStyle'   value='"+jsonObj.ebuyStyle+"' /><span>"+ebuyStyleStr1+"</span>");
				
				$(thisObj).parent().parent().find("td[id='remark']").empty().append("<span>"+json.resProjectItem.remark+"</span>");
				
				$(thisObj).find('span[id=save]').text('修改').attr("id",'update');
				$(thisObj).parent().attr("align","center");
				
			});
		}

	}
}

$(document).ready(function(){

	


	
})
</script>				