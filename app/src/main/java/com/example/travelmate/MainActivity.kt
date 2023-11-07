package com.example.travelmate

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tourDataUrl = "https://kraina-ua.com/ru/tours/tours-ukraine"
        val tourContainer = findViewById<LinearLayout>(R.id.tourContainer)

        // Загрузка данных и парсинг
        Thread {
            try {
                val doc: Document = Jsoup.connect(tourDataUrl).get()
                Log.d("DEBUG", "HTML загружен: $doc")

                val tourElements: Elements = doc.select(".price, .txt-top, .tour-img")

                Log.d("DEBUG", "Найдено элементов: ${tourElements.size}")

                runOnUiThread {
                    for (element in tourElements) {
                        val view = LayoutInflater.from(this).inflate(R.layout.tour_layout, null)

                        val tourImage = view.findViewById<ImageView>(R.id.tourImage)
                        val tourName = view.findViewById<TextView>(R.id.tourName)
                        val tourPrice = view.findViewById<TextView>(R.id.tourPrice)

                        // Получение данных из элемента
                        val imageUrl = element.select(".img img").attr("data-src")
                        val name = element.select(".txt-top").text()
                        val price = element.select(".price").text()

                        Log.d("DEBUG", "URL изображения: $imageUrl")
                        Log.d("DEBUG", "Название тура: $name")
                        Log.d("DEBUG", "Цена тура: $price")

                        // Создайте параметры изменения размера
                        val requestOptions = RequestOptions()
                            .override(300, 200) // Установите новый размер, например, 300x200

                        // Загрузка изображения с параметрами изменения размера
                        Glide.with(this)
                            .load(imageUrl)
                            .apply(requestOptions) // Примените параметры изменения размера
                            .into(tourImage)

                        tourName.text = name
                        tourPrice.text = price

                        tourContainer.addView(view)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ERROR", "Ошибка при загрузке данных: ${e.message}")
            }
        }.start()
    }
}

