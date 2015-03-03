${successCase.startTime!?string("yyyy年MM月dd日")} 至 ${successCase.endTime!?string("yyyy年MM月dd日")}，与
<#list supplierList as s >
<#if s_index == 0>
${s.orgName!}
<#else>
、${s.orgName!}
</#if>
</#list>
采用 ${ebuyMethodCN!}方式完成总金额为${budgetTotalMoney!}元的${successCase.projectName}的项目采购！


