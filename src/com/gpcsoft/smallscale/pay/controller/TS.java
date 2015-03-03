package com.gpcsoft.smallscale.pay.controller;

public class TS {
	public static void main(String[] args) {
		String ss = "em123_dd_bg_gg";
		
		int split_index_p = 0;
		int split_index_n = ss.indexOf("_");
		String empid = ss.substring(split_index_p, split_index_n);
		
		split_index_p = split_index_n;
		split_index_n = ss.indexOf("_", split_index_n+1);
		String invoiceTitle = ss.substring(split_index_p+1, split_index_n);
		
		split_index_p = split_index_n;
		split_index_n = ss.indexOf("_", split_index_n+1);
		String invoiceItems = ss.substring(split_index_p+1, split_index_n);
		
		split_index_p = split_index_n;
		split_index_n = ss.indexOf("_", split_index_n+1);
		String mailingAddress = ss.substring(split_index_p+1);
		
		System.out.println("empid:"+empid);
		System.out.println("invoiceTitle:"+invoiceTitle);
		System.out.println("invoiceItems:"+invoiceItems);
		System.out.println("mailingAddress:"+mailingAddress);
		
		
//		String[] b = ss.split("_");
//		System.out.println(b.length);
//		for(int i=0; i<b.length; i++){
//			System.out.print(i+":");
//			if(i==0){
//				System.out.println(b[i]);
//			}else if(i==1){
//				System.out.println(b[i]);
//			}else if(i==2){
//				System.out.println(b[i]);
//			}else if(i==3){
//				System.out.println(b[i]);
//			}
//		}
	}
}
