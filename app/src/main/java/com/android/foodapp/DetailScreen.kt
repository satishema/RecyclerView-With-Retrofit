package com.android.foodapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.foodapp.databinding.ActivityDetailScreenBinding
import com.android.foodapp.model.EmployeeVacationBalancesResponse

class DetailScreen : AppCompatActivity() {
    var TAG = "DetailScreen"
    private lateinit var binding: ActivityDetailScreenBinding

    companion object {
        var item: EmployeeVacationBalancesResponse? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_screen)
        Log.d(TAG, "onCreate: $item ")

//        Picasso.get().load(item?.thumb).into(binding.tvThumb)
//        binding.tvName.text = HtmlCompat.fromHtml(
//            "Name : <b>${item?.name}</b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//        binding.tvCalories.text = HtmlCompat.fromHtml(
//            "Calories : <b>${item?.calories}</b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//        binding.tvCarbos.text = HtmlCompat.fromHtml(
//            "Carbos : <b>${item?.carbos}</b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//        binding.tvCountry.visibility = View.GONE
//        binding.tvCountry.text = HtmlCompat.fromHtml(
//            "Country : <b>${item?.country}</b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//        binding.tvDescription.text = HtmlCompat.fromHtml(
//            "Description : <b>${item?.description}</b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//        binding.tvDifficulty.text = HtmlCompat.fromHtml(
//            "Difficulty : <b>${item?.difficulty.toString()}</b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//        binding.tvFats.text = HtmlCompat.fromHtml(
//            "Fats : <b>${item?.fats}</b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//        binding.tvHeadline.text = HtmlCompat.fromHtml(
//            "Headline : <b>${item?.headline}</b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//        binding.tvProteins.text = HtmlCompat.fromHtml(
//            "Proteins : <b>${item?.proteins}</b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//        binding.tvTime.text = HtmlCompat.fromHtml(
//            "Time : <b>${item?.time}</b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
    }
}