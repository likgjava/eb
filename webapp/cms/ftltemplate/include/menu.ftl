 <ul id="menuList">
    <#list menu as m>
      <li><a id="chnl_${m.objId}" href="${m.url!}" class="">${m.name!}</a></li>
	</#list>
    </ul>