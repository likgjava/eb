// setTab(����ǰ׺,��ǰ��,tab��)
function setTab(name,cursel,n){//����tab
	for(i=1;i<=n;i++){
	//if (document.getElementById(name+i) !=null){
	
	var menu=document.getElementById(name+i);
	var tab=document.getElementById("tab_"+name+i);
	try{
		menu.className=i==cursel?"selected":"tag";//�л�tab��ʽ
		tab.className=i==cursel?"show":"hidden";//��ʾ����tab����
	}catch(e){
		//	alert("Tab ID����")
			}
	//}
	//SetWinHeight();
	}
}



//setTabClick(����ǰ׺,tab����),������ÿ��tabѡ������м���setTab
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
		//	alert("TabID��"+ titleName + " ������"); 
			}
	}

}
function isPreviousSibling(obj){
if (obj.nodeType != 1)
      {obj = obj.previousSibling;}
   return obj;
}

//Tab����ʱʹ��
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