package com.nihar;

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
@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
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
        	Socket sock = new Socket("10.99.0.15",5252);
            String preference1=request.getParameter("preference1");
            String preference2=request.getParameter("preference2");
            String preference3=request.getParameter("preference3");
            String preference4=request.getParameter("preference4");
            String preference5=request.getParameter("preference5");
            
            String rating1=request.getParameter("ratingreview1");
            String rating2=request.getParameter("ratingreview2");
            String rating3=request.getParameter("ratingreview3");

            String sentiment=request.getParameter("sentiment");
            //String detail=request.getParameter("detail");
            String detail=request.getParameter("detail");

            
            //String preference6=request.getParameter("preference6");
            //out.println("<h2> Welcome "+user+"</h2>");
            OutputStream ostream = sock.getOutputStream(); 
            PrintWriter ServerOutput = new PrintWriter(ostream, true); 
            
                //sendMessage = ClientInput.readLine();  // keyboard reading
        	/*ServerOutput.println("Rating of review1 is " +rating1+ 
        			"Rating of review2 is "+rating2+
        			"Rating of review2 is "+rating3+
        			"Sentiment rating of user is" +sentiment); */
        	ServerOutput.println("14"+"::"+"12"+"::"+rating1+"\n"+
        			"10"+"::"+"107"+"::"+rating2+"\n"+
        			"18"+"::"+"04"+"::"+rating3+"\n"
        			//"Sentiment rating is "+sentiment
        			);
        	//ServerOutput.println(preference2);// sending to server
                ServerOutput.flush();  //flush the data
      
                InputStream istream = sock.getInputStream();
                BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
                
                String receiveMessage;
               // out.println("entered");
                if(( receiveMessage = receiveRead.readLine()) != null ) //receive from server
                {	
                	//out.println("entered1");
                    //out.println("Message from server is "+receiveMessage); // displaying at DOS prompt
                	out.println(""
                	 		+ "<!DOCTYPE html>"
                	 		+ "<html><head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'><!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags --><title>Bootstrap 101 Template</title><link rel='stylesheet' type='text/css' href='range.css'><!-- Bootstrap --><link href='bootstrap/css/bootstrap.min.css' rel='stylesheet'><!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries --><!-- WARNING: Respond.js doesn't work if you view the page via file:// --><!--[if lt IE 9]>  <script src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js'></script><script src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js'></script><![endif]--></head>"
                	 		+ "<body><!-- jQuery (necessary for Bootstrap's JavaScript plugins) --><script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script><!-- Include all compiled plugins (below), or include individual files as needed --><script src='bootstrap/js/bootstrap.min.js'></script><script src='js/homepage.js'></script><div class='page-header'><h1>Recommended Review for you are:</h1><p>"+receiveMessage+"</p></div></body></html>"); 
                    sock.close();
                    ostream.close();
                }   
                
                
                
