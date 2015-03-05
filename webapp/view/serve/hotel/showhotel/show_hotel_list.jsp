<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/serve/css/hotel_show.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/serve/hotel/showhotel/show_hotel_list.js"></script>
<input type="hidden" id="districtLevel" value="${districtLevel}"/>
<input type="hidden" id="districtId" value="${districtId}"/>

<div id="contentSub" class="hidden"></div>
<div id="contentMain" class="index2paL">
 	<div id="conTitle">
      	<div class="navCurrent">
      		酒店服务
      	</div>
    </div>
    <div id="conBody"><!--功能页内容-->
    	<!-- 搜索条件 开始 -->
		<div id="ListSubCategory">
			<div class="SubCategoryBox">
				<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png">按酒店星级选择：</div>
				<ul class="CategoryListTableLevel1" id="moreProp1">
					<div id="propDisp1">
						<c:if test="${fn:length(starLevelList) == 0}">
							<li>没有检索到相关酒店星级数据！</li>
						</c:if>
						<c:if test="${fn:length(starLevelList) > 0}">
							<li><a href="javascript:void(0);" name="-1" >全部星级 </a></li>
							<c:forEach var="stars" items="${starLevelList}" > 
								<li><a href="javascript:void(0);" name="${stars[0]}"
									<c:if test="${starLevel == stars[0] }"> class="strong" </c:if>
								>
								<span class="hotel_stars${stars[0]}">&nbsp;</span><br />${stars[1]}</a><i>(${stars[2]})</i></li>
							</c:forEach>
						</c:if>
					</div>
				</ul>
			</div>
			<div class="SubCategoryBox">
				<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png">按所在地区选择：</div>
				<ul class="CategoryListTableLevel1 expand" id="moreProp2">
					<div id="propDisp2">
						<c:if test="${fn:length(districtList) == 0}">
							<li>没有检索到酒店地区数据！</li>
						</c:if>
						<c:if test="${fn:length(districtList) > 0}">
							<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict('-1');return false;">全部地区</a></li>
							<c:forEach var="district" items="${districtList}">
								<li><a name="${district[0]}" href="javascript:void(0);" onclick="show_list.changeDistrict('${district[0]}');return false;"
									<c:if test="${districtId == district[0] }"> class="strong" </c:if>
									>${district[1]}</a><i>(${district[2]})</i></li>
							</c:forEach>
						</c:if>
					</div>
				</ul>
				<c:if test="${fn:length(districtList) > 16}">
					<div class="expandOpen">
						<a href="javascript:void(0);" onclick="common.showOrHiddenMore('expandProp2','moreProp2','区域');return false;">
							<span class="sysicon siDownGray" id="expandProp2">显示全部区域</span>
						</a>
					</div>
				</c:if>
			</div>
			<!--详细搜索条件 开始 -->
			<div class="SubCategoryBox">
				<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png">更多筛选条件：</div>
				<form id="goodsFilter">
    			<table style="width:100%">
					<tbody>
						<tr>
							<th>客房类型：</th>
							<td colspan="5"><html:checkbox name="guestRoomType_box" code="serve.hotel.type"/></td>
						</tr>
						<tr>
							<th>会议室类型：</th>
							<td><html:select selectedValue="0" id="meetingRoomType_sel" name="meetingRoomType_sel" code="serve.hotel.meetingRoomType">
									<html:option value="">所有</html:option>
								</html:select>
							</td>
							<td>会议室人数：</td>
							<td colspan="3">
								<html:select selectedValue="0" id="meetingRoomNum_sel" name="meetingRoomNum_sel" code="serve.hotel.meetingNumRang">
									<html:option value="">所有</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>娱乐设施：</th>
							<td colspan="5"><html:checkbox name="funFacilities_box" code="serve.hotel.funFacilities"/></td>
						</tr>
						<tr>
							<th>服务项目：</th>
							<td colspan="5"><html:checkbox name="serviceItems_box" code="serve.hotel.serviceItems"/></td>
						</tr>
						<tr>
							<th>可接受信用卡类型：</th>
							<td colspan="5">
								<input value="00" name="creditCardType_box" title="银联(UnionPay)" type="checkbox">&nbsp;<span class="pay_00">&nbsp;</span>&nbsp;
								<input value="01" name="creditCardType_box" title="万事达(Master)" type="checkbox">&nbsp;<span class="pay_01">&nbsp;</span>&nbsp;
								<input value="02" name="creditCardType_box" title="威士(VISA)" type="checkbox">&nbsp;<span class="pay_02">&nbsp;</span>&nbsp;
								<input value="03" name="creditCardType_box" title="运通(AMEX)" type="checkbox">&nbsp;<span class="pay_03">&nbsp;</span>&nbsp;
								<input value="04" name="creditCardType_box" title="大来(Diners Club)" type="checkbox">&nbsp;<span class="pay_04">&nbsp;</span>&nbsp;
								<input value="05" name="creditCardType_box" title="JCB" type="checkbox">&nbsp;<span class="pay_05">&nbsp;</span>&nbsp;
							</td>
						</tr>
						<tr>
							<th>关键字：</th>
							<td><input id="keyWord" value="${keyWord}"></td>
							<th>开业时间：</th>
							<td><input id="startDate" name="startDate" style="width:80px"> 至 <input id="endDate" name="endDate" style="width:80px"></td>
							<td class="operationBtnDiv l"><button type="button" onclick="show_list.makeSearchData('firstPage')"><span>确定</span></button></td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
			<!--详细搜索条件 结束 -->
		</div>
		<!-- 搜索条件 结束 -->
		
    	<!--排序方式 开始-->
	    <div class="conrank">
	      <ul>
	        <li class="displayBy"><em>排序方式：</em>
	        	<span class="goodsSort sysicon" id="evalSort" ><a href="javascript:void(0);">评价</a></span>
	        	<span class="goodsSort sysicon" id="starSort" ><a href="javascript:void(0);">星级</a></span></li>
	        <li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;个酒店</li>
	      </ul>
	    </div>
		<!--排序方式 结束-->
		
		<!--酒店信息 开始--> 
		<div id="showGoodsListAndPic">
			<%@ include file="/view/serve/hotel/showhotel/hotel_list_div_l.jsp" %>
		</div>
		<!--酒店信息 结束-->
	</div>
</div>

<div id="contentSupp" class="index2paR">
	<span id="otherDiv">
		<%@ include file="/view/pubservice/common/customer_service_div.jsp" %>
	</span>
	<span id="reconmendDiv"></span>
</div>

