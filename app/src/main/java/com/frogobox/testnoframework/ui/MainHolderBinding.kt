package com.frogobox.testnoframework.ui

/*
 * Created by faisalamir on 06/01/22
 * nf-testcase-app-no-framework
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 FrogoBox Inc.
 * All rights reserved
 *
 */


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frogobox.testnoframework.databinding.ItemMainBinding
import com.frogobox.testnoframework.model.Article

class MainHolderBinding(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(data: Article, listener: MainClickListener) {
        binding.apply {
            nutriRvListType11TvTitle.text = data.title
            nutriRvListType11TvDesc.text = data.description
            nutriRvListType11TvSubtitle.text = data.source?.name ?: "Unknown"
            Glide.with(root.context).load(data.urlToImage).into(nutriRvListType11IvPoster)

            root.setOnClickListener {
                listener.onClickListener(data)
            }
        }
    }

}