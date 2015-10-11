package com.dm.effortestimate;

public class Trigger {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		CreateConnection conn = new CreateConnection() ;
		StringBuilder crttable = new StringBuilder() ;
		
		crttable = new StringBuilder ("CREATE TABLE OUI_USERS (USERID INT PRIMARY KEY,USERNAME VARCHAR(50), LOGINID VARCHAR(25), H_LSTUPDDT TIMESTAMP, H_OID INT, H_WSID INT,OUI_USERS_VER INT)")  ;
		StringBuilder insrtqry = new StringBuilder ("INSERT INTO OUI_USERS (USRERID,USERNAME,LOGINID) VALUES (1,'KIRTI SHARMA','kittu')") ;
		try
		{
			int retconn = conn.CreateConn() ;
			System.out.println("Connection successful::"+retconn);
			int ret = conn.CreateInsertTable(crttable) ;
			System.out.println(ret);
			int ret1 = conn.CreateInsertTable(insrtqry) ;
			System.out.println("First Insert:"+ret1);
			insrtqry = new StringBuilder ("INSERT INTO OUI_USERS (USRERID,USERNAME,LOGINID) VALUES (2,'Deepak Mishra','deepak2m')") ;	
			ret = conn.CreateInsertTable(insrtqry) ;
		}
		catch (Exception e)
		{
			e.printStackTrace() ; 
		}
	
		
	}

}
