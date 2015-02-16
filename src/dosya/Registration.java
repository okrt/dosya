package dosya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Registration {
	public static String addUser(String ad, String soyad, String kadi, String sifre, String sifretekrar, String eposta){
		String originalPassword = sifre;
		String generatedSecuredPasswordHash = BCrypt.hashpw(
				originalPassword, BCrypt.gensalt(12));
		if(!(FormControls.notempty(kadi)&&FormControls.notempty(eposta)&&FormControls.notempty(sifre)&&FormControls.notempty(ad)&&FormControls.notempty(soyad)))
		{
			return "Tüm alanlar zorunludur, lütfen tüm alanları doldurunuz.";
		}
		if(!FormControls.notempty(eposta)||!FormControls.isValidEmailAddress(eposta))
		{
			return "E-posta adresiniz hatalı!";
		}
		if(!sifre.equals(sifretekrar))
		{
			return "Şifreler Eşleşmiyor!";
		}
		if(sifre.length()<6){
			return "Şifreniz en az 6 karakter uzunluğunda olmalıdır.";
		}
		
		//TODO: Form kontrolü işi tamam da daha önce bu kullanıcı adı ya da şifreyle kayıt olunmuş mu ona bakmak gerekiyor.
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO kullanicilar (ad,soyad,kadi,sifre,eposta) VALUES "
				+ "(?,?,?,?,?)";
		Connection con = DataBase.getConnection();
		if (con != null) {
			try{
			ps = con.prepareStatement(sql);
			ps.setString(1, ad);
			ps.setString(2, soyad);
			ps.setString(3, kadi);
			ps.setString(4, generatedSecuredPasswordHash);
			ps.setString(5, eposta);
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
		
	public static String changeInfo(String ad, String soyad, String kadi, String yenisifre, String yenisifretekrar, String eposta, String eskisifre, String kullanici_id){
		
		if(!(FormControls.notempty(ad)&&FormControls.notempty(soyad)&&FormControls.notempty(kadi)&&FormControls.notempty(yenisifre)&&FormControls.notempty(yenisifretekrar)&&FormControls.notempty(eposta)&&FormControls.notempty(eskisifre)))
		{
			return "Tüm alanlar zorunludur, lütfen tüm alanları doldurunuz.";
		}
		if(!FormControls.notempty(eposta)||!FormControls.isValidEmailAddress(eposta))
		{
			return "E-posta adresiniz hatalı!";
		}
		if(!yenisifre.equals(yenisifretekrar))
		{
			return "Şifreler Eşleşmiyor!";
		}
		if(yenisifre.length()<6){
			return "Şifreniz en az 6 karakter uzunluğunda olmalıdır.";
		}
		try{
		Connection con = DataBase.getConnection();
		String originalPassword = eskisifre;
		String generatedSecuredPasswordHash = BCrypt.hashpw(
				originalPassword, BCrypt.gensalt(12));
			if(con!=null){
				String query="select sifre from kullanicilar where kullanici_id='"+kullanici_id+"'";
				Statement st1;
				try {
					st1 = con.createStatement();
					ResultSet rs=st1.executeQuery(query); 
					if(rs.getString("sifre")!=generatedSecuredPasswordHash){
					return "Bilgilerinizde değişiklik yapabilmek için şifrenizi doğru girmeniz gerekmektedir.";
					}
					String neworiginalPassword = yenisifre;
					String newgeneratedSecuredPasswordHash = BCrypt.hashpw(
							neworiginalPassword, BCrypt.gensalt(12));
					PreparedStatement ps = null;
					String sql = "INSERT INTO kullanicilar (ad,soyad,kadi,sifre,eposta) VALUES "
							+ "(?,?,?,?,?)";
						try{
							ps = con.prepareStatement(sql);
							ps.setString(1, ad);
							ps.setString(2, soyad);
							ps.setString(3, kadi);
							ps.setString(4, newgeneratedSecuredPasswordHash);
							ps.setString(5, eposta);
							boolean t = ps.execute();
								if (t) {
								return "Sorgu işleme hatası";
								} 
								else {
								return "OK";
								}
						}
						catch(Exception e){
							return "Veritabanı hatası oluştu!";
						}
					}
				 
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ERROR";
				}  
			}
			else{
			return "HATA";
			}  
		}
		catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "ERROR";
				}  
		
	}
}
	

