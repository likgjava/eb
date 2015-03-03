1、设置流程节点使用参数，仅对当前节点有效。
--设置流程节点使用的部门ID
<setParamValue name="department.objId" value="{SELECTED_DEPT_ID}"></setParamValue>

2、重设流程节点参数，全局有效
<resetParamValue name="CURRENT_DEPT_ID" value="{SELECTED_DEPT_ID}"></resetParamValue>

3、重设流程节点参数服务，工作流改变流程前调用。
<resetParamService className="" methodName="" ></resetParamService>

4、设置流程节点序号，仅对当前节点生效。
<return>5</return>

5、逻辑判定
<if test="{buyerLevel}==2">
... 
</if>

6、执行服务，工作流节点执行后执行扩展服务，用于处理业务逻辑。
<service className="" methodName="" ></service>

7、设定流程节点操作类别。
--设定流程节点处理结果，即 业务的审批结果，待办任务、审批历史 需要使用。
<disposeResult></disposeResult>
--设定难过流程节点审批结果，对于工作流而言只有 通过、驳回、中立 这三种操作。
<auditResult></auditResult>
--设定当前操作是否可收回。
<isTakeBack></isTakeBack>

8、设定节点审核人\岗位，节点里可放入用户帐号，支持逗号分割。节点参数是设置用户ID。
--设定节点审批人
<setProcessNodeAuditUser param="{userIds}">wangwu</setProcessNodeAuditUser>
--设定节点查阅人
<setProcessNodeViewUser param="{userIds}">wangwu</setProcessNodeViewUser>
--设定节点代理人
<setProcessNodeProxyUser param="{userId}">wangwu</setProcessNodeProxyUser>
--设定节点审核岗位
<setProcessNodeAuditPost param="{roleId}">jbr</setProcessNodeAuditPost>
--设定节点查阅岗位
<setProcessNodeViewPost param="{roleId}">jbr</setProcessNodeViewPost>
--设定节点代理岗位
<setProcessNodeProxyPost param="{roleId}">jbr</setProcessNodeProxyPost>

9、指定流程节点、退出 查阅节点、会签节点
--退出查阅节点
<exitViewNode></exitViewNode>
--退出会签节点
<exitCounterSignNode></exitCounterSignNode>

10、设定 工作流审批表单是否显示扩展参数默认true
<isShowNodeExtendParam>true</isShowNodeExtendParam>

11、设定 工作流审批表单是否显示审核按钮默认true
<isShowNodeAuditButton>false</isShowNodeAuditButton>

12、设定 工作流审批表单点击按钮回调JS 方法，返回true程序继续执行，false程序终止。
<onSubmit></onSubmit>

13、会签节点参数，会签节点如果有 通过、驳回、中立操作 都需要设定会签节点参数，且必须一致。
<setCounterSignParam passPollRadix="10"  executeType="auto" autoRestartCounterSign="false" exitNodeToView="false"></setCounterSignParam>
passPollRadix 通过投票数量，即流程达到通过投票基数 流转到下一步,取值范围(1~100),默认值100
executeType 执行方式{auto:自动执行,manual:手动执行} 通过投票数量达到设定数值后,自动流转到下一节点,或会签结束后自动流转到下一节点,默认值manual
autoRestartCounterSign 自动重启会签流程{true,false}默认值false,当会签不通过时处理是否重启会签流程
exitNodeToView 退出的节点转换为查阅节点(true,false)默认值false,当会签流程自动通过，将退出的节点转换为查阅节点