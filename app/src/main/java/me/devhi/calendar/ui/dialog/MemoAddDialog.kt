package me.devhi.calendar.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.devhi.calendar.R
import me.devhi.calendar.data.vo.DayVO
import me.devhi.calendar.databinding.DialogAddMemoBinding

class MemoAddDialog(
    private val dayVO: DayVO,
    private val onEditCompleted: (DayVO) -> Unit
) : BottomSheetDialogFragment() {
    lateinit var binding: DialogAddMemoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.dialog_add_memo,
            container,
            false
        )

        binding.apply {
            day = dayVO
            content = dayVO.memo
            lifecycleOwner = this@MemoAddDialog
            cancelButton.setOnClickListener { dismiss() }
            confirmButton.setOnClickListener {
                onClickConfirmButton()
            }
            emotionPicker.setEmotionState(dayVO.emotion)
        }
        return binding.root
    }

    private fun onClickConfirmButton() {
        onEditCompleted(
            dayVO.apply {
                memo = binding.memo.text.toString()
                emotion = binding.emotionPicker.emotion
            }
        )
        dismiss()
    }
}