<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/audit_history.js"></script>
    <div class="partContainers operationLog">
    <h5 id="auditText"><span>审核历史记录</span></h5>
     <div id="setRules">
    <ul class="workRecordList">
    <li>
    <p class="reply"><span>项目审批：审批<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></span><span>处长室：刘美君</span></p>
    <p class="reply">审核通过</p>
    <p class="reply">2009-03-17 15:22</p>
    </li>
    <li>
    <p class="reply"><span>项目审批：审批<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></span><span>处长室：王建民</span></p>
    <p class="reply">审核通过</p>
    <p class="reply">2009-03-17 14:22</p>
    </li>
    <li>
    <p class="reply"><span>项目进展：已创建<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></span><span>综合部：董永权</span></p>
    <p class="reply">建议由采购一部办理</p>
    <p class="reply">2009-03-17 09:22</p>
    </li>
    </ul>
    </div>
    </div>

    