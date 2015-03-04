<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/project_time_content.js"></script>
<ul>
		<li>
			<label>报名开始时间</label>
			<input type="text"  id="signUpSTime" name="signUpSTime" />
			<em>*</em>
			<input type="hidden" id="objId" name="objId" >
		</li>
		<li>
			<label>报名结束时间</label>
			<input type="text"  id="signUpETime"  name="signUpETime"/>
			<em>*</em>
		</li>
		<li>
			<label>购买标书开始时间</label>
			<input type="text"  id="sellBidDocSTime"  name="sellBidDocSTime"/>
			<em>*</em>  
		</li>
		<li>
			<label>购买标书结束时间</label>
			<input type="text"  id="sellBidDocETime"  name="sellBidDocETime"/>
			<em>*</em>
		</li>
		<li>
			<label><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>开始时间</label>
			<input type="text"  id="tenderStartTime"  name="tenderStartTime"/>
			<em>*</em>  
		</li>
		<li>
			<label><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>结束时间</label>
			<input type="text"  id="tenderEndTime"  name="tenderEndTime"/>
			<em>*</em>
		</li>
		<li>
			<label><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>时间</label>
			<input type="text"  id="openBidTime"  name="openBidTime"/>
			<em>*</em>
		</li>
		<div class="operationBtnDiv">
			<button type="button" id="submitBtn"><span>保存</span></button>    
		</div>
	</ul>