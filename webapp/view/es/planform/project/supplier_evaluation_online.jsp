<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/supplier_evaluation_online.js"></script>  
<div class="formLayout">  
  
<div class="partContainers operationLog"><h5 id="supplierEvaluation"><span class="switch  left11">投标单位资格预审</span></h5>	</div>
  <div id="supplierEvaluationDetail">
<ul>
 <li style="color:background;font-weight:bold">项目信息及状态</li>
<li>
 <table border="1" style="margin-left: 5px">
 <tr><td colspan="4" width="800px" style="color: green;font-weight:bold"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>信息</td></tr>
 <tr><td align="right" style="padding-right:3px"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>号</td><td style="padding-left:3px">01</td><td align="right" style="padding-right:3px">包组名称</td><td style="padding-left:3px">计算机</td></tr>
 <tr><td align="right" style="padding-right:3px">招标编号</td><td style="padding-left:3px">AH-H2009001</td><td align="right" style="padding-right:3px">招标名称</td><td style="padding-left:3px">计算机,办公自动化设备采购</td></tr>
  </table>
</li>
 <li style="color:background;font-weight:bold">投标单位<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>信息</li>
<li>
  <table border="1" style="margin-left: 5px">
 <tr><td colspan="3" width="800px"></td></tr>
 <tr><td style="padding-left:3px" width="200px">评议内容</td><td style="padding-left:3px" width="440px">评议内容</td><td style="padding-left:3px" width="60px">评审结果</td></tr>
 <tr><td style="padding-left:3px" ></td><td style="padding-left:3px">1.具有独立承担民事责任能力的在中华人名共和国境内注册的法人</td><td align="center"><select name="result" id="result"><option value="1">是</option><option value="0">否</option></select></td></tr>
 <tr><td style="padding-left:3px;border-top-style:hidden;border-bottom-style:hidden; " >投标单位资格要求(合同<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>人条件)</td><td style="padding-left:3px">2.在本市有固定售后服务点</td><td align="center"><select name="result" id="result"><option value="1">是</option><option value="0">否</option></select></td></tr>
 <tr><td style="padding-left:3px"></td><td style="padding-left:3px">3.独立于招标单位和采购招标中心的投标单位，具有合法的生产或销售(代理)经营权</td><td align="center"><select name="result" id="result"><option value="1">是</option><option value="0">否</option></select></td></tr>
 <tr><td style="padding-left:3px">商务条件</td><td style="padding-left:3px">商务无重大偏移或保留</td><td align="center"><select name="result" id="result"><option value="1">是</option><option value="0">否</option></select></td></tr>
 <tr><td style="padding-left:3px">技术条件</td><td style="padding-left:3px">主要技术规格，方案无重大偏移或保留</td><td align="center"><select name="result" id="result"><option value="1">是</option><option value="0">否</option></select></td></tr>
 <tr><td style="padding-left:3px"></td><td style="padding-left:3px">1.有效期符合规定</td><td align="center"><select name="result" id="result"><option value="1">是</option><option value="0">否</option></select></td></tr>
 <tr><td style="padding-left:3px;border-top-style:hidden;border-bottom-style:hidden;"></td><td style="padding-left:3px">2.<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>价格是固定唯一价</td><td align="center"><select name="result" id="result"><option value="1">是</option><option value="0">否</option></select></td></tr>
 <tr><td style="padding-left:3px"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>条件</td><td style="padding-left:3px">3.投标高于财政部门核定的预算价</td><td align="center"><select name="result" id="result"><option value="1">是</option><option value="0">否</option></select></td></tr>
 <tr><td style="padding-left:3px;border-top-style:hidden;border-bottom-style:hidden;"></td><td style="padding-left:3px">4.<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>价格合理没有重大遗漏</td><td align="center"><select name="result" id="result"><option value="1">是</option><option value="0">否</option></select></td></tr>
 <tr><td style="padding-left:3px"></td><td style="padding-left:3px">5.是实质性响应<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></td><td align="center"><select name="result" id="result"><option value="1">是</option><option value="0">否</option></select></td></tr>
  </table>
 </li>
</ul>
    <div class="pageSubmitBtnBox">
      <button type="button" class="largeBtn"><span>刷新状态</span></button>
      <button type="button" class="largeBtn" id="complete"><span>我已完成评审</span></button>
      <button type="submit" class="largeBtn" id="evaluationNextStep"><span>进入下一步评审</span></button>
    </div>
 </div> 
</div>    
