package com.arkkgroup.starwars.utils

import java.text.SimpleDateFormat


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * Utility class for Starwars Application
 */

class Utility {

    companion object {
        /**
         * To get Metres data from cm.
         */
        fun getMetersFromCM(cms: Int): String {
            val meters = cms.toDouble() / 100
            return meters.toString()
        }


        /**
         * To get formatted for cards
         */
        fun processDate(date: String): String {

            val df1 = SimpleDateFormat(Constants.DATE_FORMAT)
            val createdDate: String? = date.substring(0, date.length - 1);
            val formattedDate = df1.parse(createdDate.toString())
            val targetFormat = SimpleDateFormat(Constants.DATE_FORMAT_V2)
            val newDate = targetFormat.format(formattedDate)

            return newDate.toString()
        }
    }

}
