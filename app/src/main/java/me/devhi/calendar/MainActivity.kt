package me.devhi.calendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.devhi.calendar.data.AppDatabase
import me.devhi.calendar.databinding.ActivityMainBinding
import me.devhi.calendar.repository.CalenderRepositoryImpl
import me.devhi.calendar.ui.adapter.MonthAdapter
import me.devhi.calendar.ui.dialog.MemoAddDialog
import me.devhi.calendar.viewmodel.MonthListViewModel

class MainActivity : AppCompatActivity() {
    private val adapter by lazy { MonthAdapter(viewModel) }
    private val viewModel by lazy {
        MonthListViewModel(
            CalenderRepositoryImpl(
                AppDatabase.getInstance(this@MainActivity).monthDao(),
                AppDatabase.getInstance(this@MainActivity).dayDao()
            )
        )
    }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@MainActivity
            binding.pagerCalendar.adapter = adapter
        }

        binding.pagerCalendar.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val position =
                    (binding.pagerCalendar.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (position != viewModel.currentPosition) {
                    viewModel.updatePosition(position)
                }
            }
        })

        observeVM()
    }

    private fun observeVM() {
        viewModel.goToPosition.observe(this) {
            binding.pagerCalendar.scrollToPosition(it)
        }

        viewModel.showMemoDialog.observe(this) {
            MemoAddDialog(it, viewModel::addMemo).show(supportFragmentManager, "MemoAddDialog")
        }

        viewModel.monthList.observe(this) {
            it.forEach { it.days.forEach { println(it.memo) } }
            if (it != null) {
                viewModel.shouldInitialize()
            }
        }

    }
}