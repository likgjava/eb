package com.gpcsoft.epp.buyresult.domain;
import java.util.Comparator;

import com.gpcsoft.epp.projreview.domain.OpenBidRecord;

public class BuyResultComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		OpenBidRecord  e1 =(OpenBidRecord) o1;
		OpenBidRecord  e2 =(OpenBidRecord) o2;
		Double d1 =   e1.getQuoteSum().doubleValue();
		Double d2 =   e2.getQuoteSum().doubleValue();
		if(d1<d2){
			return -1;
		}else if(d1>d2){
			return 1;
		}else {
			return 0;
			}
		}
}