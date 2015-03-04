<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/updateOpenbidGeneralview.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<form id="openbidGeneralviewForm" method="post" enctype="multipart/form-data">
<input type="hidden" id="_project_id" value="${projectId}"></input>
<div id="openbidGeneralList">
	<ul>
		<c:forEach items="${packList}" var="pack">
			<li><a href="#${pack.objId}" class="refreshData"><span>${pack.projName}</span></a></li>
		</c:forEach>
	</ul>
	
	<c:forEach items="${packList}" var="pack">
		<div id="${pack.objId}">
		    <ul>
			<li><a onClick="openbidGeneralview.insertRow('${pack.objId}');" class="linkButton"><span>新增</span></a></li>
			</ul>
			<table class="tableList">
				<tr>
			    	<th width='15%'>投标单位名称</th>
			    	<th width='5%'>投标报价</th>
			    	<c:forEach items="${genviewDefineList}" var="genviewDefine">
			    	    <c:if test="${genviewDefine.project.objId==pack.objId}"><th width='5%' id="${genviewDefine.objId}|${genviewDefine.factor.objId}|${genviewDefine.factorName}">${genviewDefine.factorName}</th></c:if>
			    	</c:forEach>
			        <th width='15%'>投标文件</th>
			        <th width='10%'>操作</th>
		   		</tr>
		   		   <tbody id="openbidGeneralview_${pack.objId}">
		   		   <c:set var="num" value="0"></c:set>
		   		   <c:forEach items="${openbidGeneralviewList}" var="openbidGeneralview">
		   		        <c:if test="${openbidGeneralview.subProj.objId ==pack.objId}">
		   		        <tr id="${num}_tr">
		   		        <td width='15%'>
		   		        <select name="${num}_supplierId" id="${num}_supplierId" class="required" onChange="openbidGeneralview.chooseSupplierName('${num}');">
				        <c:forEach items="${signList}" var="singUp" >	
				        <option value="${singUp.supplier.objId}" <c:if test="${singUp.supplier.objId==openbidGeneralview.supplierId}">selected="selected"</c:if>>${singUp.supplier.orgName}</option>
				        </c:forEach>
				    	</select>
		   		        	<input type='hidden' name='${num}_supplierName' id='${num}_supplierName' class='required' value='${openbidGeneralview.supplierName}'/>
		   		        	<input type='hidden' name='${num}_subProjectId' id='${num}_subProjectId' value='${pack.objId}'/>
		   		        </td>
		   		        <td width='5%'><input style='width:80%;' class='required money'  type='text' name='${num}_quotesum' id='${num}_quotesum' value='${openbidGeneralview.bidQuotesum}'/></td>
		   		       		 <c:set var="n" value="0"></c:set>
		   		        	 <c:forEach items="${genviewDefineList}" var="genviewDefine">
			    	    			<c:if test="${genviewDefine.project.objId==pack.objId}">
			    	    			    <c:forEach items="${openbidGeneralview.openBidGeneralVitemSet}"  var="openBidGeneralVitem">
		   		                			<c:if test="${genviewDefine.factor.objId == openBidGeneralVitem.factor.objId}">
		   		                					<td width='5%'><input style='width:80%;' type='text' id='${num}_openBidGeneralVitem_${n}' name='${num}_openBidGeneralVitem_${n}' value='${openBidGeneralVitem.respValue}'  class='required'>
		   		                					<input type='hidden' id='${num}_openBidGeneralVitemValue_${n}' name='${num}_openBidGeneralVitemValue_${n}' value='${genviewDefine.objId}|${genviewDefine.factor.objId}|${genviewDefine.factorName}'></td>
		   		                					<c:set var="n" value="${n+1}"></c:set>
		   		                			</c:if>
		   		        				</c:forEach>  
			    	    			</c:if>
			    			 </c:forEach>
		   		          <td width='15%'><input type='file' name='${num}_attrFile' id='${num}_attrFile' class='required' value=''/>
							  <input type='hidden' name='${num}_isUploadFile' value='false'>
							  <input type='hidden' name='${num}_attrId' value='${openbidGeneralview.attachment.objId}'/> 
							  <c:if test='${null != openbidGeneralview.attachment.objId && "" != openbidGeneralview.attachment.objId}'>
									<a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${openbidGeneralview.attachment.objId}">${openbidGeneralview.attachment.viewName}</a>
									&nbsp;&nbsp;
									<a href="#" onClick="openbidGeneralview.removeFile('${num}');" id="removeFileEle${num}">删除文件</a>
							  </c:if>
		   		          </td>
		   		          <td width='10%'><a href="#" onClick="openbidGeneralview.removeOpenbidGeneralview('${num}');" id="removeOpenbidGeneralview${num}">删除</a></td>
		   		        </tr></c:if>
		   		   	    <c:set var="num" value="${num+1}"></c:set>	
		   		   </c:forEach>
		   		  <input type="hidden" id="num" value="${num}"></input>
		   		  <input type="hidden" id="n" value="${n}"></input>
		   		  </tbody>  
		   		   
			</table>
		</div>
	</c:forEach>	
</div>
</form>
<div class="conOperation">
		<button tabindex="18" type="button" id="openBidGeneralVitemSave"><span>保存</span></button>
</div>