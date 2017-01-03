package com.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.kdtree.*;

/**
 * @author yumin
 *
 */
@Controller
@EnableAutoConfiguration
public class SearchPoi {

	/**
	 * @param model
	 * @param long1
	 * @param long2
	 * @param lat1
	 * @param lat2
	 * @return
	 */
	@RequestMapping("/search")
	String search(Model model, @RequestParam(value = "long1", required = false, defaultValue = "127") String long1,
			@RequestParam(value = "long2", required = false, defaultValue = "128") String long2,
			@RequestParam(value = "lat1", required = false, defaultValue = "35") String lat1,
			@RequestParam(value = "lat2", required = false, defaultValue = "36") String lat2) {
		
		model.addAttribute("long1", long1);
		model.addAttribute("long2", long2);
		model.addAttribute("lat1", lat1);
		model.addAttribute("lat2", lat2);
		List<Map> list = null;
		try {
			list = getData();
			System.out.println("data size = " + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Tree tree = new Tree();
		double x[] = new double[2];
		
		// tree insert
		String name, longitude, latitude;
		for (Map m : list) {
			name = (String) m.get("name");
			longitude = (String) m.get("longitude");
			latitude = (String) m.get("latitude");

			if (!longitude.equals("") && !latitude.equals("") && longitude != null && latitude != null) {
				x[0] = Double.parseDouble(longitude);
				x[1] = Double.parseDouble(latitude);
				tree.insert(new Node(name,x));
				//tree.inorder();
			}
		}
		
		// tree range search
		 //List<Map<String, String>> result = tree.rangeSearch(new Rectangle(128.57,128.578,36.55,36.581));
		 List<Map<String, String>> result = tree.rangeSearch(new Rectangle(string2Double(long1),string2Double(long2),string2Double(lat1),string2Double(lat2)));
		System.out.println("size)"+result.size());
		for(Map<String,String> m:result){
			//System.out.println(n.name +","+n.data[0]+","+n.data[1]);
			System.out.println(m.get("name")+", "+m.get("longitude")+m.get("latitude"));
		}
		
		
		model.addAttribute("poiList", result);

		return "search";
	}

	/**
	 * Makes database connection
	 * @return
	 */
	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

	/**
	 * Converts String to Double
	 * @param str
	 * @return
	 */
	public static double string2Double(String str){
		return Double.parseDouble(str);
	}
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SearchPoi.class, args);
	}

	/**
	 * Gets DB connection
	 * @return db connection
	 */
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.h2.Driver");
			dbConnection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	
	/**
	 * Gets all toilet location data
	 * @return
	 * @throws SQLException
	 */
	private static List<Map> getData() throws SQLException  {
		List<Map> list = new ArrayList<Map>();
		Connection connection = getDBConnection();
		Statement stmt = null;
		try {
			connection.setAutoCommit(false);
			stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM TOILET WHERE LATITUDE IS NOT NULL AND LONGITUDE IS NOT NULL;");
            //ResultSet rs = stmt.executeQuery("SELECT * FROM TOILET WHERE LATITUDE IS NOT NULL AND LONGITUDE IS NOT NULL LIMIT 10000;");

			Map<String, String> map = null;
	            
			while (rs.next()) {
				//System.out.println((i++) + ") name : " + rs.getString("name") + " / " + "longitude : "+ rs.getString("longitude") + " / " + "longitude : " + rs.getString("latitude"));
				map = new HashMap<String, String>();
				map.put("name", rs.getString("name"));
				map.put("longitude", rs.getString("longitude"));
				map.put("latitude", rs.getString("latitude"));
				list.add(map);
			}
	            stmt.close();
	            connection.commit();
	        } catch (SQLException e) {
	            System.out.println("Exception Message " + e.getLocalizedMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connection.close();
	        }
	        
	        return list;
	}
}