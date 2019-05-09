package com.dragonlegend.kidstories.Api;

import com.dragonlegend.kidstories.Api.Responses.BaseResponse;
import com.dragonlegend.kidstories.Api.Responses.BookmarkResponse;
import com.dragonlegend.kidstories.Api.Responses.CategoryAllResponse;
import com.dragonlegend.kidstories.Api.Responses.CategoryResponse;
import com.dragonlegend.kidstories.Api.Responses.LoginResponse;
import com.dragonlegend.kidstories.Api.Responses.RegistrationResponse;
import com.dragonlegend.kidstories.Api.Responses.SingleStory;
import com.dragonlegend.kidstories.Api.Responses.StoryAllResponse;
import com.dragonlegend.kidstories.Api.Responses.StoryCategoryResponse;
import com.dragonlegend.kidstories.Api.Responses.StoryReactionResponse;
import com.dragonlegend.kidstories.Api.Responses.StoryResponse;
import com.dragonlegend.kidstories.Model.Story;
import com.dragonlegend.kidstories.Model.User;
import com.dragonlegend.kidstories.Model.UserReg;
import com.dragonlegend.kidstories.Model.UserRegResponse;

import org.w3c.dom.Comment;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("categories")
    Call<CategoryAllResponse> getAllCategories();

    @GET("categories/{id}/stories")
    Call<BaseResponse<CategoryResponse>> getCategory(@Path("id") Integer id);

    @GET("stories")
    Call<StoryAllResponse> getAllStories();

    @GET("stories/{id}")
    Call<StoryResponse> getStory(@Path("id") Integer id);






    @FormUrlEncoded
    @POST("auth/register")
    Call<BaseResponse<RegistrationResponse>> registerUser(@Field("phone") String phone,
                                @Field("email") String email,
                                @Field("password") String passwprd,
                                @Field("first_name") String first_name,
                                @Field("last_name") String last_name);

    @POST("users/profile/update-image")
    @Multipart
    Call<ResponseBody> uploadProfilPic(@Header("Authorization") String token,@Part MultipartBody.Part photo);


    @POST("auth/login")
    @FormUrlEncoded
    Call<LoginResponse> loginUser(@Header("Authorization") String token,
            @Field("email") String email, @Field("password") String password);

    @GET("users/profile")
    Call<LoginResponse> getProfile(@Header("Authorization") String token);

    @PUT("users/profile")
    @FormUrlEncoded
    Call<LoginResponse> updateProfile(@Header("Authorization") String token,@Field("first_name") String fName,@Field("last_name") String lName, @Field("email") String email,@Field("phone") String phone);


    @POST("stories/{storyId}/reactions/{action}")
    Call<StoryReactionResponse> reactToStory(@Path("action") String action, @Path("storyId") String storyId);


    @Multipart
    @POST("stories")
    Call<ResponseBody> addStory(
            @Part("title") RequestBody title,
            @Part("body") RequestBody body,
            @Part("category_id") RequestBody category_id,
            @Part MultipartBody.Part photo,
            @Part("age") RequestBody age,
            @Part("author") RequestBody author,
            @Part("is_premium") RequestBody is_premium

    );

    
    @POST("comments")
    @FormUrlEncoded
    Call<BaseResponse> addComment(@Field("body") String body,
                                     @Field("story_id") String storyid);
    
    @POST("bookmarks/stories/{storyId}")
    Call<BookmarkResponse>addBookmark(@Path("storyId") int storyId);

    @GET("bookmarks/stories")
    Call<BaseResponse<List<Story>>> getBookmarks();

    @DELETE("bookmarks/stories/{storyId}")
    Call<BookmarkResponse>deleteBookmark(@Path("storyId") int storyId);

}
