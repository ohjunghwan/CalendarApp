package me.devhi.calendar.utils.mvvm

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.devhi.calendar.utils.CALENDAR_HEADER_FORMAT
import me.devhi.calendar.utils.getDate

@BindingAdapter("header_text")
fun setCalendarHeaderText(view: TextView, date: Long?) {
    try {
        if (date != null) {
            view.text = getDate(date, CALENDAR_HEADER_FORMAT)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@BindingAdapter("bind_item")
fun <T> bindItem(view: RecyclerView, item: List<T>?) {
    if (item != null) {
        (view.adapter as ListAdapter<T, RecyclerView.ViewHolder>).apply {
            submitList(item)
        }
    }
}

@BindingAdapter("imageResource")
fun setImageResource(view: ImageView, id: Int?) {
    if (id != null) {
        view.setImageResource(id)
    }
}