package org.haier.shop.util;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
/**
 * 用于保存图片到数据库
 * 
 * @author Administrator
 *
 */
public class PicSaveUtil {
	public static File handleFileUpId(MultipartFile fileName, HttpServletRequest request, String path, String picName) {
		File file = null;
		File fileDir = new File(path);// 此目录是根据店铺id创建的，每个id对应一个目录
		if (fileDir.isDirectory()) {
			File[] subs = fileDir.listFiles();// 获取目录下的子文件
			for (File sub : subs) {// 遍历每一个文件
				if (picName != null && picName.equals(sub.getName().substring(0, sub.getName().lastIndexOf(".")))) {// 只根据文件的名字进行匹配
					sub.delete();// 如果有重名的文件则执行覆盖操作
				}
			}
		}
		if (!fileName.isEmpty()) {
			String name = picName
					+ fileName.getOriginalFilename().substring(fileName.getOriginalFilename().lastIndexOf("."));// 设置文件名
			file = new File(path + File.separator + name);
			System.out.println(file);
			try {
				file.createNewFile();
				fileName.transferTo(file);// 将fileName转成file
			} catch (IOException e) {
				e.printStackTrace();
			} // 创建生成文件
		}
		return file;
	}
}
