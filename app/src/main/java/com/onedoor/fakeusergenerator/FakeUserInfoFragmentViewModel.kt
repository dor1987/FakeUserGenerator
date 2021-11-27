package com.onedoor.fakeusergenerator

import androidx.lifecycle.ViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class FakeUserInfoFragmentViewModel(val fakeUser: FakeUser) :
    ViewModel() {
    var timeToBirthDayInMilli: Long = 0
    init {
        timeToBirthDayInMilli = getTimeUntilBirthDayInMil(fakeUser.date)
    }

    private fun getTimeUntilBirthDayInMil(rawBirthDay: String): Long {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = inputFormat.parse(rawBirthDay)
        val formattedDate = outputFormat.format(date)

        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        var dateOB = formattedDate.split("/".toRegex())
            .toTypedArray()[0] + "/" + formattedDate.split("/".toRegex())
            .toTypedArray()[1] + "/" + Calendar.getInstance()[Calendar.YEAR]
        if (Calendar.getInstance()[Calendar.YEAR] % 4 !== 0 && formattedDate.split("/".toRegex())
                .toTypedArray()[0].toInt() == 29 && formattedDate.split("/".toRegex())
                .toTypedArray()[1].toInt() == 2
        ) {
            dateOB =
                Calendar.getInstance()[Calendar.DAY_OF_MONTH].toString() +
                        Calendar.getInstance()[Calendar.MONTH].toString() +
                        Calendar.getInstance()[Calendar.YEAR].toString()
        }
        return try {
            var bday = format.parse(dateOB)
            val today = Calendar.getInstance().time
            assert(bday != null)
            if (bday!!.before(today)) {
                val c = Calendar.getInstance()
                c.time = bday
                c.add(Calendar.YEAR, 1)
                bday = Date(c.timeInMillis)
            }
            bday.time - today.time
        } catch (e: ParseException) {
            e.printStackTrace()
            -1
        }
    }


}