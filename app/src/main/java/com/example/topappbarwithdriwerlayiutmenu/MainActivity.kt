package com.example.topappbarwithdriwerlayiutmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.topappbarwithdriwerlayiutmenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Этот метод назначает Toolbar выполнять функции ActonBar.
        // ActonBar - управляющий элемент системы, например туда выводятся меню, заголовки, кнопки управления навигацией и тп.
        setSupportActionBar(binding?.topAppBar)

        // При загрузке приложения подгружаем сразу Фрагмент: Home в наш FrameLayout(контент)
        supportFragmentManager.beginTransaction().replace(R.id.content, Home()).commit()
        // Указываем что при загрузке приложения именно item: home будет активно нажат на BottomNavigation
        binding?.bottomNav?.selectedItemId = R.id.homeBottomNavigation

        // Этот метод определяет нажатия на Айтемы BottomNavigation
        binding?.bottomNav?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeBottomNavigation -> supportFragmentManager.beginTransaction()
                    .replace(R.id.content, Home()).commit()
                R.id.uploadBottomNavigation -> supportFragmentManager.beginTransaction()
                    .replace(R.id.content, Upload()).commit()
                R.id.favoritesBottomNavigation -> supportFragmentManager.beginTransaction()
                    .replace(R.id.content, Favorites()).commit()
                R.id.settingsBottomNavigation -> supportFragmentManager.beginTransaction()
                    .replace(R.id.content, Settings()).commit()
            }
            return@setOnItemSelectedListener true
        }

        // Метод определяет нажатия на иконки(кнопки) TopAppBar
        binding?.topAppBar?.setOnMenuItemClickListener { menuItem: MenuItem ->
            // Говорим: Когда itemId - то...
            when (menuItem.itemId) {
                R.id.favoritesTopAppBar -> {
                    // Подгружает в нашу разметку XML, во FrameLayout - Фрагмент: Favorites
                    supportFragmentManager.beginTransaction().replace(R.id.content, Favorites())
                        .commit()
                    true
                }
                R.id.settingsTopAppBar -> {
                    supportFragmentManager.beginTransaction().replace(R.id.content, Settings())
                        .commit()
                    true
                }
                R.id.uploadTopAppBar -> {
                    supportFragmentManager.beginTransaction().replace(R.id.content, Upload())
                        .commit()
                    true
                }
                // Если нет действия по элементу TopAppBar, то во when передается false и условие не срабатывает просто.
                else -> false
            }
        }
    }

    // Когда меню на TopAppBare открывается впервые, сразу вызывается метод onCreateOptionsMenu()
    // Вызывается системой только один раз при создании меню.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Подгружает наше созданное меню top_app_bar.xml на сам TopAppBar
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    // Этот метод назначает действие на кнопку home(Гамбургер) на TopAppBare (Она почему то только здесь работает)
    // Подгружает Активити с DialogSheetMenu (Выезжаюзую менюшку)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val mainMenu = DialogMenuActivity()
                mainMenu.show(supportFragmentManager, "activity_dialog_menu")
                return super.onOptionsItemSelected(item)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}