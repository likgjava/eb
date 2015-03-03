<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<div class="conBody conBody-three conbody-beer-three">
    <!--功能页内容-->
    <h1 class="center">${project.projName }结果公告</h1>
    <div class="project-number">[项目编号：${project.projCode }]<span>${currentDate!?string("yyyy-MM-dd") }</span></div>    
    <table border="0" cellspacing="0" cellpadding="0" class="public-table" >
      <tr>
        <th>一、采购单位：</th>
        <td>${project.buyersName }</td>
      </tr>
      <tr>
        <th>二、项目名称：</th>
        <td>${project.projName }</td>
      </tr>
      <tr>
        <th>三、项目编号：</th>
        <td>${project.projCode }</td>
      </tr>
      <tr>
        <th>四、发布日期：</th>
        <td>${project.createTime }</td>
      </tr>
      <tr>
        <th>五、竞价开始时间：</th>
        <td>${project.evalStartTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
      </tr>
      <tr>
        <th>六、竞价结束时间：</th>
        <td>${project.evalEndTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
      </tr>
      <tr>
        <th>七、报价规则：</th>
        <td>
        	<#list projruleList as projProcessRule >
				<#if projProcessRule.code == 'bargainNumber'>
					<#if projProcessRule.resValue == '0'>单报价次<#else>多次报价</#if>&nbsp;&nbsp;
				</#if>
			</#list>
		</td>
      </tr>
      <tr>
        <th>八、是否委托代理：</th>
        <td><#if project.agencies??>委托给阳光易购平台进行代理<#else>不采用委托代理</#if></td>
      </tr>
      <tr>
        <th>九、报名条件：</th>
        <td>企业资质：${projectSignInfo.companyQualification!}</td>
      </tr>
      <tr>
        <th>十、交货方式：</th>
        <td>${projectPayInfo.deliveryTypeCN!}</td>
      </tr>
      <tr>
        <th>十一、补充说明：</th>
        <td>${projectPayInfo.supplement!}</td>
      </tr>
      <tr>
        <th>十二、联系方式：</th>
        <td>${projectContactInfo.linker!}&nbsp;&nbsp;${projectContactInfo.mobilePhone!}&nbsp;&nbsp;${projectContactInfo.fixedTelephone!}</td>
      </tr>
      <tr>
      	<th>十三、成交结果</th>
 		<td>${buyResult.buyrResultCN!}<br>
 			成交供应商: <#list buyWinnerList as buyWinner>${buyWinner.sellerName}&nbsp;</#list>
 		</td>
      </tr>
    </table>
    <ul>
      <li><h2>采购商品列表：</h2></span>
        <table border="0" cellspacing="0" cellpadding="0" class="goods-table" style="width:100%";>
          <tr class="goods-table-fristTr">
            <td>序号</td>
            <td>商品分类</td>
            <td>商品名称</td>
            <td>规格型号</td>
            <td>采购数量</td>
          </tr>
			<#list goodsList as goods >
				<tr>
					<td>${goods['no'] }</td>
					<td>${goods['goodsClass'] }</td>	
					<td>${goods['name'] }</td>
					<td>${goods['productCode'] }</td>
					<td>${goods['qty'] }</td>
				</tr>
			</#list>
        </table>
      </li>
      <li>${projectSignInfo.additionalMemo!}</li>
    </ul>
</div>

