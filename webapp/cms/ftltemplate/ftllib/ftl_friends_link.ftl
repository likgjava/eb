  <#assign count=0/>       
  <#assign options=""/> 
   <#assign imgs=""/>                 
  <#list list as l>
  <#if l.imgUrl?? && l.imgUrl?length gt 0 && count lt 6>
   <#assign imgs=imgs+"<a href='${l.outUrl!}' title='${l.title}' target='_blank' ><img src='${l.imgUrl}' width='127' height='40'/></a>"/>     
  <#assign count = count+1/>
<#else>
  <#assign options=options+"<option value='${l.outUrl!}'>${l.getTit(titLen)}</option>" />
</#if>

 </#list>
		 ${imgs}
        <select name="friendsLink" style="width:90%;">
          <option value="">更多省市政府采购网站链接</option>
          ${options}
        </select>
