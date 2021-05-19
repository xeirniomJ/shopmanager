package org.haier.shop.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class ExcelUtil<T> {
	@SuppressWarnings("unchecked")
	public  void buildExcel(List<T> list, OutputStream out) {
		
		//如果传入为空，返回
		if (list.isEmpty()) {return;}

		Object entity = null;
		Class<T> entityClass = null;//情况实体类
		Field[] fields = null;//打印字段值清空
		HSSFWorkbook workbook = null;//表格清空
		HSSFSheet sheet = null;//�½����
		HSSFCellStyle style = null;//excel��ʽ
		HSSFRow row = null;//行清空
		HSSFCell cell = null;//单元格情况
		
		for (int count = 0; count <= list.size(); count++) { //ѭ����list��size��һ��
			if (count == 0) { //首行获取类的字段名
				entity = list.get(count);//获取第一关实体类
				entityClass = (Class<T>) entity.getClass();//获取类的类型
				fields = entityClass.getDeclaredFields();//获取类的各个字段值

				workbook = new HSSFWorkbook(); //创建新的表格
				sheet = workbook.createSheet(); //创建excel表
				style = workbook.createCellStyle();  // ����һ�����и�ʽ
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				row = sheet.createRow(count); ////���ñ�ͷ�ֶΣ����У�
				for (short i = 0; i < fields.length; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(style);
					cell.setCellValue(fields[i].getName());
				}
			}
			else {
				row = sheet.createRow(count);
				for (short i = 0; i < fields.length; i++) {

					String name = null;
					String methodName = null;
					Method method = null;					
					Object fieldValue = null;

					try {
						name = fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
						methodName = "get" + name;
						method = entityClass.getMethod(methodName);
						entity = list.get(count-1); //��ȡʵ����
						fieldValue = method.invoke(entity);
					} catch (Exception e) {
						e.printStackTrace();
					}

					cell = row.createCell(i);
					cell.setCellStyle(style);
					if(fieldValue==null){
						continue;
					}
					if (fieldValue instanceof Integer) {  
						Integer intValue = (Integer) fieldValue;  
						cell.setCellValue(intValue);
					} else if (fieldValue instanceof Float) {  
						float fValue = (Float) fieldValue;  
						cell.setCellValue(fValue);
					} else if (fieldValue instanceof Double) {  
						double dValue = (Double) fieldValue;  
						cell.setCellValue(dValue);  
					} else if (fieldValue instanceof Long) {
						long lValue = (Long) fieldValue;
						cell.setCellValue(lValue);  
					} else if (fieldValue instanceof Boolean) {  
						boolean bValue = (Boolean) fieldValue;
						cell.setCellValue(bValue?"��":"Ů"); //����true
					}  else if (fieldValue instanceof Date)  
					{  
						Date date = (Date) fieldValue;  
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
						cell.setCellValue(sdf.format(date));  
					} else {
						cell.setCellValue(fieldValue.toString());
					} 
				}
			}
		}
		try {
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
