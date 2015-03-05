<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/departmentTreeList.js"></script>  
<!--页面按钮开始-->
<div id="pageMenu" class="pageMenu ui-state-default">
    <!-- a href="javascript:void(0);" id="addDepartment" class="addicon plus-2">新增部门</a-->
    <a href="javascript:void(0);" id="refresh" class="addicon refresh">刷新</a>
    <a href="javascript:void(0);" class="addicon copy">导出EXCEL</a>
    <a href="javascript:void(0);" id="print" class="addicon print">打印</a>
    <a href="javascript:void(0);" id="help" class="addicon help">帮助</a>
</div>
<!--页面按钮结束-->

<div class="pageTree"> 
    <div id="treeSort"> 
	    <BUTTON class="btn" id="openAll" disabled="disabled"><span>展开/关闭</span></BUTTON>
	    <BUTTON class="btn" id="addDepartment"><span>新增部门</span></BUTTON>
    </div>
	<div id="leftTree" class="xTree"></div>
</div>

<!--页面内容开始-->
<div class="pageContent treeRight" id="Department_from"> 
</div>
<!--页面内容结束-->