package dosya;
import java.io.File;
import dosya.Users;
import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Fileview {
	public static String fetchFile(String fileID) throws SQLException, NumberFormatException {
	String resim="yuklenen/unavailable.png";
	String d="";
	try
    {
		int fileID_int = Integer.parseInt(fileID.trim()); //fileID sayı olmalı
	
		Connection con = DataBase.getConnection();
        if(con!=null){

        	String query="select dosyalar.dosya_ad,dosyalar.tur,dosyalar.adres,dosyalar.boyut,dosyalar.yuklemetarihi,dosyalar.aciklama,dosyalar.yukleyen,kullanicilar.kadi from dosyalar INNER JOIN kullanicilar ON dosyalar.yukleyen=kullanicilar.kullanici_id where dosya_id='"+fileID_int+"'";
        	
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				int size=0;
				while (rs.next()) {
				    size++;
				}
				rs.beforeFirst();
				if(size==0){
					d=d+"HATA";
				}
				else{
				     while (rs.next()) {
				    	    String dosya_ad = rs.getString("dosya_ad");
				    	 	String tur = rs.getString("tur");
				    	 	String yukleyen = rs.getString("kadi");
				    	 	String adres = rs.getString("adres");
				    	    String boyut = rs.getString("boyut");
				    	 	String yuklemetarihi = rs.getString("yuklemetarihi");
				    	 	String aciklama = rs.getString("aciklama");

				    	 	if(tur.equals("image/png") || tur.equals("image/jpeg") || tur.equals("image/gif")){
				    	 		
				    	 		d=d+"<p><figure><img style=\"float: left;\" src=\"UploadDownloadFileServlet?fileName="+adres+"\" width=\"400\" height=\"301\" alt=\"Resim\"><figcaption><a href=\"abuse.jsp?fileID="+fileID_int+"\">Şikayet</a></figcaption></figure></p><table width=\"300\" border=\"0\" align=\"right\">";
				    	 	}
				    	 	
				    	 	else{
				    	 		d=d+"<p><figure><img style=\"float: left;\" src=\""+resim+"\" width=\"400\" height=\"301\" alt=\"Resim\"><figcaption><a href=\"abuse.jsp?fileID="+fileID_int+"\">Şikayet</a></figcaption></figure></p><table width=\"300\" border=\"0\" align=\"right\">";
				    	 		}
				    	 		
				    	    //Tabloya verileri yazacağım
			            	d=d+"<tr><td>Dosya Adı:</td><td>"+dosya_ad+"</td></tr>";
			            	d=d+"<tr><td>Dosya Türü:</td><td>"+tur+"</td></tr>";
			            	d=d+"<tr><td>Yükleyen:</td><td>"+yukleyen+"</td></tr>";
			            	d=d+"<tr><td>Boyut:</td><td>"+boyut+"</td></tr>";
			            	d=d+"<tr><td>Yükleme Tarihi:</td><td>"+yuklemetarihi+"</td></tr>";
			            	d=d+"<tr><td>Açıklama:</td><td>"+aciklama+"</td></tr>";
			            	
			            	if(rs.isLast()){
			            		d=d+"<tr><td><form method=\"GET\" action=\"UploadDownloadFileServlet\"><input type=\"hidden\" name=\"fileName\" value=\""+adres+"\"> <input id=\"indir\" class=\"juibutton\" name=\"indir\" type=\"submit\" value=\"İndir\"></form></td></tr></table>";
			            		}
			            	}
			            }
					
				}
	       
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            
        }
		
	}
	catch (NumberFormatException nfe)
    {
      System.out.println("Dosya görüntülenirken hata oluştu.");
      
    }
	return d;
}
		public static String showCategoryItems(String categoryID) throws SQLException, NumberFormatException {
			String d="";
			try
		    {
				int categoryID_int = Integer.parseInt(categoryID.trim()); //categoryID sayı olmalı
	    	
	    		Connection con = DataBase.getConnection();
	            if(con!=null){

	            	String query="select * from dosyalar WHERE kategori_id='"+categoryID_int+"'";
	                Statement st1;
	    			try {
	    				st1 = con.createStatement();
	    				ResultSet rs=st1.executeQuery(query); 
	    				
	    				String kategori = Categories.GetNameOfCategory(Integer.parseInt(categoryID));
	    				d=d+"<h2>"+kategori+" kategorisindeki dosyalar</h2>"
			    	 			+ "<table width=\"700\" border=\"0\" align=\"left\"><tr><th align=\"center\">Önizleme</th><th align=\"center\">Dosya Adı</th><th align=\"center\">Açıklama</th><th align=\"center\"><Link</th></tr>";
	    				
	    				     while (rs.next()) {
	    				    	    String adres = rs.getString("adres");
	    				    	 	String dosya_id = rs.getString("dosya_id");
	    				    	    String dosya_ad = rs.getString("dosya_ad");
	    				    	  	String tur = rs.getString("tur");
	    				    	 	String aciklama = rs.getString("aciklama");
	    				    	 	
	    				    	 	if(tur.equals("image/png") || tur.equals("image/jpeg") || tur.equals("image/gif")){
	    				    	 		
	    				    	 		d=d+"<tr><td align=\"center\"><img style=\"float: left;\" src=\"UploadDownloadFileServlet?fileName="+adres+"\" width=\"80\" height=\"60\" alt=\"Resim\"></td>";
	    				    	 		
	    				    	 	}
	    				    	 	else{
	    				    	 		d=d+"<tr><td align=\"center\"><img style=\"float: left;\" src=\"yuklenen/unavailable.png\" width=\"80\" height=\"60\" alt=\"Resim\"></td>";
	    				    	 	}
	    				    	 		
	    				    	    //Tabloya verileri yazacağım
	    			            	d=d+"<td align=\"center\"><p>"+dosya_ad+"</p></td>"
	    			            			+ "<td align=\"center\">"+aciklama+"</td>"
	    			            			+ "<td align=\"center\"><form method=\"GET\" action=\"fileview.jsp\"><input type=\"hidden\" name=\"fileID\" value=\""+dosya_id+"\"> <input id=\"indir\" class=\"juibutton\" type=\"submit\" value=\"Görüntüle\"></form></td></tr>";
	    			            	if(rs.isLast()){
	    			            		d=d+"</table>";
	    			            		}
	    			            	}
	    			            
	    					
	    				}
	    	       
	    				catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}  
	                
	            }
	    		
	    	}
			catch (NumberFormatException nfe)
		    {
		      System.out.println("Dosya görüntülenirken hata oluştu.");
		      
		    }
			return d;
	}
		public static String showUserItems(String kullanici_id) throws SQLException, NumberFormatException {
			String d="";
			try
		    {
	    		Connection con = DataBase.getConnection();
	            if(con!=null){

	            	String query="select dosyalar.kategori_id,dosyalar.adres,dosyalar.dosya_id,dosyalar.dosya_ad,dosyalar.tur,dosyalar.aciklama,kullanicilar.kullanici_id from dosyalar INNER JOIN kullanicilar ON dosyalar.yukleyen=kullanicilar.kullanici_id where dosyalar.yukleyen='"+kullanici_id+"'";
	                Statement st1;
	    			try {
	    				st1 = con.createStatement();
	    				ResultSet rs=st1.executeQuery(query); 
	    				int size=0;
	    				d=d+"<table width=\"700\" border=\"0\" align=\"center\"><tr><th align=\"center\">Önizleme</th><th align=\"center\">Dosya Adı</th><th align=\"center\">Açıklama</th><th align=\"center\"><Link</th></tr>";
	    				while (rs.next()) {
	    				    size++;
	    				}
	    				rs.beforeFirst();
	    				if(size==0){
	    					d=d+"HATA";
	    				}
	    				else{
	    				     while (rs.next()) {
	    				    	 	String dosya_id = rs.getString("dosya_id");
	    				    	    String dosya_ad = rs.getString("dosya_ad");
	    				    	    String adres = rs.getString("adres");
	    				    	  	String tur = rs.getString("tur");
	    				    	 	String aciklama = rs.getString("aciklama");

	    				    	 	if(tur.equals("image/png") || tur.equals("image/jpeg") || tur.equals("image/gif")){
	    				    	 		
	    				    	 		d=d+"<tr><td align=\"center\"><img style=\"float: left;\" src=\"UploadDownloadFileServlet?fileName="+adres+"\" width=\"80\" height=\"60\" alt=\"Resim\"></td>";
	    				    	 		
	    				    	 	}
	    				    	 	else{
	    				    	 		d=d+"<tr><td align=\"center\"><img style=\"float: left;\" src=\"yuklenen/unavailable.png\" width=\"80\" height=\"60\" alt=\"Resim\"></td>";
	    				    	 	}
	    				    	 		
	    				    	    //Tabloya verileri yazacağım
	    			            	d=d+"<td align=\"center\"><p>"+dosya_ad+"</p></td>"
	    			            			+ "<td align=\"center\">"+aciklama+"</td>"
	    			            			+ "<td align=\"center\"><form method=\"GET\" action=\"fileview.jsp\"><input type=\"hidden\" name=\"fileID\" value=\""+dosya_id+"\"> <input id=\"indir\" class=\"juibutton\" type=\"submit\" value=\"Görüntüle\"></form></td></tr>";
	    			            	if(rs.isLast()){
	    			            		d=d+"</table>";
	    			            		}
	    			            	}
	    			            }
	    					
	    				}
	    	       
	    				catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}  
	                
	            }
	    		
	    	}
			catch (NumberFormatException nfe)
		    {
		      System.out.println("Dosya görüntülenirken hata oluştu.");
		      
		    }
			return d;
		}
		
		
		
}
