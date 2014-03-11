

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Filter {
	File result = new File("/dir/control5.ctl");

	private String join(List<String> list, String format, String split) {
		StringBuilder sb = new StringBuilder(list.size() * 100);
		for (String v : list) {
			sb.append(String.format(format, v, v)).append(split);
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - split.length());
		}
		return sb.toString();
	}

	private void print(String str) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(result));
			// BufferedWriter bw = new BufferedWriter(new FileWriter(new
			// File("f:/control.ctl")));
			bw.write(str);
			bw.flush();
			bw.close();
			bw = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private void print2(String str) {
		System.out.println(str);
	}

	private void load(List<String> list) {
		print("LOAD DATA \n" + "CHARACTERSET ZHS16GBK \n"
				+ join(list, "INFILE '%s' \nBADFILE '%s.log' \n", "")
				+
				// join(list, "BADFILE '%s.log' \n", "") +
				"INTO TABLE table_name \n"
				+ "append \n"
				+ "Fields terminated by \"\t\"\n"
				+ "trailing NULLCOLS \n"
				+ "( \n"
				+ "COOKIE_TOKEN, \n"
				+ "IS_NEW_PV, \n"
				+ "HOST, \n"
				+ "URI, \n"
				+ "QUERY, \n"
				+ "MEMBER_ID, \n"
				+ "TIME date \"yyyy-mm-dd hh24:mi:ss\", \n"
				+ "LOAD_TIME, \n"
				+ "TYPE, \n"
				+ "EVENT_CODE, \n"
				+ "EVENT_PARAM, \n"
				+ "PAGE_GROUP_CODE, \n"
				+ "REFERE_PAGE_URL, \n"
				+ "REFERE_PAGE_BLOCK, \n"
				+ "REFERE_PAGE_INDEX, \n"
				+ "AD_TYPE, \n"
				+ "AD_SOURCE, \n"
				+ "AD_MEDIUM, \n"
				+ "AD_CAMPAIGN, \n"
				+ "AD_TERM, \n"
				+ "AD_GROUP, \n"
				+ "AD_CONTENT, \n"
				+ "GOODS_ID, \n"
				+ "CITY_ID, \n"
				+ "SESSION_ID, \n"
				+ "IP, \n"
				+ "USER_AGENT, \n"
				+ "OS_INFO, \n"
				+ "BROWSER_INFO, \n"
				+ "RESOLUTION_INFO, \n"
				+ "LANG_INFO, \n"
				+ "ARG1, \n"
				+ "ARG2, \n"
				+ "ARG3, \n"
				+ "ARG4, \n"
				+ "ARG5, \n"
				+ "ARG6, \n"
				+ "ARG7, \n"
				+ "ARG8, \n"
				+ "ARG9, \n"
				+ "ARG10, \n"
				+ "PAGE_PROPERTYPE1, \n"
				+ "PAGE_PROPERTYPE2, \n"
				+ "PAGE_PROPERTYPE3, \n"
				+ "PAGE_PROPERTYPE4, \n"
				+ "PAGE_PROPERTYPE5, \n"
				+ "PAGE_PROPERTYPE6, \n"
				+ "PAGE_PROPERTYPE7, \n"
				+ "PAGE_PROPERTYPE8, \n"
				+ "PAGE_PROPERTYPE9, \n"
				+ "PAGE_PROPERTYPE10, \n"
				+ "PAGE_PROPERTYPE11, \n"
				+ "PAGE_PROPERTYPE12, \n"
				+ "PAGE_PROPERTYPE13, \n"
				+ "PAGE_PROPERTYPE14, \n"
				+ "PAGE_PROPERTYPE15, \n"
				+ "PAGE_PROPERTYPE16, \n"
				+ "PAGE_PROPERTYPE17, \n"
				+ "PAGE_PROPERTYPE18, \n"
				+ "PAGE_PROPERTYPE19, \n"
				+ "PAGE_PROPERTYPE20, \n"
				+ "PAGE_PROPERTYPE21, \n"
				+ "PAGE_PROPERTYPE22, \n"
				+ "PAGE_PROPERTYPE23, \n"
				+ "PAGE_PROPERTYPE24, \n"
				+ "PAGE_PROPERTYPE25, \n"
				+ "PAGE_PROPERTYPE26, \n"
				+ "PAGE_PROPERTYPE27, \n"
				+ "PAGE_PROPERTYPE28, \n"
				+ "PAGE_PROPERTYPE29, \n"
				+ "PAGE_PROPERTYPE30, \n"
				+ "PAGE_PROPERTYPE31, \n"
				+ "PAGE_PROPERTYPE32, \n"
				+ "PAGE_PROPERTYPE33, \n"
				+ "PAGE_PROPERTYPE34, \n"
				+ "PAGE_PROPERTYPE35, \n"
				+ "PAGE_PROPERTYPE36, \n"
				+ "PAGE_PROPERTYPE37, \n"
				+ "PAGE_PROPERTYPE38, \n"
				+ "PAGE_PROPERTYPE39, \n"
				+ "PAGE_PROPERTYPE40, \n"
				+ "PAGE_PROPERTYPE41, \n"
				+ "PAGE_PROPERTYPE42, \n"
				+ "PAGE_PROPERTYPE43, \n"
				+ "PAGE_PROPERTYPE44, \n"
				+ "PAGE_PROPERTYPE45, \n"
				+ "PAGE_PROPERTYPE46, \n"
				+ "PAGE_PROPERTYPE47, \n"
				+ "PAGE_PROPERTYPE48, \n"
				+ "PAGE_PROPERTYPE49, \n"
				+ "PAGE_PROPERTYPE50 \n"
				+ ") \n" + "");
	}

	private void removeResultFile() {
		if (result.exists()) {
			result.delete();
		}
	}

	public static void main(String[] args) {
		Filter f = new Filter();
		List<String> list = new FileNameCreate().getNameList();
		try {
			if (list.size() > 0) {
				f.load(list);
			} else {
				f.removeResultFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class FileNameCreate {
		private String getDatePrefix() {
			Calendar cl = Calendar.getInstance();
			cl.add(Calendar.HOUR_OF_DAY, -1);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH");
			return df.format(cl.getTime());
		}

		private String format = "/dir/tracking.log.%s-%s%s";
		private String[] ips = { "88" };
		private String[] servers = { "a", "b" };

		public List<String> getNameList() {
			List<String> res = new ArrayList<String>(20);
			String datePrefix = getDatePrefix();
			for (String ip : ips) {
				for (String server : servers) {
					String f = String.format(format, datePrefix, ip, server);
					if (new File(f).exists()) {
						res.add(f);
					}
				}
			}
			if (res.size() == 0) {
				res.add("");
			}
			return res;
		}
	}
}
