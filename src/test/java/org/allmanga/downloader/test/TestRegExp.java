package org.allmanga.downloader.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 03.10.2010
 * Time: 3:23:04
 */
public class TestRegExp {

    private Object[] images_result = new Object[]{
            new Object[]{"001.jpg", 1200, 507},
            new Object[]{"002.jpg", 830, 1200},
            new Object[]{"003.jpg", 830, 1200},
            new Object[]{"004.jpg", 830, 1200},
            new Object[]{"005.jpg", 830, 1200},
            new Object[]{"006.jpg", 830, 1200},
            new Object[]{"007.jpg", 830, 1200},
            new Object[]{"008.jpg", 830, 1200},
            new Object[]{"009.jpg", 830, 1200},
            new Object[]{"010.jpg", 830, 1200},
            new Object[]{"011.jpg", 830, 1200},
            new Object[]{"012.jpg", 830, 1200},
            new Object[]{"013.jpg", 830, 1200},
            new Object[]{"014.jpg", 830, 1200},
            new Object[]{"015.jpg", 830, 1200},
            new Object[]{"016.jpg", 830, 1200},
            new Object[]{"017.jpg", 830, 1200},
            new Object[]{"018.jpg", 830, 1200},
            new Object[]{"019.jpg", 830, 1200},
            new Object[]{"020.jpg", 830, 1200},
            new Object[]{"021.jpg", 830, 1200},
            new Object[]{"022.jpg", 830, 1200},
            new Object[]{"023.jpg", 830, 1200},
            new Object[]{"024.jpg", 830, 1200},
            new Object[]{"025.jpg", 830, 1200},
            new Object[]{"026.jpg", 830, 1200},
            new Object[]{"027.jpg", 830, 1200},
            new Object[]{"028.jpg", 830, 1200},
            new Object[]{"029.jpg", 830, 1200},
            new Object[]{"030.jpg", 830, 1200},
            new Object[]{"031.jpg", 830, 1200},
            new Object[]{"032.jpg", 830, 1200},
            new Object[]{"033.jpg", 830, 1200},
            new Object[]{"034.jpg", 830, 1200},
            new Object[]{"035.jpg", 830, 1200},
            new Object[]{"036.jpg", 830, 1200},
            new Object[]{"037.jpg", 830, 1200},
            new Object[]{"038.jpg", 900, 1100}
    };


    @Test
    public void testRegExp() {
        String patterString = ".*Reader.init\\((\\{.*\\})\\).*";
        Pattern pattern = Pattern.compile(patterString, Pattern.DOTALL);
        Matcher matcher = pattern.matcher("$(document).ready(function() {\n" +
                "\tReader.init({\n" +
                "\t\tmirror: 8,\n" +
                "\t\tdir: \"http:\\/\\/img8.manga24.ru\\/csword\\/006\\/\",\n" +
                "\t\tmangaUrl: \"\\/csword\\/\",\n" +
                "\t\timages: [[\"001.jpg\",1200,507],[\"002.jpg\",830,1200],[\"003.jpg\",830,1200],[\"004.jpg\",830,1200],[\"005.jpg\",830,1200],[\"006.jpg\",830,1200],[\"007.jpg\",830,1200],[\"008.jpg\",830,1200],[\"009.jpg\",830,1200],[\"010.jpg\",830,1200],[\"011.jpg\",830,1200],[\"012.jpg\",830,1200],[\"013.jpg\",830,1200],[\"014.jpg\",830,1200],[\"015.jpg\",830,1200],[\"016.jpg\",830,1200],[\"017.jpg\",830,1200],[\"018.jpg\",830,1200],[\"019.jpg\",830,1200],[\"020.jpg\",830,1200],[\"021.jpg\",830,1200],[\"022.jpg\",830,1200],[\"023.jpg\",830,1200],[\"024.jpg\",830,1200],[\"025.jpg\",830,1200],[\"026.jpg\",830,1200],[\"027.jpg\",830,1200],[\"028.jpg\",830,1200],[\"029.jpg\",830,1200],[\"030.jpg\",830,1200],[\"031.jpg\",830,1200],[\"032.jpg\",830,1200],[\"033.jpg\",830,1200],[\"034.jpg\",830,1200],[\"035.jpg\",830,1200],[\"036.jpg\",830,1200],[\"037.jpg\",830,1200],[\"038.jpg\",900,1100]],\n" +
                "\t\tpage: 0,\n" +
                "\t\tprevChapter: \"005\",\n" +
                "\t\tnextChapter: \"\",\n" +
                "\t\tprefix: \"\"\t});\n" +
                "});");
        if (matcher.matches()) {
            JSONObject json = (JSONObject) JSONSerializer.toJSON(matcher.group(1));
            JSONArray images = (JSONArray) json.get("images");
            JSONArray images_need = new JSONArray();
            for (Object item : images_result) {
                images_need.add(item);
            }
            Assert.assertEquals(images_need, images);
        } else {
            Assert.assertFalse("Matcher not found", true);
        }
    }

}
