<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>jQuery Autocomplete Plugin</title>
view/resource/plug-in/autocomplete/auto.html
 
<script>

  $(document).ready(function(){
		var url=$('#initPath').val()+'/AutoCompleteController.do?method=autoComplete';
		$("#example").autocomplete(null, {
			url:$('#initPath').val()+'/AutoCompleteController.do?method=autoComplete',
			extraParams:{'queryColumns':'auth_user,usr_id,usr_name'},
			parse:$(this).gpcParse,
			formatItem: function(data, i, total) {   
	            return data.value;   
	        },   
		    formatResult: function(row) {
	            return row.value;
	        },
			max: 8,
	        autoFill: true,   
	        matchContains: true,   
			scroll: true,
			scrollHeight: 400,
		});
		$('#example').result(function(event,data,formatted){
			var id=this.id+'Prompt';this.name=this.id+'Prompt';
			 if($('#'+id).length!=0)$('#'+id).val(formatted);else $(this).after('<input type=text id='+id+' name=example value='+formatted+' ></input>');
		});
  });
	  
	</script>
  
</head>
<body>
  API Reference: <input id="example" /> (try "C" or "E")
</body>
</html>
