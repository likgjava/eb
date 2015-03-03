<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<div class="frameNews">
<h1 style="text-align:center">邀请函</h1>
<br/>
尊敬的负责人您好:<br/>
&nbsp;&nbsp;&nbsp;&nbsp;我公司（${companyName!}）现有一采购项目${project.projName! }，已甄选贵公司为邀请参加${project.ebuyMethodCN!}供应商之列，希望考虑参加本次活动。诚邀贵单位参加，敬候光临！
<br/><br/>
<div sytle="text-algin:right">
<ul style="float:right">
<li>公司：${companyName!} </li>
<li>代表人：${userName!} </li>
<li>日期：${nowDate!} </li>
</ul>

</div>
<br/><br/><br/><br/>
<ul>
<li>详细内容如下：</li>
<li>1、采购单位：${project.buyersName !}</li>
<li>2、项目名称：${project.projName !}</li>
<li>3、项目编号：${project.projCode !}</li>
<li>4、竞价开始时间：${project.evalStartTime?datetime}</li>
<li>5、竞价结束时间：${project.evalEndTime?datetime}</li>
<li>6、采购形式：${project.ebuyMethodCN!}</li>
<li>7、报价规则：${singlePrice!}</li>
<li>8、是否委托代理：<#if project.agencies??>委托给阳光易购进行代理<#else>不采用委托代理</#if></li>
<li>9、报名条件：${projectSignInfo.companyQualification!}</li>
<li>10、交货方式：${projectPayInfo.deliveryTypeCN!}</li>
<li>11、补充说明：${projectPayInfo.supplement!}</li>
<li>12、联系方式：${projectContactInfo.linker!}&nbsp;&nbsp;${projectContactInfo.mobilePhone!}&nbsp;&nbsp;${projectContactInfo.fixedTelephone!}</li>
<li>13、商品清单：
<div class="formLayout">
<table class="tableList">
	<thead>
		<tr>
			<th>序号</th>
			<th>商品分类</th>
			<th>商品名称</th>
			<th>商品型号</th>
			<th>数量</th>	
		</tr>
	</thead>
	<tbody>
		<#list goodsList as goods >
			<tr>
				<td>${goods['no'] }</td>
				<td>${goods['goodsClass'] }</td>	
				<td>${goods['name'] }</td>
				<td>${goods['code'] }</td>
				<td>${goods['qty'] }</td>
			</tr>
		</#list>
	</tbody>
</table>
</div>
</li>
</div>
