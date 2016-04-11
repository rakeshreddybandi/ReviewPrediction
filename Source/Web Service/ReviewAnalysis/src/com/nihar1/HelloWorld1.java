package com.nihar1;

import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/HelloWorld1")
public class HelloWorld1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
        PrintWriter out  = response.getWriter();
       // out.println("<h1>Hello World!</h1>");
        try {
            
            String user=request.getParameter("user");
            out.println("<h2> Welcome "+user+"</h2>");
        } finally {            
            out.close();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//
		
		response.setContentType("text/html");
        PrintWriter out  = response.getWriter();
       // out.println("<h1>Hello World!</h1>");
        try {
        	/**/
        	Socket sock = new Socket("10.99.0.15",5353);
            String preference1=request.getParameter("preference1");
            String preference2=request.getParameter("preference2");
            String preference3=request.getParameter("preference3");
            String preference4=request.getParameter("preference4");
            String preference5=request.getParameter("preference5");
            //String preference6=request.getParameter("preference6");
            //out.println("<h2> Welcome "+user+"</h2>");
            OutputStream ostream = sock.getOutputStream(); 
            PrintWriter ServerOutput = new PrintWriter(ostream, true); 
            
                //sendMessage = ClientInput.readLine();  // keyboard reading
        	ServerOutput.println(preference1+" "+preference2+" "+preference3+" "+preference4+" "+preference5); 
        	//ServerOutput.println(preference2);// sending to server
                ServerOutput.flush();  //flush the data
      
                InputStream istream = sock.getInputStream();
                BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
                
                String receiveMessage;
               // out.println("entered");
                if(( receiveMessage = receiveRead.readLine()) != null ) //receive from server
                {	
                	//out.println("entered1");
                    out.println("Message from server is "+receiveMessage);// displaying at DOS prompt
                    sock.close();
                    ostream.close();
                    
                }  
                
                
             
            
        } 
        
        catch(Exception e)
        {
        	System.out.println("Exception is" +e);
        }
        finally {   
        	//sock.close();
            out.close();
        }
	}
	}


