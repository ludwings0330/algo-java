package retrofitTest;

import retrofit2.Call;
import retrofit2.Response;
import retrofitTest.API.JsonPlaceHolderAPI;
import retrofitTest.API.JsonPlaceHolderCaller;
import retrofitTest.DTO.PostDto;

import java.io.IOException;
import java.util.List;

public class RetrofitTest {
    static JsonPlaceHolderAPI api;

    public static void main(String[] args) throws IOException {
        api = JsonPlaceHolderCaller.getInstance();
        getOneByIdTest(1l);
        getAllTest();
        savePostTest();
        modifyTest(2l);
        patchTest(1L);
        getOneByUserIdTest(1L);
    }

    private static void getOneByUserIdTest(long userId) throws IOException {
        final Call<List<PostDto.Post>> call = api.getOneByUserId(userId);
        final Response<List<PostDto.Post>> response = call.execute();
        final List<PostDto.Post> posts = response.body();


        System.out.println("---------------- get one by user id --------------------");

        for (var post :
                posts) {
            System.out.println(post);
        }
    }

    private static void patchTest(long id) throws IOException {
        final PostDto.Post post = new PostDto.Post();
        post.setTitle("foo...");
        final Call<PostDto.Post> call = api.patchPost(post, id);
        final Response<PostDto.Post> response = call.execute();
        final PostDto.Post result = response.body();
        System.out.println("------------- patch Test -------------");
        System.out.println(result);
    }

    private static void modifyTest(long id) throws IOException {
        final PostDto.Post post = new PostDto.Post();
        post.setId(id);
        post.setUserId(1L);
        post.setTitle("modify Title");
        post.setBody("modify Body");
        final Call<PostDto.Post> call = api.modifyPost(post, id);
        final Response<PostDto.Post> response = call.execute();
        final PostDto.Post responsePost = response.body();

        System.out.println("------------- modify Test -------------");
        System.out.println(responsePost);
    }

    private static void savePostTest() throws IOException {
        final PostDto.Request request = new PostDto.Request();
        request.setTitle("test Title");
        request.setBody("test body");
        request.setUserId(1l);

        final Call<PostDto.Post> call = api.savePost(request);
        final Response<PostDto.Post> response = call.execute();
        final PostDto.Post post = response.body();

        System.out.println("---------- save Post Test ----------");
        System.out.println(post);
    }

    private static void getAllTest() throws IOException {
        System.out.println("--------- getAll ----------");
        final Call<List<PostDto.Post>> all = api.getAll();
        final Response<List<PostDto.Post>> response = all.execute();
        final List<PostDto.Post> posts = response.body();
        for (var post :
                posts) {
            System.out.println(post);
        }
    }

    private static void getOneByIdTest(Long id) throws IOException {
        final Call<PostDto.Post> call = api.getOneByPostId(id);
        final Response<PostDto.Post> postResponse = call.execute();
        final PostDto.Post post = postResponse.body();

        System.out.println("--------- getOneByPostID ---------");
        System.out.println(post);
    }
}
