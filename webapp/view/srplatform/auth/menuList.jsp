<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/menuList.js"></script>
<form method="post" id="MenuList" name="MenuList" enctype="multipart/form-data">
<input type="hidden" id="jsonStr" name="jsonStr"/>
<input type="hidden" name="objId" id="objId" value="">
<div class="treePage frameMainSub frameMs12 fullScreen ">
	<div class="treeOutside frameMain">
	   	<div class="treeBtn">
		      	<ul>
		        	<li>
		            	<a id="up" class="upMove" href="javascript:void(0);" onclick="return false;" target="#" ><span></span></a>
		            </li>
		            <li>
		            	<a id="down" class="downMove" href="javascript:void(0);" onclick="return false;" target="#"  ><span></span></a>
		            </li>
		         </ul> 		
		</div>
	   <div id="menuTree" style="overflow-x: auto;overflow-y: hidden"  class="treeContentDiv"></div>
	   <div class="treeResize" ></div>
	</div>
	<div class="treeRight frameSub" id="treeRight">
	  	<div class="formLayout form2Pa detail"  id="menuDetail">
					<div class="treeEditNav">
						<ul>
							<li id="modifyMenu" class="edit"><a href="#"><span>修改</span></a></li>
							<li id="deleteMenu" class="del"><a href="#"><span>删除</span></a> </li>
							<li id="newMenu" class="add"><a href="#"><span>新增</span></a> </li>
						</ul>
					</div>
					<h5><span>菜单详细信息</span></h5>
					<ul>
						<li class="fullLine"><label for="input01">菜单名称:</label><span id="menuName"></span></li>
	                    <li class="fullLine"><label for="input02">菜单备注:</label><span id="memuMemo"></span></li>
	                    <li class="fullLine"><label for="input03">是否显示:</label><span id="showFlag"></span></li>
	                    <li class="fullLine"><label for="input04">是否默认菜单:</label><span id="isDefault"></span></li>
	                    <li class="fullLine"><label for="input05">关联资源:</label><span id="menuResource"></span></li>
	                    <li class="fullLine"><label for="input05">菜单图标:</label>
	                    	<div name="newPreview"></div>
						</li>
					
					</ul>
		</div>
			
	    <div id="menuForm">	
	    <div class="formLayout " >
		 		<h4><span>菜单信息</span></h4>
			    <ul>
			      <li>
			        <label for="input01">菜单名称:</label>
			        <input type="text" value=""  name="name" class="required" />
			        <span class="eleRequired">*</span>	
			      </li>
			      <li>
			        <label for="input01">菜单提示语:</label>
			        <input type="text" value=""  name="tip" class="required" />
			        <span class="eleRequired">*</span>	
			      </li>
			      <li>
			        <label for="input01">是否显示:</label>
			        <select name="showFlag" class="required" tabindex="10" >
		          			<option value="1">是</option>
		          			<option value="0">否</option>
		          		</select>
			      </li>
			      <li>
			        <label for="input01">是否默认菜单:</label>
			         <select name="isDefault" class="required" tabindex="10" >
		          			<option value="0">非默认菜单</option>
		          			<option value="1">默认菜单</option>
		          		</select>
			      </li>
			      <li>
			        <label for="input01">上级菜单:</label>
			        <input type="hidden" name="parent.objId" id="parent.objId" value="">
			        <input type="hidden" name="parent.level" id="parent.level" value="">
		    		<input type="text" disabled="disabled" name="parent.name" id="parent.name" value="" readonly="readonly" >
			      </li>
			      <li>
			        <label for="input01">关联资源:</label>
			         <input type="hidden" name="resource.objId" id="resource.objId" value="">
		    			<input type="text" name="resource.name" id="resource.name" value="" readonly="readonly" class="required sysicon siSearch">
		    			<span class="eleRequired">*</span>	
			      </li>
			      <li class="formTextarea">
			        <label for="input01">菜单备注:</label>
			        <textarea name="memo" cols="30" rows="3"   tabindex="14" ></textarea>
			      </li>
			      <li class="formTextarea">
			        <label for="input01">菜单图标:</label>
			        <input name="icon" type="file" id="icon" size="22"/>
			        <div name="newPreview" id="newPreview"></div>
			      </li>
			    </ul>
			    <div class="conOperation">
				    <button type="button" id="saveMenu"><span><spring:message code="globe.save"/></span></button>
					<button type="reset" ><span><spring:message code="globe.reset"/></span></button>
					 <button id="returnMenu" type="button" tabindex="11"><span>返回</span></button>
			   </div>
		 </div>
	  </div>
	  
	  </div>		
	</div>
</form>