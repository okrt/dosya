package dosya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dosya.Categories;
import dosya.Users;
public class Files {
	static String itemsPerPage="5";
	public static String GetFileList(String limit){
		String d="";
		int item=Integer.parseInt(limit)*Integer.parseInt(itemsPerPage);
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım. Gereksiz yazı
        	String query="select * from dosyalar ORDER BY dosya_id DESC LIMIT "+item+","+itemsPerPage;  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				
				while (rs.next()) {
					
					d=d+"<tr><td>"+rs.getString("dosya_ad")+"</td><td>"+Categories.GetNameOfCategory(rs.getInt("kategori_id"))+"</td><td>"+rs.getString("tur")+"</td><td>"+rs.getInt("boyut")+"</td><td>"+Users.GetUserName(rs.getInt("yukleyen"))+"</td><td><a href=\"s_files.jsp?action=delete&id="+rs.getInt("dosya_id")+"\">Sil</a> </td></tr>";
						
				}
					
				}
				
	       
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return d;
	}
	public static String SearchFileList(String keyword){
		String d="";
		
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım. Gereksiz yazı
        	String query="SELECT * FROM `dosyalar` WHERE `dosya_ad` LIKE '"+keyword+"%' ORDER BY dosya_ad LIMIT 0,30";
        	System.out.println(query);
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				
				while (rs.next()) {
					d=d+"<tr><td>"+rs.getString("dosya_ad")+"</td><td>"+Categories.GetNameOfCategory(rs.getInt("kategori_id"))+"</td><td>"+rs.getString("tur")+"</td><td>"+rs.getInt("boyut")+"</td><td>"+Users.GetUserName(rs.getInt("yukleyen"))+"</td><td><a href=\"s_files.jsp?action=delete&id="+rs.getInt("dosya_id")+"\">Sil</a> </td></tr>";

				}
					
				}
				
	       
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return d;
	}
	public static boolean deleteFile(String fileid){
		Connection con = DataBase.getConnection();
		String sql="delete from dosyalar where dosya_id=?";  
        PreparedStatement ps=null;
		try {
				ps = con.prepareStatement(sql);
				ps.setString(1, fileid);
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
		String sql="select * from dosyalar";
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
			d=d+"<a href=\"s_files.jsp?page="+String.valueOf(x)+"\">"+String.valueOf(x+1)+"</a>&nbsp;|&nbsp;";
			
		}
		
		}
		catch(Exception ex)
		{
		
		}
		return d;
	}
}
