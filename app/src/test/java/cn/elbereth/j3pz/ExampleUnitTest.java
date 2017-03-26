package cn.elbereth.j3pz;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cn.elbereth.j3pz.dto.LoginError;
import okhttp3.HttpUrl;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse("{\"errors\":[{\"status\":401,\"title\":\"登录失败\",\"detail\":\"邮箱或密码错误\",\"code\":\"LOGIN_FAILED\"}]}").getAsJsonObject();
        JsonElement element = object.get("errors");
        List<LoginError> errors = gson.fromJson(element, new TypeToken<List<LoginError>>() {}.getType());
        Assert.assertNotNull(errors);
    }

    @Test
    public void testUrl() throws Exception {
        String url = "https://www.j3pz.com/api/buff";
//        HttpUrl.Builder builder = new HttpUrl.Builder().host(url);
//        HttpUrl httpUrl = builder.build();
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder().addQueryParameter("school", "bingxin").build();

        System.out.printf(httpUrl.toString());
    }
}