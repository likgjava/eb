package com.gpcsoft.epp.worktask.domain;
import java.util.Comparator;

public class WaitTaskComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		WaitprocWorkTask waitProcWorkTaskDTO1 = (WaitprocWorkTask) o1;
		WaitprocWorkTask waitProcWorkTaskDTO2 = (WaitprocWorkTask) o2;
		//------------判断是否催办任务(催办置顶)
		if(WorktaskStatusEnum.HASTEN.equals(waitProcWorkTaskDTO1.getWorktaskStatus())&& !
				WorktaskStatusEnum.HASTEN.equals(waitProcWorkTaskDTO2.getWorktaskStatus())) {
			return -1;
		} if(WorktaskStatusEnum.HASTEN.equals(waitProcWorkTaskDTO2.getWorktaskStatus())&& !
				WorktaskStatusEnum.HASTEN.equals(waitProcWorkTaskDTO1.getWorktaskStatus())) {
			return 1;
		} if(WorktaskStatusEnum.HASTEN.equals(waitProcWorkTaskDTO1.getWorktaskStatus())&& 
				WorktaskStatusEnum.HASTEN.equals(waitProcWorkTaskDTO2.getWorktaskStatus())) {
			return 0-waitProcWorkTaskDTO1.getCreateTime().compareTo(waitProcWorkTaskDTO2.getCreateTime());
		} else 
			return 0-waitProcWorkTaskDTO1.getCreateTime().compareTo(waitProcWorkTaskDTO2.getCreateTime());
	}
}