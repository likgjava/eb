<%@ page pageEncoding="UTF-8"%>
<script language="javascript" type="text/javascript">
	$(function(){
		$('#toVoteAndShowResult').click(function(){
			fnShowSub();
			$("#contentSub").loadPage("view/staticpags/load/left_cms.html");
			$("#contentMain").loadPage("${load}/userVote.html");
		});
	})
</script>
<!--客服中心 开始-->
<div class="callCenter HlineHeight">
  <h3>客服中心</h3>
 <p><label for="phone">电话：</label> <em id="phone" class="em">010-88354986-567</em></p>
 <p><label for="email">邮箱：</label> <em id="email" class="em">service@ebid360.com</em></p>
  <!--
  <ul>
    <li><a title="" href="#">服务条款</a></li>
    <li><a title="" href="#">网站介绍</a></li>
    <li><a title="" href="#">会员体制</a></li>
    <li><a title="" href="#">积分规则</a></li>
    <li><a title="" href="#">常见问题</a></li>
    <li><a title="" href="#">投诉建议</a></li>
  </ul>
  -->
</div>
<!--客服中心 结束-->
<!--采购操作指南 开始-->
<div class="guide">
  <#if newbieHelp?? && newbieHelp?size!=0>
  <h3>${newbieHelp[0].channel.name!}</h3>
  <ul>
        <#list newbieHelp as l>
        <li><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_blank" title="${l.title!}">${l.title}</a></li>
        </#list>
  </ul>
  </#if>
</div>
<!--采购操作指南 结束-->
<!--销售操作指南 开始-->
<div class="joinUs">
  <#if join?? && join?size!=0>
  <h3>${join[0].channel.name!}</h3>
  <ul>
    <#list join as l>
        <li><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_blank" title="${l.title!}">${l.title}</a></li>
    </#list>
  </ul>
  </#if>
</div>
<!--销售操作指南 结束-->
<!--代理操作指南开始-->
<div class="guide">
  <#if comments?? && comments?size!=0>
  <h3>客户意见</h3>
  <ul>
      <#if vote??>
        <li><a href="javascript:void(0)" id="toVoteAndShowResult"  title="${vote.title!}">${vote.title!}</a></li>
      </#if>
    <#list comments as l>
        <li><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_blank" title="${l.title!}">${l.title}</a></li>
    </#list>
  </ul>
  </#if>
</div>
<!---代理操作指南 结束-->
