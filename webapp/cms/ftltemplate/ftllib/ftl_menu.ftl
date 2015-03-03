[#ftl]
[#--

头部菜单
--]
[#list list as m]
<li>
<a class="" id="chnl_${m.objId}" href="${m.url!}">${m.name!}</a>
</li>
[/#list]
