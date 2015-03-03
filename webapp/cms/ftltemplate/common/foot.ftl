<%@ page contentType="text/html;charset=UTF-8"%>

<script>
/*加载系统信息*/
$.sysInfo_load();
</script>

<p class="footerNav">
	<#if foot?? && foot?size != 0>
		<#list foot as l>
				<a href="javascript:void(0)" title="${l.title}" class="cmsHref_blank" id="${l.url!}">${l.title}</a> 
		</#list>
	</#if>
</p>
<p>
	<a target="_blank" href="http://www.chinabidding.com.cn/" title="国信创新"><img src="<%=request.getContextPath()%>/view/resource/images/ebid360.png" /></a>
	<a target="_blank" href="http://www.gpcsoft.com/" title="政采软件"><img src="<%=request.getContextPath()%>/view/resource/images/gpcsoft.gif" /></a>
</p>
<p class="copyright">Copyright &copy; 2005-2015 阳光易购电子采购与招标第三方公共服务平台</p>
<div align="center"> 
	<!-- 统计数目 -->
	<script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
		document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fa3d11dd1ee43acba6fa29feffdca5f79' type='text/javascript'%3E%3C/script%3E"));
	</script>
</div>