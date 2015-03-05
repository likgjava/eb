<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/updateMyInfo.js"></script>

 <div id="epsTabs">
    <ul>
      <li >
        <a href="#userInfo" id = "tabs0"><span>账号信息</span></a>
      </li>
      <li>
        <a href="#employeeInfo" id = "tabs1"><span>员工信息</span></a>
      </li>
    </ul>
    <div id="userInfo"></div>
    <div id="employeeInfo"></div>
</div>