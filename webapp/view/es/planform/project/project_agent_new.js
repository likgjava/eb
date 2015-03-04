

$(document).ready(function(){
	var url = $('#initPath').val()+'/ProjectPlanController.do?method=getStatusLightData';
	var data = {projectId:'402887e42aa6cc77012aa6d96a900018'};
	//$.getJSON($("#initPath").val()+"/view/es/planform/project/status_light.txt",{},function(json){
	$.getJSON(url,data,function(json){
		var result = json.result;
		$.each(result,function(i,n){
			var li = document.createElement('li');
			li.innerHTML ='<a href="#">'+n.name+'</a>';
			li.id=n.url;
			$('#secondLevelUl').append(li);
		});
		$('#secondLevelUl>li').click(function(){
			if ($(this).hasClass('selected'))return false;
			//alert($(this).attr('id'));
			$(this).siblings('li').removeClass('selected').end().addClass('selected');
			$('#projectMain .BtnDiv').html('');
			var index = $('#secondLevelUl>li').index($(this));
			$.each(result[index].children,function(j,m){
				$('#projectMain .BtnDiv').append('<button class="report_go" id='+m.url+' type="button"><span>'+m.name+'</span></button>');
			})
			$('#projectMain .BtnDiv>button').click(function(){
				alert($(this).attr('id'));
			});
		});
		$('#secondLevelUl>li:first').click();
	})
	
	
})