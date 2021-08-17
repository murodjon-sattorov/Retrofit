package uz.murodjon_sattorov.retrofitapp.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.baoyz.widget.PullRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.murodjon_sattorov.retrofitapp.R
import uz.murodjon_sattorov.retrofitapp.adapter.post.PostsAdapter
import uz.murodjon_sattorov.retrofitapp.adapter.user.UsersAdapter
import uz.murodjon_sattorov.retrofitapp.api.ApiService
import uz.murodjon_sattorov.retrofitapp.api.BaseResponse
import uz.murodjon_sattorov.retrofitapp.databinding.ActivityMainBinding
import uz.murodjon_sattorov.retrofitapp.model.post.PostModel
import uz.murodjon_sattorov.retrofitapp.model.user.UserModel

class MainActivity : AppCompatActivity(), PullRefreshLayout.OnRefreshListener {

    private lateinit var mainBinding: ActivityMainBinding

    private var userAdapter = UsersAdapter()

    private var postAdapter = PostsAdapter()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.pullRefresh.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES)
        mainBinding.pullRefresh.setColorSchemeColors(
            R.color.purple_200
        )
        mainBinding.pullRefresh.setOnRefreshListener(this)
        mainBinding.pullRefresh.setRefreshing(true)

        loadUsers()
        loadPosts()

    }

    private fun loadUsers() {
        ApiService.apiClient().getUsers().enqueue(object : Callback<BaseResponse<List<UserModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<UserModel>>>,
                response: Response<BaseResponse<List<UserModel>>>
            ) {
                mainBinding.pullRefresh.setRefreshing(false)
                mainBinding.usersRv.layoutManager =
                    StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                userAdapter.userData = response.body()?.data ?: emptyList()
                mainBinding.usersRv.adapter = userAdapter
                Log.d("TTT", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<BaseResponse<List<UserModel>>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun loadPosts() {
        ApiService.apiClient().getPosts().enqueue(object : Callback<BaseResponse<List<PostModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<PostModel>>>,
                response: Response<BaseResponse<List<PostModel>>>
            ) {
                mainBinding.postsRv.layoutManager =
                    StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                postAdapter.postData = response.body()?.data ?: emptyList()
                mainBinding.postsRv.adapter = postAdapter
                Log.d("TTT", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<BaseResponse<List<PostModel>>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onRefresh() {
        loadUsers()
        loadPosts()
    }
}