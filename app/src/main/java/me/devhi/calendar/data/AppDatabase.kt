package me.devhi.calendar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.devhi.calendar.data.dao.DayDao
import me.devhi.calendar.data.dao.MonthDao
import me.devhi.calendar.data.vo.DayVO
import me.devhi.calendar.data.vo.MonthVO
import me.devhi.calendar.data.vo.Type
import java.util.*

@Database(entities = [DayVO::class, MonthVO::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dayDao(): DayDao
    abstract fun monthDao(): MonthDao


    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val NUMBER_OF_MONTH = 40

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = createInstance(context)
                }
            }
            return INSTANCE!!
        }

        private fun createInstance(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "DataBase.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch {
                            prepopulateDb(getInstance(context))
                        }
                    }
                }).fallbackToDestructiveMigration().build()

        private fun prepopulateDb(db: AppDatabase?) {
            if (db == null) {
                return
            }

            GlobalScope.launch {
                val monthDao = db.monthDao()
                val dayDao = db.dayDao()
                val calendar = GregorianCalendar()


                for (i in -NUMBER_OF_MONTH..NUMBER_OF_MONTH) {
                    try {
                        val calendar = GregorianCalendar(
                            calendar[Calendar.YEAR],
                            calendar[Calendar.MONTH] + i,
                            1,
                            0,
                            0,
                            0
                        )

                        val monthId = monthDao.insert(MonthVO(calendar.timeInMillis))

                        val dayOfWeek = calendar[Calendar.DAY_OF_WEEK] - 1
                        val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                        for (j in 0 until dayOfWeek) {
                            dayDao.insertDay(DayVO(Type.EMPTY, monthId))
                        }
                        for (j in 1..max) {
                            dayDao.insertDay(
                                DayVO(
                                    Type.NORMAL, monthId, GregorianCalendar(
                                        calendar[Calendar.YEAR],
                                        calendar[Calendar.MONTH],
                                        j
                                    )
                                )
                            )
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

    }
}