/*
 * 平台开发  评价  form页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */
var EvaluateForm={};

$(document).ready(function(){
	
	$.each($("#starUL").find("ul"),function(index,obj){
		new Stars(obj.id,{nowClass:"current-rating",tipsTxt:["1分-严重不合格","2分-很不合格","3分-非常差","4分-比较差","5分-很遗憾","6分-刚好合格","7分-继续加油","8分-成绩不错","9分-就差一步","10分-完美"]});
	})
	
	//动态计算总分
	var defult = $("#starUL").find("ul").length;
	$("#starUL").find("a").click(function(){
		var score = $("#starUL").find("div[id=starsDiv]");
		var total = 0;
		var defult = 0;//最高分
		$.each(score,function(index,obj){
			var score = $(obj).find("input:eq(0)").val();
			var proportion = $(obj).find("input[id=proportion]").val();
			if(score!=null&&score!=""&&proportion!=null&&proportion!=""){
				total += parseInt(score)*proportion;
			}
			defult += 10*proportion;
		});
		$("span[id=summaryScore]").html(((total/defult)*10).toFixed(2));
	})
	
	//关闭
	$("#evaluateClose").click(function(){
		$("#evaluateDailog").find('.epsDialogClose').trigger('click');
	})
	
	//保存
	$("#evaluateSave").click(function(){	
		var json = formToJsonObject("EvaluateForm");
		json.summaryScore = $("span[id=summaryScore]").html();
		$.getJSON($('#initPath').val()+'/EvaluateController.do?method=saveEvaluate',
				json,
				function(json){
					if(json.success){
						alert("评价成功!");
						//如果只有一个待评价的机构，则直接关闭评价窗口
						if($("#ulforEvaluate").find('input[type=radio]').length == 1){
							$("#evaluateClose").click();
						}else{
							projectEvlauateDiv.getEvlauteObject();
						}
					}
				}
		);
	})
});
