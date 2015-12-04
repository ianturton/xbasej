package org.xbasej;

import java.io.File;
import java.io.PrintWriter;

import org.geotools.test.TestData;
import org.xbasej.DBF;
import org.xbasej.XBASEXMLParser;

import junit.framework.TestCase;


public class TestXMLParser  extends TestCase{

	
	public void testXMLParserRoundTrip() throws Exception{
	    File file = TestData.file(this, "testdbt.dbf");
            String fileName = file.getAbsolutePath();
		DBF sdf = new DBF(fileName);
		File xmlfile = TestData.file(this, "testdbtdbf.xml");
		PrintWriter pw = new PrintWriter(xmlfile);
		sdf.getXML(pw);
		XBASEXMLParser xxp = new XBASEXMLParser();
		File outFile = TestData.temp(this, "testdbtxmled.dbf");
		xxp.parse(xmlfile.getAbsolutePath(), outFile.getAbsolutePath());
		
		DBF xscf = new DBF(outFile.getPath());
		
		assertEquals(sdf.getFieldCount(), xscf.getFieldCount());
		      for (int f = 1; f <= sdf.getFieldCount(); f++) {
			  assertEquals(sdf.getField(f).getName(), xscf.getField(f).getName());
			  assertEquals(sdf.getField(f).getLength(), xscf.getField(f).getLength());
			  assertEquals(sdf.getField(f).getType(), xscf.getField(f).getType());
		      }
		assertEquals(sdf.getRecordCount(), xscf.getRecordCount());
		for (int i = 0; i < sdf.getIndexCount(); i++) {
		      sdf.read();
		      xscf.read();
		      for (int f = 1; f <= sdf.getFieldCount(); f++) {
			  assertEquals(sdf.getField(f).get(), xscf.getField(f).get());
		      }
		}
		
		
		
	}
}
