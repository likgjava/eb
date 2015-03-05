<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/resourceList.js"></script>
<form  id="ResourceList" name="ResourceList">
<input type="hidden" name="objId" id="objId" value="">
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
	  <div id="resourceTree" style="overflow-x: auto;overflow-y: hidden"  class="treeContentDiv"></div>
	  <div class="treeResize" ></div>
	</div>

	<div class="treeRight frameSub" id="treeRight">
	  	<div id="resourceDetail" class="formLayout form2Pa detail">
			<div class="treeEditNav">
				<ul>
					<li id="newRes" class="add"><a href="#"><span>新增</span></a> </li>
					<li id="modifyRes" class="edit"><a href="#"><span>修改</span></a></li>
					<li id="deleteRes" class="del"><a href="#"><span>删除</span></a></li>
				</ul>
			</div>
			<h5><span>资源信息</span></h5>
			<ul>
				<li class="fullLine"><label for="sysFlag">所属系统标识：</label><span id="sysFlag"></span></li>
				<li class="fullLine"><label for="resName">资源名称:</label><span id="resName"></span></li>
                    <li class="fullLine"><label for="resLog">是否启用日志:</label><span id="resLog"></span></li>
                    <li class="fullLine"><label for="resSystem">是否属于系统资源:</label><span id="resSystem"></span></li>
                    <li class="fullLine"><label for="resType">类型:</label><span id="resType"></span></li>
                    <li class="fullLine"><label for="resParent">上级资源:</label><span id="resParent"></span></li>
                    <li class="fullLine"><label for="resUrl">资源URL地址:</label><span id="resUrl"></span></li>
			</ul>
		</div>
		<div id="resourceForm">
		<div  class="formLayout ">
			 <h4><span>资源信息</span></h4>
			    <ul>
			      <li>
			        <label>所属系统标识：</label>
			        <select id="sysFlag" name="sysFlag">
			        	<option value="all" >all</option>
			        	<option value="xejy">小额交易系统</option>
			        	<option value="ztb">招投标系统</option>
			        </select>
			      </li>
			      <li>
			        <label for="rname">资源名称:</label>
			        <input id="rname" type="text" value="" name="name" class="required" tabindex="1" />
			        <span class="eleRequired">*</span>
			      </li>
			      <li>
			        <label for="input01">资源URL地址:</label>
			        <input type="text" name="url" id="rurl" class="required" style="width:300px;" />
				    <span class="eleRequired">*</span>			      
		      	 </li>
			     <li>
			        <label for="input01">启用日志:</label>
			        <div>
			          <label>
			          <input name="isLog" id="isLog1" value="0" type="radio" tabindex="2" checked="checked" />是
			           </label>
			          <label>
			          <input name="isLog" id="isLog2" value="1" type="radio" tabindex="3"/>否 
			           </label>
			           </div>
		      	 </li>
			      <li>
			        <label for="input01">系统资源:</label>
			        <div>
			        	<label>
			          	<input name="isSys" id="isSys1" value="0" type="radio" tabindex="4" checked="checked" />是
			          </label>
			          <label>
			          	<input name="isSys" id="isSys2" value="1" type="radio" tabindex="5"/>否
			         </label>
			         </div>
		      	 </li>
			      <li>
			        <label for="input01">类型:</label>
			        <div>
			        	<label>
			          <input name="type" id="type1" value="URL" type="radio" checked="checked" tabindex="6" />
			          <span for="type1">链接 </span>
			          </label>
			          <label>
			          <input name="type" id="type2" value="METHOD" type="radio" tabindex="7"/>
			          <span for="type2">方法 </span>
			          </label>
			          <label>
			          <input name="type" id="type3" value="GADGET" type="radio" tabindex="8"/>
			          <span for="type3">小工具</span>
			          </label>
			        </div>
		      	 </li>
			      <li>
			        <label for="input01">上级资源:</label>
			         <input type="hidden" name="parent.objId" id="parent.objId" value="" tabindex="9">
    				<input type="text" name="parent.name" disabled="disabled" id="parent.name" value="" readonly="readonly" >
			        
		      	 </li>
			      <li>
			        <label for="checkbox1">生成默认菜单:</label>
				    <input name="defaultMenu" id="defaultMenu" value="0" type="checkbox" tabindex="10" />
		      	 </li>
			      </ul>
			     	<div class="conOperation">
				      <button id="saveResource" type="button" tabindex="11"><span>提交</span></button>
				      <button id="returnResource" type="button" tabindex="12"><span>返回</span></button>
				    </div>
			  </div>
			  <div class="formTips">
			    <ul>
			       <li><spring:message code="globe.input.required.prompt"/></li>
			    </ul>
			  </div>
           </div>
      </div>
</div>
</form>