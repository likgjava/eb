<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/bizdata/project/project_agencies_list.js"></script>
<input type="hidden" name="org" id="org" value=""/>
<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span><span class="detailsSwitch" id="hightSearchSwitch">高级搜索</span></h4>	
<form id="ProjectManageSearchForm">
	<ul >
                
                 <li>
                  <label for="project.projCode">项目编号：</label>
                    <input type="text" name="projCode" id="project.projCode" >
                    <input type="hidden" name="project.projCode_op" value="like">
                </li>
               
                <li>
                  <label for="project.projName"> 项目名称：</label>
                    <input type="text" name="projName" id="project.projName">
                    <input type="hidden" name="project.projName_op" value="like">
                </li>
                <li>
                  <label for="project.ebuyMethod">采购方式：</label>
                    <select name="ebuyMethod" id="project.ebuyMethod">
                        <option value="">请选择</option>
                        <option value="00">公开招标</option>
                        <option value="01">邀请招标</option>
                        <option value="02">竞争性谈判</option>
                        <option value="03">询价</option>
                        <option value="04">单一来源</option>
                    </select>
                    <input type="hidden" name="project.ebuyMethod_op" value="like">
                </li>
                 <li class="hightSearch">
                  <label for="project.projImplStatus">实施状态：</label>
                    <select name="projImplStatus" id="project.projImplStatus">
                    <option value="">请选择</option>
                        <option value="00">正常</option>
                        <option value="01">暂停</option>
                        <option value="02">终止</option>
                    </select>
                    <input type="hidden" name="project.projImplStatus_op" value="like">
                </li>
                 <li class="hightSearch">
                  <label for="project.auditStatus">审核状态：</label>
                    <select name="auditStatus" id="project.auditStatus">
                    	<option value="">请选择</option>
                        <option value="00">待审核</option>
                        <option value="01">审核通过</option>
                        <option value="02">审核未通过</option>
                    </select>
                    <input type="hidden" name="project.auditStatus_op" value="like">
                </li>
                <li class="operationBtnDiv">
       				 <button type="button" id="query"><span>查询</span></button>
     			 </li>
              </ul>
</form>
</div>
<div id="epsTabs">
  <ul>
    <li>
      <a href="#projectInfos" id = "tabs_doing" class="refreshData"><span>正在进行中的</span></a>
    </li>
    <li>
      <a href="#projectInfos" id = "tabs_down" class="refreshData"><span>已完成的</span></a>
    </li>
  </ul>
  <div id="projectInfos">
	<table class="frontTableList" id="projectManageList">
		<thead>
			<tr>
				<th >项目编号</th>
				<th class="omission">项目名称</th>
				<th class="center">采购方式</th>
				<th class="omission">采购品目</th>
				<th class="center">进行状态</th>
				<th class="money">采购金额</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
  </div>
</div>
