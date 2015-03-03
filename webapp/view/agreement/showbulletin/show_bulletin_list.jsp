<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<style>
.selectCategory { background: none repeat scroll 0 0 #F2F2F2; border: 1px solid #CCCCCC; height: 25px; margin-bottom: 4px; overflow: hidden; position: relative; }
.selectCategory .choosed { float: left; line-height: 21px; margin-top: 2px; width: 610px; }
.selectCategory .choosed h3 { display: inline; float: left; font-size: 12px; }
.selectCategory .choosed a { background: none repeat scroll 0 0 #FFFFFF; border: 1px solid #E6E6E6; color: #404040; display: inline; float: left; line-height: 19px; margin-left: 5px; padding: 0 20px 0 5px; position: relative; white-space: nowrap; }
.selectCategory .choosed b { background: url("view/resource/skin/skin07/img/delete.gif") no-repeat scroll 0 0 transparent; }
.selectCategory .choosed b { background-position: 0 0px; height: 18px; position: absolute; right: 2px; top: 1px; width: 21px; }
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/showbulletin/show_bulletin_list.js"></script>

<input type="hidden" id="districtLevel" value="${districtLevel}"/>
<input type="hidden" id="districtId" value="${districtId}"/>
<input type="hidden" id="purCategoryId" value="${purCategoryId}"/>
<input type="hidden" id="purCategoryCode" value="${purCategoryCode}"/>

<div id="contentSub" class="hidden"></div>
<div id="contentMain" class="index2paL">
 	<div id="conTitle">
      	<div class="navCurrent">采购项目展示</div>
    </div>
    <!--功能页内容-->
    <div id="conBody">
    	<!--已选择采购品目 开始 -->
    	<c:if test="${purCategoryName != null}">
    	<div class="selectCategory">
    		<div class="choosed">
				<h3>您已选择采购品目: </h3>
				<a title="${purCategoryName}" href="javascript:void(0);" onclick="show_list.reloadList();">${purCategoryName}<b></b></a>
			</div>
		</div>
		</c:if>
		<!--已选择采购品目 结束-->
	    
    	<!-- 搜索条件 开始 -->
		<div id="ListSubCategory">
			<div class="SubCategoryBox">
				<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png">按预算采购金额选择：</div>
				<ul class="CategoryListTableLevel1 expand" id="moreProp2">
					<div id="propDisp1">
						<li>
							<a href="javascript:void(0);" name="" >金额不限</a>
						</li>
						<li>
							<a href="javascript:void(0);" name="0,1000" <c:if test="${intervalBudgetMoney == '0,1000' }">class="strong"</c:if>>&lt;1000元</a><i>(${bulletinNumList[0][0] })</i>
						</li>
						<li>
							<a href="javascript:void(0);" name="1000,5000">1000-5000元</a><i>(${bulletinNumList[1][0] })</i>
						</li>
						<li>
							<a href="javascript:void(0);" name="5000,10000">5000-1万元</a><i>(${bulletinNumList[2][0] })</i>
						</li>
						<li>
							<a href="javascript:void(0);" name="10000,50000">1万-5万元</a><i>(${bulletinNumList[3][0] })</i>
						</li>
						<li>
							<a href="javascript:void(0);" name="50000,100000">5万-10万元</a><i>(${bulletinNumList[4][0] })</i>
						</li>
						<li>
							<a href="javascript:void(0);" name="100000,500000">10万-50万元</a><i>(${bulletinNumList[5][0] })</i>
						</li>
						<li>
							<a href="javascript:void(0);" name="500000,1000000">50万-100万元</a><i>(${bulletinNumList[6][0] })</i>
						</li>
						<li>
							<a href="javascript:void(0);" name="1000000,3000000">100万-300万元</a><i>(${bulletinNumList[7][0] })</i>
						</li>
						<li>
							<a href="javascript:void(0);" name="3000000,5000000">300万-500万元</a><i>(${bulletinNumList[8][0] })</i>
						</li>
						<li>
							<a href="javascript:void(0);" name="5000000,4857867294656692220">&gt;500万元</a><i>(${bulletinNumList[9][0] })</i>
						</li>
					</div>
				</ul>
			</div>
			<div class="SubCategoryBox">
				<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png">按所在地选择：</div>
				<ul class="CategoryListTableLevel1 expand" id="moreProp2">
					<div id="propDisp2">
						<c:if test="${fn:length(districtList) == 0}">
							<li>没有检索到采购项目所在地数据！</li>
						</c:if>
						<c:if test="${fn:length(districtList) > 0}">
							<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict('-1');return false;">全部地区</a></li>
							<c:forEach var="district" items="${districtList}">
								<li><a  name="${district[0]}" href="javascript:void(0);" onclick="show_list.changeDistrict('${district[0]}');return false;"
										<c:if test="${districtId == district[0] }">class="strong" </c:if>
									>${district[1]}</a><i>(${district[2]})</i></li>
							</c:forEach>
						</c:if>
					</div>
				</ul>
				<c:if test="${fn:length(districtList) > 16}">
					<div class="expandOpen">
						<a href="javascript:void(0);" onclick="common.showOrHiddenMore('expandProp2','moreProp2','地区');return false;">
							<span class="sysicon siDownGray" id="expandProp2">显示全部地区</span>
						</a>
					</div>
				</c:if>
			</div>
			<div class="SubCategoryBox">
				<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png">按采购品目选择：</div>
				<ul class="CategoryListTableLevel1 expand" id="moreProp1">
					<div id="propDisp3">
						<c:if test="${fn:length(purCategoryList) == 0}">
							<li>没有检索到相关采购品目数据！</li>
						</c:if>
						<c:if test="${fn:length(purCategoryList) > 0}">
							<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeGoodsClass('-1');return false;">全部分类</a></li>
							<c:forEach var="purCategory" items="${purCategoryList}" > 
								<li>
									<a name="${purCategory[0]}" href="javascript:void(0);" onclick="show_list.changeGoodsClass('${purCategory[0]}');return false;"
										<c:if test="${purCategoryId == purCategory[0] }"> class="strong" </c:if>>
										${purCategory[1]}
									</a>
									<i>(${purCategory[2]})</i>
								</li>
							</c:forEach>
						</c:if>
					</div>
				</ul>
				<c:if test="${fn:length(purCategoryList) > 15}">
					<div class="expandOpen">
						<a href="javascript:void(0);" onclick="common.showOrHiddenMore('expandProp1','moreProp1','分类');return false;">
							<span class="sysicon siDownGray" id="expandProp1">显示全部分类</span>
						</a>
					</div>
				</c:if>
			</div>
		</div>
		<!-- 搜索条件 结束 -->
		
		<!--详细搜索条件 开始 -->
		<div class="SubCategoryBox">
			<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png">更多筛选条件：</div>
			<form id="bulletinFilter">
   			<table style="width:100%">
				<tbody>
					<tr>
						<td>项目进度：</td>
						<td>
							<input type="checkbox" name="evaltime_0" <c:if test="${evaltime_0}">checked="checked"</c:if>><label> 未开始</label>
							<input type="checkbox" name="evaltime_1" <c:if test="${evaltime_1}">checked="checked"</c:if>><label> 正在进行</label>
							<input type="checkbox" name="evaltime_2"><label> 已结束</label>
						</td>
						<td>公告类型:
							<html:select id="bulletinType" name="bulletinType" code="bulletinType" selectedValue="${bulletinType}">
								<html:option value="">全部</html:option>
							</html:select>
						</td>
						<td>采购方式:
							<html:select id="ebuyMethod" name="ebuyMethod" code="ebuyMethod">
								<html:option value="">全部</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td>扩展属性：</td>
						<td><input type="checkbox" name="isRecommendProject" <c:if test="${isRecommendProject}">checked="checked"</c:if>><label> 推荐采购项目</label></td>
						<td>关键字：<input type="text" name="keyWord" value="${keyWord}" id="keyWord"></td>
						<td class="operationBtnDiv l"><button type="button" onclick="show_list.makeSearchData('restart')"><span>确定</span></button></td>
					</tr>
				</tbody>
			</table>
			</form>
		</div>
		<!--详细搜索条件 结束 -->
		
    	<!--排序方式 开始-->
	    <div class="conrank">
			<ul>
				<li class="displayBy"><em>排序方式：</em>
					<span class="supplierSort sysicon" id="budgetMoneySort" ><a href="javascript:void(0);">采购金额</a></span>
					<span class="supplierSort sysicon" id="createTimeSort" ><a href="javascript:void(0);">创建时间</a></span>
				</li>
				<li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;个采购项目</li>
			</ul>
	    </div>
		<!--排序方式 结束-->
		<!--产品信息 开始--> 
		<div id="showSuppListAndPic">
			<%@ include file="/view/agreement/showbulletin/bulletin_list_div_l.jsp" %>
		</div>
		<!--产品信息 结束-->
	</div>
</div>
<div id="contentSupp" class="index2paR">
	<span id="otherDiv">
		<%@ include file="/view/pubservice/common/customer_service_div.jsp" %>
	</span>
	<span id="reconmendDiv"></span>
</div>