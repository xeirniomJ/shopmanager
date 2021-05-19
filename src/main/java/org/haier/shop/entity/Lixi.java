package org.haier.shop.entity;

public class Lixi {
	public static void main(String[] args) {
		int a=365;
		double b=1.00015;
		double sum=1;
		for(int k=0;k<a;k++){
			sum*=b;
		}
		System.out.println(sum);
	}
}
