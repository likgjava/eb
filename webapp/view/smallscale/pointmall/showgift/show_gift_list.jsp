<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/PointsMall.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/pointmall/showgift/show_gift_list.js"></script>

<input type="hidden" id="style" value="list">
<input type="hidden" id="seriesId" value="${param.seriesId}">

<div id="contentSub" class="contentSub PointsMallLeft"></div>

<div id="contentMain" class="contentMain index2paRR">
<div id="conTitle">
	<div class="navCurrent" style="margin-left:10px;">
		礼品列表
	</div>
</div>
	<!-- 搜索条件 开始 -->
	<div id="ListSubCategory">
			<div class="SubCategoryBox">
				<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png">筛选条件：</div>
				<form id="goodsFilter">
    			<table style="width:100%">
					<tbody>
						<tr>
							<th>基本属性：</th>
							<td>
								<input type="radio" name="giftType" value="" <c:if test="${param.giftType==null }">checked="checked"</c:if> >全部
								<input type="radio" name="giftType" value="01" <c:if test="${param.giftType=='01' }">checked="checked"</c:if> >虚拟礼品
								<input type="radio" name="giftType" value="00" <c:if test="${param.giftType=='00' }">checked="checked"</c:if> >实物礼品
							</td>
							<th>关键字：</th>
							<td><input id="keyWord" value="${keyWord}"></td>
							<td class="operationBtnDiv l"><button type="button" onclick="show_list.makeSearchData($('#seriesId').val());"><span>确定</span></button></td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
			<!--详细搜索条件 结束 -->
	</div>
	<!--排序方式 开始-->
	<div class="conrank">
		<ul>
			<li class="displayBy"><a id="showGoodsList" href="javascript:void(0);"><img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/view_mode_10a.gif" title="列表显示" />&nbsp;列表</a>
				<a id="showGoodsPic" href="javascript:void(0);"><img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/view_mode_10a1.gif" title="大图显示" />&nbsp;大图</a> </li>
			<li class="shortBy"><em>排序：</em>
				<span class="goodsSort sysicon" name="createTime" ><a  href="javascript:void(0);">创建时间</a></span>
			</li>
			<li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;件礼品</li>
		</ul>
	</div>
	
	<!--产品信息 开始--> 
	<div id="showGiftListAndPic">
		<%@ include file="/view/smallscale/pointmall/showgift/gift_list_div_l.jsp" %>
	</div>
	
</div>


<div id="contentSupp" class="index2paR hidden"></div>



