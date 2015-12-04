package org.xbasej;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.geotools.test.TestData;
import org.junit.Test;
import org.xbasej.DBF;
import org.xbasej.xBaseJException;
import org.xbasej.fields.CharField;

public class Clipper_Large_CharFieldTest {

    // @Test
    // public void testWithBalsetFile() {
    // try {
    // DBF clipperDBF = new DBF("BALSET.DBF");
    // for (int f=1; f <= clipperDBF.getFieldCount(); f++)
    // {
    // System.out.println(clipperDBF.getField(f).toString());
    // }
    //
    // clipperDBF.copyTo("balsetII.dbf");
    // } catch (xBaseJException e) {
    // fail(e.getMessage());
    // e.printStackTrace();
    // } catch (IOException e) {
    // fail(e.getMessage());
    // e.printStackTrace();
    // }
    // }
    @Test
    public void testBuildNew() {
        try {
            File file = TestData.temp(this, "balsetIII.dbf");
            String fileName = file.getAbsolutePath();
            System.out.println(fileName);
            CharField scf = new CharField("short", 10);
            CharField lcf = new CharField("long", 510);
            DBF newone = new DBF(fileName, true);
            newone.addField(scf);
            newone.addField(lcf);
            newone.close();
            newone = new DBF(fileName);

            for (int f = 1; f <= newone.getFieldCount(); f++) {
                System.out.println(newone.getField(f).toString());
            }

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 510; i++)
                sb.append('s');
            newone.getField("long").put(sb.toString());
            newone.write();
            newone.close();

            newone = new DBF(fileName);
            newone.read();
            System.out.println(newone.getField("long").get().length());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

}
