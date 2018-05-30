package com.arkkgroup.starwars.sources.characters


import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.rx.rx_object
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.arkkgroup.starwars.entities.Character
import com.arkkgroup.starwars.utils.Constants.SERVICE_URL
import io.reactivex.Single
import org.json.JSONObject
import org.json.JSONArray
import java.util.UUID.*


/**
 * Created by Kalpesh Mote on 5/26/2018.
 */
object CharactersRemoteDataSource : CharactersDataSource {



    internal val typeToken = object : TypeToken<Any>() {}


    /**
     * Deserialize Character Service request
     */
    internal class Deserializer : ResponseDeserializable<List<Character>> {
        override fun deserialize(content: String): List<Character>? {

            val wrapperObject = JSONObject(content);
            val contactArray = JSONArray(wrapperObject.getString("results"))

            val result = Gson().fromJson<List<Any>>(contactArray.toString(), typeToken.type)
            return result.filter { it is Map<*, *> }.map {
                it as Map<*, *>
                Character((randomUUID().getMostSignificantBits() as? Number)?.toLong(), it["name"] as? String, it["height"] as? String, it["mass"] as? String, it["created"] as? String, "")
            }
        }
    }


    /**
     * Load Character from service
     */
    override fun getCharacters(page: String): Single<List<Character>> =
            SERVICE_URL.httpGet()
                    .rx_object(Deserializer())
                    .map {
                        it.component1() ?: throw it.component2() ?: throw Exception()
                    }
}