//out.println("<!DOCTYPE html>");
//out.println("<html><head><meta charset='ISO-8859-1'><title>Insert title here</title></head><body><form method='post' action='HelloWorld1'>Enter the preferences <br><br>Preference 1 <input type='text' name='preference1' ><br>Preference 2 <input type='text' name='preference2' ><br>Preference 3 <input type='text' name='preference3' ><br>Preference 4 <input type='text' name='preference4' ><br>Preference 5 <input type='text' name='preference5' ><br><br><input type='submit' value='submit'></form></body></html>");
 //out.println(""
 	//	+ "<!DOCTYPE html>"
 		//+ "<html><head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'><!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags --><title>Bootstrap 101 Template</title><link rel='stylesheet' type='text/css' href='range.css'><!-- Bootstrap --><link href='bootstrap/css/bootstrap.min.css' rel='stylesheet'><!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries --><!-- WARNING: Respond.js doesn't work if you view the page via file:// --><!--[if lt IE 9]>  <script src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js'></script><script src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js'></script><![endif]--></head>"
 		//+ "<body><!-- jQuery (necessary for Bootstrap's JavaScript plugins) --><script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script><!-- Include all compiled plugins (below), or include individual files as needed --><script src='bootstrap/js/bootstrap.min.js'></script><script src='js/homepage.js'></script><div class='page-header'><h1>Recommended Review for you are:</h1><p> 1: R1M22N83B6BT73well written and easy to read. must own for learning your new (or old) camera. filler word filler filler filler2: R2D3XVTIE8QCK1.This book helped so much in learning how to use my new camera. Step by Step instructions.  The Book is a Must have item. You won't be disappointed.3: RFT5M1DP53C7AI bought and read this book after buying my first DSLR.  It was essential in understanding how my camera worked and to understand how to apply techniques from Brian Peterson's field guide (my other favorite photography book).  I still find myself going back and referencing this book if I get a new add on for the camera or don't quite understand how to achieve a certain technique.  It's great and I always recommend it to people getting their first DSLR camera.4: R1JEMQ0QJR4YUOIt's a great guide to figuring out the ins and outs of my camera. I've already improved how I take pictures and I use the book to find out the 'how' behind making great photos with my new camera.5: RV93QCGB4CWL6David gives much better instruction about the Canon T3 than anything I've gotten from Canon.  It is laid out wonderfully and explains many concepts that were difficult to understand without his help.6: R3KM5O62ALGIU7My camera sat unused, in the box, for over a year because I was intimidated by all of the functions. After reading the first few chapters, I was navigating the camera with no problem! I would recommend this to anyone new to DSLR photography.7: R1H8TA2T3JEB0GThis is the latest of Busch's best-of-breed guidebooks, offering a comprehensive manual for the Canon EOS Rebel T3 entry-level digital SLR. The T3 is a basic 12-megapixel model that lacks some features and the high price tag of more luxe beginner cameras like the T3i, but its affordability will attract beginners who want a camera with room to grow into.  Busch's book has the information you need to learn and grow, supplying easy explanations of using the T3's basic features accompanied by simple introductions to the kind of more advanced capabilities that will help even the rawest neophyte take the best pictures of their lives. Although very accessible, this book goes much deeper than the run-of-the-mill camera guides to offer advice on how to use every control, select every menu option.. The author's friendly style leads you through the camera's features and tells you not only what each capability does, but gives examples of when you'll get the most benefit from using it. Busch explains all the new features for the D5100 in a way that's easy to understand. His novel approach is to condense all the essential information about each and every control on the camera into a multi-page that explains their functions without the need to leap around from page to page, as is the case with the Canon manual. He devotes several full chapters to outlining every menu option, and actually provides much more detail than you'll find in most of the other expanded guides for the T3.Although this book is comprehensive, it's easy reading, and if you want a condensed version to carry around with you, Busch offers that too. His David Busch's Compact Field Guide for the Canon EOS Rebel T3/1100D is a worthy replacement for those laminated cheat sheets that fold out into six index card sized tabs with one-sentence summaries of most of the menu settings, too. While the portable version duplicates some of the information found in this book, the two make a great pair for home and field. You'll want to buy them both to have the information you need on the go and for study at home.8: R2NEKF7OLY4IRADavid Busch's guides are written in plain, easy to understand language.  This guide supplements the guide on CD and short paper book guide that came with the camera.  It has useful tips, and clear diagrams, and helps you navigate through all the menus.9: RL2IWOZ63CWLJHighly detail led guide not only on the camera but how to shoot digital photography with it. I read this after I had hee camera for a year and I still learned a lot, definitely worth a read.10: R2ERA0AV8OF4L8The book is easy to understand and put together well for an amateur getting into Photography.  I like how the basic information is given first and the details are in later chapters.  It is comprehensive and gives instruction on how to reprogram your camera to meet your personal needs and desires.  I will keep this book as a reference for my camera</p></div></body></html>");  
 
 
 
 
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


