/**
 * 
 */
package org.xbasej.examples;

import java.io.File;

import org.xbasej.DBF;
import org.xbasej.XBASEXMLParser;

/**
 * @author joseph mcverry
 *
 */
public class XMLExample1 {

    public static void main(String args[]){

           

    
            try{
                    //Open dbf file
                    DBF classDB=new DBF("class.dbf");
                    classDB.getXML("class.xml");
                    File f = new File("class.dbf");
                    f.delete();
                    XBASEXMLParser xxp = new XBASEXMLParser();
                    xxp.parse("class.xml");
                    
                    
            }catch(Exception e){
                    e.printStackTrace();
            }
    }
}
                    
