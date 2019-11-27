package com.crudstmnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crudstmnt.model.Products;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset='utf-8'");
		PrintWriter output = response.getWriter();
		
		//1. Declaramos variables
		Products myProduct = new Products();
		myProduct.setIdProduct(Integer.parseInt(request.getParameter("txtIdProduct")));
		myProduct.setNameProduct(request.getParameter("txtNameProduct"));
		myProduct.setPriceProduct(Double.parseDouble(request.getParameter("txtPriceProduct")));
		
		String urlServer="jdbc:mysql://localhost:3306/tiendita?useSSL=false&serverTimezone=UTC";
		String username="root";
		String pass="root";
		//String sentenciaSQLRead="SELECT * FROM Productos WHERE idProducto="+myProduct.getIdProduct();
		String sentenciaSQLUpdate="UPDATE Productos SET nombreProducto='"+myProduct.getNameProduct()+"', precioProducto="+myProduct.getPriceProduct()+" WHERE idProducto="+myProduct.getIdProduct();
		int rowsAffected=0;
		
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
			//rs = stmnt.executeQuery(sentenciaSQLRead);
			
			//7. Procesamos los datos
			//if(rs.next())
			//{
				rowsAffected=stmnt.executeUpdate(sentenciaSQLUpdate);
				if(rowsAffected>0)
				{
					output.append("Registro Modificado con éxito!!!");
				}
				else
				{
					output.append("Registro NO pudo ser Modificado!!!");
				}
			/*}
			else
			{
				output.append("Registro NO Encontrado!!!");
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
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
