package dsw.gerumap.app.serializer;

import com.google.gson.*;
import dsw.gerumap.app.maprepository.composite.MapNode;

import java.lang.reflect.Type;

public class AbstractElementAdapter implements JsonSerializer<MapNode>, JsonDeserializer<MapNode> {

    @Override
    public JsonElement serialize(MapNode mapNode, Type typeJson, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(mapNode.getClass().getSimpleName()));
        result.add("properties", context.serialize(mapNode, mapNode.getClass()));

        return result;
    }

    @Override
    public MapNode deserialize(JsonElement jsonElement, Type typeJson, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
//        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            String naziv = jsonObject.get("name").getAsString();
//            String name = typeJson.getTypeName();
//            String packageText = name.substring(0, name.lastIndexOf(".") + 1);

            return context.deserialize(element, Class.forName("dsw.gerumap.app.maprepository.implementation." + naziv.substring(0, 7)));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type", cnfe);
        }
    }
}
