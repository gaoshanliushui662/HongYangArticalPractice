package example.com.rxjavademo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by zhao.yaosheng on 2018/3/14.
 */

public interface GetRequest_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();

}
