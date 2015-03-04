<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>


<table class="tableList">
    <caption>机构域名信息</caption>
    <thead>
      <tr>
        <th class="name">机构名称</th>
        <th class="name">注册时间</th>
        <th class="name">二级域名</th>
      </tr>
    </thead>
    <tbody id="secondDomainList">
    	<%@ include file="/view/pubservice/common/sec_domain_list_div.jsp" %>
    </tbody>
    <tfoot>
      <tr>
        <td></td>
        <td></td>
        <td></td>
      </tr>
    </tfoot>
</table>
	
	
	
	
<div class="conOperation center">
 	<button type="button" onclick="sec_domain_manager.refreshOrgSite();" ><span>刷新</span></button>
</div>

<script type="text/javascript">
var sec_domain_manager = {};

//刷新内存中的二级域名
sec_domain_manager.refreshOrgSite = function(){
	//获取
	$.getJSON($("#initPath").val()+"/SecDomainShowController.do?method=toRefreshSecondDomain",{},function(json){
		if(json.success){
			$("#secondDomainList").loadPage("/view/pubservice/common/sec_domain_list_div.jsp");
		}
	})
}
</script>
