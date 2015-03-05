<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>人才招聘- 中国权威的电子采购与招标第三方公共服务平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/skin07/css/seller.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/listDetail.css" />
<link type="text/css" rel="stylesheet" href="/cms/resbase/foot/help.css" />
<!--JS-->
<script type="text/javascript" src='/view/srplatform/portal/include/common.js'></script>
</head>

<body>

<div id="container">
	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div class="bottom_menu">
		<div class="menu">
			<h2>职位分类</h2>
			<ul>
				<li><a href="#edit">内容编辑</a></li>
				<li><a href="#tech">技术研发</a></li>
				<li><a href="#market">市场营销</a></li>
				<li><a href="#service">客户服务</a></li>
				<li><a href="#product">产品运营</a></li>
			</ul>
		</div><!--.menu-->
		
	
		<div class="content">
			<ul class="breadcrumb">
				<li><a href="">首页</a></li>
				<li><span class="song">&gt;</span>职位分类</li>
			</ul>
			<div class="clearfloat"></div>
			<h1  id="gys">职位分类</h1>
			<h3 class="job_sort" id="edit">内容编辑</h3>
				<h4>岗位一：<strong>信息编辑</strong></h4>		
				<h5>工作职责</h5>
				<ol>
					<li>按照内容建设规划，为所负责的栏目制定切实可行的工作计划；</li>
					<li>以内容建设规范为标准，按照工作计划，保质保量按时完成内容建设任务；</li>
					<li>协助对网站频道信息进行电话审核、校对；</li>
					<li>协助编辑拓展、收集信息源及相关渠道；</li>
					<li>认真执行公司规章制度和工作程序，服从上级指挥和监督检查。</li>
				</ol>
				<h5>任职要求：</h5>
				<ol>
					<li>本科以上学历，应届生亦可；</li>
					<li>较强的人际交往和沟通、协调能力，工作主动性强；</li>
				</ol>
				<div class="fanhui"><a href="#gys">返回顶部</a></div>

			<h3 class="job_sort" id="tech">技术研发</h3>
				<h4>岗位一：JAVA开发工程师</h4>		
				<h5>工作职责</h5>
				<ol>
					<li>负责网站业务和产品项目的开发和维护；</li>
					<li>独立完成产品项目模块的设计与开发；</li>
					<li>能够参与制定开发和架构相关的规范。</li>
				</ol>
				<h5>任职要求：</h5>
				<ol>
					<li>本科以上学历，3年以上JAVA开发工作经验；</li>
					<li>精通HTML/JavaScript/CSS/JSP/XML开发技术，能够独立开发完整的WEB应用程序；</li>
					<li>熟悉Ajax，熟悉常用的js框架；</li>
					<li>有电子商务网站开发经验者优先；</li>
					<li>具有良好的文档编写能力；</li>
					<li>工作踏实，具有强烈的责任心和积极进取精神</li>
				</ol>
				<div class="fanhui"><a href="#gys">返回顶部</a></div>

  
			<h3 class="job_sort"  id="market">市场营销</h3>
				<h4>岗位一：<strong>销售代表</strong></h4>		
				<h5>工作职责</h5>
				<ol>
					<li>负责中国名企排行网（www.paihang360.com）产品销售；</li>
					<li>配合销售经理完成本部门销售任务并制定相关的业务拓展计划；</li>
					<li>宣传推广公司产品、品牌，建立并维护客户关系。</li>
				</ol>
				<h5>任职要求：</h5>
				<ol>
					<li>高中以上学历；</li>
					<li>对互联网行业有一定的了解和认识，能熟练使用办公软件；</li>
					<li>具有良好的沟通、协调和学习能力和团队合作意识；</li>
					<li>有较强的服务意识，能够适应高效率的工作环境，有较强的抗压能力；</li>
					<li>工作积极主动、认真负责、虚心学习、善于在工作中总结、改进和提高；</li>
					<li>需要具备较强的市场开拓和挖掘客户能力，能够做陌生客户的电话拜访沟通。</li>
				</ol>
				
				<div class="fanhui"><a href="#gys">返回顶部</a></div>

				<h3 class="job_sort" id="service">客户服务</h3>
				<h4>岗位一：客户服务代表</h4>		
				<h5>工作职责</h5>
				<ol>
					<li>负责网站售后服务工作，对现有客户进行服务并进行问题解答；</li>
					<li>配合销售经理完成本部门各项产品销售任务；</li>
					<li>搜集同类市场产品信息，制定业务拓展计划；</li>
					<li>宣传推广公司产品、品牌，建立并维护客户关系。</li>
				</ol>
				<h5>任职要求：</h5>
				<ol>
					<li>本科以上学历；</li>
					<li>对互联网产品有一定认识；</li>
					<li>具有良好的沟通能力和学习能力；</li>
					<li>能够承受较大工作压力，有较好的团队合作意识。</li>
				</ol>
				<div class="fanhui"><a href="#gys">返回顶部</a></div>

				
				<h3 class="job_sort" id="product">产品运营</h3>
				<h4>岗位一：产品经理</h4>		
				<h5>工作职责</h5>
				<ol>
					<li>负责公司新产品的创意、策划、改版；管理和协调技术、设计、市场等部门进行相关的开发、运营和推广工作。</li>
					<li>负责产品上线后的数据管理和运营工作，对相关数据进行监控和分析，定期对自身产品、整体行业、竞争对手等进行数据分析并评估；</li>
					<li>组织资源实施产品，对其结果负责；</li>
					<li>负责产品的用户体验优化、功能完善，提升产品黏度，提升用户体验。</li>
				</ol>
				<h5>任职要求：</h5>
				<ol>
					<li>具备良好的分析能力和商业意识； </li>
					<li>具备良好的沟通协调能力； </li>
					<li>具备良好的业务和产品规划能力； </li>
					<li>1年以上产品经理或网站策划从业经验。</li>
				</ol>
				
				<h4>岗位二：网站运营策划</h4>		
				<h5>工作职责</h5>
				<ol>
					<li>负责平台的产品规划及产品发展方向的把握和工作部署；</li>
					<li>跟进产品定义、策划、设计、开发及产品的发布，并持续提升用户体验；</li>
					<li>与其他部门合作，把平台的产品规划，与开心网其他产品线的发展相互融合。</li>
				</ol>
				<h5>任职要求：</h5>
				<ol>
					<li>一年以上电子商务相关工作经验；</li>
					<li>沟通能力强，英语水平优良；</li>
					<li>熟悉网站系统架构、策划推广，具备一定的运营管理经验；</li>
					<li>熟悉网站开发的流程，包括需求、设计、开发、维护等；</li>
					<li>能够制定电子商务活动相关运营工作流程及页面内容规范；</li> 
					<li>了解网站分析的基本方法，具备市场敏感度；</li> 
					<li>能够策划、组织和执行各类线上线下活动； </li>
					<li>熟悉网站编辑的工作内容者优先</li>
				</ol>
				<div class="fanhui"><a href="#gys">返回顶部</a></div>
				<ul class="contact_info">
					<li>人力资源邮箱：hr@chinabidding.com.cn</li>

				</ul>
				
	  </div><!--.content-->
	</div><!--.bottom_menu-->

	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
</div>
</body>

</html>
