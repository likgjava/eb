<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/common/factortypeDeList.js"></script>
<html>
<input type="hidden" name="objId" id="objId" value="">
<div class="treePage frameMainSub frameMs12 fullScreen ">
	<div class="treeOutside frameMain">
		<div id="menuTree" class="treeContentDiv"></div>
		<div class="treeResize" ></div>
	</div>
	<div class="treeRight frameSub" id="treeRight">
	  	<div class="formLayout form2Pa detail"  id="menuDetail">
			<div class="treeEditNav">
				<ul>
					<li id="newFactortypeDe" class="add"><a href="#"><span>新增</span></a> </li>
					<li id="deleteFactortypeDe" class="del"><a href="#"><span>删除</span></a> </li>
				</ul>
			</div>
			<div id="factor_type_form"></div>
		</div>
	    <div id="menu_Form"></div>
	</div>
</div>
</html>
