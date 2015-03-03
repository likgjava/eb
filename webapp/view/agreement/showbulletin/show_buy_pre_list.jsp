<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/showbulletin/show_buy_pre_list.js"></script>
<input type="hidden" id="districtLevel" value="${districtLevel}"/>
<input type="hidden" id="districtId" value="${districtId}"/>
<input type="hidden" id="goodsClassId" value="${goodsClassId}"/>
<input type="hidden" id="goodsClassCode" value="${goodsClassCode}"/>

 	<div id="conTitle">
      	<div class="navCurrent">采购预告列表</div>
    </div>
    <!--功能页内容-->
    <div id="conBody">
    	<!-- 搜索条件 开始 -->
		<div id="ListSubCategory">
			<div class="SubCategoryBox">
			<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png">搜索条件：</div>
			<form id="bulletinFilter">
   			<table >
				<tbody>					
					<tr>						
						<th>预告标题：</th>
						<td><input type="text" name="keyWord" value="${keyWord}" id="keyWord"></td>
						<td class="operationBtnDiv l"><button type="button" onclick="show_list.makeSearchData('restart')"><span>搜索</span></button></td>
					</tr>
				</tbody>
			</table>
			</form>
		</div>
		</div>
		<!-- 搜索条件 结束 -->
		
    	<!--排序方式 开始-->
	    <div class="conrank">
			<ul>
				<li class="displayBy"><em>排序方式：</em>
					<span class="supplierSort sysicon" id="createTimeSort" ><a href="javascript:void(0);">创建时间</a></span>
				</li>
				<li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;个采购项目</li>
			</ul>
	    </div>
		<!--排序方式 结束-->
		<!--产品信息 开始--> 
		<div id="showSuppListAndPic">
			<%@ include file="/view/agreement/showbulletin/bulletin_buy_pre_div.jsp" %>
		</div>
		<!--产品信息 结束-->
	</div>
