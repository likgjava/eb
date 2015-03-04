<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/auditVariationBulletinFrom.js"></script>
 <form id="bulletinFormView" method="post">
<input type="hidden" name="projectId" id="projectId" value="${bulletin.project.objId}">
<input type="hidden" name="objId" id="objId" value="${bulletin.objId}">
<div class="formLayout formPa">
	<h5><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>内容</h5>
	<span style="height:auto;">${bulletin.bullContents}</span>
</div>
 <div class="formLayout formPa">
 <!-- 
 <div class="formLayout" id="signUpCondFactorView"></div>
  -->
 <div class="conOperation">
			<ul>
				<li class="formTextarea">
					<label>意见：</label>
					<textarea name="opinion" id="opinion"></textarea>
				</li>
			</ul>
			<div class="conOperation">
	       	  		 <button class="btn primary" id="bulletinPass" type="button" tabindex="19"><span>通过</span></button>
	        		 <button class="btn primary" id="bulletinNoPass" type="button" tabindex="19"><span>不通过</span></button>
	        		 <button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
	        		 <button id="returnId"  type="button" tabindex="20"><span>返回</span></button>
	   	    </div>
	    </div>
</div>
</form>

   <div id="historyView"></div>