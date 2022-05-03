package me.devhi.calendar.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import me.devhi.calendar.R
import me.devhi.calendar.data.vo.Emotion
import me.devhi.calendar.uistate.toDrawableId

class EmotionPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val imageViewMap = mutableMapOf<Emotion, ImageView>()
    var emotion: Emotion? = null
        private set

    init {
        initEmotions()
    }

    private fun initEmotions() {
        Emotion.values()
            .map { it to it.toDrawableId() }
            .map { (emotion, drawableId) ->
                val imageView = ImageView(context).apply {
                    layoutParams = LayoutParams(100, 100)
                    setPadding(5, 5, 5, 5)
                    setImageResource(drawableId)
                }
                emotion to imageView
            }.forEach { (emotion, view) ->
                view.setOnClickListener(this::onItemClick)
                imageViewMap[emotion] = view
                addView(view)
            }
    }


    fun setEmotionState(emotion: Emotion?) {
        this.emotion = emotion
        imageViewMap[emotion]?.setBackgroundColor(resources.getColor(R.color.colorAccent))
    }

    private fun onItemClick(view: View) {
        emotion = imageViewMap.entries.find { it.value == view }?.key
        imageViewMap[emotion]?.setBackgroundColor(resources.getColor(R.color.colorAccent))
        imageViewMap
            .filterNot { it.key == emotion }
            .forEach { it.value.setBackgroundColor(0) }
    }
}