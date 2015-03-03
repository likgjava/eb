<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="list-content">
	<ul class="list-view hlisting sell">
		<c:forEach var="project" items="${PAGERESULT.data}">
			<li class="list-item">
				<h3 class="summary" style="width: 450px;"><a href="javascript:void(0);" onclick="common.goToBulletinDetail('${project.objId}','12');return false;"><c:choose><c:when test="${fn:length(project.projName) > 31}">${fn:substring(project.projName,0,30)}...</c:when><c:otherwise>${project.projName}</c:otherwise></c:choose></a></h3>
		 		<div class="photo">
					<a href="javascript:void(0);" onclick="common.goToBulletinDetail('${project.objId}','12');return false;">
						<c:choose><c:when test="${project.evalEndTime.time > nowDate.time}"><c:set var="imgName" value="being.png"/></c:when><c:otherwise><c:set var="imgName" value="over.png"/></c:otherwise></c:choose>
						<img src='<c:url value="view/resource/skin/pubservice/img/${imgName}" />'>
					</a>
				</div>
				<ul class="attribute">                        	
		            <li class="legend2">
		            	<a href="javascript:void(0);" onclick="show_list.shareBulletin('${project.objId}');return false;"><img src="view/resource/skin/sysicon/16/star.png">&nbsp;分享</a>
		            	<a href="javascript:void(0);" onclick="show_list.addFavorites('${project.objId}');return false;"><img src="view/resource/skin/sysicon/16/award_star_add.png">&nbsp;收藏</a>
		            </li>
		            <li class="place" style="width: 140px;"> ${project.createUser.emp.company.town.parent.parent.name},${project.createUser.emp.company.town.parent.name},${project.createUser.emp.company.town.name}</li>
		        </ul>
		        <div class="extend">采购方式：${project.ebuyMethodCN}</div>
				<p class="seller lister hCard">
					采购单位：${project.buyersName}<br/>
					报价时间：<font color="red"><fmt:formatDate value="${project.evalStartTime}" pattern="yyyy-MM-dd HH:mm"/> ~ <fmt:formatDate value="${project.evalEndTime}" pattern="yyyy-MM-dd HH:mm"/></font>
		        </p>
		    </li>
		</c:forEach>
	</ul>
</div>
<div><%@ include file="/view/pubservice/common/pageDirection.jsp" %></div>