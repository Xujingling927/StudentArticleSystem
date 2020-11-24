package servlet;

import dao.PaperDao;
import dao.StudentDao;
import org.apache.poi.hwpf.HWPFDocument;
import pojo.Paper;
import pojo.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet( name = "uploadServlet",urlPatterns = "/upload")
@MultipartConfig(maxFileSize = 10 * 1024 * 1024,maxRequestSize = 20 * 1024 * 1024,fileSizeThreshold = 1024 * 1024)
public class UploadServlet extends HttpServlet {
    private String filePath;
    StudentDao stuDao;
    PaperDao paperDao;
    @Override
    public void init() throws ServletException {
        filePath = getServletContext().getInitParameter("files");
        stuDao = StudentDao.getInstance();
        paperDao = PaperDao.getInstance();
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String savePath = req.getServletContext().getRealPath(filePath);

        String studentId = req.getParameter("stuId");
        String studentName = req.getParameter("stuName");
        String studentClass = req.getParameter("stuClass");
        Student student = new Student(studentId,studentName,studentClass);

        stuDao.insert(student);

        String paperTitle = req.getParameter("paperTitle");
        String paperAuthor = req.getParameter("paperAuthor");
        String paperDate = req.getParameter("paperDate");
        if (paperAuthor.equals("null")){
            paperAuthor = student.getStudentName();
        }else {
            paperAuthor = student.getStudentName() +" "+ paperAuthor;
        }
        Paper paper = new Paper(paperTitle,paperDate,paperAuthor);

        paper.setPaperId(studentId);

        String fileName = "";
        List<Part> parts = (List<Part>) req.getParts();
        for (Part file :parts) {
            if (file.getName().equals("paperFile")){
                try {
                    String header = file.getHeader("content-disposition");
                    fileName = System.currentTimeMillis() + "_" + getFileName(header);
                    file.write(savePath + File.separator + fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        try {
            InputStream input = new FileInputStream(savePath + File.separator + fileName);
            String text = "";
            //读取不同格式word文件
            if ("doc".equals(getPostFix(savePath + File.separator + fileName))){
                System.out.println("it is doc");
                HWPFDocument document = new HWPFDocument(input);
                text = document.getDocumentText();
                paper.setPaperContext(text);
            }
            else System.out.println("Error");
        } catch (Exception e) {
            e.printStackTrace();
        }

        paperDao.insert(paper);

        resp.sendRedirect("/index.jsp");
    }

    private static String getFileName(String header){
        String[] tempArr1 = header.split(";");
        String[] tempArr2 = tempArr1[2].split("=");
        return tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");
    }

    //获取文件后缀
    private String getPostFix(String path){
        if (path == null || "".equals(path.trim())){
            return "";
        }

        if (path.contains(".") && path.lastIndexOf(".") != path.length()-1){
            return path.substring(path.lastIndexOf(".") + 1,path.length());
        }

        return "";
    }
}
