<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/expert/education_list.js"></script>

<!-- 导航显示 -->
<div class="nextBar">
    <ol class="num5">
        <li class="done current-prev"><span class="first">1. 填写基本信息</span></li> 
        <li class="current"><span>2. 填写教育背景</span></li>
        <li><span>3. 填写培训经历</span></li>
        <li><span>4. 填写任职经历</span></li>
        <li class="last"><span>5. 填写职称信息</span></li>
    </ol>
</div>

<!-- end 导航显示 -->
<div class="formTips attention">
	<ul>
		<li>
			<em id="attentionAdd">新增教育经历请点击
			<span class="sysicon siAdd"><a id="addExpertInfoBtn" href="javascript:void(0);"><strong>新增教育经历</strong></a></span>
			</em>
			<em id="attentionModify" class="hidden">,当前教育经历修改后将重新进行审核！</em>
		</li>
	</ul>
</div>

<input type="hidden" id="expertInfoId" value="${expertInfoId }"/>
<!-- Tab页 -->
<div id="epsTabs" class="">
  <ul>
    <li>
      <a href="#expertInfoView" id = "auditStatus_00"><span>待提交</span></a>
    </li>
    <li>
      <a href="#expertInfoView" id = "auditStatus_01"><span>待审核</span></a>
    </li>
    <li>
      <a href="#expertInfoView" id = "auditStatus_03"><span>被退回</span></a>
    </li>
    <li>
      <a href="#expertInfoView" id = "auditStatus_02"><span>已通过</span></a>
    </li>
  </ul>
  
  <div id="expertInfoView">
    <!-- 订单列表 -->
    <table class="frontTableList" id="ExpertInfoList">
      <thead>
      	<tr>
          <th>毕业院校</th>
          <th>学历</th>
          <th>所学专业</th>
          <th class="center date">毕业时间</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
        