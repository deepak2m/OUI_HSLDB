package com.dm.effortestimate;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateConnection {

	public static StringBuilder drvclassname = new StringBuilder("org.hsqldb.jdbcDriver") ;
	public static StringBuilder dburl = new StringBuilder("C:\\Users\\deepak\\MyProjects\\DB\\Test_odb\\Test1") ;
	public Connection conn ;
	

	public static StringBuilder getDrvclassname() {
		return drvclassname;
	}

	public static void setDrvclassname(StringBuilder drvclassname) {
		CreateConnection.drvclassname = drvclassname;
	}

	public static StringBuilder getDburl() {
		return dburl;
	}

	public static void setDburl(StringBuilder dburl) {
		CreateConnection.dburl = dburl;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public int CreateConn() 
	{
		try 
		{
			Class.forName(getDrvclassname().toString()) ;
			
			setConn(DriverManager.getConnection("jdbc:hsqldb:file:"+getDburl()+";shutdown=true;ifexists=true","SA",null)) ;
			return 1 ;
		}
		catch (Exception e)		
		{
			e.printStackTrace() ;
			return 2;
		}
	}
	
	public int FetchStmnt(StringBuilder query,ArrayList al)
	{
		ResultSet rs = null ;
		PreparedStatement pstmnt = null ;
		try 
		{
			pstmnt = this.getConn().prepareStatement(query.toString()) ;
		
			rs = pstmnt.executeQuery() ;
			
			int iter = 0 ;
			
			while (rs.next())
			{
				Array tmp = rs.getArray(iter) ;
				al.add(tmp) ;
				iter++ ;
			}
			return 1 ;
		}
		catch (Exception e)
		{
			e.printStackTrace() ;
			try
			{
				rs.close() ;
				pstmnt.close() ;
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace() ;
				return 3 ;
			}
			return 2 ;
		}
		finally
		{
			try
			{
				rs.close() ;
				pstmnt.close() ;
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace() ;
				return 4 ;
			}
		}
	}
	
	public int CreateInsertTable(StringBuilder createquery)
	{
		PreparedStatement pstmnt = null ;
		try
		{
			pstmnt = this.getConn().prepareStatement(createquery.toString()) ;
			pstmnt.execute() ;
		}
		catch (Exception e)
		{
			e.printStackTrace() ;
			try
			{
				pstmnt.close() ;
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace() ;
				return 3 ;
			}
			return 2 ;
		}
		finally
		{
			try
			{
				pstmnt.close() ;
				return 1 ;
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace() ;
				return 4 ;
			}
		}
	}
}
