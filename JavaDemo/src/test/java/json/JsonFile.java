package json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @user EinIce
 * @date 2022/6/14 10:22
 * @Description 读取json文件循环遍历
 **/
public class JsonFile {
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("C:\\Users\\EinIce\\Desktop\\api.json");
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { //读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();
        String data = buffer.toString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.readTree(data).get("paths");
        Map<String, Object> valueList = new HashMap<>();
        Iterator<Map.Entry<String, JsonNode>> fields = msg.fields();
        while (fields.hasNext()){
            Map.Entry<String, JsonNode> str = fields.next();
            valueList.put(str.getKey(),str.getKey());
        }
        valueList.forEach((k,v)->{
            System.out.println(k);
        });
    }

}
