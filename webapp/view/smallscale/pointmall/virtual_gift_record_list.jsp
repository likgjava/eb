<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/pointmall/virtual_gift_record_list.js"></script>
</head>
<body>	
<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
	<form id="recordSearchZone" >
	<input name="currentTabID" id="currentTabID"  type="hidden" value="<c:out value="${param.currentTabID}"/>" >
		<ul>
		<li>
			<label>礼品名称：</label>
			<input name="gift.giftName" id="gift.giftName">
			<input type="hidden" name="gift.giftName_op" id="giftName_op" value="like">
		</li>	
		 <li>
	  	        <label for="createTime">兑换时间</label>	  	        
                <input class="date"  name="startDate" id="startDate">&nbsp;&nbsp;到
	            <input class="date"  name="endDate" id="endDate">
          </li>
                		
		  <li class="operationBtnDiv right"><button type="button" id="query"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
	</form>
	</div>
<!-- Tab页 -->
<div id="epsTabs" class="epsTabs">
  <ul>
    <li>
      <a href="#dealInfo" id = "tabs_deal" class="refreshData"><span>未处理</span></a>
    </li>
    <li>
      <a href="#dealedInfo" id = "tabs_dealed" class="refreshData"><span>已发货待确认</span></a>
    </li> 
    <li>
      <a href="#dealInfo" id = "tabs_less" class="refreshData"><span>缺货</span></a>
    </li>
    <li>
      <a href="#dealedInfo" id = "tabs_received" class="refreshData"><span>已确认</span></a>
    </li>  
  </ul>

  <div id="dealInfo">   
    <table id="dealList" class="frontTableList">
      <thead>
      	<tr>  
      	  <th class="left">礼品名称</th>             
		  <th class="center date">兑换日期</th> 
		  <th class="center">接受邮件</th>                            
          <th class="operation center">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>   
  
  <div id="dealedInfo">   
    <table id="dealedList" class="frontTableList">
      <thead>
      	<tr>
      	  <th class="left">礼品名称</th>                           
          <th class="center">使用积分</th>   
		  <th class="center date">兑换日期</th>
		  <th class="center">接受邮件</th>		         
          <th class="center datet">处理日期</th>                      
          <th class="operation center">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div> 

</div>
</body>
</html>

