package dosya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users {
	static String itemsPerPage="5";
	public static String GetUserList(String limit){
		String d="";
		int item=Integer.parseInt(limit)*Integer.parseInt(itemsPerPage);
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım. Gereksiz yazı
        	String query="select * from kullanicilar ORDER BY kadi LIMIT "+item+","+itemsPerPage;  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				
				while (rs.next()) {
					if(rs.getBoolean("is_banned")==false){
						d=d+"<tr><td>"+rs.getString("kadi")+"</td><td>"+rs.getString("ad")+"</td><td>"+rs.getString("soyad")+"</td><td>"+rs.getString("eposta")+"</td><td><a href=\"s_users.jsp?action=ban&id="+rs.getInt("kullanici_id")+"\">Engelle</a></td><td><a href=\"s_users.jsp?action=delete&id="+rs.getInt("kullanici_id")+"\">Sil</a> </td></tr>";
						}
						else{
							d=d+"<tr><td>"+rs.getString("kadi")+"</td><td>"+rs.getString("ad")+"</td><td>"+rs.getString("soyad")+"</td><td>"+rs.getString("eposta")+"</td><td><a href=\"s_users.jsp?action=unban&id="+rs.getInt("kullanici_id")+"\">Engeli kaldır</a></td><td><a href=\"s_users.jsp?action=delete&id="+rs.getInt("kullanici_id")+"\">Sil</a> </td></tr>";
						}
				}
					
				}
				
	       
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return d;
	}
	public static String SearchUserList(String keyword){
		String d="";
		
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım. Gereksiz yazı
        	String query="select * from kullanicilar WHERE kadi LIKE "+keyword+" ORDER BY kadi LIMIT 0,30";  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				
				while (rs.next()) {
					if(rs.getBoolean("is_banned")==false){
					d=d+"<tr><td>"+rs.getString("kadi")+"</td><td>"+rs.getString("ad")+"</td><td>"+rs.getString("soyad")+"</td><td>"+rs.getString("eposta")+"</td><td><a href=\"s_users.jsp?action=ban&id="+rs.getInt("kullanici_id")+"\">Engelle</a></td><td><a href=\"s_users.jsp?action=delete&id="+rs.getInt("kullanici_id")+"\">Sil</a> </td></tr>";
					}
					else{
						d=d+"<tr><td>"+rs.getString("kadi")+"</td><td>"+rs.getString("ad")+"</td><td>"+rs.getString("soyad")+"</td><td>"+rs.getString("eposta")+"</td><td><a href=\"s_users.jsp?action=unban&id="+rs.getInt("kullanici_id")+"\">Engeli kaldır</a></td><td><a href=\"s_users.jsp?action=delete&id="+rs.getInt("kullanici_id")+"\">Sil</a> </td></tr>";
					}
				}
					
				}
				
	       
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return d;
	}
	public static boolean deleteUser(String userid){
		Connection con = DataBase.getConnection();
		String sql="delete from kullanicilar where kullanici_id=?";  
        PreparedStatement ps=null;
		try {
				ps = con.prepareStatement(sql);
				ps.setString(1, userid);
			    ps.execute();
			    return true;
				
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}  
		
	}
	public static boolean banUser(String userid){
		Connection con = DataBase.getConnection();
		String sql="UPDATE `kullanicilar` SET `is_banned` = 1 WHERE `kullanici_id` = ?";
        PreparedStatement ps=null;
		try {
				ps = con.prepareStatement(sql);
				ps.setString(1, userid);
			    ps.execute();
			    return true;
				
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}  
		
	}
	public static boolean unbanUser(String userid){
		Connection con = DataBase.getConnection();
		String sql="UPDATE `kullanicilar` SET `is_banned` = 0 WHERE `kullanici_id` = ?";
        PreparedStatement ps=null;
		try {
				ps = con.prepareStatement(sql);
				ps.setString(1, userid);
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
		String sql="select * from kullanicilar";
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
			d=d+"<a href=\"s_users.jsp?page="+String.valueOf(x)+"\">"+String.valueOf(x+1)+"</a>&nbsp;|&nbsp;";
			
		}
		
		}
		catch(Exception ex)
		{
		
		}
		return d;
	}
	public static String GetUserName(int uID){
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım.
        	String query="select * from kullanicilar where kullanici_id='"+Integer.toString(uID)+"'";  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				String name="";
				while(rs.next()){
				name=rs.getString("kadi");}
			return name;
			}
			
			catch(Exception e)
			{return "HATA";}
        }
        else{return "HATA";}}
	public static String GetUserNameFromFileID(int fileID){
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım.
        	String query="select dosyalar.dosya_id,kullanicilar.kullanici_id,kullanicilar.kadi from dosyalar INNER JOIN kullanicilar ON dosyalar.yukleyen=kullanicilar.kullanici_id where dosyalar.dosya_id='"+Integer.toString(fileID)+"'";  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				String name="";
				while(rs.next()){
				name=rs.getString("kadi");}
			return name;
			}
			
			catch(Exception e)
			{return "HATA";}
        }
        else{return "HATA";}}
	public static String GetUserIDFromFileID(int fileID){
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım.
        	String query="select dosyalar.dosya_id,dosyalar.yukleyen,kullanicilar.kullanici_id,kullanicilar.kadi from dosyalar INNER JOIN kullanicilar ON dosyalar.yukleyen=kullanicilar.kullanici_id where dosyalar.dosya_id='"+Integer.toString(fileID)+"'";  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				String name="";
				while(rs.next()){
				name=rs.getString("yukleyen");}
			return name;
			}
			
			catch(Exception e)
			{return "HATA";}
        }
        else{return "HATA";}}	
}
