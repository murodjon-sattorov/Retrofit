package uz.murodjon_sattorov.retrofitapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.murodjon_sattorov.retrofitapp.const.Constants


/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 8/15/2021
 * @project Retrofit App
 */
object ApiService {
    var retrofit: Retrofit? = null

    fun apiClient(): Api{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!.create(Api::class.java)
    }

}