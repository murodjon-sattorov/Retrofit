package uz.murodjon_sattorov.retrofitapp.model.user

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 8/15/2021
 * @project Retrofit App
 */

data class UserModel(
    val firstName: String,
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String
)