package com.example.topappbarwithdriwerlayiutmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.topappbarwithdriwerlayiutmenu.databinding.ActivityDialogMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// Это полноценное активити, но это BottomSheetDialogFragment (Фрагмент)
class DialogMenuActivity : BottomSheetDialogFragment() {

    private var binding: ActivityDialogMenuBinding? = null

    // Стандартный метод для реализации BottomSheetDialogFragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Привязываем activity_dialog_menu.xml к Классу
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_dialog_menu, container, false)

        // Прослушиватель табов в Bottom Dialog Sheet Menu
        binding?.navigationView?.setNavigationItemSelectedListener { menuItem ->
            // Когда нажат айтем - то...
            when (menuItem.itemId) {
                // Просто первый Айтем
                R.id.homeMenu -> {
                    // Загружает MainActivity
                    val startHome = Intent(context, MainActivity::class.java)
                    val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((context as FragmentActivity))
                    startActivity(startHome, activityOptionsCompat.toBundle())
                }
                R.id.shopMenu -> {

                }
                R.id.favoritesMenu -> {
                    // Подгружаем Фрагмент во FrameLayout
                    //(context as FragmentActivity) нужен потому, что СаппортФрагментМенеджер работает только из полноценного Активити (А у нас Фрагмент получается)
                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, Favorites()).commit()
                    // Закрытие DialogSheetMenu (Панели)
                    dismiss()
                }
                R.id.accountMenu -> {

                }
                R.id.contactMenu -> {

                }
                R.id.videoMenu -> {

                }
                R.id.locationMenu -> {
                }
                R.id.settingsMenu -> {
                }
                R.id.helpMenu -> {

                }
                R.id.developerMenu -> {

                }
            }
            // Должен быть в методе setNavigationItemSelectedListener
            true
        }
        // Должен быть в главном методе onCreateView
        return binding?.root
    }
}