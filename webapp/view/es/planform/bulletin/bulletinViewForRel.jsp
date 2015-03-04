<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout">
<h5><span>基本信息</span></h5>
	<ul>
		<li>
		<label for="bulletinNo"><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>编号</label>
		<span id="bulletinNo">${bulletin.bulletinNo }</span>
		</li>
		<li>
		<label for="bullTitle"><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>标题</label>
		<span id="bullTitle">${bulletin.bullTitle }</span>
		</li>
	</ul> 
	<div class="conOperation">
	<button id="returnBtn" type="button" tabindex="18"><span>返回</span></button>
	</div>
</div>
	<span style="height:auto;">${bulletin.bullContents}</span>
<script type="text/javascript">

$(document).ready(function(){
	//返回
	$('#returnBtn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/bulletin/relBulletinListForSupervise.jsp');					
		});
	});
</script>
