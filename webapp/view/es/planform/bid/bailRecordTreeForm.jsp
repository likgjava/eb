<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/planform/bid/bailRecordTreeForm.js"></script>
<div class="treePage frameMainSub frameMs12 fullScreen ">
	<div class="treeOutside frameMain">
	    <div class="treeBtn">
		      	<ul>
		        	<li><a id="up" class="upMove" onclick="return false;" target="#" href="javascript:void(0)"><span></span></a></li>
		            <li><a id="down" class="downMove" onclick="return false;" target="#" href="javascript:void(0)"><span></span></a></li>
		        </ul> 		
		</div>
	  	<div id="bailRecordTree" class="treeContentDiv"></div>
		<div class="treeResize" ></div>
	</div>
<!--Form表单div开始-->
<div class="treeRight">
<form id="bailRecordForm"  method="post">
<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
<input type="hidden" name="parent.objId" id="parent.objId" value="<c:out value="${param.parent.objId}"/>"/>
	<div id="bailRecordDetail" class="formLayout form2Pa detail">
		<div class="treeEditNav">
			<ul>
				<li id="addBailRecord" class="add"><a href="javascript:void(0)"><span>新增</span></a></li>
				<li id="updateBailRecord" class="edit"><a href="javascript:void(0)"><span>修改</span></a></li>
				<li id="deleteBailRecord" class="del"><a href="javascript:void(0)"><span>删除</span></a></li>
			</ul>
		</div>
					<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
					<input type="hidden" name="parent.objId" id="parent.objId" value="<c:out value="${param.parent.objId}"/>"/>
			     	<h5><span><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>记录表</span></h5>
			     	<ul>
						<li>
			 				<label for="bailRecordForm.ballMoney"><spring:message code="bailRecordForm.ballMoney"/></label>
							<span id="ballMoney"></span>
						</li>
						<li>
			 				<label for="bailRecordForm.renderTime"><spring:message code="bailRecordForm.renderTime"/></label>
							<span id="renderTime"></span>
						</li>
						<li>
			 				<label for="bailRecordForm.renderAtt"><spring:message code="bailRecordForm.renderAtt"/></label>
							<span id="renderAtt"></span>
						</li>
						<li>
			 				<label for="bailRecordForm.returnedTime"><spring:message code="bailRecordForm.returnedTime"/></label>
							<span id="returnedTime"></span>
						</li>
						<li>
			 				<label for="bailRecordForm.returnedAtt"><spring:message code="bailRecordForm.returnedAtt"/></label>
							<span id="returnedAtt"></span>
						</li>
						<li>
			 				<label for="bailRecordForm.bailStatus"><spring:message code="bailRecordForm.bailStatus"/></label>
							<span id="bailStatus"></span>
						</li>
					</ul>
	</div>
	<div id="bailRecordFormDiv" class="eleHide">
		<div id="bailRecordUpdateDiv" class="formLayout form2Pa">
	     	<h5><span><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>记录表</span></h5>
	        <ul>
		     	<li>
		     		<label for="bailRecordForm.ballMoney"><spring:message code="bailRecordForm.ballMoney"/></label>
					<input type="text" name="ballMoney" id="ballMoney" class="required" 
						      />
					<span class="eleRequired">*</span>
	    	    </li>
		     	<li>
		     		<label for="bailRecordForm.renderTime"><spring:message code="bailRecordForm.renderTime"/></label>
					<input type="text" name="renderTime" id="renderTime" class="required" 
						      />
					<span class="eleRequired">*</span>
	    	    </li>
		     	<li>
		     		<label for="bailRecordForm.renderAtt"><spring:message code="bailRecordForm.renderAtt"/></label>
					<input type="text" name="renderAtt" id="renderAtt" class="required" 
						      />
					<span class="eleRequired">*</span>
	    	    </li>
		     	<li>
		     		<label for="bailRecordForm.returnedTime"><spring:message code="bailRecordForm.returnedTime"/></label>
					<input type="text" name="returnedTime" id="returnedTime" class="required" 
						      />
					<span class="eleRequired">*</span>
	    	    </li>
		     	<li>
		     		<label for="bailRecordForm.returnedAtt"><spring:message code="bailRecordForm.returnedAtt"/></label>
					<input type="text" name="returnedAtt" id="returnedAtt" class="required" 
						      />
					<span class="eleRequired">*</span>
	    	    </li>
		     	<li>
		     		<label for="bailRecordForm.bailStatus"><spring:message code="bailRecordForm.bailStatus"/></label>
					<input type="text" name="bailStatus" id="bailStatus" class="required" 
						      />
					<span class="eleRequired">*</span>
	    	    </li>
			</ul>
			   <div class="conOperation">
					<button type="button" id="bailRecordSave"><span><spring:message code="globe.save"/></span></button>
					<button type="button" id="bailRecordReturn" type="button" tabindex="19""><span><spring:message code="globe.return"/></span></button>
					<button type="button" id="bailRecordReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
			   </div>
		</div>
	</div>
</form>
	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
    </div>
</div>