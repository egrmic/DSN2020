package jakopec.mvpcrud.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//singleton princip
public class RetrofitKlijent {

    public static final String REST_URL="https://oziz.ffos.hr/DSN2020/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(REST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
