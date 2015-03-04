// setTab(名称前缀,当前数,tab数)
function setTab(name,cursel,n){//用于tab
	for(i=1;i<=n;i++){
	//if (document.getElementById(name+i) !=null){
	
	var menu=document.getElementById(name+i);
	var tab=document.getElementById("tab_"+name+i);
	try{
		menu.className=i==cursel?"selected":"tag";//切换tab样式
		tab.className=i==cursel?"show":"hidden";//显示隐藏tab内容
	}catch(e){
		//	alert("Tab ID错误")
			}
	//}
	//SetWinHeight();
	}
}



//setTabClick(名称前缀,tab总数),不用在每个tab选择标题中加入setTab
function setTabClick(titleName,num,eventType){
	for (var i=1;i<=num;i++){
		var titleId = document.getElementById(titleName+i);
		try{
			eventFun=function(){
				var splitNum = this.id.split(titleName);
				var Tnum  = splitNum[splitNum.length - 1]
				setTab(titleName,Tnum,num);
			}
			switch(eventType){
				case 'mouseover':
					titleId.onmouseover = eventFun;
					break;
				case 'click':
					titleId.onclick = eventFun;
					break;
				default:
					titleId.onmouseover = eventFun;
					break;
			}
			
			
		}catch(e){
		//	alert("TabID："+ titleName + " 不存在"); 
			}
	}

}
function isPreviousSibling(obj){
if (obj.nodeType != 1)
      {obj = obj.previousSibling;}
   return obj;
}

//Tab超长时使用
function tabsRightMove(titleUlId,tabRightArrow){
	var tabTitleLists = document.getElementById(titleUlId);
	var tabParentUL = tabTitleLists.parentNode;
	var tabParentDiv = tabTitleLists.parentNode.parentNode.parentNode;
	var tabPrevious = isPreviousSibling(tabTitleLists.parentNode.previousSibling);
	var tabPrevious2 = isPreviousSibling(tabPrevious.previousSibling);
	
	//alert('scrollWidth: '+tabTitleLists.scrollWidth+'\nclientWidth: '+tabTitleLists.clientWidth+'\noffsetWidth: ' +tabTitleLists.offsetWidth)
	var tabParentULW = tabParentDiv.clientWidth - tabPrevious.clientWidth - tabPrevious2.clientWidth -30;
	tabParentUL.style.width = tabParentULW + 'px';
	var tabArrow = document.getElementById(tabRightArrow);
	if((!tabTitleLists)||(!tabArrow)) return false;
	tabArrow.onclick = function(){
	tabTitleLists.appendChild(tabTitleLists.firstChild); 
	}
	}