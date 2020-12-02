

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld,Test GitHut Desktop
 */
@WebServlet(
		urlPatterns = { 
				"/DownloadServlet"
		}, 
		initParams = { 
				@WebInitParam(name = "filename", value = "this is a default filename")
		})
public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset = gb2312");
		//out.println("hello");
		//得到get方式提交的那個filename
		String fileName = request.getParameter("filename");
		System.out.println(fileName);
		//表明是要下載檔案
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		//得到伺服器裡檔案的真是地址
		String path = this.getServletContext().getRealPath("/file/"+fileName);
		//System.out.println(path);
		//一個二進位制輸入流
		FileInputStream fis = new FileInputStream(new File(path));
		byte[] b = new byte[1024];
		int length;
		OutputStream out = response.getOutputStream();
		while((length = fis.read(b)) > 0) {
			out.write(b, 0, length);//寫出
		}
		
		out.flush();
		out.close();
		fis.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
