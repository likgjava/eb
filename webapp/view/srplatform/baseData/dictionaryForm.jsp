<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/baseData/dictionaryForm.js"></script>
<div class="formLayout">       
	<form id="dictionaryForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<h4><span>数据字典</span></h4>
     	<ul >
	     	<li >
        		<label><spring:message code="dictionaryForm.dicName"/>:</label>
					<input type="text" name="dicName" id="dicName" class="required" 
									class="required"
									maxLength="50"
						      />
				<span class="eleRequired">*</span>
    	    </li>
	     	<li >
        		<label><spring:message code="dictionaryForm.dicCacheName"/>:</label>
					<input type="text" name="dicCacheName" id="dicCacheName" class="required" 
						      />
				<span class="eleRequired">*</span>	
    	    </li>
	     	<li >
        		<label><spring:message code="dictionaryForm.dicMemo"/>:</label>
					<input type="text" name="dicMemo" id="dicMemo" class="required" 
						      />
				<span class="eleRequired">*</span>
    	    </li>
	     	<li>
        		<label ><spring:message code="dictionaryForm.dicType"/>:</label>
					<input type="text" name="dicTypeGroupName" id="dicTypeGroupName" class="required" />
					<input type="hidden" name="dicType.objId" id="dicTypeId" class="required" />
				<span class="eleRequired">*</span> 
    	    </li>
	     	<li>
        		<label ><spring:message code="dictionaryForm.dicValue"/>:</label>
					<input type="text" name="dicValue" id="dicValue" class="required" 
						      />
				<span class="eleRequired">*</span>
    	    </li>
	     	<li >
        		<label ><spring:message code="dictionaryForm.dicUseCache"/>:</label>
        		<div>
        			<label><input class="checkboxInput"  type="radio" checked="checked" name="dicUseCache" id="showFlag_1" value="1"/>是&nbsp;</label>
           			<label><input class="checkboxInput"  type="radio" name="dicUseCache" id="showFlag_0" value="0"/>否</label>
           		</div>
    	    </li>
		</ul>
	    <div class="conOperation">　
	        <button class="btn primary" id="dictionarySave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	        <button class="btn primary" id="dictionaryReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	        <button class="btn" type="reset" id="dictionaryReset" tabindex="20" ><span><spring:message code="globe.reset"/></span></button>
	    </div>
	</form>
</div>