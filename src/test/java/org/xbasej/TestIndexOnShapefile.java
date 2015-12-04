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
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.geotools.TestData;
import org.xbasej.indexes.Index;
import org.xbasej.indexes.MDX;
import org.xbasej.indexes.MDXFile;

import junit.framework.TestCase;

public class TestIndexOnShapefile extends TestCase {

    public void testMDX() throws Exception {// test MDX updated by DBF Manager
        DBF aDB = null;
        Util.setxBaseJProperty("ignoreMissingMDX", "True");
        TestData.copy(this, "shapes/statepop.dbf");
        File file = TestData.file(this, "shapes/statepop.dbf");
        String fileName = file.getAbsolutePath();
        String basename = FilenameUtils.getBaseName(fileName);
        String ndxName = file.getParent() + File.pathSeparator + basename;
        aDB = new DBF(fileName);
        aDB.setVersion(DBFTypes.DBASEIV);

        int count = aDB.getFieldCount();
        /*for (int i = 1; i <= count; i++) {
            System.out.println("" + i + ":" + aDB.getField(i));

        }*/
        String name = "STATE_NAME";
        //MDXFile mdxFile = new MDXFile(ndxName + ".mdx", aDB, true);
        String tag = ndxName + "_" + name + ".ndx";
        Index idx = aDB.createIndex(tag, name, true, true);

        aDB.useIndex(idx);
        // aDB.useTag(mxTag.getName());
        assertFalse(aDB.find("Ian"));
        assertFalse(aDB.find("Wyomifornia"));
        assertFalse(aDB.find("Canada"));
        aDB.pack();
        long start = new Date().getTime();
        assertTrue(aDB.find("Washington"));
        assertTrue(aDB.find("Pennsylvania"));
        assertTrue(aDB.find("Colorado"));
        long end = new Date().getTime();
        System.out.println("with index " + (end - start) +" ms");

    }
}
