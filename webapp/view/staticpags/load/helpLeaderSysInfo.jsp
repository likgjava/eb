<%@ page pageEncoding="UTF-8"%>
<script language="javascript" type="text/javascript">
	$(function(){
		$('#toVoteAndShowResult').click(function(){
			fnShowSub();
			$("#contentSub").loadPage("view/staticpags/load/left_cms.html");
			$("#contentMain").loadPage("/view/staticpags/load/userVote.html");
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
  <h3>新手帮助</h3>
  <ul>
        <li><a href="javascript:void(0)" id="/view/staticpags/newbieHelp/2c9087922c15f17a012c1adc159005c6.jsp" class="cmsHref_blank" title="如何成为采购大使">如何成为采购大使</a></li>
        <li><a href="javascript:void(0)" id="/view/staticpags/newbieHelp/402885ef2c14c39e012c14f6c97200f6.jsp" class="cmsHref_blank" title="如何实现竞价采购">如何实现竞价采购</a></li>
        <li><a href="javascript:void(0)" id="/view/staticpags/newbieHelp/402885ef2c14c39e012c14f6a40800f4.jsp" class="cmsHref_blank" title="如何注册和身份验证">如何注册和身份验证</a></li>
  </ul>
</div>
<!--采购操作指南 结束-->
<!--销售操作指南 开始-->
<div class="joinUs">
  <h3>加入我们</h3>
  <ul>
        <li><a href="javascript:void(0)" id="/view/staticpags/join/2c9087922ccde195012ccdeb903f0011.jsp" class="cmsHref_blank" title="成为采购大使">成为采购大使</a></li>
        <li><a href="javascript:void(0)" id="/view/staticpags/join/2c9087922ccde195012ccdeb5b44000f.jsp" class="cmsHref_blank" title="成为咨询专家">成为咨询专家</a></li>
        <li><a href="javascript:void(0)" id="/view/staticpags/join/2c9087922ccde195012ccdeb1439000d.jsp" class="cmsHref_blank" title="成为供应商">成为供应商</a></li>
        <li><a href="javascript:void(0)" id="/view/staticpags/join/2c9087922cc48785012cca6d3450158f.jsp" class="cmsHref_blank" title="成为采购人">成为采购人</a></li>
        <li><a href="javascript:void(0)" id="/view/staticpags/join/402885ef2c14c39e012c14f779a800fc.jsp" class="cmsHref_blank" title="成为商圈会员">成为商圈会员</a></li>
  </ul>
</div>
<!--销售操作指南 结束-->
<!--代理操作指南开始-->
<div class="guide">
  <h3>客户意见</h3>
  <ul>
        <li><a href="javascript:void(0)" id="toVoteAndShowResult"  title="用户意见调查">用户意见调查</a></li>
        <li><a href="javascript:void(0)" id="/view/staticpags/comments/402885ef2c14c39e012c14f734ee00fa.jsp" class="cmsHref_blank" title="用户验证机制介绍">用户验证机制介绍</a></li>
  </ul>
</div>
<!---代理操作指南 结束-->
