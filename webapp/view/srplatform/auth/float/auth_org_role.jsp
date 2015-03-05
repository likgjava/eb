<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<style  type="text/css">
	.spanFont{font: 0.9em/0.9em;font-size: 12;margin-left: 2px;}
</style>
<form>
<div class="partContainers">
	<input type ="hidden" name ="orgId" value="${param.orgId}">
	<table class="tableList">
	<caption>权限列表</caption>
	<c:forEach var="vo" items="${list}" >
			<tr>
				<td class="highlight" colspan="5">
					<span style="cursor: hand" id="${vo.role.objId}" class="hidden sty_butt"></span>
					<input type="checkbox" name="rolId" value="${vo.role.objId}" <c:if test="${vo.checked}">checked="true"</c:if>  ></input>
					<span ><strong>${vo.role.chName}</strong></span>
				</td>
			</tr>
			<tr id="${vo.role.objId}" class="hidden">
			<c:set var="line" value="0"></c:set>
			<c:forEach var="resource" items="${vo.resourceList}" >
					<c:if test="${line == 5}">
							</tr><tr id="${vo.role.objId}" class="hidden">
							<c:set var="line" value="0"></c:set>
						</c:if>
				<td><span>${resource.name}</span></td>
				<c:set var="line" value="${line+1}"></c:set>
			</c:forEach>
			
			</tr>
	</c:forEach>
	</table>
	<div class="conOperation">
	       <button  id="saveOrgRole" type="button" tabindex="19"><span><spring:message code="globe.save"/></span></button>
		   <button  name ="return" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	  </div>
</div>
</form>

<script>
$(document).ready(function(){
	$('button[name=return]').click(function(){
		$('#epsDialogClose').click();
	});
	$('table.tableList').find('tr').each(function(){
		if(!$(this).hasClass('hidden')){
			var nextTr = $(this).next();
			if (undefined != nextTr && null != nextTr && $(nextTr).hasClass('hidden') && $(nextTr).find('td').length>0) {
				$(this).find('span.sty_butt').addClass('sysicon siAdd').attr('title','展开角色资源').show();
			}
		}
	})
	$('span.sty_butt').click(function(){
		if ($(this).hasClass('siAdd')) {
			$(this).removeClass('siAdd').addClass('sireduce').attr('title','收缩角色资源');
			$('tr[id='+this.id+']').show();
		} else {
			$(this).removeClass('sireduce').addClass('siAdd').attr('title','展开角色资源');
			$('tr[id='+this.id+']').hide();
		}
	})
	$('#saveOrgRole').click(function(){
		var rolIds = new Array();
		$('input[type=checkbox][name=rolId]').each(function(){
			if(this.checked)
				rolIds.push(this.value);
		})
		var url = $('#initPath').val()+'/OrganizationController.do?method=auth';
		//alert(rolIds.length);return;
		$.getJSON(url,{'rolIds':rolIds,orgId:$('input[name=orgId]').val()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("授权成功!");
			$('#epsDialogClose').click();
		});
	});
	
})
</script>
 		
	 