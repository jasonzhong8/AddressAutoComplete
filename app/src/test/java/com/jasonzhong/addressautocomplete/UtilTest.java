package com.jasonzhong.addressautocomplete;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UtilTest {

    @Test
    public void jsonParsingTest(){
        String jsonString = "{\n" +
                "   \"insurance_carriers\":[\n" +
                "      \"21st Century\",\n" +
                "      \"A Central\"]}";
        ArrayList<String> carrierList = Util.parseJSon(jsonString,"insurance_carriers");
        Assert.assertNotNull(carrierList);
        Assert.assertTrue(carrierList.size() == 2);
    }
}
