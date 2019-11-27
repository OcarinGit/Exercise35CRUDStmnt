package com.crudstmnt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import com.crudstmnt.model.Products;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 * Servlet implementation class ReadIndividualServlet
 */
@WebServlet("/ReadIndividualServlet")
public class ReadIndividualServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html charset='utf-8'");
		PrintWriter output = response.getWriter();
		
		//1. Declaramos variables
		Products myProduct = new Products();
		myProduct.setIdProduct(Integer.parseInt(request.getParameter("txtIdProduct")));
		
		String urlServer="jdbc:mysql://localhost:3306/tiendita?useSSL=false&serverTimezone=UTC";
		String username="root";
		String pass="root";
		String sentenciaSQL="SELECT * FROM Productos WHERE idProducto="+myProduct.getIdProduct();
				
		//2. Declaramos objetos
		Connection conn = null;
		Statement stmnt = null;
		ResultSet rs = null;
		
		try
		{
			//3. Instanciamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			//4. Abrimos la conexión
			conn = DriverManager.getConnection(urlServer, username, pass);
			//5. Preparamos el statement
			stmnt = conn.createStatement();
			
			//6. Ejecutamos la sentencia sql
			rs = stmnt.executeQuery(sentenciaSQL);
			
			//7. Procesamos los datos
			rs.next();
			output.append("<p>");
			output.append("Id Producto:"+rs.getInt(1));
			output.append("<br/>");
			output.append("Nombre Producto:"+rs.getString("nombreProducto"));
			output.append("<br/>");
			output.append("Precio Producto:"+rs.getDouble("precioProducto"));
			output.append("</p>");
			output.append("<a href='readIndividual.jsp'>Regresar</a>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				stmnt.close();
				conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//8. Cerramos la conexión
		output.close();
	}

}
