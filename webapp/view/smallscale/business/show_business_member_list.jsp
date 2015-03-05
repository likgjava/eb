<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/shangquan.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/business/show_business_member_list.js"></script>

<input type="hidden" id="currentCommunityId" name="currentCommunityId" value="${communityId}"/>

<div id="contentSub" class="contentSub shangquanLeft">
<ul>
	<li class="selected">
		<a class="icon1" href="javascript:void(0)"><span>商圈社区列表</span></a>
		<ul class="subnav">
			<c:forEach var="community" items="${communityList}">
				<li>
					<img src="AttachmentController.do?method=showImg&objId=${community.picture}" class="wh40"/>
			 		<a id="${community.objId}" href="javascript:void(0);" onclick="show_list.loadBusinessMemberList('${community.objId}');">${community.communityName}</a>
			 	</li>
			 </c:forEach>
		</ul>
	  </li>
</ul>
</div>
<div id="contentMain" class="index3pa sqindex3pa">
 	<div id="conTitle">
      	<div class="navCurrent">商圈会员展示</div>
    </div>
    <div id="conBody">
		<!--详细搜索条件 开始 -->
		<div class="SubCategoryBox">
			<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png">筛选条件：</div>
			<form id="businessMemberFilter">
   			<table style="width:100%">
				<tbody>
					<tr>
						<td><label>机构名称：</label></td>
						<td><input name="orgName" id="orgName"></td>
						<td>关键字：</td>
						<td><input name="keyWord" value="${keyWord}" id="keyWord"></td>
						<td class="operationBtnDiv l"><button type="button" onclick="show_list.makeSearchData()"><span>确定</span></button></td>
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
	        		<span class="businessMemberSort sysicon" id="createTimeSort" ><a href="javascript:void(0);">入库时间</a></span></li>
	        <li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;个商圈会员</li>
	      </ul>
	    </div>
		<!--排序方式 结束-->
		
		<!--商圈会员信息 开始--> 
		<div id="showSuppListAndPic">
			<%@ include file="/view/smallscale/business/business_member_list_div_1.jsp" %>
		</div>
		<!--商圈会员信息 结束-->
	</div>
</div>
<div id="contentSupp" class="index3pa hidden"></div>