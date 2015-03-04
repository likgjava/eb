<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/project_create.js"></script>
<style>
   #subProjectSave{background:url(<%=request.getContextPath()%>/view/resource/skin/base/img/smallBtn_bgR.png) no-repeat left;}
   #subProjectSave{padding-left:4px; vertical-align:middle; border:0; overflow:visible;text-align:center;}
   a.abtn {color: red}
</style>

<div class="partContainers">
	<input type="hidden" name="parentId" id="parentId" value="${parentId}" />
	<input type="hidden" name="tenderType" id="tenderType" value="${project.tenderType}" />
	<!-- 包组列表 -->
	<table class="tableList" id="SubProjectList">	    
		<caption>		   				 
			 <dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>信息列表
		     <c:choose>
			    <c:when test="${role!='03' && isStart!='yes'}">
							<span id="subProjectSave" >
		 					 [<a class="abtn"  href="#">新&nbsp;增</a>]
							</span>
				</c:when>
				<c:when test="${isStart=='yes'}">
					<span class="red">[已到投标单位报名时间，不能进行包件修改]</span>
				</c:when>
			</c:choose>
		 
		</caption>
  		<thead>
  		  <c:forEach items="${subProjectList}" var="sub" begin="0" end="0">
      		<tr>
          		<th class="center" width="15%"><dm:out value="local__package" tenderType="${sub.tproject.tenderType}">包组</dm:out>编号</th>
          		<th><dm:out value="local__package" tenderType="${sub.tproject.tenderType}">包组</dm:out>名称</th>
          		<th>拆分数量</th>
          		<th>拆分预算（元）</th>
          		<th width="15%">采购方式</th>
          		<th class="operation" width="25%">操作</th>
     		</tr>
     	 </c:forEach>
		</thead>
	<tbody>
	<c:set value="0" var="subProjectCount"/>
	<c:forEach items="${subProjectList}" var="subProject1" >
		<tr id="${subProject1.project.objId}">
			<td align="center" name="prokCodeForCheck">${subProject1.project.projCode }
			</td>
			<td align="center" name="projNameForCheck">${subProject1.project.projName }
			</td>
			<td align="center" name="quantity" class="center">${subProject1.quantity }
			</td>
			<td align="center" name="money" class="center">${subProject1.money }
			</td>
			<td align="center" class="center">
				<c:choose>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '00'}">公开招标</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '01'}">邀请招标</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '02'}">竞争性谈判</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '03'}">询价</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '04'}">单一来源</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '05'}">议价</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '06'}">竞价</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '07'}">反拍</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '08'}">挂牌</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '09'}">竞标</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '10'}">拍卖</c:when>
		      	</c:choose>
			</td>
			<td class="center" width="20%">
			<!-- 判断为招标中心 -->
			<c:if test="${role!='03'}">
			<c:if test="${isStart!='yes'}">
			<a href="#" style="text-decoration: underline;"><span class="sysicon siEdit" onclick="updateSubProject('${ subProject1.project.objId}','${subProject1.project.parentId }');" title="修改">修改</span></a>
			<a href="#" style="text-decoration: underline;"><span class="sysicon siExit" onclick="removeSubProject('${ subProject1.project.objId}');" title="删除">删除</span></a>	
			</c:if>
			<c:if test="${isStart=='yes'}">
				<a href="#" style="text-decoration: underline;"><span class="sysicon siAccept" onclick="viewSubProject('${subProject1.project.objId}');" title="查看">查看</span></a>
			</c:if>
			</c:if>
			<!-- 判断为监管机构 -->
			<c:if test="${role=='03'}">
				<a href="#" style="text-decoration: underline;"><span class="sysicon siAccept" onclick="viewSubProject('${subProject1.project.objId}');" title="查看">查看</span></a>
			</c:if>
			</td>
		</tr>
	</c:forEach>
	</tbody>
    </table>
    
    <c:if test="${fn:length(subProjectList)>0}">
    <div class="conOperation">
   		<button id="finshSaveButton" type="button" tabindex="18"><span>完成</span></button>
   		<span><font color="red">(点击完成，方可进入下一步操作！)</font></span>
 	</div>
 	</c:if>
 	
</div>