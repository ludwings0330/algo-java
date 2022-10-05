package retrofitTest.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonPlaceHolderCaller {
    private final static String baseURL = "https://jsonplaceholder.typicode.com/";
    static JsonPlaceHolderAPI instance;

    public static JsonPlaceHolderAPI getInstance() {
        if (instance == null) {
            instance = createInstance().create(JsonPlaceHolderAPI.class);
        }
        return instance;
    }

    private static Retrofit createInstance() {
        final Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseURL)
                .build();
    }
}
