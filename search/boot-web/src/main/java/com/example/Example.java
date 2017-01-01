package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;



class KDNode{
    KDNode left;
    KDNode right;
    double []data;

    public KDNode(){
        left=null;
        right=null;
    }

    public KDNode(double []x){
        left=null;
        right=null;
        data = new double[2];
        for (int k = 0; k < 2; k++)
            data[k]=x[k];
    }
}
class KDTreeImpl{
    KDNode root;
    int cd=0;
    int DIM=2;

    public KDTreeImpl() {
        root=null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(double []x){
        root = insert(x,root,cd);
    }
    private KDNode insert(double []x,KDNode t,int cd){
        if (t == null)
            t = new KDNode(x);
        else if (x[cd] < t.data[cd])
            t.left = insert(x, t.left, (cd+1)%DIM);
        else
            t.right = insert(x, t.right, (cd+1)%DIM);
        return t;
    }

    public boolean search(int []data){
        return search(data,root,0);
    }

    private boolean search(int []x,KDNode t,int cd){
        boolean found=false;
        if(t==null){
            return false;
        }
        else {
            if(x[cd]==t.data[cd]){
                if(x[0]==t.data[0] && x[1]==t.data[1]) 
                return true;
            }else if(x[cd]<t.data[cd]){
                found = search(x,t.left,(cd+1)%DIM);
            }else if(x[cd]>t.data[cd]){
                found = search(x,t.right,(cd+1)%DIM);
            }
            return found;
        }
    }

    public void inorder(){
        inorder(root);
    }
    private void inorder(KDNode r){
        if (r != null){
            inorder(r.left);
            System.out.print("("+r.data[0]+","+r.data[1] +") ");
            inorder(r.right);
        }
    }
    public void preorder() {
        preorder(root);
    }
    private void preorder(KDNode r){
        if (r != null){
            System.out.print("("+r.data[0]+","+r.data[1] +") ");
            preorder(r.left);             
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder() {
        postorder(root);
    }
    private void postorder(KDNode r) {
        if (r != null){
            postorder(r.left);             
            postorder(r.right);
            System.out.print("("+r.data[0]+","+r.data[1] +") ");
        }
    }
}


/**
 */
@Controller
@EnableAutoConfiguration
public class Example {

	@RequestMapping("/search")
	String search(Model model, @RequestParam(value = "long1", required = false, defaultValue = "World") String long1,
			@RequestParam(value = "long2", required = false, defaultValue = "World") String long2,
			@RequestParam(value = "lat1", required = false, defaultValue = "World") String lat1,
			@RequestParam(value = "lat2", required = false, defaultValue = "World") String lat2) {
		model.addAttribute("long1", long1);
		model.addAttribute("long2", long2);
		model.addAttribute("lat1", lat1);
		model.addAttribute("lat2", lat2);

		try {
			initDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// database connection
		return "search";
	}

	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Example.class, args);
	}

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

	private static void initDB() throws SQLException  {
	        Connection connection = getDBConnection();
	        Statement stmt = null;
	        try {
	            connection.setAutoCommit(false);
	            stmt = connection.createStatement();

	            // create TEST_TABLE for example
	            //stmt.execute("CREATE TABLE TEST_TABLE(idx INT PRIMARY KEY, name VARCHAR(100));");
	            
	            // insert some values into TEST_TABLE
	            //stmt.execute("INSERT INTO TEST_TABLE VALUES(1, 'Martin.Park'), (2, 'OskarDevelopers');");
	             
	            // get result by using SELECT query
	            ResultSet rs = stmt.executeQuery("SELECT * FROM TOILET ;");
	            int i=0;
	            
	            KDTreeImpl kdt = new KDTreeImpl();
	            double x[] = new double[2];
	           
	            
	            
	            while (rs.next()) {
	               // System.out.println( (i++)+ ") name : " + rs.getString("name") + " / " + "longitude : " + rs.getString("longitude") + " / " + "longitude : " + rs.getString("latitude"));
	                String longitude = rs.getString("longitude");
	                String latitude = rs.getString("latitude");
	                if(longitude!="" &&latitude!="" &&longitude!=null  &&latitude!=null){
	                	x[0] = Double.parseDouble(rs.getString("longitude"));
	                	x[1] =  Double.parseDouble(rs.getString("latitude"));
	                	kdt.insert(x);
	                	//kdt.inorder();
		            }
	            }
	            System.out.println("done");
	            stmt.close();
	            connection.commit();
	            
	        } catch (SQLException e) {
	            System.out.println("Exception Message " + e.getLocalizedMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connection.close();
	        }
	}
}