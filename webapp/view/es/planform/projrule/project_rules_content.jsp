<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/project_rules_content.js"></script>
<form class="form2Pa">
<ul class="normalInfoInput formLine">
        <li>
          <label>设定谈判轮次：</label>
          <input class="required" value="2" size="4"/>轮
          <em>*</em>
		</li>
        <li>
          <label>需要报名：</label>
          <input name="radio100" type="radio" id="radio100" value="radio00" />是
          <input name="radio100" type="radio" id="radio100" value="radio00" checked="checked"/>否
          <em>*</em>
        </li>
        <li>
          <label>预算公开：</label>
          <input name="radio101" type="radio" id="radio101" value="radio01" checked="checked"/>是
          <input name="radio101" type="radio" id="radio101" value="radio01" />否
          <em>*</em> 
        </li>
        <li>
          <label>固定保证金：</label>
          <input name="radio102" type="radio" id="radio102" value="radio02"/>是
          <input name="radio102" type="radio" id="radio102" value="radio02"  checked="checked" />否
          <em>*</em>
        </li>
        <li>
          <label>进行踏勘现场：</label>
          <input name="radio103" type="radio" id="radio103" value="radio03"/>是
          <input name="radio103" type="radio" id="radio103" value="radio03"  checked="checked" />否 
          <em>*</em>
        </li>
        <li>
          <label>进行资格预审：</label>
          <input name="radio104" type="radio" id="radio104" value="radio04"/>是
          <input name="radio104" type="radio" id="radio104" value="radio04"  checked="checked" />否
          <em>*</em>
        </li>
        <li>
          <label>进行<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>论证：</label>
          <input name="radio105" type="radio" id="radio105" value="radio05" />是
          <input name="radio105" type="radio" id="radio105" value="radio05" checked="checked"/>否
          <em>*</em>
       </li>
        <li>
          <label>拆分：</label>
          <input name="radio106" type="radio" id="radio106" value="radio06" checked="checked"/>是
          <input name="radio106" type="radio" id="radio106" value="radio06"/>否
          <em>*</em>
       </li>
        <li>
          <label>电子化<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>：</label>
          <input name="radio107" type="radio" id="radio107" value="radio07" />是
          <input name="radio107" type="radio" id="radio107" value="radio07" checked="checked"/>否
          <em>*</em>
       </li>
        <li>
          <label>发布<dm:out  value="local__PURCHASEPREVIEW" tenderType="${project.tenderType}">结果公示</dm:out>：</label>
          <input name="radio108" type="radio" id="radio108" value="radio08" />是
          <input name="radio108" type="radio" id="radio108" value="radio08" checked="checked"/>否
          <em>*</em>
        </li>
        <li>
          <label>发布<dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out>：</label>
          <input name="radio109" type="radio" id="radio109" value="radio09" checked="checked"/>是
          <input name="radio109" type="radio" id="radio109" value="radio09"/>否
          <em>*</em>
       </li>
      <li>
          <label>拆分<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>：</label>
          <input name="radio110" type="radio" id="radio110" value="radio10" />是
          <input name="radio110" type="radio" id="radio110" value="radio10" checked="checked"/>否
          <em>*</em>
        </li>
         <li>
          <label>联合体<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>：</label>
          <input name="radio111" type="radio" id="radio111" value="radio11" />是
          <input name="radio111" type="radio" id="radio111" value="radio11" checked="checked"/>否
          <em>*</em>
        </li>
      </ul>
      </form>
       <div class="conOperation" align="center">
		  <button type="button" id="submitBtn"><span>确定</span></button>    
	  </div>