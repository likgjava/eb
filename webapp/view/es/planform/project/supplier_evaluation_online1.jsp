<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/supplier_evaluation_online1.js"></script>  
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

 <tr><td style="padding-left:3px" width="30px">序号</td><td style="padding-left:3px" width="70px">项目</td><td style="padding-left:3px" width="50px">评议内容</td><td style="padding-left:3px" width="30px">分值</td><td style="padding-left:3px" width="555px">评分标准</td>																			<td style="padding-left:3px" width="20px">得分</td></tr>
 <tr><td style="padding-left:3px">1</td>               <td style="padding-left:3px"></td>				  <td style="padding-left:3px">市场准入规则</td>          <td style="padding-left:3px">2</td>               <td style="padding-left:3px">提供有效的市场准入证得2分，有总公司一级网络证得1分，该产品有总公司质量认可证得1分。(但不能高于2分)</td>		<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>
 <tr><td style="padding-left:3px">2</td>		       <td style="padding-left:3px;border-top-style:hidden;border-bottom-style:hidden;"></td>				  <td style="padding-left:3px">公司综合实力评价</td>		 <td style="padding-left:3px">3</td>			    <td style="padding-left:3px">评价其应标产品的科研，设计和制造的规模和实力，酌情打分(最优得3分，次之得1分，其余不得分)</td>				<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>
 <tr><td style="padding-left:3px">3</td>		       <td style="padding-left:3px">资信(10分)</td>		  <td style="padding-left:3px">质量体系认证</td>		     <td style="padding-left:3px">1</td>			    <td style="padding-left:3px">生产厂家通过ISO9000质量体系认证，可得1分，否则不得分</td>													<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>
 <tr><td style="padding-left:3px">4</td>		       <td style="padding-left:3px;border-top-style:hidden;border-bottom-style:hidden;"></td>				  <td style="padding-left:3px">质量保证期</td>		     <td style="padding-left:3px">1</td>			    <td style="padding-left:3px">按要求承诺三包期得0.5分，延长一年加0.5分，总分不超过1分</td>												<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>		
 <tr><td style="padding-left:3px">5</td>		       <td style="padding-left:3px"></td>				  <td style="padding-left:3px">业绩评价</td>		         <td style="padding-left:3px">3</td>			    <td style="padding-left:3px">业绩最优得3分，其余酌情打分(但不得高于3分)</td>												<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>		 
 <tr><td style="padding-left:3px">6</td>               <td style="padding-left:3px"></td>				  <td style="padding-left:3px"></td>          			 <td style="padding-left:3px">40</td>               <td style="padding-left:3px">1.完全满足主要技术要求，符合国家相关标准得基本分40分，有一条不满足得0分</td>		<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>
 <tr><td style="padding-left:3px">7</td>		       <td style="padding-left:3px;border-top-style:hidden;border-bottom-style:hidden;"></td>				  <td style="padding-left:3px;border-top-style:hidden;border-bottom-style:hidden;"></td>		 			 <td style="padding-left:3px">3</td>			    <td style="padding-left:3px">2.产品外观设计美观，合理加1分,符合安全操作要求加1分，体现人性化设计加1分</td>				<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>
 <tr><td style="padding-left:3px">8</td>		       <td style="padding-left:3px">技术(50分)</td>		  <td style="padding-left:3px">技术评价</td>		     	 <td style="padding-left:3px">2</td>			    <td style="padding-left:3px">3.产品有第三方检验及实验报告且真实有效加2分</td>													<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>
 <tr><td style="padding-left:3px">9</td>		       <td style="padding-left:3px;border-top-style:hidden;border-bottom-style:hidden;"></td>				  <td style="padding-left:3px;border-top-style:hidden;border-bottom-style:hidden;"></td>		     		 <td style="padding-left:3px">2</td>			    <td style="padding-left:3px">4.与招标内容相关的专利技术，或技术秘密，提供有效证明文件加2分</td>												<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>		
 <tr><td style="padding-left:3px">10</td>		       <td style="padding-left:3px"></td>				  <td style="padding-left:3px"></td>		         	 <td style="padding-left:3px">3</td>			    <td style="padding-left:3px">5.生产厂家加3分，合法有效代理商或生产厂家授权加1分，其余不加分</td>												<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>
 <tr><td style="padding-left:3px">小计</td>		       <td style="padding-left:3px;border-right-style:hidden;"></td>				  <td style="padding-left:3px"></td>		         	 <td style="padding-left:3px">60</td>			    <td style="padding-left:3px"></td>												<td style="padding-left:2px;padding-right:2px"></td></tr>		  		  
 <tr><td style="padding-left:3px">12</td>		       <td style="padding-left:3px;border-bottom-style:hidden;"></td>				  <td style="padding-left:3px">报价完整性</td>		 	 <td style="padding-left:3px">2</td>			    <td style="padding-left:3px">按照招标书提供的格式完整报价得2分，否则不得分</td>				<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>
 <tr><td style="padding-left:3px">13</td>		       <td style="padding-left:3px">商务(40分)</td>		  <td style="padding-left:3px">报价合理性</td>		     <td style="padding-left:3px">30</td>			    <td style="padding-left:3px">基本分15分，<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>人报出的价格标准(总价)与各<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>单位报价平均价(总价)相比每提高或降低2%，相应增加或扣减基本分1分，但最低分不低于10分，最高分不超过30分。</td>													<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>
 <tr><td style="padding-left:3px">14</td>		       <td style="padding-left:3px;border-top-style:hidden;border-bottom-style:hidden;"></td>				  <td style="padding-left:3px">交货期</td>		         <td style="padding-left:3px">5</td>			    <td style="padding-left:3px">满足客户要求的交货期可得5分，每延迟一天扣0.2分，扣完为止。</td>												<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>		
 <tr><td style="padding-left:3px">15</td>		       <td style="padding-left:3px"></td>				  <td style="padding-left:3px">售后服务承诺</td>		     <td style="padding-left:3px">3</td>			    <td style="padding-left:3px">有售后服务承诺得基本分1分，视其承诺情况酌情加分，最多不超过3分。</td>												<td style="padding-left:2px;padding-right:2px"><input type="text" id="score1" size="4"></td></tr>
 <tr><td style="padding-left:3px">小计</td>		       <td style="padding-left:3px;border-right-style:hidden;"></td>				  <td style="padding-left:3px"></td>		         	 <td style="padding-left:3px">40</td>			    <td style="padding-left:3px"></td>												<td style="padding-left:2px;padding-right:2px"></td></tr>		  		 
 <tr><td style="padding-left:3px">总计</td>		       <td style="padding-left:3px;border-right-style:hidden;"></td>				  <td style="padding-left:3px"></td>		         	 <td style="padding-left:3px">100</td>			    <td style="padding-left:3px"></td>												<td style="padding-left:2px;padding-right:2px"></td></tr>		  		 
  </table>
 </li>
</ul>

    <div class="pageSubmitBtnBox">
      <button type="button" class="largeBtn"><span>刷新状态</span></button>
      <button type="button" class="largeBtn" id="complete"><span>我已完成评审</span></button>
    </div>
    </div>
</div>    

