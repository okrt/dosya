package dosya;

import java.sql.*;

import dosya.Users;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class abuse {
	static String itemsPerPage="5";
	public static String createAbuse(String fileID, String sikayetedenkullanici_id, String neden){
		int fileID_int = Integer.parseInt(fileID.trim());
		int flaggeduser = Integer.parseInt(sikayetedenkullanici_id.trim());
		
		DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm");
    	String tarih = df.format(new Date());
    	
		PreparedStatement ps = null;
		String sql = "INSERT INTO sikayetler (dosya_id,kullanici_id,neden,tarih) VALUES "
				+ "(?,?,?,?)";
		Connection con = DataBase.getConnection();
            if(con!=null){
            	try{
        			ps = con.prepareStatement(sql);
        			ps.setInt(1, fileID_int);
        			ps.setInt(2, flaggeduser);
        			ps.setString(3, neden);
        			ps.setString(4, tarih);
        			boolean t = ps.execute();
        			if (t) {
        				return "Sorgu işleme hatası";
        			} else {
        				return "OK";
        			}
        			}
        			catch(Exception e){
        				return "Veritabanı hatası oluştu!";
        			}
        			
        		} else {
        			return "Veritabanına bağlanılamadı!";
        		}
	}
	
	public static String dosyaadi(String fileID){
		int fileID_int = Integer.parseInt(fileID.trim()); //fileID sayı olmalı
		String d="";
		Connection con = DataBase.getConnection();
		if(con!=null){

			String query="select dosya_ad from dosyalar where dosya_id='"+fileID_int+"'";
		    Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				while (rs.next()) {
				    	    String dosya_ad = rs.getString("dosya_ad");
			            	d=d+dosya_ad;
			            	}
			    }
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}  
		    
		}
		return d;  
	}

	
	public static String getAbuseList(String limit){
		String d="";
		int item=Integer.parseInt(limit)*Integer.parseInt(itemsPerPage);
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım. Gereksiz yazı
        	String query="select * from sikayetler ORDER BY sikayet_id LIMIT "+item+","+itemsPerPage;  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				
				while (rs.next()) {
				d=d+"<tr><td>"+Users.GetUserNameFromFileID(rs.getInt("dosya_id"))+"</td><td><a href=\"fileview.jsp?fileID="+rs.getString("dosya_id")+"\">Görüntüle</a></td><td>"+rs.getString("neden")+"</td><td>"+rs.getString("tarih")+"</td><td><a href=\"s_abuse.jsp?action=ignore&id="+rs.getInt("sikayet_id")+"\">Yoksay</a></td><td><a href=\"s_abuse.jsp?action=delete&id="+rs.getInt("dosya_id")+"\">Sil</a> </td><td><a href=\"s_abuse.jsp?action=ban&id="+Users.GetUserIDFromFileID(rs.getInt("dosya_id"))+"\">Engelle</a></td></tr>";
						
				}
					
				}
				
	       
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return d;

	}

	public static boolean ignoreAbuse(String fileID){
		Connection con = DataBase.getConnection();
		String sql="delete from sikayetler where sikayet_id=?";  
        PreparedStatement ps=null;
		try {
				ps = con.prepareStatement(sql);
				ps.setString(1, fileID);
			    ps.execute();
			    return true;
				
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}  
		
	}
	public static String showPageIterator()
	{
		String d="";
		Connection con = DataBase.getConnection();
		String sql="select * from sikayetler";
		Statement st1;
		try{
		st1 = con.createStatement();
		ResultSet rs=st1.executeQuery(sql); 
		int size=0;
		while (rs.next()) {
		    size++;
		}
		int pageCount=size/Integer.parseInt(itemsPerPage);
		
		for(int x=0;x<=pageCount;x++){
			d=d+"<a href=\"s_abuse.jsp?page="+String.valueOf(x)+"\">"+String.valueOf(x+1)+"</a>&nbsp;|&nbsp;";
			
		}
		
		}
		catch(Exception ex)
		{
		
		}
		return d;
	}

}
		