<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/bizplatform/base/purcatalog/catalog_audit_list.js"/>'></script>

<!-- 搜索 -->
<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="catalogSearchForm" >
			<ul>
			    <li>
					<label for="title">目录标题：</label>
					<input type="text" name="title" value="">
					<input type="hidden" name="title_op" value="like">
			    </li>
			    <li>
					<label for="areaCode">区域编码：</label>
					<input type="text" name="areaCode" value="">
					<input type="hidden" name="areaCode_op" value="like">
			    </li>
			    <li class="hightSearch">
					<label for="areaName">区域名称：</label>
					<input type="text" name="areaName">
					<input type="hidden" name="areaName_op" value="like">
				</li>
			    <li>
					<label for="year">年度：</label>
					<select name="year">
						<option value=''>所有</option>
						<c:forEach var = "y" items = "${anual}">
							<option value='${y }'>${y }</option>
						</c:forEach>
			       	</select>
			    </li>

				<li class="hightSearch">
					<label for="auditStatus">审核状态：</label>
					<select name="auditStatus">
						<option value=''>所有</option>
                        <option value='00'>新建待审核</option>
                        <option value='01'>新建审核中</option>
                        <option value='02'>通过</option>
                        <option value='03'>不通过</option>    
			       	</select>
				</li>
			    <li class="hightSearch">
					<label for="useStatus">有效状态：</label>
					<select name="useStatus">
						<option value="">所有</option>
						<option value="00">草稿</option>
						<option value="01">有效</option>
						<option value="02">报废</option>
					</select>
			    </li>
			    <li class="operationBtnDiv">
			        <button id = "catalogSearch" type="button"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
</div>
	
<div id="epsTabs">
		<ul>
			<li>
				<a href="#newcatalog" id = "tabs_toSubmit" class="refreshData"><span>待审核</span></a>
			</li>
			<li>
				<a href="#newcatalog" id = "tabs_toAudit" class="refreshData"><span>审核通过</span></a>
			</li>
		</ul>
		<div id="newcatalog">
			<table class="frontTableList" id="catalogList">
			      <thead>
			        <tr>
			          <th class="left">目录标题</th>
			          <th class="left">区域编码</th>
			          <th class="left">区域名称</th>
			          <th class="center">年度</th>
			          <th class="operation">操作</th>
			        </tr>
			      </thead>
			      <tbody>
			      </tbody>
			</table>
		</div>
</div>