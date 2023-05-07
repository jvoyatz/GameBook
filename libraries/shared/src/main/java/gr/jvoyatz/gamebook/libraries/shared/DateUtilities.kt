package gr.jvoyatz.gamebook.libraries.shared

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateUtilities {

    fun getTimeInMillisFromTimestampDate(timestamp: String): Long ? = try {

        //parse timestamp to date
        val dateFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
        val date = dateFormat.parse(timestamp)
        val calendar = Calendar.getInstance()
        calendar.time = date

        //extract elapsed time in seconds
        val elapsedHoursInSeconds = calendar.get(Calendar.HOUR) * Constants.TIME_MINUTES * Constants.TIME_SECONDS
        val elapsedMinutesInSeconds = calendar.get(Calendar.MINUTE) * Constants.TIME_SECONDS
        val elapsedSeconds = calendar.get(Calendar.SECOND)
        val seconds = elapsedHoursInSeconds + elapsedMinutesInSeconds + elapsedSeconds

        //calculate the diff of the
        val diffInMillis = Calendar.getInstance().timeInMillis - (seconds * 1000)

        diffInMillis
    }catch (ignored: Exception){
        null
    }
    fun getDateFromMillis(millis: Long) = Date(millis)
    fun convertMillisToHHMMSS(millis: Long) = convertToHHMMSS(millis / 1000)
    fun convertToHHMMSS(seconds: Long): String {

        //get the total hours till then - the count of days * 24
        val hours = TimeUnit.SECONDS.toHours(seconds)

        //get the total minutes till then - total hours * 60
        val minutes = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60)

        //get the total seconds till then - total Minutes * 60
        val secondsLeft =
            TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60)

        var template = "%02d:%02d:%02d"
        return template.format(hours, minutes, secondsLeft)
    }
}