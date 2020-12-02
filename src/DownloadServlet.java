

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
		//�o��get�覡���檺����filename
		String fileName = request.getParameter("filename");
		System.out.println(fileName);
		//����O�n�U���ɮ�
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		//�o����A�����ɮת��u�O�a�}
		String path = this.getServletContext().getRealPath("/file/"+fileName);
		//System.out.println(path);
		//�@�ӤG�i����J�y
		FileInputStream fis = new FileInputStream(new File(path));
		byte[] b = new byte[1024];
		int length;
		OutputStream out = response.getOutputStream();
		while((length = fis.read(b)) > 0) {
			out.write(b, 0, length);//�g�X
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
