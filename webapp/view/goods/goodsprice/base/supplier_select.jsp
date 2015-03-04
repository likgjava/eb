<!--
 * 版权信息： 政采软件版权所有，未经珠海市政采软件技术有限公司的书面同意，不得拷贝，传播本文件及其相关信息的任何内容。
 * 项目：     政府采购超源业务平台
 * JDK版本：  1.5
 * 版本：     3.0
 * 说明：     管理代理商选择代理商
 * 修订历史：
 * 序号：     1
 * 日期：     2010-03-30
 * 修改人：   lianggt    
 * 修改说明：（为什么修改，修改了什么）
 -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goodsprice/base/supplier_select.js"></script>
<div  class="treePage frameMainSub frameMS12 ">
	<input id="srcBtn" type="hidden" value="<c:out value="${param.src}"/>"/>
	<div class="treeRight frameSub" id="treeRight">
	
		<div class="conSearch">
			<form id="supplierSearchForm" >
				<ul>
					<li>
						<label for="supplier.orgCode">机构代码</label>
                        <input type="text" name="orgCode" value="">
                        <input type="hidden" name="orgCode_op" value="like">
	                </li>
	               	<li>
                   	 	<label for="orgName">代理商名称</label>
                        <input type="text" name="orgName" value="">
                        <input type="hidden" name="orgName_op" value="like">
	                </li>
	                <li>
						<button type="submit">
							<span><spring:message code="globe.search" /></span>
						</button>
					</li>
	          	</ul>
			</form>
		</div>
		<div class="formLayout">
		    <form id="districtSelFrm" action="" method="post">
		    <flex:flexgrid id="supplierGrid" url="OrgInfoController.do?method=getOrgInfoList" checkbox="true" 
					queryColumns="" searchZone="supplierSearchForm" rp="10" height="200" title="代理商列表"
					onSubmit="supplierList.before" onSuccess="supplierList.success">
			     <flex:flexCol name="orgCode" display="机构代码" sortable="true" width="200" align="left"></flex:flexCol>
			     <flex:flexCol name="orgName" display="代理商名称" sortable="true" width="200" ></flex:flexCol>
			 </flex:flexgrid>
		
		     <div class="pageSubmitBtnBox operationBtnDiv" id="districtSelEditBtnDiv">
		       <button type="button" class="operationBtnDiv" id="saveItemBtn"><span>确定</span></button>
		       <button type="button" class="operationBtnDiv" id="cancelItemBtn"><span>关闭</span></button>
		    </div>
		   </form>
	   </div>
	</div>  
</div>
