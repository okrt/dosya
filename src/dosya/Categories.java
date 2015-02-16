package dosya;
import java.sql.*;

import dosya.DataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Categories {
	public static boolean deleteCategory(String catid){
		Connection con = DataBase.getConnection();
		String sql="delete from kategoriler where kategori_id=?";  
        PreparedStatement ps=null;
		try {
				ps = con.prepareStatement(sql);
				ps.setString(1, catid);
			    ps.execute();
			    return true;
				
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}  
		
	}
	public static boolean addCategory(String name, String catid){
		System.out.println(name);
		System.out.println(catid);
		Connection con = DataBase.getConnection();
		String sql="INSERT INTO kategoriler (kategori_ad,ustkategori) VALUES "
				+ "(?,?)";
        PreparedStatement ps=null;
		try {
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2, Integer.parseInt(catid));
			    ps.execute();
			    return true;
				
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}  
		
	}
	public static boolean renameCategory(String name, String catid){
		System.out.println(name);
		System.out.println(catid);
		Connection con = DataBase.getConnection();
		String sql="UPDATE `kategoriler` SET `kategori_ad` = ? WHERE `kategori_id` = ?";
        PreparedStatement ps=null;
		try {
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2, Integer.parseInt(catid));
				
			    ps.execute();
			    return true;
				
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}  
		
	}
	public static String GetSubCategories(int parentID, boolean showEdit){
		String d="";
		int anaid=0;
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım.
        	String query="select * from kategoriler where ustkategori='"+Integer.toString(parentID)+"' order by kategori_ad";  
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
					//Herhangi bir alt kategorimiz yok. Bu yüzden bu elemanı kapadım.
					d=d+"</li>";
				}
				else{
				     while (rs.next()) {  
				    	 if(rs.isFirst()){
				    		 //Bu ilk altkategori olduğundan yeni bir liste açıyorum.
				    		 d=d+"<ul>";
				    		 }
				    	 	//Bunları tüm liste elemanları için yapacağım.
			            	anaid=rs.getInt("kategori_id");
			            	
			            	
			            	d=d+"<li><a href=\"categories.jsp?categoryID="+rs.getString("kategori_id")+"\">"+rs.getString("kategori_ad")+"</a>";
			            	if(showEdit==true)d=d+"&nbsp;|&nbsp;<span class=\"orange\"><a href=\"s_categories.jsp?action=delete&id="+rs.getInt("kategori_id")+"\">Sil</a> &nbsp;<a href=\"javascript:prompter("+rs.getInt("kategori_id")+")\">Yeniden Adlandır</a></span>";
			            	d=d+GetSubCategories(anaid, showEdit);
			            	
			            	if(rs.isLast()){d=d+"</ul>";
			            	}
			            }
					
				}
	       
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            
        }
		return d;
	}
	public static String GetCategories(boolean showEdit) {
		int anaid=0;
		String d="";
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım.
        	String query="select * from kategoriler where ustkategori='0' order by kategori_ad";  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query);  
	            while (rs.next()) {  
	            	anaid=rs.getInt("kategori_id");
	            	//Şimdiki ana kat. yazdır
	            	d=d+"<li><a href=\"categories.jsp?categoryID="+rs.getString("kategori_id")+"\">"+rs.getString("kategori_ad")+"</a>";
	            	if(showEdit==true)d=d+"&nbsp;|&nbsp;<span class=\"orange\"><a href=\"s_categories.jsp?action=delete&id="+rs.getInt("kategori_id")+"\">Sil</a> &nbsp;<a href=\"javascript:prompter("+rs.getInt("kategori_id")+")\">Yeniden Adlandır</a></span>";
	            	d=d+GetSubCategories(anaid, showEdit);
	            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            
        }
            return d;
	}
	
	public static String GetCategoriesUsingForUpload() {
		// Kategori fonksiyonu dosya yükleme ekranı için tekrar düzenlenmiştir.
		int anaid=0;
		String d="";
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım.
        	String query="select * from kategoriler where ustkategori='0' order by kategori_ad";  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query);  
	            while (rs.next()) {  
	            	anaid=rs.getInt("kategori_id");
	            	//Şimdiki ana kat. yazdır
	            	d=d+"<option value=\""+rs.getString("kategori_id")+"\">"+rs.getString("kategori_ad")+"</option>"+GetSubCategoriesUsingForUpload(anaid);  
	            	//d=d+"<optgroup label=\""+rs.getString("kategori_ad")+"\">"+GetSubCategoriesUsingForUpload(anaid);
	            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}  
            
        }
            return d;
	}
	
	public static String GetSubCategoriesUsingForUpload(int parentID){
		String d="";
		int anaid=0;
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım. Gereksiz yazı
        	String query="select * from kategoriler where ustkategori='"+Integer.toString(parentID)+"' order by kategori_ad";  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				int size=0;
				while (rs.next()) {
				    size++;
				}
				rs.beforeFirst();
				if(size==0 && parentID==0){
					//Herhangi bir alt kategorimiz yok. Bu yüzden bu elemanı kapadım.
					d=d+"</optgroup>";
					//<option value=\""+rs.getString("kategori_ad")+"\">"+rs.getString("kategori_ad")+"</option>
				}
				if(size>0){
				     while (rs.next()) { 
			            	anaid=rs.getInt("kategori_id");
			            	d=d+"<option value=\""+rs.getString("kategori_id")+"\">"+rs.getString("kategori_ad")+"</option>"+GetSubCategoriesUsingForUpload(anaid);
			            	if(rs.isLast()){d=d+"</optgroup>";
			            	}
			            }
					
				}
	       
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            
        }
		return d;
	}
	public static String GetNameOfCategory(int catID){
		Connection con = DataBase.getConnection();
        if(con!=null){
        //Veritabanı bağlantısını sağladım, şimdi ana kategorileri alacağım.
        	String query="select * from kategoriler where kategori_id='"+Integer.toString(catID)+"'";  
            Statement st1;
			try {
				st1 = con.createStatement();
				ResultSet rs=st1.executeQuery(query); 
				String name="";
				while(rs.next()){
				name=rs.getString("kategori_ad");}
			return name;
			}
			
			catch(Exception e)
			{return "HATA";}
        }
        else{return "HATA";}}
}
