package dosya;
import java.util.UUID;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
 









import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 







import javax.websocket.Session;

import org.apache.catalina.SessionListener;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    @Override
    public void init() throws ServletException{
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String fileName = request.getParameter("fileName");
        if(fileName == null || fileName.equals("")){
            throw new ServletException("Go home Yankee");
        }
        
        File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
        if(!file.exists()){
            throw new ServletException("Dosya bulunamadi");
        }
        System.out.println("Dosya:"+file.getAbsolutePath());
        ServletContext ctx = getServletContext();
        InputStream fis = new FileInputStream(file);
        String mimeType = ctx.getMimeType(file.getAbsolutePath());
        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
         
        ServletOutputStream os       = response.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read=0;
        while((read = fis.read(bufferData))!= -1){
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
        System.out.println("Dosya indirildi");
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!ServletFileUpload.isMultipartContent(request)){
            throw new ServletException("Istek yapilirken hata olustu");
        }
        String random = UUID.randomUUID().toString();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html><head></head><body>");
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            String kategori_id = "hata";
        	String dosya_ad = null;
        	String aciklama = null;
        	String tur 		= null;
        	long boyut=0;
        	DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm");
        	String yuklemetarihi = df.format(new Date());
        	int yukleyen = (int) request.getSession().getAttribute("kullanici_id");
            for(FileItem item : fileItemsList){
                if(!item.isFormField()){
                	dosya_ad = new File(item.getName()).getName();
                    item.write( new File(getServletContext().getAttribute("FILES_DIR_FILE")+File.separator+random+dosya_ad));
                    tur = item.getContentType();
                    boyut = (int)item.getSize();
                    out.write(dosya_ad+ " dosyasi basariyla yuklendi.");
                    out.write("<br>");
                    out.write("<a href=\"UploadDownloadFileServlet?fileName="+random+dosya_ad+"\">Indir "+dosya_ad+"</a>");
                }
                else {
                	String fieldName = item.getFieldName();
                	String value = item.getString();
                	if (fieldName.equals("kategori")) {
                        kategori_id = value;
                	}
                	
                	if(fieldName.equals("dosyaadi")){
                		dosya_ad = value;
                	}
                	
                	if(fieldName.equals("aciklama")){
                		aciklama = value;
                	}
                	
                	
				}
           }
            		// SQl sadece test için. Düzeltilmesi lazım. Ancak testlerime göre şu an dosya yüklenince 
            		// kullanıcılar tablosuna kategori, dosya adı ve açıklama bölümlerini yazıyor. Gereksiz yazı
            		String sql = "INSERT INTO dosyalar (dosya_ad,kategori_id,adres,tur,boyut,yukleyen,yuklemetarihi,aciklama) VALUES "
					+ "(?,?,?,?,?,?,?,?)";
            		Connection con= DataBase.getConnection();
            		PreparedStatement ps = null;
            		String adres = random+dosya_ad;
            		
            		if (con != null) {
            			ps = con.prepareStatement(sql);
            			ps.setString(1, dosya_ad);
            			ps.setString(2, kategori_id);
            			ps.setString(3, adres);
            			ps.setString(4, tur);
            			ps.setLong(5, boyut);
            			ps.setInt(6, yukleyen);
            			ps.setString(7, yuklemetarihi);
            			ps.setString(8, aciklama);
            			boolean t = ps.execute();
            		} 
            		else {
            			out.print("Veritabanı bağlantısı kurarken bir hata oluştuğundan işleminizi gerçekleştiremedik.");
            		}
        } catch (FileUploadException e) {
            out.write("Dosya yuklenirken hata olustu.");
        } catch (Exception e) {
            out.write("Dosya yuklenirken hata olustu.");
        }
        out.write("</body></html>");
    }
}
 
