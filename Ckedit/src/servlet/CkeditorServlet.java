package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Msg;

import Common.DbConnection;


/**
 * Servlet implementation class CkeditorServlet
 */
public class CkeditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CkeditorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");  

		System.out.println("CkeditorServlet");
		
		 Connection conn;
		 Statement stmt;
		 DbConnection db = new DbConnection();
		
		
		PrintWriter out=response.getWriter();
		ResultSet rs=null;
		conn = db.getConn();
		String content=request.getParameter("content");    //抢购须知文本内容
		System.out.println("content="+content);
		
		
        HttpSession session=request.getSession(true);
        String paltform_id="1";
        ArrayList<Msg> list=new ArrayList<Msg>();
		try{
			conn = db.getConn();
			stmt=conn.createStatement();
			String str="insert into info(content) values('"+content+"')";
			//System.out.println(str);
			stmt.execute(str);
			
			String sql="select * from info order by id";
			rs=stmt.executeQuery(sql);
			String result=null;
			
	    	while(rs.next()){
	    		result=rs.getString("content");
	        }
	    	session.setAttribute("content", result);
			
			
	    	if(rs!=null){rs.close();}
			   if(stmt!=null){stmt.close();}
			   if(conn!=null){
				   conn.close();   }  
		}catch(Exception e){e.printStackTrace();}
		
		request.getRequestDispatcher("two.jsp").forward(request,response);
		
	}

}
