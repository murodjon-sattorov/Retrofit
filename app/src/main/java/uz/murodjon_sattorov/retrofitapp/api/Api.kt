package uz.murodjon_sattorov.retrofitapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import uz.murodjon_sattorov.retrofitapp.model.post.PostModel
import uz.murodjon_sattorov.retrofitapp.model.user.UserModel

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 8/15/2021
 * @project Retrofit App
 */
interface Api {

    @Headers("app-id:6117d9466b83cb1164d12444")
    @GET("user")
    fun getUsers(): Call<BaseResponse<List<UserModel>>>

    @Headers("app-id:6117d9466b83cb1164d12444")
    @GET("post")
    fun getPosts(): Call<BaseResponse<List<PostModel>>>

}