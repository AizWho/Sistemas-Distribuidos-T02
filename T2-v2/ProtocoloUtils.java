import com.google.gson.Gson;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.Type;

// Classe auxiliar para Marshal/Unmarshal (Serialização/Desserialização JSON)
public class ProtocoloUtils {
    private static final Gson GSON = new Gson();

    // Empacota (Marshal) o argumento/resultado em um array de bytes (JSON)
    public static byte[] marshal(Object obj) {
        if (obj == null) return new byte[0];
        
        String json = GSON.toJson(obj);
        return json.getBytes(StandardCharsets.UTF_8);
    }

    // Desempacota (Unmarshal) o JSON de volta para um objeto
    public static <T> T unmarshal(byte[] bytes, Class<T> targetClass) {
        if (bytes == null || bytes.length == 0) return null;
        
        String json = new String(bytes, StandardCharsets.UTF_8);
        return GSON.fromJson(json, targetClass);
    }
    
    // Sobrecarga para tipos complexos (Usado para desserializar Livro no Skeleton)
    public static <T> T unmarshal(byte[] bytes, Type targetType) {
        if (bytes == null || bytes.length == 0) return null;
        
        String json = new String(bytes, StandardCharsets.UTF_8);
        return GSON.fromJson(json, targetType);
    }
}