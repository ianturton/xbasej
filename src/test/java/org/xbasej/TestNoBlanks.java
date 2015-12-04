package org.xbasej;
/**
 * xBaseJ - Java access to dBase files
 *<p>Copyright 1997-2014 - American Coders, LTD  - Raleigh NC USA
 *<p>All rights reserved
 *<p>Currently supports only dBase III format DBF, DBT and NDX files
 *<p>                        dBase IV format DBF, DBT, MDX and NDX files
*<p>American Coders, Ltd
*<br>P. O. Box 97462
*<br>Raleigh, NC  27615  USA
*<br>1-919-846-2014
*<br>http://www.americancoders.com
@author Joe McVerry, American Coders Ltd.
@Version 20140310
*
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library Lesser General Public
 * License along with this library; if not, write to the Free
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
*/


import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.geotools.test.TestData;
import org.xbasej.Util;
import org.xbasej.xBaseJException;
import org.xbasej.fields.CharField;
import org.xbasej.fields.DateField;



public class TestNoBlanks extends TestCase {

	public TestNoBlanks(String arg0) {
		super(arg0);
	}

	public void setUp() {
		try {
			super.setUp();
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
			Util.closexBaseJProperty();
			File file = TestData.file(this, "noblanks.xBaseJ.txt");
	                String fileName = file.getAbsolutePath();
			Util.copyFile(fileName, "org.xBaseJ.properties");
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
	}


	public void testBlank(){

		assertTrue(org.xbasej.Util.dontTrimFields()==false);
		/*
		try {
			Util.setxBaseJProperty("fieldFilledWithSpaces", "true");
		} catch (IOException e1) {

			e1.printStackTrace();
			fail(e1.getMessage());
		}*/
		assertTrue(org.xbasej.Util.fieldFilledWithSpaces());



		try {
			CharField f = new CharField("char", 5);
			DateField df = new DateField("date");
			f.put("");
			df.put("");
			assertEquals("     ", f.get());
			assertEquals("        ", df.get());
		} catch (xBaseJException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void tearDown() {
		try {
			Util.closexBaseJProperty();
			File file = TestData.file(this, "reset.xBaseJ.txt");
                        String fileName = file.getAbsolutePath();
			Util.copyFile(fileName, "org.xBaseJ.properties");
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}

	}

}
