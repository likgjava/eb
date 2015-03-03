<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/modify_project_requireItem.js"></script>

<form id="ReverseProjectCreateForm" method="post">
<input type="hidden" name="objId" id="objId" value="${objId}"/>
<c:set var="goodsId" value=""/>
		<div class="formLayout form2Pa">
		<ul>
			<li class="fullLine">
				<label>项目名称：</label>
				<input type="text" name="projName" id="projName" class="required" value="${projectName}" maxlength="100" size="40"/>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
	            <label>报名起始日期：</label>
	         	<input type="text" name="signUpSTime" id="signUpSTime" class="required" value="${fn:replace(signUpSTime,'.0', '')}" size="38" readonly="readonly"/>
	         	<span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>报名结束日期：</label>
	         	<input type="text" name="signUpETime" id="signUpETime" class="required" value="${fn:replace(signUpETime,'.0', '')}" size="38" readonly="readonly"/>
	         	<span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>是否委托代理：</label>
	         	<input type="radio" name="isDelegate" value="1" <c:if test="${isDelegate==true}" >checked="checked"</c:if>/>是
	         	<input type="radio" name="isDelegate" value="0" <c:if test="${isDelegate==false}" >checked="checked"</c:if>/>否
	         	<span style="color:gray;">[选择是的话，则可委托阳光易购进行代理]</span>
	        </li>
		</ul>
		</div>
		
		<strong>需求信息</strong>
		<table class="frontTableList hidden" id="requireItemList_desc">
			<thead>
				<tr>
					<td colspan="7" align="left"><a id="addRequireItem" href="javascript:void(0);">添加需求条目</a></td>
				</tr>
				<tr>
					<th>商品描述</th>
					<th>所属品目</th>
					<th>预算(元)</th>
					<th>数量</th>
					<th>单位</th>
					<th>金额(元)</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody id="requireItemTB">
				<c:forEach var="requireItem" items="${requireItemList}" varStatus="status">
					<tr id="tempTR${status.index+1}">
						<td class="formTextarea" style="width:300px;">
							<input type="hidden" name="objId${status.index+1}" id="objId${status.index+1}" value="${requireItem.objId}"/>
							<textarea style="width:200px;height:60px" name="descr${status.index+1}" id="descr${status.index+1}" maxlength="2000" class="required">${requireItem.descr}</textarea>
						</td>
						<td>
							<input type="hidden" value="${requireItem.purCategory.objId}" id="purCategoryId${status.index+1}" name="purCategory.objId" />
							<input type="text" value="${requireItem.purCategory.categoryName}" id="purCategoryName${status.index+1}" name="purCategory.name" class="required sysicon siSearch" readonly="readonly"/>
						</td>
						<td><input type="text" name="goodsPrice${status.index+1}" id="goodsPrice${status.index+1}" onchange="javascript:changeTotal(this,'float');" value="${requireItem.goodsPrice}"  class="required money" style="width:60px"/></td>
						<td><input type="text" name="goodsQty${status.index+1}" id="goodsQty${status.index+1}" onchange="javascript:changeTotal(this,'int');" value="${requireItem.goodsQty}" class="required digits amount" style="width:40px"/></td>
						<td><input type="text" name="goodsUnit${status.index+1}" id="goodsUnit${status.index+1}" maxlength="10" value="${requireItem.goodsUnit}" class="required center" style="width:30px" /></td>
						<td><input type="text" name="goodsTotal${status.index+1}" id="goodsTotal${status.index+1}" style="width:60px" value="${requireItem.goodsTotal}" disabled="disabled" class="r"/></td>
						<td class="operation">
							<a id="delRequireItem" onclick="javascript:removeRequireItem(this,'desc','${requireItem.objId}');return false;" href="#">删除</a>
							<c:if test="${status.index == 0}">
								<c:set var="goodsId" value="${requireItem.goods.objId}"/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<input type="hidden" id="goodsId" value="${goodsId}">
		
		<table class="frontTableList hidden" id="requireItemList_goods">
			<thead>
				<tr>
					<td colspan="8" align="left"><a id="addRequireItem_g" href="javascript:void(0);">添加需求条目</a></td>
				</tr>
				<tr>
					<th width="380px">商品名称</th>
					<th>市场价</th>
					<th>协议价</th>
					<th>单价</th>
					<th>商品数量</th>
					<th>计量单位</th>
					<th>金额</th>
					<th class="operation">删除</th>
				</tr>
			</thead>
			<tbody id="goodsAndOption">
				<c:forEach var = "requireItem" items="${requireItemList}" varStatus="status">
				<tr id="${requireItem.objId }" class="goodsInfo">
					<td>
						<input type="hidden" name="purCategory_${status.index+1}" id="purCategory_${status.index+1}" value="${requireItem.goods.purCategory.objId }" />
						<input type="hidden" name="categoryName_${status.index+1}" id="categoryName_${status.index+1}" value="${requireItem.goods.purCategory.categoryName }" />
						<input type="hidden" name="objId_${status.index+1}" id="objId_${status.index+1}" value="${requireItem.objId}"/>
						<span id="productName_${status.index+1}">${requireItem.goods.productName }</span>
					</td>
					<td class="money"> <span id="marketPrice_${status.index+1}"><fmt:formatNumber value="${requireItem.marketPrice }" pattern="#,##0.00#" /></span>
						<input type="hidden" id="marketPrice_${status.index+1}" name="marketPrice${status.index+1}" value="${requireItem.marketPrice }" />
					</td>
					<td class="money"> <span id="agreePrice_${status.index+1}"><fmt:formatNumber value="${requireItem.agreePrice }" pattern="#,##0.00#" /></span>
						<input type="hidden" id="agreePrice_${status.index+1}" name="agreePrice$_{status.index+1}" value="${requireItem.agreePrice }" />
					</td>
					<td class="money"> <input type="text" name="goodsPrice_${status.index+1}" id="goodsPrice_${status.index+1}" value="${requireItem.goodsPrice }" onchange="javascript:changeTotal_(this,'float');" class="required money" style="width:60px"/></td>
					<td class="center">
						<input name="goodsQty_${status.index+1}" id="goodsQty_${status.index+1}" value="${requireItem.goodsQty }" onchange="javascript:changeTotal_(this,'int');" style="width:25px;" />
					</td>
					<td>
						<input type="text" name="goodsUnit_${status.index+1}" id="goodsUnit_${status.index+1}" value="${requireItem.goods.measureUnit }" style="width:25px"/>
					</td>
					<td class="money" id="total">
						<span class="money" id="goodsTotal_${status.index+1}">${requireItem.goodsTotal }</span>
						<input type="hidden" name="goodsTotal_${status.index+1}" id="goodsTotal_${status.index+1}" value="${requireItem.goodsTotal }">
					</td>
					<td class="operation"><a id="delRequireItem" onclick="javascript:removeRequireItem(this,'goods','${requireItem.objId}');return false;" href="#">删除</a>
						<input type="hidden" name="goods_${status.index+1}" id="goods_${status.index+1}" value="${requireItem.goods.objId }" />
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="r">
			<ul>
				<li class="fullLine">数量总计：<strong><span id="countGoods">0</span></strong>件，金额总计：<strong>￥<span id="totalMoney">0.00</span></strong>元</li>
				<input type="hidden" name="budgetTotalMoney" id="budgetTotalMoney" value=""/>
			</ul>
		</div>
		
		<div class="conOperation">
			<button id="save" type="button"><span>保存</span></button>
		</div>
