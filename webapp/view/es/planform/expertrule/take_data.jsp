<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/expertrule/take_data.js"></script>
<div class="formLayout form2Pa">        
				<div class="conOperation" style="text-align:center">
					<button type="button" id="takeUnit"><span>获取单位信息</span></button>
					<button type="button" id="takeCategory"><span>获取品目信息</span></button>
					<button type="button" id="takeCityCode"><span>获取评审地域信息</span></button>
					<button type="button" id="takeExpertGroup"><span>获取专家类型信息</span></button>
					<button type="button" id="takeEdu"><span>获取专家学历信息</span></button>
					<button type="button" id="takeRoom"><span>获取评审室信息</span></button>
				</div>
				<div class="conOperation" style="text-align:center">
					<button type="button" id="takeAll"><span>获取所有信息</span></button>
				</div>
	</div>