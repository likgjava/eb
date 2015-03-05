<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/employeeTreeForm.js"></script>

<div class="contentTools">
	<button  id="chooseModel" class="sysicon siApplication_view_list" type="button"><span>切换列表显示</span></button>
</div>

<div class="treePage frameMainSub frameMs12 fullScreen ">
	<div class="treeOutside frameMain">
		   <div class="treeBtn">
	      	<ul>
	        	<li>
	            	<a id="up" class="upMove" href="javascript:void(0);" onclick="return false;" target="#"  ><span></span></a>
	            </li>
	            <li>
	            	<a id="down" class="downMove" href="javascript:void(0);" onclick="return false;" target="#"  ><span></span></a>
	            </li>
	         </ul> 		
		  </div>
		  <div id="employeeTreeForm" class="treeContentDiv">
		  </div>
	</div>
	<div class="epsContentMenu" >
	  <ul>
	  </ul>
	</div>
	<div class="treeRight frameSub" id="treeRight">
	</div>
</div>