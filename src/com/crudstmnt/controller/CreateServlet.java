package com.crudstmnt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author Oscar Alberto Santana Alvarez
 * 
 * @since 1.0
 * 
 * <p>
 * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html">Documentación del javadoc</a>
 * </p>
 * <p>
 * @see <a href="https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html">Esta página tiene la información que necesité</a>
 * </p>
 *
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Método doPost de mi servlet CreateServlet
	 * 
	 * @param request Este parámetro me sirve para recibir los datos del cliente
	 * @param response Este parámetro me sirve para enviar los datos al cliente
	 * 
	 * @return el objeto response con la respuesta de la base de datos
	 * 
	 * @see Servlets
	 * @since 1.0
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("application/json charset='utf-8'");
		response.setContentType("text/html charset='utf-8'");
		PrintWriter output = response.getWriter();
		
		String nameProduct = request.getParameter("txtNameProduct");
		double priceProduct = Double.parseDouble(request.getParameter("txtPriceProduct"));
		
		//1. Declaramos las variables
		String urlServidor = "jdbc:mysql://localhost:3306/tiendita?useSSL=false&serverTimezone=UTC";
		String nombreUsuario = "root";
		String password = "root";
		int rowsAffected=0;
		
		//2. Declaramos los objetos
		/*Connection conn=null;
		Statement stmnt=null;
		ResultSet rs=null;
		*/
		
		try {
			//3. Instanciamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			//4. Abrimos la conexión
			Connection conn = DriverManager.getConnection(urlServidor, nombreUsuario, password);
			Statement stmnt = conn.createStatement();
			
			//5. Ejecutamos la sentencia sql
			rowsAffected = stmnt.executeUpdate("INSERT INTO productos (nombreProducto, precioProducto) VALUES ('"+nameProduct+"', "+priceProduct+")");
			//6. Procesamos los datos
			if(rowsAffected!=0)
			{
				output.append("Registro Añadido con éxito!!");
			}
			else
			{
				output.append("Registro NO fue añadido!!");
			}
			//7. Cerramos los objetos de conexión
			stmnt.close();
			conn.close();
		} catch (SecurityException | ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		output.close();
	}
}
