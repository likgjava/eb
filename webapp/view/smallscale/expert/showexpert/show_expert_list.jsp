<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title> 专家库 - 阳光易购电子采购与招标平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/expert/showexpert/show_expert_list.js"></script>
</head>
<body>
<div id="container">
	<input type="hidden" id="districtLevel" value="${districtLevel}"/>
	<input type="hidden" id="districtId" value="${districtId}"/>

	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div id="contentSub" class="hidden"></div>
		<div id="contentMain" class="index2paL">
		 	<div id="conTitle">
		      	<div class="navCurrent">专家展示</div>
		    </div>
		    <div id="conBody"><!--功能页内容-->
				<!--详细搜索条件 开始 -->
				<div class="SubCategoryBox">
					<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png"/>筛选条件：</div>
					<form id="goodsFilter">
		   			<table style="width:100%">
						<tbody>
							<tr>
								<td><label>从事特长年限：</label></td>
								<td>
									<input id="sspecifyYear" name="sspecifyYear" style="width:40px"/> 至 <input id="especifyYear" name="especifyYear" style="width:40px"/> 年
								</td>
								
								<td><label>所属行业：</label></td>
								<td>
									<select name="belongIndustry">
										<option value="">所有</option>
										<c:forEach var="industry" items="${industryList}">
											<option value="${industry.objId}">${industry.name}</option>
										</c:forEach>
									</select>
								</td>
								<td>专家角色：
									<input type="checkbox" name="isConsultant" value="1"/> 咨询专家&nbsp;
									<input type="checkbox" name="isReviewers" value="1"/> 评标专家 
								</td>
							</tr>
							<tr>
								<td><label>专家资质：</label></td>
								<td>
									<html:select id="professionQualificationLevel" name="professionQualificationLevel" code="smallscale.expert.professionQualificationLevel" selectedValue="${expertInfo.professionQualificationLevel}">
										<html:option value="">所有</html:option>
									</html:select>
								</td>
								<td>关键字：</td>
								<td><input name="keyWord" value="${keyWord}" id="keyWord"/></td>
								<td class="operationBtnDiv l"><button type="button" onclick="show_list.makeSearchData('firstPage')"><span>确定</span></button></td>
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
			        		<span class="expertSort sysicon" id="specifySort" ><a href="javascript:void(0);">从事年限</a></span>
			        		<span class="expertSort sysicon" id="validSort" ><a href="javascript:void(0);">入库时间</a></span></li>
			        <li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;个专家</li>
			      </ul>
			    </div>
				<!--排序方式 结束-->
				<!--专家信息 开始--> 
				<div id="showSuppListAndPic">
					<%@ include file="/view/smallscale/expert/showexpert/expert_list_div_l.jsp" %>
				</div>
				<!--专家信息 结束-->
			</div>
		</div>
		<div id="contentSupp" class="index2paR">
			<span id="otherDiv">
				<%@ include file="/view/pubservice/common/customer_service_div.jsp" %>
			</span>
			<span id="reconmendDiv">
				<jsp:include page="/ExpertShowController.do?method=getRecommendExpert&rp=5&page=1&keyWord=${keyWord}"></jsp:include>
			</span>
		</div>
	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
	<!--在线客服开始-->
    <%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
    <!--在线客服结束-->
</div>
</body>
</html>