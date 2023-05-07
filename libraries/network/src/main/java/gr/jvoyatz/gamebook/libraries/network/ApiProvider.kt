package gr.jvoyatz.gamebook.libraries.network


import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import gr.jvoyatz.gamebook.libraries.network.config.AuthBearerTokenInterceptor
import gr.jvoyatz.gamebook.libraries.network.config.ConnectivityInterceptor
import gr.jvoyatz.gamebook.libraries.network.config.CustomLoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Contains the base configuration used for Retrofit ApiServices
 */
internal object ApiProvider {
    private const val TIMEOUT = 5L
    private const val TAG = "GameBookApi"

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor {
            Timber.tag(TAG).d(it)
        }.apply {
            level =
              //  if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                HttpLoggingInterceptor.Level.NONE
        }
    }

    private val moshi by lazy {
        with(Moshi.Builder()){
            addLast(KotlinJsonAdapterFactory())
        }.run {
            build()
        }
    }

    private fun getOkHttpClient(context: Context): OkHttpClient{
        return OkHttpClient.Builder().apply {

            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)

            addInterceptor(CustomLoggingInterceptor())
            addInterceptor(ConnectivityInterceptor(context))
            addInterceptor(AuthBearerTokenInterceptor(BuildConfig.TOKEN))
            addInterceptor(loggingInterceptor)
        }.run {
            build()
        }
    }
    internal inline fun <reified T> getApi(context: Context): T = Retrofit.Builder().apply {
        baseUrl(BuildConfig.HOST)
        addConverterFactory(MoshiConverterFactory.create(moshi))
        client(getOkHttpClient(context))
    }.let {
        it.build()
    }.run {
        create()
    }
}