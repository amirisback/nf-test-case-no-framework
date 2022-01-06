package com.frogobox.testnoframework

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.testnoframework.databinding.ActivityMainBinding
import com.frogobox.testnoframework.model.Article
import com.frogobox.testnoframework.model.ArticleResponse
import com.frogobox.testnoframework.sources.ConsumeNewsApi
import com.frogobox.testnoframework.sources.NutriResponse
import com.frogobox.testnoframework.ui.MainAdapter
import com.frogobox.testnoframework.ui.MainClickListener
import com.frogobox.testnoframework.util.NewsConstant
import com.frogobox.testnoframework.util.NewsUrl
import com.google.gson.Gson

class MainActivity : BaseActivity<ActivityMainBinding>(), MainClickListener {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "MainActivity"

        val consumeNewsApi = ConsumeNewsApi(NewsUrl.API_KEY)
        consumeNewsApi.getTopHeadline(
            null,
            null,
            null,
            NewsConstant.COUNTRY_ID,
            null,
            null,
            object : NutriResponse.DataResponse<ArticleResponse> {
                override fun onShowProgress() {
                    
                }

                override fun onHideProgress() {
                    
                }

                override fun onEmpty() {
                    
                }

                override fun onSuccess(data: ArticleResponse) {
                    runOnUiThread {
                        data.articles?.let { setupRv(it) }
                    }
                }

                override fun onFailed(statusCode: Int, errorMessage: String?) {
                    errorMessage?.let { showToast(it) }
                }

            })

    }

    private fun setupRv(data: List<Article>) {
        val mainAdapter = MainAdapter(this)
        mainAdapter.setContent(data)

        binding.rvMain.apply {
            adapter = mainAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun onClickListener(data: Article) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, Gson().toJson(data))
        startActivity(intent)
    }


}