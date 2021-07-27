/**
 * @author AJWuu
 */

package thread;

import java.io.*;
import java.net.URL;
import java.util.Date;

public class Downloader extends Thread {
	//Get initial time
	long d1 = new Date().getTime();
	private URL urls; //remote path
	private String files; //storage name
	private int idx;
	
	public Downloader (URL urls, String files, int idx) {
		this.urls = urls;
		this.files = files;
		this.idx = idx;
	}

	@Override
	public void run() {
		try {
        		System.out.println(urls);
        		download(urls, files);
        	}
		catch (Exception ex) {
            		ex.printStackTrace();
        	}
		finally {
        		//get the time for downloading each part
        		long d2 = new Date().getTime();
           		System.out.println("The " + (idx+1) + "th time of downloading takes: " + (d2-d1) + "ms");
        	}
    	}

	public static void main(String[] args) throws Exception {	
    		final URL[] urls = {
                	new URL("https://www.pku.edu.cn"),
                	new URL("https://www.baidu.com"),
                	new URL("https://www.sina.com.cn"),
        	};
        	final String[] files = {
                	"pku.htm", 
                	"baidu.htm",
                	"sina.htm", 
        	};
        	for (int idx=0; idx<urls.length; idx++) {
        		Downloader[] w = new Downloader[idx+1]; 
        		w[idx] = new Downloader(urls[idx], files[idx], idx);
        		w[idx].start();
        	}
    	}
    
    	static void download(URL url, String file) throws IOException {
        	try (InputStream input = url.openStream(); OutputStream output = new FileOutputStream(file)) {
            		byte[] data = new byte[1024];
            		int length;
            		while ((length=input.read(data)) != -1) {
                		output.write(data,0,length);
            		}
        	}
        	catch (IOException e) {
        		e.printStackTrace();
        	}
    	}
}
