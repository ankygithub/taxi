package export;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExportMonth {
	int year = 2016;

	int month = 4 - 1;

	/**
	 * @param flag
	 * @throws IOException
	 */
	public void export(boolean flag) throws IOException {
		Calendar c = Calendar.getInstance();
		c.set(year, month, 1);
		int num = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日");

		FileWriter fw = new FileWriter("c:\\workspace\\Taxi\\export\\month.txt",flag);
		String timeTemplate1 = "21:";
		String timeTemplate2 = "09:";

		for (int i = 0; i < num; i++) {
			c = Calendar.getInstance();
			c.set(year, month, i + 1);
			StringBuffer sb = new StringBuffer();
			if (c.get(c.DAY_OF_WEEK) == c.SUNDAY || c.get(c.DAY_OF_WEEK) == c.SATURDAY) {
				long l = genMinuter();
				if (l == 0)
					l = l - 1;
				sb.append(sd.format(c.getTime())).append(",").append(timeTemplate2 +  l).append(",").append(timeTemplate2 + (l + getDisMinuter()));
				fw.write(sb + ",周末" + "\n");
				sb = new StringBuffer();
				sb.append(sd.format(c.getTime())).append(",").append(timeTemplate1 +   l).append(",").append(timeTemplate1 + (l + getDisMinuter()));
				fw.write(sb + ",周末" + "\n");
			} else {
				long l = genMinuter();
				if (l == 0)
					l = l - 1;
				sb.append(sd.format(c.getTime())).append(",").append(timeTemplate1 + l).append(",").append(timeTemplate1 + (l + getDisMinuter()));
				fw.write(sb + "\n");
			}
		}
		fw.flush();
		fw.close();
	}

	public long genMinuter() {
		return Math.round(Math.random() * 13+12);
	}
	
	
	public long getDisMinuter(){
		return Math.round(Math.random() * 10+22);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExportMonth em = new ExportMonth();
		try {
			em.export(false);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
