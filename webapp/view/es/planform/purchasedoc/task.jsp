<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/task.js"></script>
<div class="frameReport" id="taskbookdetail">
<form id="taskForm">
  <div class="reportTitle">  
  <span id="programName" style="font-size:16px;font-weight:bold;color:black"></span>
  <div style="font-size:14px">编号：<span id="taskCode"></span></div>
  </div>
  <div class="title" style="font-size:14px;margin-top:15px">省政府采购中心：</div>
  <p style="font-size:13px">根据省级政府采购预算，现将<span id="agenCy"></span>的计算机、办公自动化设备采购项目（单位申报清单附后，不得指定品牌）下达你单位执行，该项目采购资金总计￥<span id="amt"></span>万元，其中：预算内资金￥40万元，预算外资金￥0元，其他资金￥0元。请你单位按照政府采购程序组织好此次采购活动。
     有关具体事宜，请与财政厅相关处室联系，联系人及电话行政处、宋频、5100262，招标单位<span id="agenCy"></span>联系人:<span id="linkMan"></span>,电话:<span id="linkTel"></span>。</p>
  <div class="signature" style="font-size:14px;margin-top:0px;margin-bottom:3px"><span id="createTime"></span></div>
</form>
</div>
  