package com.transcendence.core.listener

/**
 * @author joephone
 * @date 2023/7/15
 * @desc
 */
interface OnSliderClickerListener<T> {

    fun onDelete(position: Int,t:T)
    fun onCopy(position: Int,t:T)
    fun onOpen(position: Int,t:T)

}