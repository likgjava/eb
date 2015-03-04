<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/message/complain_list.js"></script>

	<form id="complainSearchZone" >
	
	<input name="currentTabID" id="currentTabID"  type="hidden" value="<c:out value="${param.currentTabID}"/>" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>	
		 <li>
	  	        <label for="title">标题</label>	  	        
                <input type="text" name="title" id="title">
                <input type="hidden" name="title_op" value="like">
          </li>
          <li>
	  	        <label for="isDispose">处理状态</label>	  
	  	        <select name="isDispose" id="isDispose" >
                        <option value=''>--全部--</option>
				  		<option value='false'>未处理</option>
						<option value='true'>已处理</option>						
				   </select>              
                <input type="hidden" name="isDispose_op" value="=">
          </li>         		
		  <li class="operationBtnDiv right"><button type="button" id="query"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>
<!-- Tab页 -->
<div id="epsTabs" class="">
  <ul>
    <li>
      <a href="#tellInfo" id = "tabs_tell" class="refreshData"><span>投诉</span></a>
    </li>
    <li>
      <a href="#complainInfo" id = "tabs_complain" class="refreshData"><span>举报</span></a>
    </li>   
  </ul>
  <div id="tellInfo">   
    <table id="tellList" class="frontTableList">
      <thead>
      	<tr>
          <th>标题</th>
          <th class="left">投诉人</th>
          <th class="left datetime">投诉时间</th>
          <th class="left">被投诉机构</th>
          <th class="left operation">处理状态</th>        
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>  
  
  <div id="complainInfo">   
    <table id="complainList" class="frontTableList">
      <thead>
      	<tr>
          <th>标题</th>
          <th class="left">举报人</th>
          <th class="left datetime">举报时间</th>
          <th class="left">被举报机构</th>
          <th class="left operation">处理状态</th>        
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>  

</div>


