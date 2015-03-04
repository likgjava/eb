<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" >
var favoritesFrom = {};

//推荐的收藏
favoritesFrom.AddRecommend = function(favoriteKey){
	if($("input[name=favoriteKey]").val()==null||""==$("input[name=favoriteKey]").val()){
		$("input[name=favoriteKey]").val($("input[name=favoriteKey]").val()+favoriteKey);
	}else{
		$("input[name=favoriteKey]").val($("input[name=favoriteKey]").val()+","+favoriteKey);
	}
}

$(document).ready(function(){
	//保存
	$("#favoriteSave").click(function(){
		if(!$('#FavoritesFrom').valid()){return;}
		var favorites = formToJsonObject("FavoritesFrom");
		$.getJSON($("#initPath").val()+"/FavoritesController.do?method=saveFavorites",
				favorites,
				function(json){
					if(json.success){
						alert(json.result);
						$('.epsDialogClose').click();
					}
		});
	});

	//返回
	$("#favoriteReturn").click(function(){
		$('.epsDialogClose').click();
	})
})
</script>

<form id="FavoritesFrom">
<input type="hidden" id="favoriteId" name="favoriteId" value="${param.favoriteId }"/>
<input type="hidden" id="favoriteName" name="favoriteName" value="<%=new String((request.getParameter("favoriteName")).getBytes("ISO-8859-1"),"UTF-8")%>"/>
<input type="hidden" id="favoriteType" name="favoriteType" value="${param.favoriteType }"/>
<div id="addFavorite" class="formLayout form1Pa">
	<h4 style="height: auto;"><span><%=new String((request.getParameter("favoriteName")).getBytes("ISO-8859-1"),"UTF-8")%></span></h4>
	<ul>
		<li>
			<label style="width: 80px;">标签：</label>
			<input id="favoriteKey" name="favoriteKey" size="30" class="required">
		</li>	
		<c:if test="${FavoritesList!=null&&not empty FavoritesList}">
		<li class="fullLine">
			<label style="width: 80px;">推荐收藏：</label>
			<c:forEach var = "favorites" items = "${FavoritesList}">
				<a id="${favorites[0] }" href="javascript:void(0);" onclick="favoritesFrom.AddRecommend('${favorites[1] }');return false;">${favorites[1] }</a>
			</c:forEach>
		</li>
		</c:if>
		<c:if test="${inFavorites!=false}">
			<div class="formTips attention"><span>您已加入收藏</span></div>
		</c:if>
	</ul>
		
	<div class="conOperation">
		<c:if test="${inFavorites==false}">
		<button id="favoriteSave" type="button" class="enterBtn"><span><spring:message code="globe.save"/></span></button>
		</c:if>
		<button id="favoriteReturn" type="button" ><span><spring:message code="globe.close"/></span></button>
	</div>
</div>
</form>