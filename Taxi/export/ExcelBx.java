package export;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

public class ExcelBx {
	int year = 2016;

	int month = 3 - 1;

	public void 餐费() throws ParsePropertyException, IOException {
		Calendar c = Calendar.getInstance();
		c.set(year, month, 1);
		int num = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		List dataList = new ArrayList();

		for (int i = 0; i < num; i++) {
			c = Calendar.getInstance();
			c.set(year, month, i + 1);
			Map items = new HashMap();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			items.put("date", sd.format(c.getTime()));
			items.put("bz", "加班餐费");
			items.put("jq", "否");
			if (c.get(c.DAY_OF_WEEK) == c.SUNDAY || c.get(c.DAY_OF_WEEK) == c.SATURDAY) {
				items.put("bz", "周末加班");
				items.put("jq", "是");
			}
			dataList.add(items);
			if (c.get(c.DAY_OF_WEEK) == c.SUNDAY || c.get(c.DAY_OF_WEEK) == c.SATURDAY) {
				items = new HashMap();
				items.put("date", sd.format(c.getTime()));
				items.put("bz", "周末加班");
				items.put("jq", "是");
				dataList.add(items);
			}
		}

		Map beans = new HashMap();
		beans.put("dataList", dataList);
		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS("C:\\workspace\\Test\\WorkMealTemplate.xls", beans, "D:\\餐费.xls");
	}

	public void 交通() throws ParsePropertyException, IOException {
		Calendar c = Calendar.getInstance();
		c.set(year, month, 1);
		int num = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		List dataList = new ArrayList();

		for (int i = 0; i < num; i++) {
			c = Calendar.getInstance();
			c.set(year, month, i + 1);
			Map items = new HashMap();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			items.put("date", sd.format(c.getTime()));
			items.put("begin", "联通公司");
			items.put("end", "三叉街新村");
			items.put("bz", "加班回家");
			if (c.get(c.DAY_OF_WEEK) == c.SUNDAY || c.get(c.DAY_OF_WEEK) == c.SATURDAY)
				items.put("bz", "周末加班");
			dataList.add(items);
			if (c.get(c.DAY_OF_WEEK) == c.SUNDAY || c.get(c.DAY_OF_WEEK) == c.SATURDAY) {
				items = new HashMap();
				items.put("date", sd.format(c.getTime()));
				items.put("begin", "三叉街新村");
				items.put("end", "联通公司");
				items.put("bz", "周末加班");
				dataList.add(items);
			}
		}

		Map beans = new HashMap();
		beans.put("dataList", dataList);
		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS("C:\\workspace\\Test\\TrafficTemplate.xls", beans, "D:\\交通.xls");
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws ParsePropertyException
	 */
	public static void main(String[] args) throws ParsePropertyException, IOException {
		// TODO Auto-generated method stub
		ExcelBx eb = new ExcelBx();
		eb.交通();
		eb.餐费();
		// Calendar c = Calendar.getInstance();
		// c.set(2011, 7, 10);
		// System.out.println(c.get(c.DAY_OF_WEEK));
	}
	
}
