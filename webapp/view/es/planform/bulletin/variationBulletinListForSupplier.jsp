<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
	<table class="tableList" id="SubProjectList">
		<caption>更正公告列表</caption>
  		<thead>
      		<tr class="center">
      			<th>招标名称</th>
      			<th>招标编号</th>
      			<th>公告编号</th>
      			<th>公告标题</th>
      			<th>操作</th>
     		</tr>
		</thead>
	<tbody>
		<c:forEach items="${listBulletin}" var="bulletin" varStatus="i">
		<tr>
			<td>${bulletin.project.projName}</td>
			<td>${bulletin.project.projCode}</td>
			<td>${bulletin.bulletinNo}</td>
			<td>${bulletin.bullTitle}</td>
			<td><button id="viewBulletinId" onclick="viewVariationBulletin('${bulletin.objId}')">查看</button></td>
		</tr>
	</c:forEach>
		
	</tbody>
    </table>
</div>

<script language="javascript">

function viewVariationBulletin(objId){
	$.epsDialog({
        title:"更正公告",
        url:$("#initPath").val()+"/VariationBulletinController.do?method=toViewVariationBulletinForSupplierByObjId&objId="+objId,
        width: 750,
        height: 500,
        isReload: false
	});
}


</script>