package com.crudstmnt.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Servlet que obtiene el listado de los productos
 * @author El Macho
 * @version 1.0
 * 
 * <p> Este es un listado de todos los productos de mi base de
 * datos</p>
 */
@WebServlet("/ReadGeneralServlet")
public class ReadGeneralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @version 1.0
	 * 
	 * Método doPost que me genera el listado de los productos
	 * en tipo MIME text/HTML
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Declaramos variables
		String urlServer="jdbc:mysql://localhost:3306/tiendita?useSSL=false&serverTimezone=UTC";
		String username="root";
		String pass="root";
		String sentenciaSQL="select * from productos";
		
		//2. Declaramos objetos
		Connection conn=null;
		Statement stmnt=null;
		ResultSet rs=null;
		
		try
		{
			//3. Instanciamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			
			//4. Abrimos conexión
			conn = DriverManager.getConnection(urlServer,username, pass);
			
			//5. Preparamos el statement
			stmnt = conn.createStatement();
			
			//6. Ejecutamos la sentenciaSQL
			rs = stmnt.executeQuery(sentenciaSQL);
			
			//7. Procesamos la salida
			while(rs.next())
			{
				response.getWriter().append("<p>");
				response.getWriter().append("Id Producto:"+rs.getInt(1));
				response.getWriter().append("<br/>");
				response.getWriter().append("Nombre Producto:"+rs.getString("nombreProducto"));
				response.getWriter().append("<br/>");
				response.getWriter().append("Precio Producto:"+rs.getDouble("precioProducto"));
				response.getWriter().append("</p>");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//8. Cerramos conexión
			try {
				rs.close();
				stmnt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

}
