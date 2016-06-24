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

	public void �ͷ�() throws ParsePropertyException, IOException {
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
			items.put("bz", "�Ӱ�ͷ�");
			items.put("jq", "��");
			if (c.get(c.DAY_OF_WEEK) == c.SUNDAY || c.get(c.DAY_OF_WEEK) == c.SATURDAY) {
				items.put("bz", "��ĩ�Ӱ�");
				items.put("jq", "��");
			}
			dataList.add(items);
			if (c.get(c.DAY_OF_WEEK) == c.SUNDAY || c.get(c.DAY_OF_WEEK) == c.SATURDAY) {
				items = new HashMap();
				items.put("date", sd.format(c.getTime()));
				items.put("bz", "��ĩ�Ӱ�");
				items.put("jq", "��");
				dataList.add(items);
			}
		}

		Map beans = new HashMap();
		beans.put("dataList", dataList);
		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS("C:\\workspace\\Test\\WorkMealTemplate.xls", beans, "D:\\�ͷ�.xls");
	}

	public void ��ͨ() throws ParsePropertyException, IOException {
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
			items.put("begin", "��ͨ��˾");
			items.put("end", "������´�");
			items.put("bz", "�Ӱ�ؼ�");
			if (c.get(c.DAY_OF_WEEK) == c.SUNDAY || c.get(c.DAY_OF_WEEK) == c.SATURDAY)
				items.put("bz", "��ĩ�Ӱ�");
			dataList.add(items);
			if (c.get(c.DAY_OF_WEEK) == c.SUNDAY || c.get(c.DAY_OF_WEEK) == c.SATURDAY) {
				items = new HashMap();
				items.put("date", sd.format(c.getTime()));
				items.put("begin", "������´�");
				items.put("end", "��ͨ��˾");
				items.put("bz", "��ĩ�Ӱ�");
				dataList.add(items);
			}
		}

		Map beans = new HashMap();
		beans.put("dataList", dataList);
		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS("C:\\workspace\\Test\\TrafficTemplate.xls", beans, "D:\\��ͨ.xls");
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws ParsePropertyException
	 */
	public static void main(String[] args) throws ParsePropertyException, IOException {
		// TODO Auto-generated method stub
		ExcelBx eb = new ExcelBx();
		eb.��ͨ();
		eb.�ͷ�();
		// Calendar c = Calendar.getInstance();
		// c.set(2011, 7, 10);
		// System.out.println(c.get(c.DAY_OF_WEEK));
	}
	
}
