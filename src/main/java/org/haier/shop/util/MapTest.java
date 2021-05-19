package org.haier.shop.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class MapTest {
	@Test
	public void test1(){
		Map<String,Object> map=new HashMap<String,Object>();
		for(int i=0;i<100;i++){
			map.put(i+"",i);
		}
		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		while(it.hasNext()){
			System.out.println(it.next().getKey());
		}
	}
}
