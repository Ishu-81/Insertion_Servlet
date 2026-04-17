package ebook581;



import java.sql.*;

import java.util.*;



public class EmployeeDAO {



   

    public static Connection getDBConnection() {

        Connection con = null;



        try {

            Class.forName("com.mysql.cj.jdbc.Driver");



            con = DriverManager.getConnection(

                "jdbc:mysql://localhost:3306/24wh1a0581",

                "root",

                "1234"

            );



        } catch (Exception e) {

            e.printStackTrace();

        }



        return con;

    }



   

    public boolean addEmployee(Employee emp) {

        boolean status = false;



        try {

            Connection con = getDBConnection();



            String query = "INSERT INTO Employee (empid, name, salary, department) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);



            ps.setInt(1, emp.getEmpid());

            ps.setString(2, emp.getName());

            ps.setInt(3, emp.getSalary());

            ps.setString(4, emp.getDepartment());



            int rows = ps.executeUpdate();



            if (rows > 0) {

                System.out.println("Inserted successfully");

                status = true;

            }



            con.close();



        } catch (Exception e) {

            e.printStackTrace();

        }



        return status;

    }



    public List<Employee> getEmployees() {

        List<Employee> list = new ArrayList<>();



        try {

            Connection con = getDBConnection();



            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM Employee");



            System.out.println("Fetching employees...");



            while (rs.next()) {

                System.out.println("Data found");



                list.add(new Employee(

                    rs.getInt("empid"),

                    rs.getString("name"),

                    rs.getInt("salary"),

                    rs.getString("department")

                ));

            }



            con.close();



        } catch (Exception e) {

            e.printStackTrace();

        }



        return list;

    }

}