</form>

<table id="tempTable">
<tr class="hidden" id="tempTR1">
	<td class="formTextarea" style="width:300px;">
		<textarea name="descr1" id="descr1" maxlength="2000" class="required" style="width:200px;height:60px"></textarea>
	</td>
	<td>
		<input type="hidden" id="purCategoryId1" name="purCategory.objId" />
		<input type="text" id="purCategoryName1" name="purCategory.name" class="required sysicon siSearch"/>
	</td>
	<td><input type="text" name="goodsPrice1" id="goodsPrice1" onchange="javascript:changeTotal(this,'float');" value="0.0"  class="required money" style="width:60px"/></td>
	<td><input type="text" name="goodsQty1" id="goodsQty1" onchange="javascript:changeTotal(this,'int');" value="0" class="required digits amount" style="width:40px"/></td>
	<td><input type="text" name="goodsUnit1" id="goodsUnit1" maxlength="10" class="required" style="width:30px"/></td>
	<td><input type="text" name="goodsTotal1" id="goodsTotal1" style="width:60px" value="0.0" disabled="disabled" class="r"/></td>
	<td class="operation">
		<a id="delRequireItem" href="#" onclick="javascript:removeRequireItem(this,'desc');return false;">删除</a>
	</td>
</tr>
</table>

<table id="tempTable_" class="frontTableList">
<tr class="hidden goodsInfo" id="tempTR_1">
	<td>
		<span id="productName_1"></span>
		<input type="hidden" name="purCategory_1" id="purCategory_1" value="" />
		<input type="hidden" name="categoryName_1" id="categoryName_1" value="" />
		<a href="#" onclick="javascript:selectGood(this);return false;">挑选商品</a>
	</td>
	<td><input type="text" name="marketPrice_1" id="marketPrice_1" class="required money" disabled="disabled" value="0.0" style="width:60px"/></td>
	<td><input type="text" name="agreePrice_1" id="agreePrice_1" class="required money" disabled="disabled" value="0.0" style="width:60px"/></td>
	<td><input type="text" name="goodsPrice_1" id="goodsPrice_1" onchange="javascript:changeTotal_(this,'float');" class="required money" value="0.0" style="width:60px"/></td>
	<td><input type="text" name="goodsQty_1" id="goodsQty_1" onchange="javascript:changeTotal_(this,'int');" value="0" class="required digits" style="width:25px"/></td>
	<td><input type="text" name="goodsUnit_1" id="goodsUnit_1" class="required" disabled="disabled" maxlength="10" style="width:25px"/></td>
	<td><input type="text" name="goodsTotal_1" id="goodsTotal_1" style="width:60px" value="0.0" disabled="disabled"/></td>
	<td class="operation">
		<a id="delRequireItem" href="#" onclick="javascript:removeRequireItem(this,'goods');return false;">删除</a>
		<input type="hidden" name="goods_1" id="goods_1" value=""/>
	</td>
</tr>
</table>
