package com.transcendence.core.publicModule.smartrefreshlayout.widget

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseViewHolder
//import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author joephone
 * @date 2023/4/3
 * @desc
 */
open class BaseDataBindingHolder<BD : ViewDataBinding>(view: View) : BaseViewHolder(view) {

    val dataBinding = DataBindingUtil.bind<BD>(view)
}