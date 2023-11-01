package IActions;

import Models.mEntrada;
import Models.mEntries;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IApi {
    @GET("entries")
    Call<mEntrada> ConsultaPais();

}