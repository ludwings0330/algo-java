package retrofitTest.API;

import retrofit2.Call;
import retrofit2.http.*;
import retrofitTest.DTO.PostDto;

import java.util.List;

public interface JsonPlaceHolderAPI {
    @GET("posts/{id}")
    public Call<PostDto.Post> getOneByPostId(@Path("id") Long id);

    @GET("posts")
    public Call<List<PostDto.Post>> getAll();

    @POST("posts")
    @Headers({
            "Content-type: application/json; charset=UTF-8"
    })
    public Call<PostDto.Post> savePost(@Body PostDto.Request request);

    @PUT("posts/{id}")
    @Headers({
            "Content-type: application/json; charset=UTF-8"
    })
    public Call<PostDto.Post> modifyPost(@Body PostDto.Post post, @Path("id") Long id);

    @PATCH("posts/{id}")
    @Headers({
            "Content-type: application/json; charset=UTF-8"
    })
    public Call<PostDto.Post> patchPost(@Body PostDto.Post post, @Path("id") Long id);

    @GET("posts")
    public Call<List<PostDto.Post>> getOneByUserId(@Query("userId") Long userId);
}
